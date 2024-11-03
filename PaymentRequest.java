package com.mycompany.paymentfacilitatorproject.dto;

import java.math.BigDecimal;

public class PaymentRequest {
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
