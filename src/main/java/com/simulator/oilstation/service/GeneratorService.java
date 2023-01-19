package com.simulator.oilstation.service;

import java.util.Collection;
import java.util.List;

import oil.station.domain.frame.Frame;

public interface GeneratorService {

    Collection<Frame> generateFrames(List<Long> wellIds);

}
