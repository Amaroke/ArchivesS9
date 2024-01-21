
package ws.soap.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour reservation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="reservation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroClient" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numeroPlace" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numeroReservation" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numeroTrain" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="trainInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reservation", propOrder = {
    "numeroClient",
    "numeroPlace",
    "numeroReservation",
    "numeroTrain",
    "trainInfo"
})
public class Reservation {

    protected int numeroClient;
    protected int numeroPlace;
    protected int numeroReservation;
    protected int numeroTrain;
    protected String trainInfo;

    /**
     * Obtient la valeur de la propriété numeroClient.
     * 
     */
    public int getNumeroClient() {
        return numeroClient;
    }

    /**
     * Définit la valeur de la propriété numeroClient.
     * 
     */
    public void setNumeroClient(int value) {
        this.numeroClient = value;
    }

    /**
     * Obtient la valeur de la propriété numeroPlace.
     * 
     */
    public int getNumeroPlace() {
        return numeroPlace;
    }

    /**
     * Définit la valeur de la propriété numeroPlace.
     * 
     */
    public void setNumeroPlace(int value) {
        this.numeroPlace = value;
    }

    /**
     * Obtient la valeur de la propriété numeroReservation.
     * 
     */
    public int getNumeroReservation() {
        return numeroReservation;
    }

    /**
     * Définit la valeur de la propriété numeroReservation.
     * 
     */
    public void setNumeroReservation(int value) {
        this.numeroReservation = value;
    }

    /**
     * Obtient la valeur de la propriété numeroTrain.
     * 
     */
    public int getNumeroTrain() {
        return numeroTrain;
    }

    /**
     * Définit la valeur de la propriété numeroTrain.
     * 
     */
    public void setNumeroTrain(int value) {
        this.numeroTrain = value;
    }

    /**
     * Obtient la valeur de la propriété trainInfo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrainInfo() {
        return trainInfo;
    }

    /**
     * Définit la valeur de la propriété trainInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrainInfo(String value) {
        this.trainInfo = value;
    }

}
