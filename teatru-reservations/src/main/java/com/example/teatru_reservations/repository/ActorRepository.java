package com.example.teatru_reservations.repository;

import com.example.teatru_reservations.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    Optional<Actor> findByActorName(String actorName);

    Optional<Actor> findById(Integer id);

}
