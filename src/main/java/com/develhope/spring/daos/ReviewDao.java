package com.develhope.spring.daos;

import com.develhope.spring.models.Rating;
import com.develhope.spring.models.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewDao extends JpaRepository<ReviewEntity, String> {

    Optional<ReviewEntity> findByOrderId(String orderId);

    List<ReviewEntity> findByRestaurantId(String restaurantId);

    List<ReviewEntity> findByCustomerId(String customerId);

    List<ReviewEntity> findByCreationDateAfter(LocalDateTime dateTime);

    List<ReviewEntity> findByCreationDateBefore(LocalDateTime dateTime);

    List<ReviewEntity> findByCreationDateBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<ReviewEntity> findByRating(Rating rating);

    List<ReviewEntity> findByRatingLessThanEqual(Rating rating);

    List<ReviewEntity> findByRatingGreaterThanEqual(Rating rating);

    List<ReviewEntity> findByRatingBetween(Rating minRating, Rating maxRating);

}