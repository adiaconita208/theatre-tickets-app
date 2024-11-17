package com.example.teatru_reservations.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActorDto {
    private Integer id;
    private String actorName;
    private Character actorGender;
}
