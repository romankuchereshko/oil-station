package com.simulator.oilstation.domain.value;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Value {

    private Double unitValue;

    private String unit;
}

