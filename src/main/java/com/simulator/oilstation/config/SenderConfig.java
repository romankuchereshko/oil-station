package com.simulator.oilstation.config;

import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.oilstation.service.frame.FrameSender;
import com.simulator.oilstation.service.frame.impl.TestConsoleFrameSenderImpl;
import com.simulator.oilstation.service.frame.impl.KafkaFrameSenderImpl;
import oil.station.domain.frame.Frame;

@Configuration
public class SenderConfig {

    @Bean
    public FrameSender kafkaFrameSender(final KafkaTemplate<String, Collection<Frame>> kafkaTemplate) {
        return new KafkaFrameSenderImpl(kafkaTemplate);
    }

    //TODO delete in future
    @Bean
    public FrameSender consoleFrameSender() {
        return new TestConsoleFrameSenderImpl(new ObjectMapper());
    }

}
