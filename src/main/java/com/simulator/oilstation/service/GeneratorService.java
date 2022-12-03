package com.simulator.oilstation.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.simulator.oilstation.domain.frame.Frame;
import com.simulator.oilstation.domain.frame.FrameProperties;
import com.simulator.oilstation.domain.value.Value;
import com.simulator.oilstation.domain.value.ValueProperty;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class GeneratorService {

    private final FrameProperties frameProperties;

    public Collection<Frame> generate(final List<UUID> uuidList) {
        final ValueProperty voltage = this.frameProperties.getVoltage();
        final ValueProperty current = this.frameProperties.getCurrent();
        final ValueProperty speed = this.frameProperties.getSpeed();
        final ValueProperty frequency = this.frameProperties.getFrequency();
        final ValueProperty temperature = this.frameProperties.getTemperature();
        final ValueProperty pressure = this.frameProperties.getPressure();
        final ValueProperty liquidFlowRate = this.frameProperties.getLiquidFlowRate();

        return uuidList.stream().map(uuid -> Frame.builder()
                .wellId(uuid)
                .voltage(Value.builder()
                    .unitValue(this.getRandomValueInRange(voltage.getMinValue(), voltage.getMaxValue()))
                    .unit(voltage.getUnit())
                    .build())
                .current(Value.builder()
                    .unitValue(this.getRandomValueInRange(current.getMinValue(), current.getMaxValue()))
                    .unit(current.getUnit())
                    .build())
                .speed(Value.builder()
                    .unitValue(this.getRandomValueInRange(speed.getMinValue(), speed.getMaxValue()))
                    .unit(speed.getUnit())
                    .build())
                .frequency(Value.builder()
                    .unitValue(this.getRandomValueInRange(frequency.getMinValue(), frequency.getMaxValue()))
                    .unit(frequency.getUnit())
                    .build())
                .temperature(Value.builder()
                    .unitValue(this.getRandomValueInRange(temperature.getMinValue(), temperature.getMaxValue()))
                    .unit(temperature.getUnit())
                    .build())
                .pressure(Value.builder()
                    .unitValue(this.getRandomValueInRange(pressure.getMinValue(), pressure.getMaxValue()))
                    .unit(pressure.getUnit())
                    .build())
                .liquidFlowRate(Value.builder()
                    .unitValue(this.getRandomValueInRange(liquidFlowRate.getMinValue(), liquidFlowRate.getMaxValue()))
                    .unit(liquidFlowRate.getUnit())
                    .build()).build())
            .toList();
    }

    private Double getRandomValueInRange(final Double min, final Double max) {
        double random = min + (Math.random() * (max - min));
        final BigDecimal decimal = BigDecimal.valueOf(random).setScale(2, RoundingMode.HALF_UP);
        return decimal.doubleValue();
    }
}
