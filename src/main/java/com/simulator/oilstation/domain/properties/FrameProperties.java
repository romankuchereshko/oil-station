package com.simulator.oilstation.domain.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "frame")
public class FrameProperties {

    private ValueProperty voltage;

    private ValueProperty current;

    private ValueProperty speed;

    private ValueProperty frequency;

    private ValueProperty temperature;

    private ValueProperty pressure;

    private ValueProperty liquidFlowRate;

}