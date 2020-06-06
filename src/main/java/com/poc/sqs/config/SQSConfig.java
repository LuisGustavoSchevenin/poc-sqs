package com.poc.sqs.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SQSConfig {

    @Value("${aws_access_key}")
    private String awsAccessKey;
    @Value("${aws_secret_key}")
    private String awsSecretKey;
    @Value("${aws_region}")
    private String awsRegion;
    @Value("${aws_customer_queue_fifo}")
    private String sqsQueue;


    public AmazonSQS getAmazonSQSClient() {
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
        AWSCredentialsProvider awsCredentials = new AWSStaticCredentialsProvider(basicAWSCredentials);

        return AmazonSQSClientBuilder.standard()
                .withCredentials(awsCredentials)
                .withRegion(awsRegion)
                .build();
    }

    public String getAmazonSQSQueue() {
        return sqsQueue;
    }
}
