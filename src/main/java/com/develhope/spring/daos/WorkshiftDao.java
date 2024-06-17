package com.develhope.spring.daos;

import com.develhope.spring.models.entities.WorkshiftEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkshiftDao extends JpaRepository<WorkshiftEntity, String> {

    Optional<WorkshiftEntity> findById(String id);

    List<WorkshiftEntity> findByDayOfWeek(DayOfWeek dayOfWeek);

    List<WorkshiftEntity> findByStartingBefore(LocalTime hour);

    List<WorkshiftEntity> findByStartingAfter(LocalTime hour);

    List<WorkshiftEntity> findByStartingBetween(LocalTime minHour, LocalTime maxHour);

    List<WorkshiftEntity> findByEndingBefore(LocalTime hour);

    List<WorkshiftEntity> findByEndingAfter(LocalTime hour);

    List<WorkshiftEntity> findByEndingBetween(LocalTime minHour, LocalTime maxHour);

}