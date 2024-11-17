package com.example.teatru_reservations.DTOs;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder

public class ShowDto {
    private Long id;
    private String title;
    private String director;
    private Instant showDate;
    private Integer durationMinutes;
    private Long idHall;
}
