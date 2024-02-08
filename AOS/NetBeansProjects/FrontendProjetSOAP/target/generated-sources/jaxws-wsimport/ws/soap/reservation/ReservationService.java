
package ws.soap.reservation;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ReservationService", targetNamespace = "http://reservation.soap.ws/", wsdlLocation = "http://localhost:8080/ProjetSOAP/ReservationService?wsdl")
public class ReservationService
    extends Service
{

    private final static URL RESERVATIONSERVICE_WSDL_LOCATION;
    private final static WebServiceException RESERVATIONSERVICE_EXCEPTION;
    private final static QName RESERVATIONSERVICE_QNAME = new QName("http://reservation.soap.ws/", "ReservationService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/ProjetSOAP/ReservationService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        RESERVATIONSERVICE_WSDL_LOCATION = url;
        RESERVATIONSERVICE_EXCEPTION = e;
    }

    public ReservationService() {
        super(__getWsdlLocation(), RESERVATIONSERVICE_QNAME);
    }

    public ReservationService(WebServiceFeature... features) {
        super(__getWsdlLocation(), RESERVATIONSERVICE_QNAME, features);
    }

    public ReservationService(URL wsdlLocation) {
        super(wsdlLocation, RESERVATIONSERVICE_QNAME);
    }

    public ReservationService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, RESERVATIONSERVICE_QNAME, features);
    }

    public ReservationService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ReservationService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ReservationsServiceImpl
     */
    @WebEndpoint(name = "ReservationsServiceImplPort")
    public ReservationsServiceImpl getReservationsServiceImplPort() {
        return super.getPort(new QName("http://reservation.soap.ws/", "ReservationsServiceImplPort"), ReservationsServiceImpl.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ReservationsServiceImpl
     */
    @WebEndpoint(name = "ReservationsServiceImplPort")
    public ReservationsServiceImpl getReservationsServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://reservation.soap.ws/", "ReservationsServiceImplPort"), ReservationsServiceImpl.class, features);
    }

    private static URL __getWsdlLocation() {
        if (RESERVATIONSERVICE_EXCEPTION!= null) {
            throw RESERVATIONSERVICE_EXCEPTION;
        }
        return RESERVATIONSERVICE_WSDL_LOCATION;
    }

}