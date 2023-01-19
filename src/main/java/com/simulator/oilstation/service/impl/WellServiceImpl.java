package com.simulator.oilstation.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.simulator.oilstation.service.GeneratorService;
import com.simulator.oilstation.service.WellService;
import com.simulator.oilstation.service.producer.FrameSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oil.station.domain.frame.Frame;

@Slf4j
@Service
@RequiredArgsConstructor
public class WellServiceImpl implements WellService {

    private final AtomicBoolean isGenerating = new AtomicBoolean(Boolean.FALSE);

    private final FrameSender frameSender;

    private final GeneratorService generatorService;

    @Value("${well.number}")
    private String wellsQuantity;

    @Override
    public boolean toggleGenerator() {
        this.isGenerating.set(!this.isGenerating.get());

        boolean currentState = this.isGenerating.get();

        if (currentState) {
            log.info("The generator is running now!");
        } else {
            log.info("The generator was stopped!");
        }
        return currentState;
    }

    @Scheduled(fixedRateString = "${cron.rate}")
    public void cronJob() {
        if (this.isGenerating.get()) {
            final List<Long> wellIds = this.generateWellIds();
            final Collection<Frame> frames = this.generatorService.generateFrames(wellIds);

            this.frameSender.send(frames);
        }
    }

    private List<Long> generateWellIds() {
        final List<Long> wellIds = LongStream.rangeClosed(1, Long.parseLong(this.wellsQuantity))
            .boxed()
            .collect(Collectors.toList());

        Collections.shuffle(wellIds);
        return wellIds;
    }

}