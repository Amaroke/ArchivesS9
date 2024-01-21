package projet.aos.dao;

import projet.aos.database.DBConnection;
import projet.aos.models.Emprunt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDAO {
    private final Connection connection;

    public EmpruntDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public List<Emprunt> getEmprunts() {
        List<Emprunt> emprunts = new ArrayList<>();
        String query = "SELECT * FROM emprunts";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                Emprunt emprunt = new Emprunt(
                        resultSet.getInt("id"),
                        resultSet.getString("immatriculation"),
                        resultSet.getInt("numero_membre"),
                        resultSet.getDate("date_pret"),
                        resultSet.getString("etat"));
                emprunts.add(emprunt);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des emprunts", e);
        }
        return emprunts;
    }

    public Emprunt getEmpruntById(int id) {
        String query = "SELECT * FROM emprunts WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return new Emprunt(
                            resultSet.getInt("id"),
                            resultSet.getString("immatriculation"),
                            resultSet.getInt("numero_membre"),
                            resultSet.getDate("date_pret"),
                            resultSet.getString("etat"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de l'emprunt par ID", e);
        }
        return null;
    }

    public void addEmprunt(Emprunt emprunt) {
        String query = "INSERT INTO emprunts VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, emprunt.getId());
            stmt.setString(2, emprunt.getImmatriculation());
            stmt.setInt(3, emprunt.getNumeroMembre());
            stmt.setDate(4, emprunt.getDatePret());
            stmt.setString(5, emprunt.getEtat());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de l'emprunt", e);
        }
    }

    public void updateEmprunt(int id, Emprunt updatedEmprunt) {
        String query = "UPDATE emprunts SET immatriculation=?, numero_membre=?, " +
                "date_pret=?, etat=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedEmprunt.getImmatriculation());
            stmt.setInt(2, updatedEmprunt.getNumeroMembre());
            stmt.setDate(3, updatedEmprunt.getDatePret());
            stmt.setString(4, updatedEmprunt.getEtat());
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de l'emprunt", e);
        }
    }

    public void deleteEmprunt(int id) {
        String query = "DELETE FROM emprunts WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'emprunt", e);
        }
    }

    public void validerEmprunt(int id) {
        String query = "UPDATE emprunts SET etat=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "confirme");
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la validation de l'emprunt", e);
        }
    }

}
