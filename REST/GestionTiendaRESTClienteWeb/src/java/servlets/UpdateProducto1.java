/*
 *  Javier Zudaire
 */
package servlets;

import conexion.ClienteAPI;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objetos.Producto;
import objetos.Tienda;

/**
 *
 * @author javierzudaire
 */
@WebServlet(name = "UpdateProducto", urlPatterns = {"/UpdateProducto"})
public class UpdateProducto1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            ClienteAPI api = new ClienteAPI();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ExportProducto1</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h1>Productos disponibles</h1>");

            Tienda t = api.getTienda();
            ArrayList<Producto> productos = t.getProductos();

            if (productos.isEmpty()) {
                System.out.println("\nTienda vacía, añade productos a la tienda\n");
            }

            int i = 0;
            for (Object p : productos) {
                i += 1;
                out.println("<strong><p>\n* Producto " + i + " *</p></strong>");
                Producto producto = (Producto) p;
                StringBuilder sb = new StringBuilder();
                sb.append("- EAN: ").append(producto.getEAN()).append("<br/>")
                        .append("- Nombre: ").append(producto.getNombre()).append("<br/>")
                        .append("- Descripción: ").append(producto.getDescripcion()).append("<br/>")
                        .append("- Precio: ").append(producto.getPrecio());
                out.println("<p>" + sb.toString() + "</p>");
                System.out.println();

            }

            out.println("<h3>Actualizar producto</h3>");
            out.println("<form action='UpdateProducto2'>");
            out.println("<label>EAN del producto:</label><br>");
            out.println("<input type='text' name='EAN'><br>");
            out.println("</br>");
            out.println("<input type=\"radio\" id=\"1\" name=\"opcion\" value=\"1\">");
            out.println("<label for=\"Nombre\">Nombre</label><br>");
            out.println("<input type=\"radio\" id=\"2\" name=\"opcion\" value=\"2\">");
            out.println("<label for=\"Descripción\">Descripción</label><br>");
            out.println("<input type=\"radio\" id=\"3\" name=\"opcion\" value=\"3\">");
            out.println("<label for=\"Valor\">Precio</label>");
            out.println("</br>");
            out.println("</br>");
            out.println("<label>Nuevo valor:</label><br>");
            out.println("<input type='text' name='valor'><br>");
            out.println("<input type='submit' value='Actualizar'>");
            out.println("</form>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
