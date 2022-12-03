package com.simulator.oilstation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.simulator.oilstation.service.frame.FrameSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WellService {

    private final List<UUID> wellsUuids = new ArrayList<>();

    private final AtomicBoolean isGenerating = new AtomicBoolean(Boolean.FALSE);

    private final FrameSender kafkaFrameSender;

    private final GeneratorService generatorService;

    @Value("${well.number}")
    private String wellsQuantity;

    public void toggleGenerator() {
        this.isGenerating.set(!this.isGenerating.get());

        if (this.isGenerating.get()) {
            log.info("Generator is working now!");
        } else {
            log.info("Generator has stopped!");
        }
    }

    @PostConstruct
    private List<UUID> defineWellsUuidList() {
        IntStream.range(0, Integer.parseInt(this.wellsQuantity))
            .forEach(value -> this.wellsUuids.add(UUID.randomUUID()));
        return this.wellsUuids;
    }

    @Scheduled(fixedRateString = "${cron.rate}")
    public void cronJob() {
        if (this.isGenerating.get()) {
            this.kafkaFrameSender.send(this.generatorService.generate(this.wellsUuids));
        }
    }
}