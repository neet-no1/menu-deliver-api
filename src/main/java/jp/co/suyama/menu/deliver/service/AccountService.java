package jp.co.suyama.menu.deliver.service;

import java.io.StringWriter;
import java.util.Calendar;
import java.util.UUID;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;

import jp.co.suyama.menu.deliver.common.Role;
import jp.co.suyama.menu.deliver.exception.MenuDeliverException;
import jp.co.suyama.menu.deliver.mapper.BounceMapperImpl;
import jp.co.suyama.menu.deliver.mapper.UsersMapperImpl;
import jp.co.suyama.menu.deliver.model.db.Bounce;
import jp.co.suyama.menu.deliver.model.db.Users;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountService {

    // ロガー
    private Logger log = LoggerFactory.getLogger(AccountService.class);

    // AWS SESのクライアント
    @Autowired
    AmazonSimpleEmailService sesClient;

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
        String confirmUrl = "https://" + DOMAIN + "/create/account/done?key=" + uuid;

        VelocityContext context = new VelocityContext();
        context.put("mail", email);
        context.put("confirm_url", confirmUrl);

        StringWriter writer = new StringWriter();
        velocityEngine.mergeTemplate("mail/AccountRegist.vm", "UTF-8", context, writer);

        // ユーザテーブルにデータを登録する
        Users user = new Users();
        user.setEmail(email);
        user.setPassword(password);
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
        SendEmailRequest request = new SendEmailRequest().withDestination(new Destination().withToAddresses(email))
                .withMessage(new Message()
                        .withBody(new Body().withText(new Content().withCharset("UTF-8").withData(writer.toString())))
                        .withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
                .withSource(FROM);
        SendEmailResult mailResult = sesClient.sendEmail(request);

        // ログに記録する
        log.info("Regist Account: userId:[{}] messageId:[{}] requestId:[{}]", userId, mailResult.getMessageId(),
                mailResult.getSdkResponseMetadata().getRequestId());
    }
}