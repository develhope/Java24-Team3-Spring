package com.develhope.spring.services;



import com.develhope.spring.dto.AdminDTO;
import com.develhope.spring.entities.AdminEntity;
import com.develhope.spring.exceptions.AdminNotFoundException;
import com.develhope.spring.mappers.AdminMapper;
import com.develhope.spring.dao.AdminDao;
import com.develhope.spring.validators.AdminValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AdminService {

    @Autowired
    private final AdminDao adminDao;
    @Autowired
    private final AdminMapper adminMapper;
    @Autowired
    private final AdminValidator adminValidator;


    public AdminService(AdminDao adminDao, AdminMapper adminMapper, AdminValidator adminValidator) {
        this.adminDao = adminDao;
        this.adminMapper = adminMapper;
        this.adminValidator = adminValidator;
    }

    public AdminDTO createAdmin (AdminDTO adminDTO) {
        AdminEntity adminCreated = adminMapper.toEntity(adminDTO);
        this.adminDao.save(adminCreated);
        return adminMapper.toDTO(adminCreated);
    }

    public AdminDTO getAdmin (Long id) {
        Optional<AdminEntity> adminGetted = this.adminDao.findById(id);
        if (adminGetted.isEmpty()) {
            throw new AdminNotFoundException("Admin doesn't exist " + id);
        } else {
            return adminMapper.toDTO(adminGetted.get());
        }
    }

    public List<AdminDTO> getAllAdmins() {
        List<AdminDTO> allAdmins = this.adminDao.findAll().stream().map(adminMapper::toDTO).toList();
        if (allAdmins.isEmpty()) {
            throw new AdminNotFoundException("There are no admins");
        }
        return allAdmins;
    }

    public AdminDTO updateAdmin(Long id, AdminDTO adminDTO) {
        Optional<AdminEntity> adminUpdated = this.adminDao.findById(id);
        if (adminUpdated.isEmpty()) {
            throw new AdminNotFoundException("Admin doesn't exist" + id);
        } else {
            adminUpdated.get().setEmail(adminDTO.getEmail());
            adminUpdated.get().setPassword(adminDTO.getPassword());
            adminUpdated.get().setIsDeleted(adminDTO.getIsDeleted());
            adminUpdated.get().setIsVerified(adminDTO.getIsVerified());
            adminUpdated.get().setName(adminDTO.getName());
            adminUpdated.get().setSurname(adminDTO.getSurname());
            adminUpdated.get().setPhoneNumber(adminDTO.getPhoneNumber());
            return adminMapper.toDTO(this.adminDao.saveAndFlush(adminUpdated.get()));
        }
    }

    public AdminDTO updatePassword(Long id, AdminDTO adminDTO) {
        Optional<AdminEntity> passwordUpdated = this.adminDao.findById(id);
        if (passwordUpdated.isEmpty()) {
            throw new AdminNotFoundException("Admin not found" + id);
        } else {
            passwordUpdated.get().setPassword(adminDTO.getPassword());
            return adminMapper.toDTO(this.adminDao.saveAndFlush(passwordUpdated.get()));
        }
    }

    public void deleteAdmin(Long id){
        if (!adminDao.existsById(id)) {
            throw new AdminNotFoundException("Admin not found" + id);
        }
        this.adminDao.deleteById(id);
    }








}
