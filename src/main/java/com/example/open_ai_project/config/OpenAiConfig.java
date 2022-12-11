package com.example.open_ai_project.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
 import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "open-ai")
@Data
public class OpenAiConfig {
    private String url;
    private String apiKey;
}
