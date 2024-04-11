package com.takima.backskeleton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.takima.backskeleton.DAO.UtilisateurDao;
import com.takima.backskeleton.models.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableAsync;

@RequiredArgsConstructor
@SpringBootApplication
@EnableAsync
public class BackSkeletonApplication {

	private final UtilisateurDao utilisateurDao;
	public static void main(String[] args) {
		SpringApplication.run(BackSkeletonApplication.class, args);
	}

	@Bean
	public void initUserData() {
		Utilisateur userOnDataBase = utilisateurDao.findByUsername("admin");
		if (userOnDataBase == null) {
			initAdmin();
		}else {
			System.out.println("Admin already exists");
		}
	}

	private void initAdmin() {
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
