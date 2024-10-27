package personal.schedulingservice.util;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import personal.schedulingservice.model.FlightStatus;

public class FlightStatusConverter implements DynamoDBTypeConverter<String, FlightStatus> {

    @Override
    public String convert(FlightStatus flightStatus){
        return flightStatus.name();
    }

    @Override
    public FlightStatus unconvert(String flightStatusName){
        return FlightStatus.valueOf(flightStatusName);
    }

}
