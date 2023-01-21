package com.simulation.oilstation.domain.properties;

import com.simulation.oilstation.domain.Value;

public interface FrameConfigurations {

    Value getVoltage();

    Value getCurrent();

    Value getSpeed();

    Value getFrequency();

    Value getTemperature();

    Value getPressure();

    Value getLiquidFlowRate();

}
