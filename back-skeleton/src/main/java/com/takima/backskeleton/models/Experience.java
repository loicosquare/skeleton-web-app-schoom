package com.takima.backskeleton.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference // Indique que c'est la partie inverse de la relation
    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    private String titre;
    private String entreprise;
    private String lieu;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDebut;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFin;
}
