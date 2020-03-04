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
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonat
 */
public class AdministradorController {

    public List<TbAlumnos> getAlumnos(int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getAlumnos(tipoescuela);
    }

    public List<TbPersonal> getPersonal(int tipoescuela) throws Exception{
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getPersonal(tipoescuela);
    }

    public void guardaTutor(TbTutor tutor, int tipoescuela) throws Exception{
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaTutor(tutor, tipoescuela);
    }

    public List<TbTutor> exportaTutor(String ruta) throws Exception {
        List<TbTutor> listtutor = new ArrayList<>();
        listtutor = readFromCSVT(ruta);
        return listtutor;
    }

    public void guardaImportaTutor(List<TbTutor> listtutor) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaImportaTutor(listtutor);
    }

    public void guardaAlumno(TbAlumnos alumno, int tipoescuela, String ruta) throws Exception{
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaAlumno(alumno, tipoescuela, ruta);
    }

    public List<TbAlumnos> exportaAlumnos(String ruta) throws Exception {
        List<TbAlumnos> alumnos = new ArrayList<>();
        alumnos = readFromCSVA(ruta);
        return alumnos;
    }

    public void guardaImportaAlumnos(List<TbAlumnos> listalumnos) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaImportaAlumnos(listalumnos);
    }

    public List<TbPersonal> exportaPersonal(String ruta) throws Exception {
        List<TbPersonal> personal = new ArrayList<>();
        personal = readFromCSVP(ruta);
        return personal;
    }

    public void guardaImportaPersonal(List<TbPersonal> listpersonal) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaImportaPersonal(listpersonal);
    }

    public TbAlumnos datosAlumno(int idalumno) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.datosAlumno(idalumno);
    }

    public void actualizaTutor(TbTutor tutor) throws Exception{
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaTutor(tutor);
    }

    public void actualizaAlumno(TbAlumnos alumno, String ruta) throws Exception{
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaAlumno(alumno, ruta);
    }

    public void eliminaAlumno(int id) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaAlumno(id);
    }

    public TbTutor datosTutor(int idtutor) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.datosTutor(idtutor);
    }

    public TbPersonal datosPeronal(int idpersonal) throws Exception{
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.datosPersonal(idpersonal);
    }

    public void actualizaPersonal(TbPersonal personal) throws Exception{
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaPersonal(personal);
    }

    public void eliminaPersonal(int id) throws Exception{
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaPersonal(id);
    }

    public void guardaPersonal(TbPersonal personal, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaPersonal(personal, tipoescuela);
    }

    public List<CtGrado> getGrado(int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getGrado(tipoescuela);
    }

    public List<CtGrupo> getGrupo(int tipoescuela)throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getGrupo(tipoescuela);
    }

    public List<CtAreaalumno> getArea(int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getArea(tipoescuela);
    }

    public List<CtCptalumno> getCpt(int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
            return consulta.getCpt(tipoescuela);
    }

    public List<CtPuesto> getPuesto(int tipoescuela) throws Exception{
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getPuesto(tipoescuela);
    }

    public List<GradoGrupo> getGradoGrupo(int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getGradoGrupo(tipoescuela);
    }

    public List<CtTipoCalificaicon> getTipoCali(int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getTipoCali(tipoescuela);
    }

    public void guardaPuesto(CtPuesto puesto, int tipoescuela) throws Exception{
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaPuesto(puesto, tipoescuela);
    }

    public void actualizaPuesto(CtPuesto puesto) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaPuesto(puesto);
    }

    public void eliminaPuesto(int id) throws Exception{
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaPuesto(id);
    }

    public void guardaPeriodo(CtPeriodoEscolar periodo, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaPeriodo(periodo, tipoescuela);
    }

    public void actualizaPeriodo(CtPeriodoEscolar periodo) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaPeriodo(periodo);
    }

    public void eliminaPeriodo(int id) throws Exception{
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaPeriodo(id);
    }

    public void guardaArea(CtAreaalumno area, int tipoescuela) throws Exception{
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaArea(area, tipoescuela);
    }

    public void actualizaArea(CtAreaalumno area) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaArea(area);
    }

    public void eliminaArea(int id) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaArea(id);
    }

    public void guardaCpt(CtCptalumno cpt, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
            consulta.guardaCpt(cpt, tipoescuela);
    }

    public void actualizaCpt(CtCptalumno cpt) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaCpt(cpt);
    }

    public void eliminaCpt(int id) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
            consulta.eliminaCpt(id);
    }

    public void guardaGrado(CtGrado grado, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
            consulta.guardaGrado(grado, tipoescuela);
    }

    public void guardaGrupo(CtGrupo grupo, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
            consulta.guardaGrupo(grupo, tipoescuela);
    }

    public void guardaTipoCali(CtTipoCalificaicon tipocali, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaTipoCali(tipocali, tipoescuela);
    }

    public void actualizaTipoCali(CtTipoCalificaicon tipocali) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaTipoCali(tipocali);
    }

    public void eliminaTipoCali(int id) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaTipoCali(id);
    }

    public List<CtDatosMateria> getMateriasFaltantes(int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getMateriasFaltantes(tipoescuela);
    }

    public void guardaNombreMateria(CtDatosMateria nombremateria, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaNombreMateria(nombremateria, tipoescuela);
    }

    public void guardaMateria(TbMateria materia, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaMateria(materia, tipoescuela);
    }

    public void eliminaMateria(int id) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaMateria(id);
    }

    private List<TbAlumnos> readFromCSVA(String ruta) throws Exception {
        List<TbAlumnos> alumnos = new ArrayList<>();
        Path pathToFile = Paths.get(ruta);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");
                TbAlumnos alumno = creaAlumno(attributes);
                alumnos.add(alumno);
                line = br.readLine();
            }

        } catch (Exception e) {
            throw e;
        }
        return alumnos;
    }

    private TbAlumnos creaAlumno(String[] attributes) {
        TbAlumnos alumno = new TbAlumnos();
        alumno.setNombre(attributes[0]);
        alumno.setApellidop(attributes[1]);
        alumno.setApellidom(attributes[2]);
        alumno.setFechanacimiento(attributes[3]);
        alumno.setCurp(attributes[4]);

        return alumno;
    }

    private List<TbPersonal> readFromCSVP(String ruta) throws Exception {
        List<TbPersonal> personal = new ArrayList<>();
        Path pathToFile = Paths.get(ruta);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");
                TbPersonal persona = creaPersonal(attributes);
                personal.add(persona);
                line = br.readLine();
            }

        } catch (Exception e) {
            throw e;
        }
        return personal;
    }

    private TbPersonal creaPersonal(String[] attributes) {
        TbPersonal personal = new TbPersonal();
        personal.setNombre(attributes[0]);
        personal.setApellidop(attributes[1]);
        personal.setApellidom(attributes[2]);
        personal.setCurp(attributes[3]);
        personal.setFechanacimiento(attributes[4]);

        return personal;
    }

    private List<TbTutor> readFromCSVT(String ruta) throws Exception {
        List<TbTutor> listtutor = new ArrayList<>();
        Path pathToFile = Paths.get(ruta);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");
                TbTutor tutor = creaTutor(attributes);
                listtutor.add(tutor);
                line = br.readLine();
            }

        } catch (Exception e) {
            throw e;
        }
        return listtutor;
    }

    private TbTutor creaTutor(String[] attributes) {
        TbTutor tutor = new TbTutor();
        tutor.setNombre(attributes[0]);
        tutor.setApellidop(attributes[1]);
        tutor.setApellidom(attributes[2]);
        tutor.setOcupacion(attributes[3]);
        tutor.setParentesco(attributes[4]);
        return tutor;
    }

}
