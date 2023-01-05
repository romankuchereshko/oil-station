package com.simulator.oilstation.domain.properties;

import lombok.Data;

@Data
public class ValueProperty {

    private Double minValue;

    private Double maxValue;

    private Double minCriticalValue;

    private Double maxCriticalValue;

}
