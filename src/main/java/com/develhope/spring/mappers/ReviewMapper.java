package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.ReviewDto;
import com.develhope.spring.models.entities.ReviewEntity;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewEntity toEntity(ReviewDto reviewDto) {
        if (reviewDto == null) {
            return null;
        }
        ReviewEntity reviewEntity = new ReviewEntity();

        reviewEntity.setId_review(reviewDto.getId_review());
        reviewEntity.setId_order(reviewDto.getId_order());
        reviewEntity.setDate(reviewDto.getDate());
        reviewEntity.setRating(reviewDto.getRating());
        reviewEntity.setComment(reviewDto.getComment());

        return reviewEntity;
    }

    public ReviewDto toDto(ReviewEntity reviewEntity){
        if (reviewEntity == null) {
            return null;
        }
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId_review(reviewEntity.getId_review());
        reviewDto.setId_order(reviewEntity.getId_order());
        reviewDto.setDate(reviewEntity.getDate());
        reviewDto.setRating(reviewEntity.getRating());
        reviewDto.setComment(reviewEntity.getComment());

        return reviewDto;
    }


}
