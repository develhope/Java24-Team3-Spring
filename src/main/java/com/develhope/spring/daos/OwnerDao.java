package com.develhope.spring.daos;

import com.develhope.spring.models.entities.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerDao extends JpaRepository<OwnerEntity, String> {
    Optional<OwnerEntity> findOwnerByEmail(String email);

    List<OwnerEntity> findOwnerByIsDeleted(Boolean isDeleted);

    List<OwnerEntity> findOwnerByIsVerified(Boolean isVerified);

}