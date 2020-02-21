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
    
    public List<TbAlumnos> getAlumnos(int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getAlumnos(tipoescuela);
    }
    
    public List<TbPersonal> getPersonal(int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getPersonal(tipoescuela);
    }
    public void guardaTutor(TbTutor tutor, int tipoescuela){
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaTutor(tutor, tipoescuela);
    }
    
    public void guardaAlumno(TbAlumnos alumno, int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaAlumno(alumno, tipoescuela);
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
    
    public void guardaPersonal(TbPersonal personal, int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaPersonal(personal, tipoescuela);
    }

    public List<CtGrado> getGrado(int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getGrado(tipoescuela);
    }

    public List<CtGrupo> getGrupo(int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getGrupo(tipoescuela);
    }

    public List<CtAreaalumno> getArea(int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getArea(tipoescuela);
    }

    public List<CtCptalumno> getCpt(int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getCpt(tipoescuela);
    }

    public List<CtPuesto> getPuesto(int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getPuesto(tipoescuela);
    }
    
    public List<GradoGrupo> getGradoGrupo(int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getGradoGrupo(tipoescuela);
    }

    public List<CtTipoCalificaicon> getTipoCali(int tipoescuela) {
       ConsultasAdministrador consulta = new ConsultasAdministrador();
       return consulta.getTipoCali(tipoescuela);
    }

    public void guardaPuesto(CtPuesto puesto, int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaPuesto(puesto,tipoescuela);
    }

    public void actualizaPuesto(CtPuesto puesto) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaPuesto(puesto);
    }

    public void eliminaPuesto(int id) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaPuesto(id);
    }

    public void guardaPeriodo(CtPeriodoEscolar periodo, int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaPeriodo(periodo, tipoescuela);
    }

    public void actualizaPeriodo(CtPeriodoEscolar periodo) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaPeriodo(periodo);
    }
    
    public void eliminaPeriodo(int id) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaPeriodo(id);
    }

    public void guardaArea(CtAreaalumno area, int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaArea(area,tipoescuela);
    }

    public void actualizaArea(CtAreaalumno area) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaArea(area);
    }

    public void eliminaArea(int id) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaArea(id);
    }

    public void guardaCpt(CtCptalumno cpt,int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaCpt(cpt, tipoescuela);
    }

    public void actualizaCpt(CtCptalumno cpt) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaCpt(cpt);
    }

    public void eliminaCpt(int id) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaCpt(id);
    }

    public void guardaGrado(CtGrado grado, int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaGrado(grado, tipoescuela);
    }

    public void guardaGrupo(CtGrupo grupo, int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaGrupo(grupo, tipoescuela);
    }

    public void guardaTipoCali(CtTipoCalificaicon tipocali, int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaTipoCali(tipocali, tipoescuela);
    }

    public void actualizaTipoCali(CtTipoCalificaicon tipocali) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaTipoCali(tipocali);
    }

    public void eliminaTipoCali(int id) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaTipoCali(id);
    }

    public List<CtDatosMateria> getMateriasFaltantes(int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getMateriasFaltantes(tipoescuela);
    }

    public void guardaNombreMateria(CtDatosMateria nombremateria, int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaNombreMateria(nombremateria,tipoescuela);
    }

    public void guardaMateria(TbMateria materia, int tipoescuela) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaMateria(materia, tipoescuela);
    }

    public void eliminaMateria(int id) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaMateria(id);
    }

}
