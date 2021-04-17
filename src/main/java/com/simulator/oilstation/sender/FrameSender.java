package com.simulator.oilstation.sender;

import com.simulator.oilstation.model.Frame;

import java.util.Collection;

public interface FrameSender {

    void send(Collection<Frame> frames);
}
