package jp.co.suyama.menu.deliver.config;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;

public class SESClient implements SendMail {

    // AWS SESのクライアント
    @Autowired
    private AmazonSimpleEmailService sesClient;

    @Override
    public SendEmailResult sendMail(String from, String to, String subject, String contents) {

        // メールを送信する
        SendEmailRequest request = new SendEmailRequest().withDestination(new Destination().withToAddresses(to))
                .withMessage(new Message()
                        .withBody(new Body().withText(new Content().withCharset("UTF-8").withData(contents)))
                        .withSubject(new Content().withCharset("UTF-8").withData(subject)))
                .withSource(from);
        SendEmailResult mailResult = sesClient.sendEmail(request);

        return mailResult;
    }

}
