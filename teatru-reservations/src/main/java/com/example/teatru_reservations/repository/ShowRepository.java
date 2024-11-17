package com.example.teatru_reservations.repository;

import com.example.teatru_reservations.models.Show;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show, Integer> {
    Optional<Show> findById(Integer idShow);

    Optional<Show> findByTitle(@Size(max = 150) @NotNull String title);
}