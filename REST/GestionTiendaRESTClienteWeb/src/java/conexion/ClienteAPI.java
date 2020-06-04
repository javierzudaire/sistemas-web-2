/*
 *  Javier Zudaire
 */
package conexion;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import objetos.Producto;
import objetos.Tienda;

/**
 *
 * @author javierzudaire
 */
public class ClienteAPI {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/GestionTiendaRESTServidor/webresources/gestiontienda";

    public String login(String email, String password) throws ClientErrorException {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("login");
        Form form = new Form();
        form.param("email", email);
        form.param("pass", password);

        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(form), String.class);
    }

    public String signup(String nombre, String apellido, String email, String password) throws ClientErrorException {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("signup");
        Form form = new Form();
        form.param("nombre", nombre);
        form.param("apellido", apellido);
        form.param("email", email);
        form.param("pass", password);

        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(form), String.class);
    }

    public void setTienda(Tienda t) throws ClientErrorException {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("tienda");
        webTarget.request(MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, "Bearer " + Token.getToken()).post(Entity.entity(t, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public Tienda getTienda() throws ClientErrorException {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("tienda");
        return webTarget.request(MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, "Bearer " + Token.getToken()).get(Tienda.class);
    }

    public void setProducto(Producto p) throws ClientErrorException {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("producto");
        webTarget.request(MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, "Bearer " + Token.getToken()).post(Entity.entity(p, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public Producto getProducto(String EAN) throws ClientErrorException {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("producto/" + EAN);
        return webTarget.request(MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, "Bearer " + Token.getToken()).get(Producto.class);
    }

    public void deleteProducto(String EAN) throws ClientErrorException {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("producto/" + EAN);
        webTarget.request(MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, "Bearer " + Token.getToken()).delete();
    }

    public void updateProducto(String EAN, String operacion, String valor) throws ClientErrorException {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("producto/" + EAN);
        Form form = new Form();
        form.param("operacion", operacion);
        form.param("valor", valor);
        webTarget.request(MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, "Bearer " + Token.getToken()).put(Entity.form(form), String.class);
    }

    public String exportTienda() throws ClientErrorException {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("tienda/export");
        return webTarget.request(MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, "Bearer " + Token.getToken()).get(String.class);
    }

    public void importTienda(String tienda) throws ClientErrorException {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("tienda/import");
        Form form = new Form();
        form.param("tienda", tienda);
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).header(HttpHeaders.AUTHORIZATION, "Bearer " + Token.getToken()).post(Entity.form(form), String.class);
    }

    public String exportProducto(String EAN) throws ClientErrorException {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("producto/" + EAN + "/export");
        return webTarget.request(MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, "Bearer " + Token.getToken()).get(String.class);
    }

    public void importProducto(String producto) throws ClientErrorException {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("producto/import");
        Form form = new Form();
        form.param("producto", producto);
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).header(HttpHeaders.AUTHORIZATION, "Bearer " + Token.getToken()).post(Entity.form(form), String.class);
    }

    public String convertTienda(String tienda) throws ClientErrorException {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("tienda/convertir");
        Form form = new Form();
        form.param("tienda", tienda);
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(form), String.class);
    }

    public String convertProducto(String producto) throws ClientErrorException {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("producto/convertir");
        Form form = new Form();
        form.param("producto", producto);
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(form), String.class);
    }

    public void close() {
        client.close();
    }

}
