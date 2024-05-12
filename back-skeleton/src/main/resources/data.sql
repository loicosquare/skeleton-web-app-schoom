-- -- Vider la table utilisateur
-- TRUNCATE TABLE utilisateur CASCADE;
--
-- -- Vider la table formation
-- TRUNCATE TABLE formation;
--
-- -- Vider la table experience
-- TRUNCATE TABLE experience;
--
-- -- Vider la table competence
-- TRUNCATE TABLE competence CASCADE;
--
-- -- Vider la table loisir
-- TRUNCATE TABLE loisir;
--
-- -- Vider la table social_media
-- TRUNCATE TABLE social_media;

-- Insertion des 10 utilisateurs.
INSERT INTO utilisateur (id, email, name, password, pays, profile_picture, telephone, username, ville) VALUES
(1, 'admin@takima.com', 'admin', 'admin', 'France', 'admin.png', '123456789', 'admin', 'Paris'),
(2, 'utilisateur2@example.com', 'Utilisateur 2', 'motdepasse2', 'USA', 'profile2.jpg', '987654321', 'utilisateur2', 'New York'),
(3, 'utilisateur3@example.com', 'Utilisateur 3', 'motdepasse3', 'Allemagne', 'profile3.jpg', '567890123', 'utilisateur3', 'Berlin'),
(4, 'utilisateur4@example.com', 'Utilisateur 4', 'motdepasse4', 'Japon', 'profile4.jpg', '345678901', 'utilisateur4', 'Tokyo'),
(5, 'utilisateur5@example.com', 'Utilisateur 5', 'motdepasse5', 'Canada', 'profile5.jpg', '901234567', 'utilisateur5', 'Toronto'),
(6, 'utilisateur6@example.com', 'Utilisateur 6', 'motdepasse6', 'Royaume-Uni', 'profile6.jpg', '234567890', 'utilisateur6', 'Londres'),
(7, 'utilisateur7@example.com', 'Utilisateur 7', 'motdepasse7', 'Espagne', 'profile7.jpg', '654321098', 'utilisateur7', 'Madrid'),
(8, 'utilisateur8@example.com', 'Utilisateur 8', 'motdepasse8', 'Australie', 'profile8.jpg', '789012345', 'utilisateur8', 'Sydney'),
(9, 'utilisateur9@example.com', 'Utilisateur 9', 'motdepasse9', 'Brésil', 'profile9.jpg', '456789012', 'utilisateur9', 'Rio de Janeiro'),
(10, 'utilisateur10@example.com', 'Utilisateur 10', 'motdepasse10', 'Chine', 'profile10.jpg', '012345678', 'utilisateur10', 'Pékin');

-- Insertion des 10 compétences.
INSERT INTO competence (id_utilisateur, competence, niveau) VALUES
(1, 'Programmation Java', 8),
(1, 'Conception de base de données', 7),
(1, 'Gestion de projet', 6),
(2, 'Analyse des données', 9),
(2, 'Résolution de problèmes', 8),
(2, 'Communication efficace', 7),
(3, 'Développement web', 8),
(3, 'Systèmes d''exploitation', 7),
(3, 'Gestion de version avec Git', 8),
(4, 'Langage SQL', 9);

-- Insertion des 10 formations.
INSERT INTO formation (id_utilisateur, diplome, domaine_etudes, ecole, lieu, annee_debut, annee_fin) VALUES
(1, 'Licence Informatique', 'Informatique', 'Université de Paris', 'Paris', '2016-09-01', '2019-06-30'),
(1, 'Certificat en Développement Web', 'Informatique', 'Coding Academy', 'Lyon', '2020-01-15', '2020-06-30'),
(2, 'Master en Sciences des Données', 'Data Science', 'Université de New York', 'New York', '2018-09-01', '2020-06-30'),
(2, 'Bachelor en Informatique', 'Informatique', 'Université de San Francisco', 'San Francisco', '2015-09-01', '2018-06-30'),
(3, 'Diplôme d''Ingénieur Informatique', 'Informatique', 'École Polytechnique de Berlin', 'Berlin', '2017-09-01', '2021-06-30'),
(3, 'Certificat en Python Avancé', 'Informatique', 'École de Programmation Madrid', 'Madrid', '2020-10-15', '2021-03-31'),
(4, 'Bachelor en Réseaux et Sécurité', 'Réseaux et Sécurité', 'Université de Tokyo', 'Tokyo', '2016-09-01', '2020-06-30'),
(4, 'Certificat en Cybersécurité', 'Cybersécurité', 'Institut de Sécurité London', 'London', '2021-01-15', '2021-06-30'),
(5, 'Master en Ingénierie Réseau', 'Réseaux Informatiques', 'Université de Sydney', 'Sydney', '2017-09-01', '2019-06-30'),
(5, 'Bachelor en Qualité Logicielle', 'Qualité Logicielle', 'Université de Toronto', 'Toronto', '2020-09-01', '2023-06-30');

