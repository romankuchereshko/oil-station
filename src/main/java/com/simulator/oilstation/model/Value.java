package com.simulator.oilstation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Value {
    private Double value;
    private Double defaultValue;
    private String unit;
}
