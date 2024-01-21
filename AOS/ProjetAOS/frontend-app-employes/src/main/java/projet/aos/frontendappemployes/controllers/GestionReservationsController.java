package projet.aos.frontendappemployes.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class GestionReservationsController {

    @FXML
    private Text reservations;

    @FXML
    private TextField id;

    public void init(String vehicules) {
        this.reservations.setText(vehicules);
    }

    @FXML
    protected void onValiderClick() {
        String id = this.id.getText();
        try {
            String apiUrl = "http://localhost:8082/api/json/emprunts/" + id + "/valider";
            URL url = new URI(apiUrl).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");

            StringBuilder response;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
            System.out.println(response.toString());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        reservations.getScene().getWindow().hide();
    }

    @FXML
    protected void onAnnulerClick() {
        String id = this.id.getText();
        try {
            String apiUrl = "http://localhost:8082/api/json/emprunts/" + id + "/annuler";
            URL url = new URI(apiUrl).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");

            StringBuilder response;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
            System.out.println(response.toString());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        reservations.getScene().getWindow().hide();
    }
}
