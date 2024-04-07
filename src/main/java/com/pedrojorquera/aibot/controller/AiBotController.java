package com.pedrojorquera.aibot.controller;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AiBotController {

    private final ChatClient chatClient;

    AiBotController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/response")
    public ChatResponse getResponse(@RequestParam String input) {
        return chatClient.call(new Prompt(input));
    }

}
