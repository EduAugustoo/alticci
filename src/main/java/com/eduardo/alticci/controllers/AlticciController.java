package com.eduardo.alticci.controllers;

import com.eduardo.alticci.services.AlticciService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/alticci")
@RestController
public class AlticciController {

    private final AlticciService alticciService;

    @GetMapping("/{num}")
    public ResponseEntity<Integer> getAlticciValue(@PathVariable Integer num) {
        return ResponseEntity.ok(this.alticciService.calculateAlticciValue(num));
    }
}
