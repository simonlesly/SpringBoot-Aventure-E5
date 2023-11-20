insert into qualite (bonus_qualite, couleur, nom) values (0,'grey','commun'),(1,'blue','rare'),(2,'pink','epique'),(3,'orange','légendaire');
insert into type_arme (limite_critique, max_des, mutiplicateur_critique, nbr_des, nom) values (18, 10, 2, 2, 'Épée'),(20, 8, 3, 1, 'Arc'),(15, 12, 1, 3, 'Hache');
insert into role (nom) values ('admin');
insert into role (nom) values ('joueur');
insert into utilisateur ( email, mdp) values ('admin@email.com','$2a$10$MJn5SDUCgsm3XEyvELGQI.lcCzCXtxhA8hunr9jX9yvDd6/FkjxYO');
insert into utilisateur_roles (roles_id, utilisateur_id) VALUES (1,1), (2,1);