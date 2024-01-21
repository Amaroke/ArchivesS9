package ws.soap.train;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;

@WebService(endpointInterface = "ws.soap.train.TrainService", serviceName = "trainservice", portName = "TrainServicePort")
public class TrainServiceImpl implements TrainService {

    private Connection connection;

    public TrainServiceImpl() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public List<Train> GetAllTrains() {
        List<Train> trainList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM trains");
                ResultSet resultSet = statement.executeQuery()) {

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
                trainList.add(train);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trainList;

    }

    @Override
    public List<Train> SearchTrains(String departure, String destination, String departureDate, int departureTime) {
        List<Train> searchResults = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Trains WHERE DateDepart > NOW() AND VilleDepart = ? AND VilleArrivee = ? AND DateDepart = ? AND HeureDepart > ? AND DateDepart > CURRENT_DATE;")) {

            statement.setString(1, departure);
            statement.setString(2, destination);
            statement.setString(3, departureDate);
            statement.setInt(4, departureTime);

            try (ResultSet resultSet = statement.executeQuery()) {
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
                    searchResults.add(train);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return searchResults;
    }

    @Override
    public List<Train> searchTrainsAdmin(String numeroTrain, String departure, String destination, String departureDate,
            int departureTime) {
        List<Train> searchResults = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Trains WHERE NumeroTrain = ? AND VilleDepart = ? AND VilleArrivee = ? AND DateDepart = ? AND HeureDepart > ? AND DateDepart > CURRENT_DATE;")) {

            statement.setString(1, numeroTrain);
            statement.setString(2, departure);
            statement.setString(3, destination);
            statement.setString(4, departureDate);
            statement.setInt(5, departureTime);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Train train = new Train(
                            resultSet.getString("NumeroTrain"),
                            resultSet.getString("VilleDepart"),
                            resultSet.getString("VilleArrivee"),
                            resultSet.getDate("DateDepart").toString(),
                            resultSet.getInt("HeureDepart"),
                            resultSet.getDouble("PrixBillet"),
                            resultSet.getInt("PlacesDisponibles"),
                            resultSet.getString("Etat"));
                    searchResults.add(train);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return searchResults;
    }

    @Override
    public void AddTrain(String departureCity, String arrivalCity, String date, int departureTime, double ticketPrice,
            int availableSeats, String state) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO trains (NumeroTrain, VilleDepart, VilleArrivee, DateDepart, HeureDepart, PrixBillet, PlacesDisponibles, Etat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, null);
            statement.setString(2, departureCity);
            statement.setString(3, arrivalCity);
            statement.setString(4, date);
            statement.setInt(5, departureTime);
            statement.setDouble(6, ticketPrice);
            statement.setInt(7, availableSeats);
            statement.setString(8, state);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UpdateTrain(String number, String departureCity, String arrivalCity, String date, int departureTime,
            double ticketPrice, int availableSeats, String state) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE trains SET VilleDepart = ?, VilleArrivee = ?, DateDepart = ?, HeureDepart = ?, PrixBillet = ?, PlacesDisponibles = ?, Etat = ? WHERE NumeroTrain = ?")) {
            statement.setString(1, departureCity);
            statement.setString(2, arrivalCity);
            statement.setString(3, date);
            statement.setInt(4, departureTime);
            statement.setDouble(5, ticketPrice);
            statement.setInt(6, availableSeats);
            statement.setString(7, state);
            statement.setString(8, number);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void CancelTrain(String numeroTrain) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE trains SET Etat = 'annule' WHERE NumeroTrain = ?")) {
            statement.setString(1, numeroTrain);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DeleteTrain(String trainNumber) {
        try (PreparedStatement deleteReservations = connection.prepareStatement(
                "DELETE FROM reservations WHERE NumeroTrain = ?")) {
            deleteReservations.setString(1, trainNumber);
            deleteReservations.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrainServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM trains WHERE NumeroTrain = ?")) {
            statement.setString(1, trainNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
