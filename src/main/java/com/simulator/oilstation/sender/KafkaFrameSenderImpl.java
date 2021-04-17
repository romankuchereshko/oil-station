package com.simulator.oilstation.sender;

import com.simulator.oilstation.model.Frame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
@Component
public class KafkaFrameSenderImpl implements FrameSender {

    @Override
    public void send(Collection<Frame> frames) {

    }
}
