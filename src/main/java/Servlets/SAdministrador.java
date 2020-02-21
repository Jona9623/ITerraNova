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
import Modelos.CtDatosMateria;
import Modelos.CtGrado;
import Modelos.CtGrupo;
import Modelos.CtPeriodoEscolar;
import Modelos.CtPuesto;
import Modelos.CtTipoCalificaicon;
import Modelos.GradoGrupo;
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
            case "guardaPuesto": guardaPuesto(request,response);break;
            case "eliminaPuesto": eliminaPuesto(request,response);break;
            case "tablaPeriodo": tablaPeriodo(request,response);break;
            case "guardaPeriodo": guardaPeriodo(request,response);break;
            case "eliminaPeriodo": eliminaPeriodo (request,response);break;
            case "tablaArea": tablaArea (request,response);break;
            case "guardaArea": guardaArea(request,response);break;
            case "eliminaArea": eliminaArea(request,response);break;
            case "tablaCpt": tablaCpt(request,response);break;
            case "guardaCpt": guardaCpt( request,response);break;
            case "eliminaCpt": eliminaCpt(request,response); break;
            case "tablaGradoGrupo": tablaGradoGrupo(request,response); break;
            case "tablaTipoCalificacion": tablaTipoCalificacion(request,response);break;
            case"guardaTipoCali": guardaTipoCali(request,response);break;
            case "eliminaTipoCali": eliminaTipoCali(request,response);break;
            case "tablaMateria": tablaMateria(request,response);break;
            case "guardaGrado": guardaGrado(request,response);break;
            case "guardaGrupo": guardaGrupo(request,response); break;
            case "guardaNombreMateria": guardaNombreMateria(request,response);break;
            case "guardaMateria": guardaMateria(request,response);break;
            case "eliminaMateria": eliminaMateria(request,response);break;
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
            listpuesto = adminC.getPuesto(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listpuesto", listpuesto);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/Puesto/tablapuesto.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void guardaPuesto(HttpServletRequest request, HttpServletResponse response) {
        CtPuesto puesto = new CtPuesto();
        adminC = new AdministradorController();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            puesto = mapper.readValue(objectJson, CtPuesto.class);
            if(puesto.getIdtbpuesto() > 0)
                adminC.actualizaPuesto(puesto);
            else
                adminC.guardaPuesto(puesto, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void eliminaPuesto(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        try {
            adminC.eliminaPuesto(Integer.parseInt(request.getParameter("ID")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void tablaPeriodo(HttpServletRequest request, HttpServletResponse response) {
        alumC = new AlumnosController();
        List <CtPeriodoEscolar> listperiodo = new ArrayList<>();
        try {
            listperiodo = alumC.getPeriodos(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listperiodo", listperiodo);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/PeriodoEscolar/tablaperiodo.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void guardaPeriodo(HttpServletRequest request, HttpServletResponse response) {
        CtPeriodoEscolar periodo = new CtPeriodoEscolar();
        adminC = new AdministradorController();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            periodo = mapper.readValue(objectJson, CtPeriodoEscolar.class);
            if(periodo.getIdtbperiodo() > 0)
                adminC.actualizaPeriodo(periodo);
            else
                adminC.guardaPeriodo(periodo, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void eliminaPeriodo(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        try {
            adminC.eliminaPeriodo(Integer.parseInt(request.getParameter("ID")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void tablaArea(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        List <CtAreaalumno> listarea = new ArrayList<>();
        try {
            listarea = adminC.getArea(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listarea", listarea);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/AreaAlumno/tablaareaalumno.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void guardaArea(HttpServletRequest request, HttpServletResponse response) {
        CtAreaalumno area = new CtAreaalumno();
        adminC = new AdministradorController();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            area = mapper.readValue(objectJson, CtAreaalumno.class);
            if(area.getIdtbarea() > 0)
                adminC.actualizaArea(area);
            else
                adminC.guardaArea(area, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void eliminaArea(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        try {
            adminC.eliminaArea(Integer.parseInt(request.getParameter("ID")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void tablaCpt(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        List <CtCptalumno> listcpt = new ArrayList<>();
         try {
             listcpt = adminC.getCpt(Integer.parseInt(request.getParameter("TIPOESCUELA")));
             request.setAttribute("listcpt", listcpt);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/Cptalumno/tablacptalumno.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void guardaCpt(HttpServletRequest request, HttpServletResponse response) {
        CtCptalumno cpt = new CtCptalumno();
        adminC = new AdministradorController();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            cpt = mapper.readValue(objectJson, CtCptalumno.class);
            if(cpt.getIdtbcpt() > 0)
                adminC.actualizaCpt(cpt);
            else
                adminC.guardaCpt(cpt, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void eliminaCpt(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        try {
            adminC.eliminaCpt(Integer.parseInt(request.getParameter("ID")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void tablaGradoGrupo(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        List <GradoGrupo> listgradogrupo = new ArrayList<>();
        try {
            listgradogrupo = adminC.getGradoGrupo(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listgradogrupo", listgradogrupo);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/TipoCalificacion/tablagradogrupo.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void tablaTipoCalificacion(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        List <CtTipoCalificaicon> listtipocali = new ArrayList<>();
        try {
            listtipocali = adminC.getTipoCali(Integer.parseInt(request.getParameter("TIPOESCUELA")));
            request.setAttribute("listtipocali", listtipocali);
            RequestDispatcher rd = request.getRequestDispatcher("vista/Administrador/TipoCalificacion/tablatipocalificacion.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void guardaTipoCali(HttpServletRequest request, HttpServletResponse response) {
        CtTipoCalificaicon tipocali = new CtTipoCalificaicon();
        adminC = new AdministradorController();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            tipocali = mapper.readValue(objectJson, CtTipoCalificaicon.class);
            if(tipocali.getIdtbtipocali() > 0)
                adminC.actualizaTipoCali(tipocali);
            else
                adminC.guardaTipoCali(tipocali, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
        }
    }

    private void eliminaTipoCali(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        try {
            adminC.eliminaTipoCali(Integer.parseInt(request.getParameter("ID")));
        } catch (Exception e) {
        }
    }

    private void tablaMateria(HttpServletRequest request, HttpServletResponse response) {
        alumC = new AlumnosController();
        adminC = new AdministradorController();
        List <TbMateria> listmateria = new ArrayList<>();
        List <CtDatosMateria> listmateriafaltante = new ArrayList<>();
        List <CtAreaalumno> listarea = new ArrayList<>();
        List <CtCptalumno> listcpt = new ArrayList<>();
        List <CtGrado> listgrado = new ArrayList<>();
        List <CtGrupo> listgrupo = new ArrayList<>();
        
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
            System.out.println(e);
        }
    }

    private void guardaGrado(HttpServletRequest request, HttpServletResponse response) {
        CtGrado grado = new CtGrado();
        adminC = new AdministradorController();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            grado = mapper.readValue(objectJson, CtGrado.class);
            adminC.guardaGrado(grado, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void guardaGrupo(HttpServletRequest request, HttpServletResponse response) {
        CtGrupo grupo = new CtGrupo();
        adminC = new AdministradorController();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            grupo = mapper.readValue(objectJson, CtGrupo.class);
            adminC.guardaGrupo(grupo, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void guardaNombreMateria(HttpServletRequest request, HttpServletResponse response) {
        CtDatosMateria nombremateria = new CtDatosMateria();
        adminC = new AdministradorController();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            nombremateria = mapper.readValue(objectJson, CtDatosMateria.class);
            adminC.guardaNombreMateria(nombremateria,Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void guardaMateria(HttpServletRequest request, HttpServletResponse response) {
        TbMateria materia = new TbMateria();
        adminC = new AdministradorController();
        objectJson = request.getParameter("OBJETO");
        mapper = new ObjectMapper();
        try {
            materia = mapper.readValue(objectJson, TbMateria.class);
            adminC.guardaMateria(materia, Integer.parseInt(request.getParameter("TIPOESCUELA")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void eliminaMateria(HttpServletRequest request, HttpServletResponse response) {
        adminC = new AdministradorController();
        try {
            adminC.eliminaMateria(Integer.parseInt(request.getParameter("ID")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
