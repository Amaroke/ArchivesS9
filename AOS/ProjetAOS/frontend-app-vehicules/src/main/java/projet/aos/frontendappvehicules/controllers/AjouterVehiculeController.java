package projet.aos.frontendappvehicules.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AjouterVehiculeController implements Initializable {

    @FXML
    private TextField immatriculation;

    @FXML
    private ComboBox<String> type;

    @FXML
    private TextField marque;

    @FXML
    private TextField modele;

    @FXML
    private ComboBox<String> categorie;

    @FXML
    private RadioButton manuelle;

    @FXML
    private RadioButton automatique;

    @FXML
    private ComboBox<String> nbPlaces;

    @FXML
    private TextField description;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.getItems().addAll("Voiture", "Moto");
        categorie.getItems().addAll("Électrique", "Essence", "Hybride");
        nbPlaces.getItems().addAll("1", "2", "4", "5");
    }

    @FXML
    protected void onManuelleClick() {
        this.automatique.setSelected(false);
        this.manuelle.setSelected(true);
    }

    @FXML
    protected void onAutomatiqueClick() {
        this.manuelle.setSelected(false);
        this.automatique.setSelected(true);
    }

    @FXML
    protected void onValiderClick() {
        try {
            String apiUrl = "http://localhost:8082/api/json/vehicules";
            URL url = new URI(apiUrl).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configuration de la requête
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Création des données JSON
            String jsonInputString = "{"
                    + "\"immatriculation\": \"" + immatriculation.getText() + "\","
                    + "\"type\": \"" + type.getValue() + "\","
                    + "\"marque\": \"" + marque.getText() + "\","
                    + "\"modele\": \"" + modele.getText() + "\","
                    + "\"categorie\": \"" + categorie.getValue() + "\","
                    + "\"boiteDeVitesse\": \"" + (automatique.isSelected() ? "Automatique" : "Manuelle") + "\","
                    + "\"nbDePlaces\": " + nbPlaces.getValue() + ","
                    + "\"description\": \"" + description.getText() + "\""
                    + "}";

            // Écrire les données dans le corps de la requête
            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("UTF-8");
                outputStream.write(input, 0, input.length);
            }

            // Lire la réponse
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                System.out.println(response.toString());
            }

            description.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
