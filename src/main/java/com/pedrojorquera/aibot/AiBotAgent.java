package com.pedrojorquera.aibot;

import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.service.spring.AiService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@Component
class AiBotTools {

    @Tool("Get my personal info")
    Map<String, Object> getPersonalInfo() {
        return Map.of(
                "name", "Pedro",
                "lastname", "Jorquera",
                "birthdate", LocalDate.of(1974, 7, 20)
        );
    }

    @Tool("Get current time")
    Date getCurrentTime() {
        return new Date();
    }
}

@AiService(tools = "aiBotTools")
public interface AiBotAgent {
    String chat(String input);
}
