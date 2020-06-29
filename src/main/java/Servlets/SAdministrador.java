/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.AdministradorController;
import Controlador.AlumnosController;
import Modelos.Alumno;
import Modelos.CtAreaalumno;
import Modelos.CtCptalumno;
import Modelos.CtDatosMateria;
import Modelos.CtDia;
import Modelos.CtGrado;
import Modelos.CtGrupo;
import Modelos.CtPeriodoEscolar;
import Modelos.CtPuesto;
import Modelos.CtSemanaFiscal;
import Modelos.CtTipoCalificaicon;
import Modelos.GradoGrupo;
import Modelos.TbAsistencia;
import Modelos.TbMateria;
import Modelos.TbMateriaAlumno;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
    public int idsemana = 0;
    public int idperiodo = 0;

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
        /*Aqui vamos a tomar el contenido de ACCION que enviamos desde el js y lo evaluamos para saber que hacer*/
        String accion = request.getParameter("ACCION");
        switch (accion) {
            case "tablaPuesto":
                try {
                    tablaPuesto(request, response);
                } catch (Exception e) {
                }
                break;
            case "guardaPuesto":
                try {
                    guardaPuesto(request, response);
                } catch (Exception e) {
                }
                break;
            case "eliminaPuesto":
                eliminaPuesto(request, response);
                break;
            case "tablaPeriodo":
                try {
                   tablaPeriodo(request, response); 
                } catch (Exception e) {
                }
                break;
            case "guardaPeriodo":
                try {
                   guardaPeriodo(request, response); 
                } catch (Exception e) {
                }
                break;
            case "eliminaPeriodo":
                eliminaPeriodo(request, response);
                break;
            case "tablaArea":
                try {
                    tablaArea(request, response);
                } catch (Exception e) {
                }
                break;
            case "guardaArea":
                try {
                    guardaArea(request, response);
                } catch (Exception e) {
                }
                break;
            case "eliminaArea":
                eliminaArea(request, response);
                break;
            case "tablaCpt":
                try {
                   tablaCpt(request, response); 
                } catch (Exception e) {
                }
                break;
            case "guardaCpt":
                try {
                    guardaCpt(request, response);
                } catch (Exception e) {
                }
                break;
            case "eliminaCpt":
                eliminaCpt(request, response);
                break;
            /*case "tablaGradoGrupo":
                try {
                    tablaGradoGrupo(request, response);
                } catch (Exception e) {
                }
                break;*/
            case "tablaTipoCalificacion":
                try {
                   tablaTipoCalificacion(request, response); 
                } catch (Exception e) {
                }
                break;
            case "guardaTipoCali":
                try {
                   guardaTipoCali(request, response); 
                } catch (Exception e) {
                }
                break;
            case "eliminaTipoCali":
                eliminaTipoCali(request, response);
                break;
            case "tablaMateria":
                try {
                    tablaMateria(request, response);
                } catch (Exception e) {
                }
                break;
            case "guardaGrado":
                try {
                   guardaGrado(request, response); 
                } catch (Exception e) {
                }
                break;
            case "guardaGrupo":
                try {
                    guardaGrupo(request, response);
                } catch (Exception e) {
                }
                break;
            case "guardaNombreMateria":
                try {
                    guardaNombreMateria(request, response);
                } catch (Exception e) {
                }
                break;
            case "guardaMateria":
                try {
                   guardaMateria(request, response); 
                } catch (Exception e) {
                }
                break;
            case "eliminaMateria":
                eliminaMateria(request, response);
                break;
            case "reporteGAsistencia":
                try {
                    reporteGAsistencia(request,response);
                } catch (Exception e) {
                }
                break;
            case "getreporteGAsistencia":
                try {
                    getreporteGAsistencia(request,response);
                } catch (Exception e) {
                }
                break;
            case "justificarFaltas":
                try {
                    justificarFaltas(request,response);
                } catch (Exception e) {
                }
                break;
            case "getDiasFaltas":
                try {
                    getDiasFaltas(request,response);
                } catch (Exception e) {
                }
                break;
            case "updateJustificar":
                try {
                    updateJustificar(request,response);
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
    /*Funcion que mostra´ra la vista de tabla personal, haciendo la consulta necesaria para dicha informacion*/
    private void tablaPuesto(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        List<CtPuesto> listpuesto = new ArrayList<>();
        try {
            listpuesto = adminC.getPuesto(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listpuesto", listpuesto);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/Puesto/tablapuesto.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*No genera ninguna vista, guarda la informacion que procede de presionar el boton guardar*/
    private void guardaPuesto(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CtPuesto puesto = new CtPuesto();
        adminC = new AdministradorController();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            puesto = mapper.readValue(objectJson, CtPuesto.class);
            if (puesto.getIdtbpuesto() > 0) {
                adminC.actualizaPuesto(puesto);
            } else {
                adminC.guardaPuesto(puesto, Integer.parseInt(request.getParameter("TIPOESCUELA")));
            }
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*No genera ninguna vista, elimina el registro seleccionado al presionar el boton de eliminar*/
    private void eliminaPuesto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        adminC = new AdministradorController();
        try {
            adminC.eliminaPuesto(Integer.parseInt(request.getParameter("ID")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*Muestra la vista de la tabla periodo*/
    private void tablaPeriodo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        alumC = new AlumnosController();
        List<CtPeriodoEscolar> listperiodo = new ArrayList<>();
        try {
            listperiodo = alumC.getPeriodos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listperiodo", listperiodo);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/PeriodoEscolar/tablaperiodo.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
           response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*No genera vista, solo guarda registro de nuevo periodo al presionar el boton de gurdar*/
    private void guardaPeriodo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CtPeriodoEscolar periodo = new CtPeriodoEscolar();
        adminC = new AdministradorController();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            periodo = mapper.readValue(objectJson, CtPeriodoEscolar.class);
            if (periodo.getIdtbperiodo() > 0) {
                adminC.actualizaPeriodo(periodo);
            } else {
                adminC.guardaPeriodo(periodo, Integer.parseInt(request.getParameter("TIPOESCUELA")));
            }
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*No genera vista, elimina el registro al presionar el boton de eliminar*/
    private void eliminaPeriodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        adminC = new AdministradorController();
        try {
            adminC.eliminaPeriodo(Integer.parseInt(request.getParameter("ID")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*Genera la vista de la tabla area*/
    private void tablaArea(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        List<CtAreaalumno> listarea = new ArrayList<>();
        try {
            listarea = adminC.getArea(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listarea", listarea);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/AreaAlumno/tablaareaalumno.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*No genera vista, guarda el registro al presionar el boton de guardar*/
    private void guardaArea(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CtAreaalumno area = new CtAreaalumno();
        adminC = new AdministradorController();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            area = mapper.readValue(objectJson, CtAreaalumno.class);
            if (area.getIdtbarea() > 0) {
                adminC.actualizaArea(area);
            } else {
                adminC.guardaArea(area, Integer.parseInt(request.getParameter("TIPOESCUELA")));
            }
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*No genera vista, elimina el registro al presionar el boton de elimianr*/
    private void eliminaArea(HttpServletRequest request, HttpServletResponse response) throws IOException {
        adminC = new AdministradorController();
        try {
            adminC.eliminaArea(Integer.parseInt(request.getParameter("ID")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*Genera la vista de la tabla cpt*/
    private void tablaCpt(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        List<CtCptalumno> listcpt = new ArrayList<>();
        try {
            listcpt = adminC.getCpt(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listcpt", listcpt);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/Cptalumno/tablacptalumno.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*No genera vista, guarda el registro al presioanr el boton*/
    private void guardaCpt(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CtCptalumno cpt = new CtCptalumno();
        adminC = new AdministradorController();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            cpt = mapper.readValue(objectJson, CtCptalumno.class);
            if (cpt.getIdtbcpt() > 0) {
                adminC.actualizaCpt(cpt);
            } else {
                adminC.guardaCpt(cpt, Integer.parseInt(request.getParameter("TIPOESCUELA")));
            }
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*No genera vista, elimina el registro al preiosnar el boton*/
    private void eliminaCpt(HttpServletRequest request, HttpServletResponse response) throws IOException {
        adminC = new AdministradorController();
        try {
            adminC.eliminaCpt(Integer.parseInt(request.getParameter("ID")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*
    private void tablaGradoGrupo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        List<GradoGrupo> listgradogrupo = new ArrayList<>();
        try {
            listgradogrupo = adminC.getGradoGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgradogrupo", listgradogrupo);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/TipoCalificacion/tablagradogrupo.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }*/
    /*Genera la vista de la tabla de tipo de calificacion*/
    private void tablaTipoCalificacion(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        List<CtTipoCalificaicon> listtipocali = new ArrayList<>();
        try {
            listtipocali = adminC.getTipoCali(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listtipocali", listtipocali);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/TipoCalificacion/tablatipocalificacion.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*No genera vista, guarda el registro al presionar el boton*/
    private void guardaTipoCali(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CtTipoCalificaicon tipocali = new CtTipoCalificaicon();
        adminC = new AdministradorController();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            tipocali = mapper.readValue(objectJson, CtTipoCalificaicon.class);
            if (tipocali.getIdtbtipocali() > 0) {
                adminC.actualizaTipoCali(tipocali);
            } else {
                adminC.guardaTipoCali(tipocali, Integer.parseInt(request.getParameter("TIPOESCUELA")));
            }
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*No genera vista, elimina e lregistro al presionar boton de eliminar*/
    private void eliminaTipoCali(HttpServletRequest request, HttpServletResponse response) throws IOException {
        adminC = new AdministradorController();
        try {
            adminC.eliminaTipoCali(Integer.parseInt(request.getParameter("ID")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*Genera la vista de la tabla materia*/
    private void tablaMateria(HttpServletRequest request, HttpServletResponse response) throws Exception {
        alumC = new AlumnosController();
        adminC = new AdministradorController();
        List<TbMateria> listmateria = new ArrayList<>();
        List<CtDatosMateria> listmateriafaltante = new ArrayList<>();
        List<CtAreaalumno> listarea = new ArrayList<>();
        List<CtCptalumno> listcpt = new ArrayList<>();
        List<CtGrado> listgrado = new ArrayList<>();
        List<CtGrupo> listgrupo = new ArrayList<>();

        try {
            listmateria = alumC.getMaterias(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listmateria", listmateria);
            listmateriafaltante = adminC.getMateriasFaltantes(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listmateriafaltante", listmateriafaltante);
            listarea = adminC.getArea(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listarea", listarea);
            listcpt = adminC.getCpt(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listcpt", listcpt);
            listgrado = adminC.getGrado(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrupo", listgrupo);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/Materia/tablamaterias.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*No genera vista, guarda el registro al presionar el boton guardar*/
    private void guardaGrado(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CtGrado grado = new CtGrado();
        adminC = new AdministradorController();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            grado = mapper.readValue(objectJson, CtGrado.class);
            adminC.guardaGrado(grado, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*No genera vista, guarda el registro al presionar el boton guardar*/
    private void guardaGrupo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CtGrupo grupo = new CtGrupo();
        adminC = new AdministradorController();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            grupo = mapper.readValue(objectJson, CtGrupo.class);
            adminC.guardaGrupo(grupo, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*No genera vista, guarda el registro al presionar el boton guardar (aqui solo se guarda el nombre de la materia)*/
    private void guardaNombreMateria(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CtDatosMateria nombremateria = new CtDatosMateria();
        adminC = new AdministradorController();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            nombremateria = mapper.readValue(objectJson, CtDatosMateria.class);
            adminC.guardaNombreMateria(nombremateria, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*No genera vista, guarda el registro al presionar el boton guardar (aqui se asocia las materias con su respectivo grado, grupo, area o cpt)*/
    private void guardaMateria(HttpServletRequest request, HttpServletResponse response) throws Exception {
        TbMateria materia = new TbMateria();
        adminC = new AdministradorController();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            materia = mapper.readValue(objectJson, TbMateria.class);
            adminC.guardaMateria(materia, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*No genera vista, se elimina el registro al presionar el boton eliminar*/
    private void eliminaMateria(HttpServletRequest request, HttpServletResponse response) throws IOException {
        adminC = new AdministradorController();
        try {
            adminC.eliminaMateria(Integer.parseInt(request.getParameter("ID")));
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*Genera la vista para el reporte de asistencia general esta vista carga los parametros a considerar para mostrar la informacion*/
    private void reporteGAsistencia(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        alumC = new AlumnosController();
        List <CtSemanaFiscal> listsemana = new ArrayList<>();
        List <CtPeriodoEscolar> listperiodo = new ArrayList<>();
        List <CtGrado> listgrado = new ArrayList<>();
        List <CtGrupo> listgrupo = new ArrayList<>();
        try {
            listperiodo = alumC.getPeriodos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listperiodo", listperiodo);
            listsemana = alumC.getSemanaiscal(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listsemana", listsemana);
            listgrado = adminC.getGrado(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrupo", listgrupo);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/ReporteGAsistencia/reporteGAsistencia.jsp");
            rd.forward(request, response);
            
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*Se genera la vista que va junto a la funcion anterior, esta parte solo carga el listado de los alumnos, materias y las faltas*/
    private void getreporteGAsistencia(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        alumC = new AlumnosController();
        List <CtSemanaFiscal> listsemana = new ArrayList<>();
        List <CtPeriodoEscolar> listperiodo = new ArrayList<>();
        List <CtGrado> listgrado = new ArrayList<>();
        List <CtGrupo> listgrupo = new ArrayList<>();
        List <TbAsistencia> listasistencia = new ArrayList<>();
        List <CtDatosMateria> listmateria = new ArrayList<>();
        List <Alumno> listalumnos = new ArrayList<>();
        try {
            listperiodo = alumC.getPeriodos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listperiodo", listperiodo);
            listsemana = alumC.getSemanaiscal(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listsemana", listsemana);
            listgrado = adminC.getGrado(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrado", listgrado);
            listgrupo = adminC.getGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgrupo", listgrupo);
            listasistencia = adminC.getreporteGAsistencia(Integer.parseInt(request.getParameter("IDP")),Integer.parseInt(request.getParameter("IDS")),Integer.parseInt(request.getParameter("IDGRADO")),Integer.parseInt(request.getParameter("IDGRUPO")),Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listasistencia", listasistencia);
            listmateria = adminC.getMateriasAsistencia(Integer.parseInt(request.getParameter("IDGRADO")),Integer.parseInt(request.getParameter("IDGRUPO")),Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listmateria", listmateria);
            listalumnos = adminC.getAlumnosAsistencia(Integer.parseInt(request.getParameter("IDGRADO")),Integer.parseInt(request.getParameter("IDGRUPO")),Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listalumnos", listalumnos);
            idsemana = Integer.parseInt(request.getParameter("IDS"));
            idperiodo = Integer.parseInt(request.getParameter("IDP"));
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/ReporteGAsistencia/getreporteGasistencia.jsp");
            rd.forward(request, response);
            
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*Genera la vista para justificar faltas, solo carga los parametros a considerar para mostrar los dias faltados*/
    private void justificarFaltas(HttpServletRequest request, HttpServletResponse response) throws Exception {
        adminC = new AdministradorController();
        alumC = new AlumnosController();
        List <CtSemanaFiscal> listsemana = new ArrayList<>();
        List <CtPeriodoEscolar> listperiodo = new ArrayList<>();
        List <CtDatosMateria> listmateria = new ArrayList<>();
        Alumno alumno = new Alumno();
        try {
            listperiodo = alumC.getPeriodos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listperiodo", listperiodo);
            listsemana = alumC.getSemanaiscal(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listsemana", listsemana);
            listmateria = adminC.justificarFaltas(Integer.parseInt(request.getParameter("IDA")),Integer.parseInt(request.getParameter("IDS")),Integer.parseInt(request.getParameter("IDP")),Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listmateria", listmateria);
            alumno = adminC.getAlumno(Integer.parseInt(request.getParameter("IDA")),Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("alumno", alumno);
            request.setAttribute("idsemana", idsemana);
            request.setAttribute("idperiodo", idperiodo);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/ReporteGAsistencia/justificar.jsp");
            rd.forward(request, response);
            
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*Muestra la tabla con los dias faltados, esta vista va junto con la funcion anterior*/
    private void getDiasFaltas(HttpServletRequest request, HttpServletResponse response) throws Exception{
        adminC = new AdministradorController();
        alumC = new AlumnosController();
        List <CtSemanaFiscal> listsemana = new ArrayList<>();
        List <CtPeriodoEscolar> listperiodo = new ArrayList<>();
        List <CtDatosMateria> listmateria = new ArrayList<>();
        List <CtDia> listdias = new ArrayList<>();
        try {
            listperiodo = alumC.getPeriodos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listperiodo", listperiodo);
            listsemana = alumC.getSemanaiscal(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listsemana", listsemana);
            listmateria = adminC.justificarFaltas(Integer.parseInt(request.getParameter("IDA")),Integer.parseInt(request.getParameter("IDS")),Integer.parseInt(request.getParameter("IDP")),Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listmateria", listmateria);
            listdias = adminC.getDiasFaltas(Integer.parseInt(request.getParameter("IDA")),Integer.parseInt(request.getParameter("IDP")),Integer.parseInt(request.getParameter("IDS")),Integer.parseInt(request.getParameter("IDM")),Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listdias", listdias);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/ReporteGAsistencia/getdiasfaltas.jsp");
            rd.forward(request, response);           
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }
    /*No genera vista, solo acualiza el registro para justificar las faltas*/
    private void updateJustificar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        mapper = new ObjectMapper();
        adminC = new AdministradorController();
        List<TbAsistencia> listasistencia = new ArrayList<TbAsistencia>();
        objectJson = request.getParameter("OBJETO");
        try {
            listasistencia = Arrays.asList(mapper.readValue(objectJson, TbAsistencia[].class));
            System.out.println(listasistencia);
            adminC.updateJustificar(listasistencia);
        } catch (Exception e) {
            response.addHeader("ERROR", e.toString());
            response.sendError(204);
        }
    }

}
