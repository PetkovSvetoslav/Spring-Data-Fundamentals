package com.resellerapp.model.dto;

public class UserBoughtOffersDTO {
    private String description;

    private double price;

    public UserBoughtOffersDTO() {
    }

    public UserBoughtOffersDTO(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public UserBoughtOffersDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public UserBoughtOffersDTO setPrice(double price) {
        this.price = price;
        return this;
    }
}
