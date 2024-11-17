package com.example.teatru_reservations.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "Customers", schema = "theatre")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Client", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "client_name", nullable = false, length = 100)
    private String clientName;

    @Size(max = 100)
    @Column(name = "client_email", length = 100)
    private String clientEmail;

    @Size(max = 15)
    @Column(name = "client_phone", length = 15)
    private String clientPhone;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

}