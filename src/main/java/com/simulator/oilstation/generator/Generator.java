package com.simulator.oilstation.generator;

import com.simulator.oilstation.model.Frame;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class Generator {

    public Collection<Frame> generate(int wellNumber) {
        Collection<Frame> frameCollection = new ArrayList<>();
        for (int i = 0; i < wellNumber; i++) {
            frameCollection.add(new Frame.Builder().build());
        }

        return frameCollection;
    }
}
