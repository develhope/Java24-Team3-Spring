package com.develhope.spring.daos;

import com.develhope.spring.models.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDao extends JpaRepository<ReviewEntity, String> {
}
