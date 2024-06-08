package com.okode.aibot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
public class Policy {

    public enum Type { AUTO, HOME, HEALTH, LIFE }

    private @Id String id = UUID.randomUUID().toString();

    @NonNull private Type type;
    @NonNull private String description;
    @NonNull private Double price;
    @NonNull private Date contractDate;

    public Policy applyDiscount() {
        price = price * 0.95;
        return this;
    }

}
