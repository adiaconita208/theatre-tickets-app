package com.example.teatru_reservations.repository;

import com.example.teatru_reservations.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket> findByIdTicket(Integer idTicket);
    Optional<Ticket> findByPrice(Double price);
    Optional<Ticket> findBySeatNumber(Integer seatNumber);
    Optional<Ticket> findByIdShow(Integer IdShow);
}
