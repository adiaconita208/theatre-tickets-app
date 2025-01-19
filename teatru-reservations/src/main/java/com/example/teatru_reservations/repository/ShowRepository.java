package com.example.teatru_reservations.repository;

import com.example.teatru_reservations.models.Show;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show, Integer> {
    Optional<Show> findById(Integer idShow);

    Optional<Show> findByTitle(@Size(max = 150) @NotNull String title);

    @Query("SELECT s.title, a.actorName " +
            "FROM Show s " +
            "JOIN Distribution d ON s.id = d.idShow.id " +
            "JOIN Actor a ON d.idActor.id = a.id " +
            "WHERE s.id = :showId")
    List<String> findActorsByShowId(@Param("showId") int showId);
}