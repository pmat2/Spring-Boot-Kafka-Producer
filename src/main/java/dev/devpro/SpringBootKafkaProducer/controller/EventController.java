package dev.devpro.SpringBootKafkaProducer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.devpro.SpringBootKafkaProducer.event.Event;
import dev.devpro.SpringBootKafkaProducer.service.EventService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService service;
    private final Logger logger = LoggerFactory.getLogger(EventController.class);

    @GetMapping("/alive")
    public ResponseEntity<Boolean> checkStatus() {
        logger.info("[checkStatus] - GET /alive endpoint invoked");
        return ResponseEntity.ok(true);
    }

    @PostMapping
    public ResponseEntity<?> sendEvent(@RequestBody Event event) {
        logger.info("[sendEvent] - POST invoked with: {}", event);
        try {
            service.sendEvent(event);
        } catch (JsonProcessingException e) {
            logger.error("Error while sending event", e);
            ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }
}
