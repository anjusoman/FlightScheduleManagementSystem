package personal.schedulingservice.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.eventbridge.EventBridgeClientBuilder;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequest;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequestEntry;

@Service
@AllArgsConstructor
public class EventProducerService {

    private final EventBridgeClient eventBridgeClient;

    public void PutEvent(String jsonString, String detailType){

        PutEventsRequestEntry entry = PutEventsRequestEntry.builder()
                .eventBusName("EventBus")
                .source("scheduling.service")
                .detail(jsonString)
                .detailType(detailType)
                .build();

        PutEventsRequest eventsRequest = PutEventsRequest.builder()
                .entries(entry)
                .build();

        eventBridgeClient.putEvents(eventsRequest);
    }
}
