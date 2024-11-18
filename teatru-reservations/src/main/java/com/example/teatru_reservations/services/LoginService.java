package com.example.teatru_reservations.services;

import com.example.teatru_reservations.models.Customer;
import com.example.teatru_reservations.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private CustomerRepository customerRepository;

    public boolean authenticate(String email, String password) {
        Optional<Customer> customer = customerRepository.findByClientEmail(email);
        return customer.isPresent() && customer.get().getPassword().equals(password);
    }
}