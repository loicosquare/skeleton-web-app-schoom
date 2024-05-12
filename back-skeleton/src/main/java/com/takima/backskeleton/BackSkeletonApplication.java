package com.takima.backskeleton;

import com.takima.backskeleton.DAO.*;
import com.takima.backskeleton.models.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
@EnableAsync
public class BackSkeletonApplication {

	private final UtilisateurDao utilisateurDao;
	private final CompetenceDao competenceDao;
	private final ExperienceDao experienceDao;
	private final LoisirDao loisirDao;
	private final SocialMediaDao socialMediaDao;
	private final FormationDao formationDao;


	public static void main(String[] args) {
		SpringApplication.run(BackSkeletonApplication.class, args);
	}

	@Bean
	public void initUserData() {
		List<String> usernames = Arrays.asList(
				"admin", "utilisateur2", "utilisateur3", "utilisateur4", "utilisateur5",
				"utilisateur6", "utilisateur7", "utilisateur8", "utilisateur9", "utilisateur10"
		);

		for (String username : usernames) {
			Utilisateur userOnDataBase = utilisateurDao.findByUsername(username);
			if (userOnDataBase == null) {
				saveUtilisateurs();
				saveCompetences();
				saveExperiences();
				saveLoisirs();
				saveSocialMedias();
				saveFormations();
				return; // Sort de la méthode après l'insertion des utilisateurs
			}
		}

		System.out.println("Tous les utilisateurs existent déjà dans la base de données.");
	}



	public void saveUtilisateurs() {
		List<Utilisateur> utilisateurs = Arrays.asList(
				Utilisateur.builder()
						.id(2L)
						.email("prisca@leila.com")
						.name("prisca")
						.password("priscaleila")
						.pays("CRM-COI")
						.profilePicture("profile2.jpg")
						.telephone("987654321")
						.username("leila")
						.ville("PARIS")
						.build(),
				Utilisateur.builder()
						.id(2L)
						.email("utilisateur2@example.com")
						.name("Utilisateur 2")
						.password("motdepasse2")
						.pays("USA")
						.profilePicture("profile2.jpg")
						.telephone("987654321")
						.username("utilisateur2")
						.ville("New York")
						.build(),
				Utilisateur.builder()
						.id(3L)
						.email("utilisateur3@example.com")
						.name("Utilisateur 3")
						.password("motdepasse3")
						.pays("Allemagne")
						.profilePicture("profile3.jpg")
						.telephone("567890123")
						.username("utilisateur3")
						.ville("Berlin")
						.build(),
				Utilisateur.builder()
						.id(4L)
						.email("utilisateur4@example.com")
						.name("Utilisateur 4")
						.password("motdepasse4")
						.pays("Japon")
						.profilePicture("profile4.jpg")
						.telephone("345678901")
						.username("utilisateur4")
						.ville("Tokyo")
						.build(),
				Utilisateur.builder()
						.id(5L)
						.email("utilisateur5@example.com")
						.name("Utilisateur 5")
						.password("motdepasse5")
						.pays("Canada")
						.profilePicture("profile5.jpg")
						.telephone("901234567")
						.username("utilisateur5")
						.ville("Toronto")
						.build(),
				Utilisateur.builder()
						.id(6L)
						.email("utilisateur6@example.com")
						.name("Utilisateur 6")
						.password("motdepasse6")
						.pays("Royaume-Uni")
						.profilePicture("profile6.jpg")
						.telephone("234567890")
						.username("utilisateur6")
						.ville("Londres")
						.build(),
				Utilisateur.builder()
						.id(7L)
						.email("utilisateur7@example.com")
						.name("Utilisateur 7")
						.password("motdepasse7")
						.pays("Espagne")
						.profilePicture("profile7.jpg")
						.telephone("654321098")
						.username("utilisateur7")
						.ville("Madrid")
						.build(),
				Utilisateur.builder()
						.id(8L)
						.email("utilisateur8@example.com")
						.name("Utilisateur 8")
						.password("motdepasse8")
						.pays("Australie")
						.profilePicture("profile8.jpg")
						.telephone("789012345")
						.username("utilisateur8")
						.ville("Sydney")
						.build(),
				Utilisateur.builder()
						.id(9L)
						.email("utilisateur9@example.com")
						.name("Utilisateur 9")
						.password("motdepasse9")
						.pays("Brésil")
						.profilePicture("profile9.jpg")
						.telephone("456789012")
						.username("utilisateur9")
						.ville("Rio de Janeiro")
						.build(),
				Utilisateur.builder()
						.id(10L)
						.email("utilisateur10@example.com")
						.name("Utilisateur 10")
						.password("motdepasse10")
						.pays("Chine")
						.profilePicture("profile10.jpg")
						.telephone("012345678")
						.username("utilisateur10")
						.ville("Pékin")
						.build()
		);

        utilisateurDao.saveAll(utilisateurs);
	}

