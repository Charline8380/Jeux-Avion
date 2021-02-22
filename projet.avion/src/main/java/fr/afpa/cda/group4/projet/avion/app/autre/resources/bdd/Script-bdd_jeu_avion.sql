-- Database: jeu_avion


--drop table jouer;
--drop table joueur;
--drop table score ;


create table joueur (
id_joueur serial,
nom_profil varchar,
mot_de_passe varchar,
primary key (id_joueur));


create table score (
id_score serial,
nb_points_score integer,
primary key (id_score));


create table jouer (
id_jouer serial,
id_joueur integer not null,
id_score integer not null,
date_jeu timestamp,
primary key (id_jouer,id_joueur,id_score));


alter table jouer add constraint FK_jouer_id_joueur foreign key (id_joueur) references joueur (id_joueur);
alter table jouer add constraint FK_jouer_id_score foreign key (id_score) references score (id_score);

/** CREATION DU JEU DE DONNEES POUR TEST**/

--fixer une taille de string pour les noms de profil pour l'affichage
INSERT INTO joueur (nom_profil, mot_de_passe) VALUES('Laurent   ','laurent59');
INSERT INTO joueur (nom_profil, mot_de_passe) VALUES('Mireille  ','mireille59');
INSERT INTO joueur (nom_profil, mot_de_passe) VALUES('Laura     ','laura59');
INSERT into joueur (nom_profil, mot_de_passe) VALUES('Rachel    ','rachel59');
INSERT into joueur (nom_profil, mot_de_passe) VALUES('Toto      ','toto75');
commit;


INSERT into score (nb_points_score) VALUES(0);
INSERT into score (nb_points_score) VALUES(30);
INSERT into score (nb_points_score) VALUES(100);
INSERT into score (nb_points_score) VALUES(879);
INSERT into score (nb_points_score) VALUES(222);
INSERT into score (nb_points_score) VALUES(500);
commit;

INSERT INTO jouer (id_joueur, id_score, date_jeu) VALUES(01,06,'11/04/2020 12:25:56');
INSERT INTO jouer (id_joueur, id_score, date_jeu) VALUES(02,05,'15/04/2020 15:30:00');
INSERT INTO jouer (id_joueur, id_score, date_jeu) VALUES(03,03,'18/04/2020 08:21:36');
INSERT INTO jouer (id_joueur, id_score, date_jeu) VALUES(04,04,'02/04/2020 13:25:56');
INSERT INTO jouer (id_joueur, id_score, date_jeu) VALUES(05,02,'12/04/2020 16:45:20');
commit;

