package com.simulator.oilstation.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.simulator.oilstation.domain.Value;
import com.simulator.oilstation.domain.properties.FrameConfigurations;
import com.simulator.oilstation.service.GeneratorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oil.station.domain.frame.Frame;

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
            .map(id -> {
                final Frame frame = Frame.builder().isCritical(Boolean.FALSE).build();

                frame.setWellId(id);
                frame.setVoltage(this.calculateRandomValueInRange(frame, voltage));
                frame.setCurrent(this.calculateRandomValueInRange(frame, current));
                frame.setSpeed(this.calculateRandomValueInRange(frame, speed));
                frame.setFrequency(this.calculateRandomValueInRange(frame, frequency));
                frame.setTemperature(this.calculateRandomValueInRange(frame, temperature));
                frame.setPressure(this.calculateRandomValueInRange(frame, pressure));
                frame.setLiquidFlowRate(this.calculateRandomValueInRange(frame, liquidFlowRate));
                frame.setCreatedAt(LocalDateTime.now());

                return frame;
            })
            .toList();
    }

    private Double calculateRandomValueInRange(final Frame frame, final Value value) {
        double random =
            value.getMinValue() + (Math.random() * (value.getMaxValue() - value.getMinValue()));
        double roundedValue = BigDecimal.valueOf(random).setScale(3, RoundingMode.HALF_UP).doubleValue();

        if (this.checkCriticalValue(roundedValue, value)) {
            frame.setIsCritical(Boolean.TRUE);
        }

        return roundedValue;
    }

    private boolean checkCriticalValue(final double roundedValue, final Value value) {
        return (roundedValue <= value.getMinCriticalValue() && roundedValue >= value.getMinValue()) ||
            (roundedValue >= value.getMaxCriticalValue() && roundedValue <= value.getMaxValue());
    }

}
