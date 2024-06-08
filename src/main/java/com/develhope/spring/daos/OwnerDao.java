package com.develhope.spring.daos;

import com.develhope.spring.models.entities.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerDao extends JpaRepository<OwnerEntity, String> {
}