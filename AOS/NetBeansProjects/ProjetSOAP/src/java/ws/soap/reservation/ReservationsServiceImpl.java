package ws.soap.reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ws.soap.train.DBConnection;

import javax.jws.WebService;

@WebService(serviceName = "ReservationService")
public class ReservationsServiceImpl implements ReservationsService {

    private Connection connection;

    public ReservationsServiceImpl() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public List<Reservation> getAllReservations(String memberEmail) {
        List<Reservation> reservations = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Reservations WHERE NumeroClient = (SELECT NumeroClient FROM Clients WHERE Email = ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, memberEmail);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    // Fetch relevant train information using an additional SQL query
                    int numeroTrain = resultSet.getInt("NumeroTrain");
                    String trainInfo = getTrainInfo(numeroTrain);

                    // Create an instance of Reservation with relevant train information
                    Reservation reservation = new Reservation(
                            resultSet.getInt("NumeroReservation"),
                            numeroTrain,
                            resultSet.getInt("NumeroClient"),
                            resultSet.getInt("NumeroPlace"),
                            trainInfo
                    );

                    reservations.add(reservation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

// Method to fetch train information from the database
private String getTrainInfo(int numeroTrain) {
    try {
        String trainInfoSql = "SELECT VilleDepart, VilleArrivee, DateDepart, heureDepart FROM Trains WHERE NumeroTrain = ?";
        try (PreparedStatement trainInfoStatement = connection.prepareStatement(trainInfoSql)) {
            trainInfoStatement.setInt(1, numeroTrain);

            ResultSet trainInfoResultSet = trainInfoStatement.executeQuery();

            if (trainInfoResultSet.next()) {
                String villeDepart = trainInfoResultSet.getString("VilleDepart");
                String villeArrivee = trainInfoResultSet.getString("VilleArrivee");
                String dateDepart = trainInfoResultSet.getString("DateDepart");
                String heureDepart = trainInfoResultSet.getString("heureDepart");

            return villeDepart + " → " + villeArrivee +
                   ", le " + dateDepart +
                   ", à " + new StringBuilder(heureDepart).insert(heureDepart.length() - 2, ":").toString() + "h.";
                        }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return "";  // Return an empty string if train information is not found
}
}
