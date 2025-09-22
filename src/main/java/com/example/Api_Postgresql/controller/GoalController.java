package com.example.Api_Postgresql.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api/goals")
@RestController
public class GoalController {

    @GetMapping("/listAllGoals")
    public ResponseEntity<String> listAllGoals() {
        return ResponseEntity.ok("All Goals");
    }

}
