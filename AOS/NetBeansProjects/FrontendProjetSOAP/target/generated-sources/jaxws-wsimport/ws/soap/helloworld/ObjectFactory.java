
package ws.soap.helloworld;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.soap.helloworld package. 
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

    private final static QName _HelloWorldSimple_QNAME = new QName("http://helloworld.soap.ws/", "HelloWorldSimple");
    private final static QName _HelloWorldSimpleResponse_QNAME = new QName("http://helloworld.soap.ws/", "HelloWorldSimpleResponse");
    private final static QName _HelloWorldParam_QNAME = new QName("http://helloworld.soap.ws/", "HelloWorldParam");
    private final static QName _HelloWorldParamResponse_QNAME = new QName("http://helloworld.soap.ws/", "HelloWorldParamResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.soap.helloworld
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HelloWorldSimpleResponse }
     * 
     */
    public HelloWorldSimpleResponse createHelloWorldSimpleResponse() {
        return new HelloWorldSimpleResponse();
    }

    /**
     * Create an instance of {@link HelloWorldParam }
     * 
     */
    public HelloWorldParam createHelloWorldParam() {
        return new HelloWorldParam();
    }

    /**
     * Create an instance of {@link HelloWorldParamResponse }
     * 
     */
    public HelloWorldParamResponse createHelloWorldParamResponse() {
        return new HelloWorldParamResponse();
    }

    /**
     * Create an instance of {@link HelloWorldSimple }
     * 
     */
    public HelloWorldSimple createHelloWorldSimple() {
        return new HelloWorldSimple();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloWorldSimple }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://helloworld.soap.ws/", name = "HelloWorldSimple")
    public JAXBElement<HelloWorldSimple> createHelloWorldSimple(HelloWorldSimple value) {
        return new JAXBElement<HelloWorldSimple>(_HelloWorldSimple_QNAME, HelloWorldSimple.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloWorldSimpleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://helloworld.soap.ws/", name = "HelloWorldSimpleResponse")
    public JAXBElement<HelloWorldSimpleResponse> createHelloWorldSimpleResponse(HelloWorldSimpleResponse value) {
        return new JAXBElement<HelloWorldSimpleResponse>(_HelloWorldSimpleResponse_QNAME, HelloWorldSimpleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloWorldParam }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://helloworld.soap.ws/", name = "HelloWorldParam")
    public JAXBElement<HelloWorldParam> createHelloWorldParam(HelloWorldParam value) {
        return new JAXBElement<HelloWorldParam>(_HelloWorldParam_QNAME, HelloWorldParam.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloWorldParamResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://helloworld.soap.ws/", name = "HelloWorldParamResponse")
    public JAXBElement<HelloWorldParamResponse> createHelloWorldParamResponse(HelloWorldParamResponse value) {
        return new JAXBElement<HelloWorldParamResponse>(_HelloWorldParamResponse_QNAME, HelloWorldParamResponse.class, null, value);
    }

}
