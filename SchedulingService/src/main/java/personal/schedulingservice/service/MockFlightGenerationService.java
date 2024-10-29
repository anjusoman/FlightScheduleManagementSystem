package personal.schedulingservice.service;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import personal.schedulingservice.model.*;
import personal.schedulingservice.repository.ScheduledFlightRepository;
import personal.schedulingservice.util.DateTimeConverter;
import personal.schedulingservice.util.ObjectToJsonConverter;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class MockFlightGenerationService {

    private final Random random;
    private final Calendar calendar;
    private final String[] airports;
    private final Map<String, Coordinates> airportCoordinates;
    private final String[] terminals;
    private final String[] gates;

    private final ScheduledFlightRepository scheduledFlightRepository;
    private final EventProducerService eventProducerService;
    private int getRandomIndex(int arrLength) {
        return random.nextInt(arrLength);
    }

    @Async
    @Scheduled(fixedRate = 5000) // Run every 5 seconds
    public void generateRandomScheduledFlight(){
        int id = 1;
        FlightEvent departure = generateRandomFlightDeparture();
        FlightEvent arrival = generateRandomFlightArrival(departure);
        LiveFlightData liveFlightData = new LiveFlightData(airportCoordinates.get(departure.getAirport()));
        String flightNumber = generateRandomFlightNumber();
        ScheduledFlight scheduledFlight = ScheduledFlight.builder()
                                                        .flightStatus(FlightStatus.SCHEDULED)
                                                        .departure(departure)
                                                        .arrival(arrival)
                                                        .flightNumber(flightNumber)
                                                        .liveFlightData(liveFlightData)
                                                        .build();
        scheduledFlightRepository.createScheduledFlight(scheduledFlight);


        String jsonString = ObjectToJsonConverter.convertObjectToJson(scheduledFlight);
        String detailType = "new-flight-event";
        eventProducerService.PutEvent(jsonString, detailType);
    }

    private FlightEvent generateRandomFlightDeparture(){
        String airport = airports[getRandomIndex(airports.length)];
        String timezone = calendar.getTimeZone().getDisplayName();
        String terminal = terminals[getRandomIndex(terminals.length)];
        String gate = gates[getRandomIndex(gates.length)];

        // Select a day 2 to 8 days from today
        int randomDays = 2 + random.nextInt(7);
        LocalDateTime randomDate = LocalDateTime.now().plusDays(randomDays);

        // Select a time between 4:00am and 10:59pm
        int randomHour = 4 + random.nextInt(19);
        int randomMinute = random.nextInt(60);
        randomDate = randomDate.withHour(randomHour).withMinute(randomMinute);

        String[] formattedDate = DateTimeConverter.convertToString(randomDate);
        String scheduledDate = formattedDate[0];
        String scheduledTime = formattedDate[1];

        // Create a departure event with the estimated date/time set to the scheduled date/time
        return new FlightEvent(airport, timezone, terminal, gate, scheduledDate, scheduledTime, scheduledDate, scheduledTime);
    }

    private FlightEvent generateRandomFlightArrival(FlightEvent departure){

        // Select an airport that differs from the departure airport
        String airport;
        do {
            airport = airports[getRandomIndex(airports.length)];
        } while (airport.equals(departure.getAirport()));

        String timezone = departure.getTimezone();

        String terminal = terminals[getRandomIndex(terminals.length)];
        String gate = gates[getRandomIndex(gates.length)];

        // Calculate the straight line distance between the airports
        Coordinates srcCoordinates = airportCoordinates.get(departure.getAirport());
        Coordinates destCoordinates = airportCoordinates.get(airport);
        double distance = Coordinates.distance(srcCoordinates, destCoordinates);

        // Calculate approximate travel time
        double planeSpeed = 900; // assume plane travels at 900 km per hour
        double approximateTravelTime = distance/ planeSpeed;
        long travelHours = (long) approximateTravelTime;
        long travelMinutes = (long) ((approximateTravelTime - travelHours) * 60);

        // Calculate approximate arrival time based on departure time and travel time
        String departureDate = departure.getScheduledDate();
        String departureTime = departure.getScheduledTime();
        LocalDateTime departureDateTime = DateTimeConverter.convertToDateTime(departureDate, departureTime);

        LocalDateTime arrivalDateTime = departureDateTime.plusHours(travelHours).plusMinutes(travelMinutes);

        // Format date time
        String[] formattedDate = DateTimeConverter.convertToString(arrivalDateTime);
        String arrivalDate = formattedDate[0];
        String arrivalTime = formattedDate[1];

        // Create a departure event with the estimated date/time set to the scheduled date/time
        return new FlightEvent(airport, timezone, terminal, gate, arrivalDate, arrivalTime, arrivalDate, arrivalTime);

    }

    private String generateRandomFlightNumber() {

        int flightNumber = random.nextInt(9999) + 1; // Ensures at least 1

        String airlineCode = "WN";
        return String.format("%s%d", airlineCode, flightNumber);
    }

}
