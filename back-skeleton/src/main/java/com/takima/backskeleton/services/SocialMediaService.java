package com.takima.backskeleton.services;

import com.takima.backskeleton.DAO.SocialMediaDao;
import com.takima.backskeleton.DAO.UtilisateurDao;
import com.takima.backskeleton.models.SocialMedia;
import com.takima.backskeleton.models.Utilisateur;
import com.takima.backskeleton.utils.SocialMediaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SocialMediaService {
    private final SocialMediaDao socialMediaDao;
    private final UtilisateurDao utilisateurDao;

    public SocialMedia saveSocialMedia(Long idUtilisateur, SocialMediaRequest socialMediaRequest) {
        Utilisateur utilisateur = utilisateurDao.findById(idUtilisateur)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + idUtilisateur));

        //Nous utilisons le pattern builder => on peut aussi utiliser les simple setters pour initialiser l'objet
        SocialMedia socialMedia = SocialMedia.builder()
                .type(socialMediaRequest.getType())
                .lien(socialMediaRequest.getLien())
                .build();
        socialMedia.setUtilisateur(utilisateur);

        //Ajouter le social media à l'utilisateur
        //utilisateur.setSocialMedias(List.of(socialMedia)); //Passer la liste n'est pas nécessaire
        utilisateur.getSocialMedias().add(socialMedia);

        return socialMediaDao.save(socialMedia);
    }

    public SocialMedia updateSocialMedia(SocialMedia socialMedia) {
        // Recherche du social media existant dans la base de données
        SocialMedia existingSocialMedia = socialMediaDao.findById(socialMedia.getId()).orElse(null);
        if (existingSocialMedia == null) {
            throw new IllegalArgumentException("SocialMedia with id " + socialMedia.getId() + " does not exist");
        }

        // Mise à jour des champs du social media existant avec les nouvelles valeurs
        existingSocialMedia.setType(socialMedia.getType());
        existingSocialMedia.setLien(socialMedia.getLien());

        // Enregistrement du social media mis à jour dans la base de données
        return socialMediaDao.save(existingSocialMedia);
    }

    public List<SocialMedia> getAllSocialMediaByUserId(Long userId) {
        return socialMediaDao.findAllByUtilisateurId(userId);
    }

    public void deleteSocialMedia(Long id) {
        SocialMedia existingSocialMedia = socialMediaDao.findById(id).orElse(null);
        if (existingSocialMedia == null) {
            throw new IllegalArgumentException("SocialMedia with id " + id + " does not exist");
        }
        socialMediaDao.deleteById(id);
    }
}
