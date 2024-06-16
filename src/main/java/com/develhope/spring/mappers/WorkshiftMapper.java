package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.WorkshiftDto;
import com.develhope.spring.models.entities.WorkshiftEntity;

public class WorkshiftMapper {

    public WorkshiftEntity toEntity(WorkshiftDto workshiftDto) {
        if (workshiftDto == null) {
            return null;
        }

        WorkshiftEntity workshiftEntity = new WorkshiftEntity();

        workshiftEntity.setId(workshiftDto.getId());
        workshiftEntity.setDayOfWeek(workshiftDto.getDayOfWeek());
        workshiftEntity.setStartingHour(workshiftDto.getStartingHour());
        workshiftEntity.setEndingHour(workshiftDto.getEndingHour());

        return workshiftEntity;
    }

    public WorkshiftDto toDto(WorkshiftEntity workshiftEntity) {
        if (workshiftEntity == null) {
            return null;
        }

        WorkshiftDto workshiftDto = new WorkshiftDto();

        workshiftDto.setId(workshiftEntity.getId());
        workshiftDto.setDayOfWeek(workshiftEntity.getDayOfWeek());
        workshiftDto.setStartingHour(workshiftEntity.getStartingHour());
        workshiftDto.setEndingHour(workshiftEntity.getEndingHour());

        return workshiftDto;
    }

}