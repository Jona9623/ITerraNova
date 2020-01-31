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
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
        String destino = "jonathan9623@hotmail.es";
        String asunto = "Reporte Disciplinar";
        String cuerpo = "Problema del reporte mas otras cosas";
        enviarCorreo(destino, asunto, cuerpo);
    }

    public List<TbReporteDisciplinar> getAlumnosReporteD() {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return consulta.getAlumnosReporteD();
    }

    public TbReporteDisciplinar datosReporteD(int id, String fecha, int periodo) {
        ConsultasAlumno consulta = new ConsultasAlumno();
        return (consulta.datosReporteD(id, fecha, periodo));
    }

    private static void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        String remitente = "pruebas.iterranova@gmail.com";
        String contrasena = "excelencia";
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", contrasena);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");

        try {
            Session sesion = Session.getDefaultInstance(props);
            MimeMessage mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.addRecipients(Message.RecipientType.TO, destinatario);
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);
            
            Transport.send(mensaje);
           /* Transport transport = sesion.getTransport("smtp");
            transport.connect(remitente, contrasena);
            transport.sendMessage(mensaje, mensaje.getAllRecipients());
            transport.close();*/
            JOptionPane.showMessageDialog(null, "Correo enviado");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
