package com.develhope.spring.models.entities;

import com.develhope.spring.models.Rating;
import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "review")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_review;
    private String id_order;
    @Column
    private Date date;
    @Column
    @Enumerated(EnumType.ORDINAL)
    private Rating rating;
    @Column
    private String comment;

    public ReviewEntity() {
    }

    public ReviewEntity(String id_review, String id_order, Date date, Rating rating, String comment) {
        this.id_review = id_review;
        this.date = date;
        this.rating = rating;
        this.comment = comment;
        this.id_order= id_order;
    }

    public String getId_review() {
        return id_review;
    }

    public void setId_review(String id_review) {
        this.id_review = id_review;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getId_order() {
        return id_order;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }
}
