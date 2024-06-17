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

    /**
     * @param paymentDto a paymentDto
     * @return a new payment
     */
    public ResponseModel createPayment(PaymentDto paymentDto) {

        try {
            paymentValidator.validatePayment(paymentDto);
            PaymentEntity newPayment = this.paymentMapper.toEntity(paymentDto);
            this.paymentDao.save(newPayment);
            return new ResponseModel(ResponseCode.B, newPayment);
        } catch (InvalidPaymentException e) {
            return new ResponseModel(ResponseCode.A).addMessageDetails(e.getMessage());
        }

    }

    /**
     * @return all payments
     */
    public ResponseModel getAllPayments() {
        List<PaymentDto> payments = this.paymentDao.findAll().stream().map(this.paymentMapper::toDto).toList();
        if (payments.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No payments found, the list may be empty");
        } else {
            return new ResponseModel(ResponseCode.E, payments);
        }
    }

    /**
     * @param id payment ID
     * @return a single payment found with the selected ID
     */
    public ResponseModel getPaymentById(String id) {
        Optional<PaymentEntity> paymentFound = this.paymentDao.findById(id);
        if (paymentFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No payment found with the selected ID");
        } else {
            return new ResponseModel(ResponseCode.C, this.paymentMapper.toDto(paymentFound.get()));
        }
    }

    //getPaymentByOrderId

    /**
     * @param id            payment ID
     * @param paymentStatus the new payment status
     * @return a payment with the status updated
     */
    public ResponseModel updatePaymentStatus(String id, PaymentStatus paymentStatus) {
        Optional<PaymentEntity> paymentToUpdate = this.paymentDao.findById(id);
        if (paymentToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Payment not found with the selected ID");
        } else if (paymentStatus != null) {
            paymentToUpdate.get().setPaymentStatus(paymentStatus);
            return new ResponseModel(ResponseCode.G, this.paymentMapper.toDto(this.paymentDao.save(paymentToUpdate.get())));
        }
        return new ResponseModel(ResponseCode.A).addMessageDetails("Impossible to update, the body should not be null");
    }

    /**
     * @param id            payment id
     * @param paymentMethod the new payment method
     * @return a payment with the method updated
     */
    public ResponseModel updatePaymentMethod(String id, PaymentMethod paymentMethod) {
        Optional<PaymentEntity> paymentToUpdate = this.paymentDao.findById(id);
        if (paymentToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Payment not found with the selected ID");
        } else if (paymentMethod != null) {
            paymentToUpdate.get().setPaymentMethod(paymentMethod);
            return new ResponseModel(ResponseCode.G, this.paymentMapper.toDto(this.paymentDao.save(paymentToUpdate.get())));
        }
        return new ResponseModel(ResponseCode.A).addMessageDetails("Impossible to update, the body should not be null");
    }

    /**
     * @param id payment id
     * @return delete a single payment
     */
    public ResponseModel deletePaymentById(String id) {
        if (!paymentDao.existsById(id)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Payment not found with the selected ID");
        } else {
            this.paymentDao.deleteById(id);
            return new ResponseModel(ResponseCode.H).addMessageDetails("Payment successfully deleted");
        }
    }

    /**
     * @return delete all payments
     */
    public ResponseModel deleteAllPayments() {
        this.paymentDao.deleteAll();
        return new ResponseModel(ResponseCode.H).addMessageDetails("All payments have been deleted");
    }

}
