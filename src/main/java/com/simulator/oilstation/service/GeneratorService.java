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

@Slf4j
@Service
@AllArgsConstructor
public class GeneratorService {

    private final FrameProperties frameProperties;

    public Collection<Frame> generate(final List<UUID> wellsUuids) {
        final ValueProperty voltage = this.frameProperties.getVoltage();
        final ValueProperty current = this.frameProperties.getCurrent();
        final ValueProperty speed = this.frameProperties.getSpeed();
        final ValueProperty frequency = this.frameProperties.getFrequency();
        final ValueProperty temperature = this.frameProperties.getTemperature();
        final ValueProperty pressure = this.frameProperties.getPressure();
        final ValueProperty liquidFlowRate = this.frameProperties.getLiquidFlowRate();

        return wellsUuids.stream()
            .map(uuid -> Frame.builder()
                .frameId(UUID.randomUUID())
                .creationDateTime(LocalDateTime.now())
                .wellId(uuid)
                .voltage(this.getRandomValueInRange(voltage.getMinValue(), voltage.getMaxValue()))
                .current(this.getRandomValueInRange(current.getMinValue(), current.getMaxValue()))
                .speed(this.getRandomValueInRange(speed.getMinValue(), speed.getMaxValue()))
                .frequency(this.getRandomValueInRange(frequency.getMinValue(), frequency.getMaxValue()))
                .temperature(this.getRandomValueInRange(temperature.getMinValue(), temperature.getMaxValue()))
                .pressure(this.getRandomValueInRange(pressure.getMinValue(), pressure.getMaxValue()))
                .liquidFlowRate(this.getRandomValueInRange(liquidFlowRate.getMinValue(), liquidFlowRate.getMaxValue()))
                .build())
            .toList();
    }

    private Double getRandomValueInRange(final Double min, final Double max) {
        double random = min + (Math.random() * (max - min));
        final BigDecimal decimal = BigDecimal.valueOf(random).setScale(3, RoundingMode.HALF_UP);
        return decimal.doubleValue();
    }
}
