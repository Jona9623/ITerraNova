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
import Modelos.TbReporteDisciplinar;
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

    public void guardaIncidente(CtIncidente incidente) {
        ConsultasAlumno consulta = new ConsultasAlumno();
        consulta.guardaIincidente(incidente);
    }

    public void guardaReporteD(TbReporteDisciplinar reporteD, String ruta, int status) {
        ConsultasAlumno consulta = new ConsultasAlumno();
        consulta.guardaReporteD(reporteD, ruta);
        if (status == 1) {
            TbReporteDisciplinar reporte = new TbReporteDisciplinar();
            reporte = consulta.datosReporteD(reporteD.getRalumno(), reporteD.getFecha(), reporteD.getRperiodo());
            String correotutor = reporte.getCorreotutor();
            String asunto = "Notificacion de Reporte Disciplinar";
            String cuerpo = "Estimado padre de familia, le informo que el dia " + reporte.getHora() + " su hijo " + reporte.getAlumno() + "" + reporte.getAlumnoapep() + "" + reporte.getAlumnoapem() + " "
                    + "le fue levantado un reporte de tipo " + reporte.getNivel() + " del estilo " + reporte.getTipoincidente() + "\n"
                    + "Le pedimos que platique con su hij@, y cualquier duda con mucho gusto contactar a la institución para poder platicar mas adelante\n"
                    + "\nNOTA: En caso de que la información se encuentre equivocada, favor de comunicarse a la institucuión ";
            enviarCorreo(correotutor, asunto, cuerpo);
        }else
            System.out.println("No entro");
    }

    public List<TbReporteDisciplinar> getAlumnosReporteD() {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.getAlumnosReporteD();
    }

    public TbReporteDisciplinar datosReporteD(int id, String fecha, int periodo) {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return (consulta.datosReporteD(id, fecha, periodo));
    }

    private static void enviarCorreo(String correotutor, String asunto, String cuerpo) {
        String usuario = "pruebas.iterranova@gmail.com";
        String contrasena = "excelencia";

        try {
            Properties pro = System.getProperties();
            /* Session sesion = Session.getDefaultInstance(pro);
            MimeMessage mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(usuario));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            mensaje.setSubject("ESTE ES EL ASUNTO");
            mensaje.setText("ESTE ES EL CUERPO DEL MENSAJE");
            Transport.send(mensaje);*/
            pro.put("mail.smtp.host", "smtp.gmail.com");
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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
