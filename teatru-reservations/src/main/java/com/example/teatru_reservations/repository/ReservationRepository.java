package com.example.teatru_reservations.repository;

import com.example.teatru_reservations.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findByIdReservation(Integer idReservation);
}
