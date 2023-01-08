package com.simulator.oilstation.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.simulator.oilstation.domain.properties.FrameProperties;
import com.simulator.oilstation.domain.properties.ValueProperty;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oil.station.domain.frame.Frame;
import oil.station.domain.frame.Frame.FrameBuilder;

@Slf4j
@Service
@AllArgsConstructor
public class GeneratorService {

    private final FrameProperties frameProperties;

    public Collection<Frame> generate(final List<Long> wellsUuids) {
        final ValueProperty voltage = this.frameProperties.getVoltage();
        final ValueProperty current = this.frameProperties.getCurrent();
        final ValueProperty speed = this.frameProperties.getSpeed();
        final ValueProperty frequency = this.frameProperties.getFrequency();
        final ValueProperty temperature = this.frameProperties.getTemperature();
        final ValueProperty pressure = this.frameProperties.getPressure();
        final ValueProperty liquidFlowRate = this.frameProperties.getLiquidFlowRate();

        return wellsUuids.stream()
            .map(uuid -> {
                FrameBuilder builder = Frame.builder();

                return builder
                    .id(UUID.randomUUID())
                    .creationDateTime(LocalDateTime.now())
                    .wellId(uuid)
                    .voltage(this.getRandomValueInRange(builder, voltage))
                    .current(this.getRandomValueInRange(builder, current))
                    .speed(this.getRandomValueInRange(builder, speed))
                    .frequency(this.getRandomValueInRange(builder, frequency))
                    .temperature(this.getRandomValueInRange(builder, temperature))
                    .pressure(this.getRandomValueInRange(builder, pressure))
                    .liquidFlowRate(this.getRandomValueInRange(builder, liquidFlowRate))
                    .build();
            })
            .toList();
    }

    private Double getRandomValueInRange(final FrameBuilder frame, final ValueProperty valueProperty) {
        double random =
            valueProperty.getMinValue() + (Math.random() * (valueProperty.getMaxValue() - valueProperty.getMinValue()));
        double roundedValue = BigDecimal.valueOf(random).setScale(3, RoundingMode.HALF_UP).doubleValue();

        if (this.checkCriticalValue(roundedValue, valueProperty)) {
            frame.isCriticalValue(true);
        }

        return roundedValue;
    }

    private boolean checkCriticalValue(final double roundedValue, final ValueProperty valueProperty) {
        return (roundedValue <= valueProperty.getMinCriticalValue() && roundedValue >= valueProperty.getMinValue()) ||
            (roundedValue >= valueProperty.getMaxCriticalValue() && roundedValue <= valueProperty.getMaxValue());
    }

}
