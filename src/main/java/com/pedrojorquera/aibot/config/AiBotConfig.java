package com.pedrojorquera.aibot.config;

import com.pedrojorquera.aibot.agent.AiBotAgent;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AiBotProperties.class)
public class AiBotConfig {

    @Bean
    ChatLanguageModel openAiChatModel(AiBotProperties properties) {
        return OpenAiChatModel.builder()
                .apiKey(properties.getApiKey())
                .modelName(properties.getModel())
                .logRequests(true)
                .logResponses(true)
                .build();
    }

    @Bean
    public AiBotAgent agent(ChatLanguageModel chatLanguageModel) {
        return AiServices.builder(AiBotAgent.class)
                .chatLanguageModel(chatLanguageModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(20))
                .build();
    }

}
