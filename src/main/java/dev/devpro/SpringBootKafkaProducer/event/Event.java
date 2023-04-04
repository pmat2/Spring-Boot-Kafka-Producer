package dev.devpro.SpringBootKafkaProducer.event;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Event {
    private String message;
    private LocalDateTime time;
}
