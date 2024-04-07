package com.pedrojorquera.aibot.agent;

import dev.langchain4j.agent.tool.Tool;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public class AiBotTools {

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
