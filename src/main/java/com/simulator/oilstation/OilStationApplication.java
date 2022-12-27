package com.simulator.oilstation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class OilStationApplication {

	public static void main(String[] args) {
		SpringApplication.run(OilStationApplication.class, args);
		log.info("Running...");
	}

}
