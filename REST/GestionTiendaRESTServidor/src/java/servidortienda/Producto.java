/*
 *  Javier Zudaire
 */
package servidortienda;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author javierzudaire
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "producto")

public class Producto implements Serializable {

    @XmlAttribute
    private String EAN;
    @XmlElement
    private String nombre;
    @XmlElement
    private String descripcion;
    @XmlElement
    private Double precio;

    public Producto() {

    }

    //El constructor no deja que el marshall funcione en Producto por eso he hecho esta clase
    public Producto(String EAN, String nombre, String descripcion, Double precio) {
        this.EAN = EAN;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    /**
     * @return the EAN
     */
    public String getEAN() {
        return EAN;
    }

    /**
     * @param EAN the EAN to set
     */
    public void setEAN(String EAN) {
        this.EAN = EAN;
    }

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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("- EAN: ").append(this.EAN).append("\n")
//                .append("- Nombre: ").append(this.nombre).append("\n")
//                .append("- Descripción: ").append(this.descripcion).append("\n")
//                .append("- Precio: ").append(this.precio);
//
//        return sb.toString();
//    }
}
