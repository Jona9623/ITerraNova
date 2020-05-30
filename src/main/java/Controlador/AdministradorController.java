/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import BD.ConsultasAdministrador;
import BD.ConsultasAlumno;
import Modelos.Alumno;
import Modelos.CtAreaalumno;
import Modelos.CtCptalumno;
import Modelos.CtDatosMateria;
import Modelos.CtDia;
import Modelos.CtGrado;
import Modelos.CtGrupo;
import Modelos.CtPeriodoEscolar;
import Modelos.CtPuesto;
import Modelos.CtTipoCalificaicon;
import Modelos.GradoGrupo;
import Modelos.TbAlumnos;
import Modelos.TbAsistencia;
import Modelos.TbHorario;
import Modelos.TbMateria;
import Modelos.TbMateriaAlumno;
import Modelos.TbMateriaPersonal;
import Modelos.TbPersonal;
import Modelos.TbTutor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author Jonat
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30, maxRequestSize = 1024 * 1024 * 50)
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

    public void guardaImportaPersonal(List<TbPersonal> listpersonal) throws Exception {
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

    public List<CtDia> getDias(int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getDias(tipoescuela);
    }

    public void asignaMateria(int id, List<TbMateriaPersonal> materiapersonal, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.asignaMateria(id, materiapersonal, tipoescuela);
    }

    public List<TbMateriaPersonal> getMateriasAPersonal(int tipoescuela, int idpersonal) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getMateriasAPersonal(tipoescuela, idpersonal);
    }

    public void asignaAlumnos(int tipoescuela, List<TbMateriaAlumno> listmateriaalum) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.asignaAlumnos(tipoescuela, listmateriaalum);
    }

    public List<TbMateriaAlumno> getMateriasAlum(int idalumno, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getMateriasAlum(idalumno, tipoescuela);
    }

    public void asignaHorario(TbHorario horario, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.asignaHorario(horario, tipoescuela);
    }

    public void guardaAsistencia(List<TbAsistencia> listasistencia, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        consulta.guardaAsistencia(listasistencia, tipoescuela);
    }

    public List<TbAsistencia> getreporteGAsistencia(int idperiodo, int idsemana, int idgrado, int idgrupo, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getreporteGAsistencia(idperiodo, idsemana, idgrado, idgrupo, tipoescuela);
    }

    public List<CtDatosMateria> getMateriasAsistencia(int idgrado, int idgrupo, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getMateriasAsistencia(idgrado, idgrupo, tipoescuela);
    }

    public List<Alumno> getAlumnosAsistencia(int idgrado, int idgrupo, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getAlumnosAsistencia(idgrado, idgrupo, tipoescuela);
    }
    
    public List<CtDatosMateria> justificarFaltas(int idalumno, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.justificarFaltas(idalumno,tipoescuela);
    }
    
    public List<CtDia> getDiasFaltas(int idalumno, int idperiodo, int idsemana, int idmateria, int tipoescuela) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        return consulta.getDiasFaltas(idalumno,idperiodo,idsemana,idmateria,tipoescuela);
    }

    private List<TbAlumnos> readFromCSVA(String ruta, int tipoescuela) throws Exception {
        List<TbAlumnos> alumnos = new ArrayList<>();
        Path pathToFile = Paths.get(ruta);
        String line = null;
        int con = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                TbAlumnos alumno = creaAlumno(attributes, tipoescuela);
                alumnos.add(alumno);
                con++;
            }

        } catch (Exception e) {
            System.out.println("Este es el error........" + con);
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
        if (attributes[8].equals("H")) {
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
        alumno.setNivelcursa(attributes[16]);
        if (attributes[27].equals("Secundaria")) {
            System.out.println("entra");
            switch (attributes[17]) {
                case "1":
                    alumno.setRgrado(1);
                    break;
                case "2":
                    alumno.setRgrado(2);
                    break;
                case "3":
                    alumno.setRgrado(3);
                    break;
            }
            switch (attributes[18]) {
                case "A":
                    alumno.setRgrupo(1);
                    break;
            }
        }
        if (attributes[27].equals("Preparatoria")) {
            System.out.println("entra");
            switch (attributes[17]) {
                case "1":
                    alumno.setRgrado(4);
                    break;
                case "2":
                    alumno.setRgrado(5);
                    break;
                case "3":
                    alumno.setRgrado(6);
                    break;
                case "4":
                    alumno.setRgrado(7);
                    break;
                case "5":
                    alumno.setRgrado(8);
                    break;
                case "6":
                    alumno.setRgrado(9);
                    break;
            }
            switch (attributes[18]) {
                case "A":
                    alumno.setRgrupo(2);
                    break;
                case "B":
                    alumno.setRgrupo(3);
                    break;
                case "C":
                    alumno.setRgrupo(4);
                    break;
                case "D":
                    alumno.setRgrupo(5);
                    break;
            }
        }

        alumno.setArea(attributes[19]);
        alumno.setCpt(attributes[20]);
        alumno.setPlantelproce(attributes[21]);
        alumno.setNivelanterior(attributes[22]);
        alumno.setGradoanterior(Integer.parseInt(attributes[23]));
        if (attributes[24].equals("Matutino")) {
            alumno.setTurnoanterior(1);
        } else {
            alumno.setTurnoanterior(2);
        }
        alumno.setMunicipioante(attributes[25]);
        if (attributes[27].equals("Secundaria")) {
            alumno.setTipoescuela(2);
        } else {
            alumno.setTipoescuela(1);
        }

        return alumno;
    }

    private List<TbPersonal> readFromCSVP(String ruta, int tipoescuela) throws Exception {
        List<TbPersonal> personal = new ArrayList<>();
        Path pathToFile = Paths.get(ruta);
        String line = null;
        //BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                TbPersonal persona = creaPersonal(attributes, tipoescuela);
                personal.add(persona);
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
        if (attributes[8].equals("H")) {
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
        if (attributes[23] == "Secundaria") {
            if (attributes[22].equals("Director")) {
                personal.setRpuesto(1);
            }
            if (attributes[22].equals("Subdirector")) {
                personal.setRpuesto(2);
            }
            if (attributes[22].equals("Docente")) {
                personal.setRpuesto(3);
            }
            if (attributes[22].equals("Administrativo")) {
                personal.setRpuesto(4);
            }
            if (attributes[22].equals("Prefectura")) {
                personal.setRpuesto(5);
            }
        } else {
            if (attributes[22].equals("Director")) {
                personal.setRpuesto(6);
            }
            if (attributes[22].equals("Subdirector")) {
                personal.setRpuesto(7);
            }
            if (attributes[22].equals("Docente")) {
                personal.setRpuesto(8);
            }
            if (attributes[22].equals("Administrativo")) {
                personal.setRpuesto(9);
            }
            if (attributes[22].equals("Prefectura")) {
                personal.setRpuesto(10);
            }
        }
        if (attributes[23].equals("Secundaria")) {
            personal.setTipoescuela(2);
        } else {
            personal.setTipoescuela(1);
        }

        return personal;
    }

    private List<TbTutor> readFromCSVT(String ruta, int tipoescuela) throws Exception {
        List<TbTutor> listtutor = new ArrayList<>();
        Path pathToFile = Paths.get(ruta);
        String line = null;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                TbTutor tutor = creaTutor(attributes, tipoescuela);
                listtutor.add(tutor);
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
        if (attributes[36].equals("Secundaria")) {
            tutor.setTipoescuela(1);
        } else {
            tutor.setTipoescuela(2);
        }
        return tutor;
    }

    public void correoUsuario(String usuariocorreo, String contrasenacorreo, int id) throws Exception {
        ConsultasAdministrador consulta = new ConsultasAdministrador();
        String correo = consulta.correoUsuario(id);
        enviaCorreoUsuario(usuariocorreo, contrasenacorreo, correo);
    }

    private void enviaCorreoUsuario(String usuariocorreo, String contrasenacorreo, String correo) throws Exception {
        String usuario = "sistema@iterra.edu.mx";
        String contrasena = "guanabana2035";
        String asunto = "Datos Inicio de sesion al sistema";
        String cuerpo = "Usted ha sido registrado con éxito, por favor inicie sesión dentro del sistema www.sistema.iterra.edu.mx con los siguientes datos\n\n"
                + "usuario: " + usuariocorreo + "\ncontraseña: " + contrasenacorreo;

        try {
            Properties pro = System.getProperties();
            pro.put("mail.smtp.host", "mail.iterra.edu.mx");
            pro.put("mail.smtp.port", "587");
            pro.put("mail.smtp.auth", "true");
            pro.put("mail.smtp.starttls.enable", "true");

            Session sesion = Session.getDefaultInstance(pro);

            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(usuario));
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);
            Transport t = sesion.getTransport("smtp");
            t.connect(usuario, contrasena);
            t.sendMessage(mensaje, mensaje.getRecipients(Message.RecipientType.TO));
            t.close();

        } catch (Exception e) {
            throw e;
        }
    }
 

}
