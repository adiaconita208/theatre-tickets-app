package com.example.teatru_reservations.controllers;

import com.example.teatru_reservations.DTOs.ShowWithAvailableSeatsDTO;
import com.example.teatru_reservations.models.Ticket;
import com.example.teatru_reservations.services.ShowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping("/shows-with-seats")
    public List<ShowWithAvailableSeatsDTO> getShowsWithAvailableSeats() {
        return showService.getShowsWithAvailableSeats();
    }

}