package com.takima.backskeleton.DAO;

import com.takima.backskeleton.models.SocialMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialMediaDao extends JpaRepository<SocialMedia, Long> {
    List<SocialMedia> findAllByUtilisateurId(Long userId);
}
