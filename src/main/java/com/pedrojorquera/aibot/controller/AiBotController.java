package com.pedrojorquera.aibot.controller;

import com.pedrojorquera.aibot.agent.AiBotAgent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AiBotController {

    private AiBotAgent agent;

    AiBotController(AiBotAgent agent) {
        this.agent = agent;
    }

    @GetMapping("/response")
    public String getResponse(@RequestParam String input) {
        return agent.chat(input);
    }

}
