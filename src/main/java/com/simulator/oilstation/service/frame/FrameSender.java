package com.simulator.oilstation.service.frame;

import java.util.Collection;

import com.simulator.oilstation.domain.frame.Frame;

public interface FrameSender {

    void send(Collection<Frame> frames);

}
