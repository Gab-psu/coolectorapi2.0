package com.example.payment.dto;

public class PaymentResponse {
    private String transactionId;
    private String message;

    public PaymentResponse(String transactionId) {
        this.transactionId = transactionId;
        this.message = "Transaction successful";
    }

    public PaymentResponse(String message) {
        this.message = message;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
