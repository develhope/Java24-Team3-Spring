package com.develhope.spring.services;

import com.develhope.spring.daos.ReviewDao;
import com.develhope.spring.exceptions.ReviewNotFoundException;
import com.develhope.spring.mappers.ReviewMapper;
import com.develhope.spring.models.Rating;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.ReviewDto;
import com.develhope.spring.models.entities.ReviewEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.develhope.spring.models.Rating.*;

@Service
public class ReviewService {

    private final ReviewDao reviewDao;
    private final ReviewMapper reviewMapper;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public ReviewService(ReviewDao reviewDao, ReviewMapper reviewMapper, OrderService orderService, OrderMapper orderMapper) {
        this.reviewDao = reviewDao;
        this.reviewMapper = reviewMapper;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    public ResponseModel createReview(ReviewDto reviewDto) {
        OrderEntity order = orderService.getOrderEntityById(reviewDto.getId_order());
        if (order.getStatus() != OrderStatus.DELIVERED) {
            return new ResponseModel(ResponseCode.A).addMessageDetails("Review available only if status is delivered");
        }
        ReviewEntity review = reviewMapper.toEntity(reviewDto);
        reviewDao.save(review);
        OrderEntity orderWithReviewEntity = orderService.setReview(order, review);
        orderWithReviewDto = orderMapper.toDto(orderWithReviewEntity);
        return new ResponseModel(ResponseCode.B, orderWithReviewDto);
    }

    public ResponseModel getMeanRating(String restaurant_id) {
        List<OrderEntity> orderList = orderService.getOrdersByRestaurantId(restaurant_id);
        List<ReviewEntity> reviews = new ArrayList<>();
        for(OrderEntity order : orderList) {
            if (order.getReview()) != null) {
            reviews.add(order.getReview());
            }
        }
        if (reviews.isEmpty()){
            return new ResponseModel(ResponseCode.D).addMessageDetails("No reviews found for this restaurant");
        }

        double totalRating = 0;
        for (ReviewEntity review: reviews) {
            totalRating += review.getRating().getValue();
        }
        double meanRating = totalRating / reviews.size();

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("meanRating", meanRating);
        return new ResponseModel(ResponseCode.B, responseData);
    }

    /*public int getValue(Rating rating) {
        int value;
        switch (rating) {
            case ONE:
                value = 1;
                break;
            case TWO:
                value = 2;
                break;
            case THREE:
                value = 3;
                break;
            case FOUR:
                value = 4;
                break;
            case FIVE:
                value = 5;
                break;
            default:
                throw new IllegalArgumentException("Unknown rating" + rating);
        }
        return value;
    }*/

    public ResponseModel getReview(String id) {
        Optional<ReviewEntity> reviewGetted = reviewDao.findById(id);
        if (reviewGetted.isPresent()) {
            ReviewEntity reviewEntityFound = reviewGetted.get();
            ReviewDto reviewDtoFound = reviewMapper.toDto(reviewEntityFound);
            return new ResponseModel(ResponseCode.C, reviewDtoFound);
        } else {
            return new ResponseModel(ResponseCode.D);
        }
    }

    public ResponseModel getAllReview() {
        List<ReviewDto> allReview = this.reviewDao.findAll().stream().map(reviewMapper::toDto).toList();
        if (allReview.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No review found");
        } else {
            return new ResponseModel(ResponseCode.E, allReview);
        }
    }

    public ResponseModel updateReview(String id, ReviewDto reviewDto) {
        Optional<ReviewEntity> reviewUpdated = this.reviewDao.findById(id);
        if (reviewUpdated.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Review with specified id not found");
        } else if (reviewDto != null) {
            if (reviewDto.getComment() != null) {
                reviewUpdated.get().setComment(reviewDto.getComment());
            }
            if (reviewDto.getRating() != null) {
                reviewUpdated.get().setRating(reviewDto.getRating());
            }
            return new ResponseModel(ResponseCode.G, this.reviewMapper.toDto(this.reviewDao.saveAndFlush(reviewUpdated.get())));
        }
        return new ResponseModel(ResponseCode.A).addMessageDetails("Impossible to update, body should not be null");
    }

    public ResponseModel deleteReview(String id) {
        if (!reviewDao.existsById(id)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Review with specified id not found");
        } else {
            this.reviewDao.deleteById(id);
            return new ResponseModel(ResponseCode.H).addMessageDetails("Review deleted");
        }

    }

    public ResponseModel deleteAllReview() {
        this.reviewDao.deleteAll();
        return new ResponseModel(ResponseCode.H).addMessageDetails("All review deleted");
    }


}
