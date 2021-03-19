package jp.co.suyama.menu.deliver.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

@Configuration
public class MyBatisConfig {

    /**
     * MyBatisを使用するための設定情報
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        // DataSourceを設定
        factoryBean.setDataSource(dataSource);

        // mybatisの設定ファイルを読み込む
        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
        factoryBean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));

        return factoryBean.getObject();
    }
}