package com.develhope.spring.daos;

import com.develhope.spring.models.entities.WorkshiftEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface WorkshiftDao extends JpaRepository<WorkshiftEntity, String> {

    List<WorkshiftEntity> findByDayOfWeek(DayOfWeek dayOfWeek);

    List<WorkshiftEntity> findByStartBefore(LocalTime hour);

    List<WorkshiftEntity> findByStartAfter(LocalTime hour);

    List<WorkshiftEntity> findByStartBetween(LocalTime minHour, LocalTime maxHour);

    List<WorkshiftEntity> findByEndBefore(LocalTime hour);

    List<WorkshiftEntity> findByEndAfter(LocalTime hour);

    List<WorkshiftEntity> findByEndBetween(LocalTime minHour, LocalTime maxHour);

}