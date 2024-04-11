package com.takima.backskeleton.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "utilisateur")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private String profilePicture;
    private String name;
    private String telephone;
    private String pays;
    private String ville;

    // Ignorer la sérialisation de la liste des formations
    //@JsonIgnore
    @JsonManagedReference // Indique que c'est la partie propriétaire de la relation
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Formation> formations;

    // Ignorer la sérialisation de la liste des expériences
    //@JsonIgnore
    //@JsonManagedReference // Indique que c'est la partie propriétaire de la relation
    @JsonBackReference // Indique que c'est la partie inverse de la relation
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Experience> experiences;

    // Ignorer la sérialisation de la liste des compétences
    //@JsonIgnore
    @JsonManagedReference // Indique que c'est la partie propriétaire de la relation
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Competence> competences;

    // Ignorer la sérialisation de la liste des loisirs
    //@JsonIgnore
    @JsonManagedReference // Indique que c'est la partie propriétaire de la relation
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Loisir> loisirs;

    // Ignorer la sérialisation de la liste des réseaux sociaux
    //@JsonIgnore
    @JsonManagedReference // Indique que c'est la partie propriétaire de la relation
    // @JsonBackReference // Indique que c'est la partie inverse de la relation
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SocialMedia> socialMedias;
}