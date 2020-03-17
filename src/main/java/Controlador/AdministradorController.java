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
    
    public List<TbPersonal> getPersonal(int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getPersonal(tipoescuela);
    }
    
    public void guardaTutor(TbTutor tutor, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaTutor(tutor, tipoescuela);
    }
    
    public List<TbTutor> exportaTutor(String ruta, int tipoescuela) throws Exception {
        List<TbTutor> listtutor = new ArrayList<>();
        listtutor = readFromCSVT(ruta, tipoescuela);
        return listtutor;
    }
    
    public void guardaImportaTutor(List<TbTutor> listtutor) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaImportaTutor(listtutor);
    }
    
    public void guardaAlumno(TbAlumnos alumno, int tipoescuela, String ruta) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaAlumno(alumno, tipoescuela, ruta);
    }
    
    public List<TbAlumnos> exportaAlumnos(String ruta, int tipoescuela) throws Exception {
        List<TbAlumnos> alumnos = new ArrayList<>();
        alumnos = readFromCSVA(ruta, tipoescuela);
        return alumnos;
    }
    
    public void guardaImportaAlumnos(List<TbAlumnos> listalumnos) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaImportaAlumnos(listalumnos);
    }
    
    public List<TbPersonal> exportaPersonal(String ruta, int tipoescuela) throws Exception {
        List<TbPersonal> personal = new ArrayList<>();
        personal = readFromCSVP(ruta, tipoescuela);
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
    
    public void actualizaTutor(TbTutor tutor) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaTutor(tutor);
    }
    
    public void actualizaAlumno(TbAlumnos alumno, String ruta) throws Exception {
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
    
    public TbPersonal datosPeronal(int idpersonal) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.datosPersonal(idpersonal);
    }
    
    public void actualizaPersonal(TbPersonal personal) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaPersonal(personal);
    }
    
    public void eliminaPersonal(int id) throws Exception {
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
    
    public List<CtGrupo> getGrupo(int tipoescuela) throws Exception {
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
    
    public List<CtPuesto> getPuesto(int tipoescuela) throws Exception {
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
    
    public void guardaPuesto(CtPuesto puesto, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaPuesto(puesto, tipoescuela);
    }
    
    public void actualizaPuesto(CtPuesto puesto) {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.actualizaPuesto(puesto);
    }
    
    public void eliminaPuesto(int id) throws Exception {
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
    
    public void eliminaPeriodo(int id) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.eliminaPeriodo(id);
    }
    
    public void guardaArea(CtAreaalumno area, int tipoescuela) throws Exception {
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
    
    private List<TbAlumnos> readFromCSVA(String ruta, int tipoescuela) throws Exception {
        List<TbAlumnos> alumnos = new ArrayList<>();
        Path pathToFile = Paths.get(ruta);
        
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            
            while (line != null) {
                String[] attributes = line.split(",");
                TbAlumnos alumno = creaAlumno(attributes, tipoescuela);
                alumnos.add(alumno);
                line = br.readLine();
            }
            
        } catch (Exception e) {
            throw e;
        }
        return alumnos;
    }
    
    private TbAlumnos creaAlumno(String[] attributes, int tipoescuela) {
        TbAlumnos alumno = new TbAlumnos();
        alumno.setNombre(attributes[0]);
        alumno.setApellidop(attributes[1]);
        alumno.setApellidom(attributes[2]);
        alumno.setFechanacimiento(attributes[3]);
        alumno.setCurp(attributes[4]);
        alumno.setMunicipiona(attributes[5]);
        alumno.setEstadona(attributes[6]);
        alumno.setNacionalidad(attributes[7]);
        if (attributes[8] == "H") {
            alumno.setSexo(true);
        } else {
            alumno.setSexo(false);
        }
        alumno.setCalledom(attributes[9]);
        alumno.setNumerodom(Integer.parseInt(attributes[10]));
        alumno.setColoniadom(attributes[11]);
        alumno.setCodigopostal(Integer.parseInt(attributes[12]));
        alumno.setTelefonocasa(attributes[13]);
        alumno.setCelular(attributes[14]);
        alumno.setCorreo(attributes[15]);
        alumno.setNivelcursa(attributes[15]);
        if (tipoescuela == 2) {
            switch(attributes[16]){
                case "1": alumno.setRgrado(1); break;
                case "2": alumno.setRgrado(2); break;
                case "3": alumno.setRgrado(3); break;
            }
            switch(attributes[17]){
                case "A": alumno.setRgrupo(1); break;
            }
        }
        if (tipoescuela == 1) {
            switch(attributes[16]){
                case "1": alumno.setRgrado(4); break;
                case "2": alumno.setRgrado(5); break;
                case "3": alumno.setRgrado(6); break;
                case "4": alumno.setRgrado(7); break;
                case "5": alumno.setRgrado(8); break;
                case "6": alumno.setRgrado(9); break;
            }
            switch(attributes[17]){
                case "A": alumno.setRgrupo(2); break;
                case "B": alumno.setRgrupo(3); break;
                case "C": alumno.setRgrupo(4); break;
                case "D": alumno.setRgrupo(5); break;
            }
        }
        
        alumno.setArea(attributes[18]);
        alumno.setCpt(attributes[19]);
        alumno.setPlantelproce(attributes[20]);
        alumno.setNivelanterior(attributes[21]);
        alumno.setGradoanterior(Integer.parseInt(attributes[22]));
        if (attributes[23] == "Matutino" || attributes[23] == "matutino") {
            alumno.setTurnoanterior(1);
        } else {
            alumno.setTurnoanterior(2);
        }
        alumno.setMunicipioante(attributes[24]);
        alumno.setTipoescuela(tipoescuela);
        
        return alumno;
    }
    
    private List<TbPersonal> readFromCSVP(String ruta, int tipoescuela) throws Exception {
        List<TbPersonal> personal = new ArrayList<>();
        Path pathToFile = Paths.get(ruta);
        
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            
            while (line != null) {
                String[] attributes = line.split(",");
                TbPersonal persona = creaPersonal(attributes, tipoescuela);
                personal.add(persona);
                line = br.readLine();
            }
            
        } catch (Exception e) {
            throw e;
        }
        return personal;
    }
    
    private TbPersonal creaPersonal(String[] attributes, int tipoescuela) {
        TbPersonal personal = new TbPersonal();
        personal.setNombre(attributes[0]);
        personal.setApellidop(attributes[1]);
        personal.setApellidom(attributes[2]);
        personal.setFechanacimiento(attributes[3]);
        personal.setCurp(attributes[4]);
        personal.setMunicipionac(attributes[5]);
        personal.setEstadonac(attributes[6]);
        personal.setNacionalidad(attributes[7]);
        if (attributes[8] == "H") {
            personal.setSexo(true);
        } else {
            personal.setSexo(false);
        }
        personal.setCalledom(attributes[9]);
        personal.setNumerodom(Integer.parseInt(attributes[10]));
        personal.setColoniadom(attributes[11]);
        personal.setCodigopostal(Integer.parseInt(attributes[12]));
        personal.setTelefonocasa(attributes[13]);
        personal.setCelular(attributes[14]);
        personal.setCorreo(attributes[15]);
        personal.setNss(attributes[16]);
        personal.setRfc(attributes[17]);
        personal.setNivelestudios(attributes[18]);
        personal.setLicenciatura(attributes[19]);
        personal.setMaestria(attributes[20]);
        personal.setDoctorado(attributes[21]);
        if (attributes[22] == "Maestro") {
            personal.setRpuesto(1);
        }
        if (attributes[22] == "Prefecto") {
            personal.setRpuesto(2);
        }
        if (attributes[22] == "Administrativo") {
            personal.setRpuesto(3);
        }
        personal.setTipoescuela(tipoescuela);
        
        return personal;
    }
    
    private List<TbTutor> readFromCSVT(String ruta, int tipoescuela) throws Exception {
        List<TbTutor> listtutor = new ArrayList<>();
        Path pathToFile = Paths.get(ruta);
        
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            
            while (line != null) {
                String[] attributes = line.split(",");
                TbTutor tutor = creaTutor(attributes, tipoescuela);
                listtutor.add(tutor);
                line = br.readLine();
            }
            
        } catch (Exception e) {
            throw e;
        }
        return listtutor;
    }
    
    private TbTutor creaTutor(String[] attributes, int tipoescuela) {
        TbTutor tutor = new TbTutor();
        tutor.setNombre(attributes[0]);
        tutor.setApellidop(attributes[1]);
        tutor.setApellidom(attributes[2]);
        tutor.setOcupacion(attributes[3]);
        tutor.setParentesco(attributes[4]);
        tutor.setCalledom(attributes[5]);
        tutor.setNumerodom(Integer.parseInt(attributes[6]));
        tutor.setColoniadom(attributes[7]);
        tutor.setCodigopostal(Integer.parseInt(attributes[8]));
        tutor.setTelefonocasa(attributes[9]);
        tutor.setCelular(attributes[10]);
        tutor.setCorreo(attributes[11]);
        tutor.setNombre2(attributes[12]);
        tutor.setApellidop2(attributes[13]);
        tutor.setApellidom2(attributes[14]);
        tutor.setOcupacion2(attributes[15]);
        tutor.setParentesco2(attributes[16]);
        tutor.setCalledom2(attributes[17]);
        tutor.setNumerodom2(Integer.parseInt(attributes[18]));
        tutor.setColoniadom2(attributes[19]);
        tutor.setCodigopostal2(Integer.parseInt(attributes[20]));
        tutor.setTelefonocasa2(attributes[21]);
        tutor.setCelular2(attributes[22]);
        tutor.setCorreo2(attributes[23]);
        tutor.setNombre3(attributes[24]);
        tutor.setApellidop3(attributes[25]);
        tutor.setApellidom3(attributes[26]);
        tutor.setOcupacion3(attributes[27]);
        tutor.setParentesco3(attributes[28]);
        tutor.setCalledom3(attributes[29]);
        tutor.setNumerodom3(Integer.parseInt(attributes[30]));
        tutor.setColoniadom3(attributes[31]);
        tutor.setCodigopostal3(Integer.parseInt(attributes[32]));
        tutor.setTelefonocasa3(attributes[33]);
        tutor.setCelular3(attributes[34]);
        tutor.setCorreo3(attributes[35]);
        tutor.setTipoescuela(tipoescuela);
        return tutor;
    }
    
}
