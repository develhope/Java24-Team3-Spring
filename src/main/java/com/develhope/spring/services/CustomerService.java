package com.develhope.spring.services;

import com.develhope.spring.exceptions.InvalidCustomerException;
import com.develhope.spring.mappers.CustomerMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.CustomerDto;
import com.develhope.spring.models.entities.CustomerEntity;
import com.develhope.spring.daos.CustomerDao;
import com.develhope.spring.validators.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * @param customerDto CustomerDto
     * @return a new Customer
     */
    public ResponseModel addCustomer(CustomerDto customerDto) {

        try {
            customerValidator.validateCustomer(customerDto);
            CustomerEntity newCustomer = this.customerMapper.toEntity(customerDto);
            this.customerDao.saveAndFlush(newCustomer);
            return new ResponseModel(ResponseCode.B, ResponseCode.B.getResponseType().toString() + ": "
                    + ResponseCode.B.getResponseType().getMessage() + " Details: "
                    + ResponseCode.B.getResponseCodeMessage(), this.customerMapper.toDTO(newCustomer));
        } catch (InvalidCustomerException e) {
            return new ResponseModel(ResponseCode.A, ResponseCode.A.getResponseType().toString() + ": "
                    + ResponseCode.A.getResponseType().getMessage() + " Details: "
                    + ResponseCode.A.getResponseCodeMessage(), e.getMessage());
        }

    }

    /**
     * @param id customer id
     * @return a single customer
     */
    public ResponseModel getCustomerById(Long id) {
        Optional<CustomerEntity> customerFound = this.customerDao.findById(id);
        if (customerFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D, ResponseCode.D.getResponseType().toString() + ": "
                    + ResponseCode.D.getResponseType().getMessage() + " Details: "
                    + ResponseCode.D.getResponseCodeMessage(), "Customer not found with the selected ID");
        } else {
            return new ResponseModel(ResponseCode.C, ResponseCode.C.getResponseType().toString() + ": "
                    + ResponseCode.C.getResponseType().getMessage() + " Details: "
                    + ResponseCode.C.getResponseCodeMessage(), this.customerMapper.toDTO(customerFound.get()));
        }
    }

    /**
     * @return List of all customers
     */
    public ResponseModel getAllCustomers() {
        List<CustomerDto> customers = this.customerDao.findAll().stream().map(customerMapper::toDTO).toList();
        if (customers.isEmpty()) {
            return new ResponseModel(ResponseCode.D, ResponseCode.D.getResponseType().toString() + ": "
                    + ResponseCode.D.getResponseType().getMessage() + " Details: "
                    + ResponseCode.D.getResponseCodeMessage(), "No customers were found, the list may be empty");
        } else {
            return new ResponseModel(ResponseCode.E, ResponseCode.E.getResponseType().toString() + ": "
                    + ResponseCode.E.getResponseType().getMessage() + " Details: "
                    + ResponseCode.E.getResponseCodeMessage(), customers);
        }
    }

    /**
     * @param email String
     * @return a single customer
     */
    public ResponseModel getCustomerByEmail(String email) {
        Optional<CustomerEntity> customerFound = this.customerDao.findCustomerByEmail(email);
        if (customerFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D, ResponseCode.D.getResponseType().toString() + ": "
                    + ResponseCode.D.getResponseType().getMessage() + " Details: "
                    + ResponseCode.D.getResponseCodeMessage(), "Customer not found with the selected email");
        } else {
            return new ResponseModel(ResponseCode.C, ResponseCode.C.getResponseType().toString() + ": "
                    + ResponseCode.C.getResponseType().getMessage() + " Details: "
                    + ResponseCode.C.getResponseCodeMessage(), this.customerMapper.toDTO(customerFound.get()));
        }
    }

    /**
     * @param isDeleted Boolean
     * @return all customers with the selected deleted status
     */
    public ResponseModel getCustomerByDeletedStatus(Boolean isDeleted) {
        List<CustomerDto> customers = this.customerDao.findCustomerByIsDeleted(isDeleted).stream().map(customerMapper::toDTO).toList();
        if (customers.isEmpty()) {
            return new ResponseModel(ResponseCode.D, ResponseCode.D.getResponseType().toString() + ": "
                    + ResponseCode.D.getResponseType().getMessage() + " Details: "
                    + ResponseCode.D.getResponseCodeMessage(), "No customers were found with the selected parameter");
        } else {
            return new ResponseModel(ResponseCode.E, ResponseCode.E.getResponseType().toString() + ": "
                    + ResponseCode.E.getResponseType().getMessage() + " Details: "
                    + ResponseCode.E.getResponseCodeMessage(), customers);
        }
    }

    /**
     * @param isVerified Boolean
     * @return all customers with the selected verified status
     */
    public ResponseModel getCustomersByVerifiedStatus(Boolean isVerified) {
        List<CustomerDto> customers = this.customerDao.findCustomerByIsVerified(isVerified).stream().map(customerMapper::toDTO).toList();
        if (customers.isEmpty()) {
            return new ResponseModel(ResponseCode.D, ResponseCode.D.getResponseType().toString() + ": "
                    + ResponseCode.D.getResponseType().getMessage() + " Details: "
                    + ResponseCode.D.getResponseCodeMessage(), "No customers were found with the selected parameter");
        } else {
            return new ResponseModel(ResponseCode.E, ResponseCode.E.getResponseType().toString() + ": "
                    + ResponseCode.E.getResponseType().getMessage() + " Details: "
                    + ResponseCode.E.getResponseCodeMessage(), customers);
        }
    }

    /**
     * @param id              customer id
     * @param customerUpdates CustomerDto
     * @return a customer updated
     */
    public ResponseModel updateCustomer(Long id, CustomerDto customerUpdates) {
        Optional<CustomerEntity> customerToUpdate = this.customerDao.findById(id);
        if (customerToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D, ResponseCode.D.getResponseType().toString() + ": "
                    + ResponseCode.D.getResponseType().getMessage() + " Details: "
                    + ResponseCode.D.getResponseCodeMessage(), "Customer not found with the selected ID");
        } else if (customerUpdates != null) {
            if (customerUpdates.getEmail() != null) {
                customerToUpdate.get().setEmail(customerUpdates.getEmail());
            }
            if (customerUpdates.getPassword() != null) {
                customerToUpdate.get().setPassword(customerUpdates.getPassword());
            }
            if (customerUpdates.getIsDeleted() != null) {
                customerToUpdate.get().setIsDeleted(customerUpdates.getIsDeleted());
            }
            if (customerUpdates.getIsVerified() != null) {
                customerToUpdate.get().setIsVerified(customerUpdates.getIsVerified());
            }
            if (customerUpdates.getUserDetails() != null) {
                customerToUpdate.get().setUserDetailsEntity(customerUpdates.getUserDetails());
            }
            return new ResponseModel(ResponseCode.G, ResponseCode.G.getResponseType().toString() + ": "
                    + ResponseCode.G.getResponseType().getMessage() + " Details: "
                    + ResponseCode.G.getResponseCodeMessage(), this.customerMapper.toDTO(this.customerDao.saveAndFlush(customerToUpdate.get())));
        }
        return new ResponseModel(ResponseCode.A, ResponseCode.A.getResponseType().toString() + ": "
                + ResponseCode.A.getResponseType().getMessage() + " Details: "
                + ResponseCode.A.getResponseCodeMessage(), "Impossible to update, the body should not be null");
    }

    /**
     * @param id          customer id
     * @param customerDto CustomerDto
     * @return customer with password updated
     */
    public ResponseModel updatePassword(Long id, CustomerDto customerDto) {
        Optional<CustomerEntity> customerToUpdate = this.customerDao.findById(id);
        if (customerToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D, "Customer not found with the selected ID");
        } else if (customerDto != null) {
            if (customerDto.getPassword() != null) {
                customerToUpdate.get().setPassword(customerDto.getPassword());
                return new ResponseModel(ResponseCode.G, ResponseCode.G.getResponseType().toString() + ": "
                        + ResponseCode.G.getResponseType().getMessage() + " Details: "
                        + ResponseCode.G.getResponseCodeMessage(), customerMapper.toDTO(this.customerDao.saveAndFlush(customerToUpdate.get())));
            }
        }
        return new ResponseModel(ResponseCode.A, ResponseCode.A.getResponseType().toString() + ": "
                + ResponseCode.A.getResponseType().getMessage() + " Details: "
                + ResponseCode.A.getResponseCodeMessage(), "Impossible to update, the body should not be null");
    }

    /**
     * @param id customer id
     */
    public ResponseModel deleteCustomer(Long id) {
        if (!this.customerDao.existsById(id)) {
            return new ResponseModel(ResponseCode.D, ResponseCode.D.getResponseType().toString() + ": "
                    + ResponseCode.D.getResponseType().getMessage() + " Details: "
                    + ResponseCode.D.getResponseCodeMessage(), "Customer not found with the selected ID");
        } else {
            this.customerDao.deleteById(id);
            return new ResponseModel(ResponseCode.H, ResponseCode.H.getResponseType().toString() + ": "
                    + ResponseCode.H.getResponseType().getMessage() + " Details: "
                    + ResponseCode.H.getResponseCodeMessage(), "Customer eliminated");
        }
    }

    public ResponseModel deleteAllCustomers() {
        this.customerDao.deleteAll();
        return new ResponseModel(ResponseCode.H, ResponseCode.H.getResponseType().toString() + ": "
                + ResponseCode.H.getResponseType().getMessage() + " Details: "
                + ResponseCode.H.getResponseCodeMessage(), "All customers eliminated");
    }

}
