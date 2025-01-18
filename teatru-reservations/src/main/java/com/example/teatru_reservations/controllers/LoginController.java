package com.example.teatru_reservations.controllers;

import com.example.teatru_reservations.models.Customer;
import com.example.teatru_reservations.repository.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {
        Customer customer = customerRepository.findByClientEmail(email).orElse(null);

        if (customer == null || !customer.getPassword().equals(password)) {
            model.addAttribute("error", "Email-ul sau parola sunt incorecte.");
            return "login";
        }

        // Salvează utilizatorul în sesiune
        session.setAttribute("loggedInUser", customer);
        return "redirect:/profile";
    }

    @GetMapping("/login") // This must handle GET requests
    public String getLoginPage() {
        return "login"; // Return the login page
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Șterge sesiunea
        return "redirect:/login";
    }
}