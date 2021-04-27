package com.simulator.oilstation.service;

import com.simulator.oilstation.OilStationApplication;
import com.simulator.oilstation.generator.Generator;
import com.simulator.oilstation.sender.FrameSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
public class WellService {
    private final FrameSender frameSender;
    private final Integer wellNumber;
    private final Generator generator;

    private final List<UUID> uuidList = new ArrayList<>();

    private AtomicBoolean isGenerating = new AtomicBoolean(Boolean.TRUE);

    @Autowired
    public WellService(@Qualifier("consoleFrameSenderImpl") FrameSender frameSender,
                       @Value("${well.number}") Integer wellNumber,
                       Generator generator) {
        this.frameSender = frameSender;
        this.wellNumber = wellNumber;
        this.generator = generator;
    }

    public void toggle() {
        isGenerating.set(!isGenerating.get());
    }

    @Scheduled(fixedRateString = "${cron.rate}")
    public void cronJob() {
        if (isGenerating.get()) {
            log.info("**************************************************************");
            frameSender.send(generator.generate(uuidList));
            log.info("**************************************************************");
        }
    }

    @PostConstruct
    public List<UUID> getUuidList() {
        for (int i = 0; i < wellNumber; i++) {
            uuidList.add(UUID.randomUUID());
        }
        return uuidList;
    }
}
