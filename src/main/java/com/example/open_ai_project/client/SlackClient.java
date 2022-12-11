package com.example.open_ai_project.client;

import com.example.open_ai_project.config.SlackConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SlackClient {
    private SlackConfig slackConfig;
    private WebClient webClient;

    public SlackClient(SlackConfig slackConfig) {
        this.webClient = WebClient.builder()
                .baseUrl(slackConfig.getUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
