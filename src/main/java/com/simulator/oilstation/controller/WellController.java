package com.simulator.oilstation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simulator.oilstation.service.WellService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class WellController {

    private final WellService wellService;

    @GetMapping(value = "/toggle")
    public ResponseEntity<HttpStatus> toggleGenerator() {
        this.wellService.toggleGenerator();

        return ResponseEntity.ok(HttpStatus.OK);
    }

}