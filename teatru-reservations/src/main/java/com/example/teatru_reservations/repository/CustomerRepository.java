package com.example.teatru_reservations.repository;

import com.example.teatru_reservations.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerName(String customerName);

    Optional<Customer> findById(Integer id);
}
