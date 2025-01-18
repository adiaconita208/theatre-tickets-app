package com.example.teatru_reservations.services;

import com.example.teatru_reservations.DTOs.ShowWithAvailableSeatsDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {
    private EntityManager entityManager;

    public ShowService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<ShowWithAvailableSeatsDTO> getShowsWithAvailableSeats() {
        String jpql = "SELECT new ShowWithAvailableSeatsDTO(s.id, s.title, h.hallName, COUNT(t.id), s.showDate, s.durationMinutes) " +
                "FROM Show s " +
                "JOIN s.idHall h " +
                "LEFT JOIN Ticket t ON s.id = t.idShow.id AND t.reserved = false " +
                "GROUP BY s.id, h.hallName, h.capacity";

        TypedQuery<ShowWithAvailableSeatsDTO> query = entityManager.createQuery(jpql, ShowWithAvailableSeatsDTO.class);
        return query.getResultList();
    }
}
