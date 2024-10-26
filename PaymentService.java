// PaymentService.java
package com.example.payment.service;

import com.example.payment.dto.PaymentRequest;
import com.example.payment.dto.PaymentResponse;
import com.example.payment.entity.Transaction;
import com.example.payment.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    private final TransactionRepository transactionRepository;

    public PaymentService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        // Business logic and validations
        if (paymentRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return new PaymentResponse("Invalid payment amount.");
        }

        // Simulate transaction processing
        Transaction transaction = new Transaction();
        transaction.setAmount(paymentRequest.getAmount());
        transaction.setStatus("SUCCESS");
        transactionRepository.save(transaction);

        return new PaymentResponse(transaction.getId().toString());
    }
}
