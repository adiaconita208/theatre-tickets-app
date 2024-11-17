package com.example.teatru_reservations.repository;

import com.example.teatru_reservations.models.Distribution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistributionRepository extends JpaRepository<Distribution, Integer> {
    Optional<Distribution> findById(Integer id);
}
