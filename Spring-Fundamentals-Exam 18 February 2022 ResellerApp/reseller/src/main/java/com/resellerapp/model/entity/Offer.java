package com.resellerapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity{
    @Column(nullable = false)
    private String description; //TODO: Description length must be between 2 and 50 characters

    @Column(nullable = false, columnDefinition = "FLOAT")
    private double price;

    @ManyToOne
    private Condition condition;

    @ManyToOne()
    private User user;

    @ManyToOne()
    private User buyer;

    public Offer() {
    }

    public String getDescription() {
        return description;
    }

    public Offer setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Offer setPrice(double price) {
        this.price = price;
        return this;
    }

    public Condition getCondition() {
        return condition;
    }

    public Offer setCondition(Condition condition) {
        this.condition = condition;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Offer setUser(User user) {
        this.user = user;
        return this;
    }

    public User getBuyer() {
        return buyer;
    }

    public Offer setBuyer(User buyer) {
        this.buyer = buyer;
        return this;
    }
}
