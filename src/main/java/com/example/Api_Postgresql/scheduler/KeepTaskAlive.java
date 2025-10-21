package com.example.Api_Postgresql.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class KeepTaskAlive {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${TOKEN_USER}")
    private String adminToken;

    @Scheduled(fixedRate = 600000)
    public void ping() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + adminToken);
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));

            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    "https://api-postgresql-zeta-fide.onrender.com/api/ping",
                    HttpMethod.GET,
                    requestEntity,
                    String.class
            );

            System.out.println("Ping realizado com status: " + response.getStatusCode());
        } catch (Exception e) {
            System.out.println("Failure on ping API: " + e.getMessage());
        }
    }

}
