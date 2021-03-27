package jp.co.suyama.menu.deliver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class MenuDeliverConfig {

    /**
     * ObjectMapperの生成
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
