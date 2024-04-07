package com.pedrojorquera.aibot.agent;

import dev.langchain4j.service.SystemMessage;

public interface AiBotAgent {

    @SystemMessage({
       "You are a simple chat bot"
    })
    String chat(String input);

}
