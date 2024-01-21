package projet.aos.frontendappvehicules.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import projet.aos.frontendappvehicules.GestionDesVehiculesApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@SuppressWarnings("CallToPrintStackTrace")
public class GestionDesVehiculesController {

    @FXML
    private RadioButton XML;

    @FXML
    private RadioButton JSON;

    @FXML
    protected void initialize() {
        JSON.setSelected(true);
    }

    @FXML
    protected void onJSONClick() {
        this.XML.setSelected(false);
        this.JSON.setSelected(true);
    }

    @FXML
    protected void onXMLClick() {
        this.JSON.setSelected(false);
        this.XML.setSelected(true);
    }

    @FXML
    protected void onAfficherVehiculesClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesVehiculesApp.class.getResource("afficher_vehicules.fxml"));

        String format = JSON.isSelected() ? "json" : "xml";

        try {
            String apiUrl = "http://localhost:8082/api/" + format + "/vehicules";
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

            AfficherVehiculesController controller = loader.getController();

            if (format.equals("json")) {
                controller.init(response.toString().replace(",\"", ",\n\"").replace("},", "},\n"));
            } else {
                controller.init(response.toString().replace("><", ">\n<"));
            }
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onRechercherVehiculeClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesVehiculesApp.class.getResource("rechercher_vehicule.fxml"));

        try {
            Parent root = loader.load();

            RechercherVehiculeController controller = loader.getController();
            controller.init(JSON.isSelected());

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onAjouterVehiculeClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesVehiculesApp.class.getResource("ajouter_vehicule.fxml"));

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
    protected void onModifierVehiculeClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesVehiculesApp.class.getResource("modifier_vehicule.fxml"));

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
    protected void onSupprimerVehiculeClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesVehiculesApp.class.getResource("supprimer_vehicule.fxml"));

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
        FXMLLoader loader = new FXMLLoader(GestionDesVehiculesApp.class.getResource("gestion_reservations.fxml"));

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