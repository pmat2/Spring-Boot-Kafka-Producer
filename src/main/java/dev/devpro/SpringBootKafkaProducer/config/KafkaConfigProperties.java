package dev.devpro.SpringBootKafkaProducer.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ConfigurationProperties("devpro.kafka")
public class KafkaConfigProperties {
    private String topic;
}
