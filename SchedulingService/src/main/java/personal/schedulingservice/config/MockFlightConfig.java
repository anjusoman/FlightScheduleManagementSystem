package personal.schedulingservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import personal.schedulingservice.model.Coordinates;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MockFlightConfig {
    @Bean
    public String[] airports() {
        return new String[] {
                "ATL",
                "LAX",
                "ORD",
                "DFW",
                "DEN",
                "JFK",
                "SFO",
                "SEA",
                "LAS",
                "MIA"
        };
    }

    @Bean
    public static Map<String, Coordinates> airportCoordinates() {
        Map<String, Coordinates> coordinatesMap = new HashMap<>();

        // Initialize the coordinates for each airport (latitude, longitude)
        coordinatesMap.put("ATL", new Coordinates(33.6407, -84.4279));  // Atlanta, GA
        coordinatesMap.put("LAX", new Coordinates(33.9416, -118.4085)); // Los Angeles, CA
        coordinatesMap.put("ORD", new Coordinates(41.9742, -87.9073)); // Chicago, IL
        coordinatesMap.put("DFW", new Coordinates(32.8998, -97.0403)); // Dallas/Fort Worth, TX
        coordinatesMap.put("DEN", new Coordinates(39.8617, -104.6738)); // Denver, CO
        coordinatesMap.put("JFK", new Coordinates(40.6413, -73.7781)); // New York, NY
        coordinatesMap.put("SFO", new Coordinates(37.6213, -122.3790)); // San Francisco, CA
        coordinatesMap.put("SEA", new Coordinates(47.4502, -122.3087)); // Seattle, WA
        coordinatesMap.put("LAS", new Coordinates(36.0801, -115.1522)); // Las Vegas, NV
        coordinatesMap.put("MIA", new Coordinates(25.7959, -80.2870));  // Miami, FL

        return coordinatesMap;
    }

    @Bean
    public String[] terminals(){
        return new String[] {
                "A",
                "B",
                "C",
                "D",
                "E",
                "F",
                "G",
                "H",
                "I",
                "J"
        };
    }

    @Bean
    public String[] gates(){
        return new String[] {
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10"
        };
    }

}
