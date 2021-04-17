package com.simulator.oilstation.config;

import com.simulator.oilstation.sender.ConsoleFrameSenderImpl;
import com.simulator.oilstation.sender.FrameSender;
import com.simulator.oilstation.sender.KafkaFrameSenderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderConfig {

    @Bean
    public FrameSender consoleFrameSender() {
        return new ConsoleFrameSenderImpl();
    }

    @Bean
    public FrameSender kafkaFrameSender() {
        return new KafkaFrameSenderImpl();
    }
}
