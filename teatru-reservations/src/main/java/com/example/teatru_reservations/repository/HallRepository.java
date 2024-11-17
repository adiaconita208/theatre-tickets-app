package com.example.teatru_reservations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.teatru_reservations.models.Hall;

import java.util.Optional;

public interface HallRepository extends JpaRepository<Hall, Integer> {
    Optional<Hall> findByHallName(String hallName);
    Optional<Hall> finfByIdHall(Integer idHall);
}
