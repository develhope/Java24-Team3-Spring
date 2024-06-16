package com.develhope.spring.daos;

import com.develhope.spring.models.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleDao extends JpaRepository<ScheduleEntity, String> {

    Optional<ScheduleEntity> findById(String id);

    List<ScheduleEntity> findByDayOfWeek(DayOfWeek dayOfWeek);

    List<ScheduleEntity> findByStartingBefore(LocalTime hour);

    List<ScheduleEntity> findByStartingAfter(LocalTime hour);

    List<ScheduleEntity> findByStartingBetween(LocalTime minHour, LocalTime maxHour);

    List<ScheduleEntity> findByEndingBefore(LocalTime hour);

    List<ScheduleEntity> findByEndingAfter(LocalTime hour);

    List<ScheduleEntity> findByEndingBetween(LocalTime minHour, LocalTime maxHour);

}