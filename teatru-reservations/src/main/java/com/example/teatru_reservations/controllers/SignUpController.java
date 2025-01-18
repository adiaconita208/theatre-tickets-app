package com.example.teatru_reservations.controllers;

import com.example.teatru_reservations.models.Customer;
import com.example.teatru_reservations.repository.CustomerRepository;
import com.example.teatru_reservations.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/signup")
    public String showSignUpPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("phone") String phone,
                               @RequestParam("name") String name,
                               Model model) {
        // Verifică dacă utilizatorul există deja
        if (customerRepository.findByClientEmail(email).isPresent()) {
            model.addAttribute("error", "Email-ul este deja utilizat.");
            return "signup";
        }

        // Creează un nou utilizator
        Customer customer = new Customer();
        customer.setClientEmail(email);
        customer.setPassword(password);
        customer.setClientPhone(phone);
        customer.setClientName(name);
        customerRepository.save(customer);

        return "redirect:/login";
    }
}