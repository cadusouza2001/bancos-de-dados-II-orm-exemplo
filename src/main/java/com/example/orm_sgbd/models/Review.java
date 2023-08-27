package com.example.orm_sgbd.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "reviews", uniqueConstraints = {@UniqueConstraint(columnNames = {"idMovie", "idUser"})})
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idReview;

    private String description;
    @Column(nullable = false)
    private double rating;

    //Review date generated automatically
    private java.sql.Date reviewDate = new java.sql.Date(System.currentTimeMillis());

    //Many reviews to one movie
    @ManyToOne
    @JoinColumn(name = "idMovie")
    private Movie movie;

    //Many reviews to one user
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    public Review() {
        //Empty constructor for JPA
    }

    public Review(UUID idReview, String description, double rating, Date reviewDate, Movie movie, User user) {
        this.idReview = idReview;
        this.description = description;
        this.rating = rating;
        this.reviewDate = reviewDate;
        this.movie = movie;
        this.user = user;
    }

    public UUID getIdReview() {
        return idReview;
    }

    public void setIdReview(UUID idReview) {
        this.idReview = idReview;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