	public void saveCompetences() {
		List<Competence> competences = Arrays.asList(
				Competence.builder()
						.utilisateur(Utilisateur.builder().id(1L).build())
						.competence("Programmation Java")
						.niveau(8)
						.build(),
				Competence.builder()
						.utilisateur(Utilisateur.builder().id(1L).build())
						.competence("Conception de base de données")
						.niveau(7)
						.build(),
				Competence.builder()
						.utilisateur(Utilisateur.builder().id(1L).build())
						.competence("Gestion de projet")
						.niveau(6)
						.build(),
				Competence.builder()
						.utilisateur(Utilisateur.builder().id(2L).build())
						.competence("Analyse des données")
						.niveau(9)
						.build(),
				Competence.builder()
						.utilisateur(Utilisateur.builder().id(2L).build())
						.competence("Résolution de problèmes")
						.niveau(8)
						.build(),
				Competence.builder()
						.utilisateur(Utilisateur.builder().id(2L).build())
						.competence("Communication efficace")
						.niveau(7)
						.build(),
				Competence.builder()
						.utilisateur(Utilisateur.builder().id(3L).build())
						.competence("Développement web")
						.niveau(8)
						.build(),
				Competence.builder()
						.utilisateur(Utilisateur.builder().id(3L).build())
						.competence("Systèmes d'exploitation")
						.niveau(7)
						.build(),
				Competence.builder()
						.utilisateur(Utilisateur.builder().id(3L).build())
						.competence("Gestion de version avec Git")
						.niveau(8)
						.build(),
				Competence.builder()
						.utilisateur(Utilisateur.builder().id(4L).build())
						.competence("Langage SQL")
						.niveau(9)
						.build()
		);

        competenceDao.saveAll(competences);
	}


	public void saveLoisirs() {
		List<Loisir> loisirs = Arrays.asList(
				Loisir.builder().utilisateur(Utilisateur.builder().id(1L).build()).hobby("Lecture").build(),
				Loisir.builder().utilisateur(Utilisateur.builder().id(1L).build()).hobby("Jogging").build(),
				Loisir.builder().utilisateur(Utilisateur.builder().id(1L).build()).hobby("Cuisine").build(),
				Loisir.builder().utilisateur(Utilisateur.builder().id(2L).build()).hobby("Voyages").build(),
				Loisir.builder().utilisateur(Utilisateur.builder().id(2L).build()).hobby("Photographie").build(),
				Loisir.builder().utilisateur(Utilisateur.builder().id(2L).build()).hobby("Peinture").build(),
				Loisir.builder().utilisateur(Utilisateur.builder().id(3L).build()).hobby("Musique").build(),
				Loisir.builder().utilisateur(Utilisateur.builder().id(3L).build()).hobby("Randonnée").build(),
				Loisir.builder().utilisateur(Utilisateur.builder().id(3L).build()).hobby("Jardinage").build(),
				Loisir.builder().utilisateur(Utilisateur.builder().id(4L).build()).hobby("Cinéma").build()
		);

		loisirDao.saveAll(loisirs);
	}

