// AuthRequest.java
package com.example.payment.dto;

public class AuthRequest {
    private String username;
    private String password;

    // Getters and Setters
}

// AuthResponse.java
package com.example.payment.dto;

public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    // Getters and Setters
}

// PaymentRequest.java
package com.example.payment.dto;

import java.math.BigDecimal;

public class PaymentRequest {
    private BigDecimal amount;

    // Getters and Setters
}

// PaymentResponse.java
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

    // Getters and Setters
}
