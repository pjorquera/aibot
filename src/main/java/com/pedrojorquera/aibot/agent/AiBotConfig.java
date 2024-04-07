package com.pedrojorquera.aibot.agent;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class AiBotConfig {

    @Bean
    AiBotAgent aiBotAgent(ChatLanguageModel model) {
        return AiServices.builder(AiBotAgent.class)
                .chatLanguageModel(model)
                .tools(new AiBotTools())
                .build();
    }

    @Bean
    ApplicationRunner interactiveChatRunner(AiBotAgent agent) {
        return args -> {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("User: ");
                String userMessage = scanner.nextLine();

                if ("exit".equalsIgnoreCase(userMessage)) {
                    break;
                }

                String agentMessage = agent.chat(userMessage);
                System.out.println("Agent: " + agentMessage);
            }

            scanner.close();
        };
    }

}
