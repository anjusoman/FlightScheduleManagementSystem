package personal.schedulingservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import personal.schedulingservice.util.DateTimeConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@DynamoDBDocument
public class LiveFlightData {
    private String lastUpdatedDate;
    private String lastUpdatedTime;
    private Coordinates coordinates;
    private double altitude;
    private double speed;
    private boolean isGround;

    // Constructor for flight that has not yet departed
    public LiveFlightData(Coordinates coordinates){
        LocalDateTime dateTimeNow = LocalDateTime.now();
        String[] formattedDateTimeNow = DateTimeConverter.convertToString(dateTimeNow);

        this.lastUpdatedDate = formattedDateTimeNow[0];
        this.lastUpdatedTime = formattedDateTimeNow[1];

        this.coordinates = coordinates;
        this.altitude = 0; // Assumes flat earth for simplicity
        this.speed = 0;
        this.isGround = true;
    }


}
