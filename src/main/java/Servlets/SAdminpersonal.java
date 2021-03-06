/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.AdministradorController;
import Controlador.AlumnosController;
import Modelos.Alumno;
import Modelos.CtDatosMateria;
import Modelos.CtDia;
import Modelos.CtGrado;
import Modelos.CtGrupo;
import Modelos.CtPeriodoEscolar;
import Modelos.CtPuesto;
import Modelos.CtSemanaFiscal;
import Modelos.TbAlumnos;
import Modelos.TbAsistencia;
import Modelos.TbMateria;
import Modelos.TbMateriaAlumno;
import Modelos.TbMateriaPersonal;
import Modelos.TbPersonal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    int verifica;
    int bandera;

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
                    agregaAlumnos(request, response);
                } catch (Exception e) {
                }
                break;
            case "getAsignaAlumno":
                try {
                    getAsignaAlumno(request, response);
                } catch (Exception e) {
                }
                break;
            case "asignaAlumnos":
                try {
                    asignaAlumnos(request, response);
                } catch (Exception e) {
                }
                break;
            case "listaAlumnos":
                try {
                    listaAlumnos(request, response);
                } catch (Exception e) {
                }
                break;
            case "getListaAlumno":
                try {
                    getListaAlumno(request, response);
                } catch (Exception e) {
                }
                break;
            case "guardaAsistencia":
                try {
                    guardaAsistencia(request, response);
                } catch (Exception e) {
                }
                break;
            case "reporteAsistencia":
                try {
                    reporteAsistencia(request, response);
                } catch (Exception e) {
                }
                break;
            case "getreporteAsistencia":
                try {
                    getreporteAsistencia(request, response);
                } catch (Exception e) {
                }
                break;
            case "asistenciaAnterior":
                try {
                    asistenciaAnterior(request, response);
                } catch (Exception e) {
                }
                break;
            case "actualizaAsistencia":
                try {
                    actualizaAsistencia(request, response);
                } catch (Exception e) {
                }
                break;
            case "verificaAsistencia":
                try {
                    verificaAsistencia(request, response);
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
    //Funcion que trae la vista de la tabla que muestra el personal
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
    //Funcion que muestra la vista con toda la info del personal
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
    //Funcion que trae la informacion del personal pra mostrarla en el formulrio y editarlo
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
    //Funcion solo para eliminar el registro
    private void eliminarPersonal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        adminC = new AdministradorController();
        try {
            adminC.eliminaPersonal(Integer.parseInt(request.getParameter("IDPERSONAL")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    //Funcion para mostrar la vista del formulario para agregar un perosnal
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
    //Funcion para guardar la info del personal recogida del formulario
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
    //Tomamos el archivo para importar la informacion del personal a agregar
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
    //Funcion para la vista que muestra las materias para agregarlas al profe
    private void agregaMateria(HttpServletRequest request, HttpServletResponse response) throws Exception {
        alumC = new AlumnosController();
        List<TbMateria> listmateria = new ArrayList<>();
        List<CtPeriodoEscolar> listperiodo = new ArrayList<>();
        TbPersonal personal = new TbPersonal();
        int persona = 0;
        try {
            listmateria = alumC.getMateriasPersonal(Integer.parseInt(request.getParameter("TIPOESCUELA")), Integer.parseInt(request.getParameter("ID")));
            request.setAttribute("listmateria", listmateria);
            personal = adminC.datosPeronal(Integer.parseInt(request.getParameter("ID")));
            request.setAttribute("personal", personal);
            listperiodo = alumC.getPeriodos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listperiodo", listperiodo);
            HttpSession objsesion = request.getSession(false);
            persona = (int) objsesion.getAttribute("personal");
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/agregamateriaspersonal.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    //Funcion que asigna las materias al profe para su uso posterior
    private void asignaMateria(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        mapper = new ObjectMapper();
        objectJson = request.getParameter("PRUEBA");
        try {
            List<TbMateriaPersonal> materiapersonal = Arrays.asList(mapper.readValue(objectJson, TbMateriaPersonal[].class));
            adminC.asignaMateria(Integer.parseInt(request.getParameter("ID")), materiapersonal, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }

    }
    //Funcion para la vista que muestra los parametros a tomar en cuenta para los alumnos que se debe nmostrar
    private void agregaAlumnos(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        List<TbMateriaPersonal> listmateria = new ArrayList<>();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();
        TbPersonal personal = new TbPersonal();
        try {
            listmateria = adminC.getMateriasAPersonal(Integer.parseInt(request.getParameter("TIPOESCUELA")), Integer.parseInt(request.getParameter("ID")));
            request.setAttribute("listmateria", listmateria);
            listgrado = adminC.getGrado(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrupo", listgrupo);
            personal = adminC.datosPeronal(Integer.parseInt(request.getParameter("ID")));
            request.setAttribute("personal", personal);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/agregaalumnospersonal.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    //Funcion que trae los laumnos de acuerdo a la materia seleccionada para mostrarlos e nla vista arriba
    private void getAsignaAlumno(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        alumC = new AlumnosController();
        List<TbMateriaPersonal> listmateria = new ArrayList<>();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();
        List<Alumno> listalumno = new ArrayList<>();
        try {
            listmateria = adminC.getMateriasAPersonal(Integer.parseInt(request.getParameter("TIPOESCUELA")), Integer.parseInt(request.getParameter("ID")));
            request.setAttribute("listmateria", listmateria);
            listgrado = adminC.getGrado(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrupo", listgrupo);
            listalumno = alumC.getAlumnosMateria(Integer.parseInt(request.getParameter("GRADO")), Integer.parseInt(request.getParameter("GRUPO")), Integer.parseInt(request.getParameter("AREA")), Integer.parseInt(request.getParameter("CPT")), Integer.parseInt(request.getParameter("IDM")), Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listalumno", listalumno);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/asignaalumno.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    //Funcion que guarda la infomracion del alumno al profe en la base de datos
    private void asignaAlumnos(HttpServletRequest request, HttpServletResponse response) throws Exception {
        mapper = new ObjectMapper();
        adminC = new AdministradorController();
        List<TbMateriaAlumno> listmateriaalum = new ArrayList<TbMateriaAlumno>();
        objectJson = request.getParameter("OBJETO");
        try {
            listmateriaalum = Arrays.asList(mapper.readValue(objectJson, TbMateriaAlumno[].class));
            adminC.asignaAlumnos(Integer.parseInt(request.getParameter("TIPOESCUELA")), listmateriaalum);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*Funcion para la vista que muestra los parametros para guardar la lista de sistencia, usamos la clase Calendar
    para obtener el dia actual y mostrarlo en la toma de lista*/
    private void listaAlumnos(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        alumC = new AlumnosController();
        List<TbMateriaPersonal> listmateria = new ArrayList<>();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();
        List<CtDia> listdia = new ArrayList<>();
        CtSemanaFiscal semana = new CtSemanaFiscal();
        CtPeriodoEscolar periodo = new CtPeriodoEscolar();
        TbPersonal personal = new TbPersonal();
        Calendar fecha = Calendar.getInstance();
        String[] strDays = new String[]{"Sunday", "Monday", "Tuesday",
            "Wednesday", "Thusday", "Friday", "Saturday"};
        try {
            listmateria = adminC.getMateriasAPersonal(Integer.parseInt(request.getParameter("TIPOESCUELA")), Integer.parseInt(request.getParameter("ID")));
            request.setAttribute("listmateria", listmateria);
            listgrado = adminC.getGrado(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrupo", listgrupo);
            listdia = adminC.getDias(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listdia", listdia);
             String dia = strDays[fecha.get(Calendar.DAY_OF_WEEK) - 1];
            switch (dia) {
                case "Monday":
                    request.setAttribute("diaactual", "Lunes");
                    break;
                case "Tuesday":
                    request.setAttribute("diaactual", "Martes");
                    break;
                case "Wednesday":
                    request.setAttribute("diaactual", "Miercoles");
                    break;
                case "Thusday":
                    request.setAttribute("diaactual", "Jueves");
                    break;
                case "Friday":
                    request.setAttribute("diaactual", "Viernes");
                    break;

            }
            System.out.println(dia);
            System.out.println(new Date());
            //request.setAttribute("diaactual", "Viernes");
            semana = alumC.getSemanaiscalAsistencia(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("semana", semana);
            periodo = alumC.getPeriodosAsistencia(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("periodo", periodo);
            personal = adminC.datosPeronal(Integer.parseInt(request.getParameter("ID")));
            request.setAttribute("personal", personal);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/listaalumnos.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println("lista" + e);
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    //Funcion que trae la lista de los alumnos de aacuerdo a la materia del profe
    private void getListaAlumno(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        alumC = new AlumnosController();
        List<TbMateriaPersonal> listmateria = new ArrayList<>();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();
        List<Alumno> listalumno = new ArrayList<>();
        List<CtDia> listdia = new ArrayList<>();
        CtSemanaFiscal semana = new CtSemanaFiscal();
        CtPeriodoEscolar periodo = new CtPeriodoEscolar();
        Calendar fecha = Calendar.getInstance();
        String[] strDays = new String[]{"Sunday", "Monday", "Tuesday",
            "Wednesday", "Thusday", "Friday", "Saturday"};
        try {
            listmateria = adminC.getMateriasAPersonal(Integer.parseInt(request.getParameter("TIPOESCUELA")), Integer.parseInt(request.getParameter("ID")));
            request.setAttribute("listmateria", listmateria);
            listgrado = adminC.getGrado(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrupo", listgrupo);
            listdia = adminC.getDias(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listdia", listdia);
            /* String dia = strDays[fecha.get(Calendar.DAY_OF_WEEK) - 1];
            switch (dia) {
                case "Monday":
                    request.setAttribute("diaactual", "Lunes");
                    break;
                case "Tuesday":
                    request.setAttribute("diaactual", "Martes");
                    break;
                case "Wednesday":
                    request.setAttribute("diaactual", "Miercoles");
                    break;
                case "Thusday":
                    request.setAttribute("diaactual", "Jueves");
                    break;
                case "Friday":
                    request.setAttribute("diaactual", "viernes");
                    break;

            }
            System.out.println(dia);*/
            //request.setAttribute("diaactual", "Jueves");
            semana = alumC.getSemanaiscalAsistencia(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("semana", semana);
            periodo = alumC.getPeriodosAsistencia(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("periodo", periodo);
            listalumno = alumC.getListaAlumnos(Integer.parseInt(request.getParameter("GRADO")), Integer.parseInt(request.getParameter("GRUPO")), Integer.parseInt(request.getParameter("AREA")), Integer.parseInt(request.getParameter("CPT")), Integer.parseInt(request.getParameter("IDM")), Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listalumno", listalumno);
            //La funcion verifica trae un entero con la suma de los registros en la tabla de assitencia, si devuelve mayor que 0 significa que se tomó lista de ese dia
            //por lo tanto el boton de guardar asistencia para esa materia se debe ocultar y se debe mostrar el de actualizar y viceversa
            verifica = adminC.verificarAsistencia(Integer.parseInt(request.getParameter("IDP")), Integer.parseInt(request.getParameter("IDS")), Integer.parseInt(request.getParameter("IDD")), Integer.parseInt(request.getParameter("IDM")), Integer.parseInt(request.getParameter("TIPOESCUELA")));
            if (verifica > 0) {
                bandera = 1;
            } else {
                bandera = 0;
            }
            request.setAttribute("bandera", bandera);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/alumnoslista.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println("getlista" + e);
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    //Funcion que guarda en la base de datos la lista de los alumnos a los que se les tomó asistencia
    private void guardaAsistencia(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        mapper = new ObjectMapper();
        objectJson = request.getParameter("OBJETO");
        List<TbAsistencia> listasistencia = new ArrayList<>();
        try {
            listasistencia = Arrays.asList(mapper.readValue(objectJson, TbAsistencia[].class));
            adminC.guardaAsistencia(listasistencia, Integer.parseInt(request.getParameter("TIPOESCUELA")));

        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    //Funcion que trae la vista que muestra los parametros para la tabla de reporte de asistencia que muestra los alumnos que han faltado
    private void reporteAsistencia(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        alumC = new AlumnosController();
        List<TbMateriaPersonal> listmateria = new ArrayList<>();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();
        List<Alumno> listalumno = new ArrayList<>();
        List<CtDia> listdia = new ArrayList<>();
        List<CtSemanaFiscal> listsemana = new ArrayList<>();
        List<CtPeriodoEscolar> listperiodo = new ArrayList<>();
        TbPersonal personal = new TbPersonal();
        try {
            listmateria = adminC.getMateriasAPersonal(Integer.parseInt(request.getParameter("TIPOESCUELA")), Integer.parseInt(request.getParameter("ID")));
            request.setAttribute("listmateria", listmateria);
            listsemana = alumC.getSemanaiscal(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listsemana", listsemana);
            listperiodo = alumC.getPeriodos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listperiodo", listperiodo);
            personal = adminC.datosPeronal(Integer.parseInt(request.getParameter("ID")));
            request.setAttribute("personal", personal);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/reporteasistencia.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    //Trae los alumnos con faltas de acuerdo a los parametros requeridos
    private void getreporteAsistencia(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        alumC = new AlumnosController();
        List<TbMateriaPersonal> listmateria = new ArrayList<>();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();
        List<TbAsistencia> listalumno = new ArrayList<>();
        List<TbAsistencia> listalumnoJ = new ArrayList<>();
        List<CtDia> listdia = new ArrayList<>();
        List<CtSemanaFiscal> listsemana = new ArrayList<>();
        List<CtPeriodoEscolar> listperiodo = new ArrayList<>();
        try {
            listmateria = adminC.getMateriasAPersonal(Integer.parseInt(request.getParameter("TIPOESCUELA")), Integer.parseInt(request.getParameter("ID")));
            request.setAttribute("listmateria", listmateria);
            listsemana = alumC.getSemanaiscal(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listsemana", listsemana);
            listperiodo = alumC.getPeriodos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listperiodo", listperiodo);
            listalumno = alumC.getAlumnosAsistencia(Integer.parseInt(request.getParameter("IDP")), Integer.parseInt(request.getParameter("IDM")), Integer.parseInt(request.getParameter("IDS")), Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listalumno", listalumno);
            listalumnoJ = alumC.getAlumnosAsistenciaJ(Integer.parseInt(request.getParameter("IDP")), Integer.parseInt(request.getParameter("IDM")), Integer.parseInt(request.getParameter("IDS")), Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listalumnoJ", listalumnoJ);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/getreporteasistencia.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    //Funcion que trae la vist que muestra la lista con los datos que se hayan guardado en ese dia por cualquier posible correccion
    private void asistenciaAnterior(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        alumC = new AlumnosController();
        List<TbAsistencia> listasistencia = new ArrayList<>();
        TbPersonal personal = new TbPersonal();
        CtDatosMateria materia = new CtDatosMateria();
        try {
            listasistencia = alumC.asistenciaAnterior(Integer.parseInt(request.getParameter("IDD")), Integer.parseInt(request.getParameter("IDP")), Integer.parseInt(request.getParameter("IDS")), Integer.parseInt(request.getParameter("IDM")), Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listasistencia", listasistencia);
            personal = adminC.datosPeronal(Integer.parseInt(request.getParameter("ID")));
            request.setAttribute("personal", personal);
            materia = adminC.getMateriaAsistenciaA(Integer.parseInt(request.getParameter("IDM")), Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("materia", materia);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/asistenciaanterior.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    //Funcion que actualiza los datos de la assitencia
    private void actualizaAsistencia(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        mapper = new ObjectMapper();
        objectJson = request.getParameter("OBJETO");
        List<TbAsistencia> listasistencia = new ArrayList<>();
        try {
            listasistencia = Arrays.asList(mapper.readValue(objectJson, TbAsistencia[].class));
            adminC.actualizaAsistencia(listasistencia);
            System.out.println(listasistencia);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    //Funcion
    private void verificaAsistencia(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        alumC = new AlumnosController();
        List<TbMateriaPersonal> listmateria = new ArrayList<>();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();
        List<Alumno> listalumno = new ArrayList<>();
        List<CtDia> listdia = new ArrayList<>();
        List<CtSemanaFiscal> listsemana = new ArrayList<>();
        List<CtPeriodoEscolar> listperiodo = new ArrayList<>();
        try {
            listmateria = adminC.getMateriasAPersonal(Integer.parseInt(request.getParameter("TIPOESCUELA")), Integer.parseInt(request.getParameter("ID")));
            request.setAttribute("listmateria", listmateria);
            listgrado = adminC.getGrado(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrupo", listgrupo);
            listdia = adminC.getDias(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listdia", listdia);
            listsemana = alumC.getSemanaiscal(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listsemana", listsemana);
            listperiodo = alumC.getPeriodos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listperiodo", listperiodo);
            listalumno = alumC.getListaAlumnos(Integer.parseInt(request.getParameter("GRADO")), Integer.parseInt(request.getParameter("GRUPO")), Integer.parseInt(request.getParameter("AREA")), Integer.parseInt(request.getParameter("CPT")), Integer.parseInt(request.getParameter("IDM")), Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listalumno", listalumno);

            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/alumnoslista.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    //Funcion para tratar los archivos
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
