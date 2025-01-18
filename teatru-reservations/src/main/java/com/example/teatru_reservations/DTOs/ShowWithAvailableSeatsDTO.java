package com.example.teatru_reservations.DTOs;

import java.time.Instant;

public class ShowWithAvailableSeatsDTO {
    private Integer id;
    private String title;
    private String hallName;
    private Long availableSeats;
    private Instant showDate;
    private Integer durationMinutes;

    public ShowWithAvailableSeatsDTO(Integer id, String title, String hallName, Long availableSeats, Instant showDate, Integer durationMinutes) {
        this.id = id;
        this.title = title;
        this.hallName = hallName;
        this.availableSeats = availableSeats;
        this.showDate = showDate;
        this.durationMinutes = durationMinutes;
    }

    // Getters și Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getShowDate() {
        return showDate;
    }

    public void setShowDate(Instant showDate) {
        this.showDate = showDate;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public Long getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Long availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "ShowWithAvailableSeatsDTO{" +
                "title='" + title + '\'' +
                ", hallName='" + hallName + '\'' +
                ", availableSeats=" + availableSeats +
                '}';
    }
}