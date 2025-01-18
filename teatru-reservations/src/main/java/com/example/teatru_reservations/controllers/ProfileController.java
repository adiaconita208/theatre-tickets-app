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
public class ProfileController{

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/profile")
    public String getProfilePage(HttpSession session, Model model) {
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/login"; // Redirecționează utilizatorul dacă nu este logat
        }

        model.addAttribute("user", loggedInUser);
        return "profile";
    }

    @PostMapping("/profile/update-phone")
    public String updatePhone(@RequestParam("phone") String phone, HttpSession session, Model model) {
        // Obține utilizatorul logat din sesiune
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/login"; // Redirecționează dacă utilizatorul nu este logat
        }

        if (!phone.matches("\\d{10}")) { // Exemplu: doar numere de 10 cifre
            model.addAttribute("error", "Numărul de telefon este invalid.");
            model.addAttribute("user", loggedInUser);
            return "profile";
        }

        // Actualizează numărul de telefon
        loggedInUser.setClientPhone(phone);
        customerRepository.save(loggedInUser);

        // Actualizează sesiunea cu datele noi
        session.setAttribute("loggedInUser", loggedInUser);

        // Adaugă un mesaj de succes
        model.addAttribute("success", "Numărul de telefon a fost actualizat cu succes.");
        model.addAttribute("user", loggedInUser);

        return "profile"; // Reîncarcă pagina profilului
    }
}