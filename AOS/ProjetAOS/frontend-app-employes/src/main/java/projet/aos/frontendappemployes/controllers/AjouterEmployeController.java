package projet.aos.frontendappemployes.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AjouterEmployeController {

    @FXML
    private TextField motDePasse;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField numeroSecu;

    @FXML
    private TextField permis;

    @FXML
    private TextField adresse;

    @FXML
    protected void onValiderClick() {
        try {
            String apiUrl = "http://localhost:8082/api/json/employes";
            URL url = new URI(apiUrl).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configuration de la requête
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Création des données JSON
            String jsonInputString = "{"
                    + "\"motDePasse\": \"" + motDePasse.getText() + "\","
                    + "\"nom\": \"" + nom.getText() + "\","
                    + "\"prenom\": \"" + prenom.getText() + "\","
                    + "\"numeroSecuSociale\": \"" + numeroSecu.getText() + "\","
                    + "\"numeroPermis\": \"" + permis.getText() + "\","
                    + "\"adresseDomicile\": \"" + adresse.getText() + "\""
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

            adresse.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
