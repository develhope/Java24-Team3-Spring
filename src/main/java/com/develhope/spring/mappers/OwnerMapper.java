package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.OwnerDto;
import com.develhope.spring.models.entities.OwnerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {

    @Autowired
    private UserDetailsMapper userDetailsMapper;


    public OwnerEntity toEntity(OwnerDto ownerDto) {
        if (ownerDto == null) {
            return null;
        }

        OwnerEntity ownerEntity = new OwnerEntity();

        ownerEntity.setId(ownerDto.getId());
        ownerEntity.setEmail(ownerDto.getEmail());
        ownerEntity.setPassword(ownerDto.getPassword());
        ownerEntity.setIsDeleted(ownerDto.getIsDeleted());
        ownerEntity.setIsVerified(ownerDto.getIsVerified());
        ownerEntity.setUserDetailsEntity(userDetailsMapper.toEntity(ownerDto.getUserDetails()));

        return ownerEntity;
    }

    public OwnerDto toDto(OwnerEntity ownerEntity) {
        if (ownerEntity == null) {
            return null;
        }

        OwnerDto ownerDto = new OwnerDto();

        ownerDto.setId(ownerEntity.getId());
        ownerDto.setEmail(ownerEntity.getEmail());
        ownerDto.setPassword(ownerEntity.getPassword());
        ownerDto.setIsDeleted(ownerEntity.getIsDeleted());
        ownerDto.setIsVerified(ownerEntity.getIsVerified());
        ownerDto.setUserDetails(userDetailsMapper.toDto(ownerEntity.getUserDetails()));

        return ownerDto;
    }
}
