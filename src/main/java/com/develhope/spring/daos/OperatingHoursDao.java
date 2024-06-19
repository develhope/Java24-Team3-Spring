package com.develhope.spring.daos;

import com.develhope.spring.models.entities.OperatingHoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatingHoursDao extends JpaRepository<OperatingHoursEntity, String> {
}
