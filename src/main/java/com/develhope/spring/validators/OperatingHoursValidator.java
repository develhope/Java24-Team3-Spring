package com.develhope.spring.validators;

import com.develhope.spring.models.dtos.OperatingHoursDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OperatingHoursValidator {

    @Autowired
    IdValidator idValidator;

    public void validateOperatingHours(List<OperatingHoursDto> operatingHoursDtos) throws Exception {
        for (OperatingHoursDto o:operatingHoursDtos){
            idValidator.noId(o.getId_oparatingHours());
            if (o.getDayOfWeek() == null){throw new Exception("Day of the week can't be null.");}
            if (o.getOpeningHour() == null || o.getClosingHour() == null){throw new Exception("Hours can't be null.");}
            if (!o.getOpeningHour().isBefore(o.getClosingHour().minusHours(1))){throw new Exception("Opening Hour must be at least 1 hour before Closing Hour.");}

        }
    }
}
