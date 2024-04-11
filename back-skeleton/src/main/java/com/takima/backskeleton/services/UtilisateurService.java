package com.takima.backskeleton.services;

import com.takima.backskeleton.DAO.*;
import com.takima.backskeleton.models.*;
import com.takima.backskeleton.utils.LoginRequest;
import com.takima.backskeleton.utils.UtilisateurRequest;
import jakarta.mail.MessagingException;
import jdk.jshell.execution.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UtilisateurService {

    private final UtilisateurDao utilisateurDao;
    private final CompetenceDao competenceDao;
    private final ExperienceDao experienceDao;
    private final FormationDao formationDao;
    private final LoisirDao loisirDao;
    private final SocialMediaDao socialMediaDao;
    private final EmailService emailService;

    public Utilisateur saveUser(Utilisateur utilisateur) {
        return utilisateurDao.save(utilisateur);
    }

    public Utilisateur getUserById(Long id) {
        return utilisateurDao.findById(id).orElse(null);
    }

    public Utilisateur getUserByEmail(String email) {
        return utilisateurDao.findByEmail(email);
    }

    public Utilisateur getUserByUsername(String username) {
        return utilisateurDao.findByUsername(username);
    }

    public Utilisateur updateUser(UtilisateurRequest utilisateur) {

        Utilisateur existingUser = utilisateurDao.findById(utilisateur.getId()).orElse(null);
        if (existingUser == null) {
            return null;
        }
        existingUser = Utilisateur.builder()
                .id(utilisateur.getId())
                .username(utilisateur.getUsername())
                .email(utilisateur.getEmail())
                .password(utilisateur.getPassword())
                .profilePicture(utilisateur.getProfilePicture())
                .name(utilisateur.getName())
                .telephone(utilisateur.getTelephone())
                .pays(utilisateur.getPays())
                .ville(utilisateur.getVille())
                .build();
        return utilisateurDao.save(existingUser);
    }

    public Utilisateur login(LoginRequest loginRequest) {

        Utilisateur existingUser = utilisateurDao.findByEmailAndPassword(loginRequest.email, loginRequest.password);
        if (existingUser == null) {
            //return null;
            throw new RuntimeException("User not found");
        }

        List<Competence> userCompetence = competenceDao.findAllByUtilisateurId(existingUser.getId());
        List<Experience> userExperience = experienceDao.findAllByUtilisateurId(existingUser.getId());
        List<Formation> userFormation = formationDao.findAllByUtilisateurId(existingUser.getId());
        List<Loisir> userLoisir = loisirDao.findAllByUtilisateurId(existingUser.getId());
        List<SocialMedia> userSocialMedia = socialMediaDao.findAllByUtilisateurId(existingUser.getId());
        existingUser.setCompetences(userCompetence);
        existingUser.setExperiences(userExperience);
        existingUser.setFormations(userFormation);
        existingUser.setLoisirs(userLoisir);
        existingUser.setSocialMedias(userSocialMedia);
        sendEmail(existingUser);
        return existingUser;
    }

    public void sendEmail(Utilisateur utilisateur) {
        // Send the confirmation email
        try {
            emailService.send(
                    utilisateur.getEmail(),
                    utilisateur.getUsername()   + " " + utilisateur.getName() + " " +
                    null,
                    String.format("PERTE U", "yoooo")
            );
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

