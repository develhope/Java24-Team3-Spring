package com.develhope.spring.services;

import com.develhope.spring.daos.OrderDao;
import com.develhope.spring.daos.ReviewDao;
import com.develhope.spring.exceptions.ReviewNotFoundException;
import com.develhope.spring.mappers.OrderMapper;
import com.develhope.spring.mappers.ReviewMapper;
import com.develhope.spring.models.Rating;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.ReviewDto;
import com.develhope.spring.models.entities.OrderEntity;
import com.develhope.spring.models.entities.ReviewEntity;
import com.develhope.spring.utils.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReviewService {

    private final ReviewDao reviewDao;
    private final ReviewMapper reviewMapper;
    private final OrderMapper orderMapper;
    private final OrderDao orderDao;

    @Autowired
    public ReviewService(ReviewDao reviewDao,
                         ReviewMapper reviewMapper,
                         OrderMapper orderMapper,
                         OrderDao orderDao) {
        this.reviewDao = reviewDao;
        this.reviewMapper = reviewMapper;
        this.orderMapper = orderMapper;
        this.orderDao = orderDao;
    }

    // CREATE

    public ResponseModel create(ReviewDto reviewDto) {
        try {
            Optional<OrderEntity> order = orderDao.findById(reviewDto.getOrderId());
            if (order.get().getStatus() != OrderStatus.DELIVERED) {
                return new ResponseModel(ResponseCode.A).addMessageDetails("review available only if status is delivered.");
            } else {
                ReviewEntity newReview = reviewMapper.toEntity(reviewDto);
                this.reviewDao.save(newReview);
                order.get().setReview(newReview);
                orderDao.save(order.get());
                return new ResponseModel(ResponseCode.B, orderMapper.toDto(order.get()));
            }
        } catch (ReviewNotFoundException r) {
            return new ResponseModel(ResponseCode.A).addMessageDetails(r.getMessage());
        }
    }

    // READ

    public ResponseModel getAll() {
        List<ReviewDto> reviews = this.reviewDao.findAll().stream().map(reviewMapper::toDto).toList();
        if (reviews.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("no review found");
        } else {
            return new ResponseModel(ResponseCode.E, reviews);
        }
    }

    public ResponseModel getById(String id) {
        Optional<ReviewEntity> review = reviewDao.findById(id);
        if (review.isPresent()) {
            ReviewEntity reviewEntityFound = review.get();
            ReviewDto reviewDtoFound = reviewMapper.toDto(reviewEntityFound);
            return new ResponseModel(ResponseCode.C, reviewDtoFound);
        } else {
            return new ResponseModel(ResponseCode.D);
        }
    }

    public ResponseModel getByOrderId(String orderId) {
        Optional<ReviewEntity> orderFound = this.reviewDao.findByOrderId(orderId);
        if (orderFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("no reviews associated to this order found.");
        } else {
            return new ResponseModel(ResponseCode.C, this.reviewMapper.toDto(orderFound.get()));
        }
    }

    public ResponseModel getByCustomerId(String customerId) {
        List<ReviewEntity> orders = this.reviewDao.findByCustomerId(customerId);
        if (orders.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("no reviews created by this customer found.");
        } else {
            return new ResponseModel(ResponseCode.E, orders);
        }
    }

    public ResponseModel getByRestaurantId(String restaurantId) {
        List<ReviewEntity> orders = this.reviewDao.findByRestaurantId(restaurantId);
        if (orders.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("o reviews associated to this restaurant found.");
        } else {
            return new ResponseModel(ResponseCode.E, orders);
        }
    }

    public ResponseModel getCreatedAfter(LocalDateTime dateTime) {
        List<ReviewEntity> orders = this.reviewDao.findByCreationDateAfter(dateTime);
        if (orders.isEmpty()) {
            String messageDetails = "no reviews created after " + dateTime.toString() + " found.";
            return new ResponseModel(ResponseCode.D).addMessageDetails(messageDetails);
        } else {
            return new ResponseModel(ResponseCode.E, orders);
        }
    }

    public ResponseModel getCreatedBefore(LocalDateTime dateTime) {
        List<ReviewEntity> orders = this.reviewDao.findByCreationDateBefore(dateTime);
        if (orders.isEmpty()) {
            String messageDetails = "no orders created before " + dateTime.toString() + " found.";
            return new ResponseModel(ResponseCode.D).addMessageDetails(messageDetails);
        } else {
            return new ResponseModel(ResponseCode.E, orders);
        }
    }

    public ResponseModel getCreatedBetween(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        List<ReviewEntity> orders = this.reviewDao.findByCreationDateBetween(dateTime1, dateTime2);
        if (orders.isEmpty()) {
            String messageDetails = "no orders created between " + dateTime1.toString() + " and " + dateTime2.toString() + " found.";
            return new ResponseModel(ResponseCode.D).addMessageDetails(messageDetails);
        } else {
            return new ResponseModel(ResponseCode.E, orders);
        }
    }

    public ResponseModel getByRating(Rating rating) {
        List<ReviewEntity> orders = this.reviewDao.findByRating(rating);
        if (orders.isEmpty()) {
            String messageDetails = "no reviews with rating " + rating.toString() + " found.";
            return new ResponseModel(ResponseCode.D).addMessageDetails(messageDetails);
        } else {
            return new ResponseModel(ResponseCode.E, orders);
        }
    }

    public ResponseModel getByRatingLessThanEqual(Rating rating) {
        List<ReviewEntity> orders = this.reviewDao.findByRatingLessThanEqual(rating);
        if (orders.isEmpty()) {
            String messageDetails = "no reviews with rating " + rating.toString() + " or less found.";
            return new ResponseModel(ResponseCode.D).addMessageDetails(messageDetails);
        } else {
            return new ResponseModel(ResponseCode.E, orders);
        }
    }

    public ResponseModel getByRatingGreaterThanEqual(Rating rating) {
        List<ReviewEntity> orders = this.reviewDao.findByRatingGreaterThanEqual(rating);
        if (orders.isEmpty()) {
            String messageDetails = "no orders with rating " + rating.toString() + " or higher found.";
            return new ResponseModel(ResponseCode.D).addMessageDetails(messageDetails);
        } else {
            return new ResponseModel(ResponseCode.E, orders);
        }
    }

    public ResponseModel getByRatingBetween(Rating minRating, Rating maxRating) {
        List<ReviewEntity> orders = this.reviewDao.findByRatingBetween(minRating, maxRating);
        if (orders.isEmpty()) {
            String messageDetails = "no orders with rating between " + minRating.toString() + " and " + maxRating.toString() + " found.";
            return new ResponseModel(ResponseCode.D).addMessageDetails(messageDetails);
        } else {
            return new ResponseModel(ResponseCode.E, orders);
        }
    }

    public ResponseModel getMeanRating(String restaurantId) {
        List<ReviewEntity> restaurantReviews = reviewDao.findByRestaurantId(restaurantId);

        if (restaurantReviews.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("no reviews found for this restaurant");
        }

        double totalRating = 0;

        for (ReviewEntity review : restaurantReviews) {
            totalRating += review.getRating().getValue();
        }

        double meanRating = totalRating / restaurantReviews.size();

        /*
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("meanRating", meanRating);
        */

        return new ResponseModel(ResponseCode.B, meanRating);
    }

    // UPDATE

    public ResponseModel updateReview(String id, ReviewDto reviewDto) {
        Optional<ReviewEntity> reviewUpdated = this.reviewDao.findById(id);
        if (reviewUpdated.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("review with specified id not found.");
        } else if (reviewDto != null) {
            if (reviewDto.getComment() != null) {
                reviewUpdated.get().setComment(reviewDto.getComment());
            }
            if (reviewDto.getRating() != null) {
                reviewUpdated.get().setRating(reviewDto.getRating());
            }

            return new ResponseModel(ResponseCode.G, this.reviewMapper.toDto(this.reviewDao.saveAndFlush(reviewUpdated.get())));
        }

        return new ResponseModel(ResponseCode.A).addMessageDetails("impossible to update, body should not be null.");
    }

    // DELETE

    public ResponseModel deleteById(String id) {
        if (!reviewDao.existsById(id)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("review not found.");
        } else {
            reviewDao.findById(id).get().getOrder().setReview(null);
            this.reviewDao.deleteById(id);
            return new ResponseModel(ResponseCode.H).addMessageDetails("review successfully deleted.");
        }

    }

    public ResponseModel deleteAll() {
        List<ReviewEntity> reviews = this.reviewDao.findAll();

        for(ReviewEntity review : reviews) {
            review.getOrder().setReview(null);
        }

        this.reviewDao.deleteAll();
        return new ResponseModel(ResponseCode.H).addMessageDetails("all reviews have been successfully deleted.");
    }

}