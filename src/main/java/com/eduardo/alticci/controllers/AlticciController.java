package com.eduardo.alticci.controllers;

import com.eduardo.alticci.services.AlticciService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@Tag(name = "Alticci Sequence")
@RequiredArgsConstructor
@RequestMapping("/alticci")
@RestController
public class AlticciController {

    private final AlticciService alticciService;

    @Operation(summary = "Calculate Alticci sequence value")
    @ApiResponse(responseCode = "200", description = "Value calculated")
    @GetMapping("/{num}")
    public ResponseEntity<BigInteger> getAlticciValue(@PathVariable Integer num) {
        return ResponseEntity.ok(this.alticciService.calculateAlticciValue(num));
    }
}
