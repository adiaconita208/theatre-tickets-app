package com.example.teatru_reservations.services;

import com.example.teatru_reservations.models.Customer;
import com.example.teatru_reservations.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    private CustomerRepository customerRepository;

    public boolean registerUser(String email, String password, String name, String phone) {
        if (customerRepository.findByClientEmail(email) != null) {
            return false;
        }
        Customer customer = new Customer();
        customer.setClientEmail(email);
        customer.setPassword(password);
        customer.setClientName(name);
        customer.setClientPhone(phone);
        customerRepository.save(customer);
        return true;
    }
}