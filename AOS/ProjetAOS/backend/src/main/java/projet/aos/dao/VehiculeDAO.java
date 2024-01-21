package projet.aos.dao;

import projet.aos.database.DBConnection;
import projet.aos.models.Vehicule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculeDAO {
    private final Connection connection;

    public VehiculeDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public List<Vehicule> getVehicules() {
        List<Vehicule> vehicules = new ArrayList<>();
        String query = "SELECT * FROM vehicules";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                Vehicule vehicule = new Vehicule(
                        resultSet.getString("immatriculation"),
                        resultSet.getString("categorie_vehicule"),
                        resultSet.getString("marque"),
                        resultSet.getString("modele"),
                        resultSet.getString("type_vehicule"),
                        resultSet.getString("boite_vitesse"),
                        resultSet.getInt("nombre_places"),
                        resultSet.getString("description"));
                vehicules.add(vehicule);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des véhicules");
        }
        return vehicules;
    }

    public Vehicule getVehiculeByImmatriculation(String immatriculation) {
        String query = "SELECT * FROM vehicules WHERE immatriculation=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, immatriculation);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return new Vehicule(
                            resultSet.getString("immatriculation"),
                            resultSet.getString("categorie_vehicule"),
                            resultSet.getString("marque"),
                            resultSet.getString("modele"),
                            resultSet.getString("type_vehicule"),
                            resultSet.getString("boite_vitesse"),
                            resultSet.getInt("nombre_places"),
                            resultSet.getString("description"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération du véhicule par immatriculation", e);
        }
        return null;
    }

    public void addVehicule(Vehicule vehicule) {
        String query = "INSERT INTO vehicules VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vehicule.getImmatriculation());
            stmt.setString(2, vehicule.getCategorie());
            stmt.setString(3, vehicule.getMarque());
            stmt.setString(4, vehicule.getModele());
            stmt.setString(5, vehicule.getType());
            stmt.setString(6, vehicule.getBoiteDeVitesse());
            stmt.setInt(7, vehicule.getNbDePlaces());
            stmt.setString(8, vehicule.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout du véhicule", e);
        }
    }

    public void updateVehicule(String immatriculation, Vehicule updatedVehicule) {
        String query = "UPDATE vehicules SET categorie_vehicule=?, marque=?, modele=?, type_vehicule=?, " +
                "boite_vitesse=?, nombre_places=?, description=? WHERE immatriculation=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedVehicule.getCategorie());
            stmt.setString(2, updatedVehicule.getMarque());
            stmt.setString(3, updatedVehicule.getModele());
            stmt.setString(4, updatedVehicule.getType());
            stmt.setString(5, updatedVehicule.getBoiteDeVitesse());
            stmt.setInt(6, updatedVehicule.getNbDePlaces());
            stmt.setString(7, updatedVehicule.getDescription());
            stmt.setString(8, immatriculation);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour du véhicule", e);
        }
    }

    public void deleteVehicule(String immatriculation) {
        try {
            // Suppression des réservations liées au véhicule
            String deleteEmpruntsQuery = "DELETE FROM emprunts WHERE immatriculation=?";
            try (PreparedStatement stmtEmprunts = connection.prepareStatement(deleteEmpruntsQuery)) {
                stmtEmprunts.setString(1, immatriculation);
                stmtEmprunts.executeUpdate();
            }

            // Suppression du véhicule
            String deleteVehiculeQuery = "DELETE FROM vehicules WHERE immatriculation=?";
            try (PreparedStatement stmtVehicule = connection.prepareStatement(deleteVehiculeQuery)) {
                stmtVehicule.setString(1, immatriculation);
                stmtVehicule.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression du véhicule", e);
        }
    }
}
