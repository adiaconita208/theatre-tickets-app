package com.example.teatru_reservations.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "Actors", schema = "theatre")
public class Actor {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Actor", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "actor_name", nullable = false, length = 100)
    private String actorName;

    @ColumnDefault("'F'")
    @Column(name = "actor_gender")
    private Character actorGender;

}