/*
 *  Javier Zudaire
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servidortienda.GestionTienda_Service;
import servidortienda.Producto;
import servidortienda.Tienda;

/**
 *
 * @author javierzudaire
 */
public class ExportProducto1 extends HttpServlet {

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

            GestionTienda_Service service = new GestionTienda_Service();
            servidortienda.GestionTienda tienda = service.getGestionTiendaPort();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ExportProducto1</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h1>Productos disponibles</h1>");

            servidortienda.Tienda t = tienda.getTienda();
            Tienda.Productos productos = t.getProductos();

            if (productos.getProducto().isEmpty()) {
                System.out.println("\nTienda vacía, añade productos a la tienda\n");
            }

            int i = 0;
            for (Object p : productos.getProducto()) {
                i += 1;
                out.println("<strong><p>\n* Producto " + i + " *</p></strong>");
                Producto producto = (Producto) p;
                StringBuilder sb = new StringBuilder();
                sb.append("- EAN: ").append(producto.getEan()).append("<br/>")
                        .append("- Nombre: ").append(producto.getNombre()).append("<br/>")
                        .append("- Descripción: ").append(producto.getDescripcion()).append("<br/>")
                        .append("- Precio: ").append(producto.getPrecio());
                out.println("<p>" + sb.toString() + "</p>");
                System.out.println();

            }

            out.println("<h3>Exportar producto</h3>");
            out.println("<form action='ExportProducto2'>");
            out.println("<label>EAN del producto:</label><br>");
            out.println("<input type='text' name='EAN'><br>");
            out.println("<label>Nombre de fichero:</label><br>");
            out.println("<input type='text' name='fichero'><br><br>");
            out.println("<input type='submit' value='Exportar'>");
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
