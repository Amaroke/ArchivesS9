package ws.soap.reservation;

import java.io.Serializable;

public class Reservation implements Serializable {
    private int numeroReservation;  // Correspond au champ NumeroReservation dans la table Reservations
    private int numeroTrain;  // Correspond au champ NumeroTrain dans la table Reservations
    private int numeroClient;  // Correspond au champ NumeroClient dans la table Reservations
    private int numeroPlace;  // Correspond au champ NumeroPlace dans la table Reservations
    private String trainInfo;  // New field to store relevant train information as a string

    public Reservation(int numeroReservation, int numeroTrain, int numeroClient, int numeroPlace, String trainInfo) {
        this.numeroReservation = numeroReservation;
        this.numeroTrain = numeroTrain;
        this.numeroClient = numeroClient;
        this.numeroPlace = numeroPlace;
        this.trainInfo = trainInfo;
    }

    // Add getters and setters for the new field
    public String getTrainInfo() {
        return trainInfo;
    }

    public void setTrainInfo(String trainInfo) {
        this.trainInfo = trainInfo;
    }

    // Existing getters and setters remain unchanged
    public int getNumeroReservation() {
        return numeroReservation;
    }

    public void setNumeroReservation(int numeroReservation) {
        this.numeroReservation = numeroReservation;
    }

    public int getNumeroTrain() {
        return numeroTrain;
    }

    public void setNumeroTrain(int numeroTrain) {
        this.numeroTrain = numeroTrain;
    }

    public int getNumeroClient() {
        return numeroClient;
    }

    public void setNumeroClient(int numeroClient) {
        this.numeroClient = numeroClient;
    }

    public int getNumeroPlace() {
        return numeroPlace;
    }

    public void setNumeroPlace(int numeroPlace) {
        this.numeroPlace = numeroPlace;
    }
}
