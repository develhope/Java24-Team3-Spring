package com.develhope.spring.daos;

import com.develhope.spring.models.entities.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailsDao extends JpaRepository<UserDetailsEntity, Long> {

    Optional<UserDetailsEntity> findUserDetailsByPhoneNumber(String phoneNumber);

    List<UserDetailsEntity> findUserDetailsByCreationDate(LocalDate creationDate);

}
