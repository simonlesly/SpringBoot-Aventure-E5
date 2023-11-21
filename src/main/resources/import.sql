insert into qualite (bonus_qualite, couleur, nom) values (0,'grey','commun'),(1,'blue','rare'),(2,'pink','epique'),(3,'orange','légendaire');
insert into type_arme (limite_critique, max_des, mutiplicateur_critique, nbr_des, nom) values (18, 10, 2, 2, 'Épée'),(20, 8, 3, 1, 'Arc'),(15, 12, 1, 3, 'Hache');
insert into role (nom) values ('admin');
insert into role (nom) values ('joueur');
insert into utilisateur ( email, mdp) values ('admin@email.com','$2a$10$MJn5SDUCgsm3XEyvELGQI.lcCzCXtxhA8hunr9jX9yvDd6/FkjxYO');
insert into utilisateur_roles (roles_id, utilisateur_id) VALUES (1,1), (2,1);


-- import.sql

-- Monstre 1
INSERT INTO Personnage (nom, attaque, defense, endurance, vitesse, point_de_vie,utilisateur_id) VALUES ('Goblin', 10, 5, 20, 15, 70,1);

-- Monstre 2
INSERT INTO Personnage (nom, attaque, defense, endurance, vitesse, point_de_vie,utilisateur_id) VALUES ('Dragon Rouge', 50, 30, 100, 40, 150,1);

-- Monstre 3
INSERT INTO Personnage (nom, attaque, defense, endurance, vitesse, point_de_vie,utilisateur_id) VALUES ('Sorcier des Ombres', 25, 15, 60, 25, 100,1);

-- Monstre 4
INSERT INTO Personnage (nom, attaque, defense, endurance, vitesse, point_de_vie, utilisateur_id) VALUES ('Orc Berserker', 35, 20, 80, 30, 120,1);

-- Monstre 5
INSERT INTO Personnage (nom, attaque, defense, endurance, vitesse, point_de_vie, utilisateur_id) VALUES ('Liche Maléfique', 40, 25, 90, 35, 110,1);

-- Monstre 6
INSERT INTO Personnage (nom, attaque, defense, endurance, vitesse, point_de_vie,utilisateur_id) VALUES ('Chimère Élémentaire', 55, 35, 120, 45, 180,1);
-- Hero 1
INSERT INTO Personnage (nom, attaque, defense, endurance, vitesse, point_de_vie,utilisateur_id) VALUES ('Paladin', 37, 45, 75, 30, 110,1);

INSERT INTO campagne (nom, date_maj, dernier_score, meilleur_score, statut, utilisateur_id,hero_id) VALUES ('Campagne1', '2023-01-01', 0, 0, 'En cours', 1,7);
INSERT INTO campagne (nom, date_maj, dernier_score, meilleur_score, statut, utilisateur_id,hero_id) VALUES ('Campagne2', '2023-02-01', 0, 0, 'En cours', 1,7);

insert into combat (nb_tour, nb_tour_min, campagne_id, monstre_id) VALUES (0,0,1,1),(0,0,1,2),(0,0,1,3);
insert into combat (nb_tour, nb_tour_min, campagne_id, monstre_id) VALUES (0,0,2,1),(0,0,2,2),(0,0,2,5),(0,0,2,6);