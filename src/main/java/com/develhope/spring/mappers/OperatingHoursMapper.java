package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.OperatingHoursDto;
import com.develhope.spring.models.entities.OperatingHoursEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OperatingHoursMapper {

    public List<OperatingHoursDto> toDto(List<OperatingHoursEntity> operatingHoursEntities) {
        if (operatingHoursEntities == null) return null;


        List<OperatingHoursDto> operatingHoursDtos = new ArrayList<>();
        for (OperatingHoursEntity o : operatingHoursEntities) {
            operatingHoursDtos.add(
                    new OperatingHoursDto()
            );
        }
        return operatingHoursDtos;
    }

    public List<OperatingHoursEntity> toEntity(List<OperatingHoursDto> operatingHoursDtos) {
        if (operatingHoursDtos == null) return null;

        List<OperatingHoursEntity> operatingHoursEntities = new ArrayList<>();
        for (OperatingHoursDto o : operatingHoursDtos) {
            operatingHoursEntities.add(
                    new OperatingHoursEntity()
            );
        }
        return operatingHoursEntities;
    }


}
