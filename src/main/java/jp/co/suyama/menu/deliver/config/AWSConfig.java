package jp.co.suyama.menu.deliver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

@Configuration
public class AWSConfig {

    @Value("${aws.accessKeyId}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    /**
     * SESクライアントの生成
     */
    @Bean
    public AmazonSimpleEmailService amazonSESClient() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                // Replace US_WEST_2 with the AWS Region you're using for
                // Amazon SES.
                .withRegion(Regions.US_WEST_2).build();

        return client;
    }

    /**
     * S3クライアントの生成
     */
    @Bean
    @Profile("default")
    public AmazonS3 amazonS3ClienDevt() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        EndpointConfiguration endpoint = new EndpointConfiguration("http://127.0.0.1:9000", "my_region");
        AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withEndpointConfiguration(endpoint).build();

        return s3;
    }

    /**
     * S3クライアントの生成
     */
    @Bean
    @Profile({ "development", "product" })
    public AmazonS3 amazonS3Client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(Regions.AP_NORTHEAST_1).build();

        return s3;
    }
}
