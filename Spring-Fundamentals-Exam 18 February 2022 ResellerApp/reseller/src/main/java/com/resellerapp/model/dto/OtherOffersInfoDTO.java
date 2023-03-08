package com.resellerapp.model.dto;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.enums.ConditionName;

import javax.naming.CompositeName;
import java.util.UUID;

public class OtherOffersInfoDTO {
    private UUID id;

    private String description;

    private double price;

    private String condition;

    private String sellerUsername;

    public OtherOffersInfoDTO(UUID id, String description, double price, ConditionName condition, String sellerUsername) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.condition = condition.name();
        this.sellerUsername = sellerUsername;
    }

    public UUID getId() {
        return id;
    }

    public OtherOffersInfoDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OtherOffersInfoDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public OtherOffersInfoDTO setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public OtherOffersInfoDTO setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public OtherOffersInfoDTO setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
        return this;
    }
}
