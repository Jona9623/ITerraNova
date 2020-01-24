/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import BD.ConsultasAlumno;
import Modelos.CtIncidente;
import Modelos.CtPeriodoEscolar;
import Modelos.TbAlumnos;
import Modelos.TbMateria;
import Modelos.Alumno;
import java.util.List;

/**
 *
 * @author Jonat
 */
public class AlumnosController {

    public List<CtPeriodoEscolar> getPeriodos() {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.getPeriodos();
    }

    public List<TbMateria> getMaterias() {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.getMaterias();
    }

    public List<CtIncidente> getIncidentes() {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.getIncidentes();
    }

    public List<Alumno> getAlumnos(int grado, int grupo) {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.getAlumnos(grado, grupo);
    }

    
}
