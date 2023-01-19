package com.simulator.oilstation.config;

import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import com.simulator.oilstation.service.producer.FrameSender;
import com.simulator.oilstation.service.producer.impl.FrameSenderImpl;
import oil.station.domain.frame.Frame;

@Configuration
public class SenderConfiguration {

    @Bean
    public FrameSender frameSender(final KafkaTemplate<String, Collection<Frame>> kafkaTemplate) {
        return new FrameSenderImpl(kafkaTemplate);
    }

}
