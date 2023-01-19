package com.simulator.oilstation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.simulator.oilstation.domain.Value;
import com.simulator.oilstation.domain.properties.FrameConfigurations;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("frame")
public class FrameConfigurationsImpl implements FrameConfigurations {

    private Value voltage;

    private Value current;

    private Value speed;

    private Value frequency;

    private Value temperature;

    private Value pressure;

    private Value liquidFlowRate;

}