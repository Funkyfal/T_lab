package com.example.spring_boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DeepLConfig {

    @Bean(name = "deepLRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
