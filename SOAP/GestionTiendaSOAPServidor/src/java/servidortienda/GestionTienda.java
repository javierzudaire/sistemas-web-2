/*
 *  Javier Zudaire
 */
package servidortienda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;

/**
 *
 * @author javierzudaire
 */
@WebService(serviceName = "GestionTienda")
public class GestionTienda {

    ArrayList<Producto> productos = new ArrayList();

    Tienda mitienda = new Tienda();

    XML xml = new XML();

    @WebMethod(operationName = "setTienda")
    public String setTienda(@WebParam(name = "tienda") Tienda t) {

        mitienda = t;
        productos.clear();
        t.setProductos(productos);

        return "Tienda creada";
    }

    @WebMethod(operationName = "getTienda")
    public Tienda getTienda() {

        return mitienda;
    }

    @WebMethod(operationName = "exportTienda")
    public String exportTienda() {

        try {
            return xml.exportTienda(mitienda);
        } catch (JAXBException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Error";
    }

    @WebMethod(operationName = "importTienda")
    public String importTienda(@WebParam(name = "base64") String base64) {

        mitienda = xml.importTienda(base64);

        return "Tienda importada";
    }

    @WebMethod(operationName = "setProducto")
    public String setProducto(@WebParam(name = "producto") Producto p) {

        productos.add(p);
        mitienda.setProductos(productos);

        return "Producto " + p.getNombre() + " añadido";
    }

    @WebMethod(operationName = "getProducto")
    public Producto getProducto(@WebParam(name = "EAN") String EAN) {

        for (Producto p : productos) {
            if (p.getEAN() == EAN) {
                return p;
            }
        }

        return null;
    }

    @WebMethod(operationName = "exportProducto")
    public String exportProducto(@WebParam(name = "EAN") String EAN) {

        for (Producto p : productos) {
            if (p.getEAN().equals(EAN)) {
                try {
                    return xml.exportProducto(p);
                } catch (JAXBException ex) {
                    Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return "Error";
    }

    @WebMethod(operationName = "importProducto")
    public String importProducto(@WebParam(name = "base64") String base64) {

        mitienda.getProductos().add(xml.importProducto(base64));

        return "Producto importado";
    }
}
