package projet.aos.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Employe {
    private int numeroMembre;
    private String motDePasse;
    private String nom;
    private String prenom;
    private String numeroSecuSociale;
    private String numeroPermis;
    private String adresseDomicile;

    public Employe() {
    }

    public Employe(int numeroMembre, String motDePasse, String nom, String prenom, String numeroSecuSociale, String numeroPermis, String adresseDomicile) {
        this.numeroMembre = numeroMembre;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroSecuSociale = numeroSecuSociale;
        this.numeroPermis = numeroPermis;
        this.adresseDomicile = adresseDomicile;
    }

    public int getNumeroMembre() {
        return numeroMembre;
    }

    public void setNumeroMembre(int numeroMembre) {
        this.numeroMembre = numeroMembre;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumeroSecuSociale() {
        return numeroSecuSociale;
    }

    public void setNumeroSecuSociale(String numeroSecuSociale) {
        this.numeroSecuSociale = numeroSecuSociale;
    }

    public String getNumeroPermis() {
        return numeroPermis;
    }

    public void setNumeroPermis(String numeroPermis) {
        this.numeroPermis = numeroPermis;
    }

    public String getAdresseDomicile() {
        return adresseDomicile;
    }

    public void setAdresseDomicile(String adresseDomicile) {
        this.adresseDomicile = adresseDomicile;
    }
}
