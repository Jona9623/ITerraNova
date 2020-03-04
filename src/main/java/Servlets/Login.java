/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.Usuarios;
import Modelos.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.faces.context.FacesContext;
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
        try {
            Usuario user = new Usuario();
            user.setUsuario(request.getParameter("user"));
            user.setContrasena(request.getParameter("password"));
            String contra = AES.encrypt(user.getContrasena(), "terra");
            user.setContrasena(contra);
            Usuarios usuarios = new Usuarios();
            user = usuarios.iniciarSesion(user);
            if(user.getIdtbusuario() > 0){
                HttpSession sesion = request.getSession(true);
                if(user.getR_tipo() == 1){
                    sesion.setAttribute("id", user.getIdtbusuario());
                }
                if(user.getR_tipo() == 2){
                    sesion.setAttribute("id", user.getIdtbusuario());
                }
                if(user.getR_tipo() == 3){
                    sesion.setAttribute("id", user.getIdtbusuario());
                }
                if(user.getR_tipo() == 4){
                    sesion.setAttribute("id", user.getIdtbusuario());
                }
                if(user.getR_tipo() == 5){
                    sesion.setAttribute("id", user.getIdtbusuario());
                }
                //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user.getUsuario());
                sesion.setAttribute("user", user.getUsuario());
                sesion.setAttribute("password", user.getContrasena());
                sesion.setAttribute("tipo", user.getR_tipo());
                response.sendRedirect(request.getContextPath()+ "/Principal.jsp");
            }else{
                response.sendRedirect(request.getContextPath()+ "/Login.jsp");
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
