package com.example.teatru_reservations.repository;

import com.example.teatru_reservations.models.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends CrudRepository<Reservation, Integer> {

    // 1. Lista rezervărilor pentru spectacole
    @Query("SELECT s.title, c.clientName, c.clientEmail, t.seatNumber " +
            "FROM Reservation r " +
            "JOIN r.customer c " +
            "JOIN r.idTicket t " +
            "JOIN t.idShow s")
    List<Object[]> findReservationsForShows();

    // 2. Spectacolele cu cele mai multe bilete rezervate
    @Query("SELECT s.title, COUNT(r.id) " +
            "FROM Reservation r " +
            "JOIN r.idTicket t " +
            "JOIN t.idShow s " +
            "GROUP BY s.id, s.title " +
            "ORDER BY COUNT(r.id) DESC")
    List<Object[]> findTopShowsByReservations();

    // 3. Utilizatori fără rezervări
    @Query("SELECT c.clientName, c.clientEmail " +
            "FROM Customer c " +
            "LEFT JOIN Reservation r ON c.id = r.customer.id " +
            "WHERE r.id IS NULL")
    List<Object[]> findUsersWithoutReservations();

    // 4. Actorii în mai multe spectacole
    @Query("SELECT a.actorName, COUNT(d.idShow.id) " +
            "FROM Actor a " +
            "JOIN a.distributions d " +
            "GROUP BY a.id, a.actorName " +
            "HAVING COUNT(d.idShow.id) > 1")
    List<Object[]> findActorsInMultipleShows();

    // 1. Spectacole fără rezervări
    @Query("SELECT s.title, s.showDate, s.durationMinutes " +
            "FROM Show s " +
            "WHERE s.id NOT IN (" +
            "    SELECT t.idShow.id " +
            "    FROM Reservation r " +
            "    JOIN r.idTicket t)")
    List<Object[]> findShowsWithoutReservations();

    // 2. Cel mai rezervat utilizator
    @Query("SELECT c.clientName, c.clientEmail, COUNT(r.id) AS totalReservations " +
            "FROM Customer c " +
            "JOIN Reservation r ON c.id = r.customer.id " +
            "GROUP BY c.id, c.clientName, c.clientEmail " +
            "HAVING COUNT(r.id) = (" +
            "    SELECT MAX(total) " +
            "    FROM (" +
            "        SELECT COUNT(r2.id) AS total " +
            "        FROM Reservation r2 " +
            "        GROUP BY r2.customer.id" +
            "    )" +
            ")")
    List<Object[]> findTopCustomer();


    // 4. Actori fără spectacole viitoare
    @Query("SELECT a.actorName " +
            "FROM Actor a " +
            "WHERE a.id NOT IN (" +
            "    SELECT d.idActor.id " +
            "    FROM Distribution d " +
            "    JOIN d.idShow s " +
            "    WHERE s.showDate > CURRENT_TIMESTAMP)")
    List<String> findActorsWithoutUpcomingShows();

    @Query("SELECT s.title AS title, h.capacity AS capacity, " +
            "       (h.capacity - COALESCE((" +
            "           SELECT COUNT(r.id) " +
            "           FROM Reservation r " +
            "           JOIN r.idTicket t " +
            "           WHERE t.idShow.id = s.id" +
            "       ), 0)) AS availableTickets " +
            "FROM Show s " +
            "JOIN s.idHall h " +
            "ORDER BY (h.capacity - COALESCE((" +
            "           SELECT COUNT(r.id) " +
            "           FROM Reservation r " +
            "           JOIN r.idTicket t " +
            "           WHERE t.idShow.id = s.id" +
            "       ), 0)) ASC")
    List<Object[]> findShowsWithFewestAvailableTickets();
}