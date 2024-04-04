package com.takima.backskeleton.controllers;

import com.takima.backskeleton.models.Experience;
import com.takima.backskeleton.services.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RequestMapping("experiences")
@RestController
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping("/add")
    public ResponseEntity<Experience> saveExperience(@RequestParam Long idUtilisateur,
                                                     @RequestParam String titre,
                                                     @RequestParam String entreprise,
                                                     @RequestParam String lieu,
                                                     @RequestParam String description,
                                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateDebut,
                                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFin) {

        experienceService.saveExperience(idUtilisateur, titre, entreprise, lieu, description, dateDebut, dateFin);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Experience> updateExperience(@RequestBody Experience experience) {
        Experience updatedExperience = experienceService.updateExperience(experience);
        return new ResponseEntity<>(updatedExperience, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Experience>> getAllExperiencesByUserId(@PathVariable Long userId) {
        List<Experience> experiences = experienceService.getAllExperiencesByUserId(userId);
        return new ResponseEntity<>(experiences, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
