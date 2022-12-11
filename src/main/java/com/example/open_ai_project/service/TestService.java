package com.example.open_ai_project.service;

import com.example.open_ai_project.client.OpenAiClient;
import com.example.open_ai_project.client.PapagoClient;
import com.example.open_ai_project.client.SlackClient;
import com.example.open_ai_project.enums.LanguageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TestService {
    private final OpenAiClient openAiClient;
    private final PapagoClient papagoClient;
    private final SlackClient slackClient;

    public String call(String text) {
        String query = papagoClient.translate(LanguageCode.KOREAN, LanguageCode.ENGLISH, text);
        String answer = openAiClient.call(query);
        return papagoClient.translate(LanguageCode.ENGLISH, LanguageCode.KOREAN, answer);
    }
}
