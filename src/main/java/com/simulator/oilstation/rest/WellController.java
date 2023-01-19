package com.simulator.oilstation.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simulator.oilstation.rest.dto.SuccessInfoDTO;
import com.simulator.oilstation.service.WellService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class WellController {

    private final WellService wellService;

    @GetMapping(value = "/toggle-generator")
    public ResponseEntity<SuccessInfoDTO> toggleGenerator() {
        boolean isGenerating = this.wellService.toggleGenerator();

        return ResponseEntity.ok(SuccessInfoDTO.builder()
            .status(HttpStatus.OK.value())
            .message(this.buildMessage(isGenerating))
            .build());
    }

    private String buildMessage(final boolean isGenerating) {
        return isGenerating ? "The generator is running now!" : "The generator was stopped!";
    }

}