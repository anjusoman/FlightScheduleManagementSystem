package personal.schedulingservice.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import personal.schedulingservice.model.ScheduledFlight;

@Repository
@AllArgsConstructor
public class ScheduledFlightRepository {
    private final DynamoDBMapper dynamoDBMapper;

    public String createScheduledFlight(ScheduledFlight scheduledFlight){
        dynamoDBMapper.save(scheduledFlight);
        return scheduledFlight.getFlightId();
    }

    public ScheduledFlight updateScheduledFlight(String flightId, ScheduledFlight scheduledFlight){
        ScheduledFlight load = dynamoDBMapper.load(ScheduledFlight.class, flightId);

        load.setDeparture(scheduledFlight.getDeparture());
        load.setArrival(scheduledFlight.getArrival());
        load.setFlightStatus(scheduledFlight.getFlightStatus());
        load.setFlightNumber(scheduledFlight.getFlightNumber());
        load.setLiveFlightData(scheduledFlight.getLiveFlightData());

        return dynamoDBMapper.load(ScheduledFlight.class, flightId);
    }

    public ScheduledFlight getScheduledFlightById(String flightId){
        return dynamoDBMapper.load(ScheduledFlight.class, flightId);
    }

    public String deleteScheduledFlight(String id){
        return "";
    }
}