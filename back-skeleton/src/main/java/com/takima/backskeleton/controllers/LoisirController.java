package com.takima.backskeleton.controllers;

import com.takima.backskeleton.models.Loisir;
import com.takima.backskeleton.models.Utilisateur;
import com.takima.backskeleton.services.LoisirService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RequestMapping("loisirs")
@RestController
@RequiredArgsConstructor
public class LoisirController {

    private final LoisirService loisirService;

    @PostMapping("/add")
    public ResponseEntity<Loisir> saveLoisir(@RequestParam Long userId, @RequestParam String hobby) {
        Loisir savedLoisir = loisirService.saveLoisir(userId, hobby);
        return new ResponseEntity<>(savedLoisir, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loisir> updateLoisir(@RequestBody Loisir loisir) {
        Loisir updatedLoisir = loisirService.updateLoisir(loisir);
        return new ResponseEntity<>(updatedLoisir, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Loisir>> getAllLoisirsByUserId(@PathVariable Long userId) {
        List<Loisir> loisirs = loisirService.getAllLoisirsByUserId(userId);
        return new ResponseEntity<>(loisirs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoisir(@PathVariable Long id) {
        loisirService.deleteLoisir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
