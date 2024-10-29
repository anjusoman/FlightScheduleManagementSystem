package personal.schedulingservice.config;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class EventbridgeConfig {

    @Value("${aws.eventbridge.endpoint}")
    private String eventbridgeEndpoint;

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.dynamodb.accessKey}")
    private String awsAccessKey;

    @Value("${aws.dynamodb.secretKey}")
    private String awsSecretKey;

    @Bean
    public EventBridgeClient eventBridgeClient(){
        AwsBasicCredentials credentials = AwsBasicCredentials.create(awsAccessKey, awsSecretKey);

        return EventBridgeClient.builder()
                .endpointOverride(URI.create(eventbridgeEndpoint))
                .region(Region.of(awsRegion))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();

    }

}
