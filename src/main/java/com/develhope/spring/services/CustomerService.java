package com.develhope.spring.services;

import com.develhope.spring.mappers.CustomerMapper;
import com.develhope.spring.models.dtos.CustomerDto;
import com.develhope.spring.models.entities.CustomerEntity;
import com.develhope.spring.exceptions.CustomerNotFoundException;
import com.develhope.spring.daos.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    private final CustomerDao customerDao;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerDao customerDao, CustomerMapper customerMapper) {
        this.customerDao = customerDao;
        this.customerMapper = customerMapper;
    }

    /**
     * @param customerDto CustomerDto
     * @return new customer
     */
    public CustomerDto addCustomer(CustomerDto customerDto) {
        CustomerEntity newCustomer = customerMapper.asEntity(customerDto);
        this.customerDao.saveAndFlush(newCustomer);
        return customerMapper.asDTO(newCustomer);
    }

    /**
     * @param id customer id
     * @return a single customer
     */
    public CustomerDto getCustomer(Long id) {
        Optional<CustomerEntity> customerFound = this.customerDao.findById(id);
        if (customerFound.isEmpty()) {
            throw new CustomerNotFoundException();
        } else {
            return customerMapper.asDTO(customerFound.get());
        }
    }

    /**
     * @return List of customers
     */
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customers = this.customerDao.findAll().stream().map(customerMapper::asDTO).toList();
        if (customers.isEmpty()) {
            return new ArrayList<>();
        }
        return customers;
    }

    //getCustomerByEmail

    //getCustomersByDeleteStatus

    //getCustomersByVerifiedStatus

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
            customerToUpdate.get().setName(customerDto.getName());
            customerToUpdate.get().setSurname(customerDto.getSurname());
            customerToUpdate.get().setBirthDate(customerDto.getBirthDate());
            customerToUpdate.get().setPhoneNumber(customerDto.getPhoneNumber());
            customerToUpdate.get().setCreationDate(customerDto.getCreationDate());
            customerToUpdate.get().setUpdateDate(customerDto.getUpdateDate());
            customerToUpdate.get().setIsDeleted(customerDto.getIsDeleted());
            customerToUpdate.get().setIsVerified(customerDto.getIsVerified());
            return customerMapper.asDTO(this.customerDao.saveAndFlush(customerToUpdate.get()));
        }
    }

    /**
     *
     * @param id customer id
     * @param customerDto CustomerDto
     * @return customer with password updated
     */
    public CustomerDto updatePassword(Long id, CustomerDto customerDto) {
        Optional<CustomerEntity> customerToUpdate = this.customerDao.findById(id);
        if (customerToUpdate.isEmpty()) {
            throw new CustomerNotFoundException();
        } else {
            customerToUpdate.get().setPassword(customerDto.getPassword());
            return customerMapper.asDTO(this.customerDao.saveAndFlush(customerToUpdate.get()));
        }
    }

    /**
     * @param id customer id
     */
    public void deleteCustomer(Long id) {
        if (!customerDao.existsById(id)) {
            throw new CustomerNotFoundException();
        }
        this.customerDao.deleteById(id);
    }

    public void deleteAllCustomers() {
        this.customerDao.deleteAll();
    }

}
