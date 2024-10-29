package personal.schedulingservice.config;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class DynamoDBConfig {

   @Value("${aws.dynamodb.endpoint}")
   private String dynamoDbEndpoint;

   @Value("${aws.region}")
   private String awsRegion;

   @Value("${aws.dynamodb.accessKey}")
   private String awsAccessKey;

   @Value("${aws.dynamodb.secretKey}")
   private String awsSecretKey;

   @Bean
   public DynamoDBMapper dynamoDBMapper() {
       return new DynamoDBMapper(buildAmazonDynamoDB());
   }

   private AmazonDynamoDB buildAmazonDynamoDB() {
       BasicAWSCredentials credentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);

       return AmazonDynamoDBClientBuilder.standard()
               .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dynamoDbEndpoint, awsRegion))
               .withCredentials(new AWSStaticCredentialsProvider(credentials))
               .build();

   }
}

