package com.example.musicdbexam.model.entity;

import com.example.musicdbexam.model.entity.enums.GenreEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "albums")
public class AlbumEntity extends BaseEntity{

    private String name;
    private String imageUrl;
    private String description;
    private Integer copies;
    private BigDecimal price;
    private LocalDate releaseDate;
    private String producer;
    private GenreEnum genre;
    private ArtistEntity artist;
    private UserEntity addedFrom;

    public AlbumEntity() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(nullable = false)
    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }
    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(nullable = false)
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Column
    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
    @Enumerated(EnumType.ORDINAL)
    public GenreEnum getGenre() {
        return genre;
    }

    public void setGenre(GenreEnum genre) {
        this.genre = genre;
    }

    @ManyToOne
    public ArtistEntity getArtist() {
        return artist;
    }

    public void setArtist(ArtistEntity artist) {
        this.artist = artist;
    }

    @ManyToOne
    public UserEntity getAddedFrom() {
        return addedFrom;
    }

    public void setAddedFrom(UserEntity addedFrom) {
        this.addedFrom = addedFrom;
    }
}
