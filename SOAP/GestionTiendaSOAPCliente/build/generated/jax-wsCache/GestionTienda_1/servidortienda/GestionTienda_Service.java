
package servidortienda;

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
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "GestionTienda", targetNamespace = "http://servidortienda/", wsdlLocation = "http://localhost:8080/GestionTiendaSOAPServidor/GestionTienda?wsdl")
public class GestionTienda_Service
    extends Service
{

    private final static URL GESTIONTIENDA_WSDL_LOCATION;
    private final static WebServiceException GESTIONTIENDA_EXCEPTION;
    private final static QName GESTIONTIENDA_QNAME = new QName("http://servidortienda/", "GestionTienda");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/GestionTiendaSOAPServidor/GestionTienda?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        GESTIONTIENDA_WSDL_LOCATION = url;
        GESTIONTIENDA_EXCEPTION = e;
    }

    public GestionTienda_Service() {
        super(__getWsdlLocation(), GESTIONTIENDA_QNAME);
    }

    public GestionTienda_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), GESTIONTIENDA_QNAME, features);
    }

    public GestionTienda_Service(URL wsdlLocation) {
        super(wsdlLocation, GESTIONTIENDA_QNAME);
    }

    public GestionTienda_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, GESTIONTIENDA_QNAME, features);
    }

    public GestionTienda_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GestionTienda_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns GestionTienda
     */
    @WebEndpoint(name = "GestionTiendaPort")
    public GestionTienda getGestionTiendaPort() {
        return super.getPort(new QName("http://servidortienda/", "GestionTiendaPort"), GestionTienda.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GestionTienda
     */
    @WebEndpoint(name = "GestionTiendaPort")
    public GestionTienda getGestionTiendaPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://servidortienda/", "GestionTiendaPort"), GestionTienda.class, features);
    }

    private static URL __getWsdlLocation() {
        if (GESTIONTIENDA_EXCEPTION!= null) {
            throw GESTIONTIENDA_EXCEPTION;
        }
        return GESTIONTIENDA_WSDL_LOCATION;
    }

}
