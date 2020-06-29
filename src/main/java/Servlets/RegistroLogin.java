/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.AdministradorController;
import Controlador.Usuarios;
import Modelos.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Complx
 */
public class RegistroLogin extends HttpServlet {

    private String usuariocorreo;
    private String contrasenacorreo;
    AdministradorController adminC;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*Funcion que se usa al registrar un usuario, tomamos valores de los campos, y enviamos las credenciales al correo del personal asociado a esa cuenta*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Usuario newuser = new Usuario();
            newuser.setUsuario(request.getParameter("newuser"));
            newuser.setContrasena(request.getParameter("newpassword"));
            newuser.setR_tipo(Integer.parseInt(request.getParameter("newtipo")));
            newuser.setR_personal(Integer.parseInt(request.getParameter("selectuser")));
            usuariocorreo = newuser.getUsuario();
            contrasenacorreo = newuser.getContrasena();
            String contra = AES.encrypt(newuser.getContrasena(), "terra");
            newuser.setContrasena(contra);
            Usuarios usuarios = new Usuarios();
            if (usuarios.registroUsuario(newuser) != 0) {
                adminC = new AdministradorController();
                adminC.correoUsuario(usuariocorreo,contrasenacorreo,newuser.getR_personal());
                response.sendRedirect(request.getContextPath() + "/indexpre.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/Login.jsp");
            }
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
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
