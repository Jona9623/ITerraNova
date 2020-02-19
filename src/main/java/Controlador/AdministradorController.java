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
import Modelos.CtDatosMateria;
import Modelos.CtGrado;
import Modelos.CtGrupo;
import Modelos.CtPeriodoEscolar;
import Modelos.CtPuesto;
import Modelos.CtTipoCalificaicon;
import Modelos.GradoGrupo;
import Modelos.TbAlumnos;
import Modelos.TbMateria;
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
    
    public List<GradoGrupo> getGradoGrupo() {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getGradoGrupo();
    }

    public List<CtTipoCalificaicon> getTipoCali() {
       ConsultasAdministrador consulta = new ConsultasAdministrador();
       return consulta.getTipoCali();
    }

    public void guardaPuesto(CtPuesto puesto) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaPuesto(puesto);
    }

    public void actualizaPuesto(CtPuesto puesto) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaPuesto(puesto);
    }

    public void eliminaPuesto(int id) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaPuesto(id);
    }

    public void guardaPeriodo(CtPeriodoEscolar periodo) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaPeriodo(periodo);
    }

    public void actualizaPeriodo(CtPeriodoEscolar periodo) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaPeriodo(periodo);
    }
    
    public void eliminaPeriodo(int id) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaPeriodo(id);
    }

    public void guardaArea(CtAreaalumno area) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaArea(area);
    }

    public void actualizaArea(CtAreaalumno area) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaArea(area);
    }

    public void eliminaArea(int id) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaArea(id);
    }

    public void guardaCpt(CtCptalumno cpt) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaCpt(cpt);
    }

    public void actualizaCpt(CtCptalumno cpt) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaCpt(cpt);
    }

    public void eliminaCpt(int id) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaCpt(id);
    }

    public void guardaGrado(CtGrado grado) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaGrado(grado);
    }

    public void guardaGrupo(CtGrupo grupo) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaGrupo(grupo);
    }

    public void guardaTipoCali(CtTipoCalificaicon tipocali) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaTipoCali(tipocali);
    }

    public void actualizaTipoCali(CtTipoCalificaicon tipocali) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaTipoCali(tipocali);
    }

    public void eliminaTipoCali(int id) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaTipoCali(id);
    }

    public List<CtDatosMateria> getMateriasFaltantes() {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getMateriasFaltantes();
    }

}
