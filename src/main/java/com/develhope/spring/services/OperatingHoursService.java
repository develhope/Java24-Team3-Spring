package com.develhope.spring.services;

import com.develhope.spring.daos.OperatingHoursDao;
import com.develhope.spring.exceptions.IdException;
import com.develhope.spring.mappers.OperatingHoursMapper;
import com.develhope.spring.models.dtos.OperatingHoursDto;
import com.develhope.spring.models.entities.OperatingHoursEntity;
import com.develhope.spring.validators.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatingHoursService {

        @Autowired
        OperatingHoursMapper operatingHoursMapper;

        @Autowired
        OperatingHoursDao operatingHoursDao;

        @Autowired
        IdValidator idValidator;

        public List<OperatingHoursDto> createOperatingHours(List<OperatingHoursDto> operatingHoursDtos) throws IdException {
            for (OperatingHoursDto o : operatingHoursDtos){
                idValidator.noId(o.getId_oparatingHours());
            }
            List<OperatingHoursEntity> operatingHoursEntitySaved = operatingHoursDao.saveAll(operatingHoursMapper.toEntity(operatingHoursDtos));
            return operatingHoursMapper.toDto(operatingHoursEntitySaved);
        }

        public OperatingHoursDto updateOperatingHours(OperatingHoursDto operatingHoursDto) {
            return null;
        }

        public OperatingHoursDto deleteOperatingHoursById(OperatingHoursDto operatingHoursDto) {
            return null;

        }


    

}
