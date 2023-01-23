package com.simulation.oilstation.config;

import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import com.simulation.library.domain.Frame;
import com.simulation.oilstation.service.producer.FrameSender;
import com.simulation.oilstation.service.producer.impl.FrameSenderImpl;

@Configuration
public class SenderConfiguration {

    @Bean
    public FrameSender frameSender(final KafkaTemplate<String, Collection<Frame>> kafkaTemplate) {
        return new FrameSenderImpl(kafkaTemplate);
    }

}
