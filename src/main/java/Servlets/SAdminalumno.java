/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Modelos.TbTutor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Controlador.AdministradorController;
import Modelos.CtAreaalumno;
import Modelos.CtCptalumno;
import Modelos.CtGrado;
import Modelos.CtGrupo;
import Modelos.TbAlumnos;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author complx
 */
public class SAdminalumno extends HttpServlet {

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
            case "MuestraAgregaAlumno":
                MuestraAgregaalumno(request, response);
                break;
            case "AgregaAlumno":
                Agregaalumno(request, response);
                break;
            case "editarAlumno":
                editarAlumno(request, response);
                break;
            case "GuardaTutor":
                GuardaTutor(request, response);
                break;
            case "GuardaAlumno":
                GuardaAlumno(request, response);
                break;
            case "eliminarAlumno":
                eliminarAlumno(request, response);
                break;
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

    private void MuestraAgregaalumno(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        List<TbAlumnos> listalumnos = new ArrayList<>();
        try {
            listalumnos = adminC.getAlumnos();
            request.setAttribute("listalumnos", listalumnos);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/tablaalumnos.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void editarAlumno(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        TbTutor tutor = new TbTutor();
        TbAlumnos alumno = new TbAlumnos();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();
        List<CtAreaalumno> listarea = new ArrayList<>();
        List<CtCptalumno> listcpt = new ArrayList<>();
        try {
            tutor = adminC.datosTutor(Integer.parseInt(request.getParameter("IDTUTOR")));
            request.setAttribute("tutor", tutor);
            alumno = adminC.datosAlumno(Integer.parseInt(request.getParameter("IDALUMNO")));
            request.setAttribute("alumno", alumno);
            listgrado = adminC.getGrado();
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo();
            request.setAttribute("listgrupo", listgrupo);
            listarea = adminC.getArea();
            request.setAttribute("listarea", listarea);
            listcpt = adminC.getCpt();
            request.setAttribute("listcpt", listcpt);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/formalumno.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void eliminarAlumno(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        try {
           adminC.eliminaAlumno(Integer.parseInt(request.getParameter("IDALUMNO"))); 
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void Agregaalumno(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();
        List<CtAreaalumno> listarea = new ArrayList<>();
        List<CtCptalumno> listcpt = new ArrayList<>();
        try {
            listgrado = adminC.getGrado();
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo();
            request.setAttribute("listgrupo", listgrupo);
            listarea = adminC.getArea();
            request.setAttribute("listarea", listarea);
            listcpt = adminC.getCpt();
            request.setAttribute("listcpt", listcpt);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/formalumno.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void GuardaTutor(HttpServletRequest request, HttpServletResponse response) {
        TbTutor tutor = new TbTutor();
        adminC = new AdministradorController();
        objectJson = request.getParameter("TUTOR");
        mapper = new ObjectMapper();
        try {
            tutor = mapper.readValue(objectJson, TbTutor.class);
            if (tutor.getIdtbtutor() > 0) {
                adminC.actualizaTutor(tutor);
            } else {
                adminC.guardaTutor(tutor);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void GuardaAlumno(HttpServletRequest request, HttpServletResponse response) {
        TbAlumnos alumno = new TbAlumnos();
        adminC = new AdministradorController();
        objectJson = request.getParameter("ALUMNO");
        mapper = new ObjectMapper();
        try {
            alumno = mapper.readValue(objectJson, TbAlumnos.class);
            if (alumno.getIdtbalumnos() > 0) {
                adminC.actualizaAlumno(alumno);
            } else {
                adminC.guardaAlumno(alumno);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
