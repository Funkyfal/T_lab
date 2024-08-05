package com.example.spring_boot;

import java.time.LocalDateTime;

public class TranslationRequest {
    private Long id;
    private String ipAddress;
    private String text;
    private String translatedText;
    private LocalDateTime timestamp;
    private String sourceLang;
    private String targetLang;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getSourceLang() {
        return sourceLang;
    }

    public void setSourceLang(String sourceLang) {
        this.sourceLang = sourceLang;
    }

    public String getTargetLang() {
        return targetLang;
    }

    public void setTargetLang(String targetLang) {
        this.targetLang = targetLang;
    }

    @Override
    public String toString() {
        return String.format(
                "TranslationRequest[id=%d, ipAddress='%s', text='%s', translatedText='%s', timestamp='%s', sourceLang='%s', targetLang='%s']",
                id, ipAddress, text, translatedText, timestamp, sourceLang, targetLang);
    }
}
