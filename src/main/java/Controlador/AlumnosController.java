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
import Modelos.CtAtencion;
import Modelos.CtSemanaFiscal;
import Modelos.ImagenReporteAcademico;
import Modelos.ImagenReporteAcademicoTarea;
import Modelos.TbReporteAcademico;
import Modelos.TbReporteDisciplinar;
import Modelos.TbTareaSemanal;
import com.sun.mail.smtp.SMTPTransport;
import java.util.List;
import java.util.Properties;
import java.awt.Font;
import java.util.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonat
 */
public class AlumnosController {

    public List<CtPeriodoEscolar> getPeriodos(int tipoescuela) throws Exception{
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.getPeriodos(tipoescuela);
    }

    public List<TbMateria> getMaterias(int tipoescuela) throws Exception {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.getMaterias(tipoescuela);
    }

    public List<CtIncidente> getIncidentes(int tipoescuela) throws Exception {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.getIncidentes(tipoescuela);
    }

    public List<Alumno> getAlumnos(int grado, int grupo, int tipoescuela) throws Exception {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.getAlumnos(grado, grupo, tipoescuela);
    }

    public void guardaIncidente(CtIncidente incidente, int tipoescuela) throws Exception {
        ConsultasAlumno consulta = new ConsultasAlumno();
        consulta.guardaIincidente(incidente, tipoescuela);
    }

    public void guardaReporteD(TbReporteDisciplinar reporteD, String ruta, int status, int tipoescuela) throws Exception{
        ConsultasAlumno consulta = new ConsultasAlumno();
        consulta.guardaReporteD(reporteD, ruta,tipoescuela);
        if (status == 1) {
            TbReporteDisciplinar reporte = new TbReporteDisciplinar();
            reporte = consulta.datosReporteD(reporteD.getRalumno(), reporteD.getFecha(), reporteD.getHora(),tipoescuela);
            String correotutor = reporte.getCorreotutor();
            String asunto = "Notificacion de Reporte Disciplinar";
            String cuerpo = "Estimado padre de familia, le informo que el dia " + reporte.getHora() + " su hijo " + reporte.getAlumno() + "" + reporte.getAlumnoapep() + "" + reporte.getAlumnoapem() + " "
                    + "le fue levantado un reporte de tipo " + reporte.getNivel() + " del estilo " + reporte.getTipoincidente() + "\n"
                    + "Le pedimos que platique con su hij@, y cualquier duda con mucho gusto contactar a la institución para poder platicar mas adelante\n"
                    + "\nNOTA: En caso de que la información se encuentre equivocada, favor de comunicarse a la institucuión ";
            enviarCorreo(correotutor, asunto, cuerpo);
        }
    }

    public List<TbReporteDisciplinar> getAlumnosReporteD(int tipoescuela) throws Exception {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.getAlumnosReporteD(tipoescuela);
    }

    public TbReporteDisciplinar datosReporteD(int id, String fecha, String hora, int tipoescuela) throws Exception {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return (consulta.datosReporteD(id, fecha, hora,tipoescuela));
    }
    
    public TbReporteDisciplinar editarReporteD(int id, String fecha, String hora) throws Exception {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.editarReporteD(id,fecha,hora);
    }

