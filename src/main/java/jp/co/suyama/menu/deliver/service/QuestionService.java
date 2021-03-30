package jp.co.suyama.menu.deliver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import jp.co.suyama.menu.deliver.common.S3Access;
import jp.co.suyama.menu.deliver.exception.MenuDeliverException;
import jp.co.suyama.menu.deliver.mapper.AnswerImagesMapperImpl;
import jp.co.suyama.menu.deliver.mapper.AnswersMapperImpl;
import jp.co.suyama.menu.deliver.mapper.QuestionImagesMapperImpl;
import jp.co.suyama.menu.deliver.mapper.QuestionsMapperImpl;
import jp.co.suyama.menu.deliver.mapper.UsersMapperImpl;
import jp.co.suyama.menu.deliver.model.QuestionAndPage;
import jp.co.suyama.menu.deliver.model.auto.AnswerData;
import jp.co.suyama.menu.deliver.model.auto.PageNation;
import jp.co.suyama.menu.deliver.model.auto.QuestionCategoryData;
import jp.co.suyama.menu.deliver.model.auto.QuestionData;
import jp.co.suyama.menu.deliver.model.db.AnswerImages;
import jp.co.suyama.menu.deliver.model.db.Answers;
import jp.co.suyama.menu.deliver.model.db.QuestionImages;
import jp.co.suyama.menu.deliver.model.db.Questions;
import jp.co.suyama.menu.deliver.model.db.Users;
import jp.co.suyama.menu.deliver.utils.ConvertUtils;
import jp.co.suyama.menu.deliver.utils.PageNationUtils;
import jp.co.suyama.menu.deliver.utils.PathUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class QuestionService {

    // S3アクセス
    @Autowired
    private S3Access s3Access;

    // 質問テーブル
    @Autowired
    private QuestionsMapperImpl questionsMapper;

    // 質問画像テーブル
    @Autowired
    private QuestionImagesMapperImpl questionImagesMapper;

    // 回答テーブル
    @Autowired
    private AnswersMapperImpl answersMapper;

    // 回答画像テーブル
    @Autowired
    private AnswerImagesMapperImpl answerImagesMapper;

    // ユーザテーブル
    @Autowired
    private UsersMapperImpl usersMapper;

    /**
     * 回答を投稿する
     *
     * @param email    メールアドレス
     * @param id       質問ID
     * @param contents 回答内容
     * @param img      回答画像
     */
    public void postAnswer(String email, int id, String contents, MultipartFile img) {

        // ユーザ情報取得
        Users users = usersMapper.selectEmail(email);

        // ユーザが存在しない場合はエラー
        if (users == null) {
            throw new MenuDeliverException("ユーザが存在しません。");
        }

        // 質問情報取得
        Questions question = questionsMapper.selectByPrimaryKey(id);

        // 質問が存在しない場合はエラー
        if (question == null) {
            throw new MenuDeliverException("質問が存在しません。");
        }

        // 回答テーブルに登録する
        Answers answer = new Answers();
        answer.setQuestionId(question.getId());
        answer.setUserId(users.getId());
        answer.setContents(contents);

        int answerId = answersMapper.registAnswer(answer);

        // 回答画像があれば登録する
        if (!img.isEmpty()) {

            // 画像パスを取得する
            String imgPath = PathUtils.createAnswerImagePath(answerId, img.getOriginalFilename());
            AnswerImages answerImage = new AnswerImages();
            answerImage.setAnswerId(answerId);
            answerImage.setPath(imgPath);

            answerImagesMapper.registAnswerImage(answerImage);

            // S3にアップロードする
            s3Access.uploadAnswerImage(imgPath, ConvertUtils.convertFile(img));
        }
    }

    /**
     * 回答一覧を取得する
     *
     * @param id 質問ID
     * @return 回答一覧
     */
    public List<AnswerData> getAnswers(int id) {

        // 質問情報を取得する
        Questions question = questionsMapper.selectByPrimaryKey(id);

        // 存在しない場合エラー
        if (question == null) {
            throw new MenuDeliverException("質問が存在しません。");
        }

        // 回答一覧を取得する
        List<Answers> answers = answersMapper.selectAllByQuestionId(id);

        return convertAnswerDataList(answers);

    }

    /**
     * 質問を投稿する
     *
     * @param email    メールアドレス
     * @param contents 質問内容
     * @param img      質問画像
     */
    public void postQuestion(String email, String contents, MultipartFile img) {

        // ユーザ情報取得
        Users users = usersMapper.selectEmail(email);

        // ユーザが存在しない場合はエラー
        if (users == null) {
            throw new MenuDeliverException("ユーザが存在しません。");
        }

        // 質問テーブルに登録する
        Questions question = new Questions();
        question.setUserId(users.getId());
        question.setContents(contents);

        int questionId = questionsMapper.registQuestion(question);

        // 質問画像があれば登録する
        if (!img.isEmpty()) {

            // 画像パスを取得する
            String imgPath = PathUtils.createQuestionImagePath(questionId, img.getOriginalFilename());
            QuestionImages questionImage = new QuestionImages();
            questionImage.setQuestionId(questionId);
            questionImage.setPath(imgPath);

            questionImagesMapper.registQuestionImage(questionImage);

            // S3にアップロードする
            s3Access.uploadQuestionImage(imgPath, ConvertUtils.convertFile(img));
        }
    }

    /**
     * 質問のカテゴリを取得する
     *
     * @return カテゴリリスト
     */
    public List<QuestionCategoryData> getCategories() {

        List<QuestionCategoryData> categories = new ArrayList<>();

        // カテゴリ情報は「未解決」「解決済み」の２つあるため、DBに持っていない
        QuestionCategoryData category = new QuestionCategoryData();
        category.setId(1);
        category.setName("未解決");
        categories.add(category);

        category = new QuestionCategoryData();
        category.setId(2);
        category.setName("解決済み");
        categories.add(category);

        return categories;
    }

    /**
     * 新着質問を取得する
     *
     * @param keywordList 検索キーワード
     * @param page        取得ページ
     * @return 質問一覧
     */
    public QuestionAndPage searchQuestionNewArrival(List<String> keywordList, int page) {

        // レスポンス
        QuestionAndPage result = new QuestionAndPage();

        // 取得件数
        int limit = 8;

        // 全体の件数を取得する
        int count = questionsMapper.countQuestionNewArrival(keywordList);

        // ページネーションを取得
        PageNation pageNation = PageNationUtils.createPageNation(page, count, limit);

        // 取得ページからoffsetを計算する
        int offset = (pageNation.getCurrentPage() - 1) * limit;

        // 検索処理
        List<Questions> questionsList = questionsMapper.searchQuestionNewArrival(keywordList, limit, offset);

        // レスポンスに値を設定
        result.setQuestionDataList(convertQuestionDataList(null, questionsList));
        result.setPage(pageNation);

        return result;
    }

    /**
     * 未解決・解決済みの質問を検索する
     *
     * @param keywordList 検索キーワード
     * @param page        取得ページ
     * @param solved      解決済みフラグ
     * @return 質問一覧
     */
    public QuestionAndPage searchQuestion(List<String> keywordList, int page, boolean solved) {

        // レスポンス
        QuestionAndPage result = new QuestionAndPage();

        // 取得件数
        int limit = 8;

        // 全体の件数を取得する
        int count = questionsMapper.countQuestion(keywordList, solved);

        // ページネーションを取得
        PageNation pageNation = PageNationUtils.createPageNation(page, count, limit);

        // 取得ページからoffsetを計算する
        int offset = (pageNation.getCurrentPage() - 1) * limit;

        // 検索処理
        List<Questions> questionsList = questionsMapper.searchQuestion(keywordList, solved, limit, offset);

        // レスポンスに値を設定
        result.setQuestionDataList(convertQuestionDataList(null, questionsList));
        result.setPage(pageNation);

        return result;
    }

    /**
     * 質問内容を取得する
     *
     * @param email メールアドレス
     * @param id    質問ID
     * @return 質問内容
     */
    public QuestionData getQuestion(String email, int id) {

        // ユーザ情報取得
        Integer userId = null;
        Users user = usersMapper.selectEmail(email);

        // 存在する場合、ユーザIDを設定
        if (user != null) {
            userId = user.getId();
        }

        // 質問情報を取得する
        Questions question = questionsMapper.selectByPrimaryKey(id);

        // 存在しない場合エラー
        if (question == null) {
            throw new MenuDeliverException("質問が存在しません。");
        }

        // 詰め替えする
        QuestionData result = convertQuestionData(userId, question);

        return result;
    }

    /**
     * 質問情報リストから関連情報を取得し、質問データリストに変換する
     *
     * @param userId       ユーザID(自分の投稿かを判断する)
     * @param questionList 質問情報リスト
     * @return 質問データリスト
     */
    private List<QuestionData> convertQuestionDataList(Integer userId, List<Questions> questionList) {

        // レスポンス
        List<QuestionData> questionDataList = new ArrayList<>();

        // 詰め替える
        for (Questions question : questionList) {
            // 質問データを追加
            questionDataList.add(convertQuestionData(userId, question));
        }

        return questionDataList;
    }

    /**
     * 質問情報から関連情報を取得し、質問データに変換する
     *
     * @param userId   ユーザID(自分の投稿かを判断する)
     * @param question 質問情報
     * @return 質問データ
     */
    private QuestionData convertQuestionData(Integer userId, Questions question) {

        // 画像情報を取得する
        QuestionImages images = questionImagesMapper.selectByQuestionId(question.getId());
        String imagePath = null;
        if (images != null) {
            imagePath = PathUtils.getQuestionImagePath(images.getPath());
        }

        // ユーザ情報を取得する
        Users user = usersMapper.selectByPrimaryKey(question.getUserId());

        // データを詰め替える
        QuestionData data = new QuestionData();
        data.setId(question.getId());
        data.setContents(question.getContents());
        data.setImages(imagePath);
        data.setUserName(user.getName());
        data.setUserIcon(PathUtils.getUserIconPath(user.getIcon()));
        data.setMine(userId == null ? false : userId == question.getUserId());

        return data;
    }

    /**
     * 回答情報リストから関連情報を取得し、回答データリストに変換する
     *
     * @param answerList 回答情報リスト
     * @return 回答データリスト
     */
    private List<AnswerData> convertAnswerDataList(List<Answers> answerList) {

        // レスポンス
        List<AnswerData> answerDataList = new ArrayList<>();

        // 詰め替える
        for (Answers answer : answerList) {
            // 回答データを追加
            answerDataList.add(convertAnswerData(answer));
        }

        return answerDataList;
    }

    /**
     * 回答情報から関連情報を取得し、回答データに変換する
     *
     * @param answer 回答情報
     * @return 回答データ
     */
    private AnswerData convertAnswerData(Answers answer) {

        // 画像情報を取得する
        AnswerImages images = answerImagesMapper.selectByAnswerId(answer.getId());
        String imagePath = null;
        if (images != null) {
            imagePath = PathUtils.getAnswerImagePath(images.getPath());
        }

        // ユーザ情報を取得する
        Users user = usersMapper.selectByPrimaryKey(answer.getUserId());

        // データを詰め替える
        AnswerData data = new AnswerData();
        data.setId(answer.getId());
        data.setContents(answer.getContents());
        data.setImages(imagePath);
        data.setUserName(user.getName());
        data.setUserIcon(PathUtils.getUserIconPath(user.getIcon()));

        return data;
    }
}
