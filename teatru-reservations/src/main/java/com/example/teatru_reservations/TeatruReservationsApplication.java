package com.example.teatru_reservations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.teatru_reservations.models")
public class TeatruReservationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeatruReservationsApplication.class, args);
	}

}
