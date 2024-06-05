package com.develhope.spring.dao;
import com.develhope.spring.entities.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminDao extends JpaRepository<AdminEntity, Long> {
}
