/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.AdministradorController;
import Controlador.AlumnosController;
import Modelos.CtGrado;
import Modelos.CtGrupo;
import Modelos.CtIncidente;
import Modelos.CtPeriodoEscolar;
import Modelos.TbAlumnos;
import Modelos.Alumno;
import Modelos.TbMateria;
import Modelos.TbPersonal;
import Modelos.TbReporteDisciplinar;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.servlet.annotation.MultipartConfig;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author complx
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30, maxRequestSize = 1024 * 1024 * 50)
public class SReportes extends HttpServlet {

    AlumnosController alumnoC;
    AdministradorController adminC;
    ObjectMapper mapper;
    String objectJson;

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
            case "Rdisciplinar":
                ReporteDisciplinar(request, response);
                break;
            case "Racademico":
                ReporteAcademico(request, response);
                break;
            case "alumnoGradoGrupo":
                alumnoGradoGrupo(request, response);
                break;
            case "guardaIncidente":
                guardaIncidente(request, response);
                break;
            case "GUARDAR":
                guardaReporteD(request, response);
                break;
            case "mostrarReportes":
                mostrarReportes(request, response);
                break;
            case "infoReporteD":
                infoReporteD(request, response);
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

    private void ReporteDisciplinar(HttpServletRequest request, HttpServletResponse response) {
        alumnoC = new AlumnosController();
        adminC = new AdministradorController();
        List<CtPeriodoEscolar> listperiodo = new ArrayList<>();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();
        List<Alumno> listalumno = new ArrayList<>();
        List<TbPersonal> listpersonal = new ArrayList<>();
        List<TbMateria> listmateria = new ArrayList<>();
        List<CtIncidente> listincidente = new ArrayList<>();
        try {
            listperiodo = alumnoC.getPeriodos();
            request.setAttribute("listperiodo", listperiodo);
            listgrado = adminC.getGrado();
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo();
            request.setAttribute("listgrupo", listgrupo);
            listpersonal = adminC.getPersonal();
            request.setAttribute("listpersonal", listpersonal);
            listmateria = alumnoC.getMaterias();
            request.setAttribute("listmateria", listmateria);
            listincidente = alumnoC.getIncidentes();
            request.setAttribute("listincidente", listincidente);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Alumnos/reportedisciplinar.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void alumnoGradoGrupo(HttpServletRequest request, HttpServletResponse response) {
        alumnoC = new AlumnosController();
        adminC = new AdministradorController();
        List<CtPeriodoEscolar> listperiodo = new ArrayList<>();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();
        List<Alumno> listalumno = new ArrayList<>();
        List<TbPersonal> listpersonal = new ArrayList<>();
        List<TbMateria> listmateria = new ArrayList<>();
        List<CtIncidente> listincidente = new ArrayList<>();
        try {
            listperiodo = alumnoC.getPeriodos();
            request.setAttribute("listperiodo", listperiodo);
            listgrado = adminC.getGrado();
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo();
            request.setAttribute("listgrupo", listgrupo);
            listpersonal = adminC.getPersonal();
            request.setAttribute("listpersonal", listpersonal);
            listalumno = alumnoC.getAlumnos(Integer.parseInt(request.getParameter("GRADO")), Integer.parseInt(request.getParameter("GRUPO")));
            request.setAttribute("listalumno", listalumno);
            listmateria = alumnoC.getMaterias();
            request.setAttribute("listmateria", listmateria);
            listincidente = alumnoC.getIncidentes();
            request.setAttribute("listincidente", listincidente);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Alumnos/alumnosgradogrupo.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void ReporteAcademico(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher("vista/Alumnos/reporteacademico.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void guardaIncidente(HttpServletRequest request, HttpServletResponse response) {
        alumnoC = new AlumnosController();
        CtIncidente incidente = new CtIncidente();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            incidente = mapper.readValue(objectJson, CtIncidente.class);
            alumnoC.guardaIncidente(incidente);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void guardaReporteD(HttpServletRequest request, HttpServletResponse response) {
        TbReporteDisciplinar reporteD = new TbReporteDisciplinar();
        String ruta = null;
        try {
            String foto =(String.valueOf(request.getParameter("Archivo")));
            if (foto.equals("null")) {
                String applicationPath = getServletContext().getRealPath("");
                String uploadPath = "C:\\Users\\Complx\\Desktop"; //applicationPath + File.separator + "archivos";
                File fileUploadDirectory = new File(uploadPath);
                if (!fileUploadDirectory.exists()) {
                    fileUploadDirectory.mkdirs();
                }
                Part part = request.getPart("Archivo");
                String nombrearchivo = extractFileName(part);
                ruta = uploadPath + File.separator + nombrearchivo;
                System.out.println("Ruta:" + ruta);
                part.write(ruta);
            }
            int status = Integer.parseInt(request.getParameter("tutorcorreo"));
            reporteD.setRperiodo(Integer.parseInt(request.getParameter("PeriodoD")));
            reporteD.setRalumno(Integer.parseInt(request.getParameter("Alumnodisciplinar")));
            reporteD.setRtipoincidente(Integer.parseInt(request.getParameter("Incidente")));
            reporteD.setNivel(Integer.parseInt(request.getParameter("Nivel")));
            reporteD.setHora(String.valueOf(request.getParameter("Horaincidente")));
            reporteD.setFecha(String.valueOf(request.getParameter("Fechaincidente")));
            reporteD.setFechareporte(String.valueOf(request.getParameter("Fechareporte")));
            reporteD.setRpersonalsolicita(Integer.parseInt(request.getParameter("Personalsolicita")));
            reporteD.setRpersonalllena(Integer.parseInt(request.getParameter("Personalllena")));
            reporteD.setRmateria(Integer.parseInt(request.getParameter("MateriaD")));
            reporteD.setRpersonal(Integer.parseInt(request.getParameter("Personal")));
            reporteD.setLugar(String.valueOf(request.getParameter("Lugarincidente")));
            reporteD.setDescripcion(String.valueOf(request.getParameter("Descripcion")));
            alumnoC.guardaReporteD(reporteD,ruta,status);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void mostrarReportes(HttpServletRequest request, HttpServletResponse response) {
        alumnoC = new AlumnosController();
        List<CtPeriodoEscolar> listperiodo = new ArrayList<>();
        List<TbReporteDisciplinar> alumnosdisciplinar = new ArrayList<>();
        try {
            listperiodo = alumnoC.getPeriodos();
            request.setAttribute("listperiodo", listperiodo);
            alumnosdisciplinar = alumnoC.getAlumnosReporteD();
            request.setAttribute("alumnosdisciplinar", alumnosdisciplinar);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/tablareportes.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void infoReporteD(HttpServletRequest request, HttpServletResponse response) {
        alumnoC = new AlumnosController();
        TbReporteDisciplinar reporteD = new TbReporteDisciplinar();
        try {
            reporteD = alumnoC.datosReporteD(Integer.parseInt(request.getParameter("ID")), request.getParameter("FECHA"), Integer.parseInt(request.getParameter("PERIODO")));
            request.setAttribute("reporteD", reporteD);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/datosreportedi.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String extractFileName(Part part) {
        String fileName = "",
                contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return fileName;
    }

}
