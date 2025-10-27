package com.example.Api_Postgresql.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KeepTaskAlive {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${TOKEN_USER}")
    private String userToken;

    @Scheduled(fixedRate = 600000)
    public void ping() {
        try {

            ResponseEntity<String> response = restTemplate.getForEntity(
                    "https://api-postgresql-zeta-fide.onrender.com/api/health",
                    String.class
            );

            System.out.println("Ping realizado com status: " + response.getStatusCode());
        } catch (Exception e) {
            System.out.println("Failure on ping API: " + e.getMessage());
        }
    }

}