-- Insertion des 10 loisirs.
INSERT INTO loisir (id_utilisateur, hobby) VALUES
(1, 'Lecture'),
(1, 'Jogging'),
(1, 'Cuisine'),
(2, 'Voyages'),
(2, 'Photographie'),
(2, 'Peinture'),
(3, 'Musique'),
(3, 'Randonnée'),
(3, 'Jardinage'),
(4, 'Cinéma');

-- Insertion des 10 médias sociaux.
INSERT INTO social_media (id_utilisateur, type, lien) VALUES
(1, 'LinkedIn', 'https://www.linkedin.com/in/utilisateur1'),
(1, 'Twitter', 'https://twitter.com/utilisateur1'),
(1, 'GitHub', 'https://github.com/utilisateur1'),
(2, 'LinkedIn', 'https://www.linkedin.com/in/utilisateur2'),
(2, 'Instagram', 'https://www.instagram.com/utilisateur2'),
(2, 'Facebook', 'https://www.facebook.com/utilisateur2'),
(3, 'LinkedIn', 'https://www.linkedin.com/in/utilisateur3'),
(3, 'YouTube', 'https://www.youtube.com/utilisateur3'),
(3, 'Twitter', 'https://twitter.com/utilisateur3'),
(4, 'LinkedIn', 'https://www.linkedin.com/in/utilisateur4');

-- Insertion des 10 expériences professionnelles.
INSERT INTO experience (id_utilisateur, poste, entreprise, lieu, annee_debut, annee_fin, description) VALUES
(1, 'Développeur Java', 'Takima', 'Paris', '2019-07-01', '2021-06-30', 'Développement de logiciels Java pour des clients internationaux.'),
(1, 'Stagiaire Développeur Web', 'Coding Academy', 'Lyon', '2020-01-15', '2020-06-30', 'Développement de sites web pour des clients locaux.'),
(2, 'Data Scientist', 'Google', 'Mountain View', '2020-07-01', '2021-06-30', 'Analyse des données pour améliorer les produits Google.'),
(2, 'Stagiaire Data Analyst', 'Facebook', 'Menlo Park', '2019-07-01', '2019-12-31', 'Analyse des données pour améliorer les produits Facebook.'),
(3, 'Ingénieur Logiciel', 'Amazon', 'Seattle', '2021-07-01', '2022-06-30', 'Développement de logiciels pour les services Amazon.'),
(3, 'Stagiaire Développeur Python', 'Microsoft', 'Redmond', '2020-10-15', '2021-03-31', 'Développement de scripts Python pour automatiser des tâches.'),
(4, 'Ingénieur Réseau', 'Cisco', 'San Jose', '2021-07-01', '2022-06-30', 'Configuration et maintenance des réseaux Cisco.'),
(4, 'Stagiaire Cybersécurité', 'IBM', 'Armonk', '2021-01-15', '2021-06-30', 'Analyse des vulnérabilités des systèmes informatiques.'),
(5, 'Ingénieur Réseau', 'Apple', 'Cupertino', '2021-07-01', '2022-06-30', 'Configuration et maintenance des réseaux Apple.'),
(5, 'Stagiaire Qualité Logicielle', 'Netflix', 'Los Gatos', '2020-09-01', '2020-12-31', 'Tests de qualité des logiciels Netflix.');
