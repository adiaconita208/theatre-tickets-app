package com.example.teatru_reservations.DTOs;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DistributionDto {
    private Integer id;
    private Integer idShow;
    private Integer idActor;
    private String roleName;
}
