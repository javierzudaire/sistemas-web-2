/*
 *  Javier Zudaire
 */
package servlets;

import conexion.ClienteAPI;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objetos.Tienda;

/**
 *
 * @author javierzudaire
 */
public class GestionTienda extends HttpServlet {

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

            Tienda mitienda = new Tienda();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Gestion Tienda</title>");
            out.println("</head>");
            out.println("<body>");
            try {
                out.println("<h1>Gestión de " + api.getTienda().getNombre() + "</h1>");
            } catch (Exception e) {
                response.sendRedirect("CrearTienda.html");

            }

            out.println("<p>Bienvenido!</p>");
            out.println("<center>");
            out.println("<h2><u>MENÚ</u></H2>");
            out.println("</br>");
            out.println("<a href='AddProducto.html'>1. Añadir producto</a>");
            out.println("</br>");
            out.println("</br>");
            out.println("<a href='GetProductos'>2. Listar productos</a>");
            out.println("</br>");
            out.println("</br>");
            out.println("<a href='GetProducto.html'>3. Obtener producto</a>");
            out.println("</br>");
            out.println("</br>");
            out.println("<a href='DeleteProducto.html'>4. Borrar producto</a>");
            out.println("</br>");
            out.println("</br>");
            out.println("<a href='UpdateProducto1'>5. Actualizar producto</a>");
            out.println("</br>");
            out.println("</br>");
            out.println("<a href='ExportTienda.html'>6. Exportar tienda</a>");
            out.println("</br>");
            out.println("</br>");
            out.println("<a href='ImportTienda.html'>7. Importar tienda</a>");
            out.println("</br>");
            out.println("</br>");
            out.println("<a href='ExportProducto1'>8. Exportar producto</a>");
            out.println("</br>");
            out.println("</br>");
            out.println("<a href='ImportProducto.html'>9. Importar producto</a>");
            out.println("</br>");
            out.println("</br>");
            out.println("<a href='InformacionTienda'>10. Información tienda</a>");
            out.println("</br>");
            out.println("</br>");
            out.println("<a href='CrearTienda.html'>11. Nueva tienda</a>");
            out.println("</br>");
            out.println("</br>");
            out.println("<a href='index.html'>12. Salir</a>");
            out.println("</center>");
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
