package com.develhope.spring.models.dtos;

import com.develhope.spring.models.Rating;
import jakarta.persistence.*;

import java.util.Date;

public class ReviewDto {

    private String id_review;

    private String id_order;

    private Date date;

    private Rating rating;

    private String comment;

    public ReviewDto() {
    }

    public ReviewDto(String id_review, String id_order, Date date, Rating rating, String comment) {
        this.id_review = id_review;
        this.id_order = id_order;
        this.date = date;
        this.rating = rating;
        this.comment = comment;
    }

    public String getId_review() {
        return id_review;
    }

    public void setId_review(String id_review) {
        this.id_review = id_review;
    }

    public String getId_order() {
        return id_order;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
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
}
