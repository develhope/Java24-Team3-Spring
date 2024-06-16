package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.RiderDto;
import com.develhope.spring.models.entities.RiderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RiderMapper {

    @Autowired
    UserDetailsMapper userDetailsMapper;

    public RiderEntity toEntity(RiderDto riderDto) {
        if (riderDto == null) {
            return null;
        }

        RiderEntity riderEntity = new RiderEntity();

        riderEntity.setId(riderDto.getId());
        riderEntity.setEmail(riderDto.getEmail());
        riderEntity.setPassword(riderDto.getPassword());
        riderEntity.setIsDeleted(riderDto.getIsDeleted());
        riderEntity.setIsVerified(riderDto.getIsVerified());
        riderEntity.setUserDetailsEntity(userDetailsMapper.toEntity(riderDto.getUserDetails()));
        riderEntity.setStartingPosition(riderDto.getCurrentPosition());
        riderEntity.setCurrentPosition(riderDto.getCurrentPosition());

        return riderEntity;
    }

    public RiderDto toDTO(RiderEntity riderEntity) {
        if (riderEntity == null) {
            return null;
        }

        RiderDto riderDto = new RiderDto();

        riderDto.setId(riderEntity.getId());
        riderDto.setEmail(riderEntity.getEmail());
        riderDto.setPassword(riderEntity.getPassword());
        riderDto.setIsDeleted(riderEntity.getIsDeleted());
        riderDto.setIsVerified(riderEntity.getIsVerified());
        riderDto.setUserDetails(userDetailsMapper.toDto(riderEntity.getUserDetails()));
        riderDto.setStartingPosition(riderEntity.getStartingPosition());
        riderDto.setCurrentPosition(riderEntity.getCurrentPosition());

        return riderDto;
    }

}