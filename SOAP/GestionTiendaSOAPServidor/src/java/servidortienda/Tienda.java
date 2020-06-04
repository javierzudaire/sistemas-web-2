/*
 *  Javier Zudaire
 */
package servidortienda;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author javierzudaire
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tienda")
@XmlSeeAlso({Producto.class})

public class Tienda implements Serializable {

    @XmlAttribute
    private String nombre;
    @XmlElement
    private String direccion;
    @XmlElement
    private Integer telefono;
    @XmlElementWrapper(name = "productos")
    @XmlElement
    private ArrayList producto;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public Integer getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the productos
     */
    public ArrayList getProductos() {
        return producto;
    }

    /**
     * @param productos the productos to set
     */
    public void setProductos(ArrayList productos) {
        this.producto = productos;
    }

}
