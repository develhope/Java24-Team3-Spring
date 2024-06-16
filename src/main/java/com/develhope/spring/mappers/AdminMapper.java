package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.AdminDto;
import com.develhope.spring.models.entities.AdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AdminMapper {

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    public AdminEntity toEntity(AdminDto adminDTO) {
        if (adminDTO == null) {
            return null;
        }

        AdminEntity adminEntity = new AdminEntity();

        adminEntity.setId(adminDTO.getId());
        adminEntity.setEmail(adminDTO.getEmail());
        adminEntity.setPassword(adminDTO.getPassword());
        adminEntity.setUserDetailsEntity(userDetailsMapper.toEntity(adminDTO.getUserDetails()));
        adminEntity.setIsDeleted(adminDTO.getIsDeleted());
        adminEntity.setIsVerified(adminDTO.getIsVerified());

        return adminEntity;
    }

    public AdminDto toDto(AdminEntity adminEntity) {
        if (adminEntity == null) {
            return null;
        }

        AdminDto adminDto = new AdminDto();

        adminDto.setId(adminEntity.getId());
        adminDto.setEmail(adminEntity.getEmail());
        adminDto.setPassword(adminEntity.getPassword());
        adminDto.setUserDetails(userDetailsMapper.toDto(adminEntity.getUserDetails()));
        adminDto.setIsDeleted(adminEntity.getIsDeleted());
        adminDto.setIsVerified(adminEntity.getIsVerified());

        return adminDto;
    }
}
