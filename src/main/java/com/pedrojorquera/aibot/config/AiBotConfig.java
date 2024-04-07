package com.pedrojorquera.aibot.config;

import com.pedrojorquera.aibot.agent.AiBotAgent;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiBotConfig {

    @Bean
    public AiBotAgent agent(ChatLanguageModel chatLanguageModel) {
        return AiServices.builder(AiBotAgent.class)
                .chatLanguageModel(chatLanguageModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(20))
                .build();
    }

}
