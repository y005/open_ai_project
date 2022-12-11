package com.example.open_ai_project.controller;

import com.example.open_ai_project.dto.QueryRequest;
import com.example.open_ai_project.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class TestController {
    private final TestService testService;

    @PostMapping("/test")
    public String test(@RequestBody QueryRequest request) {
        return testService.call(request.getQuery());
    }
}
