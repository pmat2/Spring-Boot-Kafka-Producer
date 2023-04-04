package dev.devpro.SpringBootKafkaProducer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.devpro.SpringBootKafkaProducer.config.KafkaConfigProperties;
import dev.devpro.SpringBootKafkaProducer.event.Event;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventService {

    private final ObjectMapper objectMapper;
    private final KafkaConfigProperties properties;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(EventService.class);

    public void sendEvent(Event event) throws JsonProcessingException {
        logger.info("[sendEvent] - for {}", event);
        event.setTime(LocalDateTime.now());
        final String payload = objectMapper.writeValueAsString(event);
        kafkaTemplate.send(properties.getTopic(), payload);
    }
}
