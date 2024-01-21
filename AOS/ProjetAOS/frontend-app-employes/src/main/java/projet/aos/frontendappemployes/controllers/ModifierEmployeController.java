package projet.aos.frontendappemployes.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ModifierEmployeController {

    @FXML
    private TextField id;

    @FXML
    private TextField motDePasse;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField permis;

    @FXML
    private TextField adresse;

    @FXML
    protected void onValiderClick() {
        try {
            String apiUrl = "http://localhost:8082/api/json/employes/" + id.getText();
            URL url = new URI(apiUrl).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configuration de la requête
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Création des données JSON pour la mise à jour
            String jsonInputString = "{"
                    + "\"motDePasse\": \"" + motDePasse.getText() + "\","
                    + "\"nom\": \"" + nom.getText() + "\","
                    + "\"prenom\": \"" + prenom.getText() + "\","
                    + "\"numeroPermis\": \"" + permis.getText() + "\","
                    + "\"adresseDomicile\": \"" + adresse.getText() + "\""
                    + "}";

            // Écrire les données dans le corps de la requête
            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("UTF-8");
                outputStream.write(input, 0, input.length);
            }

            // Lire la réponse
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Employé mis à jour avec succès.");
            } else {
                System.out.println("Erreur lors de la mise à jour de l'employé. Code de réponse : " + responseCode);
            }

            adresse.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
