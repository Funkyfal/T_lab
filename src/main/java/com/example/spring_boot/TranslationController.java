package com.example.spring_boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/translate")
public class TranslationController {

    @Autowired
    private TranslationService translationService;

    @PostMapping
    public String translate(@RequestBody TranslationRequest request) {
        return translationService.translateText(request.getText(), request.getSourceLang(), request.getTargetLang());
    }
}
