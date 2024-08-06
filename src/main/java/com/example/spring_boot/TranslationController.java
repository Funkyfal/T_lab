package com.example.spring_boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/translate")
public class TranslationController {

    @Autowired
    private TranslationService translationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> translate(@RequestBody TranslationRequest request) {
        String translatedText = translationService.translateText(request.getText(), request.getSourceLang(), request.getTargetLang());
        return ResponseEntity.ok(translatedText);
    }

    @GetMapping("/languages")
    public List<String> getLanguages() {
        return Arrays.asList("EN", "DE", "FR", "ES", "RU", "IT", "JA", "ZH");
    }
}
