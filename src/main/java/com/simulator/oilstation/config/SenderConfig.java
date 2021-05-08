package com.simulator.oilstation.config;

import com.simulator.oilstation.model.Frame;
import com.simulator.oilstation.sender.ConsoleFrameSenderImpl;
import com.simulator.oilstation.sender.FrameSender;
import com.simulator.oilstation.sender.KafkaFrameSenderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Collection;

@Configuration
public class SenderConfig {

    @Bean
    public FrameSender consoleFrameSender() {
        return new ConsoleFrameSenderImpl();
    }

    @Bean
    public FrameSender kafkaFrameSender(KafkaTemplate<String, Collection<Frame>> kafkaTemplate) {
        return new KafkaFrameSenderImpl(kafkaTemplate);
    }
}
