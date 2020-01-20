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
import Modelos.TbAlumnos;
import Modelos.TbTutor;
import java.util.List;

/**
 *
 * @author Jonat
 */
public class AdministradorController {
    
    public void guardaTutor(TbTutor tutor){
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaTutor(tutor);
    }

    public void guardaAlumno(TbAlumnos alumno) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaAlumno(alumno);
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
}
