package com.simulator.oilstation.service.frame;

import java.util.Collection;

import oil.station.domain.frame.Frame;

public interface FrameSender {

    void send(Collection<Frame> frames);

}
