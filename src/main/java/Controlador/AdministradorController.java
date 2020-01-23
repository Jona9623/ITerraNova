/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import BD.ConsultasAdministrador;
import BD.ConsultasAlumno;
import Modelos.CtAreaalumno;
import Modelos.CtCptalumno;
import Modelos.CtGrado;
import Modelos.CtGrupo;
import Modelos.CtPuesto;
import Modelos.TbAlumnos;
import Modelos.TbPersonal;
import Modelos.TbTutor;
import java.util.List;

/**
 *
 * @author Jonat
 */
public class AdministradorController {
    
    public List<TbAlumnos> getAlumnos() {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getAlumnos();
    }
    
    public List<TbPersonal> getPersonal() {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getPersonal();
    }
    public void guardaTutor(TbTutor tutor){
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaTutor(tutor);
    }
    
    public void guardaAlumno(TbAlumnos alumno) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaAlumno(alumno);
    }
    
    public TbAlumnos datosAlumno(int idalumno) {
        ConsultasAdministrador consulta = new  ConsultasAdministrador();
        return consulta.datosAlumno(idalumno);
    }
    
    public void actualizaTutor(TbTutor tutor) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaTutor(tutor);
    }
    
    public void actualizaAlumno(TbAlumnos alumno) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaAlumno(alumno);
    }
    
    public void eliminaAlumno(int id) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaAlumno(id);
    }
    
    public TbTutor datosTutor(int idtutor) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.datosTutor(idtutor);
    }
    
    public TbPersonal datosPeronal(int idpersonal) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.datosPersonal(idpersonal);
    }
    
    public void actualizaPersonal(TbPersonal personal) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaPersonal(personal);
    }
    
    public void eliminaPersonal(int id) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaPersonal(id);
    }
    
    public void guardaPersonal(TbPersonal personal) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaPersonal(personal);
    }

    public List<CtGrado> getGrado() {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getGrado();
    }

    public List<CtGrupo> getGrupo() {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getGrupo();
    }

    public List<CtAreaalumno> getArea() {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getArea();
    }

    public List<CtCptalumno> getCpt() {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getCpt();
    }

    public List<CtPuesto> getPuesto() {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getPuesto();
    }


}
