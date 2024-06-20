package com.develhope.spring.services;



import com.develhope.spring.mappers.UserDetailsMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.AdminDto;
import com.develhope.spring.models.entities.AdminEntity;
import com.develhope.spring.exceptions.AdminNotFoundException;
import com.develhope.spring.mappers.AdminMapper;
import com.develhope.spring.daos.AdminDao;
import com.develhope.spring.validators.AdminValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AdminService {


    private final AdminDao adminDao;

    private final AdminMapper adminMapper;

    private final AdminValidator adminValidator;


    private UserDetailsMapper userDetailsMapper;

    @Autowired
    public AdminService(AdminDao adminDao, AdminMapper adminMapper, AdminValidator adminValidator, UserDetailsMapper userDetailsMapper) {
        this.adminDao = adminDao;
        this.adminMapper = adminMapper;
        this.adminValidator = adminValidator;
        this.userDetailsMapper = userDetailsMapper;
    }

    public ResponseModel createAdmin (AdminDto adminDTO) {
        try {
            AdminEntity adminCreated = adminMapper.toEntity(adminDTO);
            AdminEntity adminEntitySaved = adminDao.save(adminCreated);
            AdminDto adminDtoSaved = adminMapper.toDto(adminEntitySaved);
            return new ResponseModel(ResponseCode.B, adminDtoSaved);
        } catch (AdminNotFoundException e) {
            return new ResponseModel(ResponseCode.A).addMessageDetails(e.getMessage());
        }
    }

    public ResponseModel getAdmin (String id) {
        Optional<AdminEntity> adminGetted = adminDao.findById(id);
        if (adminGetted.isPresent()) {
            AdminEntity adminEntityFound = adminGetted.get();
            AdminDto adminDtoFound = adminMapper.toDto(adminEntityFound);
            return new ResponseModel(ResponseCode.C, adminDtoFound);
        } else {
            return new ResponseModel(ResponseCode.D);
        }
    }

    public ResponseModel getAllAdmins() {
        List<AdminDto> allAdmins = this.adminDao.findAll().stream().map(adminMapper::toDto).toList();
        if (allAdmins.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No admins found");
        } else {
            return new ResponseModel(ResponseCode.E, allAdmins);
        }
    }

    public ResponseModel updateAdmin(String id, AdminDto adminDTO) {
        Optional<AdminEntity> adminUpdated = this.adminDao.findById(id);
        if (adminUpdated.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Admin with specified id not found");
        } else if (adminDTO != null) {
            if (adminDTO.getEmail() != null) {
                adminUpdated.get().setEmail(adminDTO.getEmail());
            }
            if (adminDTO.getPassword() != null) {
                adminUpdated.get().setPassword(adminDTO.getPassword());
            }
            if (adminDTO.getIsDeleted() != null) {
                adminUpdated.get().setIsDeleted(adminDTO.getIsDeleted());
            }
            if (adminDTO.getIsVerified() != null) {
                adminUpdated.get().setIsVerified(adminDTO.getIsVerified());
            }
            if (adminDTO.getUserDetails() != null) {
                adminUpdated.get().setUserDetailsEntity(userDetailsMapper.toEntity(adminDTO.getUserDetails()));
            }

            return new ResponseModel(ResponseCode.G, this.adminMapper.toDto(this.adminDao.saveAndFlush(adminUpdated.get())));
        }
        return new ResponseModel(ResponseCode.A).addMessageDetails("Impossible to update, body should not be null");
    }

    public ResponseModel updatePassword(String id, AdminDto adminDTO) {
        Optional<AdminEntity> passwordUpdated = this.adminDao.findById(id);
        if (passwordUpdated.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Admin with the specified ID not found");
        } else if (adminDTO != null) {
            if (adminDTO.getPassword() != null) {
                passwordUpdated.get().setPassword(adminDTO.getPassword());
                return new ResponseModel(ResponseCode.G, adminMapper.toDto(this.adminDao.saveAndFlush(passwordUpdated.get())));
            }
        }
        return new ResponseModel(ResponseCode.A).addMessageDetails("Impossible to update, the body should not be null");
    }

    public ResponseModel deleteAdmin(String id) {
        if (!adminDao.existsById(id)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Admin with specified id not found");
        } else {
            this.adminDao.deleteById(id);
            return new ResponseModel(ResponseCode.H).addMessageDetails("Admin deleted");
        }
    }

    public ResponseModel deleteAllAdmins(){
        this.adminDao.deleteAll();
        return new ResponseModel(ResponseCode.H).addMessageDetails("All admins deleted");
    }








}
