package projet.aos.frontendappvehicules.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AjouterTrainController implements Initializable {

    @FXML
    private ComboBox<String> depart;

    @FXML
    private ComboBox<String> arrivee;

    @FXML
    private TextField date;

    @FXML
    private TextField heure;

    @FXML
    private TextField prix;

    @FXML
    private TextField places;

    @FXML
    private void onValiderClick() {
        String villeDepart = depart.getValue();
        String villeArrivee = arrivee.getValue();
        String dateDepart = date.getText();
        String heureDepart = heure.getText();
        int placesDisponibles = Integer.parseInt(places.getText());
        double prixBillet = Double.parseDouble(prix.getText());

        String heureFormattee = heureDepart.replace(":", "");
        int heureInt = Integer.valueOf(heureFormattee);

        try {
            ws.soap.train.Trainservice_Service service = new ws.soap.train.Trainservice_Service();
            ws.soap.train.Trainservice port = service.getTrainServicePort();
            port.addTrain(villeDepart, villeArrivee, dateDepart, heureInt, prixBillet, placesDisponibles, "valide");
            places.getScene().getWindow().hide();
        } catch (Exception ex) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        depart.getItems().addAll("Lyon", "Paris", "Nice", "Nancy", "Marseille", "Metz");
        arrivee.getItems().addAll("Lyon", "Paris", "Nice", "Nancy", "Marseille", "Metz");
    }
}
