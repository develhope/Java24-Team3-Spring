package com.develhope.spring.daos;

import com.develhope.spring.models.entities.RiderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RiderDao extends JpaRepository<RiderEntity, String> {

    List<RiderEntity> findByName(String name);

    List<RiderEntity> findBySurname(String surname);

    List<RiderEntity> findByPhoneNumber(String phoneNumber);

    Optional<RiderEntity> findByEmail(String email);

    List<RiderEntity> findByDeletedStatus(Boolean isDeleted);

    List<RiderEntity> findByVerifiedStatus(Boolean isVerified);

}