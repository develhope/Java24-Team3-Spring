package com.develhope.spring.mappers;

import com.develhope.spring.daos.CustomerDao;
import com.develhope.spring.daos.OrderDao;
import com.develhope.spring.daos.RestaurantDao;
import com.develhope.spring.models.dtos.ReviewDto;
import com.develhope.spring.models.entities.ReviewEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewMapper {

    private final OrderDao orderDao;
    private final RestaurantDao restaurantDao;
    private final CustomerDao customerDao;

    @Autowired
    public ReviewMapper(OrderDao orderDao, RestaurantDao restaurantDao, CustomerDao customerDao) {
        this.orderDao = orderDao;
        this.restaurantDao = restaurantDao;
        this.customerDao = customerDao;
    }

    public ReviewEntity toEntity(ReviewDto reviewDto) {
        if (reviewDto == null) {
            return null;
        }

        ReviewEntity reviewEntity = new ReviewEntity();

        reviewEntity.setId(reviewDto.getId());
        reviewEntity.setOrder(orderDao.findById(reviewDto.getOrderId()).get());
        reviewEntity.setCreationDate(reviewDto.getCreationDate());
        reviewEntity.setRating(reviewDto.getRating());
        reviewEntity.setRestaurant(restaurantDao.findById(reviewDto.getRestaurantId()).get());
        reviewEntity.setCustomer(customerDao.findById(reviewDto.getCustomerId()).get());

        if (reviewDto.getComment() != null || !reviewDto.getComment().isEmpty()) {
            reviewEntity.setComment(reviewDto.getComment());
        }

        return reviewEntity;
    }

    public ReviewDto toDto(ReviewEntity reviewEntity){
        if (reviewEntity == null) {
            return null;
        }

        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(reviewEntity.getId());
        reviewDto.setOrderId(reviewEntity.getOrder().getId());
        reviewDto.setCreationDate(reviewEntity.getCreationDate());
        reviewDto.setRating(reviewEntity.getRating());
        reviewDto.setRestaurantId(reviewEntity.getRestaurant().getId());
        reviewDto.setCustomerId(reviewEntity.getCustomer().getId());

        if (reviewEntity.getComment() != null || !reviewEntity.getComment().isEmpty()) {
            reviewDto.setComment(reviewEntity.getComment());
        }

        return reviewDto;
    }

    public List<ReviewEntity> toEntities(List<ReviewDto> reviews) {

        if (reviews == null || reviews.isEmpty()) {
            return null;
        } else {
            List<ReviewEntity> mappedReviews = new ArrayList<>();

            for (ReviewDto review : reviews) {
                mappedReviews.add(this.toEntity(review));
            }

            return mappedReviews;
        }

    }

    public List<ReviewDto> toDtos(List<ReviewEntity> reviews) {

        if (reviews == null || reviews.isEmpty()) {
            return null;
        } else {
            List<ReviewDto> mappedReviews = new ArrayList<>();

            for (ReviewEntity review : reviews) {
                mappedReviews.add(this.toDto(review));
            }

            return mappedReviews;
        }

    }

}
