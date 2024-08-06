package com.example.spring_boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/translate")
public class TranslationController {

    @Autowired
    private TranslationService translationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public CompletableFuture<ResponseEntity<String>> translate(@RequestBody TranslationRequest request) {
        return translationService.translateText(request.getText(), request.getSourceLang(), request.getTargetLang())
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/languages")
    public List<String> getLanguages() {
        return Arrays.asList("EN", "DE", "FR", "ES", "RU", "IT", "JA", "ZH");
    }
}
