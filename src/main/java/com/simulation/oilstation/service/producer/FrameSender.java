package com.simulation.oilstation.service.producer;

import java.util.Collection;

import com.simulation.library.domain.Frame;

public interface FrameSender {

    void send(Collection<Frame> frames);

}
