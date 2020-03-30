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
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.File;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

/**
 *
 * @author complx
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30, maxRequestSize = 1024 * 1024 * 50)
public class SAdminalumno extends HttpServlet {

    AdministradorController adminC;
    public String objectJson;
    public ObjectMapper mapper;
    int tipoescuelaAlumno = 0;

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
                try {
                    MuestraAgregaalumno(request, response);
                } catch (Exception e) {
                }
                break;
            case "AgregaAlumno":
                try {
                    Agregaalumno(request, response);
                } catch (Exception e) {
                }
                break;
            case "infoAlumno":
                try {
                    infoAlumno(request,response);
                } catch (Exception e) {
                }
                break;
            case "editarAlumno":
                try {
                    editarAlumno(request, response);
                } catch (Exception e) {
                }
                break;
            case "GuardaTutor":
                try {
                    GuardaTutor(request, response);
                } catch (Exception e) {
                }
                break;
            case "GuardaAlumno":
                try {
                    GuardaAlumno(request, response);
                } catch (Exception e) {
                }
                break;
            case "eliminarAlumno":
                try {
                    eliminarAlumno(request, response);
                } catch (Exception e) {
                }
                break;
            case "importaAlumno":
                try {
                    importaAlumno(request, response);
                } catch (Exception e) {
                }
                break;
            case "importaTutor":
                try {
                    importaTutor(request, response);
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

    private void MuestraAgregaalumno(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        List<TbAlumnos> listalumnos = new ArrayList<>();
        try {
            tipoescuelaAlumno = Integer.parseInt(request.getParameter("TIPOESCUELA"));
            listalumnos = adminC.getAlumnos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listalumnos", listalumnos);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/tablaalumnos.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    private void infoAlumno(HttpServletRequest request, HttpServletResponse response) throws IOException {
        adminC = new AdministradorController();
        TbAlumnos alumno = new TbAlumnos();
        TbTutor tutor = new TbTutor();
        try {
            tutor = adminC.datosTutor(Integer.parseInt(request.getParameter("IDTUTOR")));
            request.setAttribute("tutor", tutor);
            alumno = adminC.datosAlumno(Integer.parseInt(request.getParameter("IDALUMNO")));
            request.setAttribute("alumno", alumno);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/infoalumno.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void editarAlumno(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
            listgrado = adminC.getGrado(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrupo", listgrupo);
            listarea = adminC.getArea(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listarea", listarea);
            listcpt = adminC.getCpt(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listcpt", listcpt);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/formalumno.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void eliminarAlumno(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        try {
            adminC.eliminaAlumno(Integer.parseInt(request.getParameter("IDALUMNO")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void Agregaalumno(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();
        List<CtAreaalumno> listarea = new ArrayList<>();
        List<CtCptalumno> listcpt = new ArrayList<>();
        try {
            listgrado = adminC.getGrado(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrupo", listgrupo);
            listarea = adminC.getArea(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listarea", listarea);
            listcpt = adminC.getCpt(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listcpt", listcpt);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/formalumno.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void GuardaTutor(HttpServletRequest request, HttpServletResponse response) throws Exception {
        TbTutor tutor = new TbTutor();
        adminC = new AdministradorController();
        objectJson = request.getParameter("TUTOR");
        mapper = new ObjectMapper();
        try {
            tutor = mapper.readValue(objectJson, TbTutor.class);
            if (tutor.getIdtbtutor() > 0) {
                adminC.actualizaTutor(tutor);
            } else {
                adminC.guardaTutor(tutor, Integer.parseInt(request.getParameter("TIPOESCUELA")));
            }
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void GuardaAlumno(HttpServletRequest request, HttpServletResponse response) throws Exception {
        TbAlumnos alumno = new TbAlumnos();
        String ruta = null;
        String base64 = null;
        adminC = new AdministradorController();
        //objectJson = request.getParameter("ALUMNO");
        //mapper = new ObjectMapper();
        try {
            String foto = (String.valueOf(request.getParameter("fotoAlumno")));
            if (foto.equals("null")) {
                String applicationPath = getServletContext().getRealPath("");
                System.out.println("OTRA RUTA" + applicationPath);
                String uploadPath = "/home/escape9/sistema.iterra.edu.mx/alumnos";
                File fileUploadDirectory = new File(uploadPath);
                if (!fileUploadDirectory.exists()) {
                    fileUploadDirectory.mkdirs();
                }
                Part part = request.getPart("fotoAlumno");
                String nombrearchivo = extractFileName(part);
                ruta = uploadPath + "/" + nombrearchivo;
                System.out.println("Ruta:" + ruta);
                part.write(ruta);
                base64 = String.valueOf(request.getParameter("filealumno"));
            }
            alumno.setIdtbalumnos(Integer.parseInt(request.getParameter("idalumno")));
            alumno.setNombre(String.valueOf(request.getParameter("nombrea")));
            alumno.setApellidop(String.valueOf(request.getParameter("apellidopa")));
            alumno.setApellidom(String.valueOf(request.getParameter("apellidoma")));
            alumno.setFechanacimiento(String.valueOf(request.getParameter("fechanaa")));
            alumno.setCurp(String.valueOf(request.getParameter("curpa")));
            alumno.setTelefonocasa(String.valueOf(request.getParameter("telcasaa")));
            alumno.setCelular(String.valueOf(request.getParameter("celulara")));
            alumno.setCorreo(String.valueOf(request.getParameter("correoa")));
            alumno.setMunicipiona(String.valueOf(request.getParameter("municipionaca")));
            alumno.setEstadona(String.valueOf(request.getParameter("estadonaca")));
            alumno.setNacionalidad(String.valueOf(request.getParameter("nacionalidada")));
            alumno.setSexo(Boolean.parseBoolean(request.getParameter("sexo")));
            alumno.setCalledom(String.valueOf(request.getParameter("calledoma")));
            alumno.setNumerodom(Integer.parseInt(request.getParameter("numerodoma")));
            alumno.setColoniadom(String.valueOf(request.getParameter("coloniadoa")));
            alumno.setCodigopostal(Integer.parseInt(request.getParameter("codigopostala")));
            alumno.setNivelcursa(String.valueOf(request.getParameter("nivela")));
            alumno.setRgrado(Integer.parseInt(request.getParameter("grado")));
            alumno.setRgrupo(Integer.parseInt(request.getParameter("grupo")));
            alumno.setRarea(Integer.parseInt(request.getParameter("area")));
            alumno.setRcpt(Integer.parseInt(request.getParameter("cpt")));
            alumno.setPlantelproce(String.valueOf(request.getParameter("plantelproce")));
            alumno.setNivelanterior(String.valueOf(request.getParameter("nivelanterior")));
            alumno.setTurnoanterior(Integer.parseInt(request.getParameter("turnoanterior")));
            alumno.setGradoanterior(Integer.parseInt(request.getParameter("gradoanterior")));
            alumno.setPlantelproce(String.valueOf(request.getParameter("plantelanterior")));
            if (alumno.getIdtbalumnos() > 0) {
                adminC.actualizaAlumno(alumno, base64);
            } else {
                adminC.guardaAlumno(alumno, tipoescuelaAlumno, base64);
            }
        } catch (Exception e) {
            System.out.println(e);
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

    private void importaAlumno(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        List<TbAlumnos> listalumnos = new ArrayList<>();
        String ruta = null;
        try {
            String foto = (String.valueOf(request.getParameter("importaAlumno")));
            if (foto.equals("null")) {
                String applicationPath = getServletContext().getRealPath("");
                String uploadPath = applicationPath; //applicationPath + File.separator + "archivos";
                File fileUploadDirectory = new File(uploadPath);
                if (!fileUploadDirectory.exists()) {
                    fileUploadDirectory.mkdirs();
                }
                Part part = request.getPart("importaAlumno");
                String nombrearchivo = extractFileName(part);
                ruta = uploadPath + File.separator + nombrearchivo;
                System.out.println("Ruta:" + ruta);
                part.write(ruta);
            }
            listalumnos = adminC.exportaAlumnos(ruta,tipoescuelaAlumno);
             adminC.guardaImportaAlumnos(listalumnos);
            String f = null;
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

    private void importaTutor(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        List<TbTutor> listtutor = new ArrayList<>();
        String ruta = null;
        try {
            String foto = (String.valueOf(request.getParameter("importaTutor")));
            if (foto.equals("null")) {
                String applicationPath = getServletContext().getRealPath("");
                String uploadPath = applicationPath; //applicationPath + File.separator + "archivos";
                File fileUploadDirectory = new File(uploadPath);
                if (!fileUploadDirectory.exists()) {
                    fileUploadDirectory.mkdirs();
                }
                Part part = request.getPart("importaTutor");
                String nombrearchivo = extractFileName(part);
                ruta = uploadPath + File.separator + nombrearchivo;
                System.out.println("Ruta:" + ruta);
                part.write(ruta);
            }
            listtutor = adminC.exportaTutor(ruta,tipoescuelaAlumno);
            adminC.guardaImportaTutor(listtutor);
            String f = null;
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }

    }

}
