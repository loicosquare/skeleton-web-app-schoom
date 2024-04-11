package com.takima.backskeleton.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ExperienceRequest {
    //private Long idUtilisateur;
    private String titre;
    private String entreprise;
    private String lieu;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
}
