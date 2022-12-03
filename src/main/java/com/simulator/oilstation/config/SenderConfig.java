package com.simulator.oilstation.config;

import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.oilstation.domain.frame.Frame;
import com.simulator.oilstation.service.frame.FrameSender;
import com.simulator.oilstation.service.frame.impl.ConsoleFrameSenderImpl;
import com.simulator.oilstation.service.frame.impl.KafkaFrameSenderImpl;

@Configuration
public class SenderConfig {

    @Bean
    public FrameSender consoleFrameSender() {
        return new ConsoleFrameSenderImpl(new ObjectMapper());
    }

    @Bean
    public FrameSender kafkaFrameSender(final KafkaTemplate<String, Collection<Frame>> kafkaTemplate) {
        return new KafkaFrameSenderImpl(kafkaTemplate);
    }
}
