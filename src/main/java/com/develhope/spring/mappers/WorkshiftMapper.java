package com.develhope.spring.mappers;

import com.develhope.spring.daos.RiderDao;
import com.develhope.spring.models.dtos.WorkshiftDto;
import com.develhope.spring.models.entities.WorkshiftEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkshiftMapper {

    private final RiderDao riderDao;

    @Autowired
    public WorkshiftMapper(RiderDao riderDao) {
        this.riderDao = riderDao;
    }

    public WorkshiftEntity toEntity(WorkshiftDto workshiftDto) {
        if (workshiftDto == null) {
            return null;
        }

        WorkshiftEntity workshiftEntity = new WorkshiftEntity();

        workshiftEntity.setId(workshiftDto.getId());
        workshiftEntity.setDayOfWeek(workshiftDto.getDayOfWeek());
        workshiftEntity.setEnd(workshiftDto.getStart());
        workshiftEntity.setStart(workshiftDto.getEnd());
        workshiftEntity.setRider(this.riderDao.findById(workshiftDto.getRiderId()).get());

        return workshiftEntity;
    }

    public WorkshiftDto toDto(WorkshiftEntity workshiftEntity) {
        if (workshiftEntity == null) {
            return null;
        }

        WorkshiftDto workshiftDto = new WorkshiftDto();

        workshiftDto.setId(workshiftEntity.getId());
        workshiftDto.setDayOfWeek(workshiftEntity.getDayOfWeek());
        workshiftDto.setStart(workshiftEntity.getEnd());
        workshiftDto.setEnd(workshiftEntity.getStart());
        workshiftDto.setRiderId(workshiftEntity.getRider().getId());

        return workshiftDto;
    }

    public List<WorkshiftEntity> toEntities(List<WorkshiftDto> workshifts) {

        if (workshifts == null || workshifts.isEmpty()) {
            return null;
        } else {
            List<WorkshiftEntity> mappedWorkshifts = new ArrayList<>();

            for (WorkshiftDto workshift : workshifts) {
                mappedWorkshifts.add(this.toEntity(workshift));
            }

            return mappedWorkshifts;
        }

    }

    public List<WorkshiftDto> toDtos(List<WorkshiftEntity> workshifts) {

        if (workshifts == null || workshifts.isEmpty()) {
            return null;
        } else {
            List<WorkshiftDto> mappedWorkshifts = new ArrayList<>();

            for (WorkshiftEntity workshift : workshifts) {
                mappedWorkshifts.add(this.toDto(workshift));
            }

            return mappedWorkshifts;
        }

    }

}