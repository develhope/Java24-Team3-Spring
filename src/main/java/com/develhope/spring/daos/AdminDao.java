package com.develhope.spring.daos;
import com.develhope.spring.models.entities.AdminEntity;
import com.develhope.spring.models.entities.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminDao extends JpaRepository<AdminEntity, String> {

    Optional<AdminEntity> findAdminByEmail(String email);

}