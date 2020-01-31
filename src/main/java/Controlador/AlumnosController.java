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
import java.util.List;
import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Authenticator;
import javax.mail.Message;
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

    public void guardaReporteD(TbReporteDisciplinar reporteD) {
        ConsultasAlumno consulta = new ConsultasAlumno();
        consulta.guardaReporteD(reporteD);
        TbReporteDisciplinar reporte = new TbReporteDisciplinar();
        reporte = consulta.datosReporteD(reporteD.getRalumno(), reporteD.getFecha(), reporteD.getRperiodo());
        /*String destino =  "jonathan9623@hotmail.es";
         String asunto = "Reporte Disciplinar";
         String cuerpo ="Problema del reporte mas otras cosas";*/
        enviarCorreo();
    }

    public List<TbReporteDisciplinar> getAlumnosReporteD() {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.getAlumnosReporteD();
    }

    public TbReporteDisciplinar datosReporteD(int id, String fecha, int periodo) {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return (consulta.datosReporteD(id, fecha, periodo));
    }

    private static void enviarCorreo() {
        final String usuario = "pruebas.iterranova@gmail.com";
        final String contrasena = "excelencia";

        try {
            Properties pro = System.getProperties();
            pro.put("mail.smtp.host", "smtp.gmail.com");
            pro.put("mail.smtp.port", "587");
            pro.put("mail.smtp.auth", "true");
            pro.put("mail.smtp.starttls.enable", "true");

            Session sesion = Session.getInstance(pro, new javax.mail.Authenticator() {
                protected PasswordAuthentication getpPasswordAuthentication() {
                    return new PasswordAuthentication(usuario, contrasena);
                }
            });
            
            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress("jonathan9623@hotmail.es"));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse("pruebas.iterranova@gmail.com, jonathan9623@hotmail.es"));
            mensaje.setSubject("TEXTO UNO");
            mensaje.setText("TEXTO 2");
            Transport.send(mensaje);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
