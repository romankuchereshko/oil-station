package com.simulator.oilstation.generator;

import com.simulator.oilstation.model.Frame;
import com.simulator.oilstation.model.Value;
import com.simulator.oilstation.properties.FrameProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class Generator {
    private final FrameProperties frameProperties;
    private final DecimalFormat df = new DecimalFormat("#.##");

    @Autowired
    public Generator(FrameProperties frameProperties) {
        this.frameProperties = frameProperties;
    }

    public Collection<Frame> generate(int wellNumber) {
        Collection<Frame> frameCollection = new ArrayList<>();
        for (int i = 0; i < wellNumber; i++) {
            frameCollection.add(new Frame.Builder()
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
                    .build());
        }

        return frameCollection;
    }

    private Double getRandomInRange(Double min, Double max) {
        return Double.valueOf(df.format(min + (Math.random() * (max - min))));
    }
}
