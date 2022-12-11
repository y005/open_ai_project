package com.example.open_ai_project.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PapagoConfigTest {
    @Autowired
    private PapagoConfig papagoConfig;

    @Test
    void getUrl() {
        assertEquals(papagoConfig.getUrl(), "https://openapi.naver.com/v1/papago/n2mt");
    }

    @Test
    void getClientId() {
    }

    @Test
    void getClientSecret() {
    }
}