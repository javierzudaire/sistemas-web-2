/*
 *  Javier Zudaire
 */
package servidortienda;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.Base64;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author javierzudaire
 */
public class XML {

    public String exportTienda(Tienda t) throws JAXBException, FileNotFoundException, IOException {

        File file = new File("tienda.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(Tienda.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(t, file);
        jaxbMarshaller.marshal(t, System.out);

        byte[] bytes = Base64.getEncoder().encode(Files.readAllBytes(file.toPath()));
        String encodedString = new String(bytes);

        return encodedString;
    }

    public String exportProducto(Producto p) throws JAXBException, FileNotFoundException, IOException {

        File file = new File("producto.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(Producto.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(p, file);
        jaxbMarshaller.marshal(p, System.out);

        byte[] bytes = Base64.getEncoder().encode(Files.readAllBytes(file.toPath()));
        String encodedString = new String(bytes);

        return encodedString;
    }

    public Tienda importTienda(String encodedString) {

        byte[] bytes = Base64.getDecoder().decode(encodedString);
        InputStream stream = new ByteArrayInputStream(bytes);
        Tienda t = null;

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Tienda.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            t = (Tienda) jaxbUnmarshaller.unmarshal(stream);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return t;

    }

    public Tienda importTiendaXML(String xml) {

        Tienda t = null;

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Tienda.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            t = (Tienda) jaxbUnmarshaller.unmarshal(new StringReader(xml));

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return t;

    }

    public Producto importProducto(String encodedString) {

        byte[] bytes = Base64.getDecoder().decode(encodedString);
        InputStream stream = new ByteArrayInputStream(bytes);
        Producto p = null;

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Producto.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            p = (Producto) jaxbUnmarshaller.unmarshal(stream);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return p;

    }

    public Producto importProductoXML(String xml) {

        Producto p = null;

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Producto.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            p = (Producto) jaxbUnmarshaller.unmarshal(new StringReader(xml));

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return p;

    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }

}
