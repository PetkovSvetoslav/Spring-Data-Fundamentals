package com.resellerapp.model.dto;

import com.resellerapp.model.enums.ConditionName;

import java.util.UUID;

public class LoggedUserOfferInfoDTO {
    private UUID id;
    private String description;
    private double price;
    private String condition;

    public LoggedUserOfferInfoDTO(UUID id, String description, double price, ConditionName condition) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.condition = condition.getValue();
    }

    public UUID getId() {
        return id;
    }

    public LoggedUserOfferInfoDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public LoggedUserOfferInfoDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public LoggedUserOfferInfoDTO setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public LoggedUserOfferInfoDTO setCondition(String condition) {
        this.condition = condition;
        return this;
    }
}
