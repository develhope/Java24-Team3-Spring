package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.OperatingHoursDto;
import com.develhope.spring.models.entities.OperatingHoursEntity;

import java.util.ArrayList;
import java.util.List;

public class OperatingHoursMapper {

        public static List<OperatingHoursDto> toDto(List<OperatingHoursEntity> operatingHoursEntities){
            if (operatingHoursEntities==null) return null;


            List<OperatingHoursDto> operatingHoursDtos = new ArrayList<>();
            for (OperatingHoursEntity o : operatingHoursEntities) {
                operatingHoursDtos.add(
                        new OperatingHoursDto()
                );
            }
            return operatingHoursDtos;
        }

    public static List<OperatingHoursEntity> toEntity(List<OperatingHoursDto> operatingHoursDtos){
        if (operatingHoursDtos==null) return null;

        List<OperatingHoursEntity> operatingHoursEntities = new ArrayList<>();
        for (OperatingHoursDto o : operatingHoursDtos) {
            operatingHoursEntities.add(
                    new OperatingHoursEntity()
            );
        }
        return operatingHoursEntities;
    }



}
