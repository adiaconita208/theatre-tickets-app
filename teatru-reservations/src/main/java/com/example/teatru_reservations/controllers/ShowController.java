package com.example.teatru_reservations.controllers;

import com.example.teatru_reservations.DTOs.ShowWithAvailableSeatsDTO;
import com.example.teatru_reservations.models.Show;
import com.example.teatru_reservations.models.Ticket;
import com.example.teatru_reservations.repository.ShowRepository;
import com.example.teatru_reservations.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Controller
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping("/shows-with-seats")
    public List<ShowWithAvailableSeatsDTO> getShowsWithAvailableSeats() {
        return showService.getShowsWithAvailableSeats();
    }

    @Autowired
    private ShowRepository showRepository;


    @GetMapping("/actors-by-show")
    public String getShowsWithActors(@RequestParam("showId") int showId, Model model) {

        List<String> actors = showRepository.findActorsByShowId(showId);

        if (actors.isEmpty()) {
            model.addAttribute("message", "Nu există actori pentru acest spectacol.");
        } else {
            model.addAttribute("actors", actors);
        }

        return "actors-by-show"; // Numele șablonului Thymeleaf
    }

    @GetMapping("/test-actors-page")
    public String testActorsPage() {
        return "main";
    }

}