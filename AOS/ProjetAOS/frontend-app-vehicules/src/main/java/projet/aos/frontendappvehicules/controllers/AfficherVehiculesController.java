package projet.aos.frontendappvehicules.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class AfficherVehiculesController {
    @FXML
    private Text vehicules;

    public void init(String vehicules) {
        this.vehicules.setText(vehicules);
    }
}
