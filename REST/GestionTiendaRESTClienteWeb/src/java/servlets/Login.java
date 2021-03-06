/*
 *  Javier Zudaire
 */
package servlets;

import conexion.ClienteAPI;
import conexion.Token;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author javierzudaire
 */
public class Login extends HttpServlet {

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

        ClienteAPI api = new ClienteAPI();

        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        String apiResponse = api.login(email, pass);

        System.out.println("RESPONSE " + response);

        if (apiResponse.equals("Usuario/contraseña incorrectos")) {
            response.sendRedirect("index.html");

        } else {
            Token.setToken(apiResponse);
            response.sendRedirect("GestionTienda");
        }
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
