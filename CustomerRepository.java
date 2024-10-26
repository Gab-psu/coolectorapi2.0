package com.tired.test5;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;
    private String table;

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.table = "customers_test";
    }

    public List<Customer> findAllCustomers() {
        String sql = "SELECT * from " + table;
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Customer.class));
    }
}
