package com.simulation.oilstation.service.producer;

import java.util.Collection;

import oil.station.domain.frame.Frame;

public interface FrameSender {

    void send(Collection<Frame> frames);

}
