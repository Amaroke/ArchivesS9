package projet.aos.frontendappemployes.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projet.aos.frontendappemployes.GestionDesEmployesApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@SuppressWarnings("CallToPrintStackTrace")
public class GestionDesEmployesController {

    @FXML
    protected void onAfficherEmployesClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesEmployesApp.class.getResource("afficher_employes.fxml"));

        try {
            String apiUrl = "http://localhost:8082/api/json/employes";
            URL url = new URI(apiUrl).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            StringBuilder response;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            Parent root = loader.load();

            AfficherEmployesController controller = loader.getController();

            controller.init(response.toString().replace(",\"", ",\n\"").replace("},", "},\n"));

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onRechercherEmployeClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesEmployesApp.class.getResource("rechercher_employe.fxml"));

        try {
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onAjouterEmployeClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesEmployesApp.class.getResource("ajouter_employe.fxml"));

        try {
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onModifierEmployeClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesEmployesApp.class.getResource("modifier_employe.fxml"));

        try {
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onSupprimerEmployeClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesEmployesApp.class.getResource("supprimer_employe.fxml"));

        try {
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onValiderReservationsClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesEmployesApp.class.getResource("gestion_reservations.fxml"));

        try {
            Parent root = loader.load();

            GestionReservationsController controller = loader.getController();

            // Get reservations
            String apiUrl = "http://localhost:8082/api/json/emprunts";
            URL url = new URI(apiUrl).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            StringBuilder response;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            controller.init(response.toString().replace(",\"", ",\n\"").replace("},", "},\n"));

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}