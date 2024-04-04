package com.takima.backskeleton.controllers;

import com.takima.backskeleton.models.Competence;
import com.takima.backskeleton.services.CompetenceService;
import com.takima.backskeleton.utils.CompetenceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("competences")
@RestController
@RequiredArgsConstructor
public class CompetenceController {

    private final CompetenceService competenceService;

    @PostMapping("/add")
    public ResponseEntity<Competence> saveCompetence(@RequestBody CompetenceRequest competenceRequest, @RequestParam Long userId) {
        Competence savedCompetence = competenceService.saveCompetence(competenceRequest, userId);
        return new ResponseEntity<>(savedCompetence, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Competence> updateCompetence(@RequestBody Competence competence) {
        /*if (!id.equals(competence.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }*/
        Competence updatedCompetence = competenceService.updateCompetence(competence);
        return new ResponseEntity<>(updatedCompetence, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Competence>> getAllCompetencesByUserId(@PathVariable Long userId) {
        List<Competence> competences = competenceService.getAllCompetencesByUserId(userId);
        return new ResponseEntity<>(competences, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetence(@PathVariable Long id) {
        competenceService.deleteCompetence(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
