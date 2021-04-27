package com.simulator.oilstation.generator;

import com.simulator.oilstation.model.Frame;
import com.simulator.oilstation.model.Value;
import com.simulator.oilstation.properties.FrameProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
public class Generator {
    private final FrameProperties frameProperties;

    @Autowired
    public Generator(FrameProperties frameProperties) {
        this.frameProperties = frameProperties;
    }

    public Collection<Frame> generate(List<UUID> uuidList) {
        Collection<Frame> frameCollection = new ArrayList<>();
        uuidList.forEach(uuid ->
                frameCollection.add(new Frame.Builder()
                        .wellId(uuid)
                        .voltage(Value.builder()
                                .defaultValue(frameProperties.getVoltageDefaultValue())
                                .value(getRandomInRange(frameProperties.getVoltageMinValue(), frameProperties.getVoltageMaxValue()))
                                .unit(frameProperties.getVoltageUnit())
                                .build())
                        .current(Value.builder().defaultValue(frameProperties.getCurrentDefaultValue())
                                .value(getRandomInRange(frameProperties.getCurrentMinValue(), frameProperties.getCurrentMaxValue()))
                                .unit(frameProperties.getCurrentUnit())
                                .build())
                        .speed(Value.builder().defaultValue(frameProperties.getSpeedDefaultValue())
                                .value(getRandomInRange(frameProperties.getSpeedMinValue(), frameProperties.getSpeedMaxValue()))
                                .unit(frameProperties.getSpeedUnit())
                                .build())
                        .frequency(Value.builder().defaultValue(frameProperties.getFrequencyDefaultValue())
                                .value(getRandomInRange(frameProperties.getFrequencyMinValue(), frameProperties.getFrequencyMaxValue()))
                                .unit(frameProperties.getFrequencyUnit())
                                .build())
                        .temperature(Value.builder().defaultValue(frameProperties.getTemperatureDefaultValue())
                                .value(getRandomInRange(frameProperties.getTemperatureMinValue(), frameProperties.getTemperatureMaxValue()))
                                .unit(frameProperties.getTemperatureUnit())
                                .build())
                        .pressure(Value.builder().defaultValue(frameProperties.getPressureDefaultValue())
                                .value(getRandomInRange(frameProperties.getPressureMinValue(), frameProperties.getPressureMaxValue()))
                                .unit(frameProperties.getPressureUnit())
                                .build())
                        .liquidFlowRate(Value.builder().defaultValue(frameProperties.getLiquidFlowRateDefaultValue())
                                .value(getRandomInRange(frameProperties.getLiquidFlowRateMinValue(), frameProperties.getLiquidFlowRateMaxValue()))
                                .unit(frameProperties.getLiquidFlowRateUnit())
                                .build())
                        .build())
        );
        return frameCollection;
    }

    private Double getRandomInRange(Double min, Double max) {
        double random = min + (Math.random() * (max - min));
        BigDecimal decimal = new BigDecimal(random).setScale(2, RoundingMode.HALF_UP);
        return decimal.doubleValue();
    }
}
