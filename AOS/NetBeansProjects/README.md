# Mise en place du projet

## Prérequis

- [MySQL](https://www.mysql.com/fr/)
- [NetBeans](https://netbeans.apache.org/front/main/)
- [Java](https://www.java.com/fr/download/)
- [Maven](https://maven.apache.org/download.cgi)

## Installation et lancement

### Base de données

- Créer une instance de MySQL (sous Docker ou directement sur votre PC accessible via `localhost:3306`, port par défaut de mySQL).
- Importer le fichier `script.sql` dans la base de données. Disponible sous `backend/src/main/java/projet/aos/database/script.sql`.
- Il doit y avoir un utilisateur `root` avec le mot de passe `root`.

### Backend

- Importer les trois projets dans Netbeans.
- Clean and Build les trois projets.
- Deploy le backend.

### Frontend Web (HTML/CSS)

- Pour exécuter le projet, on "Run file les .html".

### Frontend App (javaFX)

- Pour exécuter le projet : `mvn clean javafx:run`

Pour tous les détails concernant les fonctionnalités, veuillez vous référer au rapport.