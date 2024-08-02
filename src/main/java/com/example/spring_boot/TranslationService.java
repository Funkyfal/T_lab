package com.example.spring_boot;

import org.example.ConnectionUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Service
public class TranslationService {
    private final RestTemplate restTemplate;
    private final ConnectionUtil connectionUtil;

    public TranslationService(RestTemplate restTemplate, ConnectionUtil connectionUtil){
        this.connectionUtil = connectionUtil;
        this.restTemplate = restTemplate;
    }

    public String translateWord(String word, String sourceLang, String targetLang){
        String url = "https://translate.google.com/?hl=ru&sl=" + sourceLang + "&tl=" + targetLang +
                "&text=" + word + "&op=translate";
        return restTemplate.getForObject(url, String.class);
    }

    public void saveTranslationRequest(String ipAddress, String inputText, String translatedText){
        try(Connection conn = connectionUtil.connect_to_db("TDB", "postgres", "DBPASSWORD")){
            String sql = "INSERT INTO translation_requests (ip_address, input_text, translated_text, timestamp) VALUES(?, ?, ?, ?)";
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1, ipAddress);
                pstmt.setString(2, inputText);
                pstmt.setString(3, translatedText);
                pstmt.setString(4, String.valueOf(Timestamp.valueOf(LocalDateTime.now())));
                pstmt.executeUpdate();
            }
        }   catch(SQLException e){
            e.printStackTrace();
        }
    }
}
