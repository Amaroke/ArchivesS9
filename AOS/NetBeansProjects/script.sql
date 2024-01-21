DROP DATABASE IF EXISTS aos;

CREATE DATABASE aos;

USE aos;

CREATE TABLE Trains (
    NumeroTrain INT PRIMARY KEY AUTO_INCREMENT,
    VilleDepart VARCHAR(255),
    VilleArrivee VARCHAR(255),
    DateDepart DATE,
    HeureDepart INT CHECK (HeureDepart >= 0 AND HeureDepart <= 2359),
    PrixBillet DECIMAL(10, 2),
    PlacesDisponibles INT,
    Etat VarChar(20)
);

CREATE TABLE Clients (
    NumeroClient INT PRIMARY KEY AUTO_INCREMENT,
    Nom VARCHAR(255),
    Prenom VARCHAR(255),
    Email VARCHAR(255) UNIQUE,
    MotDePasse VARCHAR(255)
);

CREATE TABLE Reservations (
    NumeroReservation INT PRIMARY KEY AUTO_INCREMENT,
    NumeroTrain INT,
    NumeroClient INT,
    NumeroPlace INT,
    CONSTRAINT fk_train FOREIGN KEY (NumeroTrain) REFERENCES Trains(NumeroTrain),
    CONSTRAINT fk_client FOREIGN KEY (NumeroClient) REFERENCES Clients(NumeroClient)
);

INSERT INTO Trains (NumeroTrain, VilleDepart, VilleArrivee, DateDepart, HeureDepart, PrixBillet, PlacesDisponibles, Etat)
VALUES
    (1, 'Paris', 'Lyon', '2024-02-04', 1000, 48.20, 90, 'valide'),
    (2, 'Lyon', 'Nice', '2024-02-05', 1300, 42.80, 110, 'valide'),
    (3, 'Paris', 'Marseille', '2024-02-06', 1500, 53.50, 70, 'valide'),
    (4, 'Paris', 'Nancy', '2024-02-07', 1130, 57.90, 85, 'valide'),
    (5, 'Metz', 'Paris', '2024-02-08', 1030, 49.75, 95, 'valide'),
    (6, 'Nice', 'Lyon', '2024-02-09', 1330, 44.60, 105, 'valide'),
    (7, 'Marseille', 'Paris', '2024-02-10', 1200, 51.30, 75, 'annule'),
    (8, 'Nancy', 'Metz', '2024-02-11', 1400, 46.90, 100, 'valide'),
    (9, 'Paris', 'Nice', '2024-02-12', 1230, 54.25, 80, 'valide'),
    (10, 'Nancy', 'Nice', '2024-02-13', 1100, 58.00, 88, 'annule'),
    (11, 'Lyon', 'Marseille', '2024-02-14', 1430, 47.80, 92, 'valide'),
    (12, 'Nice', 'Nancy', '2024-02-15', 1200, 52.50, 78, 'valide'),
    (13, 'Marseille', 'Paris', '2024-02-16', 1030, 56.20, 87, 'valide'),
    (14, 'Nancy', 'Lyon', '2024-02-17', 1130, 48.90, 105, 'valide'),
    (15, 'Paris', 'Metz', '2024-02-18', 1330, 43.75, 98, 'valide'),
    (16, 'Paris', 'Nice', '2024-02-19', 1400, 50.60, 82, 'valide'),
    (17, 'Lyon', 'Marseille', '2024-02-20', 1230, 55.30, 89, 'valide'),
    (18, 'Nice', 'Nancy', '2024-02-21', 1100, 59.00, 94, 'valide'),
    (19, 'Marseille', 'Paris', '2024-02-22', 1300, 46.20, 101, 'annule'),
    (20, 'Nancy', 'Lyon', '2024-02-23', 1130, 51.90, 80, 'valide'),
    (21, 'Paris', 'Lyon', '2024-02-24', 1400, 55.60, 75, 'valide'),
    (22, 'Lyon', 'Nice', '2024-02-25', 1200, 43.80, 110, 'valide'),
    (23, 'Paris', 'Marseille', '2024-02-26', 1130, 52.50, 70, 'valide'),
    (24, 'Paris', 'Nancy', '2024-02-27', 1330, 57.90, 85, 'valide'),
    (25, 'Metz', 'Paris', '2024-02-28', 1030, 49.75, 95, 'valide'),
    (26, 'Nice', 'Lyon', '2024-02-29', 1230, 44.60, 105, 'valide'),
    (27, 'Marseille', 'Paris', '2024-03-01', 1300, 51.30, 75, 'annule'),
    (28, 'Nancy', 'Metz', '2024-03-02', 1500, 46.90, 100, 'valide'),
    (29, 'Paris', 'Nice', '2024-03-03', 1200, 54.25, 80, 'valide'),
    (30, 'Nancy', 'Nice', '2024-03-04', 1030, 58.00, 88, 'annule');


INSERT INTO Clients (NumeroClient, Nom, Prenom, Email, MotDePasse)
VALUES
    (1, 'Jax', 'Darius', 'pierre.dupont@example.com', 'mdp123'),
    (2, 'Zoe', 'Zac', 'sophie.martin@example.com', 'pass456'),
    (3, 'Lefevre', 'Jean', 'jean.lefevre@example.com', 'secret789'),
    (4, 'Dubois', 'Marie', 'marie.dubois@example.com', 'mdp123'),
    (5, 'Bertrand', 'Nicolas', 'nicolas.bertrand@example.com', 'pass456'),
    (6, 'Roux', 'Isabelle', 'isabelle.roux@example.com', 'secret789'),
    (7, 'Lemoine', 'Luc', 'luc.lemoine@example.com', 'mdp123'),
    (8, 'Renard', 'Catherine', 'catherine.renard@example.com', 'pass456'),
    (9, 'Meyer', 'Anne', 'anne.meyer@example.com', 'secret789'),
    (10, 'Leroux', 'Marc', 'marc.leroux@example.com', 'mdp123');

INSERT INTO Reservations (NumeroReservation, NumeroTrain, NumeroClient, NumeroPlace)
VALUES
    (1, 1, 1, 15),
    (2, 2, 2, 20),
    (3, 3, 3, 10),
    (4, 4, 4, 18),
    (5, 5, 5, 25),
    (6, 6, 6, 13),
    (7, 7, 7, 22),
    (8, 8, 8, 17),
    (9, 9, 9, 14),
    (10, 10, 10, 19);