/*
 *  Javier Zudaire
 */
package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import net.sf.saxon.xqj.SaxonXQDataSource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import tienda.Producto;
import tienda.Tienda;

/**
 *
 * @author javierzudaire
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws java.io.IOException, ParserConfigurationException, SAXException, InterruptedException, XPathExpressionException, XQException {

        ArrayList<Producto> productos = new ArrayList();

        Tienda mitienda = new Tienda();

        Scanner sc = new Scanner(System.in);

        System.out.println("--- CREACIÓN TIENDA ---");
        System.out.print("\nIntroduce nombre: ");
        mitienda.setNombre(sc.nextLine());
        System.out.print("Introduce dirección: ");
        mitienda.setDireccion(sc.nextLine());
        System.out.print("Introduce teléfono: ");
        try {
            mitienda.setTelefono(Integer.parseInt(sc.nextLine()));
        } catch (Exception e) {
            System.out.println("\n-- Error: teléfono no válido --\n");
            System.out.print("Introduce teléfono: ");
            mitienda.setTelefono(Integer.parseInt(sc.nextLine()));
            System.out.println("");
        }
        mitienda.setProductos(productos);

        XML xml = new XML();

        System.out.println("===============================");
        System.out.println("|             MENU            |");
        System.out.println("===============================");
        System.out.println("| Opciones:                   |");
        System.out.println("|     1. Añadir producto      |");
        System.out.println("|     2. Listar productos     |");
        System.out.println("|     3. Exportar tienda      |");
        System.out.println("|     4. Importar tienda      |");
        System.out.println("|     5. Exportar producto    |");
        System.out.println("|     6. Importar producto    |");
        System.out.println("|     7. Validar con DTD      |");
        System.out.println("|     8. Validar con XSD      |");
        System.out.println("|     9. Sentencia XPath      |");
        System.out.println("|     10. Consulta XQuery     |");
        System.out.println("|     11. Salir               |");
        System.out.println("===============================\n");

        int opcionNum = 0;

        while (opcionNum != 11) {

            System.out.print("Seleccionar opción (* para mostrar menu): ");
            String opcionn = sc.next();

            if (opcionn.equals("*")) {

                System.out.println("\n===============================");
                System.out.println("|             MENU            |");
                System.out.println("===============================");
                System.out.println("| Opciones:                   |");
                System.out.println("|     1. Añadir producto      |");
                System.out.println("|     2. Listar productos     |");
                System.out.println("|     3. Exportar tienda      |");
                System.out.println("|     4. Importar tienda      |");
                System.out.println("|     5. Exportar producto    |");
                System.out.println("|     6. Importar producto    |");
                System.out.println("|     7. Validar con DTD      |");
                System.out.println("|     8. Validar con XSD      |");
                System.out.println("|     9. Sentencia XPath      |");
                System.out.println("|     10. Consulta XQuery     |");
                System.out.println("|     11. Salir               |");
                System.out.println("===============================\n");

                System.out.print("Seleccionar opción: ");
                opcionn = sc.nextLine();
            }

            try {
                opcionNum = Integer.parseInt(opcionn);
            } catch (Exception e) {
                System.out.println("Opcion inválida");
            }

            switch (opcionNum) {
                case 1:

                    Producto x = new Producto();

                    System.out.println("\n--- AÑADIR PRODUCTO ---");
                    System.out.print("\nIntroduce EAN: ");
                    sc.nextLine();
                    x.setEAN(sc.nextLine());
                    System.out.print("Introduce nombre: ");
                    x.setNombre(sc.nextLine());
                    System.out.print("Introduce descripción: ");
                    x.setDescripcion(sc.nextLine());
                    System.out.print("Introduce precio: ");
                    try {
                        x.setPrecio(Double.parseDouble(sc.next()));
                    } catch (Exception e) {
                        System.out.println("\n-- Error: precio no válido --\n");
                        System.out.println("\n-- Formato: X.XX --\n");
                        System.out.print("Introduce precio: ");
                        x.setPrecio(Double.parseDouble(sc.next()));
                    }

                    System.out.println();

                    productos.add(x);
                    mitienda.setProductos(productos);

                    break;

                case 2:
                    System.out.println("\n--- LISTA DE PRODUCTOS --- ");
                    ArrayList<Producto> misproductos = mitienda.getProductos();
                    int i = 0;
                    if (misproductos.size() == 0) {
                        System.out.println("\nTienda vacía, añade productos a la tienda\n");
                    }

                    for (Producto pp : misproductos) {
                        i += 1;
                        System.out.println("\n* Producto " + i + " *");
                        System.out.println(pp.toString());
                        System.out.println();

                    }
                    break;

                case 3:
                    System.out.println("\n--- EXPORTAR TIENDA --- ");
                    System.out.print("\nIntroducir nombre de fichero: ");
                    String filename = sc.next();
                     {
                        try {
                            xml.exportTienda(mitienda, filename);
                        } catch (JAXBException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.out.println();
                    break;

                case 4:
                    System.out.println("\n--- IMPORTAR TIENDA --- ");
                    System.out.print("\nIntroduce nombre de fichero: ");
                    String ficheroTienda = sc.next();
                    mitienda = xml.importTienda(ficheroTienda);
                    System.out.println();
                    break;

                case 5:
                    System.out.println("\n--- EXPORTAR PRODUCTO --- ");
                    System.out.print("\nIntroducir nombre de fichero: ");
                    String filename2 = sc.next();
                    ArrayList<Producto> misproductos2 = mitienda.getProductos();
                    int ii = 0;
                    for (Producto pp : misproductos2) {
                        ii += 1;
                        System.out.println("\n* Producto " + ii + " *");
                        System.out.println(pp.toString());
                        System.out.println();

                    }

                    System.out.print("Introduce número del producto a exportar: ");
                    String productoExport = sc.next();
                    Producto export = misproductos2.get(Integer.parseInt(productoExport) - 1);
                    System.out.println();
                    try {
                        xml.exportProducto(export, filename2);
                    } catch (JAXBException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println();
                    break;

                case 6:
                    System.out.println("\n--- IMPORTAR PRODUCTO --- ");
                    System.out.print("\nIntroduce nombre de fichero: ");
                    String ficheroProducto = sc.next();
                    Producto producto2 = xml.importProducto(ficheroProducto);
                    mitienda.getProductos().add(producto2);
                    System.out.println();
                    break;

                case 7:
                    System.out.println("\n--- VALIDAR TIENDA CON DTD --- ");
                    System.out.print("\nIntroduce nombre de fichero: ");
                    String ficheroTienda1 = sc.next();
                    System.out.print("\nValidando tienda.");
                    Thread.sleep(1000);
                    System.out.print(".");
                    Thread.sleep(1000);
                    System.out.print(".");

                    try {
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setNamespaceAware(true);

                        DocumentBuilder builder = factory.newDocumentBuilder();

                        builder.setErrorHandler(new XMLErrorHandler());

                        Document document = builder.parse(new InputSource("data/" + ficheroTienda1));
                    } catch (Exception e) {
                        System.out.println("\nError en la validación\n");
                        break;
                    }

                    System.out.println("\nTienda validada correctamente\n");

                    break;

                case 8:
                    System.out.println("\n--- VALIDAR TIENDA CON ESQUEMA XSD --- ");
                    System.out.print("\nIntroduce nombre de fichero: ");
                    String ficheroTienda2 = sc.next();
                    System.out.print("\nValidando tienda.");
                    Thread.sleep(1000);
                    System.out.print(".");
                    Thread.sleep(1000);
                    System.out.print(".");

                    try {
                        SchemaFactory factory
                                = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                        Schema schema = factory.newSchema(new File("src/tienda/schema.xsd"));
                        Validator validator = schema.newValidator();
                        validator.validate(new StreamSource(new File("data/" + ficheroTienda2)));
                    } catch (IOException e) {
                        System.out.println("\nException: " + e.getMessage());
                        System.out.println("Error en la validación\n");
                        break;
                    } catch (SAXException e1) {
                        System.out.println("\nSAX Exception: " + e1.getMessage());
                        System.out.println("Error en la validación\n");
                        break;
                    }

                    System.out.println("\nTienda validada correctamente\n");
                    break;

                case 9:
                    System.out.println("\n--- SENTENCIA XPATH --- ");
                    System.out.print("\nIntroduce fichero: ");
                    String ficheroXPath = sc.next();
                    System.out.print("\nIntroduce sentencia XPath: ");
                    String sentenciaXPath = sc.next();

                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    factory.setNamespaceAware(true);
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document doc = builder.parse("data/" + ficheroXPath);

                    XPathFactory xpathfactory = XPathFactory.newInstance();
                    XPath xpath = xpathfactory.newXPath();

                    XPathExpression expr = xpath.compile(sentenciaXPath);
                    Object result = expr.evaluate(doc, XPathConstants.NODESET);
                    NodeList nodes = (NodeList) result;
                    for (int z = 0; z < nodes.getLength(); z++) {
                        System.out.println(nodes.item(z).getNodeValue());
                    }
                    break;

                case 10:
                    System.out.println("\n--- CONSULTA XQUERY --- ");
                    System.out.print("\nIntroduce fichero con XQuery (ejemplo: tienda.xqy): ");
                    String ficheroXQuery = sc.next();
                    System.out.println("\nRESULTADO:");
                    InputStream inputStream = new FileInputStream(new File("data/" + ficheroXQuery));
                    XQDataSource ds = new SaxonXQDataSource();
                    XQConnection conn = ds.getConnection();
                    XQPreparedExpression exp = conn.prepareExpression(inputStream);
                    XQResultSequence sequenceResult = exp.executeQuery();

                    while (sequenceResult.next()) {
                        System.out.println(sequenceResult.getItemAsString(null));
                    }
                    System.out.println();
                    break;

                case 11:
                    System.out.println("\nSaliendo...");
                    break;
            }
        }

    }

}
