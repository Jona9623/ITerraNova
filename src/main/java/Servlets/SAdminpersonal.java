/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.AdministradorController;
import Modelos.CtPuesto;
import Modelos.TbPersonal;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author complx
 */
public class SAdminpersonal extends HttpServlet {

    AdministradorController adminC;
    public String objectJson;
    public ObjectMapper mapper;

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
        String accion = request.getParameter("ACCION");
        switch (accion) {
            case "MuestraAgregaPersonal":
                MuestraAgregaPersonal(request, response);
                break;
            case "AgregaPersonal":
                AgregaPersonal(request, response);
                break;
            case "GuardaPersonal":
                GuardaPersonal(request, response);
                break;
            case "editarPersonal":
                editarPersonal(request, response);
                break;
            case "eliminarPersonal": eliminarPersonal (request, response); break;    
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

    private void MuestraAgregaPersonal(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        List<TbPersonal> listpersonal = new ArrayList<>();
        try {
            listpersonal = adminC.getPersonal();
            request.setAttribute("listpersonal", listpersonal);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/tablapersonal.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void editarPersonal(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        TbPersonal personal = new TbPersonal();
        List<CtPuesto> listpuesto = new ArrayList<>();
        try {
            listpuesto = adminC.getPuesto();
            request.setAttribute("listpuesto", listpuesto);
            personal = adminC.datosPeronal(Integer.parseInt(request.getParameter("IDPERSONAL")));
            request.setAttribute("personal", personal);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/formpersonal.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void eliminarPersonal(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        try {
           adminC.eliminaPersonal(Integer.parseInt(request.getParameter("IDPERSONAL"))); 
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void AgregaPersonal(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        List<CtPuesto> listpuesto = new ArrayList<>();
        try {
            listpuesto = adminC.getPuesto();
            request.setAttribute("listpuesto", listpuesto);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/formpersonal.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void GuardaPersonal(HttpServletRequest request, HttpServletResponse response) {
        objectJson = request.getParameter("PERSONAL");
        mapper = new ObjectMapper();
        TbPersonal personal = new TbPersonal();
        try {
            personal = mapper.readValue(objectJson, TbPersonal.class);
            if (personal.getIdtbpersonal() > 0) {
                adminC.actualizaPersonal(personal);
            } else {
                adminC.guardaPersonal(personal);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
