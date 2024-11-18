package com.example.teatru_reservations.controllers;

import com.example.teatru_reservations.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/signup")
    public String showSignUpPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String email, @RequestParam String password, @RequestParam String name, @RequestParam String phone, RedirectAttributes redirectAttributes) {
        if (signUpService.registerUser(email, password, name, phone)) {
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("error", "Sign-up failed. Try again.");
            return "redirect:/signup";
        }
    }
}