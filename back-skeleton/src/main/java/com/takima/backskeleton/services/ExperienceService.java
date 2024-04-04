package com.takima.backskeleton.services;

import com.takima.backskeleton.DAO.ExperienceDao;
import com.takima.backskeleton.DAO.UtilisateurDao;
import com.takima.backskeleton.models.Experience;
import com.takima.backskeleton.models.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ExperienceService {
    private final ExperienceDao experienceDao;
    private final UtilisateurDao utilisateurDao;
    

    public void saveExperience(Long idUtilisateur, String titre, String entreprise, String lieu, String description, LocalDate dateDebut, LocalDate dateFin) { //On pouvait aussi créer un objet experienceRequest pour éviter de passer autant de paramètres
        // Rechercher l'utilisateur
        Utilisateur utilisateur = utilisateurDao.findById(idUtilisateur)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + idUtilisateur));

        // Créer une nouvelle expérience
        Experience nouvelleExperience = new Experience();
        nouvelleExperience.setTitre(titre);
        nouvelleExperience.setEntreprise(entreprise);
        nouvelleExperience.setLieu(lieu);
        nouvelleExperience.setDescription(description);
        nouvelleExperience.setDateDebut(dateDebut);
        nouvelleExperience.setDateFin(dateFin);

        // Définir l'utilisateur pour l'expérience
        nouvelleExperience.setUtilisateur(utilisateur);

        // Ajouter l'expérience à l'utilisateur
        utilisateur.getExperiences().add(nouvelleExperience);

        // Enregistrer l'expérience
        experienceDao.save(nouvelleExperience);
    }

    public Experience updateExperience(Experience experience) {
        // Recherche de l'expérience existante dans la base de données
        Experience existingExperience = experienceDao.findById(experience.getId()).orElse(null);
        if (existingExperience == null) {
            throw new IllegalArgumentException("Experience with id " + experience.getId() + " does not exist");
        }

        // Mise à jour des champs de l'expérience existante avec les nouvelles valeurs
        existingExperience.setTitre(experience.getTitre());
        existingExperience.setEntreprise(experience.getEntreprise());
        existingExperience.setLieu(experience.getLieu());
        existingExperience.setDescription(experience.getDescription());
        existingExperience.setDateDebut(experience.getDateDebut());
        existingExperience.setDateFin(experience.getDateFin());

        // Enregistrement de l'expérience mise à jour dans la base de données
        return experienceDao.save(existingExperience);
    }

    public List<Experience> getAllExperiencesByUserId(Long userId) {
        return experienceDao.findAllByUtilisateurId(userId);
    }

    public void deleteExperience(Long id) {
        Experience existingExperience = experienceDao.findById(id).orElse(null);
        if (existingExperience == null) {
            throw new IllegalArgumentException("Experience with id " + id + " does not exist");
        }
        experienceDao.deleteById(id);
    }
}