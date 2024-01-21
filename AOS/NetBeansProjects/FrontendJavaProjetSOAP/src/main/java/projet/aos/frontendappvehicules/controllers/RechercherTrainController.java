package projet.aos.frontendappvehicules.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projet.aos.frontendappvehicules.GestionDesTrainsApp;

public class RechercherTrainController implements Initializable {
    @FXML
    private TextField numero;

    @FXML
    private ComboBox<String> depart;

    @FXML
    private ComboBox<String> arrivee;

    @FXML
    private TextField date;

    @FXML
    private TextField heure;

    @FXML
    private void onValiderClick() {
        String numeroTrain = numero.getText();
        String villeDepart = depart.getValue();
        String villeArrivee = arrivee.getValue();
        String dateDepart = date.getText();
        String heureDepart = heure.getText();

        String heureFormattee = heureDepart.replace(":", "");
        int heureInt = Integer.valueOf(heureFormattee);

         try {
        ws.soap.train.Trainservice_Service service = new ws.soap.train.Trainservice_Service();
        ws.soap.train.Trainservice port = service.getTrainServicePort();

        java.util.List<ws.soap.train.Train> searchResult = port.searchTrainsAdmin(numeroTrain, villeDepart, villeArrivee, dateDepart, heureInt);

        FXMLLoader loader = new FXMLLoader(GestionDesTrainsApp.class.getResource("afficher_trains.fxml"));
        Parent root = loader.load();

        AfficherTrainController controller = loader.getController();

        if (searchResult.isEmpty()) {
            controller.init("Aucun train correspondant aux critères de recherche.");
        } else {
            controller.init(searchResult);
        }

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    } catch (Exception ex) {
        ex.printStackTrace(); 
    }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        depart.getItems().addAll("Lyon", "Paris", "Nice", "Nancy", "Marseille", "Metz");
        arrivee.getItems().addAll("Lyon", "Paris", "Nice", "Nancy", "Marseille", "Metz");
    }
}
