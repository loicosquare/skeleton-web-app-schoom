package com.takima.backskeleton.DAO;

import com.takima.backskeleton.models.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceDao extends JpaRepository<Experience, Long>{
    List<Experience> findAllByUtilisateurId(Long userId);
}