    private static void enviarCorreo(String correotutor, String asunto, String cuerpo) throws Exception {
        String usuario = "sistema@iterra.edu.mx";
        String contrasena = "guanabana2035";

        try {
            Properties pro = System.getProperties();
            pro.put("mail.smtp.host", "mail.iterra.edu.mx");
           // pro.put("mail.smtp.socketFactory.port", "587");
            pro.put("mail.smtp.port", "587");
            pro.put("mail.smtp.auth", "true");
            pro.put("mail.smtp.starttls.enable", "true");

            Session sesion = Session.getDefaultInstance(pro);

            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(usuario));
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(correotutor));
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);
            Transport t = sesion.getTransport("smtp");
            t.connect(usuario, contrasena);
            t.sendMessage(mensaje, mensaje.getRecipients(Message.RecipientType.TO));
            t.close();
            enviacorreoSistema(usuario,contrasena,asunto,cuerpo);

        } catch (Exception e) {
            throw e;
        }

    }
    
    private static void enviacorreoSistema(String usuario, String contrasena, String asunto, String cuerpo) throws Exception {
        try {
            Properties pro = System.getProperties();
            pro.put("mail.smtp.host", "mail.iterra.edu.mx");
            pro.put("mail.smtp.socketFactory.port", "587");
            pro.put("mail.smtp.port", "587");
            pro.put("mail.smtp.auth", "true");
            pro.put("mail.smtp.starttls.enable", "true");

            Session sesion = Session.getDefaultInstance(pro);

            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(usuario));
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(usuario));
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

    public void guardareditarReporteD(TbReporteDisciplinar reporteD) throws Exception {
        ConsultasAlumno consulta = new ConsultasAlumno();
        consulta.guardareditarReporteD(reporteD);
    }

    public List<CtSemanaFiscal> getSemanaiscal(int tipoescuela) throws Exception {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.getSemanaFiscal(tipoescuela);
    }

    public List<CtAtencion> getAtencion(int tipoescuela) throws Exception {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.getAtencion(tipoescuela);
    }

    public void guardaActividadSemanal(TbTareaSemanal tarea,int tipoescuela) throws Exception {
        ConsultasAlumno consulta = new ConsultasAlumno();
        consulta.guardaActividadSemanal(tarea,tipoescuela);
    }

    public void guardaReporteAcademico(TbReporteAcademico reporteA,int tipoescuela) throws Exception {
        ConsultasAlumno consulta = new ConsultasAlumno();
        consulta.guardaReporteAcademico(reporteA,tipoescuela);
    }

    public void guardaComportamiento(CtAtencion atencion, int tipoescuela) throws Exception {
        ConsultasAlumno consulta = new ConsultasAlumno();
        consulta.guardaComportamiento(atencion,tipoescuela);
    }

    public void guardaSemana(CtSemanaFiscal semana, int tipoescuela) throws Exception {
        ConsultasAlumno consulta = new ConsultasAlumno();
        consulta.guardaSemana(semana, tipoescuela);
    }

    public ImagenReporteAcademico datosGuardaImagen(int tipoescuela) throws Exception {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.datosGuardaImagen(tipoescuela);
    }
    
    public ImagenReporteAcademicoTarea datosGuardaImagenActividad(int tipoescuela) throws Exception{
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.datosGuardaImagenTarea(tipoescuela);
    }

    public TbReporteAcademico datosReporteA(int tipoescuelareporte)  throws Exception{
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.datosReporteA(tipoescuelareporte);
    }

    public TbTareaSemanal datosTareaSemanal(int tipoescuelareporte)throws Exception{
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.datosTareaSemanal(tipoescuelareporte);
    }

    public List<TbReporteAcademico> getAlumnosReporteA(int tipoescuela) throws Exception{
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.getAlumnosReporteA(tipoescuela);
    }

    public List<TbTareaSemanal> getTareas(int tipoescuela) throws Exception {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.getTareas(tipoescuela);
    }

    public void eliminarTarea(int id) throws Exception{
        ConsultasAlumno consulta = new ConsultasAlumno();
        consulta.eliminarTarea(id);
    }

    public void eliminarReporteA(int id) throws Exception{
        ConsultasAlumno consulta = new ConsultasAlumno();
        consulta.eliminarReporteA(id);
    }

    public void eliminarReporteD(int id) throws Exception{
        ConsultasAlumno consulta = new ConsultasAlumno();
        consulta.eliminarReporteD(id);
    }

    public void correoPersonalImagen(String correo, String url) throws Exception {
        String usuario = "sistema@iterra.edu.mx";
        String contrasena = "guanabana2035";
        String asunto = "URL Imagen Reporte Academico";
        String cuerpo = "La direccion a continuación debera guardarla y agregarla a la pagina de iterra.edu.mx para que posteriormente se anexe la imagen dentro"
                    + "del portal, el URL es el siguiente:\n";

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
            mensaje.setText(cuerpo + url);
            Transport t = sesion.getTransport("smtp");
            t.connect(usuario, contrasena);
            t.sendMessage(mensaje, mensaje.getRecipients(Message.RecipientType.TO));
            t.close();
            enviacorreoSistema(usuario, contrasena, asunto, cuerpo);

        } catch (Exception e) {
            throw e;
        }
        
    }

    public void correoPersonalImagenTarea(String correo, String url) throws Exception {
        String usuario = "sistema@iterra.edu.mx";
        String contrasena = "guanabana2035";
        String asunto = "URL Imagen Tarea Semanal";
        String cuerpo = "La direccion a continuación debera guardarla y agregarla a la pagina de iterra.edu.mx para que posteriormente se anexe la imagen dentro"
                    + "del portal, el URL es el siguiente:\n";

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
            mensaje.setText(cuerpo + url);
            Transport t = sesion.getTransport("smtp");
            t.connect(usuario, contrasena);
            t.sendMessage(mensaje, mensaje.getRecipients(Message.RecipientType.TO));
            t.close();
            enviacorreoSistema(usuario, contrasena, asunto, cuerpo);

        } catch (Exception e) {
            throw e;
        }
    }
    
}
