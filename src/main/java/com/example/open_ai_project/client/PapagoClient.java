package com.example.open_ai_project.client;

import com.example.open_ai_project.config.PapagoConfig;
import com.example.open_ai_project.enums.LanguageCode;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class PapagoClient {
    private PapagoConfig papagoConfig;
    private WebClient webClient;

    public PapagoClient(PapagoConfig papagoConfig) {
        this.papagoConfig = papagoConfig;
        this.webClient = WebClient.builder()
                .baseUrl(papagoConfig.getUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "charset=UTF-8")
                .defaultHeader("X-Naver-Client-Id", papagoConfig.getClientId())
                .defaultHeader("X-Naver-Client-Secret", papagoConfig.getClientSecret())
                .build();
    }

    public String translate(LanguageCode source, LanguageCode target, String text) {
        MultiValueMap<String, String> formData = makeForm(source.getCode(), target.getCode(), text);
        JSONObject result = request(formData);
        return (String) result.getJSONObject("message")
                .getJSONObject("result")
                .get("translatedText");
    }

    private JSONObject request(MultiValueMap<String, String> formData) {
        return webClient.post()
                .body(BodyInserters.fromFormData(formData))
                .exchangeToMono(
                        res ->  {
                            if (res.statusCode().equals(HttpStatus.OK)) {
                                return res.bodyToMono(JSONObject.class);
                            }
                            return null;
                        })
                .block();
    }

    private MultiValueMap<String, String> makeForm(String src, String tar, String text) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("source", src);
        formData.add("target", tar);
        formData.add("text", text);
        return formData;
    }
}
