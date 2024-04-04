package com.takima.backskeleton.services;

import com.takima.backskeleton.DAO.CompetenceDao;
import com.takima.backskeleton.DAO.UtilisateurDao;
import com.takima.backskeleton.models.Competence;
import com.takima.backskeleton.models.Utilisateur;
import com.takima.backskeleton.utils.CompetenceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CompetenceService {
    private final CompetenceDao competenceDao;
    private final UtilisateurDao utilisateurDao;

    public Competence saveCompetence(CompetenceRequest competenceRequest, Long userId) {
        // Rechercher l'utilisateur
        Utilisateur utilisateur = utilisateurDao.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + userId));

        // Créer une nouvelle compétence
        Competence nouvelleCompetence = new Competence();
        nouvelleCompetence.setCompetence(competenceRequest.competence);
        nouvelleCompetence.setNiveau(competenceRequest.niveau);

        // Définir l'utilisateur pour la compétence
        nouvelleCompetence.setUtilisateur(utilisateur);

        // Ajouter la compétence à l'utilisateur
        utilisateur.getCompetences().add(nouvelleCompetence);

        // Enregistrer la compétence
        competenceDao.save(nouvelleCompetence);

        return nouvelleCompetence;
    }

    public Competence updateCompetence(Competence competence) {
        // Recherche de la compétence existante dans la base de données
        Competence existingCompetence = competenceDao.findById(competence.getId()).orElse(null);

        if (existingCompetence == null) {
            throw new IllegalArgumentException("Competence with id " + competence.getId() + " does not exist");
        }

        // Mise à jour des champs de la compétence existante avec les nouvelles valeurs
        existingCompetence.setCompetence(competence.getCompetence());
        existingCompetence.setNiveau(competence.getNiveau());

        // Enregistrement de la compétence mise à jour dans la base de données
        return competenceDao.save(existingCompetence);
    }

    public List<Competence> getAllCompetencesByUserId(Long userId) {
        return competenceDao.findAllByUtilisateurId(userId);
    }

    public void deleteCompetence(Long id) {
        // Recherche de la compétence existante dans la base de données
        Competence existingCompetence = competenceDao.findById(id).orElse(null);
        if (existingCompetence == null) {
            throw new IllegalArgumentException("Competence with id " + id + " does not exist");
        }
        competenceDao.deleteById(id);
    }
}
