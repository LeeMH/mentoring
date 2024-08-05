package me.mhlee.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, " + Instant.now().getEpochSecond();
    }
}
