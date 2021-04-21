package com.simulator.oilstation.rest;

import com.simulator.oilstation.service.WellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WellRestController {
    private final WellService wellService;

    @Autowired
    public WellRestController(WellService wellService) {
        this.wellService = wellService;
    }

    @GetMapping(value = "/toggle")
    public void toggleGenerator() {
        wellService.toggle();
    }
}
