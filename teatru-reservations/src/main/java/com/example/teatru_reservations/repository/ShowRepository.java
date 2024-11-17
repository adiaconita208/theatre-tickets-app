package com.example.teatru_reservations.repository;

import com.example.teatru_reservations.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show, Integer> {
    Optional<Show> findByIdShow(Integer idShow);
    Optional<Show> findByShowName(String showName);
}
