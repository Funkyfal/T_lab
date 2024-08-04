package com.example.spring_boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Service
public class TranslationService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String translateWord(String word, String sourceLang, String targetLang){
        String url = "https://translate.google.com/?hl=ru&sl=" + sourceLang + "&tl=" + targetLang +
                "&text=" + word + "&op=translate";
        return restTemplate.getForObject(url, String.class);
    }
}
