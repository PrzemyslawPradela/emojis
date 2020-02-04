
package ws.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the ws.client package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DeleteById_QNAME = new QName("http://server.ws/", "deleteById");
    private final static QName _Save_QNAME = new QName("http://server.ws/", "save");
    private final static QName _Update_QNAME = new QName("http://server.ws/", "update");
    private final static QName _GetEmojisResponse_QNAME = new QName("http://server.ws/", "getEmojisResponse");
    private final static QName _SaveResponse_QNAME = new QName("http://server.ws/", "saveResponse");
    private final static QName _UpdateResponse_QNAME = new QName("http://server.ws/", "updateResponse");
    private final static QName _DeleteByIdResponse_QNAME = new QName("http://server.ws/", "deleteByIdResponse");
    private final static QName _FindById_QNAME = new QName("http://server.ws/", "findById");
    private final static QName _FindByIdResponse_QNAME = new QName("http://server.ws/", "findByIdResponse");
    private final static QName _GetEmojis_QNAME = new QName("http://server.ws/", "getEmojis");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.client
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeleteByIdResponse }
     */
    public DeleteByIdResponse createDeleteByIdResponse() {
        return new DeleteByIdResponse();
    }

    /**
     * Create an instance of {@link FindById }
     */
    public FindById createFindById() {
        return new FindById();
    }

    /**
     * Create an instance of {@link FindByIdResponse }
     */
    public FindByIdResponse createFindByIdResponse() {
        return new FindByIdResponse();
    }

    /**
     * Create an instance of {@link GetEmojis }
     */
    public GetEmojis createGetEmojis() {
        return new GetEmojis();
    }

    /**
     * Create an instance of {@link DeleteById }
     */
    public DeleteById createDeleteById() {
        return new DeleteById();
    }

    /**
     * Create an instance of {@link Save }
     */
    public Save createSave() {
        return new Save();
    }

    /**
     * Create an instance of {@link Update }
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link GetEmojisResponse }
     */
    public GetEmojisResponse createGetEmojisResponse() {
        return new GetEmojisResponse();
    }

    /**
     * Create an instance of {@link SaveResponse }
     */
    public SaveResponse createSaveResponse() {
        return new SaveResponse();
    }

    /**
     * Create an instance of {@link UpdateResponse }
     */
    public UpdateResponse createUpdateResponse() {
        return new UpdateResponse();
    }

    /**
     * Create an instance of {@link Emoji }
     */
    public Emoji createEmoji() {
        return new Emoji();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteById }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server.ws/", name = "deleteById")
    public JAXBElement<DeleteById> createDeleteById(DeleteById value) {
        return new JAXBElement<DeleteById>(_DeleteById_QNAME, DeleteById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Save }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server.ws/", name = "save")
    public JAXBElement<Save> createSave(Save value) {
        return new JAXBElement<Save>(_Save_QNAME, Save.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Update }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server.ws/", name = "update")
    public JAXBElement<Update> createUpdate(Update value) {
        return new JAXBElement<Update>(_Update_QNAME, Update.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEmojisResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server.ws/", name = "getEmojisResponse")
    public JAXBElement<GetEmojisResponse> createGetEmojisResponse(GetEmojisResponse value) {
        return new JAXBElement<GetEmojisResponse>(_GetEmojisResponse_QNAME, GetEmojisResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server.ws/", name = "saveResponse")
    public JAXBElement<SaveResponse> createSaveResponse(SaveResponse value) {
        return new JAXBElement<SaveResponse>(_SaveResponse_QNAME, SaveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server.ws/", name = "updateResponse")
    public JAXBElement<UpdateResponse> createUpdateResponse(UpdateResponse value) {
        return new JAXBElement<UpdateResponse>(_UpdateResponse_QNAME, UpdateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteByIdResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server.ws/", name = "deleteByIdResponse")
    public JAXBElement<DeleteByIdResponse> createDeleteByIdResponse(DeleteByIdResponse value) {
        return new JAXBElement<DeleteByIdResponse>(_DeleteByIdResponse_QNAME, DeleteByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindById }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server.ws/", name = "findById")
    public JAXBElement<FindById> createFindById(FindById value) {
        return new JAXBElement<FindById>(_FindById_QNAME, FindById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByIdResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server.ws/", name = "findByIdResponse")
    public JAXBElement<FindByIdResponse> createFindByIdResponse(FindByIdResponse value) {
        return new JAXBElement<FindByIdResponse>(_FindByIdResponse_QNAME, FindByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEmojis }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server.ws/", name = "getEmojis")
    public JAXBElement<GetEmojis> createGetEmojis(GetEmojis value) {
        return new JAXBElement<GetEmojis>(_GetEmojis_QNAME, GetEmojis.class, null, value);
    }

}