	public void saveSocialMedias() {
		List<SocialMedia> socialMedias = Arrays.asList(
				SocialMedia.builder().utilisateur(Utilisateur.builder().id(1L).build()).type("LinkedIn").lien("https://www.linkedin.com/in/utilisateur1").build(),
				SocialMedia.builder().utilisateur(Utilisateur.builder().id(1L).build()).type("Twitter").lien("https://twitter.com/utilisateur1").build(),
				SocialMedia.builder().utilisateur(Utilisateur.builder().id(1L).build()).type("GitHub").lien("https://github.com/utilisateur1").build(),
				SocialMedia.builder().utilisateur(Utilisateur.builder().id(2L).build()).type("LinkedIn").lien("https://www.linkedin.com/in/utilisateur2").build(),
				SocialMedia.builder().utilisateur(Utilisateur.builder().id(2L).build()).type("Instagram").lien("https://www.instagram.com/utilisateur2").build(),
				SocialMedia.builder().utilisateur(Utilisateur.builder().id(2L).build()).type("Facebook").lien("https://www.facebook.com/utilisateur2").build(),
				SocialMedia.builder().utilisateur(Utilisateur.builder().id(3L).build()).type("LinkedIn").lien("https://www.linkedin.com/in/utilisateur3").build(),
				SocialMedia.builder().utilisateur(Utilisateur.builder().id(3L).build()).type("YouTube").lien("https://www.youtube.com/utilisateur3").build(),
				SocialMedia.builder().utilisateur(Utilisateur.builder().id(3L).build()).type("Twitter").lien("https://twitter.com/utilisateur3").build(),
				SocialMedia.builder().utilisateur(Utilisateur.builder().id(4L).build()).type("LinkedIn").lien("https://www.linkedin.com/in/utilisateur4").build()
		);

		socialMediaDao.saveAll(socialMedias);
	}

	public void saveFormations() {
		List<Formation> formations = Arrays.asList(
				Formation.builder().utilisateur(Utilisateur.builder().id(1L).build()).diplome("Licence Informatique").domaineEtudes("Informatique").ecole("Université de Paris").lieu("Paris").anneeDebut(LocalDate.parse("2016-09-01")).anneeFin(LocalDate.parse("2019-06-30")).build(),
				Formation.builder().utilisateur(Utilisateur.builder().id(1L).build()).diplome("Certificat en Développement Web").domaineEtudes("Informatique").ecole("Coding Academy").lieu("Lyon").anneeDebut(LocalDate.parse("2020-01-15")).anneeFin(LocalDate.parse("2020-06-30")).build(),
				Formation.builder().utilisateur(Utilisateur.builder().id(2L).build()).diplome("Master en Sciences des Données").domaineEtudes("Data Science").ecole("Université de New York").lieu("New York").anneeDebut(LocalDate.parse("2018-09-01")).anneeFin(LocalDate.parse("2020-06-30")).build(),
				Formation.builder().utilisateur(Utilisateur.builder().id(2L).build()).diplome("Bachelor en Informatique").domaineEtudes("Informatique").ecole("Université de San Francisco").lieu("San Francisco").anneeDebut(LocalDate.parse("2015-09-01")).anneeFin(LocalDate.parse("2018-06-30")).build(),
				Formation.builder().utilisateur(Utilisateur.builder().id(3L).build()).diplome("Diplôme d'Ingénieur Informatique").domaineEtudes("Informatique").ecole("École Polytechnique de Berlin").lieu("Berlin").anneeDebut(LocalDate.parse("2017-09-01")).anneeFin(LocalDate.parse("2021-06-30")).build(),
				Formation.builder().utilisateur(Utilisateur.builder().id(3L).build()).diplome("Certificat en Python Avancé").domaineEtudes("Informatique").ecole("École de Programmation Madrid").lieu("Madrid").anneeDebut(LocalDate.parse("2020-10-15")).anneeFin(LocalDate.parse("2021-03-31")).build(),
				Formation.builder().utilisateur(Utilisateur.builder().id(4L).build()).diplome("Bachelor en Réseaux et Sécurité").domaineEtudes("Réseaux et Sécurité").ecole("Université de Tokyo").lieu("Tokyo").anneeDebut(LocalDate.parse("2016-09-01")).anneeFin(LocalDate.parse("2020-06-30")).build(),
				Formation.builder().utilisateur(Utilisateur.builder().id(4L).build()).diplome("Certificat en Cybersécurité").domaineEtudes("Cybersécurité").ecole("Institut de Sécurité London").lieu("London").anneeDebut(LocalDate.parse("2021-01-15")).anneeFin(LocalDate.parse("2021-06-30")).build(),
				Formation.builder().utilisateur(Utilisateur.builder().id(5L).build()).diplome("Master en Ingénierie Réseau").domaineEtudes("Réseaux Informatiques").ecole("Université de Sydney").lieu("Sydney").anneeDebut(LocalDate.parse("2017-09-01")).anneeFin(LocalDate.parse("2019-06-30")).build(),
				Formation.builder().utilisateur(Utilisateur.builder().id(5L).build()).diplome("Bachelor en Qualité Logicielle").domaineEtudes("Qualité Logicielle").ecole("Université de Toronto").lieu("Toronto").anneeDebut(LocalDate.parse("2020-09-01")).anneeFin(LocalDate.parse("2023-06-30")).build()
		);

		formationDao.saveAll(formations);
	}
	
