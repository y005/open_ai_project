package com.example.open_ai_project.enums;

import lombok.Getter;

public enum LanguageCode {
    KOREAN("ko"),
    ENGLISH("en"),
    ;
    @Getter
    private String code;

    LanguageCode(String code) {
        this.code = code;
    }
}
