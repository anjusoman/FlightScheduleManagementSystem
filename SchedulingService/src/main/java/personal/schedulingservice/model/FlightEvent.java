package personal.schedulingservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@DynamoDBDocument
public class FlightEvent {
    private String airport;
    private String timezone;
    private String terminal;
    private String gate;
    private String scheduledDate;
    private String scheduledTime;
    private String estimatedDate;
    private String estimatedTime;
}
