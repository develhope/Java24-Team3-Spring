package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.WorkshiftDto;
import com.develhope.spring.models.entities.WorkshiftEntity;
import org.springframework.stereotype.Component;

@Component
public class WorkshiftMapper {

    public WorkshiftEntity toEntity(WorkshiftDto workshiftDto) {
        if (workshiftDto == null) {
            return null;
        }

        WorkshiftEntity workshiftEntity = new WorkshiftEntity();

        workshiftEntity.setId(workshiftDto.getId());
        workshiftEntity.setDayOfWeek(workshiftDto.getDayOfWeek());
        workshiftEntity.setStart(workshiftDto.getStart());
        workshiftEntity.setEnd(workshiftDto.getEnd());

        return workshiftEntity;
    }

    public WorkshiftDto toDto(WorkshiftEntity workshiftEntity) {
        if (workshiftEntity == null) {
            return null;
        }

        WorkshiftDto workshiftDto = new WorkshiftDto();

        workshiftDto.setId(workshiftEntity.getId());
        workshiftDto.setDayOfWeek(workshiftEntity.getDayOfWeek());
        workshiftDto.setStart(workshiftEntity.getStart());
        workshiftDto.setEnd(workshiftEntity.getEnd());

        return workshiftDto;
    }

}