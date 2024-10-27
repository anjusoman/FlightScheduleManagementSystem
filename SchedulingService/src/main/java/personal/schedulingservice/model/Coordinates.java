package personal.schedulingservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBDocument
public class Coordinates {
    private double latitude;
    private double longitude;

    // Code adapted from StackOverflow: https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude/3694410
    // Returns distance between two coordinates in kilometers
    public static double distance(Coordinates srcCoordinates, Coordinates destCoordinates) {

        double lat1 = srcCoordinates.getLatitude();
        double lat2 = destCoordinates.getLatitude();
        double lon1 = srcCoordinates.getLongitude();
        double lon2 = destCoordinates.getLongitude();

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        return distance;
    }
}
