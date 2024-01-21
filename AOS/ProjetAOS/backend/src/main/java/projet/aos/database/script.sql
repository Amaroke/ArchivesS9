DROP DATABASE IF EXISTS aos;

CREATE DATABASE aos;

USE aos;

CREATE TABLE IF NOT EXISTS Vehicules (
    immatriculation VARCHAR(10) PRIMARY KEY,
    type_vehicule VARCHAR(20) NOT NULL,
    marque VARCHAR(50) NOT NULL,
    modele VARCHAR(50) NOT NULL,
    categorie_vehicule VARCHAR(20) NOT NULL,
    boite_vitesse VARCHAR(15) NOT NULL,
    nombre_places INT NOT NULL,
    description TEXT
    );

CREATE TABLE IF NOT EXISTS Employes (
    numero_membre INT PRIMARY KEY AUTO_INCREMENT,
    mot_de_passe VARCHAR(255) NOT NULL,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    numero_secu_sociale VARCHAR(15) NOT NULL,
    numero_permis VARCHAR(20) NOT NULL,
    adresse_domicile TEXT NOT NULL
    );

CREATE TABLE IF NOT EXISTS Emprunts (
    id INT PRIMARY KEY,
    immatriculation VARCHAR(10) NOT NULL,
    numero_membre INT NOT NULL,
    date_pret DATE NOT NULL,
    etat VARCHAR(20) CHECK (etat IN ('reserve', 'confirme', 'rendu')),
    CONSTRAINT fk_emprunt_vehicule FOREIGN KEY (immatriculation) REFERENCES Vehicules(immatriculation),
    CONSTRAINT fk_emprunt_employe FOREIGN KEY (numero_membre) REFERENCES Employes(numero_membre)
    );

INSERT INTO Vehicules (immatriculation, type_vehicule, marque, modele, categorie_vehicule, boite_vitesse, nombre_places, description)
VALUES
    ('AA-001-AA', 'voiture', 'Tesla', 'Model S', 'électrique', 'automatique', 5, 'Bonne condition, avec option autopilote'),
    ('BB-002-BB', 'voiture', 'Toyota', 'Camry', 'essence', 'automatique', 5, 'Faible kilométrage, climatisation'),
    ('CC-003-CC', 'moto', 'Harley-Davidson', 'LiveWire', 'électrique', 'automatique', 2, 'Moto électrique puissante'),
    ('DD-004-DD', 'voiture', 'Honda', 'Civic', 'hybride', 'automatique', 5, 'Excellent état, économique en carburant'),
    ('EE-005-EE', 'voiture', 'Ford', 'Mustang', 'essence', 'manuelle', 4, 'Style sportif, moteur puissant'),
    ('FF-006-FF', 'moto', 'BMW', 'R1250GS', 'essence', 'manuelle', 2, 'Moto tout-terrain, idéale pour les voyages'),
    ('GG-007-GG', 'voiture', 'Nissan', 'Leaf', 'électrique', 'automatique', 5, 'Véhicule écologique, autonomie élevée'),
    ('HH-008-HH', 'voiture', 'Chevrolet', 'Equinox', 'essence', 'automatique', 5, 'Spacieux, confortable pour les longs trajets'),
    ('II-009-II', 'moto', 'Ducati', 'Monster', 'essence', 'manuelle', 2, 'Moto sportive, design moderne'),
    ('JJ-010-JJ', 'voiture', 'Volkswagen', 'Golf', 'hybride', 'automatique', 5, 'Compacte et écoénergétique');

INSERT INTO Employes (numero_membre, mot_de_passe, nom, prenom, numero_secu_sociale, numero_permis, adresse_domicile)
VALUES
    (1, 'motdepasse1', 'Musk', 'Elon', '278014512345678', '185739204617', '123 Main Street'),
    (2, 'motdepasse2', 'Gates', 'Bill', '285123456789012', '629347815032', '456 Oak Avenue'),
    (3, 'motdepasse3', 'Bezos', 'Jeff', '293098765432145', '504982631247', '789 Pine Road'),
    (4, 'motdepasse4', 'Zuckerberg', 'Mark', '270076543210987', '742159306428', '101 Cedar Lane'),
    (5, 'motdepasse5', 'Cook', 'Tim', '266065432109876', '408927536124', '202 Birch Street'),
    (6, 'motdepasse6', 'Gates', 'Melinda', '282032198765432', '316275840932', '303 Maple Avenue'),
    (7, 'motdepasse7', 'Jobs', 'Steve', '289109876543210', '572631094583', '404 Pine Lane'),
    (8, 'motdepasse8', 'Wozniak', 'Steve', '276111122233344', '920357146823', '505 Oak Road'),
    (9, 'motdepasse9', 'Brin', 'Sergey', '284151155566677', '685713092476', '606 Cedar Avenue'),
    (10, 'motdepasse10', 'Page', 'Larry', '281181188889900', '159374820563', '707 Birch Road');

INSERT INTO Emprunts (id, immatriculation, numero_membre, date_pret, etat)
VALUES
    (1, 'AA-001-AA', 1, '2023-01-01', 'confirme'),
    (2, 'BB-002-BB', 2, '2023-02-15', 'reserve'),
    (3, 'CC-003-CC', 3, '2023-03-30', 'confirme'),
    (4, 'DD-004-DD', 4, '2023-04-10', 'reserve'),
    (5, 'EE-005-EE', 5, '2023-05-20', 'confirme'),
    (6, 'FF-006-FF', 6, '2023-06-05', 'confirme'),
    (7, 'GG-007-GG', 7, '2023-07-15', 'reserve'),
    (8, 'HH-008-HH', 8, '2023-08-30', 'confirme'),
    (9, 'II-009-II', 9, '2023-09-10', 'reserve'),
    (10, 'JJ-010-JJ', 10, '2023-10-25', 'confirme');
