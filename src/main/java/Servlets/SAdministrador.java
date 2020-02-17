/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.AdministradorController;
import Controlador.AlumnosController;
import Modelos.CtAreaalumno;
import Modelos.CtCptalumno;
import Modelos.CtPeriodoEscolar;
import Modelos.CtPuesto;
import Modelos.CtTipoCalificaicon;
import Modelos.TbMateria;
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
 * @author Complx
 */
public class SAdministrador extends HttpServlet {
    AdministradorController adminC;
    AlumnosController alumC;
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
        switch (accion){
            case "tablaPuesto": tablaPuesto (request,response); break;
            case "tablaPeriodo": tablaPeriodo(request,response);break;
            case "tablaArea": tablaArea (request,response);break;
            case "tablaCpt": tablaCpt(request,response);break;
            case "tablaGrado": tablaGrado(request,response); break;
            case "tablaGrupo": tablaGrupo(request,response);break;
            case "tablaTipoCalificacion": tablaTipoCalificacion(request,response);break;
            case "tablaMateria": tablaMateria(request,response);break;
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

    private void tablaPuesto(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        List <CtPuesto> listpuesto = new ArrayList<>();
        try {
            listpuesto = adminC.getPuesto();
            request.setAttribute("listpuesto", listpuesto);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/Puesto/tablapuesto.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void tablaPeriodo(HttpServletRequest request, HttpServletResponse response) {
        alumC = new AlumnosController();
        List <CtPeriodoEscolar> listperiodo = new ArrayList<>();
        try {
            listperiodo = alumC.getPeriodos();
            request.setAttribute("listperiodo", listperiodo);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/PeriodoEscolar/tablaperiodo.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void tablaArea(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        List <CtAreaalumno> listarea = new ArrayList<>();
        try {
            listarea = adminC.getArea();
            request.setAttribute("listarea", listarea);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/AreaAlumno/tablaareaalumno.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void tablaCpt(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        List <CtCptalumno> listcpt = new ArrayList<>();
         try {
             listcpt = adminC.getCpt();
             request.setAttribute("listcpt", listcpt);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/Cptalumno/tablacptalumno.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void tablaGrado(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void tablaGrupo(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void tablaTipoCalificacion(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        List <CtTipoCalificaicon> listtipocali = new ArrayList<>();
        try {
            listtipocali = adminC.getTipoCali();
            request.setAttribute("listtipocali", listtipocali);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/TipoCalificacion/tablatipocalificacion.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void tablaMateria(HttpServletRequest request, HttpServletResponse response) {
        alumC = new AlumnosController();
        List <TbMateria> listmateria = new ArrayList<>();
        try {
            listmateria = alumC.getMaterias();
            request.setAttribute("listmateria", listmateria);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/Materia/tablamaterias.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
