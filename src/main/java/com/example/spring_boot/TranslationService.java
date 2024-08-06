package com.example.spring_boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;


@Service
public class TranslationService {

    private final RestTemplate restTemplate;
    private final JdbcTemplate jdbcTemplate;
    @Value("${deepl.api.key}")
    private String apiKey;

    private static final String DEEPL_TRANSLATE_URL = "https://api-free.deepl.com/v2/translate";

    public TranslationService(RestTemplate restTemplate, JdbcTemplate jdbcTemplate) {
        this.restTemplate = restTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    public String translateText(String text, String sourceLang, String targetLang) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        String requestBody = "auth_key=" + apiKey +
                "&text=" + UriUtils.encode(text, StandardCharsets.UTF_8) +
                "&source_lang=" + sourceLang +
                "&target_lang=" + targetLang;
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<TranslationResponse> response = restTemplate.exchange(DEEPL_TRANSLATE_URL, HttpMethod.POST, entity, TranslationResponse.class);
        String translatedText = "Translation failed";

        if (response.getBody() != null && response.getBody().getTranslations() != null && !response.getBody().getTranslations().isEmpty()) {
            translatedText = response.getBody().getTranslations().get(0).getText();
        }
        saveTranslationRequest(text, translatedText);
        return translatedText;
    }


    private void saveTranslationRequest(String inputText, String translatedText) {
        String ipAddress = "unknown";
        try {
            ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String sql = "INSERT INTO translation_requests (ip_address, input_text, translated_text, timestamp) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, ipAddress, inputText, translatedText, LocalDateTime.now());
    }
}
