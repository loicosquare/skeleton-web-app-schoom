package com.takima.backskeleton.services;

import com.takima.backskeleton.DAO.FormationDao;
import com.takima.backskeleton.DAO.UtilisateurDao;
import com.takima.backskeleton.models.Formation;
import com.takima.backskeleton.models.Utilisateur;
import com.takima.backskeleton.utils.FormationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FormationService {
    private final FormationDao formationDao;
    private final UtilisateurDao utilisateurDao;

    public Formation saveFormation(Long idUtilisateur, FormationRequest formationRequest) {
        Utilisateur utilisateur = utilisateurDao.findById(idUtilisateur)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + idUtilisateur));

        Formation formation = Formation.builder()
                .diplome(formationRequest.getDiplome())
                .domaineEtudes(formationRequest.getDomaineEtudes())
                .ecole(formationRequest.getEcole())
                .lieu(formationRequest.getLieu())
                .anneeDebut(formationRequest.getAnneeDebut())
                .anneeFin(formationRequest.getAnneeFin())
                .utilisateur(utilisateur)
                .build();

        //Ajouter la formation à l'utilisateur
        utilisateur.getFormations().add(formation);

        return formationDao.save(formation);
    }

    public Formation updateFormation(Formation formation) {
        // Recherche de la formation existante dans la base de données
        Formation existingFormation = formationDao.findById(formation.getId()).orElse(null);
        if (existingFormation == null) {
            throw new IllegalArgumentException("Formation with id " + formation.getId() + " does not exist");
        }

        // Mise à jour des champs de la formation existante avec les nouvelles valeurs
        existingFormation.setDiplome(formation.getDiplome());
        existingFormation.setDomaineEtudes(formation.getDomaineEtudes());
        existingFormation.setEcole(formation.getEcole());
        existingFormation.setLieu(formation.getLieu());
        existingFormation.setAnneeDebut(formation.getAnneeDebut());
        existingFormation.setAnneeFin(formation.getAnneeFin());

        // Enregistrement de la formation mise à jour dans la base de données
        return formationDao.save(existingFormation);
    }

    public List<Formation> getAllFormationsByUserId(Long userId) {
        return formationDao.findAllByUtilisateurId(userId);
    }

    public void deleteFormation(Long formationId) {
        Formation existingFormation = formationDao.findById(formationId).orElse(null);
        if (existingFormation == null) {
            throw new IllegalArgumentException("Formation with id " + formationId + " does not exist");
        }
        formationDao.deleteById(formationId);
    }
}
