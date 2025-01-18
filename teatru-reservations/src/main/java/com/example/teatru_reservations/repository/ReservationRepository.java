package com.example.teatru_reservations.repository;

import com.example.teatru_reservations.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findById(Integer idReservation);
    @Query("SELECT r FROM Reservation r WHERE r.customer.id = :customerId")
    List<Reservation> findAllByCustomerId(@Param("customerId") int customerId);
}
