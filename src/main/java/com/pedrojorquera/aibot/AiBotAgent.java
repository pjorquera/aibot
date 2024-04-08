package com.pedrojorquera.aibot;

import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.spring.AiService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

@Configuration
class Config {

    @Bean
    ChatMemory aiBotChatMemory() {
        return MessageWindowChatMemory.withMaxMessages(30);
    }

    @Bean
    ApplicationRunner applicationRunner(AiBotAgent aiBotAgent) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("User: ");
                String userMessage = scanner.nextLine();
                if ("exit".equalsIgnoreCase(userMessage)) {
                    break;
                }
                System.out.println("Agent: " + aiBotAgent.chat(userMessage));
            }
            scanner.close();
        };
    }

}

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

@AiService(tools = "aiBotTools", chatMemory = "aiBotChatMemory")
public interface AiBotAgent {
    String chat(String input);
}
