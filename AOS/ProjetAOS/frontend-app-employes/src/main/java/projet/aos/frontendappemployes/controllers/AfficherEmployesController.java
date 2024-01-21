package projet.aos.frontendappemployes.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class AfficherEmployesController {
    @FXML
    private Text employes;

    public void init(String employes) {
        this.employes.setText(employes);
    }
}
