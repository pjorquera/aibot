package com.pedrojorquera.aibot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import java.util.Date;

@Data
@Document
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
public class Policy {

    public enum Type { AUTO, HOME, HEALTH, LIFE };

    private @Id @GeneratedValue(strategy = GenerationStrategy.UNIQUE) String id;

    @NonNull private Type type;
    @NonNull private String description;
    @NonNull private Double price;
    @NonNull private Date contractDate;

    public Policy applyDiscount() {
        price = price * 0.95;
        return this;
    }

}
