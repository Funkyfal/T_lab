package com.example.spring_boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
        String url = UriComponentsBuilder.fromHttpUrl(DEEPL_TRANSLATE_URL)
                .queryParam("auth_key", apiKey)
                .queryParam("text", text)
                .queryParam("source_lang", sourceLang)
                .queryParam("target_lang", targetLang)
                .toUriString();

        TranslationResponse response = restTemplate.postForObject(url, null, TranslationResponse.class);
        String translatedText = "Translation failed";
        if (response != null && response.getTranslations() != null && !response.getTranslations().isEmpty()) {
            translatedText = response.getTranslations().get(0).getText();
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
