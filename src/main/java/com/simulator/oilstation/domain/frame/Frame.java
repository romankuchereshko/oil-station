package com.simulator.oilstation.domain.frame;

import java.time.LocalDateTime;
import java.util.UUID;

import com.simulator.oilstation.domain.value.Value;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Frame {

    private final LocalDateTime timestamp = LocalDateTime.now();

    private final UUID frameId = UUID.randomUUID();

    private UUID wellId;

    private Value voltage;

    private Value current;

    private Value speed;

    private Value frequency;

    private Value temperature;

    private Value pressure;

    private Value liquidFlowRate;

}
