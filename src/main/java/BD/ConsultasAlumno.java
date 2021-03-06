/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelos.Alumno;
import Modelos.CtAtencion;
import Modelos.CtIncidente;
import Modelos.CtPeriodoEscolar;
import Modelos.CtPuesto;
import Modelos.CtSemanaFiscal;
import Modelos.ImagenReporteAcademico;
import Modelos.ImagenReporteAcademicoTarea;
import Modelos.TbAlumnos;
import Modelos.TbAsistencia;
import Modelos.TbHorario;
import Modelos.TbMateria;
import Modelos.TbReporteAcademico;
import Modelos.TbReporteDisciplinar;
import Modelos.TbTareaSemanal;
import Modelos.TbTutor;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author complx
 */
public class ConsultasAlumno {

    private Connection con;
    private int x = 1;

    /*Consulta para obtener lista de periodos*/
    public List<CtPeriodoEscolar> getPeriodos(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtPeriodoEscolar> listperiodo = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_periodoescolar where status = 1 and tipoescuela = ? order by idCt_PeriodoEscolar desc";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                CtPeriodoEscolar periodo = new CtPeriodoEscolar();
                periodo.setIdtbperiodo(rs.getInt("idCt_PeriodoEscolar"));
                periodo.setNombre(rs.getString("nombre"));
                periodo.setStatus(rs.getInt("status"));
                periodo.setTipoescuela(rs.getInt("tipoescuela"));
                listperiodo.add(periodo);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return listperiodo;
    }

    /*Consulta para obtener lista de materias*/
    public List<TbMateria> getMaterias(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TbMateria> listmateria = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from tb_materia inner join ct_datosmateria on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria\n"
                    + "left join ct_grado on tb_materia.r_grado = ct_grado.idCt_Grado\n"
                    + "left join ct_grupo on tb_materia.r_grupo = ct_grupo.idCt_Grupo \n"
                    + "left join ct_areaalumno on tb_materia.r_area = ct_areaalumno.idCt_AreaAlumno\n"
                    + "left join ct_cptalumno on tb_materia.r_cpt = ct_cptalumno.idCt_CptAlumno\n"
                    + "where tb_materia.status = 1 and tb_materia.tipoescuela = ?;";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                TbMateria materia = new TbMateria();
                materia.setIdtbmateria(rs.getInt("tb_materia.idTb_Materia"));
                materia.setNombrelargo(rs.getString("ct_datosmateria.nombrelargo"));
                materia.setNombrecorto(rs.getString("ct_datosmateria.nombrecorto"));
                materia.setStatus(rs.getInt("tb_materia.status"));
                materia.setTipoescuela(rs.getInt("tb_materia.tipoescuela"));
                materia.setGrado(rs.getString("ct_grado.nombre"));
                materia.setGrupo(rs.getString("ct_grupo.nombre"));
                materia.setArea(rs.getString("ct_areaalumno.nombre"));
                materia.setCpt(rs.getString("ct_cptalumno.nombre"));
                listmateria.add(materia);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return listmateria;
    }

    /*Consulta para obtener materias de un profesor*/
    public List<TbMateria> getMateriasPersonal(int tipoescuela, int idpersonal) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TbMateria> listmateria = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "SELECT * FROM tb_materia inner join ct_datosmateria on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria\n"
                    + "                    where tb_materia.tipoescuela = ? and idTb_Materia not in (\n"
                    + "                    select r_materia from tb_materiapersonal where tb_materiapersonal.r_personal = ? and tb_materia.status = 1\n"
                    + "                    );";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            pst.setInt(2, idpersonal);
            rs = pst.executeQuery();
            while (rs.next()) {
                TbMateria materia = new TbMateria();
                materia.setIdtbmateria(rs.getInt("tb_materia.idTb_Materia"));
                materia.setNombrelargo(rs.getString("ct_datosmateria.nombrelargo"));
                materia.setNombrecorto(rs.getString("ct_datosmateria.nombrecorto"));
                materia.setStatus(rs.getInt("tb_materia.status"));
                materia.setTipoescuela(rs.getInt("tb_materia.tipoescuela"));
                listmateria.add(materia);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return listmateria;
    }

    /*Consulta para obtener lista de incidentes*/
    public List<CtIncidente> getIncidentes(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtIncidente> listincidente = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_incidente where status = 1 and tipoescuela = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                CtIncidente incidente = new CtIncidente();
                incidente.setIdtbincidente(rs.getInt("idCt_incidente"));
                incidente.setNombre(rs.getString("nombre"));
                incidente.setStatus(rs.getInt("status"));
                incidente.setTipoescuela(rs.getInt("tipoescuela"));
                listincidente.add(incidente);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return listincidente;
    }

