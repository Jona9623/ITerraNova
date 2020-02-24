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
import Modelos.CtAtencion;
import Modelos.CtSemanaFiscal;
import Modelos.TbMateria;
import Modelos.TbPersonal;
import Modelos.TbReporteAcademico;
import Modelos.TbReporteDisciplinar;
import Modelos.TbTareaSemanal;
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
    int tipoescuelareporteD = 0;

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
                try {
                    ReporteDisciplinar(request, response);
                } catch (Exception e) {
                    System.out.println(e);
                }

                break;
            case "Racademico":
                try {
                    ReporteAcademico(request, response);
                } catch (Exception e) {
                }
                break;
            case "alumnoGradoGrupo":
                try {
                alumnoGradoGrupo(request, response);
                } catch (Exception e) {
                }
                break;
            case "guardaIncidente":
                try {
                    guardaIncidente(request, response);
                } catch (Exception e) {
                }
                break;
            case "GUARDAR":
                try {
                   guardaReporteD(request, response); 
                } catch (Exception e) {
                }
                break;
            case "mostrarReportes":
                try {
                    mostrarReportes(request, response);
                } catch (Exception e) {
                }
                break;
            case "infoReporteD":
                try {
                    infoReporteD(request, response);
                } catch (Exception e) {
                }
                break;
            case "editarReporteD":
                try {
                   editarReporteD(request, response); 
                } catch (Exception e) {
                }
                break;
            case "guardaeditarReporteD":
                try {
                    guardaeditarReporteD(request, response);
                } catch (Exception e) {
                }
                break;
            case "alumnoGradoGrupoAca":
                alumnoGradoGrupoAca(request, response);
                break;
            case "alumnoGradoGrupoAcaAtencion":
                alumnoGradoGrupoAcaAtencion(request, response);
                break;
            case "guardaReporteAcademico":
                try {
                    guardaReporteAcademico(request, response);
                } catch (Exception e) {
                }
                break;
            case "guardaActividadSemanal":
                try {
                    guardaActividadSemanal(request, response);
                } catch (Exception e) {
                }
                break;
            case "imagenReporteAca":
                imagenReporteAca(request, response);
                break;
            case "guardaComportamiento":
                try {
                    guardaComportamiento(request, response);
                } catch (Exception e) {
                }
                break;
            case "guardaSemana":
                try {
                    guardaSemana(request, response);
                } catch (Exception e) {
                }
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

    private void ReporteDisciplinar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        alumnoC = new AlumnosController();
        adminC = new AdministradorController();
        List<CtPeriodoEscolar> listperiodo = new ArrayList<>();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();
        List<TbPersonal> listpersonal = new ArrayList<>();
        List<TbMateria> listmateria = new ArrayList<>();
        List<CtIncidente> listincidente = new ArrayList<>();
        try {
            tipoescuelareporteD = Integer.parseInt(request.getParameter("TIPOESCUELA"));
            listperiodo = alumnoC.getPeriodos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listperiodo", listperiodo);
            listgrado = adminC.getGrado(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrupo", listgrupo);
            listpersonal = adminC.getPersonal(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listpersonal", listpersonal);
            listmateria = alumnoC.getMaterias(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listmateria", listmateria);
            listincidente = alumnoC.getIncidentes(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listincidente", listincidente);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Alumnos/reportedisciplinar.jsp");
            rd.forward(request, response);
        
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);            
            
            
        }
    }

    private void alumnoGradoGrupo(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
            listperiodo = alumnoC.getPeriodos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listperiodo", listperiodo);
            listgrado = adminC.getGrado(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrupo", listgrupo);
            listpersonal = adminC.getPersonal(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listpersonal", listpersonal);
            listalumno = alumnoC.getAlumnos(Integer.parseInt(request.getParameter("GRADO")), Integer.parseInt(request.getParameter("GRUPO")), Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listalumno", listalumno);
            listmateria = alumnoC.getMaterias(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listmateria", listmateria);
            listincidente = alumnoC.getIncidentes(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listincidente", listincidente);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Alumnos/alumnosgradogrupo.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void ReporteAcademico(HttpServletRequest request, HttpServletResponse response) throws Exception {
        alumnoC = new AlumnosController();
        adminC = new AdministradorController();
        List<CtPeriodoEscolar> listperiodo = new ArrayList<>();
        List<CtSemanaFiscal> listsemana = new ArrayList<>();
        List<TbPersonal> listpersonal = new ArrayList<>();
        List<TbMateria> listmateria = new ArrayList<>();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();
        List<CtAtencion> listatencion = new ArrayList<>();
        try {
            listperiodo = alumnoC.getPeriodos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listperiodo", listperiodo);
            listsemana = alumnoC.getSemanaiscal(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listsemana", listsemana);
            listpersonal = adminC.getPersonal(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listpersonal", listpersonal);
            listmateria = alumnoC.getMaterias(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listmateria", listmateria);
            listgrado = adminC.getGrado(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrupo", listgrupo);
            listatencion = alumnoC.getAtencion(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listatencion", listatencion);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Alumnos/reporteacademico.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void alumnoGradoGrupoAca(HttpServletRequest request, HttpServletResponse response) {
        alumnoC = new AlumnosController();
        adminC = new AdministradorController();
        List<CtPeriodoEscolar> listperiodo = new ArrayList<>();
        List<CtSemanaFiscal> listsemana = new ArrayList<>();
        List<TbPersonal> listpersonal = new ArrayList<>();
        List<TbMateria> listmateria = new ArrayList<>();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();
        List<CtAtencion> listatencion = new ArrayList<>();
        //List<Alumno> listalumnoA = new ArrayList<>();
        List<Alumno> listalumnoH = new ArrayList<>();
        try {
            listperiodo = alumnoC.getPeriodos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listperiodo", listperiodo);
            listsemana = alumnoC.getSemanaiscal(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listsemana", listsemana);
            listpersonal = adminC.getPersonal(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listpersonal", listpersonal);
            listmateria = alumnoC.getMaterias(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listmateria", listmateria);
            listgrado = adminC.getGrado(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrupo", listgrupo);
            listatencion = alumnoC.getAtencion(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listatencion", listatencion);
            listalumnoH = alumnoC.getAlumnos(Integer.parseInt(request.getParameter("GRADOHONOR")), Integer.parseInt(request.getParameter("GRUPOHONOR")), Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listalumnoH", listalumnoH);
            /* listalumnoA = alumnoC.getAlumnos(Integer.parseInt(request.getParameter("GRADOATENCION")), Integer.parseInt(request.getParameter("GRUPOATENCION")));
            request.setAttribute("listalumnoA", listalumnoA);*/
            RequestDispatcher rd = request.getRequestDispatcher("vista/Alumnos/alumnosgradogrupo.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void alumnoGradoGrupoAcaAtencion(HttpServletRequest request, HttpServletResponse response) {
        alumnoC = new AlumnosController();
        adminC = new AdministradorController();
        List<CtPeriodoEscolar> listperiodo = new ArrayList<>();
        List<CtSemanaFiscal> listsemana = new ArrayList<>();
        List<TbPersonal> listpersonal = new ArrayList<>();
        List<TbMateria> listmateria = new ArrayList<>();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();
        List<CtAtencion> listatencion = new ArrayList<>();
        List<Alumno> listalumnoA = new ArrayList<>();
        //List<Alumno> listalumnoH = new ArrayList<>();
        try {
            listperiodo = alumnoC.getPeriodos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listperiodo", listperiodo);
            listsemana = alumnoC.getSemanaiscal(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listsemana", listsemana);
            listpersonal = adminC.getPersonal(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listpersonal", listpersonal);
            listmateria = alumnoC.getMaterias(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listmateria", listmateria);
            listgrado = adminC.getGrado(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrupo", listgrupo);
            listatencion = alumnoC.getAtencion(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listatencion", listatencion);
            listalumnoA = alumnoC.getAlumnos(Integer.parseInt(request.getParameter("GRADOATENCION")), Integer.parseInt(request.getParameter("GRUPOATENCION")), Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listalumnoA", listalumnoA);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Alumnos/alumnogradogrupoatencion.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void guardaIncidente(HttpServletRequest request, HttpServletResponse response) throws Exception {
        alumnoC = new AlumnosController();
        CtIncidente incidente = new CtIncidente();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            incidente = mapper.readValue(objectJson, CtIncidente.class);
            alumnoC.guardaIncidente(incidente, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void guardaReporteD(HttpServletRequest request, HttpServletResponse response) throws Exception {
        TbReporteDisciplinar reporteD = new TbReporteDisciplinar();
        String ruta = null;
        try {
            String foto = (String.valueOf(request.getParameter("Archivo")));
            if (foto.equals("null")) {
                String applicationPath = getServletContext().getRealPath("");
                String uploadPath = "C:\\Users\\Complx\\perro"; //applicationPath + File.separator + "archivos";
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
            alumnoC.guardaReporteD(reporteD, ruta, status, tipoescuelareporteD);

        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void mostrarReportes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        alumnoC = new AlumnosController();
        List<CtPeriodoEscolar> listperiodo = new ArrayList<>();
        List<TbReporteDisciplinar> alumnosdisciplinar = new ArrayList<>();
        try {
            listperiodo = alumnoC.getPeriodos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listperiodo", listperiodo);
            alumnosdisciplinar = alumnoC.getAlumnosReporteD(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("alumnosdisciplinar", alumnosdisciplinar);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/tablareportes.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void infoReporteD(HttpServletRequest request, HttpServletResponse response) throws Exception{
        alumnoC = new AlumnosController();
        TbReporteDisciplinar reporteD = new TbReporteDisciplinar();
        try {
            reporteD = alumnoC.datosReporteD(Integer.parseInt(request.getParameter("ID")), request.getParameter("FECHA"), request.getParameter("HORA"), Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("reporteD", reporteD);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/datosreportedi.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
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

    private void editarReporteD(HttpServletRequest request, HttpServletResponse response) throws Exception {
        alumnoC = new AlumnosController();
        TbReporteDisciplinar reporteD = new TbReporteDisciplinar();
        List<CtPeriodoEscolar> listperiodo = new ArrayList<>();
        List<TbPersonal> listpersonal = new ArrayList<>();
        List<TbMateria> listmateria = new ArrayList<>();
        List<CtIncidente> listincidente = new ArrayList<>();
        try {
            reporteD = alumnoC.editarReporteD(Integer.parseInt(request.getParameter("ID")), request.getParameter("FECHA"), request.getParameter("HORA"));
            request.setAttribute("reporteD", reporteD);
            listperiodo = alumnoC.getPeriodos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listperiodo", listperiodo);
            listpersonal = adminC.getPersonal(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listpersonal", listpersonal);
            listmateria = alumnoC.getMaterias(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listmateria", listmateria);
            listincidente = alumnoC.getIncidentes(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listincidente", listincidente);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Alumnos/reportedisciplinareditar.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }

    }

    private void guardaeditarReporteD(HttpServletRequest request, HttpServletResponse response) throws Exception {
        alumnoC = new AlumnosController();
        TbReporteDisciplinar reporteD = new TbReporteDisciplinar();
        mapper = new ObjectMapper();
        objectJson = request.getParameter("OBJETO");
        try {
            reporteD = mapper.readValue(objectJson, TbReporteDisciplinar.class);
            alumnoC.guardareditarReporteD(reporteD);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void guardaActividadSemanal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        alumnoC = new AlumnosController();
        TbTareaSemanal tarea = new TbTareaSemanal();
        mapper = new ObjectMapper();
        objectJson = request.getParameter("OBJETO");
        try {
            tarea = mapper.readValue(objectJson, TbTareaSemanal.class);
            alumnoC.guardaActividadSemanal(tarea, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void guardaReporteAcademico(HttpServletRequest request, HttpServletResponse response) throws Exception {
        TbReporteAcademico reporteA = new TbReporteAcademico();
        alumnoC = new AlumnosController();
        mapper = new ObjectMapper();
        objectJson = request.getParameter("OBJETO");
        try {
            reporteA = mapper.readValue(objectJson, TbReporteAcademico.class);
            alumnoC.guardaReporteAcademico(reporteA, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void imagenReporteAca(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher("vista/Alumnos/imagenreporteaca.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void guardaComportamiento(HttpServletRequest request, HttpServletResponse response) throws Exception {
        alumnoC = new AlumnosController();
        CtAtencion atencion = new CtAtencion();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            atencion = mapper.readValue(objectJson, CtAtencion.class);
            alumnoC.guardaComportamiento(atencion, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void guardaSemana(HttpServletRequest request, HttpServletResponse response) throws Exception {
        alumnoC = new AlumnosController();
        CtSemanaFiscal semana = new CtSemanaFiscal();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            semana = mapper.readValue(objectJson, CtSemanaFiscal.class);
            alumnoC.guardaSemana(semana, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

}
