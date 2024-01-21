package projet.aos.frontendappvehicules.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projet.aos.frontendappvehicules.GestionDesTrainsApp;
import java.io.IOException;
import java.net.URISyntaxException;

@SuppressWarnings("CallToPrintStackTrace")
public class GestionDesTrainsController {

    @FXML
    protected void onAjouterTrainClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesTrainsApp.class.getResource("ajouter_train.fxml"));

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
    protected void onConsulterTrainClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesTrainsApp.class.getResource("afficher_trains.fxml"));

        try {
            Parent root = loader.load();

            AfficherTrainController controller = loader.getController();

            java.util.List<ws.soap.train.Train> result = null;
            Boolean isEmpty = false;

            try {
                ws.soap.train.Trainservice_Service service = new ws.soap.train.Trainservice_Service();
                ws.soap.train.Trainservice port = service.getTrainServicePort();
                result = port.getAllTrains();
            } catch (Exception ex) {
                isEmpty = true;
            }

            if (isEmpty) {
                controller.init("Aucun train ne sont enregistrés dans la base de données");
            } else {
                controller.init(result);
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
    protected void onModifierTrainClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesTrainsApp.class.getResource("modifier_train.fxml"));

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
    protected void onRechercherTrainClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesTrainsApp.class.getResource("rechercher_train.fxml"));

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
    protected void onSupprimerTrainClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesTrainsApp.class.getResource("supprimer_train.fxml"));

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
    protected void onAnnulerTrainClick() throws URISyntaxException {
        FXMLLoader loader = new FXMLLoader(GestionDesTrainsApp.class.getResource("annuler_train.fxml"));

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

}