    /*Consulta para obtener alumnos para cuadro de honor*/
    public List<Alumno> getAlumnos(int grado, int grupo, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Alumno> listalumno = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from tb_alumnos where r_grado = ? and r_grupo = ? and tipoescuela = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, grado);
            pst.setInt(2, grupo);
            pst.setInt(3, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setId(rs.getInt("idTb_Alumnos"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellidop(rs.getString("apellidopaterno"));
                alumno.setApellidom(rs.getString("apellidomaterno"));
                listalumno.add(alumno);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return listalumno;
    }

    /*Consulta para obtener alumnos para cuadro de atencion*/
    public List<Alumno> getAlumnosAtencion(int grado, int grupo, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Alumno> listalumno = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from tb_alumnos where r_grado = ? and r_grupo = ? and tipoescuela = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, grado);
            pst.setInt(2, grupo);
            pst.setInt(3, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setId(rs.getInt("idTb_Alumnos"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellidop(rs.getString("apellidopaterno"));
                alumno.setApellidom(rs.getString("apellidomaterno"));
                listalumno.add(alumno);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return listalumno;
    }

    /*Consulta para guardar incidente*/
    public void guardaIincidente(CtIncidente incidente, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into ct_incidente (nombre,status,tipoescuela) values (?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, incidente.getNombre());
            pst.setInt(2, 1);
            pst.setInt(3, tipoescuela);

            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al guardar");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /*Consulta para guardar reporte disciplinar*/
    public void guardaReporteD(TbReporteDisciplinar reporteD, String ruta, int tipoescuela) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into tb_reportedisciplinar (r_alumno,r_personal,hora,fecha,fechareporte,r_personalsolicita,r_personalllena,r_materia,lugar,r_tipoincidente,"
                    + "r_periodo,nivel,descripcion,foto,status,tipoescuela) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, reporteD.getRalumno());
            if (reporteD.getRpersonal() != 0) {
                pst.setInt(2, reporteD.getRpersonal());
            } else {
                pst.setString(2, null);
            }
            pst.setString(3, reporteD.getHora());
            pst.setString(4, reporteD.getFecha());
            pst.setString(5, reporteD.getFechareporte());
            pst.setInt(6, reporteD.getRpersonalsolicita());
            pst.setInt(7, reporteD.getRpersonalllena());
            if (reporteD.getRmateria() != 0) {
                pst.setInt(8, reporteD.getRmateria());
            } else {
                pst.setString(8, null);
            }
            pst.setString(9, reporteD.getLugar());
            pst.setInt(10, reporteD.getRtipoincidente());
            pst.setInt(11, reporteD.getRperiodo());
            pst.setInt(12, reporteD.getNivel());
            pst.setString(13, reporteD.getDescripcion());
            pst.setString(14, ruta);
            pst.setInt(15, 1);
            pst.setInt(16, tipoescuela);

            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al guardar");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /*Consulta para traer alumnos con reportes disciplinares*/
    public List<TbReporteDisciplinar> getAlumnosReporteD(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TbReporteDisciplinar> alumnosdisciplinar = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = " select * from tb_reportedisciplinar left join tb_personal on tb_reportedisciplinar.r_personal = tb_personal.idTb_Personal \n"
                    + " left join tb_materia on tb_reportedisciplinar.r_materia = tb_materia.idTb_Materia\n"
                    + " join tb_alumnos on tb_reportedisciplinar.r_alumno = tb_alumnos.idTb_Alumnos\n"
                    + " join ct_incidente on tb_reportedisciplinar.r_tipoincidente = ct_incidente.idCt_incidente\n"
                    + " join ct_periodoescolar on tb_reportedisciplinar.r_periodo = ct_periodoescolar.idCt_PeriodoEscolar\n"
                    + " left join ct_datosmateria on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria\n"
                    + " join ct_grado on tb_alumnos.r_grado = ct_grado.idCt_Grado\n"
                    + " join ct_grupo on tb_alumnos.r_grupo = ct_grupo.idCt_Grupo\n"
                    + " join tb_personal as tb1 on tb_reportedisciplinar.r_personalllena = tb1.idTb_Personal\n"
                    + " join tb_personal as tb2 on tb_reportedisciplinar.r_personalsolicita = tb2.idTb_Personal\n"
                    + " where tb_reportedisciplinar.status = 1 and tb_reportedisciplinar.tipoescuela = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                TbReporteDisciplinar reporteD = new TbReporteDisciplinar();
                reporteD.setIdtbreporte(rs.getInt("idTb_ReporteDisciplinar"));
                reporteD.setRalumno(rs.getInt("tb_reportedisciplinar.r_alumno"));
                reporteD.setAlumno(rs.getString("tb_alumnos.nombre"));
                reporteD.setAlumnoapep(rs.getString("tb_alumnos.apellidopaterno"));
                reporteD.setAlumnoapem(rs.getString("tb_alumnos.apellidomaterno"));
                reporteD.setGrado(rs.getString("ct_grado.nombre"));
                reporteD.setGrupo(rs.getString("ct_grupo.nombre"));
                reporteD.setPersonal(rs.getString("tb_personal.nombre"));
                reporteD.setPersonalllena(rs.getString("tb1.nombre"));
                reporteD.setPersonalsolicita(rs.getString("tb2.nombre"));
                reporteD.setHora(rs.getString("tb_reportedisciplinar.hora"));
                reporteD.setFecha(rs.getString("tb_reportedisciplinar.fecha"));
                reporteD.setFechareporte(rs.getString("tb_reportedisciplinar.fechareporte"));
                reporteD.setMateria(rs.getString("ct_datosmateria.nombrecorto"));
                reporteD.setLugar(rs.getString("tb_reportedisciplinar.lugar"));
                reporteD.setTipoincidente(rs.getString("ct_incidente.nombre"));
                reporteD.setPeriodo(rs.getString("ct_periodoescolar.nombre"));
                reporteD.setRperiodo(rs.getInt("tb_reportedisciplinar.r_periodo"));
                reporteD.setNivel(rs.getInt("tb_reportedisciplinar.nivel"));
                reporteD.setDescripcion(rs.getString("tb_reportedisciplinar.descripcion"));
                reporteD.setFoto(rs.getString("tb_reportedisciplinar.foto"));
                alumnosdisciplinar.add(reporteD);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return alumnosdisciplinar;
    }

    /*Consulta para traer la informacion de un reporte en especifico*/
    public TbReporteDisciplinar datosReporteD(int id, String fecha, String hora, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        TbReporteDisciplinar datosreporteD = new TbReporteDisciplinar();
        try {
            con.setAutoCommit(false);
            String consulta = " select * from tb_reportedisciplinar left join tb_personal on tb_reportedisciplinar.r_personal = tb_personal.idTb_Personal \n"
                    + " left join tb_materia on tb_reportedisciplinar.r_materia = tb_materia.idTb_Materia\n"
                    + " join tb_alumnos on tb_reportedisciplinar.r_alumno = tb_alumnos.idTb_Alumnos\n"
                    + " left join tb_tutor on tb_alumnos.r_tutor = tb_tutor.idTb_Tutor\n"
                    + " join ct_incidente on tb_reportedisciplinar.r_tipoincidente = ct_incidente.idCt_incidente\n"
                    + " join ct_periodoescolar on tb_reportedisciplinar.r_periodo = ct_periodoescolar.idCt_PeriodoEscolar\n"
                    + " left join ct_datosmateria on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria\n"
                    + " join ct_grado on tb_alumnos.r_grado = ct_grado.idCt_Grado\n"
                    + " join ct_grupo on tb_alumnos.r_grupo = ct_grupo.idCt_Grupo\n"
                    + " join tb_personal as tb1 on tb_reportedisciplinar.r_personalllena = tb1.idTb_Personal\n"
                    + " join tb_personal as tb2 on tb_reportedisciplinar.r_personalsolicita = tb2.idTb_Personal\n"
                    + " where tb_reportedisciplinar.status = 1 and tb_reportedisciplinar.tipoescuela = ? "
                    + "and tb_reportedisciplinar.r_alumno = ? and tb_reportedisciplinar.fecha = ? and tb_reportedisciplinar.hora = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            pst.setInt(2, id);
            pst.setString(3, fecha);
            pst.setString(4, hora);
            rs = pst.executeQuery();
            while (rs.next()) {
                datosreporteD.setIdtbreporte(rs.getInt("idTb_ReporteDisciplinar"));
                datosreporteD.setRalumno(rs.getInt("tb_reportedisciplinar.r_alumno"));
                datosreporteD.setAlumno(rs.getString("tb_alumnos.nombre"));
                datosreporteD.setAlumnoapep(rs.getString("tb_alumnos.apellidopaterno"));
                datosreporteD.setAlumnoapem(rs.getString("tb_alumnos.apellidomaterno"));
                datosreporteD.setCorreotutor(rs.getString("tb_tutor.correo"));
                datosreporteD.setGrado(rs.getString("ct_grado.nombre"));
                datosreporteD.setGrupo(rs.getString("ct_grupo.nombre"));
                datosreporteD.setPersonal(rs.getString("tb_personal.nombre"));
                datosreporteD.setPersonalllena(rs.getString("tb1.nombre"));
                datosreporteD.setPersonalsolicita(rs.getString("tb2.nombre"));
                datosreporteD.setHora(rs.getString("tb_reportedisciplinar.hora"));
                datosreporteD.setFecha(rs.getString("tb_reportedisciplinar.fecha"));
                datosreporteD.setFechareporte(rs.getString("tb_reportedisciplinar.fechareporte"));
                datosreporteD.setMateria(rs.getString("ct_datosmateria.nombrecorto"));
                datosreporteD.setLugar(rs.getString("tb_reportedisciplinar.lugar"));
                datosreporteD.setTipoincidente(rs.getString("ct_incidente.nombre"));
                datosreporteD.setPeriodo(rs.getString("ct_periodoescolar.nombre"));
                datosreporteD.setRperiodo(rs.getInt("tb_reportedisciplinar.r_periodo"));
                datosreporteD.setNivel(rs.getInt("tb_reportedisciplinar.nivel"));
                datosreporteD.setDescripcion(rs.getString("tb_reportedisciplinar.descripcion"));
                datosreporteD.setFoto(rs.getString("tb_reportedisciplinar.foto"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return datosreporteD;
    }

    /*Consulta para trer los campos a acualizar del reporte*/
    public TbReporteDisciplinar editarReporteD(int id, String fecha, String hora) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        TbReporteDisciplinar reportedAlumno = new TbReporteDisciplinar();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from tb_reportedisciplinar left join tb_personal on tb_reportedisciplinar.r_personal = tb_personal.idTb_Personal \n"
                    + " left join tb_materia on tb_reportedisciplinar.r_materia = tb_materia.idTb_Materia\n"
                    + " join tb_alumnos on tb_reportedisciplinar.r_alumno = tb_alumnos.idTb_Alumnos\n"
                    + " join tb_tutor on tb_alumnos.r_tutor = tb_tutor.idTb_Tutor\n"
                    + " join ct_incidente on tb_reportedisciplinar.r_tipoincidente = ct_incidente.idCt_incidente\n"
                    + " join ct_periodoescolar on tb_reportedisciplinar.r_periodo = ct_periodoescolar.idCt_PeriodoEscolar\n"
                    + " left join ct_datosmateria on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria\n"
                    + " join ct_grado on tb_alumnos.r_grado = ct_grado.idCt_Grado\n"
                    + " join ct_grupo on tb_alumnos.r_grupo = ct_grupo.idCt_Grupo\n"
                    + " join tb_personal as tb1 on tb_reportedisciplinar.r_personalllena = tb1.idTb_Personal\n"
                    + " join tb_personal as tb2 on tb_reportedisciplinar.r_personalsolicita = tb2.idTb_Personal\n"
                    + " where tb_reportedisciplinar.status = 1 and tb_reportedisciplinar.tipoescuela = 1 "
                    + "and tb_reportedisciplinar.r_alumno = ? and tb_reportedisciplinar.fecha = ? and tb_reportedisciplinar.hora = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, id);
            pst.setString(2, fecha);
            pst.setString(3, hora);
            rs = pst.executeQuery();
            while (rs.next()) {
                reportedAlumno.setIdtbreporte(rs.getInt("idTb_ReporteDisciplinar"));
                reportedAlumno.setRalumno(rs.getInt("tb_reportedisciplinar.r_alumno"));
                reportedAlumno.setAlumno(rs.getString("tb_alumnos.nombre"));
                reportedAlumno.setAlumnoapep(rs.getString("tb_alumnos.apellidopaterno"));
                reportedAlumno.setAlumnoapem(rs.getString("tb_alumnos.apellidomaterno"));
                reportedAlumno.setCorreotutor(rs.getString("tb_tutor.correo"));
                reportedAlumno.setGrado(rs.getString("ct_grado.nombre"));
                reportedAlumno.setGrupo(rs.getString("ct_grupo.nombre"));
                reportedAlumno.setPersonal(rs.getString("tb_personal.nombre"));
                reportedAlumno.setPersonalllena(rs.getString("tb1.nombre"));
                reportedAlumno.setPersonalsolicita(rs.getString("tb2.nombre"));
                reportedAlumno.setHora(rs.getString("tb_reportedisciplinar.hora"));
                reportedAlumno.setFecha(rs.getString("tb_reportedisciplinar.fecha"));
                reportedAlumno.setFechareporte(rs.getString("tb_reportedisciplinar.fechareporte"));
                reportedAlumno.setMateria(rs.getString("ct_datosmateria.nombrecorto"));
                reportedAlumno.setLugar(rs.getString("tb_reportedisciplinar.lugar"));
                reportedAlumno.setRtipoincidente(rs.getInt("tb_reportedisciplinar.r_tipoincidente"));
                reportedAlumno.setTipoincidente(rs.getString("ct_incidente.nombre"));
                reportedAlumno.setPeriodo(rs.getString("ct_periodoescolar.nombre"));
                reportedAlumno.setRperiodo(rs.getInt("tb_reportedisciplinar.r_periodo"));
                reportedAlumno.setNivel(rs.getInt("tb_reportedisciplinar.nivel"));
                reportedAlumno.setDescripcion(rs.getString("tb_reportedisciplinar.descripcion"));
                reportedAlumno.setFoto(rs.getString("tb_reportedisciplinar.foto"));
                reportedAlumno.setRpersonal(rs.getInt("tb_reportedisciplinar.r_personal"));
                reportedAlumno.setRpersonalsolicita(rs.getInt("tb_reportedisciplinar.r_personalsolicita"));
                reportedAlumno.setRpersonalllena(rs.getInt("tb_reportedisciplinar.r_personalllena"));
                reportedAlumno.setRmateria(rs.getInt("tb_reportedisciplinar.r_materia"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return (reportedAlumno);
    }

    /*Consulta para guardar los cambios al reporte disciplinar*/
    public void guardareditarReporteD(TbReporteDisciplinar reporteD) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            String consulta = "UPDATE `tb_reportedisciplinar` SET `r_personal` = ?, `hora` = ?, `fecha` = ?, `fechareporte` = ?, "
                    + "`r_personalsolicita` = ?, `r_personalllena` = ?, `r_materia` = ?, `lugar` = ?, `r_tipoincidente` = ?, `r_periodo` = ?, `nivel` = ?, "
                    + "`descripcion` = ? WHERE (`idTb_ReporteDisciplinar` = ?);";
            pst = con.prepareStatement(consulta);
            if (reporteD.getRpersonal() != 0) {
                pst.setInt(1, reporteD.getRpersonal());
            } else {
                pst.setString(1, null);
            }
            pst.setString(2, reporteD.getHora());
            pst.setString(3, reporteD.getFecha());
            pst.setString(4, reporteD.getFechareporte());
            pst.setInt(5, reporteD.getRpersonalsolicita());
            pst.setInt(6, reporteD.getRpersonalllena());
            if (reporteD.getRmateria() != 0) {
                pst.setInt(7, reporteD.getRmateria());
            } else {
                pst.setString(7, null);
            }
            pst.setString(8, reporteD.getLugar());
            pst.setInt(9, reporteD.getRtipoincidente());
            pst.setInt(10, reporteD.getRperiodo());
            pst.setInt(11, reporteD.getNivel());
            pst.setString(12, reporteD.getDescripcion());
            pst.setInt(13, reporteD.getIdtbreporte());
            if (pst.executeUpdate() == 1) {
                con.commit();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                System.out.print("Error" + e);
            }
        }
    }

    /*Consulta para obtener lista de semana fiscal*/
    public List<CtSemanaFiscal> getSemanaFiscal(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtSemanaFiscal> listsemana = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_semanafiscal where status = 1 and tipoescuela = ? order by idCt_SemanaFiscal desc";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                CtSemanaFiscal semana = new CtSemanaFiscal();
                semana.setIdtbsemana(rs.getInt("idCt_SemanaFiscal"));
                semana.setNombre(rs.getString("nombre"));
                semana.setStatus(rs.getInt("status"));
                semana.setTipoescuela(rs.getInt("tipoescuela"));
                listsemana.add(semana);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return listsemana;
    }

    /*Consulta para obtener uns lista de problemas*/
    public List<CtAtencion> getAtencion(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtAtencion> listatencion = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_atencion where status = 1 and tipoescuela = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                CtAtencion atencion = new CtAtencion();
                atencion.setIdtbatencion(rs.getInt("idCt_atencion"));
                atencion.setNombre(rs.getString("nombre"));
                atencion.setStatus(rs.getInt("status"));
                atencion.setTipoescuela(rs.getInt("tipoescuela"));
                listatencion.add(atencion);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return listatencion;
    }

    /*Consulta para guardar actividad semanal*/
    public void guardaActividadSemanal(TbTareaSemanal tarea, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into tb_tareasemanal (r_semanafiscal,r_periodo,tarea,r_dia,r_materiapersonal,fechaentrega,status,tipoescuela) values(?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tarea.getRsemana());
            pst.setInt(2, tarea.getRperiodo());
            pst.setString(3, tarea.getTarea());
            pst.setInt(4, tarea.getRdia());
            pst.setInt(5, tarea.getRpersonal());
            pst.setString(6, tarea.getFechaentrega());
            pst.setInt(7, 1);
            pst.setInt(8, tipoescuela);

            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al guardar");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /*Consulta para guardar reporte academico*/
    public void guardaReporteAcademico(TbReporteAcademico reporteA, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into tb_reporteacademico (r_materiapersonal,r_semanafiscal,r_alumnohonor,r_alumnoatencion,r_atencion,r_periodo,status,tipoescuela)"
                    + " values (?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, reporteA.getRmateria());
            pst.setInt(2, reporteA.getRsemana());
            pst.setInt(3, reporteA.getRalumnohonor());
            pst.setInt(4, reporteA.getRalumnoatencion());
            pst.setInt(5, reporteA.getRatencion());
            pst.setInt(6, reporteA.getRperiodo());
            pst.setInt(7, 1);
            pst.setInt(8, tipoescuela);

            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al guardar");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void guardarAtencion(TbReporteAcademico reporteA) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "update tb_reporteacademico set r_alumnoatencion = ?, r_atencion = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, reporteA.getRalumnoatencion());
            pst.setInt(2, reporteA.getRatencion());

            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al guardar");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /*Consulta par aguardar un problema*/
    public void guardaComportamiento(CtAtencion atencion, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into ct_atencion (nombre,status,tipoescuela) values(?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, atencion.getNombre());
            pst.setInt(2, 1);
            pst.setInt(3, tipoescuela);

            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al guardar");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /*Consulta para guardar una semana fiscal*/
    public void guardaSemana(CtSemanaFiscal semana, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into ct_semanafiscal (nombre,status,tipoescuela) values(?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, semana.getNombre());
            pst.setInt(2, 1);
            pst.setInt(3, tipoescuela);

            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al guardar");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /*Consulta que trae datos para agregarlos a una imagen, el registro será el ultimo agregado a al tabla justo despues de guardar un reporte academico*/
    public ImagenReporteAcademico datosGuardaImagen(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        ImagenReporteAcademico datosA = new ImagenReporteAcademico();
        try {
            con.setAutoCommit(false);
            String consulta = "SELECT tb_personal.correo, tb_personal.nombre, tb_personal.apellidopaterno, tb_personal.apellidomaterno, ct_datosmateria.nombrecorto, ct_periodoescolar.nombre, ct_semanafiscal.nombre from tb_reporteacademico inner join tb_materiapersonal\n" +
"                    on tb_reporteacademico.r_materiapersonal = tb_materiapersonal.idTb_MateriaPersonal inner join\n" +
"                    tb_personal on tb_materiapersonal.r_personal = tb_personal.idTb_Personal inner join\n" +
"                    ct_semanafiscal on tb_reporteacademico.r_semanafiscal = ct_semanafiscal.idCt_SemanaFiscal inner join\n" +
"                    ct_periodoescolar on tb_reporteacademico.r_periodo = ct_periodoescolar.idCt_PeriodoEscolar inner join\n" +
"                    tb_materia on tb_materiapersonal.r_materia = tb_materia.idTb_Materia inner join\n" +
"                    ct_datosmateria on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria where tb_reporteacademico.status = 1 and tb_reporteacademico.tipoescuela = ? order by tb_reporteacademico.idTb_ReporteAcademico desc limit 1;";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                datosA.setNombreP(rs.getString("tb_personal.nombre"));
                datosA.setApellidopP(rs.getString("tb_personal.apellidopaterno"));
                datosA.setApellidomP(rs.getString("tb_personal.apellidomaterno"));
                datosA.setNombremateria(rs.getString("ct_datosmateria.nombrecorto"));
                datosA.setPeriodo(rs.getString("ct_periodoescolar.nombre"));
                datosA.setSemanafiscal(rs.getString("ct_semanafiscal.nombre"));
                datosA.setCorreo(rs.getString("tb_personal.correo"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return datosA;
    }

    /*Consulta que trae datos para agregarlos a una imagen, el registro será el ultimo agregado a al tabla justo despues de guardar un reporte academico en la parte de actividad semanal*/
    public ImagenReporteAcademicoTarea datosGuardaImagenTarea(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        ImagenReporteAcademicoTarea datosTarea = new ImagenReporteAcademicoTarea();
        try {
            con.setAutoCommit(false);
            String consulta = "select tb_tareasemanal.idTb_TareaSemanal, ct_periodoescolar.nombre, ct_semanafiscal.nombre, tb_personal.nombre, tb_personal.apellidopaterno, tb_personal.apellidomaterno, tb_personal.correo from tb_tareasemanal\n"
                    + "inner join ct_periodoescolar on tb_tareasemanal.r_periodo = ct_periodoescolar.idCt_PeriodoEscolar inner join\n"
                    + "ct_semanafiscal on tb_tareasemanal.r_semanafiscal = ct_semanafiscal.idCt_SemanaFiscal inner join\n"
                    + "tb_materiapersonal on tb_tareasemanal.r_materiapersonal = tb_materiapersonal.idTb_MateriaPersonal inner join\n"
                    + "tb_personal on tb_materiapersonal.r_personal = tb_personal.idTb_Personal where tb_tareasemanal.status = 1 and tb_tareasemanal.tipoescuela = ? order by tb_tareasemanal.idTb_TareaSemanal desc limit 1;";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                datosTarea.setNombreP(rs.getString("tb_personal.nombre"));
                datosTarea.setApellidopP(rs.getString("tb_personal.apellidopaterno"));
                datosTarea.setApellidomP(rs.getString("tb_personal.apellidomaterno"));
                datosTarea.setPeriodo(rs.getString("ct_periodoescolar.nombre"));
                datosTarea.setSemanafiscal(rs.getString("ct_semanafiscal.nombre"));
                datosTarea.setCorreo(rs.getString("tb_personal.correo"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return datosTarea;
    }

    /*Consulta que trae los datos de una reporte academico especifico*/
    public TbReporteAcademico datosReporteA(int tipoescuelareporte) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        TbReporteAcademico datosA = new TbReporteAcademico();
        try {
            con.setAutoCommit(false);
            String consulta = "select tb_reporteacademico.idTb_ReporteAcademico, tb1.nombre, tb1.apellidopaterno, tb1.apellidomaterno,tb2.nombre, tb2.apellidopaterno, tb2.apellidomaterno, ct_atencion.nombre from tb_reporteacademico inner join tb_alumnos as tb1\n"
                    + "on tb_reporteacademico.r_alumnohonor = tb1.idTb_Alumnos inner join\n"
                    + "tb_alumnos as tb2 on tb_reporteacademico.r_alumnoatencion = tb2.idTb_Alumnos inner join\n"
                    + "ct_atencion on tb_reporteacademico.r_atencion = ct_atencion.idCt_atencion"
                    + " where tb_reporteacademico.status = 1 and tb_reporteacademico.tipoescuela = ? order by tb_reporteacademico.idTb_ReporteAcademico desc limit 1";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuelareporte);
            rs = pst.executeQuery();
            while (rs.next()) {
                datosA.setIdtbreporte(rs.getInt("tb_reporteacademico.idTb_ReporteAcademico"));
                datosA.setNombrehonor(rs.getString("tb1.nombre"));
                datosA.setApellidophonor(rs.getString("tb1.apellidopaterno"));
                datosA.setApellidomhonor(rs.getString("tb1.apellidomaterno"));
                datosA.setNombreatencion(rs.getString("tb2.nombre"));
                datosA.setApellidopatencion(rs.getString("tb2.apellidopaterno"));
                datosA.setApellidomatencion(rs.getString("tb2.apellidomaterno"));
                datosA.setAtencion(rs.getString("ct_atencion.nombre"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return datosA;
    }

    /*Consulta para traer los datos de una tarea asignada*/
    public TbTareaSemanal datosTareaSemanal(int tipoescuelareporte) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        TbTareaSemanal tarea = new TbTareaSemanal();
        try {
            con.setAutoCommit(false);
            String consulta = "select tb_tareasemanal.idTb_TareaSemanal, ct_dia.nombre, tb_personal.nombre,tb_personal.apellidopaterno,tb_personal.apellidomaterno,tb_tareasemanal.tarea, ct_datosmateria.nombrelargo,tb_tareasemanal.fechaentrega\n"
                    + "                     from tb_tareasemanal inner join ct_dia on tb_tareasemanal.r_dia = ct_dia.idCt_Dia\n"
                    + "                    inner join tb_materiapersonal on tb_tareasemanal.r_materiapersonal = tb_materiapersonal.idTb_MateriaPersonal\n"
                    + "                    inner join tb_personal on tb_materiapersonal.r_personal = tb_personal.idTb_Personal\n"
                    + "                    inner join tb_materia on tb_materiapersonal.r_materia = tb_materia.idTb_Materia\n"
                    + "                    inner join ct_datosmateria on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria\n"
                    + "                     where tb_tareasemanal.status = 1 and tb_tareasemanal.tipoescuela = ? order by tb_tareasemanal.idTb_TareaSemanal desc limit 1;";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuelareporte);
            rs = pst.executeQuery();
            while (rs.next()) {
                tarea.setIdtbtarea(rs.getInt("tb_tareasemanal.idTb_TareaSemanal"));
                tarea.setDia(rs.getString("ct_dia.nombre"));
                tarea.setPersonal(rs.getString("tb_personal.nombre"));
                tarea.setApellidop(rs.getString("tb_personal.apellidopaterno"));
                tarea.setApellidom(rs.getString("tb_personal.apellidomaterno"));
                tarea.setTarea(rs.getString("tb_tareasemanal.tarea"));
                tarea.setMateria(rs.getString("ct_datosmateria.nombrelargo"));
                tarea.setFechaentrega(rs.getString("tb_tareasemanal.fechaentrega"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return tarea;
    }

    /*Consulta para traer todos lso reportes academicos*/
    public List<TbReporteAcademico> getAlumnosReporteA(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TbReporteAcademico> alumnosacademico = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select tb_reporteacademico.idTb_ReporteAcademico, tb_personal.nombre, tb_personal.apellidopaterno,tb_personal.apellidomaterno,ct_semanafiscal.nombre,ct_periodoescolar.idCt_PeriodoEscolar, ct_datosmateria.nombrecorto, tb1.nombre,tb1.apellidopaterno,tb1.apellidomaterno,\n"
                    + "                    tb2.nombre,tb2.apellidopaterno,tb2.apellidomaterno, ct_atencion.nombre from tb_reporteacademico inner join \n"
                    + "                    tb_materiapersonal on tb_reporteacademico.r_materiapersonal = tb_materiapersonal.idTb_MateriaPersonal inner join\n"
                    + "                    tb_personal on tb_materiapersonal.r_personal = tb_personal.idTb_Personal inner join\n"
                    + "                    ct_semanafiscal on tb_reporteacademico.r_semanafiscal = ct_semanafiscal.idCt_SemanaFiscal inner join\n"
                    + "                    ct_atencion on tb_reporteacademico.r_atencion = ct_atencion.idCt_atencion inner join\n"
                    + "                    ct_periodoescolar on tb_reporteacademico.r_periodo = ct_periodoescolar.idct_periodoescolar inner join\n"
                    + "                    tb_alumnos as tb1 on tb_reporteacademico.r_alumnohonor = tb1.idTb_Alumnos inner join\n"
                    + "                    tb_alumnos as tb2 on tb_reporteacademico.r_alumnoatencion = tb2.idTb_Alumnos inner join\n"
                    + "                    tb_materia on tb_materiapersonal.r_materia = tb_materia.idTb_Materia inner join\n"
                    + "                    ct_datosmateria on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria where tb_reporteacademico.status = 1 and tb_reporteacademico.tipoescuela = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                TbReporteAcademico reporteA = new TbReporteAcademico();
                reporteA.setIdtbreporte(rs.getInt("tb_reporteacademico.idTb_ReporteAcademico"));
                reporteA.setNombrepersonal(rs.getString("tb_personal.nombre"));
                reporteA.setApellidoppersonal(rs.getString("tb_personal.apellidopaterno"));
                reporteA.setApellidompersonal(rs.getString("tb_personal.apellidomaterno"));
                reporteA.setSemana(rs.getString("ct_semanafiscal.nombre"));
                reporteA.setNombrehonor(rs.getString("tb1.nombre"));
                reporteA.setApellidophonor(rs.getString("tb1.apellidopaterno"));
                reporteA.setApellidomhonor(rs.getString("tb1.apellidomaterno"));
                reporteA.setNombreatencion(rs.getString("tb2.nombre"));
                reporteA.setApellidopatencion(rs.getString("tb2.apellidopaterno"));
                reporteA.setApellidomatencion(rs.getString("tb2.apellidomaterno"));
                reporteA.setAtencion(rs.getString("ct_atencion.nombre"));
                reporteA.setRperiodo(rs.getInt("ct_periodoescolar.idCt_PeriodoEscolar"));
                reporteA.setMateria(rs.getString("ct_datosmateria.nombrecorto"));
                alumnosacademico.add(reporteA);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return alumnosacademico;
    }

    /*Consulta para traer todas las tareas asignadas*/
    public List<TbTareaSemanal> getTareas(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TbTareaSemanal> listtareas = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select  tb_tareasemanal.idTb_TareaSemanal,tb_personal.nombre,tb_personal.apellidopaterno,tb_personal.apellidomaterno, ct_periodoescolar.idCt_PeriodoEscolar, \n" +
"                    ct_dia.Nombre, tb_tareasemanal.fechaentrega, tb_tareasemanal.tarea, ct_semanafiscal.nombre from\n" +
"                    tb_tareasemanal inner join tb_materiapersonal on tb_tareasemanal.r_materiapersonal = tb_materiapersonal.idTb_MateriaPersonal inner join\n" +
"                    tb_personal on tb_materiapersonal.r_personal = tb_personal.idTb_Personal inner join\n" +
"                    ct_periodoescolar on tb_tareasemanal.r_periodo = ct_periodoescolar.idCt_PeriodoEscolar inner join\n" +
"                    ct_dia on tb_tareasemanal.r_dia = ct_dia.idCt_Dia inner join\n" +
"                    ct_semanafiscal on tb_tareasemanal.r_semanafiscal = ct_semanafiscal.idCt_SemanaFiscal where tb_tareasemanal.status = 1 and tb_tareasemanal.tipoescuela = ?;";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                TbTareaSemanal tarea = new TbTareaSemanal();
                tarea.setIdtbtarea(rs.getInt("tb_tareasemanal.idTb_TareaSemanal"));
                tarea.setPersonal(rs.getString("tb_personal.nombre"));
                tarea.setApellidop(rs.getString("tb_personal.apellidopaterno"));
                tarea.setApellidom(rs.getString("tb_personal.apellidomaterno"));
                tarea.setRperiodo(rs.getInt("ct_periodoescolar.idCt_PeriodoEscolar"));
                tarea.setDia(rs.getString("ct_dia.Nombre"));
                tarea.setSemana(rs.getString("ct_semanafiscal.nombre"));
                tarea.setFechaentrega(rs.getString("tb_tareasemanal.fechaentrega"));
                tarea.setTarea(rs.getString("tb_tareasemanal.tarea"));
                listtareas.add(tarea);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return listtareas;
    }

    /*Se elimian el registro de manera logica*/
    public void eliminarTarea(int id) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "update `tb_tareasemanal` set `status` = 2 where `idTb_TareaSemanal` = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, id);
            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al eliminar");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /*Se elimina el registro de manera logica*/
    public void eliminarReporteA(int id) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "update `tb_reporteacademico` set `status` = 2 where `idTb_ReporteAcademico` = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, id);
            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al eliminar");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /*Se elimina el regitro de manera logica*/
    public void eliminarReporteD(int id) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "update `tb_reportedisciplinar` set `status` = 2 where `idTb_ReporteDisciplinar` = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, id);
            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al eliminar");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /*Consulta que trae alumnos que aun no estan asignados a materias, las condiciones nos dicen que consulta usar de acuerdo a los parametros que recibimos*/
    public List<Alumno> getAlumnosMateria(int grado, int grupo, int area, int cpt, int materiapersonal, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Alumno> listalumno = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            if (grado != 0 && grupo == 0) {
                String consulta = "SELECT tb_alumnos.idTb_Alumnos,tb_alumnos.nombre,tb_alumnos.apellidopaterno,tb_alumnos.apellidomaterno from tb_alumnos"
                        + " where tb_alumnos.r_grado = ? and tb_alumnos.status = 1 and tb_alumnos.tipoescuela = ?\n"
                        + "and idTb_Alumnos not in (\n"
                        + "	select r_alumno from tb_materiaalumno where tb_materiaalumno.r_materiapersonal = ?\n"
                        + ");";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, grado);
                pst.setInt(2, tipoescuela);
                pst.setInt(3, materiapersonal);
                rs = pst.executeQuery();
            } else if (area == 0 && cpt == 0) {
                String consulta = "SELECT tb_alumnos.idTb_Alumnos,tb_alumnos.nombre,tb_alumnos.apellidopaterno,tb_alumnos.apellidomaterno from tb_alumnos"
                        + " where tb_alumnos.r_grado = ? and tb_alumnos.r_grupo = ? and tb_alumnos.status = 1 and tb_alumnos.tipoescuela = ?\n"
                        + "and idTb_Alumnos not in (\n"
                        + "	select r_alumno from tb_materiaalumno where tb_materiaalumno.r_materiapersonal = ?\n"
                        + ");";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, grado);
                pst.setInt(2, grupo);
                pst.setInt(3, tipoescuela);
                pst.setInt(4, materiapersonal);
                rs = pst.executeQuery();
            }
            if (area != 0 && cpt == 0) {
                String consulta = "SELECT tb_alumnos.idTb_Alumnos,tb_alumnos.nombre,tb_alumnos.apellidopaterno,tb_alumnos.apellidomaterno from tb_alumnos\n"
                        + "                    where tb_alumnos.r_area = ? and tb_alumnos.r_grado = ? and tb_alumnos.status = 1 and tb_alumnos.tipoescuela = ?\n"
                        + "                    and idTb_Alumnos not in (\n"
                        + "                    	select r_alumno from tb_materiaalumno where tb_materiaalumno.r_materiapersonal = ?\n"
                        + "                    );";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, area);
                pst.setInt(2, grado);
                pst.setInt(3, tipoescuela);
                pst.setInt(4, materiapersonal);
                rs = pst.executeQuery();
            }
            if (area == 0 && cpt != 0) {
                String consulta = "SELECT tb_alumnos.idTb_Alumnos,tb_alumnos.nombre,tb_alumnos.apellidopaterno,tb_alumnos.apellidomaterno from tb_alumnos\n"
                        + "                    where tb_alumnos.r_cpt = ? and tb_alumnos.r_grado = ? and tb_alumnos.status = 1 and tb_alumnos.tipoescuela = ?\n"
                        + "                    and idTb_Alumnos not in (\n"
                        + "                    	select r_alumno from tb_materiaalumno where tb_materiaalumno.r_materiapersonal = ?\n"
                        + "                    );";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, cpt);
                pst.setInt(2, grado);
                pst.setInt(3, tipoescuela);
                pst.setInt(4, materiapersonal);
                rs = pst.executeQuery();
            }
            if (area != 0 && cpt != 0 && grado != 0 && grupo == 0) {
                String consulta = "SELECT tb_alumnos.idTb_Alumnos,tb_alumnos.nombre,tb_alumnos.apellidopaterno,tb_alumnos.apellidomaterno from tb_alumnos\n"
                        + "                    where tb_alumnos.r_cpt = ? and tb_alumnos.r_grado = ? and tb_alumnos.r_grupo = ? and tb_alumnos.status = 1 and tb_alumnos.tipoescuela = ?\n"
                        + "                    and idTb_Alumnos not in (\n"
                        + "                    	select r_alumno from tb_materiaalumno where tb_materiaalumno.r_materiapersonal = ?\n"
                        + "                    );";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, cpt);
                pst.setInt(2, grado);
                pst.setInt(3, grupo);
                pst.setInt(4, tipoescuela);
                pst.setInt(5, materiapersonal);
                rs = pst.executeQuery();
            }
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setId(rs.getInt("tb_alumnos.idTb_Alumnos"));
                alumno.setNombre(rs.getString("tb_alumnos.nombre"));
                alumno.setApellidop(rs.getString("tb_alumnos.apellidopaterno"));
                alumno.setApellidom(rs.getString("tb_alumnos.apellidomaterno"));
                listalumno.add(alumno);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return listalumno;
    }

    /*Consulta que trae los alumnos para cada materia del profesor, la condicion nos dirá que consuta usar de acuerdo a los parametros recibidos*/
    public List<Alumno> getListaAlumnos(int grado, int grupo, int area, int cpt, int materiapersonal, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Alumno> listalumno = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            if (grado != 0 && grupo == 0) {
                String consulta = "select tb_materiaalumno.idtb_materiaalumno, tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno from tb_materiaalumno inner join tb_alumnos\n"
                        + "on tb_materiaalumno.r_alumno = tb_alumnos.idTb_Alumnos inner join tb_materiapersonal\n"
                        + "on tb_materiaalumno.r_materiapersonal = tb_materiapersonal.idTb_MateriaPersonal\n"
                        + "where tb_alumnos.r_grado = ? and tb_materiaalumno.r_materiapersonal = ? and tb_materiaalumno.status = 1 and tb_materiaalumno.tipoescuela = ?;";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, grado);
                pst.setInt(2, materiapersonal);
                pst.setInt(3, tipoescuela);
                rs = pst.executeQuery();
            } else if (area == 0 && cpt == 0) {
                String consulta = "select tb_materiaalumno.idtb_materiaalumno, tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno from tb_materiaalumno inner join tb_alumnos\n"
                        + "on tb_materiaalumno.r_alumno = tb_alumnos.idTb_Alumnos inner join tb_materiapersonal\n"
                        + "on tb_materiaalumno.r_materiapersonal = tb_materiapersonal.idTb_MateriaPersonal\n"
                        + "where tb_alumnos.r_grado = ? and tb_alumnos.r_grupo = ? and tb_materiaalumno.r_materiapersonal = ? and tb_materiaalumno.status = 1 and tb_materiaalumno.tipoescuela = ?;";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, grado);
                pst.setInt(2, grupo);
                pst.setInt(3, materiapersonal);
                pst.setInt(4, tipoescuela);
                rs = pst.executeQuery();
            }
            if (area != 0 && cpt == 0) {
                String consulta = "select tb_materiaalumno.idtb_materiaalumno, tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno from tb_materiaalumno inner join tb_alumnos\n"
                        + "on tb_materiaalumno.r_alumno = tb_alumnos.idTb_Alumnos inner join tb_materiapersonal\n"
                        + "on tb_materiaalumno.r_materiapersonal = tb_materiapersonal.idTb_MateriaPersonal\n"
                        + "where tb_alumnos.r_area = ? and tb_alumnos.r_grado = ? and tb_materiaalumno.r_materiapersonal = ? and tb_materiaalumno.status = 1 and tb_materiaalumno.tipoescuela = ?;";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, area);
                pst.setInt(2, grado);
                pst.setInt(3, materiapersonal);
                pst.setInt(4, tipoescuela);
                rs = pst.executeQuery();
            }
            if (area == 0 && cpt != 0) {
                String consulta = "select tb_materiaalumno.idtb_materiaalumno, tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno from tb_materiaalumno inner join tb_alumnos\n"
                        + "on tb_materiaalumno.r_alumno = tb_alumnos.idTb_Alumnos inner join tb_materiapersonal\n"
                        + "on tb_materiaalumno.r_materiapersonal = tb_materiapersonal.idTb_MateriaPersonal\n"
                        + "where tb_alumnos.r_cpt = ? and tb_alumnos.r_grado = ? and tb_materiaalumno.r_materiapersonal = ? and tb_materiaalumno.status = 1 and tb_materiaalumno.tipoescuela = ?;";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, cpt);
                pst.setInt(2, grado);
                pst.setInt(3, materiapersonal);
                pst.setInt(4, tipoescuela);
                rs = pst.executeQuery();
            }
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setId(rs.getInt("tb_materiaalumno.idtb_materiaalumno"));
                alumno.setNombre(rs.getString("tb_alumnos.nombre"));
                alumno.setApellidop(rs.getString("tb_alumnos.apellidopaterno"));
                alumno.setApellidom(rs.getString("tb_alumnos.apellidomaterno"));
                listalumno.add(alumno);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return listalumno;
    }

    public List<TbHorario> getHorario(int idalumno, int idperiodo, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TbHorario> listhorario = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select tb_horario.idtb_horario,ct_periodoescolar.nombre, ct_datosmateria.nombrecorto, ct_datosmateria.nombrelargo, tb_personal.nombre, tb_personal.apellidopaterno, tb_personal.apellidomaterno,\n"
                    + "tb_horario.hora from tb_horario inner join tb_materiaalumno on tb_horario.r_materiaalumno = tb_materiaalumno.idtb_materiaalumno\n"
                    + "inner join tb_materiapersonal on tb_materiaalumno.r_materiapersonal = tb_materiapersonal.idTb_MateriaPersonal\n"
                    + "inner join tb_materia on tb_materiapersonal.r_materia = tb_materia.idTb_Materia\n"
                    + "inner join ct_datosmateria on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria\n"
                    + "inner join tb_personal on tb_materiapersonal.r_personal = tb_personal.idTb_Personal\n"
                    + "inner join ct_periodoescolar on tb_horario.r_periodo = ct_periodoescolar.idCt_PeriodoEscolar\n"
                    + "where tb_materiaalumno.r_alumno = ? and tb_horario.status = 1 and tb_horario.tipoescuela = ? and tb_horario.r_periodo = ?;";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, idalumno);
            pst.setInt(2, tipoescuela);
            pst.setInt(3, idperiodo);
            rs = pst.executeQuery();
            while (rs.next()) {
                TbHorario horario = new TbHorario();
                horario.setIdtbhorario(rs.getInt("tb_horario.idtb_horario"));
                horario.setNombrecorto(rs.getString("ct_datosmateria.nombrecorto"));
                horario.setNombrelargo(rs.getString("ct_datosmateria.nombrelargo"));
                horario.setNombrepe(rs.getString("tb_personal.nombre"));
                horario.setApellidopp(rs.getString("tb_personal.apellidopaterno"));
                horario.setApellidomp(rs.getString("tb_personal.apellidomaterno"));
                horario.setHora(rs.getString("tb_horario.hora"));
                horario.setPeriodo(rs.getString("ct_periodoescolar.nombre"));
                listhorario.add(horario);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return listhorario;
    }

    /*Consulta que trae la cantidad de faltas de los alumnos de acuerdo a una materia, asi como los dias en lso que ha faltado, agrupados para no repetir registros*/
    public List<TbAsistencia> getAlumnosAsistencia(int idperiodo, int idmateria, int idsemana, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TbAsistencia> listalumno = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select tb_asistencia.idtb_asistencia, count(tb_asistencia.asistencia) as faltas,group_concat(ct_dia.nombre) as dias,tb_alumnos.idTb_Alumnos,  tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno\n"
                    + "from tb_asistencia inner join tb_materiaalumno\n"
                    + "						on tb_asistencia.r_materiaalumno = tb_materiaalumno.idtb_materiaalumno inner join ct_semanafiscal\n"
                    + "                        on tb_asistencia.r_semanafiscal = ct_semanafiscal.idCt_SemanaFiscal inner join ct_dia\n"
                    + "                        on tb_asistencia.r_dia = ct_dia.idCt_Dia inner join ct_periodoescolar\n"
                    + "                        on tb_asistencia.r_periodo = ct_periodoescolar.idCt_PeriodoEscolar inner join tb_alumnos\n"
                    + "                        on tb_materiaalumno.r_alumno = tb_alumnos.idTb_Alumnos inner join tb_materiapersonal\n"
                    + "                        on tb_materiaalumno.r_materiapersonal = tb_materiapersonal.idTb_MateriaPersonal\n"
                    + "                       where tb_asistencia.r_periodo = ? and tb_materiaalumno.r_materiapersonal = ? and tb_asistencia.r_semanafiscal = ? and\n"
                    + "                       tb_asistencia.status = 1 and tb_materiaalumno.tipoescuela = ? and tb_asistencia.asistencia = 0 \n"
                    + "						group by tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno;";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, idperiodo);
            pst.setInt(2, idmateria);
            pst.setInt(3, idsemana);
            pst.setInt(4, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                TbAsistencia asistencia = new TbAsistencia();
                asistencia.setIdtbasistencia(rs.getInt("tb_asistencia.idtb_asistencia"));
                asistencia.setAsistencia(rs.getInt("faltas"));
                asistencia.setDia(rs.getString("dias"));
                asistencia.setNombrealum(rs.getString("tb_alumnos.nombre"));
                asistencia.setApellidopa(rs.getString("tb_alumnos.apellidopaterno"));
                asistencia.setApellidoma(rs.getString("tb_alumnos.apellidomaterno"));
                asistencia.setR_alumno(rs.getInt("tb_alumnos.idTb_Alumnos"));
                listalumno.add(asistencia);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return listalumno;
    }

    /*Consulta que trae la cantidad de faltas justificadasa de los alumnos de acuerdo a una materia, asi como los dias en lso que ha faltado, agrupados para no repetir registros*/
    public List<TbAsistencia> getAlumnosAsistenciaJ(int idperiodo, int idmateria, int idsemana, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TbAsistencia> listalumno = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select tb_asistencia.idtb_asistencia, count(tb_asistencia.asistencia) as faltas,group_concat(ct_dia.nombre) as dias,tb_alumnos.idTb_Alumnos,  tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno\n"
                    + "from tb_asistencia inner join tb_materiaalumno\n"
                    + "						on tb_asistencia.r_materiaalumno = tb_materiaalumno.idtb_materiaalumno inner join ct_semanafiscal\n"
                    + "                        on tb_asistencia.r_semanafiscal = ct_semanafiscal.idCt_SemanaFiscal inner join ct_dia\n"
                    + "                        on tb_asistencia.r_dia = ct_dia.idCt_Dia inner join ct_periodoescolar\n"
                    + "                        on tb_asistencia.r_periodo = ct_periodoescolar.idCt_PeriodoEscolar inner join tb_alumnos\n"
                    + "                        on tb_materiaalumno.r_alumno = tb_alumnos.idTb_Alumnos inner join tb_materiapersonal\n"
                    + "                        on tb_materiaalumno.r_materiapersonal = tb_materiapersonal.idTb_MateriaPersonal\n"
                    + "                       where tb_asistencia.r_periodo = ? and tb_materiaalumno.r_materiapersonal = ? and tb_asistencia.r_semanafiscal = ? and\n"
                    + "                       tb_asistencia.status = 1 and tb_materiaalumno.tipoescuela = ? and tb_asistencia.asistencia = 3 \n"
                    + "						group by tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno;";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, idperiodo);
            pst.setInt(2, idmateria);
            pst.setInt(3, idsemana);
            pst.setInt(4, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                TbAsistencia asistencia = new TbAsistencia();
                asistencia.setIdtbasistencia(rs.getInt("tb_asistencia.idtb_asistencia"));
                asistencia.setAsistencia(rs.getInt("faltas"));
                asistencia.setDia(rs.getString("dias"));
                asistencia.setNombrealum(rs.getString("tb_alumnos.nombre"));
                asistencia.setApellidopa(rs.getString("tb_alumnos.apellidopaterno"));
                asistencia.setApellidoma(rs.getString("tb_alumnos.apellidomaterno"));
                asistencia.setR_alumno(rs.getInt("tb_alumnos.idTb_Alumnos"));
                listalumno.add(asistencia);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return listalumno;
    }

    /*Consulta para traer una lista que contendrá la asistencia tomada con anterioridad para su modificacion y posterior actualizacion*/
    public List<TbAsistencia> asistenciaAnterior(int iddia, int idperiodo, int idsemana, int idmateria, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TbAsistencia> listasistencia = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "SELECT tb_asistencia.idtb_asistencia, tb_alumnos.nombre,tb_alumnos.apellidopaterno,tb_alumnos.apellidomaterno, tb_asistencia.asistencia FROM tb_asistencia inner join tb_materiaalumno\n"
                    + "                    on tb_asistencia.r_materiaalumno = tb_materiaalumno.idtb_materiaalumno inner join tb_alumnos\n"
                    + "                    on tb_materiaalumno.r_alumno = tb_alumnos.idTb_Alumnos inner join tb_materiapersonal\n"
                    + "                    on tb_materiaalumno.r_materiapersonal = tb_materiapersonal.idTb_MateriaPersonal\n"
                    + "                    where tb_asistencia.r_periodo = ? and tb_asistencia.r_semanafiscal = ? and tb_asistencia.r_dia = ? and tb_asistencia.status = 1 and tb_asistencia.tipoescuela = ? and tb_materiaalumno.r_materiapersonal = ?\n"
                    + "                     order by tb_alumnos.apellidopaterno asc;";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, idperiodo);
            pst.setInt(2, idsemana);
            pst.setInt(3, iddia);
            pst.setInt(4, tipoescuela);
            pst.setInt(5, idmateria);
            rs = pst.executeQuery();
            while (rs.next()) {
                TbAsistencia asistencia = new TbAsistencia();
                asistencia.setIdtbasistencia(rs.getInt("tb_asistencia.idtb_asistencia"));
                asistencia.setAsistencia(rs.getInt("tb_asistencia.asistencia"));
                asistencia.setNombrealum(rs.getString("tb_alumnos.nombre"));
                asistencia.setApellidopa(rs.getString("tb_alumnos.apellidopaterno"));
                asistencia.setApellidoma(rs.getString("tb_alumnos.apellidomaterno"));
                listasistencia.add(asistencia);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return listasistencia;
    }

    /*Consulta para traer el ultimo periodo registrado en la base de datos para la vista de guardar asistencia*/
    public CtPeriodoEscolar getPeriodosAsistencia(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        CtPeriodoEscolar periodo = new CtPeriodoEscolar();
        try {
            con.setAutoCommit(false);
            String consulta = "select * FROM ct_periodoescolar where tipoescuela = ? and status = 1 order by idCt_PeriodoEscolar desc limit 1;";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                periodo.setIdtbperiodo(rs.getInt("idCt_PeriodoEscolar"));
                periodo.setNombre(rs.getString("nombre"));
                periodo.setStatus(rs.getInt("status"));
                periodo.setTipoescuela(rs.getInt("tipoescuela"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return periodo;
    }

    /*Consulta para traer la ultima semana fiscal registrada en la base de datos para la vista de guardar asistencia*/
    public CtSemanaFiscal getSemanaFiscalAsistencia(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        CtSemanaFiscal semana = new CtSemanaFiscal();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_semanafiscal where status = 1 and tipoescuela = ? order by idCt_SemanaFiscal desc limit 1";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                semana.setIdtbsemana(rs.getInt("idCt_SemanaFiscal"));
                semana.setNombre(rs.getString("nombre"));
                semana.setStatus(rs.getInt("status"));
                semana.setTipoescuela(rs.getInt("tipoescuela"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.err.println("Error " + e);
            }
        }
        return semana;
    }

}
