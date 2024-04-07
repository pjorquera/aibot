package com.pedrojorquera.aibot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "aibot")
public class AiBotProperties {

    String apiKey;
    String model;

}
