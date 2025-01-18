package com.example.teatru_reservations.controllers;

import com.example.teatru_reservations.models.Customer;
import com.example.teatru_reservations.models.Reservation;
import com.example.teatru_reservations.models.Ticket;
import com.example.teatru_reservations.repository.ReservationRepository;
import com.example.teatru_reservations.repository.TicketRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyTicketsController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/my-tickets")
    public String getMyTicketsPage(HttpSession session, Model model) {
        // Obține utilizatorul logat din sesiune
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/login"; // Redirecționează la login dacă utilizatorul nu este logat
        }

        // Găsește toate rezervările utilizatorului logat
        List<Reservation> reservations = reservationRepository.findAllByCustomerId(loggedInUser.getId());

        // Adaugă rezervările în model pentru a fi afișate
        model.addAttribute("reservations", reservations);

        return "my-tickets"; // Returnează șablonul Thymeleaf
    }

    @PostMapping("/delete-reservation")
    public String deleteReservation(@RequestParam("reservationId") int reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalStateException("Rezervarea nu a fost găsită."));

        // Găsește biletul asociat rezervării
        Ticket ticket = reservation.getIdTicket();

        // Marchează biletul ca disponibil (reserved = 0)
        ticket.setReserved(false);
        ticket.setReservations(null);
        ticketRepository.save(ticket);

        // Șterge rezervarea
        reservationRepository.delete(reservation);

        // Redirecționează înapoi la pagina "My Tickets"
        return "redirect:/my-tickets";
    }
}