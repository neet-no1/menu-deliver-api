package jp.co.suyama.menu.deliver.service;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.simpleemail.model.SendEmailResult;

import jp.co.suyama.menu.deliver.common.MenuDeliverConstants;
import jp.co.suyama.menu.deliver.common.Role;
import jp.co.suyama.menu.deliver.common.S3Access;
import jp.co.suyama.menu.deliver.config.SendMail;
import jp.co.suyama.menu.deliver.exception.MenuDeliverException;
import jp.co.suyama.menu.deliver.mapper.BounceMapperImpl;
import jp.co.suyama.menu.deliver.mapper.UsersMapperImpl;
import jp.co.suyama.menu.deliver.model.auto.AccountData;
import jp.co.suyama.menu.deliver.model.db.Bounce;
import jp.co.suyama.menu.deliver.model.db.Users;
import jp.co.suyama.menu.deliver.utils.PathUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountService {

    // ロガー
    private Logger log = LoggerFactory.getLogger(AccountService.class);

    // S3アクセス
    @Autowired
    private S3Access s3Access;

    @Autowired
    private SendMail sendMailClient;

    // メール件名
    private static final String SUBJECT = "メールアドレス認証";

    // メールの送信元
    @Value("${mail.from}")
    private String FROM;

    // ドメイン
    @Value("${domain}")
    private String DOMAIN;

    // ユーザテーブル
    @Autowired
    private UsersMapperImpl usersMapper;

    // メール拒否テーブル
    @Autowired
    private BounceMapperImpl bounceMapper;

    // テンプレートエンジン
    @Autowired
    private VelocityEngine velocityEngine;

    // パスワードエンコーダー
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * アカウントを登録する
     *
     * @param email    メールアドレス
     * @param password エンコード済みパスワード
     */
    public void registAccount(String email, String password) {

        // ユーザテーブルに存在しているかを確認する
        Users existUser = usersMapper.selectEmail(email);

        if (existUser != null) {
            // 存在している場合、エラーとする
            throw new MenuDeliverException("既にユーザが存在します。");
        }

        // ワンタイムパスワードを取得する
        String uuid = UUID.randomUUID().toString();

        // 有効期限を設定(24時間後)
        Calendar expires = Calendar.getInstance();
        expires.add(Calendar.DAY_OF_MONTH, 1);

        // メールアドレス認証用アドレス
        String confirmUrl = DOMAIN + "/#/create/account/done?key=" + uuid;

        VelocityContext context = new VelocityContext();
        context.put("mail", email);
        context.put("confirm_url", confirmUrl);

        StringWriter writer = new StringWriter();
        velocityEngine.mergeTemplate("mail/AccountRegist.vm", "UTF-8", context, writer);

        // ユーザテーブルにデータを登録する
        Users user = new Users();
        user.setEmail(email);
        user.setPassword(password);
        user.setName("NO_NAME");
        user.setIcon("no_image");
        user.setRole(Role.ROLE_USER);
        user.setAvailable(false);
        user.setOncePassword(uuid);
        user.setExpires(expires.getTime());
        user.setDeleted(false);

        int userId = usersMapper.registUser(user);

        // メール拒否テーブルにデータを登録する
        Bounce bounce = new Bounce();
        bounce.setUserId(userId);
        bounce.setBounce(false);

        bounceMapper.insert(bounce);

        // メールを送信する
        SendEmailResult mailResult = sendMailClient.sendMail(FROM, email, SUBJECT, writer.toString());

        // ログに記録する
        log.info("Regist Account: userId:[{}] messageId:[{}] requestId:[{}]", userId, mailResult.getMessageId(),
                mailResult.getSdkResponseMetadata().getRequestId());
    }

    /**
     * アカウント情報を取得
     *
     * @param email メールアドレス
     * @return アカウント情報
     */
    public AccountData getAccountInfo(String email) {

        // ユーザ情報取得
        Users user = usersMapper.selectEmail(email);

        // 存在していない場合エラー
        if (user == null) {
            throw new MenuDeliverException("ユーザが存在しません。");
        }

        AccountData data = new AccountData();
        data.setId(user.getId());
        data.setImgPath(PathUtils.getUserIconPath(user.getIcon()));
        data.setName(user.getName());
        data.setEmail(user.getEmail());

        return data;
    }

    /**
     * メールアドレスの有効性を確認する
     *
     * @param otp ワンタイムパスワード
     */
    public void checkEmailAvailable(String otp) {

        // ワンタイムパスワードを元にユーザ情報を取得する
        Users user = usersMapper.selectByOncePassword(otp);

        // ユーザが存在しない場合エラー
        if (user == null) {
            throw new MenuDeliverException("ユーザが存在しません。");
        }

        // 有効期限が切れていないか確認
        Date expires = user.getExpires();
        Date now = new Date();
        if (expires.before(now)) {
            // 有効期限が切れている場合はエラー
            throw new MenuDeliverException("有効期限が切れています。");
        }

        // メールアドレス有効性をtrueに設定する
        user.setAvailable(true);

        // ユーザ情報を更新する
        usersMapper.updateUser(user);
    }

    /**
     * アカウント情報更新
     *
     * @param name          ニックネーム
     * @param email         メールアドレス
     * @param icon          アイコン
     * @param previousEmail 現在のメールアドレス
     */
    public void updateAccountInfo(String name, String email, MultipartFile icon, String previousEmail) {

        // ユーザを取得
        Users user = usersMapper.selectEmail(previousEmail);

        // ユーザが存在しない場合エラー
        if (user == null) {
            throw new MenuDeliverException("ユーザが存在しません。");
        }

        // アイコンのパスを取得
        String iconPath = MenuDeliverConstants.NO_IMAGE_USER_ICON;

        if (icon != null && !icon.isEmpty()) {
            iconPath = PathUtils.createUserIconPath(user.getId(), previousEmail);
        }

        // ユーザ情報を更新
        user.setEmail(email);
        user.setName(name);
        user.setIcon(iconPath);

        usersMapper.updateUser(user);

        // ユーザアイコンをS3にアップロードする
        if (icon != null && !icon.isEmpty()) {
            s3Access.uploadUserIcon(iconPath, icon);
        }
    }

    /**
     * パスワード更新
     *
     * @param email           メールアドレス
     * @param currentPassword 現在のパスワード
     * @param newPassword     新しいパスワード
     */
    public void updatePassword(String email, String currentPassword, String newPassword) {

        // ユーザを取得
        Users user = usersMapper.selectEmail(email);

        // ユーザが存在しない場合エラー
        if (user == null) {
            throw new MenuDeliverException("ユーザが存在しません。");
        }

        // 現在パスワードを比較
        if (!currentPassword.equals(user.getPassword())) {
            throw new MenuDeliverException("現在のパスワードが違います。");
        }

        // 新しいパスワード設定する
        user.setPassword(newPassword);

        // 更新
        usersMapper.updateUser(user);
    }

    /**
     * パスワードをリセットする
     *
     * @param email メールアドレス
     */
    public void resetPassword(String email) {

        // ユーザを取得
        Users user = usersMapper.selectEmail(email);

        // ユーザが存在しない場合エラー
        if (user == null) {
            throw new MenuDeliverException("ユーザが存在しません。");
        }

        // 大文字小文字英数字混合の8桁のパスワードを生成
        List<CharacterRule> rules = Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1), new CharacterRule(EnglishCharacterData.Digit, 1));
        PasswordGenerator generator = new PasswordGenerator();
        String password = generator.generatePassword(8, rules);

        // パスワードをエンコードする
        String encodedPassword = passwordEncoder.encode(password);

        // テーブルを更新
        user.setPassword(encodedPassword);
        user.setDeleted(false);

        usersMapper.updateUser(user);

        // パスワードリセットのメールを送信する
        VelocityContext context = new VelocityContext();
        context.put("mail", email);
        context.put("password", password);

        StringWriter writer = new StringWriter();
        velocityEngine.mergeTemplate("mail/PasswordReset.vm", "UTF-8", context, writer);

        // メールを送信する
        SendEmailResult mailResult = sendMailClient.sendMail(FROM, email, "パスワードリセットのお知らせ", writer.toString());

        // ログに記録する
        log.info("Password Reset: userId:[{}] messageId:[{}] requestId:[{}]", user.getId(), mailResult.getMessageId(),
                mailResult.getSdkResponseMetadata().getRequestId());
    }
}
