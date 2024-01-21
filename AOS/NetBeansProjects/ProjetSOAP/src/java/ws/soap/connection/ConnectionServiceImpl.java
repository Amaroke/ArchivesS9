/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package ws.soap.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.jws.WebService;
import ws.soap.train.DBConnection;

/**
 *
 * @author hugoa
 */
@WebService(serviceName = "ConnectionService")
public class ConnectionServiceImpl implements ConnectionService {

    private Connection connection;

    public ConnectionServiceImpl() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public boolean Login(String email, String password) {
        try {
            String sql = "SELECT * FROM Clients WHERE Email = ? AND MotDePasse = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                statement.setString(2, password);

                ResultSet resultSet = statement.executeQuery();

                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean createReservation(String trainNumber, String email) {
        try {
            // Obtenir le numéro du client à partir de l'email
            String clientSql = "SELECT NumeroClient FROM Clients WHERE Email = ?";
            try (PreparedStatement clientStatement = connection.prepareStatement(clientSql)) {
                clientStatement.setString(1, email);
                ResultSet clientResultSet = clientStatement.executeQuery();

                // Vérifier si le client existe
                if (clientResultSet.next()) {
                    int numeroClient = clientResultSet.getInt("NumeroClient");

                    // Créer la réservation
                    String reservationSql = "INSERT INTO Reservations (NumeroTrain, NumeroClient, NumeroPlace) VALUES (?, ?, ?)";
                    try (PreparedStatement reservationStatement = connection.prepareStatement(reservationSql)) {
                        reservationStatement.setInt(1, Integer.parseInt(trainNumber));
                        reservationStatement.setInt(2, numeroClient);
                        reservationStatement.setInt(3, new Random().nextInt(10));

                        int rowsAffected = reservationStatement.executeUpdate();

                        return rowsAffected > 0;
                    }
                } else {
                    // Le client n'existe pas
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
