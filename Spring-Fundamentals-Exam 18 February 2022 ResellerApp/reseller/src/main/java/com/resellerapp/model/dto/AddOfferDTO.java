package com.resellerapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class AddOfferDTO {
    @NotNull
    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    private String description;

    @NotNull
    @Positive(message = "Price must be positive number!")
    private double price;

    @NotBlank(message = "You must select a condition!")
    private String conditionStr;

    public AddOfferDTO() {
    }

    public String getDescription() {
        return description;
    }

    public AddOfferDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public AddOfferDTO setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getConditionStr() {
        return conditionStr;
    }

    public AddOfferDTO setConditionStr(String conditionStr) {
        this.conditionStr = conditionStr;
        return this;
    }
}
