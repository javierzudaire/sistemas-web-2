
package servidortienda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for setTienda complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="setTienda"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tienda" type="{http://servidortienda/}tienda" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setTienda", propOrder = {
    "tienda"
})
public class SetTienda {

    protected Tienda tienda;

    /**
     * Gets the value of the tienda property.
     * 
     * @return
     *     possible object is
     *     {@link Tienda }
     *     
     */
    public Tienda getTienda() {
        return tienda;
    }

    /**
     * Sets the value of the tienda property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tienda }
     *     
     */
    public void setTienda(Tienda value) {
        this.tienda = value;
    }

}
