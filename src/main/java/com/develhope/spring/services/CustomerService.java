package com.develhope.spring.services;

import com.develhope.spring.daos.CartDao;
import com.develhope.spring.daos.OrderDao;
import com.develhope.spring.exceptions.InvalidCustomerException;
import com.develhope.spring.mappers.CustomerMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.CustomerDto;
import com.develhope.spring.models.entities.CartEntity;
import com.develhope.spring.models.entities.CustomerEntity;
import com.develhope.spring.daos.CustomerDao;
import com.develhope.spring.models.entities.OrderEntity;
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
    private final OrderDao orderDao;

    @Autowired
    public CustomerService(CustomerDao customerDao, CustomerMapper customerMapper, CustomerValidator customerValidator, OrderDao orderDao) {
        this.customerDao = customerDao;
        this.customerMapper = customerMapper;
        this.customerValidator = customerValidator;
        this.orderDao = orderDao;
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
            return new ResponseModel(ResponseCode.B, this.customerMapper.toDTO(newCustomer));
        } catch (InvalidCustomerException e) {
            return new ResponseModel(ResponseCode.A).addMessageDetails(e.getMessage());
        }

    }

    /**
     * @param id customer id
     * @return a single customer
     */
    public ResponseModel getCustomerById(String id) {
        Optional<CustomerEntity> customerFound = this.customerDao.findById(id);
        if (customerFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Customer not found with the selected ID");
        } else {
            return new ResponseModel(ResponseCode.C, this.customerMapper.toDTO(customerFound.get()));
        }
    }

    /**
     * @return List of all customers
     */
    public ResponseModel getAllCustomers() {
        List<CustomerDto> customers = this.customerDao.findAll().stream().map(this.customerMapper::toDTO).toList();
        if (customers.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No customers were found, the list may be empty");
        } else {
            return new ResponseModel(ResponseCode.E, customers);
        }
    }

    /**
     * @param email String
     * @return a single customer
     */
    public ResponseModel getCustomerByEmail(String email) {
        Optional<CustomerEntity> customerFound = this.customerDao.findCustomerByEmail(email);
        if (customerFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Customer not found with the selected email");
        } else {
            return new ResponseModel(ResponseCode.C, this.customerMapper.toDTO(customerFound.get()));
        }
    }

    /**
     * @param isDeleted Boolean
     * @return all customers with the selected deleted status
     */
    public ResponseModel getCustomerByDeletedStatus(Boolean isDeleted) {
        List<CustomerDto> customers = this.customerDao.findCustomerByIsDeleted(isDeleted).stream().map(this.customerMapper::toDTO).toList();
        if (customers.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No customers were found with the selected parameter");
        } else {
            return new ResponseModel(ResponseCode.E, customers);
        }
    }

    /**
     * @param isVerified Boolean
     * @return all customers with the selected verified status
     */
    public ResponseModel getCustomersByVerifiedStatus(Boolean isVerified) {
        List<CustomerDto> customers = this.customerDao.findCustomerByIsVerified(isVerified).stream().map(this.customerMapper::toDTO).toList();
        if (customers.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No customers were found with the selected parameter");
        } else {
            return new ResponseModel(ResponseCode.E, customers);
        }
    }

    /**
     * @param id              customer id
     * @param customerUpdates CustomerDto
     * @return a customer updated
     */
    public ResponseModel updateCustomer(String id, CustomerDto customerUpdates) {
        Optional<CustomerEntity> customerToUpdate = this.customerDao.findById(id);
        if (customerToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Customer not found with the selected ID");
        } else if (customerUpdates != null) {
            CustomerEntity customerEntityUpdates = this.customerMapper.toEntity(customerUpdates);
            if (customerUpdates.getEmail() != null) {
                customerToUpdate.get().setEmail(customerEntityUpdates.getEmail());
            }
            if (customerUpdates.getPassword() != null) {
                customerToUpdate.get().setPassword(customerEntityUpdates.getPassword());
            }
            if (customerUpdates.getUserDetails() != null) {
                customerToUpdate.get().setUserDetails(customerEntityUpdates.getUserDetails());
            }
            return new ResponseModel(ResponseCode.G, this.customerMapper.toDTO(this.customerDao.saveAndFlush(customerToUpdate.get())));
        }
        return new ResponseModel(ResponseCode.A).addMessageDetails("Impossible to update, the body should not be null");
    }

    /**
     * @param id          customer id
     * @param customerDto CustomerDto
     * @return customer with password updated
     */
    public ResponseModel updatePassword(String id, CustomerDto customerDto) {
        Optional<CustomerEntity> customerToUpdate = this.customerDao.findById(id);
        if (customerToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Customer not found with the selected ID");
        } else if (customerDto != null) {
            if (customerDto.getPassword() != null) {
                customerToUpdate.get().setPassword(customerDto.getPassword());
                return new ResponseModel(ResponseCode.G, this.customerMapper.toDTO(this.customerDao.saveAndFlush(customerToUpdate.get())));
            }
        }
        return new ResponseModel(ResponseCode.A).addMessageDetails("Impossible to update, the body should not be null");
    }

    /**
     * @param id customer id
     * @param isVerified the status of isVerified
     * @return isVerified status updated
     */
    public ResponseModel setIsVerified(String id, Boolean isVerified) {
        Optional<CustomerEntity> customerToUpdate = this.customerDao.findById(id);
        if (customerToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Customer not found with the selected ID");
        }
        customerToUpdate.get().setIsVerified(isVerified);
        return new ResponseModel(ResponseCode.G, this.customerMapper.toDTO(this.customerDao.saveAndFlush(customerToUpdate.get())));
    }

    /**
     * @param id customer id
     */
    public ResponseModel deleteCustomer(String id) {
        if (!this.customerDao.existsById(id)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Customer not found with the selected ID");
        } else {
            Optional<CustomerEntity> customerEntity = customerDao.findById(id);
            List<OrderEntity> orderEntities = orderDao.findByCustomerId(id);
            CartEntity cart = customerEntity.get().getCart();
            if (cart != null) {
                customerEntity.get().setCart(null);
            }
            for(OrderEntity order : orderEntities){
                order.getCustomer().setIsDeleted(true);
            }
            customerEntity.get().setIsDeleted(true);
            this.customerDao.saveAndFlush(customerEntity.get());
            return new ResponseModel(ResponseCode.H).addMessageDetails("Customer successfully deleted");
        }
    }

    public ResponseModel deleteAllCustomers() {
        List<CustomerEntity> allCustomers = this.customerDao.findAll();
        List<OrderEntity> allOrders = this.orderDao.findAll();
        for(CustomerEntity customer : allCustomers) {
            customer.setCart(null);
            customer.setIsDeleted(true);
        }
        for(OrderEntity order : allOrders){
            order.getCustomer().setIsDeleted(true);
        }
        this.customerDao.saveAll(allCustomers);
        return new ResponseModel(ResponseCode.H).addMessageDetails("All customers have been deleted");
    }

}