package com.example.demo.consumingrest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumingRestController {
    @GetMapping("/consumingRest")
    public String consumingRest(RestTemplate restTemplate){
        Quote quote = restTemplate.getForObject(
                "https://quoters.apps.pcfone.io/api/random", Quote.class);
        return quote.toString();
    }
}