package projet.aos.frontendappvehicules.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class AfficherTrainController {
    @FXML
    private Text trains;

    public void init(java.util.List<ws.soap.train.Train> trains) {
        StringBuilder trainInfo = new StringBuilder();

        for (ws.soap.train.Train train : trains) {
            trainInfo.append("Numéro du train : ").append(train.getNumber()).append("\n");
            trainInfo.append("Ville de départ : ").append(train.getDepartureCity()).append("\n");
            trainInfo.append("Ville d'arrivée : ").append(train.getArrivalCity()).append("\n");
            trainInfo.append("Date : ").append(train.getDate()).append("\n");
            trainInfo.append("Heure de départ : ").append(train.getDepartureTime()).insert(trainInfo.length() - 2, ":")
                    .append("\n");
            trainInfo.append("Prix du billet : ").append(train.getTicketPrice()).append("€\n");
            trainInfo.append("Places disponibles : ").append(train.getAvailableSeats()).append("\n");
            trainInfo.append("État : ").append(train.getState()).append("\n");
            trainInfo.append("-----------------------------------------").append("\n");
        }

        this.trains.setText(trainInfo.toString());
    }

    public void init(String empty) {
        this.trains.setText(empty);
    }
}
