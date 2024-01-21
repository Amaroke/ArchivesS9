package projet.aos.frontendappvehicules.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SupprimerVehiculeController {

    @FXML
    private TextField immatriculation;

    @FXML
    protected void onValiderClick() {
        try {
            String apiUrl = "http://localhost:8082/api/json/vehicules/" + immatriculation.getText();
            URL url = new URI(apiUrl).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configuration de la requête
            connection.setRequestMethod("DELETE");

            // Lire la réponse
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Véhicule supprimé avec succès.");
            } else {
                System.out.println("Erreur lors de la suppression du véhicule. Code de réponse : " + responseCode);
            }

            immatriculation.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
