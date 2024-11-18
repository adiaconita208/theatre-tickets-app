package com.example.teatru_reservations.controllers;

import com.example.teatru_reservations.models.Show;
import com.example.teatru_reservations.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ShowRepository showRepository;

    @GetMapping("/main")
    public String showMainPage(Model model) {
        List<Show> shows = showRepository.findAll();
        model.addAttribute("shows", shows);
        return "main";
    }
}