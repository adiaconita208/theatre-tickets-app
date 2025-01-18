package com.example.teatru_reservations.controllers;

import com.example.teatru_reservations.models.Show;
import com.example.teatru_reservations.models.Ticket;
import com.example.teatru_reservations.repository.ShowRepository;
import com.example.teatru_reservations.services.ShowService;
import com.example.teatru_reservations.DTOs.ShowWithAvailableSeatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ShowRepository showRepository;

    private final ShowService showService;

    public MainController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping({"/"})
    public String getMainPage(Model model) {
        // Obține lista spectacolelor cu locuri disponibile
        List<ShowWithAvailableSeatsDTO> shows = showService.getShowsWithAvailableSeats();
        // Adaugă lista în model pentru a fi afișată în pagină
        model.addAttribute("shows", shows);
        return "main"; // Thymeleaf va folosi main.html pentru afișare
    }

}