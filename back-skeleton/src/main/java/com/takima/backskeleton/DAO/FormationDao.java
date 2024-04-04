package com.takima.backskeleton.DAO;

import com.takima.backskeleton.models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormationDao extends JpaRepository<Formation, Long> {
    List<Formation> findAllByUtilisateurId(Long userId);
}
