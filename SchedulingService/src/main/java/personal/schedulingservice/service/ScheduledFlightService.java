package personal.schedulingservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import personal.schedulingservice.model.ScheduledFlight;
import personal.schedulingservice.repository.ScheduledFlightRepository;

@Service
@AllArgsConstructor
public class ScheduledFlightService {
    private final ScheduledFlightRepository scheduledFlightRepository;

    public String createScheduledFlight(ScheduledFlight customer){
        return scheduledFlightRepository.createScheduledFlight(customer);
    }

    public ScheduledFlight updateScheduledFlight(String id, ScheduledFlight scheduledFlight){
        return scheduledFlightRepository.updateScheduledFlight(id, scheduledFlight);
    }

    public ScheduledFlight getScheduledFlightById(String flightId){
        return scheduledFlightRepository.getScheduledFlightById(flightId);
    }

    public String deleteScheduledFlight(String id){
        return scheduledFlightRepository.deleteScheduledFlight(id);
    }
}