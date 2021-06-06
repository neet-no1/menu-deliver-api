package jp.co.suyama.menu.deliver.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setPort(1025);
        sender.setHost("127.0.0.1");

        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.timeout", 5000);
        properties.put("mail.smtp.connectiontimeout", 5000);
        properties.put("mail.smtp.writetimeout", 5000);

        sender.setJavaMailProperties(properties);

        return sender;
    }

    /**
     * メールクライアントの生成
     */
    @Bean
    @Profile("default")
    public SendMail mailClientDev() {
        return new MailHogClient();
    }

    /**
     * メールクライアントの生成
     */
    @Bean
    @Profile({ "development", "product" })
    public SendMail mailClient() {
        return new SESClient();
    }
}
