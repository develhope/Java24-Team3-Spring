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


    List<ScheduleEntity> findByDayOfWeek(DayOfWeek dayOfWeek);

    List<ScheduleEntity> findByStartBefore(LocalTime hour);

    List<ScheduleEntity> findByStartAfter(LocalTime hour);

    List<ScheduleEntity> findByStartBetween(LocalTime minHour, LocalTime maxHour);

    List<ScheduleEntity> findByEndBefore(LocalTime hour);

    List<ScheduleEntity> findByEndAfter(LocalTime hour);

    List<ScheduleEntity> findByEndBetween(LocalTime minHour, LocalTime maxHour);

}