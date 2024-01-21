
package ws.soap.helloworld;

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
@WebServiceClient(name = "helloworld", targetNamespace = "http://helloworld.soap.ws/", wsdlLocation = "http://localhost:8080/ProjetSOAP/helloworld?wsdl")
public class Helloworld_Service
    extends Service
{

    private final static URL HELLOWORLD_WSDL_LOCATION;
    private final static WebServiceException HELLOWORLD_EXCEPTION;
    private final static QName HELLOWORLD_QNAME = new QName("http://helloworld.soap.ws/", "helloworld");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/ProjetSOAP/helloworld?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        HELLOWORLD_WSDL_LOCATION = url;
        HELLOWORLD_EXCEPTION = e;
    }

    public Helloworld_Service() {
        super(__getWsdlLocation(), HELLOWORLD_QNAME);
    }

    public Helloworld_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), HELLOWORLD_QNAME, features);
    }

    public Helloworld_Service(URL wsdlLocation) {
        super(wsdlLocation, HELLOWORLD_QNAME);
    }

    public Helloworld_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, HELLOWORLD_QNAME, features);
    }

    public Helloworld_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Helloworld_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Helloworld
     */
    @WebEndpoint(name = "HelloWorldPort")
    public Helloworld getHelloWorldPort() {
        return super.getPort(new QName("http://helloworld.soap.ws/", "HelloWorldPort"), Helloworld.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Helloworld
     */
    @WebEndpoint(name = "HelloWorldPort")
    public Helloworld getHelloWorldPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://helloworld.soap.ws/", "HelloWorldPort"), Helloworld.class, features);
    }

    private static URL __getWsdlLocation() {
        if (HELLOWORLD_EXCEPTION!= null) {
            throw HELLOWORLD_EXCEPTION;
        }
        return HELLOWORLD_WSDL_LOCATION;
    }

}
