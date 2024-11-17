package com.example.teatru_reservations.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class ReservationDto {
    private Integer id;
    private Integer idClient;
    private Integer idTicket;
    private String reservedAt;
}
