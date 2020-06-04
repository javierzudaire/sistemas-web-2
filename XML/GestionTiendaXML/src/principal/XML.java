/*
 *  Javier Zudaire
 */
package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import tienda.Producto;
import tienda.Tienda;

/**
 *
 * @author javierzudaire
 */
public class XML {

    public void exportTienda(Tienda t, String filename) throws JAXBException, FileNotFoundException {

        File file = new File("data/" + filename);
        JAXBContext jaxbContext = JAXBContext.newInstance(Tienda.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(t, file);
        jaxbMarshaller.marshal(t, System.out);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        InputStream inStream = new FileInputStream("data/" + filename);
        t = (Tienda) jaxbUnmarshaller.unmarshal(inStream);
    }

    public void exportProducto(Producto p, String filename) throws JAXBException, FileNotFoundException, IOException {

        File file = new File("data/" + filename);
        JAXBContext jaxbContext = JAXBContext.newInstance(Producto.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(p, file);
        jaxbMarshaller.marshal(p, System.out);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        InputStream inStream = new FileInputStream("data/" + filename);
        p = (Producto) jaxbUnmarshaller.unmarshal(inStream);

    }

    public Tienda importTienda(String file) {

        File xmlFile = new File("data/" + file);
        Tienda t = null;

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Tienda.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            t = (Tienda) jaxbUnmarshaller.unmarshal(xmlFile);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return t;

    }

    public Producto importProducto(String file) {

        File xmlFile = new File("data/" + file);
        Producto p = null;

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Producto.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            p = (Producto) jaxbUnmarshaller.unmarshal(xmlFile);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return p;

    }

}
