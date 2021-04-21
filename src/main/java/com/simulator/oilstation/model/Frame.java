package com.simulator.oilstation.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Frame {
    private final UUID frameId = UUID.randomUUID();
    private UUID wellId;
    private Value voltage;
    private Value current;
    private Value speed;
    private Value frequency;
    private Value temperature;
    private Value pressure;
    private Value liquidFlowRate;
    private final LocalDateTime timestamp = LocalDateTime.now();

    private Frame() {
    }

    public static class Builder {
        private final Frame frame;

        public Builder() {
            this.frame = new Frame();
        }

        public Builder voltage(Value voltage) {
            frame.voltage = voltage;
            return this;
        }

        public Builder current(Value current) {
            frame.current = current;
            return this;
        }

        public Builder speed(Value speed) {
            frame.speed = speed;
            return this;
        }

        public Builder frequency(Value frequency) {
            frame.frequency = frequency;
            return this;
        }

        public Builder temperature(Value temperature) {
            frame.temperature = temperature;
            return this;
        }

        public Builder pressure(Value pressure) {
            frame.pressure = pressure;
            return this;
        }

        public Builder liquidFlowRate(Value liquidFlowRate) {
            frame.liquidFlowRate = liquidFlowRate;
            return this;
        }

        public Frame build() {
            return frame;
        }
    }
}
