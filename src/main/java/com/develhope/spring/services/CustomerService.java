package com.develhope.spring.services;

import com.develhope.spring.exceptions.InvalidPasswordException;
import com.develhope.spring.mappers.CustomerMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.CustomerDto;
import com.develhope.spring.models.entities.CustomerEntity;
import com.develhope.spring.exceptions.CustomerNotFoundException;
import com.develhope.spring.daos.CustomerDao;
import com.develhope.spring.validators.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    private final CustomerDao customerDao;
    private final CustomerMapper customerMapper;
    private final CustomerValidator customerValidator;

    @Autowired
    public CustomerService(CustomerDao customerDao, CustomerMapper customerMapper, CustomerValidator customerValidator) {
        this.customerDao = customerDao;
        this.customerMapper = customerMapper;
        this.customerValidator = customerValidator;
    }

//    /**
//     * @param customerDto CustomerDto
//     * @return new customer
//     */
//    public CustomerDto addCustomer(CustomerDto customerDto) {
//        CustomerEntity newCustomer = this.customerMapper.toEntity(customerDto);
//        this.customerDao.saveAndFlush(newCustomer);
//        return this.customerMapper.toDTO(newCustomer);
//    }

    public ResponseModel addCustomer(CustomerDto customerDto) throws InvalidPasswordException {

        try {
            customerValidator.validateCustomerPassword(customerDto.getPassword());
            CustomerEntity newCustomer = this.customerMapper.toEntity(customerDto);
            this.customerDao.saveAndFlush(newCustomer);
           return new ResponseModel(ResponseCode.B, ResponseCode.B.getResponseType().toString() + ": "
                    + ResponseCode.B.getResponseType().getMessage() + "Details: "
                    + ResponseCode.B.getResponseCodeMessage(), this.customerMapper.toDTO(newCustomer));
        } catch (InvalidPasswordException e) {
            return new ResponseModel(ResponseCode.A, e.getMessage());
        }

    }

    /**
     * @param id customer id
     * @return a single customer
     */
    public CustomerDto getCustomerById(Long id) {
        Optional<CustomerEntity> customerFound = this.customerDao.findById(id);
        if (customerFound.isEmpty()) {
            throw new CustomerNotFoundException();
        } else {
            return this.customerMapper.toDTO(customerFound.get());
        }
    }

    /**
     * @return List of all customers
     */
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customers = this.customerDao.findAll().stream().map(customerMapper::toDTO).toList();
        if (customers.isEmpty()) {
            return new ArrayList<>();
        } else {
            return customers;
        }
    }

    /**
     * @param email String
     * @return a single customer
     */
    public CustomerDto getCustomerByEmail(String email) {
        Optional<CustomerEntity> customerFound = this.customerDao.findCustomerByEmail(email);
        if (customerFound.isEmpty()) {
            throw new CustomerNotFoundException();
        } else {
            return this.customerMapper.toDTO(customerFound.get());
        }
    }

    /**
     * @param isDeleted Boolean
     * @return all customers with the selected deleted status
     */
    public List<CustomerDto> getCustomerByDeletedStatus(Boolean isDeleted) {
        List<CustomerDto> customers = this.customerDao.findCustomerByIsDeleted(isDeleted).stream().map(customerMapper::toDTO).toList();
        if (customers.isEmpty()) {
            return new ArrayList<>();
        } else {
            return customers;
        }
    }

    /**
     * @param isVerified Boolean
     * @return all customers with the selected verified status
     */
    public List<CustomerDto> getCustomersByVerifiedStatus(Boolean isVerified) {
        List<CustomerDto> customers = this.customerDao.findCustomerByIsVerified(isVerified).stream().map(customerMapper::toDTO).toList();
        if (customers.isEmpty()) {
            return new ArrayList<>();
        } else {
            return customers;
        }
    }

    /**
     * @param id          customer id
     * @param customerDto CustomerDto
     * @return a customer updated
     */
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Optional<CustomerEntity> customerToUpdate = this.customerDao.findById(id);
        if (customerToUpdate.isEmpty()) {
            throw new CustomerNotFoundException();
        } else {
            customerToUpdate.get().setEmail(customerDto.getEmail());
            customerToUpdate.get().setPassword(customerDto.getPassword());
            customerToUpdate.get().setIsDeleted(customerDto.getIsDeleted());
            customerToUpdate.get().setIsVerified(customerDto.getIsVerified());
            customerToUpdate.get().setUserDetailsEntity(customerDto.getUserDetails());

            return this.customerMapper.toDTO(this.customerDao.saveAndFlush(customerToUpdate.get()));
        }
    }

    /**
     * @param id          customer id
     * @param customerDto CustomerDto
     * @return customer with password updated
     */
    public CustomerDto updatePassword(Long id, CustomerDto customerDto) {
        Optional<CustomerEntity> customerToUpdate = this.customerDao.findById(id);
        if (customerToUpdate.isEmpty()) {
            throw new CustomerNotFoundException();
        } else {
            customerToUpdate.get().setPassword(customerDto.getPassword());
            return customerMapper.toDTO(this.customerDao.saveAndFlush(customerToUpdate.get()));
        }
    }

    /**
     * @param id customer id
     */
    public void deleteCustomer(Long id) {
        if (!this.customerDao.existsById(id)) {
            throw new CustomerNotFoundException();
        }
        this.customerDao.deleteById(id);
    }

    public void deleteAllCustomers() {
        this.customerDao.deleteAll();
    }

}
