
package ws.soap.reservation;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.soap.reservation package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAllReservationsResponse_QNAME = new QName("http://reservation.soap.ws/", "getAllReservationsResponse");
    private final static QName _GetAllReservations_QNAME = new QName("http://reservation.soap.ws/", "getAllReservations");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.soap.reservation
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllReservations }
     * 
     */
    public GetAllReservations createGetAllReservations() {
        return new GetAllReservations();
    }

    /**
     * Create an instance of {@link GetAllReservationsResponse }
     * 
     */
    public GetAllReservationsResponse createGetAllReservationsResponse() {
        return new GetAllReservationsResponse();
    }

    /**
     * Create an instance of {@link Reservation }
     * 
     */
    public Reservation createReservation() {
        return new Reservation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllReservationsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://reservation.soap.ws/", name = "getAllReservationsResponse")
    public JAXBElement<GetAllReservationsResponse> createGetAllReservationsResponse(GetAllReservationsResponse value) {
        return new JAXBElement<GetAllReservationsResponse>(_GetAllReservationsResponse_QNAME, GetAllReservationsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllReservations }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://reservation.soap.ws/", name = "getAllReservations")
    public JAXBElement<GetAllReservations> createGetAllReservations(GetAllReservations value) {
        return new JAXBElement<GetAllReservations>(_GetAllReservations_QNAME, GetAllReservations.class, null, value);
    }

}
