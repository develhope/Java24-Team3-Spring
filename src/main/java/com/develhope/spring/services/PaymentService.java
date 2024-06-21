package com.develhope.spring.services;

import com.develhope.spring.daos.PaymentDao;
import com.develhope.spring.exceptions.InvalidPaymentException;
import com.develhope.spring.mappers.PaymentMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.PaymentDto;
import com.develhope.spring.models.entities.PaymentEntity;
import com.develhope.spring.models.entities.PaymentMethod;
import com.develhope.spring.models.entities.PaymentStatus;
import com.develhope.spring.validators.PaymentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentDao paymentDao;
    private final PaymentValidator paymentValidator;
    private final PaymentMapper paymentMapper;

    @Autowired
    public PaymentService(PaymentDao paymentDao, PaymentValidator paymentValidator, PaymentMapper paymentMapper) {
        this.paymentDao = paymentDao;
        this.paymentValidator = paymentValidator;
        this.paymentMapper = paymentMapper;
    }

    // CREATE

    /**
     * @param paymentDto a paymentDto
     * @return a new payment
     */
    public ResponseModel create(PaymentDto paymentDto) {
        try {
            paymentValidator.validatePayment(paymentDto);
            PaymentEntity newPayment = this.paymentMapper.toEntity(paymentDto);
            this.paymentDao.save(newPayment);
            return new ResponseModel(ResponseCode.B, newPayment);
        } catch (InvalidPaymentException e) {
            return new ResponseModel(ResponseCode.A).addMessageDetails(e.getMessage());
        }
    }

    // READ

    /**
     * @return all payments
     */
    public ResponseModel getAll() {
        List<PaymentDto> payments = this.paymentDao.findAll().stream().map(this.paymentMapper::toDto).toList();
        if (payments.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No payments found, the list may be empty");
        } else {
            return new ResponseModel(ResponseCode.E, payments);
        }
    }

    /**
     * @param id payment id
     * @return a single payment found with the selected ID
     */
    public ResponseModel getById(String id) {
        Optional<PaymentEntity> paymentFound = this.paymentDao.findById(id);
        if (paymentFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No payment found with the selected ID");
        } else {
            return new ResponseModel(ResponseCode.C, this.paymentMapper.toDto(paymentFound.get()));
        }
    }

    /**
     * @param orderId order id
     * @return
     */
    public ResponseModel getByOrderId(String orderId) {
        Optional<PaymentEntity> paymentFound = this.paymentDao.findByOrderId(orderId);
        if(paymentFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No payments associated to this order ID");
        } else {
            return new ResponseModel(ResponseCode.C, this.paymentMapper.toDto(paymentFound.get()));
        }
    }

    public ResponseModel getByStatus(PaymentStatus status) {
        List<PaymentEntity> payments = this.paymentDao.findByStatus(status);
        if(payments.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No payments with " + status.toString() + " status found.");
        } else {
            return new ResponseModel(ResponseCode.E, payments);
        }
    }

    public ResponseModel getByMethod(PaymentMethod method) {
        List<PaymentEntity> payments = this.paymentDao.findByMethod(method);
        if(payments.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No payments with " + method.toString() + " method found.");
        } else {
            return new ResponseModel(ResponseCode.E, payments);
        }
    }

    // UPDATE

    /**
     * @param id            payment id
     * @param paymentStatus the new payment status
     * @return a payment with the status updated
     */
    public ResponseModel updateStatus(String id, PaymentStatus paymentStatus) {
        Optional<PaymentEntity> paymentToUpdate = this.paymentDao.findById(id);
        if (paymentToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Payment not found with the selected ID");
        } else if (paymentStatus != null) {
            paymentToUpdate.get().setStatus(paymentStatus);
            return new ResponseModel(ResponseCode.G, this.paymentMapper.toDto(this.paymentDao.save(paymentToUpdate.get())));
        }
        return new ResponseModel(ResponseCode.A).addMessageDetails("Impossible to update, the body should not be null");
    }

    /**
     * @param id            payment id
     * @param paymentMethod the new payment method
     * @return a payment with the method updated
     */
    public ResponseModel updateMethod(String id, PaymentMethod paymentMethod) {
        Optional<PaymentEntity> paymentToUpdate = this.paymentDao.findById(id);
        if (paymentToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Payment not found with the selected ID");
        } else if (paymentMethod != null) {
            paymentToUpdate.get().setMethod(paymentMethod);
            return new ResponseModel(ResponseCode.G, this.paymentMapper.toDto(this.paymentDao.save(paymentToUpdate.get())));
        }
        return new ResponseModel(ResponseCode.A).addMessageDetails("Impossible to update, the body should not be null");
    }

    // DELETE

    /**
     * @param id payment id
     * @return delete a single payment
     */
    public ResponseModel deleteById(String id) {
        if (!paymentDao.existsById(id)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("payment not found.");
        } else {
            this.paymentDao.findById(id).get().setOrder(null);
            this.paymentDao.deleteById(id);
            return new ResponseModel(ResponseCode.H).addMessageDetails("payment successfully deleted.");
        }
    }

    /**
     * @return delete all payments
     */
    public ResponseModel deleteAll() {
        List<PaymentEntity> payments = this.paymentDao.findAll();

        for(PaymentEntity payment : payments) {
            payment.getOrder().setReview(null);
        }

        this.paymentDao.deleteAll();
        return new ResponseModel(ResponseCode.H).addMessageDetails("all payments have been successfully deleted.");
    }

}
