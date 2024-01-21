/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ws.soap.train;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hugoa
 */
public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    private DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/aos?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false",
                    "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'initialisation de la connexion à la base de données.");
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    // Pour tester la connexion indépendament
    public static void main(String[] args) {
        try {
            DBConnection dbConnection = DBConnection.getInstance();
            Connection connection = dbConnection.getConnection();
            List<Train> trainlist = new ArrayList<>();
            if (connection != null && !connection.isClosed()) {
                System.out.println("Connexion à la base de données réussie.");
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM trains");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Train train = new Train(
                            resultSet.getString("NumeroTrain"),
                            resultSet.getString("VilleDepart"),
                            resultSet.getString("VilleArrivee"),
                            resultSet.getString("DateDepart"),
                            resultSet.getInt("HeureDepart"),
                            resultSet.getDouble("PrixBillet"),
                            resultSet.getInt("PlacesDisponibles"),
                            resultSet.getString("Etat"));
                    trainlist.add(train);
                }
                for (Train a : trainlist) {
                    System.out.println(a);
                }

            } else {
                System.out.println("Échec de la connexion à la base de données.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Une erreur s'est produite lors de la vérification de la connexion.");
        }
    }
}