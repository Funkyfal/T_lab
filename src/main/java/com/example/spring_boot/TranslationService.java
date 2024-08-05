package com.example.spring_boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@Service
public class TranslationService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String translateWord(String word, String sourceLang, String targetLang) {
        String url = "https://translate.google.com/?hl=ru&sl=" + sourceLang + "&tl=" + targetLang +
                "&text=" + word + "&op=translate";
        return restTemplate.getForObject(url, String.class);
    }

    public String translateText(String inputText, String sourceLang, String targerLang)
            throws InterruptedException, ExecutionException {
        String[] words = inputText.split("\\s+");
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<String>> futures = new ArrayList<>();

        for (String word : words) {
            futures.add(executor.submit(() -> translateWord(word, sourceLang, targerLang)));
        }

        StringBuilder translatedText = new StringBuilder();
        for(Future<String> future : futures){
            translatedText.append(future.get()).append(" ");
        }

        executor.shutdown();
        return translatedText.toString().trim();
    }
}
