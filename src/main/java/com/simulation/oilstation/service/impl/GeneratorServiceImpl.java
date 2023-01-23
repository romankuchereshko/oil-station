package com.simulation.oilstation.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.simulation.library.domain.Frame;
import com.simulation.library.domain.Value;
import com.simulation.library.domain.properties.FrameConfigurations;
import com.simulation.oilstation.service.GeneratorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class GeneratorServiceImpl implements GeneratorService {

    private final FrameConfigurations frameConfigurations;

    @Override
    public Collection<Frame> generateFrames(final List<Long> wellIds) {
        final Value voltage = this.frameConfigurations.getVoltage();
        final Value current = this.frameConfigurations.getCurrent();
        final Value speed = this.frameConfigurations.getSpeed();
        final Value frequency = this.frameConfigurations.getFrequency();
        final Value temperature = this.frameConfigurations.getTemperature();
        final Value pressure = this.frameConfigurations.getPressure();
        final Value liquidFlowRate = this.frameConfigurations.getLiquidFlowRate();

        return wellIds.stream()
            .map(id -> Frame.builder()
                .wellId(id)
                .voltage(this.calculateRandomValueInRange(voltage))
                .current(this.calculateRandomValueInRange(current))
                .speed(this.calculateRandomValueInRange(speed))
                .frequency(this.calculateRandomValueInRange(frequency))
                .temperature(this.calculateRandomValueInRange(temperature))
                .pressure(this.calculateRandomValueInRange(pressure))
                .liquidFlowRate(this.calculateRandomValueInRange(liquidFlowRate))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build())
            .toList();
    }

    private Double calculateRandomValueInRange(final Value configValue) {
        double random =
            configValue.getMinValue() + (Math.random() * (configValue.getMaxValue() - configValue.getMinValue()));

        return BigDecimal.valueOf(random).setScale(3, RoundingMode.HALF_UP).doubleValue();
    }

}
