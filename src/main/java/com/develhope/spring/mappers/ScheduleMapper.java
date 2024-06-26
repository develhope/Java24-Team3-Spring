package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.ScheduleDto;
import com.develhope.spring.models.entities.ScheduleEntity;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {

    public ScheduleEntity toEntity(ScheduleDto scheduleDto) {
        if (scheduleDto == null) {
            return null;
        }

        ScheduleEntity scheduleEntity = new ScheduleEntity();

        scheduleEntity.setId(scheduleDto.getId());
        scheduleEntity.setDayOfWeek(scheduleDto.getDayOfWeek());
        scheduleEntity.setStart(scheduleDto.getStart());
        scheduleEntity.setEnd(scheduleDto.getEnd());

        return scheduleEntity;
    }

    public ScheduleDto toDto(ScheduleEntity scheduleEntity) {
        if (scheduleEntity == null) {
            return null;
        }

        ScheduleDto scheduleDto = new ScheduleDto();

        scheduleDto.setId(scheduleEntity.getId());
        scheduleDto.setDayOfWeek(scheduleEntity.getDayOfWeek());
        scheduleDto.setStart(scheduleEntity.getStart());
        scheduleDto.setEnd(scheduleEntity.getEnd());

        return scheduleDto;
    }

}