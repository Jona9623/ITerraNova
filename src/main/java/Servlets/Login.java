/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.AdministradorController;
import Controlador.Usuarios;
import Modelos.TbPersonal;
import Modelos.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Complx
 */
public class Login extends HttpServlet {

    AdministradorController adminC = new AdministradorController();
    List<TbPersonal> listpersonal = new ArrayList<>();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*Se ejecuta cuando se inicia sesion, tomamos parametros de los campos de usuario y contraseña, se hace la validacion correspondiente para iniciar sesion o redireccionar a la pagina de 
    login para que intente de nuevo*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Usuario user = new Usuario();
            user.setUsuario(request.getParameter("user"));
            user.setContrasena(request.getParameter("password"));
            String contra = AES.encrypt(user.getContrasena(), "terra");
            user.setContrasena(contra);
            Usuarios usuarios = new Usuarios();
            user = usuarios.iniciarSesion(user);
            if (user.getIdtbusuario() > 0) {
                HttpSession sesion = request.getSession(true);
                //Definimos los permisos que tendra el usuario que inicio sesion
                if (user.getR_tipo() == 1) {
                    sesion.setAttribute("id", user.getIdtbusuario());
                }
                if (user.getR_tipo() == 2) {
                    sesion.setAttribute("id", user.getIdtbusuario());
                }
                if (user.getR_tipo() == 3) {
                    sesion.setAttribute("id", user.getIdtbusuario());
                }
                if (user.getR_tipo() == 4) {
                    sesion.setAttribute("id", user.getIdtbusuario());
                }
                if (user.getR_tipo() == 5) {
                    sesion.setAttribute("id", user.getIdtbusuario());
                }
                //Se asignan atributos a la variable sesion para su posterior tratado como permisos, etc
                sesion.setAttribute("user", user.getUsuario());
                sesion.setAttribute("password", user.getContrasena());
                sesion.setAttribute("tipo", user.getR_tipo());
                sesion.setAttribute("personal", user.getR_personal());
                listpersonal = adminC.getPersonal(Integer.parseInt(request.getParameter("input")));
                request.setAttribute("listpersonal", listpersonal);
                RequestDispatcher rd = request.getRequestDispatcher("/indexpre.jsp");
                rd.forward(request, response);
                //response.sendRedirect(request.getContextPath() + "/indexpre.jsp");
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
