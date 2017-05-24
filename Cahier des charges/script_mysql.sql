#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Utilisateur
#------------------------------------------------------------

CREATE TABLE Utilisateur(
        pseudoUtilisateur     Varchar (12) NOT NULL ,
        nomUtilisateur        Varchar (20) NOT NULL ,
        prenomUtilisateur     Varchar (20) NOT NULL ,
        sexeUtilisateur       Char (1) NOT NULL ,
        mailUtilisateur       Varchar (30) NOT NULL ,
        dateDerniereConnexion Date ,
        expUtilisateur        Int NOT NULL ,
        poidsUtilisateur      TinyINT ,
        tailleUtilisateur     TinyINT ,
        ageUtilisateur        TinyINT ,
        idNiveau              Int ,
        PRIMARY KEY (pseudoUtilisateur )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Programme
#------------------------------------------------------------

CREATE TABLE Programme(
        idProgramme          int (11) Auto_increment  NOT NULL ,
        nomProgramme         Varchar (25) NOT NULL ,
        descriptionProgramme Text ,
        PRIMARY KEY (idProgramme )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Exercice
#------------------------------------------------------------

CREATE TABLE Exercice(
        idExercice          int (11) Auto_increment  NOT NULL ,
        nomExercice         Varchar (25) NOT NULL ,
        difficulteExercice  TinyINT NOT NULL ,
        descriptionExercice Text ,
        experienceExercice  Int NOT NULL ,
        tempsExercice       Time ,
        risqueExercice      Text ,
        PRIMARY KEY (idExercice )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Muscle
#------------------------------------------------------------

CREATE TABLE Muscle(
        idMuscle  int (11) Auto_increment  NOT NULL ,
        nomMuscle Varchar (30) ,
        PRIMARY KEY (idMuscle )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: GroupeMusculaire
#------------------------------------------------------------

CREATE TABLE GroupeMusculaire(
        idGroupeMusculaire  int (11) Auto_increment  NOT NULL ,
        nomGroupeMusculaire Varchar (30) NOT NULL ,
        PRIMARY KEY (idGroupeMusculaire )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: sessionMulti
#------------------------------------------------------------

CREATE TABLE sessionMulti(
        idSessionMulti            int (11) Auto_increment  NOT NULL ,
        nomSessionMulti           Varchar (30) NOT NULL ,
        descriptionSession        Text NOT NULL ,
        difficulteSession         TinyINT ,
        dateFinInscriptionSession Datetime ,
        dateSession               Datetime NOT NULL ,
        dateFinSession            Datetime NOT NULL ,
        nombreDeParticipants      Int NOT NULL ,
        experienceSession         Int ,
        dateCreationSession       Datetime ,
        pseudoUtilisateur         Varchar (12) NOT NULL ,
        PRIMARY KEY (idSessionMulti )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Lieu
#------------------------------------------------------------

CREATE TABLE Lieu(
        idLieu         int (11) Auto_increment  NOT NULL ,
        coordonneesGPS Double NOT NULL ,
        PRIMARY KEY (idLieu )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Niveau
#------------------------------------------------------------

CREATE TABLE Niveau(
        idNiveau         Int NOT NULL ,
        nomNiveau        Varchar (20) NOT NULL ,
        experienceRequis Int NOT NULL ,
        PRIMARY KEY (idNiveau )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: muscle-groupemuscle
#------------------------------------------------------------

CREATE TABLE muscle_groupemuscle(
        idGroupeMusculaire Int NOT NULL ,
        idMuscle           Int NOT NULL ,
        PRIMARY KEY (idGroupeMusculaire ,idMuscle )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: programme-exercice
#------------------------------------------------------------

CREATE TABLE programme_exercice(
        nbRepetitions TinyINT ,
        idProgramme   Int NOT NULL ,
        idExercice    Int NOT NULL ,
        PRIMARY KEY (idProgramme ,idExercice )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: cible
#------------------------------------------------------------

CREATE TABLE cible(
        idExercice         Int NOT NULL ,
        idGroupeMusculaire Int NOT NULL ,
        PRIMARY KEY (idExercice ,idGroupeMusculaire )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: utilisateur-programme
#------------------------------------------------------------

CREATE TABLE utilisateur_programme(
        dateSeance        Date NOT NULL ,
        pseudoUtilisateur Varchar (12) NOT NULL ,
        idProgramme       Int NOT NULL ,
        PRIMARY KEY (pseudoUtilisateur ,idProgramme )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: utilisateur-exercice
#------------------------------------------------------------

CREATE TABLE utilisateur_exercice(
        dateExercice      Date NOT NULL ,
        pseudoUtilisateur Varchar (12) NOT NULL ,
        idExercice        Int NOT NULL ,
        PRIMARY KEY (pseudoUtilisateur ,idExercice )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: a_lieu
#------------------------------------------------------------

CREATE TABLE a_lieu(
        idLieu         Int NOT NULL ,
        idSessionMulti Int NOT NULL ,
        PRIMARY KEY (idLieu ,idSessionMulti )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: S'inscrire
#------------------------------------------------------------

CREATE TABLE S_inscrire(
        pseudoUtilisateur Varchar (12) NOT NULL ,
        idSessionMulti    Int NOT NULL ,
        PRIMARY KEY (pseudoUtilisateur ,idSessionMulti )
)ENGINE=InnoDB;

ALTER TABLE Utilisateur ADD CONSTRAINT FK_Utilisateur_idNiveau FOREIGN KEY (idNiveau) REFERENCES Niveau(idNiveau);
ALTER TABLE sessionMulti ADD CONSTRAINT FK_sessionMulti_pseudoUtilisateur FOREIGN KEY (pseudoUtilisateur) REFERENCES Utilisateur(pseudoUtilisateur);
ALTER TABLE muscle_groupemuscle ADD CONSTRAINT FK_muscle_groupemuscle_idGroupeMusculaire FOREIGN KEY (idGroupeMusculaire) REFERENCES GroupeMusculaire(idGroupeMusculaire);
ALTER TABLE muscle_groupemuscle ADD CONSTRAINT FK_muscle_groupemuscle_idMuscle FOREIGN KEY (idMuscle) REFERENCES Muscle(idMuscle);
ALTER TABLE programme_exercice ADD CONSTRAINT FK_programme_exercice_idProgramme FOREIGN KEY (idProgramme) REFERENCES Programme(idProgramme);
ALTER TABLE programme_exercice ADD CONSTRAINT FK_programme_exercice_idExercice FOREIGN KEY (idExercice) REFERENCES Exercice(idExercice);
ALTER TABLE cible ADD CONSTRAINT FK_cible_idExercice FOREIGN KEY (idExercice) REFERENCES Exercice(idExercice);
ALTER TABLE cible ADD CONSTRAINT FK_cible_idGroupeMusculaire FOREIGN KEY (idGroupeMusculaire) REFERENCES GroupeMusculaire(idGroupeMusculaire);
ALTER TABLE utilisateur_programme ADD CONSTRAINT FK_utilisateur_programme_pseudoUtilisateur FOREIGN KEY (pseudoUtilisateur) REFERENCES Utilisateur(pseudoUtilisateur);
ALTER TABLE utilisateur_programme ADD CONSTRAINT FK_utilisateur_programme_idProgramme FOREIGN KEY (idProgramme) REFERENCES Programme(idProgramme);
ALTER TABLE utilisateur_exercice ADD CONSTRAINT FK_utilisateur_exercice_pseudoUtilisateur FOREIGN KEY (pseudoUtilisateur) REFERENCES Utilisateur(pseudoUtilisateur);
ALTER TABLE utilisateur_exercice ADD CONSTRAINT FK_utilisateur_exercice_idExercice FOREIGN KEY (idExercice) REFERENCES Exercice(idExercice);
ALTER TABLE a_lieu ADD CONSTRAINT FK_a_lieu_idLieu FOREIGN KEY (idLieu) REFERENCES Lieu(idLieu);
ALTER TABLE a_lieu ADD CONSTRAINT FK_a_lieu_idSessionMulti FOREIGN KEY (idSessionMulti) REFERENCES sessionMulti(idSessionMulti);
ALTER TABLE S_inscrire ADD CONSTRAINT FK_S_inscrire_pseudoUtilisateur FOREIGN KEY (pseudoUtilisateur) REFERENCES Utilisateur(pseudoUtilisateur);
ALTER TABLE S_inscrire ADD CONSTRAINT FK_S_inscrire_idSessionMulti FOREIGN KEY (idSessionMulti) REFERENCES sessionMulti(idSessionMulti);
