package jp.co.suyama.menu.deliver.config;

import com.amazonaws.services.simpleemail.model.SendEmailResult;

public interface SendMail {

    abstract SendEmailResult sendMail(String from, String to, String subject, String contents);
}
