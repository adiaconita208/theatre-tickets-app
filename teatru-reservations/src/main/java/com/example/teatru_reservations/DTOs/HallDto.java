package com.example.teatru_reservations.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HallDto {
    private Long id;
    private String name;
    private Integer capacity;
}
