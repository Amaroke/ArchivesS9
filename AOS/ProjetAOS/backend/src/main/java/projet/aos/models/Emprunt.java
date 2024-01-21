package projet.aos.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Emprunt {
    private int id;
    private String immatriculation;
    private int numeroMembre;
    private Date datePret;
    private String etat;

    public Emprunt() {
    }

    public Emprunt(int id, String immatriculation, int numeroMembre, Date datePret, String etat) {
        this.id = id;
        this.immatriculation = immatriculation;
        this.numeroMembre = numeroMembre;
        this.datePret = datePret;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public int getNumeroMembre() {
        return numeroMembre;
    }

    public void setNumeroMembre(int numeroMembre) {
        this.numeroMembre = numeroMembre;
    }

    public Date getDatePret() {
        return datePret;
    }

    public void setDatePret(Date datePret) {
        this.datePret = datePret;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
