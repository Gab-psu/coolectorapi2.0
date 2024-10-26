
package com.example.payment.config;

import com.example.payment.middleware.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/auth/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}



@PostMapping("/payments")
public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {
    try {
        // Validate payment amount
        if (paymentRequest.getAmount() <= 0) {
            throw new InvalidPaymentAmountException("Invalid payment amount.");
        }

        // Simulate payment processing logic
        String transactionId = UUID.randomUUID().toString();
        
        // Log payment information
        logger.info("Processing payment: Amount: {}, Currency: {}, Transaction ID: {}", 
                    paymentRequest.getAmount(), paymentRequest.getCurrency(), transactionId);
        
        // Store payment info in the database (implementation not shown here)

        return ResponseEntity.ok(new PaymentResponse(transactionId));
    } catch (InvalidPaymentAmountException e) {
        logger.error("Error processing payment: {}", e.getMessage());
        return ResponseEntity.badRequest().body(new PaymentResponse(e.getMessage()));
    } catch (Exception e) {
        logger.error("Unexpected error: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(new PaymentResponse("An unexpected error occurred."));
    }
}




package com.example.payment.entity;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    // Getters and Setters
}



package com.example.payment.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private String status;
    private LocalDateTime createdAt;

    // Getters and Setters
}




package com.example.payment.repository;

import com.example.payment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

package com.example.payment.repository;

import com.example.payment.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {}




package com.example.payment.middleware;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

    private final String secret = "YourSecretKey";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        // Basic JWT Validation Logic
    }
}



package com.example.payment.service;

import com.example.payment.entity.User;
import com.example.payment.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final String secret = "YourSecretKey";

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        }
        return null;
    }
}

package com.example.payment.service;

import com.example.payment.entity.Transaction;
import com.example.payment.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {
    private final TransactionRepository transactionRepository;

    public String processPayment(BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setStatus("SUCCESS");
        transactionRepository.save(transaction);
        return "Transaction successful with ID: " + transaction.getId();
    }
}


package com.example.payment.controller;

import com.example.payment.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return authService.login(username, password);
    }
}


package com.example.payment.controller;

import com.example.payment.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/process")
    public String processPayment(@RequestParam BigDecimal amount) {
        return paymentService.processPayment(amount);
    }
}


spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update


