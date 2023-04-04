package dev.devpro.SpringBootKafkaProducer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.devpro.SpringBootKafkaProducer.config.KafkaConfigProperties;
import dev.devpro.SpringBootKafkaProducer.event.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventService {

    private final ObjectMapper objectMapper;
    private final KafkaConfigProperties properties;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendEvent(Event event) throws JsonProcessingException {
        event.setTime(LocalDateTime.now());
        final String payload = objectMapper.writeValueAsString(event);
        kafkaTemplate.send(properties.getTopic(), payload);
    }
}
