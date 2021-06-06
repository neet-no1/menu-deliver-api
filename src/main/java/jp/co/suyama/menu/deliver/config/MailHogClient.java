package jp.co.suyama.menu.deliver.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.simpleemail.model.SendEmailResult;

public class MailHogClient implements SendMail {

    @Autowired
    private JavaMailSender sender;

    @Override
    public SendEmailResult sendMail(String from, String to, String subject, String contents) {
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setFrom(from);
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(contents);

        sender.send(msg);

        Map<String, String> map = new HashMap<>();
        ResponseMetadata metaData = new ResponseMetadata(map);

        SendEmailResult result = new SendEmailResult();
        result.setMessageId("demo mail");
        result.setSdkResponseMetadata(metaData);

        return result;
    }

}
