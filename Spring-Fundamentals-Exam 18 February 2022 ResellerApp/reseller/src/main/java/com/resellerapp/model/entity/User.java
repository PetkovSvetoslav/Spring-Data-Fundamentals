package com.resellerapp.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; //TODO: Password length must be between 3 and 20 characters

    @Column(unique = true, nullable = false)
    private String email; //TODO: must be email

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Offer.class, mappedBy = "user")
    private Set<Offer> offers;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Offer.class, mappedBy = "buyer")
    private Set<Offer> boughtOffers;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public User setOffers(Set<Offer> offers) {
        this.offers = offers;
        return this;
    }

    public Set<Offer> getBoughtOffers() {
        return boughtOffers;
    }

    public User setBoughtOffers(Set<Offer> boughtOffers) {
        this.boughtOffers = boughtOffers;
        return this;
    }
}
