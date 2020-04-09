/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.AdministradorController;
import Controlador.AlumnosController;
import Modelos.Alumno;
import Modelos.CtGrado;
import Modelos.CtGrupo;
import Modelos.CtPeriodoEscolar;
import Modelos.CtPuesto;
import Modelos.TbAlumnos;
import Modelos.TbMateria;
import Modelos.TbMateriaPersonal;
import Modelos.TbPersonal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author complx
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30, maxRequestSize = 1024 * 1024 * 50)
public class SAdminpersonal extends HttpServlet {

    AdministradorController adminC;
    AlumnosController alumC;
    public String objectJson;
    public ObjectMapper mapper;
    int tipoescuela = 0;

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
                try {
                    MuestraAgregaPersonal(request, response);
                } catch (Exception e) {
                }
                break;
            case "AgregaPersonal":
                try {
                    AgregaPersonal(request, response);
                } catch (Exception e) {
                }
                break;
            case "GuardaPersonal":
                try {
                    GuardaPersonal(request, response);
                } catch (Exception e) {
                }
                break;
            case "infoPersonal":
                try {
                    infoPersonal(request, response);
                } catch (Exception e) {
                }
                break;
            case "editarPersonal":
                try {
                    editarPersonal(request, response);
                } catch (Exception e) {
                }
                break;
            case "eliminarPersonal":
                eliminarPersonal(request, response);
                break;
            case "importaPersonal":
                try {
                    importaPersonal(request, response);
                } catch (Exception e) {
                }
                break;
            case "agregaMateria":
                try {
                    agregaMateria(request, response);
                } catch (Exception e) {
                }
                break;
            case "asignaMateria":
                try {
                    asignaMateria(request, response);
                } catch (Exception e) {
                }
                break;
            case "agregaAlumnos":
                try {
                    agregaAlumnos(request,response);
                } catch (Exception e) {
                }
                break;
            case "getAsignaAlumno":
                try {
                    getAsignaAlumno(request,response);
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

    private void MuestraAgregaPersonal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        List<TbPersonal> listpersonal = new ArrayList<>();
        try {
            tipoescuela = Integer.parseInt(request.getParameter("TIPOESCUELA"));
            listpersonal = adminC.getPersonal(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listpersonal", listpersonal);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/tablapersonal.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void infoPersonal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        TbPersonal personal = new TbPersonal();
        try {
            personal = adminC.datosPeronal(Integer.parseInt(request.getParameter("IDPERSONAL")));
            request.setAttribute("personal", personal);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/infopersonal.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void editarPersonal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        TbPersonal personal = new TbPersonal();
        List<CtPuesto> listpuesto = new ArrayList<>();
        try {
            listpuesto = adminC.getPuesto(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listpuesto", listpuesto);
            personal = adminC.datosPeronal(Integer.parseInt(request.getParameter("IDPERSONAL")));
            request.setAttribute("personal", personal);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/formpersonal.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void eliminarPersonal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        adminC = new AdministradorController();
        try {
            adminC.eliminaPersonal(Integer.parseInt(request.getParameter("IDPERSONAL")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void AgregaPersonal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        List<CtPuesto> listpuesto = new ArrayList<>();
        try {
            listpuesto = adminC.getPuesto(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listpuesto", listpuesto);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/formpersonal.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void GuardaPersonal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        objectJson = request.getParameter("PERSONAL");
        mapper = new ObjectMapper();
        TbPersonal personal = new TbPersonal();
        try {
            personal = mapper.readValue(objectJson, TbPersonal.class);
            if (personal.getIdtbpersonal() > 0) {
                adminC.actualizaPersonal(personal);
            } else {
                adminC.guardaPersonal(personal, Integer.parseInt(request.getParameter("TIPOESCUELA")));
            }

        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);

        }

    }

    private void importaPersonal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        List<TbPersonal> listpersonal = new ArrayList<>();
        String ruta = null;
        try {
            String foto = (String.valueOf(request.getParameter("importaPersonal")));
            if (foto.equals("null")) {
                String applicationPath = getServletContext().getRealPath("");
                String uploadPath = applicationPath; //applicationPath + File.separator + "archivos";
                File fileUploadDirectory = new File(uploadPath);
                if (!fileUploadDirectory.exists()) {
                    fileUploadDirectory.mkdirs();
                }
                Part part = request.getPart("importaPersonal");
                String nombrearchivo = extractFileName(part);
                ruta = uploadPath + File.separator + nombrearchivo;
                System.out.println("Ruta:" + ruta);
                part.write(ruta);
            }
            listpersonal = adminC.exportaPersonal(ruta, tipoescuela);
            adminC.guardaImportaPersonal(listpersonal);
            String f = null;
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void agregaMateria(HttpServletRequest request, HttpServletResponse response) throws Exception {
        alumC = new AlumnosController();
        List<TbMateria> listmateria = new ArrayList<>();
        List<CtPeriodoEscolar> listperiodo = new ArrayList<>();
        TbPersonal personal = new TbPersonal();
        try {
            listmateria = alumC.getMateriasPersonal(Integer.parseInt(request.getParameter("TIPOESCUELA")),Integer.parseInt(request.getParameter("ID")));
            request.setAttribute("listmateria", listmateria);
            personal = adminC.datosPeronal(Integer.parseInt(request.getParameter("ID")));
            request.setAttribute("personal", personal);
            listperiodo = alumC.getPeriodos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listperiodo", listperiodo);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/agregamateriaspersonal.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    
    private void asignaMateria(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        mapper = new ObjectMapper();
        objectJson = request.getParameter("PRUEBA");
        try {
            List<TbMateriaPersonal> materiapersonal = Arrays.asList(mapper.readValue(objectJson, TbMateriaPersonal[].class));
            adminC.asignaMateria(Integer.parseInt(request.getParameter("ID")),materiapersonal, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }

    }
    
    private void agregaAlumnos(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        List<TbMateriaPersonal> listmateria = new ArrayList<>();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();
        try {
            listmateria = adminC.getMateriasAPersonal(Integer.parseInt(request.getParameter("TIPOESCUELA")),Integer.parseInt(request.getParameter("ID")));
            request.setAttribute("listmateria", listmateria);
            listgrado = adminC.getGrado(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrupo", listgrupo);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/agregaalumnospersonal.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    
    private void getAsignaAlumno(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        alumC = new  AlumnosController();
        List<TbMateriaPersonal> listmateria = new ArrayList<>();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();
        List<Alumno> listalumno = new ArrayList<>();
        try {
            listmateria = adminC.getMateriasAPersonal(Integer.parseInt(request.getParameter("TIPOESCUELA")),Integer.parseInt(request.getParameter("ID")));
            request.setAttribute("listmateria", listmateria);
            listgrado = adminC.getGrado(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrupo", listgrupo);
            listalumno = alumC.getAlumnos(Integer.parseInt(request.getParameter("GRADO")), Integer.parseInt(request.getParameter("GRUPO")), Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listalumno", listalumno);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/asignaalumno.jsp");
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

}
