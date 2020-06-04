/*
 *  Javier Zudaire
 */
package conexion;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import servidortienda.Producto;
import servidortienda.Tienda;

/**
 *
 * @author javierzudaire
 */
public class ConexionBBDD {

    private DataSource datasource;

    public Connection abrirConexion() throws SQLException, NamingException {
        InitialContext initialContext = new InitialContext();
        datasource = (DataSource) initialContext.lookup("jdbc/gestiontienda");
        Connection conn = datasource.getConnection();
        return conn;
    }

    public boolean autenticar(String email, String password) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {

        Connection conn = abrirConexion();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT pass FROM usuario WHERE email='" + email + "'");

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);
        String pass = null;
        while (rs.next()) {

            pass = rs.getString("pass");

        }

        conn.close();

        if (encoded.equals(pass)) {
            return true;
        } else {
            return false;
        }

    }

    public void addUsuario(String nombre, String apellido, String email, String password) throws SQLException, NamingException, NoSuchAlgorithmException {

        Connection conn = abrirConexion();

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);

        Statement stmt = abrirConexion().createStatement();
        stmt.executeUpdate("INSERT INTO usuario (nombre, apellido, email, pass) VALUES ('" + nombre + "', '" + apellido + "', '" + email + "', '" + encoded + "')");
        conn.close();

    }

    public Tienda getTienda(int usuario_id) throws SQLException, ClassNotFoundException, NamingException {

        Connection conn = abrirConexion();

        Tienda mitienda = new Tienda();

        ArrayList<Producto> productos = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tienda WHERE usuario_id='" + usuario_id + "'");

        while (rs.next()) {

            mitienda.setNombre(rs.getString("nombre"));
            mitienda.setDireccion(rs.getString("direccion"));
            mitienda.setTelefono(rs.getInt("telefono"));

        }

        Statement stmt2 = conn.createStatement();
        ResultSet rs2 = stmt2.executeQuery("SELECT tienda_id FROM tienda WHERE usuario_id='" + usuario_id + "'");
        String tienda_id = null;

        while (rs2.next()) {

            tienda_id = rs2.getString("tienda_id");

        }
        Statement stmt3 = conn.createStatement();
        ResultSet rs3 = stmt3.executeQuery("SELECT * FROM producto WHERE tienda_id='" + tienda_id + "'");
        int i = 0;

        while (rs3.next()) {

            String EAN = rs3.getString("EAN");
            String nombre = rs3.getString("nombre");
            //System.out.println(i + " " + nombre);
            String descripcion = rs3.getString("descripcion");
            Double precio = Double.valueOf(rs3.getString("precio"));

            productos.add(i, new Producto(EAN, nombre, descripcion, precio));
            i++;

        }

        //System.out.println("Producto 1: " + productos.get(0).getNombre());
        mitienda.setProductos(productos);
        conn.close();
        return mitienda;
    }

    public int getUserID(String email) throws SQLException, ClassNotFoundException, NamingException {

        Connection conn = abrirConexion();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT usuario_id FROM usuario WHERE email='" + email + "'");
        int usuario_id = 0;

        while (rs.next()) {

            usuario_id = rs.getInt("usuario_id");

        }

        conn.close();
        return usuario_id;
    }

    public void addTienda(String nombre, String direccion, String telefono, int usuario_id) throws SQLException, NamingException {

        Connection conn = abrirConexion();

        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM tienda WHERE usuario_id='" + usuario_id + "'");

        Statement stmt2 = conn.createStatement();
        stmt2.executeUpdate("INSERT INTO tienda (nombre, direccion, telefono, usuario_id) VALUES ('" + nombre + "', '" + direccion + "', '" + telefono + "', '" + usuario_id + "')");

        Statement stmt3 = abrirConexion().createStatement();
        stmt3.executeUpdate("DELETE FROM producto WHERE tienda_id='" + usuario_id + "'");
        conn.close();

    }

    public void addProducto(String EAN, String nombre, String descripcion, Double precio, int usuario_id) throws SQLException, NamingException {

        Connection conn = abrirConexion();

        Statement stmt2 = conn.createStatement();
        ResultSet rs2 = stmt2.executeQuery("SELECT tienda_id FROM tienda WHERE usuario_id='" + usuario_id + "'");
        String tienda_id = null;

        while (rs2.next()) {

            tienda_id = rs2.getString("tienda_id");
            System.out.println(tienda_id);

        }

        Statement stmt = abrirConexion().createStatement();
        stmt.executeUpdate("INSERT INTO producto (EAN, nombre, descripcion, precio, tienda_id) VALUES ('" + EAN + "', '" + nombre + "', '" + descripcion + "', '" + precio + "', '" + tienda_id + "')");
        conn.close();

    }

    public Producto getProducto(String EAN) throws SQLException, NamingException {

        Connection conn = abrirConexion();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM producto WHERE EAN='" + EAN + "'");
        Producto p = new Producto();

        while (rs.next()) {

            p.setEAN(rs.getString("EAN"));
            p.setNombre(rs.getString("nombre"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setPrecio(Double.valueOf(rs.getString("precio")));

        }

        return p;

    }

    public void deleteProducto(String EAN) throws SQLException, NamingException {

        Connection conn = abrirConexion();

        Statement stmt = abrirConexion().createStatement();
        stmt.executeUpdate("DELETE FROM producto WHERE EAN='" + EAN + "'");
        conn.close();

    }

    public void updateProducto(String EAN, int operacion, String valor) throws SQLException, NamingException {

        Connection conn = abrirConexion();

        switch (operacion) {
            case 1: {
                Statement stmt = abrirConexion().createStatement();
                stmt.executeUpdate("UPDATE producto SET nombre='" + valor + "'WHERE EAN='" + EAN + "'");
                conn.close();
                break;
            }
            case 2: {
                Statement stmt = abrirConexion().createStatement();
                stmt.executeUpdate("UPDATE producto SET descripcion='" + valor + "'WHERE EAN='" + EAN + "'");
                conn.close();
                break;
            }
            case 3: {
                Statement stmt = abrirConexion().createStatement();
                stmt.executeUpdate("UPDATE producto SET precio='" + valor + "'WHERE EAN='" + EAN + "'");
                conn.close();
                break;
            }
            default:
                break;
        }

    }

}
