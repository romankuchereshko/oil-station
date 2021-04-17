package com.simulator.oilstation.service;

import com.simulator.oilstation.generator.Generator;
import com.simulator.oilstation.sender.FrameSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WellService {
    private boolean toggle;

    private final FrameSender frameSender;
    private final Integer wellNumber;
    private final Generator generator;

    @Autowired
    public WellService(@Qualifier("consoleFrameSenderImpl") FrameSender frameSender,
                       @Value("${well.number}") Integer wellNumber,
                       Generator generator) {
        this.frameSender = frameSender;
        this.wellNumber = wellNumber;
        this.generator = generator;
    }

    @Scheduled(fixedRateString = "${cron.rate}")
    public void cronJob() {
        log.info("**************************************************************");
        frameSender.send(generator.generate(wellNumber));
        log.info("**************************************************************");
    }
}
