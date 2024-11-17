package com.example.teatru_reservations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.teatru_reservations.models.Distribution;

import java.util.Optional;

public interface DistributionRepository extends JpaRepository<Distribution, Integer> {
    Optional<Distribution> findById(Integer id);
}
