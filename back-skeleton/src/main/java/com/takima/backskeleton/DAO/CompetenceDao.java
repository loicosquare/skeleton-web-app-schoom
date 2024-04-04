package com.takima.backskeleton.DAO;

import com.takima.backskeleton.models.Competence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompetenceDao  extends JpaRepository<Competence, Long>{
    List<Competence> findAllByUtilisateurId(Long userId);
}
