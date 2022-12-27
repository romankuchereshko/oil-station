package com.simulator.oilstation.service.frame.impl;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.oilstation.service.frame.FrameSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oil.station.domain.frame.Frame;

/*
    Use only for testing
*/
@Slf4j
@Component
@AllArgsConstructor
public class TestConsoleFrameSenderImpl implements FrameSender {

    private ObjectMapper objectMapper;

    @Override
    public void send(final Collection<Frame> frames) {
        frames.forEach(frame -> {
            try {
                log.info(this.objectMapper.writeValueAsString(frame));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }
}
