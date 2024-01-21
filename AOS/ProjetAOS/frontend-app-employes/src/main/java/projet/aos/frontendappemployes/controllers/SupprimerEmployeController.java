package projet.aos.frontendappemployes.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SupprimerEmployeController {

    @FXML
    private TextField id;

    @FXML
    protected void onValiderClick() {
        try {
            String apiUrl = "http://localhost:8082/api/json/employes/" + id.getText();
            URL url = new URI(apiUrl).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configuration de la requête
            connection.setRequestMethod("DELETE");

            // Lire la réponse
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Employé supprimé avec succès.");
            } else {
                System.out.println("Erreur lors de la suppression de l'employé. Code de réponse : " + responseCode);
            }

            id.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
