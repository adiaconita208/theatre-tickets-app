package com.example.teatru_reservations.controllers;

import com.example.teatru_reservations.models.Customer;
import com.example.teatru_reservations.models.Reservation;
import com.example.teatru_reservations.models.Show;
import com.example.teatru_reservations.models.Ticket;
import com.example.teatru_reservations.repository.ShowRepository;
import com.example.teatru_reservations.repository.TicketRepository;
import com.example.teatru_reservations.repository.ReservationRepository;
import com.example.teatru_reservations.services.ShowService;
import com.example.teatru_reservations.DTOs.ShowWithAvailableSeatsDTO;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ReservationRepository reservationRepository;

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

    @PostMapping("/reserve-ticket")
    public String reserveTicket(@RequestParam("showId") int showId, HttpSession session) {
        // Obține utilizatorul logat din sesiune
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/login"; // Redirecționează la login dacă utilizatorul nu este logat
        }

        // Găsește primul bilet disponibil pentru spectacol
        List<Ticket> availableTickets = ticketRepository.findAvailableTicketsByShowId(showId);
        if (availableTickets.isEmpty()) {
            throw new IllegalStateException("Nu există bilete disponibile pentru acest spectacol.");
        }

        Ticket ticket = availableTickets.get(0);

        // Creează rezervarea
        Reservation reservation = new Reservation();
        reservation.setCustomer(loggedInUser);
        reservation.setIdTicket(ticket);

        ticket.setReserved(true); // Marchează biletul ca rezervat

        // Salvează rezervarea
        reservationRepository.save(reservation);
        ticketRepository.save(ticket);

        return "redirect:/my-tickets"; // Redirecționează către pagina biletele utilizatorului
    }

}