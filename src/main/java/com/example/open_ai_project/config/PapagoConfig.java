package com.example.open_ai_project.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "papago")
@Data
public class PapagoConfig {
    private String url;
    private String clientId;
    private String clientSecret;
}
