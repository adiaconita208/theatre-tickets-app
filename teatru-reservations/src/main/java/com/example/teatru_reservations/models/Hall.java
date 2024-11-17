package com.example.teatru_reservations.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Halls", schema = "theatre")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Hall", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "hall_name", nullable = false, length = 100)
    private String hallName;

    @NotNull
    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @OneToMany(mappedBy = "idHall")
    private Set<com.example.teatru_reservations.models.Show> shows = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idHall")
    private Set<com.example.teatru_reservations.models.Ticket> tickets = new LinkedHashSet<>();

}