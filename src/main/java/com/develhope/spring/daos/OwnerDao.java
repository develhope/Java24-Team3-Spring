package com.develhope.spring.daos;

import com.develhope.spring.models.entities.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerDao extends JpaRepository<OwnerEntity, String> {

//    @Modifying
//    @Query("UPDATE OwnerEntity o SET o.deleted = true WHERE o.id = :id")
//    int delete(@Param("id") String id);

    Optional<OwnerEntity> findOwnerByEmail(String email);

    List<OwnerEntity> findOwnerByIsDeleted(Boolean isDeleted);

    List<OwnerEntity> findOwnerByIsVerified(Boolean isVerified);

}