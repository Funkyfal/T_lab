package com.example.spring_boot;


import java.security.Timestamp;

public class TranslationRequest {


    private Long id;

    private String ipAddress;

    private String inputText;

    private String translatedText;

    private Timestamp timestamp;

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

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format(
                "TranslationRequest[id=%d, ipAddress='%s', inputText='%s'," +
                        " inputText='%s', translatedText='%s', timeStamp='%s']",
                id, ipAddress, inputText, translatedText, timestamp);
    }
}