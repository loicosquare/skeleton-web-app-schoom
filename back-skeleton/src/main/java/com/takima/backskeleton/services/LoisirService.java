package com.takima.backskeleton.services;

import com.takima.backskeleton.DAO.LoisirDao;
import com.takima.backskeleton.DAO.UtilisateurDao;
import com.takima.backskeleton.models.Loisir;
import com.takima.backskeleton.models.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoisirService {
    private final LoisirDao loisirDao;
    private final UtilisateurDao utilisateurDao;

    public Loisir saveLoisir(Long idUtilisateur, String hobby) {
        Utilisateur utilisateur = utilisateurDao.findById(idUtilisateur)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + idUtilisateur));

        Loisir loisir = new Loisir();
        loisir.setHobby(hobby);
        loisir.setUtilisateur(utilisateur);

        //Ajouter le loisir à l'utilisateur
        utilisateur.getLoisirs().add(loisir);

        return loisirDao.save(loisir);
    }

    public Loisir updateLoisir(Loisir loisir) {
        // Recherche du loisir existant dans la base de données
        Loisir existingLoisir = loisirDao.findById(loisir.getId()).orElse(null);
        if (existingLoisir == null) {
            throw new IllegalArgumentException("Loisir with id " + loisir.getId() + " does not exist");
        }

        // Mise à jour des champs du loisir existant avec les nouvelles valeurs
        existingLoisir.setHobby(loisir.getHobby());

        // Enregistrement du loisir mis à jour dans la base de données
        return loisirDao.save(existingLoisir);
    }

    public List<Loisir> getAllLoisirsByUserId(Long userId) {
        return loisirDao.findAllByUtilisateurId(userId);
    }

    public void deleteLoisir(Long loisirId) {
        Loisir existingLoisir = loisirDao.findById(loisirId).orElse(null);
        if (existingLoisir == null) {
            throw new IllegalArgumentException("Loisir with id " + loisirId + " does not exist");
        }
        loisirDao.deleteById(loisirId);
    }
}