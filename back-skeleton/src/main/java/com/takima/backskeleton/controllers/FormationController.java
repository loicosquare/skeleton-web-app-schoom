package com.takima.backskeleton.controllers;

import com.takima.backskeleton.models.Formation;
import com.takima.backskeleton.services.FormationService;
import com.takima.backskeleton.utils.FormationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("formations")
@RestController
@RequiredArgsConstructor
public class FormationController {

    private final FormationService formationService;

    @PostMapping("/add")
    public ResponseEntity<Formation> saveFormation(@RequestParam Long userId, @RequestBody FormationRequest formationRequest) {
        Formation savedFormation = formationService.saveFormation(userId, formationRequest);
        return new ResponseEntity<>(savedFormation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Formation> updateFormation(@RequestBody Formation formation) {
        Formation updatedFormation = formationService.updateFormation(formation);
        return new ResponseEntity<>(updatedFormation, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Formation>> getAllFormationsByUserId(@PathVariable Long userId) {
        List<Formation> formations = formationService.getAllFormationsByUserId(userId);
        return new ResponseEntity<>(formations, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable Long id) {
        formationService.deleteFormation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}