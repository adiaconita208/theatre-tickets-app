package com.example.teatru_reservations.repository;

import com.example.teatru_reservations.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HallRepository extends JpaRepository<Hall, Integer> {
    Optional<Hall> findByHallName(String hallName);
    Optional<Hall> findById(Integer idHall);
}
