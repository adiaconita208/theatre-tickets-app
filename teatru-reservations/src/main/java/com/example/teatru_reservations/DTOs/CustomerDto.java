package com.example.teatru_reservations.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class CustomerDto {
    private Integer id;
    private String clientName;
    private String clientEmail;
    private String clientPhone;
    private String createdAt;
}
