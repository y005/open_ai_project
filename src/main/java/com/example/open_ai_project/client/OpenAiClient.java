package com.example.open_ai_project.client;

import com.example.open_ai_project.config.OpenAiConfig;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class OpenAiClient {
    private OpenAiConfig openAiConfig;
    private OpenAiService openAiService;

    public OpenAiClient(OpenAiConfig openAiConfig) {
        openAiService = new OpenAiService(openAiConfig.getApiKey());
    }

    public String call(String message) {
        StringBuilder answer = new StringBuilder();

        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(message + "\n")
                .echo(true)
                .temperature(0d)
                .maxTokens(100)
                .topP(1d)
                .frequencyPenalty(0.0)
                .presencePenalty(0.0)
                .stop(List.of("\n"))
                .build();

        openAiService.createCompletion(completionRequest)
                .getChoices()
                .stream()
                .map(CompletionChoice::getText)
                .forEach(answer::append);
        log.info("Question {} -> Answer {}", message, answer);
        return answer.toString();
    }
}
