package com.simulator.oilstation.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Frame {
    private final UUID frameId = UUID.randomUUID();
    private Double voltage; // V
    private Double current; // A
    private Double speed; // rpm
    private Double frequency; // Hz
    private Double temperature; // F
    private Double pressure; // bar
    private Double liquidFlowRate; // m3/h
    private final LocalDateTime timestamp = LocalDateTime.now();

    private Frame() {
    }

    public static class Builder {
        private final Frame frame;

        public Builder() {
            this.frame = new Frame();
        }

        public Builder voltage(Double voltage) {
            frame.voltage = voltage;
            return this;
        }

        public Builder current(Double current) {
            frame.current = current;
            return this;
        }

        public Builder speed(Double speed) {
            frame.speed = speed;
            return this;
        }

        public Builder frequency(Double frequency) {
            frame.frequency = frequency;
            return this;
        }

        public Builder temperature(Double temperature) {
            frame.temperature = temperature;
            return this;
        }

        public Builder pressure(Double pressure) {
            frame.pressure = pressure;
            return this;
        }

        public Builder liquidFlowRate(Double liquidFlowRate) {
            frame.liquidFlowRate = liquidFlowRate;
            return this;
        }

        public Frame build() {
            return frame;
        }
    }
}
