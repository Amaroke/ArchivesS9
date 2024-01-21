package projet.aos.frontendappvehicules.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SupprimerTrainController {
    @FXML
    private TextField numero;

    @FXML
    private void onValiderClick() {
        String numeroTrain = numero.getText();

        try {
            ws.soap.train.Trainservice_Service service = new ws.soap.train.Trainservice_Service();
            ws.soap.train.Trainservice port = service.getTrainServicePort();
            port.deleteTrain(numeroTrain);
            numero.getScene().getWindow().hide();
        } catch (Exception ex) {
        }
    }

}
