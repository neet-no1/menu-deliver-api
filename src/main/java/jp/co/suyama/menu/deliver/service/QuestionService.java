package jp.co.suyama.menu.deliver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import jp.co.suyama.menu.deliver.common.S3Access;
import jp.co.suyama.menu.deliver.exception.MenuDeliverException;
import jp.co.suyama.menu.deliver.mapper.QuestionImagesMapperImpl;
import jp.co.suyama.menu.deliver.mapper.QuestionsMapperImpl;
import jp.co.suyama.menu.deliver.mapper.UsersMapperImpl;
import jp.co.suyama.menu.deliver.model.auto.QuestionCategoryData;
import jp.co.suyama.menu.deliver.model.db.QuestionImages;
import jp.co.suyama.menu.deliver.model.db.Questions;
import jp.co.suyama.menu.deliver.model.db.Users;
import jp.co.suyama.menu.deliver.utils.ConvertUtils;
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

    // ユーザテーブル
    @Autowired
    private UsersMapperImpl usersMapper;

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
}
