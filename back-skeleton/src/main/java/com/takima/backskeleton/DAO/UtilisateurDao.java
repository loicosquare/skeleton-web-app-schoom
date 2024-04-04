package com.takima.backskeleton.DAO;

import com.takima.backskeleton.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);

    Utilisateur findByUsername(String username);

    Utilisateur findByEmailAndPassword(String email, String password);

    //Nous n'utilisons pas cette requete car elle cause l'érreur de fetchs multiples du a plusieurs jointures dans la requete.
    @Query("SELECT u FROM Utilisateur u LEFT JOIN FETCH u.competences LEFT JOIN FETCH u.experiences LEFT JOIN FETCH u.formations LEFT JOIN FETCH u.loisirs LEFT JOIN FETCH u.socialMedias WHERE u.email = :email AND u.password = :password")
    Utilisateur findByEmailAndPasswordWithDetails(@Param("email") String email, @Param("password") String password);
}
