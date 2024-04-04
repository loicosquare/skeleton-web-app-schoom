package com.takima.backskeleton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.takima.backskeleton.DAO.UtilisateurDao;
import com.takima.backskeleton.models.Utilisateur;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class BackSkeletonApplication {

	private final UtilisateurDao utilisateurDao;
	public static void main(String[] args) {
		SpringApplication.run(BackSkeletonApplication.class, args);
	}

	@Bean
	public void initUserData() {
		Utilisateur utilisateur =  Utilisateur.builder()
				.id(1L)
				.username("admin")
				.email("admin@takima.com")
				.password("admin")
				.profilePicture("admin.png")
				.name("admin")
				.telephone("123456789")
				.pays("France")
				.ville("Paris")
				.build();
		utilisateurDao.save(utilisateur);
	}

}
