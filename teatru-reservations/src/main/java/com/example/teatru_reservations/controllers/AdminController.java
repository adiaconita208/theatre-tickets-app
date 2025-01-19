package com.example.teatru_reservations.controllers;

import com.example.teatru_reservations.models.Show;
import com.example.teatru_reservations.repository.AdminRepository;
import com.example.teatru_reservations.repository.CustomerRepository;
import com.example.teatru_reservations.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Controller
public class AdminController {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/admin")
    public String getAdminDashboard(Model model) {
        // Adaugă spectacolele și utilizatorii pentru afișare
        model.addAttribute("shows", showRepository.findAll());
        model.addAttribute("users", customerRepository.findAll());

        model.addAttribute("reservations", adminRepository.findReservationsForShows());
        model.addAttribute("topShows", adminRepository.findTopShowsByReservations());
        model.addAttribute("usersWithoutReservations", adminRepository.findUsersWithoutReservations());
        model.addAttribute("actorsInMultipleShows", adminRepository.findActorsInMultipleShows());

        model.addAttribute("showsWithoutReservations", adminRepository.findShowsWithoutReservations());
        model.addAttribute("topCustomer", adminRepository.findTopCustomer());
        model.addAttribute("actorsWithoutUpcomingShows", adminRepository.findActorsWithoutUpcomingShows());
        model.addAttribute("showsWithFewestTickets", adminRepository.findShowsWithFewestAvailableTickets());

        return "admin-dashboard"; // Șablonul Thymeleaf pentru dashboard
    }

    @GetMapping("/admin/delete-show")
    public String deleteShow(@RequestParam("id") int showId) {
        showRepository.deleteById(showId);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete-user")
    public String deleteUser(@RequestParam("id") Long userId) {
        customerRepository.deleteById(userId);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit-show")
    public String editShow(@RequestParam("id") int showId, Model model) {
        Show show = showRepository.findById(showId).orElseThrow(() -> new IllegalArgumentException("Spectacolul nu există."));
        // Conversia Instant -> LocalDateTime
        LocalDateTime showDateTime = LocalDateTime.ofInstant(show.getShowDate(), ZoneId.systemDefault());

        // Formatul pentru câmpul datetime-local
        String formattedDateTime = showDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

        model.addAttribute("show", show);
        model.addAttribute("formattedShowDate", formattedDateTime);
        return "edit-show"; // Șablon pentru editarea spectacolului
    }

    @PostMapping("/admin/save-show")
    public String saveShow(@RequestParam("id") int showId,
                           @RequestParam("showDate") String showDate,
                           @RequestParam("title") String title,
                           @RequestParam("durationMinutes") int durationMinutes) {
        Show show = showRepository.findById(showId).orElseThrow(() -> new IllegalArgumentException("Spectacolul nu există."));

        // Convertirea din String (datetime-local) în Instant
        LocalDateTime localDateTime = LocalDateTime.parse(showDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();

        // Actualizarea obiectului
        show.setShowDate(instant);
        show.setTitle(title);
        show.setDurationMinutes(durationMinutes);

        showRepository.save(show);
        return "redirect:/admin";
    }

    @GetMapping("/admin/add-show")
    public String showAddShowForm(Model model) {
        model.addAttribute("show", new Show()); // Creează un obiect gol pentru binding
        return "add-show"; // Redirecționează către șablonul Thymeleaf pentru formular
    }

    @PostMapping("/admin/add-show")
    public String addShow(@ModelAttribute("show") Show show, @RequestParam("showDateString") String showDate) {
        try {

            System.out.println("Show date: " + showDate);

            LocalDateTime localDateTime = LocalDateTime.parse(showDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();

            System.out.println("LocalDateTime: " + localDateTime);
            System.out.println("Instant: " + instant);

            // Setăm valoarea convertită
            show.setShowDate(instant);

            // Salvează spectacolul
            showRepository.save(show);
        } catch (Exception e) {
            // Loghează eroarea și returnează un mesaj prietenos
            e.printStackTrace();
            return "redirect:/admin/add-show?error=invalid_date";
        }
        return "redirect:/admin";
    }
}