# Mise en place du projet

## Prérequis

- [MySQL](https://www.mysql.com/fr/)
- [Node.js](https://nodejs.org/en/)
- [Tomcat](https://tomcat.apache.org/download-90.cgi)
- [IntelliJ](https://www.jetbrains.com/fr-fr/idea/download)
- [Java](https://www.java.com/fr/download/)
- [Maven](https://maven.apache.org/download.cgi)

## Installation et lancement

### Base de données

- Créer une instance de MySQL (sous Docker ou directement sur votre PC accessible via `localhost:3306`, port par défaut de mySQL).
- Importer le fichier `script.sql` dans la base de données. Disponible sous `backend/src/main/java/projet/aos/database/script.sql`.
- Il doit y avoir un utilisateur `root` avec le mot de passe `root`.

### Backend

- Avoir tomcat installé sur sa machine.
- Importer le projet `backend` dans votre IDE (IntelliJ).
- Configurer le projet pour utiliser Tomcat, comme indiqué dans le sujet, en retirant le `/war` de la configuration. De manière à ce que les routes soit toutes sous la forme `localhost:8080/api/...`.
- Lancer le projet.
- Le backend est accessible via `localhost:8080/api/...`.
- Les exemples de requêtes sont disponibles dans ma documentation Postman, accessible [ici](https://documenter.getpostman.com/view/20927448/2s9YsGgshR).
- Les requêtes permettent de récupérer les données au format JSON ou XML, lorsque cela est possible et utilisable.

### Frontend Web (Angular & Tailwind CSS)

- Avoir Node.js installé sur sa machine.
- Se placer dans le dossier `frontend-web`.
- Lancer la commande `npm install` pour installer les dépendances.
- Lancer la commande `npm start` pour lancer le serveur de développement.
- Le frontend est accessible via `localhost:4200`.

### Frontend App (javaFX) - Employés

- Importer le projet `frontend-app-employes` dans votre IDE (IntelliJ).
- Lancer le projet.
- Le frontend est accessible via l'application javaFX.

### Frontend App (javaFX) - Vehicules

- Importer le projet `frontend-app-vehicules` dans votre IDE (IntelliJ).
- Lancer le projet.
- Le frontend est accessible via l'application javaFX.

## Utilisation

### Frontend Web (Angular & Tailwind CSS)

- Pour exécuter le projet : `npm run start`

### Frontend App (javaFX) - Employés

- Pour exécuter le projet : `mvn clean javafx:run`

### Frontend App (javaFX) - Vehicules

- Pour exécuter le projet : `mvn clean javafx:run`

Pour tous les détails concernant les fonctionnalités, veuillez vous référer au rapport.