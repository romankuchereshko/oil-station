package com.simulation.oilstation.service;

import java.util.Collection;
import java.util.List;

import com.simulation.library.domain.Frame;

public interface GeneratorService {

    Collection<Frame> generateFrames(List<Long> wellIds);

}
