package com.example.teatru_reservations.controllers;

import com.example.teatru_reservations.models.Ticket;
import com.example.teatru_reservations.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    private final TicketRepository ticketRepository;

    @Autowired
    public ReservationController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @PostMapping("/reserve")
    public String reserveNextAvailableTicket(@RequestParam("showId") int showId) {
        // Găsește biletele disponibile pentru spectacol
        Ticket nextAvailableTicket = ticketRepository.findFirstAvailableTicketByShowId(showId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Nu mai există bilete disponibile pentru acest spectacol."));

        // Marchează biletul ca rezervat
        nextAvailableTicket.setReserved(true);
        ticketRepository.save(nextAvailableTicket);

        // Redirecționează înapoi la pagina principală
        return "redirect:/";
    }
}