
package servidortienda;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servidortienda package. 
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

    private final static QName _ExportProducto_QNAME = new QName("http://servidortienda/", "exportProducto");
    private final static QName _ExportProductoResponse_QNAME = new QName("http://servidortienda/", "exportProductoResponse");
    private final static QName _ExportTienda_QNAME = new QName("http://servidortienda/", "exportTienda");
    private final static QName _ExportTiendaResponse_QNAME = new QName("http://servidortienda/", "exportTiendaResponse");
    private final static QName _GetProducto_QNAME = new QName("http://servidortienda/", "getProducto");
    private final static QName _GetProductoResponse_QNAME = new QName("http://servidortienda/", "getProductoResponse");
    private final static QName _GetTienda_QNAME = new QName("http://servidortienda/", "getTienda");
    private final static QName _GetTiendaResponse_QNAME = new QName("http://servidortienda/", "getTiendaResponse");
    private final static QName _ImportProducto_QNAME = new QName("http://servidortienda/", "importProducto");
    private final static QName _ImportProductoResponse_QNAME = new QName("http://servidortienda/", "importProductoResponse");
    private final static QName _ImportTienda_QNAME = new QName("http://servidortienda/", "importTienda");
    private final static QName _ImportTiendaResponse_QNAME = new QName("http://servidortienda/", "importTiendaResponse");
    private final static QName _Producto_QNAME = new QName("http://servidortienda/", "producto");
    private final static QName _SetProducto_QNAME = new QName("http://servidortienda/", "setProducto");
    private final static QName _SetProductoResponse_QNAME = new QName("http://servidortienda/", "setProductoResponse");
    private final static QName _SetTienda_QNAME = new QName("http://servidortienda/", "setTienda");
    private final static QName _SetTiendaResponse_QNAME = new QName("http://servidortienda/", "setTiendaResponse");
    private final static QName _Tienda_QNAME = new QName("http://servidortienda/", "tienda");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servidortienda
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Tienda }
     * 
     */
    public Tienda createTienda() {
        return new Tienda();
    }

    /**
     * Create an instance of {@link ExportProducto }
     * 
     */
    public ExportProducto createExportProducto() {
        return new ExportProducto();
    }

    /**
     * Create an instance of {@link ExportProductoResponse }
     * 
     */
    public ExportProductoResponse createExportProductoResponse() {
        return new ExportProductoResponse();
    }

    /**
     * Create an instance of {@link ExportTienda }
     * 
     */
    public ExportTienda createExportTienda() {
        return new ExportTienda();
    }

    /**
     * Create an instance of {@link ExportTiendaResponse }
     * 
     */
    public ExportTiendaResponse createExportTiendaResponse() {
        return new ExportTiendaResponse();
    }

    /**
     * Create an instance of {@link GetProducto }
     * 
     */
    public GetProducto createGetProducto() {
        return new GetProducto();
    }

    /**
     * Create an instance of {@link GetProductoResponse }
     * 
     */
    public GetProductoResponse createGetProductoResponse() {
        return new GetProductoResponse();
    }

    /**
     * Create an instance of {@link GetTienda }
     * 
     */
    public GetTienda createGetTienda() {
        return new GetTienda();
    }

    /**
     * Create an instance of {@link GetTiendaResponse }
     * 
     */
    public GetTiendaResponse createGetTiendaResponse() {
        return new GetTiendaResponse();
    }

    /**
     * Create an instance of {@link ImportProducto }
     * 
     */
    public ImportProducto createImportProducto() {
        return new ImportProducto();
    }

    /**
     * Create an instance of {@link ImportProductoResponse }
     * 
     */
    public ImportProductoResponse createImportProductoResponse() {
        return new ImportProductoResponse();
    }

    /**
     * Create an instance of {@link ImportTienda }
     * 
     */
    public ImportTienda createImportTienda() {
        return new ImportTienda();
    }

    /**
     * Create an instance of {@link ImportTiendaResponse }
     * 
     */
    public ImportTiendaResponse createImportTiendaResponse() {
        return new ImportTiendaResponse();
    }

    /**
     * Create an instance of {@link Producto }
     * 
     */
    public Producto createProducto() {
        return new Producto();
    }

    /**
     * Create an instance of {@link SetProducto }
     * 
     */
    public SetProducto createSetProducto() {
        return new SetProducto();
    }

    /**
     * Create an instance of {@link SetProductoResponse }
     * 
     */
    public SetProductoResponse createSetProductoResponse() {
        return new SetProductoResponse();
    }

    /**
     * Create an instance of {@link SetTienda }
     * 
     */
    public SetTienda createSetTienda() {
        return new SetTienda();
    }

    /**
     * Create an instance of {@link SetTiendaResponse }
     * 
     */
    public SetTiendaResponse createSetTiendaResponse() {
        return new SetTiendaResponse();
    }

    /**
     * Create an instance of {@link Tienda.Productos }
     * 
     */
    public Tienda.Productos createTiendaProductos() {
        return new Tienda.Productos();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExportProducto }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExportProducto }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidortienda/", name = "exportProducto")
    public JAXBElement<ExportProducto> createExportProducto(ExportProducto value) {
        return new JAXBElement<ExportProducto>(_ExportProducto_QNAME, ExportProducto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExportProductoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExportProductoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidortienda/", name = "exportProductoResponse")
    public JAXBElement<ExportProductoResponse> createExportProductoResponse(ExportProductoResponse value) {
        return new JAXBElement<ExportProductoResponse>(_ExportProductoResponse_QNAME, ExportProductoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExportTienda }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExportTienda }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidortienda/", name = "exportTienda")
    public JAXBElement<ExportTienda> createExportTienda(ExportTienda value) {
        return new JAXBElement<ExportTienda>(_ExportTienda_QNAME, ExportTienda.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExportTiendaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExportTiendaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidortienda/", name = "exportTiendaResponse")
    public JAXBElement<ExportTiendaResponse> createExportTiendaResponse(ExportTiendaResponse value) {
        return new JAXBElement<ExportTiendaResponse>(_ExportTiendaResponse_QNAME, ExportTiendaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProducto }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetProducto }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidortienda/", name = "getProducto")
    public JAXBElement<GetProducto> createGetProducto(GetProducto value) {
        return new JAXBElement<GetProducto>(_GetProducto_QNAME, GetProducto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetProductoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidortienda/", name = "getProductoResponse")
    public JAXBElement<GetProductoResponse> createGetProductoResponse(GetProductoResponse value) {
        return new JAXBElement<GetProductoResponse>(_GetProductoResponse_QNAME, GetProductoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTienda }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetTienda }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidortienda/", name = "getTienda")
    public JAXBElement<GetTienda> createGetTienda(GetTienda value) {
        return new JAXBElement<GetTienda>(_GetTienda_QNAME, GetTienda.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTiendaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetTiendaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidortienda/", name = "getTiendaResponse")
    public JAXBElement<GetTiendaResponse> createGetTiendaResponse(GetTiendaResponse value) {
        return new JAXBElement<GetTiendaResponse>(_GetTiendaResponse_QNAME, GetTiendaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImportProducto }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ImportProducto }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidortienda/", name = "importProducto")
    public JAXBElement<ImportProducto> createImportProducto(ImportProducto value) {
        return new JAXBElement<ImportProducto>(_ImportProducto_QNAME, ImportProducto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImportProductoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ImportProductoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidortienda/", name = "importProductoResponse")
    public JAXBElement<ImportProductoResponse> createImportProductoResponse(ImportProductoResponse value) {
        return new JAXBElement<ImportProductoResponse>(_ImportProductoResponse_QNAME, ImportProductoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImportTienda }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ImportTienda }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidortienda/", name = "importTienda")
    public JAXBElement<ImportTienda> createImportTienda(ImportTienda value) {
        return new JAXBElement<ImportTienda>(_ImportTienda_QNAME, ImportTienda.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImportTiendaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ImportTiendaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidortienda/", name = "importTiendaResponse")
    public JAXBElement<ImportTiendaResponse> createImportTiendaResponse(ImportTiendaResponse value) {
        return new JAXBElement<ImportTiendaResponse>(_ImportTiendaResponse_QNAME, ImportTiendaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Producto }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Producto }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidortienda/", name = "producto")
    public JAXBElement<Producto> createProducto(Producto value) {
        return new JAXBElement<Producto>(_Producto_QNAME, Producto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetProducto }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SetProducto }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidortienda/", name = "setProducto")
    public JAXBElement<SetProducto> createSetProducto(SetProducto value) {
        return new JAXBElement<SetProducto>(_SetProducto_QNAME, SetProducto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetProductoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SetProductoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidortienda/", name = "setProductoResponse")
    public JAXBElement<SetProductoResponse> createSetProductoResponse(SetProductoResponse value) {
        return new JAXBElement<SetProductoResponse>(_SetProductoResponse_QNAME, SetProductoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetTienda }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SetTienda }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidortienda/", name = "setTienda")
    public JAXBElement<SetTienda> createSetTienda(SetTienda value) {
        return new JAXBElement<SetTienda>(_SetTienda_QNAME, SetTienda.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetTiendaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SetTiendaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidortienda/", name = "setTiendaResponse")
    public JAXBElement<SetTiendaResponse> createSetTiendaResponse(SetTiendaResponse value) {
        return new JAXBElement<SetTiendaResponse>(_SetTiendaResponse_QNAME, SetTiendaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tienda }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Tienda }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidortienda/", name = "tienda")
    public JAXBElement<Tienda> createTienda(Tienda value) {
        return new JAXBElement<Tienda>(_Tienda_QNAME, Tienda.class, null, value);
    }

}
