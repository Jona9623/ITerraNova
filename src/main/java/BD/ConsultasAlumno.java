/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelos.Alumno;
import Modelos.CtIncidente;
import Modelos.CtPeriodoEscolar;
import Modelos.CtPuesto;
import Modelos.TbAlumnos;
import Modelos.TbMateria;
import Modelos.TbReporteDisciplinar;
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

    public List<CtPeriodoEscolar> getPeriodos() {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtPeriodoEscolar> listperiodo = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_periodoescolar where status = 1 and tipoescuela = 1 order by idCt_PeriodoEscolar desc";
            pst = con.prepareStatement(consulta);
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
            System.out.println(e);
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

    public List<TbMateria> getMaterias() {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TbMateria> listmateria = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from (tb_materia inner join ct_datosmateria on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria) where tb_materia.status = 1 and tb_materia.tipoescuela = 1";
            pst = con.prepareStatement(consulta);
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
            System.out.println(e);
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

    public List<CtIncidente> getIncidentes() {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtIncidente> listincidente = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_incidente where status = 1 and tipoescuela = 1";
            pst = con.prepareStatement(consulta);
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
            System.out.println(e);
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

    public List<Alumno> getAlumnos(int grado, int grupo) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Alumno> listalumno = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from tb_alumnos where r_grado = ? and r_grupo = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, grado);
            pst.setInt(2, grupo);
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
            System.out.println(e);
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

    public void guardaIincidente(CtIncidente incidente) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into ct_incidente (nombre,status,tipoescuela) values (?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, incidente.getNombre());
            pst.setInt(2, 1);
            pst.setInt(3, 1);

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

    public void guardaReporteD(TbReporteDisciplinar reporteD) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into tb_reportedisciplinar (r_alumno,r_personal,hora,fecha,fechareporte,r_personalsolicita,r_personalllena,r_materia,lugar,r_tipoincidente,"
                    + "r_periodo,nivel,descripcion,foto,status,tipoescuela) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, reporteD.getRalumno());
            pst.setInt(2, reporteD.getRpersonal());
            pst.setString(3, reporteD.getHora());
            pst.setString(4, reporteD.getFecha());
            pst.setString(5, reporteD.getFechareporte());
            pst.setInt(6, reporteD.getRpersonalsolicita());
            pst.setInt(7, reporteD.getRpersonalllena());
            pst.setInt(8, reporteD.getRmateria());
            pst.setString(9, reporteD.getLugar());
            pst.setInt(10, reporteD.getRtipoincidente());
            pst.setInt(11, reporteD.getRperiodo());
            pst.setInt(12, reporteD.getNivel());
            pst.setString(13, reporteD.getDescripcion());
            pst.setString(14, reporteD.getFoto());
            pst.setInt(15, 1);
            pst.setInt(16, 1);

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

    public List<TbReporteDisciplinar> getAlumnosReporteD() {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TbReporteDisciplinar> alumnosdisciplinar = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ((((((((((tb_reportedisciplinar inner join tb_alumnos on tb_reportedisciplinar.r_alumno = tb_alumnos.idTb_Alumnos)inner join tb_personal on tb_reportedisciplinar.r_personal = tb_personal.idTb_Personal)\n"
                    + "inner join tb_materia on tb_reportedisciplinar.r_materia = tb_materia.idTb_Materia)inner join ct_incidente on tb_reportedisciplinar.r_tipoincidente = ct_incidente.idCt_incidente)\n"
                    + "inner join ct_periodoescolar on tb_reportedisciplinar.r_periodo = ct_periodoescolar.idCt_PeriodoEscolar)inner join ct_datosmateria on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria)\n"
                    + "inner join ct_grado on tb_alumnos.r_grado = ct_grado.idCt_Grado)inner join ct_grupo on tb_alumnos.r_grupo = ct_grupo.idCt_Grupo)inner join tb_personal as tb1 on tb_reportedisciplinar.r_personalllena = tb1.idTb_Personal)\n"
                    + "inner join tb_personal as tb2 on tb_reportedisciplinar.r_personalsolicita = tb2.idTb_Personal)\n"
                    + " where tb_reportedisciplinar.status = 1 and tb_reportedisciplinar.tipoescuela = 1";
            pst = con.prepareStatement(consulta);
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
            System.out.println(e);
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

}
