package projet.aos.frontendappemployes.controllers;

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
import projet.aos.frontendappemployes.GestionDesEmployesApp;

public class RechercherEmployeController {
    @FXML
    TextField numero;

    @FXML
    protected void onAfficherEmployeClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesEmployesApp.class.getResource("afficher_employes.fxml"));

        try {
            String apiUrl = "http://localhost:8082/api/json/employes/" + numero.getText();
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
}
