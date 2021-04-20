package com.simulator.oilstation.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "frame")
public class FrameProperties {
    private Double voltageDefaultValue;
    private Double voltageMinValue;
    private Double voltageMaxValue;
    private String voltageUnit;

    private Double currentDefaultValue;
    private Double currentMinValue;
    private Double currentMaxValue;
    private String currentUnit;

    private Double speedDefaultValue;
    private Double speedMinValue;
    private Double speedMaxValue;
    private String speedUnit;

    private Double frequencyDefaultValue;
    private Double frequencyMinValue;
    private Double frequencyMaxValue;
    private String frequencyUnit;

    private Double temperatureDefaultValue;
    private Double temperatureMinValue;
    private Double temperatureMaxValue;
    private String temperatureUnit;

    private Double pressureDefaultValue;
    private Double pressureMinValue;
    private Double pressureMaxValue;
    private String pressureUnit;

    private Double liquidFlowRateDefaultValue;
    private Double liquidFlowRateMinValue;
    private Double liquidFlowRateMaxValue;
    private String liquidFlowRateUnit;
}