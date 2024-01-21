
package ws.soap.train;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.soap.train package. 
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

    private final static QName _SearchTrainsResponse_QNAME = new QName("http://train.soap.ws/", "SearchTrainsResponse");
    private final static QName _AddTrain_QNAME = new QName("http://train.soap.ws/", "AddTrain");
    private final static QName _DeleteTrain_QNAME = new QName("http://train.soap.ws/", "DeleteTrain");
    private final static QName _AddTrainResponse_QNAME = new QName("http://train.soap.ws/", "AddTrainResponse");
    private final static QName _DeleteTrainResponse_QNAME = new QName("http://train.soap.ws/", "DeleteTrainResponse");
    private final static QName _GetAllTrainsResponse_QNAME = new QName("http://train.soap.ws/", "GetAllTrainsResponse");
    private final static QName _SearchTrains_QNAME = new QName("http://train.soap.ws/", "SearchTrains");
    private final static QName _GetAllTrains_QNAME = new QName("http://train.soap.ws/", "GetAllTrains");
    private final static QName _SearchTrainsAdminResponse_QNAME = new QName("http://train.soap.ws/", "SearchTrainsAdminResponse");
    private final static QName _UpdateTrain_QNAME = new QName("http://train.soap.ws/", "UpdateTrain");
    private final static QName _CancelTrain_QNAME = new QName("http://train.soap.ws/", "CancelTrain");
    private final static QName _CancelTrainResponse_QNAME = new QName("http://train.soap.ws/", "CancelTrainResponse");
    private final static QName _SearchTrainsAdmin_QNAME = new QName("http://train.soap.ws/", "SearchTrainsAdmin");
    private final static QName _UpdateTrainResponse_QNAME = new QName("http://train.soap.ws/", "UpdateTrainResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.soap.train
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateTrain }
     * 
     */
    public UpdateTrain createUpdateTrain() {
        return new UpdateTrain();
    }

    /**
     * Create an instance of {@link SearchTrainsAdminResponse }
     * 
     */
    public SearchTrainsAdminResponse createSearchTrainsAdminResponse() {
        return new SearchTrainsAdminResponse();
    }

    /**
     * Create an instance of {@link GetAllTrains }
     * 
     */
    public GetAllTrains createGetAllTrains() {
        return new GetAllTrains();
    }

    /**
     * Create an instance of {@link UpdateTrainResponse }
     * 
     */
    public UpdateTrainResponse createUpdateTrainResponse() {
        return new UpdateTrainResponse();
    }

    /**
     * Create an instance of {@link CancelTrain }
     * 
     */
    public CancelTrain createCancelTrain() {
        return new CancelTrain();
    }

    /**
     * Create an instance of {@link CancelTrainResponse }
     * 
     */
    public CancelTrainResponse createCancelTrainResponse() {
        return new CancelTrainResponse();
    }

    /**
     * Create an instance of {@link SearchTrainsAdmin }
     * 
     */
    public SearchTrainsAdmin createSearchTrainsAdmin() {
        return new SearchTrainsAdmin();
    }

    /**
     * Create an instance of {@link AddTrain }
     * 
     */
    public AddTrain createAddTrain() {
        return new AddTrain();
    }

    /**
     * Create an instance of {@link DeleteTrain }
     * 
     */
    public DeleteTrain createDeleteTrain() {
        return new DeleteTrain();
    }

    /**
     * Create an instance of {@link AddTrainResponse }
     * 
     */
    public AddTrainResponse createAddTrainResponse() {
        return new AddTrainResponse();
    }

    /**
     * Create an instance of {@link DeleteTrainResponse }
     * 
     */
    public DeleteTrainResponse createDeleteTrainResponse() {
        return new DeleteTrainResponse();
    }

    /**
     * Create an instance of {@link SearchTrainsResponse }
     * 
     */
    public SearchTrainsResponse createSearchTrainsResponse() {
        return new SearchTrainsResponse();
    }

    /**
     * Create an instance of {@link SearchTrains }
     * 
     */
    public SearchTrains createSearchTrains() {
        return new SearchTrains();
    }

    /**
     * Create an instance of {@link GetAllTrainsResponse }
     * 
     */
    public GetAllTrainsResponse createGetAllTrainsResponse() {
        return new GetAllTrainsResponse();
    }

    /**
     * Create an instance of {@link Train }
     * 
     */
    public Train createTrain() {
        return new Train();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchTrainsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://train.soap.ws/", name = "SearchTrainsResponse")
    public JAXBElement<SearchTrainsResponse> createSearchTrainsResponse(SearchTrainsResponse value) {
        return new JAXBElement<SearchTrainsResponse>(_SearchTrainsResponse_QNAME, SearchTrainsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddTrain }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://train.soap.ws/", name = "AddTrain")
    public JAXBElement<AddTrain> createAddTrain(AddTrain value) {
        return new JAXBElement<AddTrain>(_AddTrain_QNAME, AddTrain.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteTrain }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://train.soap.ws/", name = "DeleteTrain")
    public JAXBElement<DeleteTrain> createDeleteTrain(DeleteTrain value) {
        return new JAXBElement<DeleteTrain>(_DeleteTrain_QNAME, DeleteTrain.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddTrainResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://train.soap.ws/", name = "AddTrainResponse")
    public JAXBElement<AddTrainResponse> createAddTrainResponse(AddTrainResponse value) {
        return new JAXBElement<AddTrainResponse>(_AddTrainResponse_QNAME, AddTrainResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteTrainResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://train.soap.ws/", name = "DeleteTrainResponse")
    public JAXBElement<DeleteTrainResponse> createDeleteTrainResponse(DeleteTrainResponse value) {
        return new JAXBElement<DeleteTrainResponse>(_DeleteTrainResponse_QNAME, DeleteTrainResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllTrainsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://train.soap.ws/", name = "GetAllTrainsResponse")
    public JAXBElement<GetAllTrainsResponse> createGetAllTrainsResponse(GetAllTrainsResponse value) {
        return new JAXBElement<GetAllTrainsResponse>(_GetAllTrainsResponse_QNAME, GetAllTrainsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchTrains }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://train.soap.ws/", name = "SearchTrains")
    public JAXBElement<SearchTrains> createSearchTrains(SearchTrains value) {
        return new JAXBElement<SearchTrains>(_SearchTrains_QNAME, SearchTrains.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllTrains }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://train.soap.ws/", name = "GetAllTrains")
    public JAXBElement<GetAllTrains> createGetAllTrains(GetAllTrains value) {
        return new JAXBElement<GetAllTrains>(_GetAllTrains_QNAME, GetAllTrains.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchTrainsAdminResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://train.soap.ws/", name = "SearchTrainsAdminResponse")
    public JAXBElement<SearchTrainsAdminResponse> createSearchTrainsAdminResponse(SearchTrainsAdminResponse value) {
        return new JAXBElement<SearchTrainsAdminResponse>(_SearchTrainsAdminResponse_QNAME, SearchTrainsAdminResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTrain }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://train.soap.ws/", name = "UpdateTrain")
    public JAXBElement<UpdateTrain> createUpdateTrain(UpdateTrain value) {
        return new JAXBElement<UpdateTrain>(_UpdateTrain_QNAME, UpdateTrain.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelTrain }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://train.soap.ws/", name = "CancelTrain")
    public JAXBElement<CancelTrain> createCancelTrain(CancelTrain value) {
        return new JAXBElement<CancelTrain>(_CancelTrain_QNAME, CancelTrain.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelTrainResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://train.soap.ws/", name = "CancelTrainResponse")
    public JAXBElement<CancelTrainResponse> createCancelTrainResponse(CancelTrainResponse value) {
        return new JAXBElement<CancelTrainResponse>(_CancelTrainResponse_QNAME, CancelTrainResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchTrainsAdmin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://train.soap.ws/", name = "SearchTrainsAdmin")
    public JAXBElement<SearchTrainsAdmin> createSearchTrainsAdmin(SearchTrainsAdmin value) {
        return new JAXBElement<SearchTrainsAdmin>(_SearchTrainsAdmin_QNAME, SearchTrainsAdmin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTrainResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://train.soap.ws/", name = "UpdateTrainResponse")
    public JAXBElement<UpdateTrainResponse> createUpdateTrainResponse(UpdateTrainResponse value) {
        return new JAXBElement<UpdateTrainResponse>(_UpdateTrainResponse_QNAME, UpdateTrainResponse.class, null, value);
    }

}
