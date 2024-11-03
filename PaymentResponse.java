package com.example.payment.dto;

public class PaymentResponse {
    private String transactionId;
    private String message;

    // Constructor to initialize both fields
    public PaymentResponse(String transactionId, String message) {
        this.transactionId = transactionId;
        this.message = message;
    }

    // Constructor for cases where only the message is needed
    public PaymentResponse(String message) {
        this.message = message;
    }

    // Getters and Setters for transactionId and message
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

    @Override
    public String toString() {
        return "PaymentResponse{" +
                "transactionId='" + transactionId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

