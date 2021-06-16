package com.simulator.oilstation.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.oilstation.model.Frame;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@Slf4j
public class ConsoleFrameSenderImpl implements FrameSender {

    private final ObjectMapper objectMapper;

    public ConsoleFrameSenderImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void send(Collection<Frame> frames) {
        frames.forEach(frame -> {
            try {
                log.info(objectMapper.writeValueAsString(frame));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }
}
