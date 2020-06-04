/*
 *  Javier Zudaire
 */
package servidortienda;

import conexion.ConexionBBDD;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author javierzudaire
 */
@Path("gestiontienda")
public class GestionTienda {

    XML xml = new XML();

    ConexionBBDD conexion = new ConexionBBDD();
    JwtTokenUtil token = new JwtTokenUtil();

    @POST
    @Path("login")
    public String login(@FormParam("email") String email, @FormParam("pass") String pass) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {

        if (conexion.autenticar(email, pass) == false) {
            return "Usuario/contraseña incorrectos";
        }

        return token.generateToken(email);

    }

    @POST
    @Path("signup")
    public String signup(@FormParam("nombre") String nombre, @FormParam("apellido") String apellido, @FormParam("email") String email, @FormParam("pass") String pass) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {

        conexion.addUsuario(nombre, apellido, email, pass);

        return token.generateToken(email);

    }

    @POST
    @Authenticate
    @Path("tienda")
    @Consumes(MediaType.APPLICATION_XML)
    public void setTienda(@Context SecurityContext securityContext, String tienda) {

        Principal principal = securityContext.getUserPrincipal();
        String email = principal.getName();
        int usuario_id = 0;
        try {
            usuario_id = conexion.getUserID(email);
        } catch (SQLException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        Tienda t = xml.importTiendaXML(tienda);

        try {
            conexion.addTienda(t.getNombre(), t.getDireccion(), Integer.toString(t.getTelefono()), usuario_id);
        } catch (SQLException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Tienda creada");
    }

    @GET
    @Authenticate
    @Path("tienda")
    @Produces(MediaType.APPLICATION_XML)
    public Tienda getTienda(@Context SecurityContext securityContext) {

        Principal principal = securityContext.getUserPrincipal();
        String email = principal.getName();
        int usuario_id = 0;
        try {
            usuario_id = conexion.getUserID(email);
        } catch (SQLException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (conexion.getTienda(usuario_id).getNombre() == null) {
                System.out.println("TIENDA NULL");
                return null;
            }

            return conexion.getTienda(usuario_id);

        } catch (SQLException ex) {
            System.out.print("4");
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.print("5");
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            System.out.print("6");
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Authenticate
    @Path("tienda/export")
    @Produces(MediaType.APPLICATION_XML)
    public String exportTienda(@Context SecurityContext securityContext) {

        Principal principal = securityContext.getUserPrincipal();
        String email = principal.getName();
        int usuario_id = 0;
        try {
            usuario_id = conexion.getUserID(email);
        } catch (SQLException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            try {
                return xml.exportTienda(conexion.getTienda(usuario_id));
            } catch (JAXBException ex) {
                Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Error";

    }

    @POST
    @Authenticate
    @Path("tienda/import")
    public void importTienda(@Context SecurityContext securityContext, @FormParam("tienda") String tienda) {

        Principal principal = securityContext.getUserPrincipal();
        String email = principal.getName();
        int usuario_id = 0;
        try {
            usuario_id = conexion.getUserID(email);
        } catch (SQLException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        Tienda t = xml.importTienda(tienda);
        try {
            conexion.addTienda(t.getNombre(), t.getDireccion(), t.getTelefono().toString(), usuario_id);
        } catch (SQLException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < t.getProductos().size(); i++) {
            Producto p = (Producto) t.getProductos().get(i);
            try {
                conexion.addProducto(p.getEAN(), p.getNombre(), p.getDescripcion(), p.getPrecio(), usuario_id);
            } catch (SQLException ex) {
                Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("Tienda importada");

    }

    @POST
    @Authenticate
    @Path("producto")
    @Consumes(MediaType.APPLICATION_XML)
    public String setProducto(@Context SecurityContext securityContext, String producto) {

        Principal principal = securityContext.getUserPrincipal();
        String email = principal.getName();
        int usuario_id = 0;
        try {
            usuario_id = conexion.getUserID(email);
        } catch (SQLException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(producto);
        Producto p = xml.importProductoXML(producto);

        try {
            conexion.addProducto(p.getEAN(), p.getNombre(), p.getDescripcion(), p.getPrecio(), usuario_id);
        } catch (SQLException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Producto " + p.getNombre() + " añadido";
    }

    @GET
    @Authenticate
    @Path("producto/{EAN}")
    @Produces(MediaType.APPLICATION_XML)
    public Producto getProducto(@Context SecurityContext securityContext, @PathParam("EAN") String EAN) {

        Principal principal = securityContext.getUserPrincipal();
        String email = principal.getName();
        int usuario_id = 0;
        try {
            usuario_id = conexion.getUserID(email);
        } catch (SQLException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            return conexion.getProducto(EAN);

        } catch (SQLException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    @GET
    @Authenticate
    @Path("producto/{EAN}/export")
    @Produces(MediaType.APPLICATION_XML)
    public String exportProducto(@Context SecurityContext securityContext, @PathParam("EAN") String EAN) {

        Principal principal = securityContext.getUserPrincipal();
        String email = principal.getName();
        int usuario_id = 0;
        try {
            usuario_id = conexion.getUserID(email);
        } catch (SQLException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<Producto> productos = new ArrayList();
        try {
            productos = conexion.getTienda(usuario_id).getProductos();
        } catch (SQLException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    @POST
    @Authenticate
    @Path("producto/import")
    public String importProducto(@Context SecurityContext securityContext, @FormParam("producto") String producto) {

        Principal principal = securityContext.getUserPrincipal();
        String email = principal.getName();
        int usuario_id = 0;
        try {
            usuario_id = conexion.getUserID(email);
        } catch (SQLException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        Producto p = xml.importProducto(producto);
        try {
            conexion.addProducto(p.getEAN(), p.getNombre(), p.getDescripcion(), p.getPrecio(), usuario_id);
        } catch (SQLException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Producto importado";
    }

    @DELETE
    @Authenticate
    @Path("producto/{EAN}")
    @Produces(MediaType.APPLICATION_XML)
    public String deleteProducto(@Context SecurityContext securityContext, @PathParam("EAN") String EAN) {

        try {
            conexion.deleteProducto(EAN);
        } catch (SQLException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Producto borrado";
    }

    @PUT
    @Authenticate
    @Path("producto/{EAN}")
    @Produces(MediaType.APPLICATION_XML)
    public void updateProducto(@Context SecurityContext securityContext, @PathParam("EAN") String EAN, @FormParam("operacion") String operacion, @FormParam("valor") String valor) {

        try {
            conexion.updateProducto(EAN, Integer.parseInt(operacion), valor);
        } catch (SQLException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @POST
    @Path("tienda/convertir")
    public String convertTienda(@Context ServletContext context, @FormParam("tienda") String tienda) throws IOException {

        TransformerFactory tFactory = TransformerFactory.newInstance();

        String fullPath = context.getRealPath("/WEB-INF/tienda.xsl");

        Source xslDoc = new StreamSource(fullPath);

        byte[] bytes = Base64.getDecoder().decode(tienda);
        InputStream stream = new ByteArrayInputStream(bytes);

        Source xmlDoc = new StreamSource(stream);

        File htmlFile = new File("tienda.html");

        Transformer trasform = null;
        try {
            trasform = tFactory.newTransformer(xslDoc);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            trasform.transform(xmlDoc, new StreamResult(htmlFile));
        } catch (TransformerException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        byte[] bytes2 = Base64.getEncoder().encode(Files.readAllBytes(htmlFile.toPath()));
        String encodedString = new String(bytes2);

        return encodedString;
    }

    @POST
    @Path("producto/convertir")
    public String convertProducto(@Context ServletContext context, @FormParam("producto") String producto) throws IOException {

        TransformerFactory tFactory = TransformerFactory.newInstance();

        String fullPath = context.getRealPath("/WEB-INF/producto.xsl");

        Source xslDoc = new StreamSource(fullPath);

        byte[] bytes = Base64.getDecoder().decode(producto);
        InputStream stream = new ByteArrayInputStream(bytes);

        Source xmlDoc = new StreamSource(stream);

        File htmlFile = new File("producto.html");

        Transformer trasform = null;
        try {
            trasform = tFactory.newTransformer(xslDoc);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            trasform.transform(xmlDoc, new StreamResult(htmlFile));
        } catch (TransformerException ex) {
            Logger.getLogger(GestionTienda.class.getName()).log(Level.SEVERE, null, ex);
        }

        byte[] bytes2 = Base64.getEncoder().encode(Files.readAllBytes(htmlFile.toPath()));
        String encodedString = new String(bytes2);

        return encodedString;
    }

}
