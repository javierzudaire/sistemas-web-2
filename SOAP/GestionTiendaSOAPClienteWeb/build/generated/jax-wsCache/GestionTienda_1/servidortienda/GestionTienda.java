
package servidortienda;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0
 * Generated source version: 2.2
 * 
 */
@WebService(name = "GestionTienda", targetNamespace = "http://servidortienda/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface GestionTienda {


    /**
     * 
     * @param base64
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "importTienda", targetNamespace = "http://servidortienda/", className = "servidortienda.ImportTienda")
    @ResponseWrapper(localName = "importTiendaResponse", targetNamespace = "http://servidortienda/", className = "servidortienda.ImportTiendaResponse")
    @Action(input = "http://servidortienda/GestionTienda/importTiendaRequest", output = "http://servidortienda/GestionTienda/importTiendaResponse")
    public String importTienda(
        @WebParam(name = "base64", targetNamespace = "")
        String base64);

    /**
     * 
     * @param producto
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "setProducto", targetNamespace = "http://servidortienda/", className = "servidortienda.SetProducto")
    @ResponseWrapper(localName = "setProductoResponse", targetNamespace = "http://servidortienda/", className = "servidortienda.SetProductoResponse")
    @Action(input = "http://servidortienda/GestionTienda/setProductoRequest", output = "http://servidortienda/GestionTienda/setProductoResponse")
    public String setProducto(
        @WebParam(name = "producto", targetNamespace = "")
        Producto producto);

    /**
     * 
     * @param ean
     * @return
     *     returns servidortienda.Producto
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getProducto", targetNamespace = "http://servidortienda/", className = "servidortienda.GetProducto")
    @ResponseWrapper(localName = "getProductoResponse", targetNamespace = "http://servidortienda/", className = "servidortienda.GetProductoResponse")
    @Action(input = "http://servidortienda/GestionTienda/getProductoRequest", output = "http://servidortienda/GestionTienda/getProductoResponse")
    public Producto getProducto(
        @WebParam(name = "EAN", targetNamespace = "")
        String ean);

    /**
     * 
     * @param ean
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "exportProducto", targetNamespace = "http://servidortienda/", className = "servidortienda.ExportProducto")
    @ResponseWrapper(localName = "exportProductoResponse", targetNamespace = "http://servidortienda/", className = "servidortienda.ExportProductoResponse")
    @Action(input = "http://servidortienda/GestionTienda/exportProductoRequest", output = "http://servidortienda/GestionTienda/exportProductoResponse")
    public String exportProducto(
        @WebParam(name = "EAN", targetNamespace = "")
        String ean);

    /**
     * 
     * @param base64
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "importProducto", targetNamespace = "http://servidortienda/", className = "servidortienda.ImportProducto")
    @ResponseWrapper(localName = "importProductoResponse", targetNamespace = "http://servidortienda/", className = "servidortienda.ImportProductoResponse")
    @Action(input = "http://servidortienda/GestionTienda/importProductoRequest", output = "http://servidortienda/GestionTienda/importProductoResponse")
    public String importProducto(
        @WebParam(name = "base64", targetNamespace = "")
        String base64);

    /**
     * 
     * @param tienda
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "setTienda", targetNamespace = "http://servidortienda/", className = "servidortienda.SetTienda")
    @ResponseWrapper(localName = "setTiendaResponse", targetNamespace = "http://servidortienda/", className = "servidortienda.SetTiendaResponse")
    @Action(input = "http://servidortienda/GestionTienda/setTiendaRequest", output = "http://servidortienda/GestionTienda/setTiendaResponse")
    public String setTienda(
        @WebParam(name = "tienda", targetNamespace = "")
        Tienda tienda);

    /**
     * 
     * @return
     *     returns servidortienda.Tienda
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getTienda", targetNamespace = "http://servidortienda/", className = "servidortienda.GetTienda")
    @ResponseWrapper(localName = "getTiendaResponse", targetNamespace = "http://servidortienda/", className = "servidortienda.GetTiendaResponse")
    @Action(input = "http://servidortienda/GestionTienda/getTiendaRequest", output = "http://servidortienda/GestionTienda/getTiendaResponse")
    public Tienda getTienda();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "exportTienda", targetNamespace = "http://servidortienda/", className = "servidortienda.ExportTienda")
    @ResponseWrapper(localName = "exportTiendaResponse", targetNamespace = "http://servidortienda/", className = "servidortienda.ExportTiendaResponse")
    @Action(input = "http://servidortienda/GestionTienda/exportTiendaRequest", output = "http://servidortienda/GestionTienda/exportTiendaResponse")
    public String exportTienda();

}
