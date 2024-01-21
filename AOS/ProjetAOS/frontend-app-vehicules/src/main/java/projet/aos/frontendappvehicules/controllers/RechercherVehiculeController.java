package projet.aos.frontendappvehicules.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projet.aos.frontendappvehicules.GestionDesVehiculesApp;

public class RechercherVehiculeController {
    private boolean json;

    @FXML
    TextField immatriculation;

    public void init(Boolean json) {
        this.json = json;
    }

    @FXML
    protected void onAfficherVehiculeClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesVehiculesApp.class.getResource("afficher_vehicules.fxml"));

        String format = json ? "json" : "xml";

        try {
            String apiUrl = "http://localhost:8082/api/" + format + "/vehicules/" + immatriculation.getText();
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
}
