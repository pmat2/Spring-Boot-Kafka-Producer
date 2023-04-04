package dev.devpro.SpringBootKafkaProducer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.devpro.SpringBootKafkaProducer.event.Event;
import dev.devpro.SpringBootKafkaProducer.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService service;

    @GetMapping("/alive")
    public ResponseEntity<Boolean> checkStatus() {
        return ResponseEntity.ok(true);
    }

    @PostMapping
    public ResponseEntity<?> sendEvent(@RequestBody Event event) {
        try {
            service.sendEvent(event);
        } catch (JsonProcessingException e) {
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok().build();
    }
}
