package com.develhope.spring.services;

import com.develhope.spring.dtos.CustomerDTO;
import com.develhope.spring.mappers.CustomerMapper;
import com.develhope.spring.entities.CustomerEntity;
import com.develhope.spring.exceptions.CustomerNotFoundException;
import com.develhope.spring.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    /**
     * @param customerDTO CustomerDTO
     * @return new customer
     */
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        CustomerEntity newCustomer = customerMapper.asEntity(customerDTO);
        this.customerRepository.save(newCustomer);
        return customerMapper.asDTO(newCustomer);
    }

    /**
     * @param id customer id
     * @return a single customer
     */
    public CustomerDTO getCustomer(Long id) {
        Optional<CustomerEntity> customerFound = this.customerRepository.findById(id);
        if (customerFound.isEmpty()) {
            throw new CustomerNotFoundException();
        } else {
            return customerMapper.asDTO(customerFound.get());
        }
    }

    /**
     * @return List of customers
     */
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customers = this.customerRepository.findAll().stream().map(customerMapper::asDTO).toList();
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException();
        }
        return customers;
    }

    /**
     * @param id          customer id
     * @param customerDTO CustomerDTO
     * @return a customer updated
     */
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Optional<CustomerEntity> customerToUpdate = this.customerRepository.findById(id);
        if (customerToUpdate.isEmpty()) {
            throw new CustomerNotFoundException();
        } else {
            customerToUpdate.get().setEmail(customerDTO.getEmail());
            customerToUpdate.get().setPassword(customerDTO.getPassword());
            customerToUpdate.get().setName(customerDTO.getName());
            customerToUpdate.get().setSurname(customerDTO.getSurname());
            customerToUpdate.get().setPhoneNumber(customerDTO.getPhoneNumber());
            return customerMapper.asDTO(this.customerRepository.saveAndFlush(customerToUpdate.get()));
        }
    }

    /**
     *
     * @param id customer id
     * @param newPassword
     * @return a customer with password updated
     */
    public CustomerDTO updatePassword(Long id, String newPassword) {
        Optional<CustomerEntity> customerToUpdate = this.customerRepository.findById(id);
        if (customerToUpdate.isEmpty()) {
            throw new CustomerNotFoundException();
        } else {
            customerToUpdate.get().setPassword(newPassword);
            return customerMapper.asDTO(this.customerRepository.saveAndFlush(customerToUpdate.get()));
        }
    }

    /**
     * @param id customer id
     */
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException();
        }
        this.customerRepository.deleteById(id);
    }

    public void deleteAllCustomers() {
        this.customerRepository.deleteAll();
    }

}
