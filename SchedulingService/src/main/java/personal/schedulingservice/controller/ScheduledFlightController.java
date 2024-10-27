package personal.schedulingservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import personal.schedulingservice.model.ScheduledFlight;
import personal.schedulingservice.service.ScheduledFlightService;

@AllArgsConstructor
@RestController
public class ScheduledFlightController {

    private final ScheduledFlightService scheduledFlightService;

    @PostMapping("/scheduledFlight")
    public String createScheduledFlight(@RequestBody ScheduledFlight customer){
        return scheduledFlightService.createScheduledFlight(customer);
    }

    @GetMapping("/scheduledFlight/{flightId}")
    public ScheduledFlight getScheduledFlightById(@PathVariable String flightId){
        return scheduledFlightService.getScheduledFlightById(flightId);
    }

    @PutMapping("/scheduledFlight/{flightId}")
    public ScheduledFlight updateScheduledFlight(@PathVariable String flightId, @RequestBody ScheduledFlight customer){
        return scheduledFlightService.updateScheduledFlight(flightId, customer);
    }

    @DeleteMapping("/scheduledFlight/{flightId}")
    public String deleteScheduledFlight(@PathVariable String flightId){
        return scheduledFlightService.deleteScheduledFlight(flightId);
    }
}