	public void saveExperiences() {
		List<Experience> experiences = Arrays.asList(
				Experience.builder().utilisateur(Utilisateur.builder().id(1L).build()).titre("Développeur Java Junior").entreprise("Société Informatique Paris").lieu("Paris").dateDebut(LocalDate.parse("2019-07-01")).dateFin(LocalDate.parse("2020-06-30")).build(),
				Experience.builder().utilisateur(Utilisateur.builder().id(1L).build()).titre("Développeur Web Full Stack").entreprise("Startup Tech Lyon").lieu("Lyon").dateDebut(LocalDate.parse("2020-07-01")).dateFin(LocalDate.parse("2021-06-30")).build(),
				Experience.builder().utilisateur(Utilisateur.builder().id(2L).build()).titre("Data Analyst").entreprise("Entreprise Data New York").lieu("New York").dateDebut(LocalDate.parse("2020-07-01")).dateFin(LocalDate.parse("2021-06-30")).build(),
				Experience.builder().utilisateur(Utilisateur.builder().id(2L).build()).titre("Développeur Python Senior").entreprise("Startup Tech San Francisco").lieu("San Francisco").dateDebut(LocalDate.parse("2021-07-01")).dateFin(LocalDate.parse("2022-06-30")).build(),
				Experience.builder().utilisateur(Utilisateur.builder().id(3L).build()).titre("Ingénieur Logiciel").entreprise("Société Informatique Berlin").lieu("Berlin").dateDebut(LocalDate.parse("2021-07-01")).dateFin(LocalDate.parse("2022-06-30")).build(),
				Experience.builder().utilisateur(Utilisateur.builder().id(3L).build()).titre("Développeur Python Junior").entreprise("Startup Tech Madrid").lieu("Madrid").dateDebut(LocalDate.parse("2022-07-01")).dateFin(LocalDate.parse("2023-06-30")).build(),
				Experience.builder().utilisateur(Utilisateur.builder().id(4L).build()).titre("Ingénieur Réseau").entreprise("Société Informatique Tokyo").lieu("Tokyo").dateDebut(LocalDate.parse("2022-07-01")).dateFin(LocalDate.parse("2023-06-30")).build());

		experienceDao.saveAll(experiences);
	}

}
