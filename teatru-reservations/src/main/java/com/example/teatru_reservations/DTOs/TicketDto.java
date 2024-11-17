package com.example.teatru_reservations.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketDto {
    private Integer id;
    private String seatNumber;
    private Long idShow;
    private Long idHall;
    private String price;
}
