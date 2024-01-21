package projet.aos.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Vehicule {
    private String immatriculation;
    private String type;
    private String marque;
    private String modele;
    private String categorie;
    private String boiteDeVitesse;
    private int nbDePlaces;
    private String description;

    public Vehicule() {
    }

    public Vehicule(String immatriculation, String type, String marque, String modele, String categorie, String boiteDeVitesse, int nbDePlaces, String description) {
        this.immatriculation = immatriculation;
        this.type = type;
        this.marque = marque;
        this.modele = modele;
        this.categorie = categorie;
        this.boiteDeVitesse = boiteDeVitesse;
        this.nbDePlaces = nbDePlaces    ;
        this.description = description;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getBoiteDeVitesse() {
        return boiteDeVitesse;
    }

    public void setBoiteDeVitesse(String boiteDeVitesse) {
        this.boiteDeVitesse = boiteDeVitesse;
    }

    public int getNbDePlaces() {
        return nbDePlaces;
    }

    public void setNbDePlaces(int nbDePlaces) {
        this.nbDePlaces = nbDePlaces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
