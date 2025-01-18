package com.example.teatru_reservations.repository;

import com.example.teatru_reservations.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket> findById(Integer idTicket);
    Optional<Ticket> findByPrice(Double price);
    Optional<Ticket> findBySeatNumber(String seatNumber);

    @Query(value = "SELECT * FROM Tickets WHERE ID_Show = :showId AND reserved = false ORDER BY ID_Ticket ASC LIMIT 1", nativeQuery = true)
    Optional<Ticket> findFirstAvailableTicketByShowId(@Param("showId") int showId);

    @Query("SELECT t FROM Ticket t WHERE t.idShow.id = :showId AND t.reserved = false")
    List<Ticket> findAvailableTicketsByShowId(@Param("showId") int showId);
}
