package com.equinox.uploadservice.configuration;

import static com.amazonaws.regions.Regions.*;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsS3Config {
    @Value("${awsId:#{environment.AWS_ACCESS_KEY_ID}}")
    private String accessKeyId;

    @Value("${awsSecret:#{environment.AWS_SECRET_ACCESS_KEY}}")
    private String accessKeySecret;

    @Bean
    public AmazonS3 getAmazonS3Client() {
        final var basicAwsCredentials = new BasicAWSCredentials(accessKeyId, accessKeySecret);

        return AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(basicAwsCredentials))
            .withRegion(EU_CENTRAL_1)
            .build();
    }
}