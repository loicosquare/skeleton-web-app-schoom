package com.takima.backskeleton.DAO;

import com.takima.backskeleton.models.Loisir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoisirDao extends JpaRepository<Loisir, Long> {
    List<Loisir> findAllByUtilisateurId(Long userId);
}
