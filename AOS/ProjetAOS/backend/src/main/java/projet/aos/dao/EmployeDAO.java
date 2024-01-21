package projet.aos.dao;

import projet.aos.database.DBConnection;
import projet.aos.models.Employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeDAO {
    private final Connection connection;

    public EmployeDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public List<Employe> getEmployes() {
        List<Employe> employes = new ArrayList<>();
        String query = "SELECT * FROM employes";
        try (PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                Employe employe = new Employe(
                        resultSet.getInt("numero_membre"),
                        resultSet.getString("mot_de_passe"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("numero_secu_sociale"),
                        resultSet.getString("numero_permis"),
                        resultSet.getString("adresse_domicile"));
                employes.add(employe);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des employés", e);
        }
        return employes;
    }

    public Employe getEmployeByNumeroMembre(String numero) {
        String query = "SELECT * FROM employes WHERE numero_membre=? OR numero_secu_sociale=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, numero);
            stmt.setString(2, numero);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return new Employe(
                            resultSet.getInt("numero_membre"),
                            resultSet.getString("mot_de_passe"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"),
                            resultSet.getString("numero_secu_sociale"),
                            resultSet.getString("numero_permis"),
                            resultSet.getString("adresse_domicile"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de l'employé par numéro de membre", e);
        }
        return null;
    }

    public void addEmploye(Employe employe) {
        String query = "INSERT INTO employes (mot_de_passe, nom, prenom, numero_secu_sociale, numero_permis, adresse_domicile) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, employe.getMotDePasse());
            stmt.setString(2, employe.getNom());
            stmt.setString(3, employe.getPrenom());
            stmt.setString(4, employe.getNumeroSecuSociale());
            stmt.setString(5, employe.getNumeroPermis());
            stmt.setString(6, employe.getAdresseDomicile());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de l'employé", e);
        }
    }

    public void updateEmploye(int numeroMembre, Employe updatedEmploye) {
        String query = "UPDATE employes SET mot_de_passe=?, nom=?, prenom=?, " +
                "numero_permis=?, adresse_domicile=? WHERE numero_membre=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedEmploye.getMotDePasse());
            stmt.setString(2, updatedEmploye.getNom());
            stmt.setString(3, updatedEmploye.getPrenom());
            stmt.setString(4, updatedEmploye.getNumeroPermis());
            stmt.setString(5, updatedEmploye.getAdresseDomicile());
            stmt.setInt(6, numeroMembre);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de l'employé", e);
        }
    }

    public void deleteEmploye(int numeroMembre) {
        String query = "DELETE FROM employes WHERE numero_membre=?";
        String query2 = "DELETE FROM reservations WHERE numero_membre=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, numeroMembre);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'employé", e);
        }
        try (PreparedStatement stmt = connection.prepareStatement(query2)) {
            stmt.setInt(1, numeroMembre);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression des réservations de l'employé", e);
        }
    }

    public void connecterEmploye(int numeroMembre, String motDePasse) {
        String query = "UPDATE employes SET connecte=1 WHERE numero_membre=? AND mot_de_passe=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, numeroMembre);
            stmt.setString(2, motDePasse);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la connexion de l'employé", e);
        }
    }
}
