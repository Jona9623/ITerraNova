/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonat
 */
public class ConsultasAdministrador {

    private Connection con;
    private int x = 1;

    /*Consulta que trae informacion del alumno para mostrarlo en la tabla*/
    public List<TbAlumnos> getAlumnos(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TbAlumnos> listalumnos = new ArrayList<>();
        try {
            con.setAutoCommit(false);
             String consulta = "select tb_alumnos.r_tutor, tb_alumnos.idTb_Alumnos, tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno, tb_alumnos.matricula, ct_grado.nombre, ct_grupo.nombre, tb_tutor.nombre, tb_tutor.apellidopaterno from\n"
                    + "(((tb_alumnos inner join ct_grado on tb_alumnos.r_grado = ct_grado.idCt_Grado) inner join ct_grupo on tb_alumnos.r_grupo = ct_grupo.idCt_Grupo) inner join\n"
                    + "tb_tutor on tb_alumnos.r_tutor = tb_tutor.idTb_Tutor) where tb_alumnos.status = 1 and tb_alumnos.tipoescuela = ?";
           /* String consulta = "select tb_alumnos.r_tutor, tb_alumnos.idTb_Alumnos, tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno, tb_alumnos.matricula, ct_grado.nombre, ct_grupo.nombre from\n"
                    + "                    tb_alumnos inner join ct_grado on tb_alumnos.r_grado = ct_grado.idCt_Grado inner join ct_grupo on tb_alumnos.r_grupo = ct_grupo.idCt_Grupo \n"
                    + "                    where tb_alumnos.status = 1 and tb_alumnos.tipoescuela = ?;";*/
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                TbAlumnos alumno = new TbAlumnos();
                alumno.setIdtbalumnos(rs.getInt("tb_alumnos.idTb_Alumnos"));
                alumno.setNombre(rs.getString("tb_alumnos.nombre"));
                alumno.setApellidop(rs.getString("tb_alumnos.apellidopaterno"));
                alumno.setApellidom(rs.getString("tb_alumnos.apellidomaterno"));
                alumno.setMatricula(rs.getString("tb_alumnos.matricula"));
                alumno.setRgrado(rs.getInt("ct_grado.nombre"));
                alumno.setGrupo(rs.getString("ct_grupo.nombre"));
                alumno.setRtutor(rs.getInt("tb_alumnos.r_tutor"));
                alumno.setTutor(rs.getString("tb_tutor.nombre"));
                alumno.setApellidotutor(rs.getString("tb_tutor.apellidopaterno"));
                listalumnos.add(alumno);
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
        return listalumnos;
    }

    /*Consulta que trae informacion del personal para mostrarlos en la tabla*/
    public List<TbPersonal> getPersonal(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TbPersonal> listpersonal = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select tb_personal.idTb_Personal, tb_personal.nombre, tb_personal.apellidopaterno, tb_personal.apellidomaterno, tb_personal.correo, ct_puesto.nombre from (tb_personal inner join\n"
                    + "ct_puesto on tb_personal.r_puesto = ct_puesto.idCt_Puesto) where tb_personal.status = 1";
            pst = con.prepareStatement(consulta);
            rs = pst.executeQuery();
            while (rs.next()) {
                TbPersonal personal = new TbPersonal();
                personal.setIdtbpersonal(rs.getInt("tb_personal.idTb_Personal"));
                personal.setNombre(rs.getString("tb_personal.nombre"));
                personal.setApellidop(rs.getString("tb_personal.apellidopaterno"));
                personal.setApellidom(rs.getString("tb_personal.apellidomaterno"));
                personal.setCorreo(rs.getString("tb_personal.correo"));
                personal.setPuesto(rs.getString("ct_puesto.nombre"));
                listpersonal.add(personal);
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
        return listpersonal;
    }

    /*Consulta que guarda la información del tutor*/
    public void guardaTutor(TbTutor tutor, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into tb_tutor (nombre,apellidopaterno,apellidomaterno,ocupacion,parentesco,calledomicilio,numerodomicilio,\n"
                    + "coloniadomicilio,codigopostal,telefonocasa,celular,correo, nombre2,apellidopaterno2,apellidomaterno2,ocupacion2,parentesco2,calledomicilio2,numerodomicilio2,\n"
                    + "coloniadomicilio2,codigopostal2,telefonocasa2,celular2,correo2, nombre3,apellidopaterno3,apellidomaterno3,ocupacion3,parentesco3,calledomicilio3,numerodomicilio3,\n"
                    + "coloniadomicilio3,codigopostal3,telefonocasa3,celular3,correo3,  status,tipoescuela) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, tutor.getNombre());
            pst.setString(2, tutor.getApellidop());
            pst.setString(3, tutor.getApellidom());
            pst.setString(4, tutor.getOcupacion());
            pst.setString(5, tutor.getParentesco());
            pst.setString(6, tutor.getCalledom());
            pst.setInt(7, tutor.getNumerodom());
            pst.setString(8, tutor.getColoniadom());
            pst.setInt(9, tutor.getCodigopostal());
            pst.setString(10, tutor.getTelefonocasa());
            pst.setString(11, tutor.getCelular());
            pst.setString(12, tutor.getCorreo());
            pst.setString(13, tutor.getNombre2());
            pst.setString(14, tutor.getApellidop2());
            pst.setString(15, tutor.getApellidom2());
            pst.setString(16, tutor.getOcupacion2());
            pst.setString(17, tutor.getParentesco2());
            pst.setString(18, tutor.getCalledom2());
            pst.setInt(19, tutor.getNumerodom2());
            pst.setString(20, tutor.getColoniadom2());
            pst.setInt(21, tutor.getCodigopostal2());
            pst.setString(22, tutor.getTelefonocasa2());
            pst.setString(23, tutor.getCelular2());
            pst.setString(24, tutor.getCorreo2());
            pst.setString(25, tutor.getNombre3());
            pst.setString(26, tutor.getApellidop3());
            pst.setString(27, tutor.getApellidom3());
            pst.setString(28, tutor.getOcupacion3());
            pst.setString(29, tutor.getParentesco3());
            pst.setString(30, tutor.getCalledom3());
            pst.setInt(31, tutor.getNumerodom3());
            pst.setString(32, tutor.getColoniadom3());
            pst.setInt(33, tutor.getCodigopostal3());
            pst.setString(34, tutor.getTelefonocasa3());
            pst.setString(35, tutor.getCelular3());
            pst.setString(36, tutor.getCorreo3());
            pst.setInt(37, 1);
            pst.setInt(38, tipoescuela);

            if (pst.executeUpdate() == 1) {
                con.commit();
                String sql2 = "update tb_tutor set tb_tutor.matricula = 10000 + tb_tutor.idTb_Tutor;";
                pst2 = con.prepareStatement(sql2);
                if (pst2.executeUpdate() > 0) {
                    con.commit();
                } else {
                    System.out.println("No se pudo guardar");
                }
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

    /*Consulta que guarda la informacion del alumno*/
    public void guardaAlumno(TbAlumnos alumno, int tipoescuela, String ruta) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        ResultSet rs2 = null;
        int id = 0;
        try {
            con.setAutoCommit(false);
            String sql = "SELECT idTb_Tutor FROM tb_tutor ORDER BY idTb_Tutor DESC LIMIT 1";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt(1);
            }
            String consulta = "insert into tb_alumnos (matricula,nombre,apellidopaterno,apellidomaterno,fechanacimiento,curp,municipionacimiento,estadonacimiento,\n"
                    + "nacionalidad,sexo,calledomicilio,numerodomicilio,coloniadomicilio,codigopostal,telefonocasa,celularalumno,correoalumno,nivelcursa,\n"
                    + "r_grado,r_grupo,r_area,r_cpt,plantelprocedencia,nivelanterior,gradoanterior,turnoanterior,municipioanterior,r_tutor,foto,status,tipoescuela) values (?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, alumno.getMatricula());
            pst.setString(2, alumno.getNombre());
            pst.setString(3, alumno.getApellidop());
            pst.setString(4, alumno.getApellidom());
            pst.setString(5, alumno.getFechanacimiento());
            pst.setString(6, alumno.getCurp());
            pst.setString(7, alumno.getMunicipiona());
            pst.setString(8, alumno.getEstadona());
            pst.setString(9, alumno.getNacionalidad());
            pst.setBoolean(10, alumno.getSexo());
            pst.setString(11, alumno.getCalledom());
            pst.setInt(12, alumno.getNumerodom());
            pst.setString(13, alumno.getColoniadom());
            pst.setInt(14, alumno.getCodigopostal());
            pst.setString(15, alumno.getTelefonocasa());
            pst.setString(16, alumno.getCelular());
            pst.setString(17, alumno.getCorreo());
            pst.setString(18, alumno.getNivelcursa());
            pst.setInt(19, alumno.getRgrado());
            pst.setInt(20, alumno.getRgrupo());
            if (alumno.getRarea() != 0) {
                pst.setInt(21, alumno.getRarea());
            } else {
                pst.setString(21, null);
            }
            if (alumno.getRcpt() != 0) {
                pst.setInt(22, alumno.getRcpt());
            } else {
                pst.setString(22, null);
            }
            pst.setString(23, alumno.getPlantelproce());
            pst.setString(24, alumno.getNivelanterior());
            pst.setInt(25, alumno.getGradoanterior());
            pst.setInt(26, alumno.getTurnoanterior());
            pst.setString(27, alumno.getMunicipioante());
            pst.setInt(28, id);
            pst.setString(29, ruta);
            pst.setInt(30, 1);
            pst.setInt(31, tipoescuela);

            if (pst.executeUpdate() == 1) {
                con.commit();
                String sql2 = "update tb_alumnos set tb_alumnos.matricula = 10000 + tb_alumnos.idTb_Alumnos;";
                pst2 = con.prepareStatement(sql2);
                if (pst2.executeUpdate() > 0) {
                    con.commit();
                } else {
                    System.out.println("No se pudo guardar");
                }
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

    /*Consulta que trae la informacion de un alumno en especifico*/
    public TbAlumnos datosAlumno(int id) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        TbAlumnos alumno = new TbAlumnos();
        try {
            String consulta = "select * from tb_alumnos inner join ct_grado on tb_alumnos.r_grado = ct_grado.idCt_Grado\n"
                    + "inner join ct_grupo on tb_alumnos.r_grupo = ct_grupo.idCt_Grupo\n"
                    + "left join ct_areaalumno on tb_alumnos.r_area = ct_areaalumno.idCt_AreaAlumno\n"
                    + "left join ct_cptalumno on tb_alumnos.r_cpt = ct_cptalumno.idCt_CptAlumno where idTb_Alumnos = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                alumno.setIdtbalumnos(rs.getInt("tb_alumnos.idTb_Alumnos"));
                alumno.setMatricula(rs.getString("tb_alumnos.matricula"));
                alumno.setNombre(rs.getString("tb_alumnos.nombre"));
                alumno.setApellidop(rs.getString("tb_alumnos.apellidopaterno"));
                alumno.setApellidom(rs.getString("tb_alumnos.apellidomaterno"));
                alumno.setFechanacimiento(rs.getString("tb_alumnos.fechanacimiento"));
                alumno.setCurp(rs.getString("tb_alumnos.curp"));
                alumno.setMunicipiona(rs.getString("tb_alumnos.municipionacimiento"));
                alumno.setEstadona(rs.getString("tb_alumnos.estadonacimiento"));
                alumno.setNacionalidad(rs.getString("tb_alumnos.nacionalidad"));
                alumno.setSexo(rs.getBoolean("tb_alumnos.sexo"));
                alumno.setCalledom(rs.getString("tb_alumnos.calledomicilio"));
                alumno.setNumerodom(rs.getInt("tb_alumnos.numerodomicilio"));
                alumno.setColoniadom(rs.getString("tb_alumnos.coloniadomicilio"));
                alumno.setCodigopostal(rs.getInt("tb_alumnos.codigopostal"));
                alumno.setTelefonocasa(rs.getString("tb_alumnos.telefonocasa"));
                alumno.setCelular(rs.getString("tb_alumnos.celularalumno"));
                alumno.setCorreo(rs.getString("tb_alumnos.correoalumno"));
                alumno.setNivelcursa(rs.getString("nivelcursa"));
                alumno.setRgrado(rs.getInt("tb_alumnos.r_grado"));
                alumno.setRgrupo(rs.getInt("tb_alumnos.r_grupo"));
                alumno.setRarea(rs.getInt("tb_alumnos.r_area"));
                alumno.setRcpt(rs.getInt("tb_alumnos.r_cpt"));
                alumno.setPlantelproce(rs.getString("tb_alumnos.plantelprocedencia"));
                alumno.setNivelanterior(rs.getString("tb_alumnos.nivelanterior"));
                alumno.setGradoanterior(rs.getInt("tb_alumnos.gradoanterior"));
                alumno.setTurnoanterior(rs.getInt("tb_alumnos.turnoanterior"));
                alumno.setMunicipioante(rs.getString("tb_alumnos.municipioanterior"));
                alumno.setFoto(rs.getString("tb_alumnos.foto"));
                alumno.setGrado(rs.getString("ct_grado.nombre"));
                alumno.setGrupo(rs.getString("ct_grupo.nombre"));
                alumno.setArea(rs.getString("ct_areaalumno.nombre"));
                alumno.setCpt(rs.getString("ct_cptalumno.nombre"));
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
        return alumno;
    }

    /*Consulta que trae la informacion del tutor */
    public TbTutor datosTutor(int id) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        TbTutor tutor = new TbTutor();
        try {
            String consulta = "select * from tb_tutor where idTb_Tutor = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                tutor.setIdtbtutor(rs.getInt("idTb_Tutor"));
                tutor.setNombre(rs.getString("nombre"));
                tutor.setApellidop(rs.getString("apellidopaterno"));
                tutor.setApellidom(rs.getString("apellidomaterno"));
                tutor.setOcupacion(rs.getString("ocupacion"));
                tutor.setParentesco(rs.getString("parentesco"));
                tutor.setCalledom(rs.getString("calledomicilio"));
                tutor.setNumerodom(rs.getInt("numerodomicilio"));
                tutor.setColoniadom(rs.getString("coloniadomicilio"));
                tutor.setCodigopostal(rs.getInt("codigopostal"));
                tutor.setTelefonocasa(rs.getString("telefonocasa"));
                tutor.setCelular(rs.getString("celular"));
                tutor.setCorreo(rs.getString("correo"));
                tutor.setNombre2(rs.getString("nombre2"));
                tutor.setApellidop2(rs.getString("apellidopaterno2"));
                tutor.setApellidom2(rs.getString("apellidomaterno2"));
                tutor.setOcupacion2(rs.getString("ocupacion2"));
                tutor.setParentesco2(rs.getString("parentesco2"));
                tutor.setCalledom2(rs.getString("calledomicilio2"));
                tutor.setNumerodom2(rs.getInt("numerodomicilio2"));
                tutor.setColoniadom2(rs.getString("coloniadomicilio2"));
                tutor.setCodigopostal2(rs.getInt("codigopostal2"));
                tutor.setTelefonocasa2(rs.getString("telefonocasa2"));
                tutor.setCelular2(rs.getString("celular2"));
                tutor.setCorreo2(rs.getString("correo2"));
                tutor.setNombre3(rs.getString("nombre3"));
                tutor.setApellidop3(rs.getString("apellidopaterno3"));
                tutor.setApellidom3(rs.getString("apellidomaterno3"));
                tutor.setOcupacion3(rs.getString("ocupacion3"));
                tutor.setParentesco3(rs.getString("parentesco3"));
                tutor.setCalledom3(rs.getString("calledomicilio3"));
                tutor.setNumerodom3(rs.getInt("numerodomicilio3"));
                tutor.setColoniadom3(rs.getString("coloniadomicilio3"));
                tutor.setCodigopostal3(rs.getInt("codigopostal3"));
                tutor.setTelefonocasa3(rs.getString("telefonocasa3"));
                tutor.setCelular3(rs.getString("celular3"));
                tutor.setCorreo3(rs.getString("correo3"));
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
        return tutor;
    }

    /*Consulta que trae la info de un personal en especifico*/
    public TbPersonal datosPersonal(int idpersonal) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        TbPersonal personal = new TbPersonal();
        try {
            String consulta = "select * from tb_personal inner join ct_puesto on tb_personal.r_puesto = ct_puesto.idCt_Puesto where idTb_Personal = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, idpersonal);
            rs = pst.executeQuery();
            while (rs.next()) {
                personal.setIdtbpersonal(rs.getInt("tb_personal.idTb_Personal"));
                personal.setNombre(rs.getString("tb_personal.nombre"));
                personal.setApellidop(rs.getString("tb_personal.apellidopaterno"));
                personal.setApellidom(rs.getString("tb_personal.apellidomaterno"));
                personal.setFechanacimiento(rs.getString("tb_personal.fechanacimiento"));
                personal.setCurp(rs.getString("tb_personal.curp"));
                personal.setMunicipionac(rs.getString("tb_personal.municipionacimiento"));
                personal.setEstadonac(rs.getString("tb_personal.estadonacimiento"));
                personal.setNacionalidad(rs.getString("tb_personal.nacionalidad"));
                personal.setSexo(rs.getBoolean("tb_personal.sexo"));
                personal.setCalledom(rs.getString("tb_personal.calledomicilio"));
                personal.setNumerodom(rs.getInt("tb_personal.numerodomicilio"));
                personal.setColoniadom(rs.getString("tb_personal.coloniadomicilio"));
                personal.setCodigopostal(rs.getInt("tb_personal.codigopostal"));
                personal.setTelefonocasa(rs.getString("tb_personal.telefonocasa"));
                personal.setCelular(rs.getString("tb_personal.celular"));
                personal.setCorreo(rs.getString("tb_personal.correo"));
                personal.setNss(rs.getString("tb_personal.nss"));
                personal.setRfc(rs.getString("tb_personal.rfc"));
                personal.setNivelestudios(rs.getString("tb_personal.nivelmaxestudios"));
                personal.setLicenciatura(rs.getString("tb_personal.licenciatura"));
                personal.setMaestria(rs.getString("tb_personal.maestria"));
                personal.setDoctorado(rs.getString("tb_personal.doctorado"));
                personal.setRpuesto(rs.getInt("tb_personal.r_puesto"));
                personal.setPuesto(rs.getString("ct_puesto.nombre"));
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
        return personal;
    }

    /*Consulta que actualiza datos del alumno*/
    public void actualizaAlumno(TbAlumnos alumno, String ruta) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            String consulta = "UPDATE `tb_alumnos` SET `matricula` = ?, `nombre` = ?, `apellidopaterno` = ?, `apellidomaterno` = ?, `fechanacimiento` = ?, `curp` = ?, \n"
                    + "`municipionacimiento` = ?, `estadonacimiento` = ?, `nacionalidad` = ?, `sexo` = ?, `calledomicilio` = ?, `numerodomicilio` = ?, `coloniadomicilio` = ?,\n"
                    + " `codigopostal` = ?, `telefonocasa` = ?, `celularalumno` = ?, `correoalumno` = ?, `nivelcursa` = ?, `r_grado` = ?, `r_grupo` = ?,\n"
                    + " `r_area` = ?, `r_cpt` = ?, `plantelprocedencia` = ?, `nivelanterior` = ?, `gradoanterior` = ?, `turnoanterior` = ?, `municipioanterior` = ?,`foto` = ? \n"
                    + " WHERE (`idTb_Alumnos` = ?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, alumno.getMatricula());
            pst.setString(2, alumno.getNombre());
            pst.setString(3, alumno.getApellidop());
            pst.setString(4, alumno.getApellidom());
            pst.setString(5, alumno.getFechanacimiento());
            pst.setString(6, alumno.getCurp());
            pst.setString(7, alumno.getMunicipiona());
            pst.setString(8, alumno.getEstadona());
            pst.setString(9, alumno.getNacionalidad());
            pst.setBoolean(10, alumno.getSexo());
            pst.setString(11, alumno.getCalledom());
            pst.setInt(12, alumno.getNumerodom());
            pst.setString(13, alumno.getColoniadom());
            pst.setInt(14, alumno.getCodigopostal());
            pst.setString(15, alumno.getTelefonocasa());
            pst.setString(16, alumno.getCelular());
            pst.setString(17, alumno.getCorreo());
            pst.setString(18, alumno.getNivelcursa());
            pst.setInt(19, alumno.getRgrado());
            pst.setInt(20, alumno.getRgrupo());
            if (alumno.getRarea() != 0) {
                pst.setInt(21, alumno.getRarea());
            } else {
                pst.setString(21, null);
            }
            if (alumno.getRcpt() != 0) {
                pst.setInt(22, alumno.getRcpt());
            } else {
                pst.setString(22, null);
            }
            pst.setString(23, alumno.getPlantelproce());
            pst.setString(24, alumno.getNivelanterior());
            pst.setInt(25, alumno.getGradoanterior());
            pst.setInt(26, alumno.getTurnoanterior());
            pst.setString(27, alumno.getMunicipioante());
            pst.setString(28, ruta);
            pst.setInt(29, alumno.getIdtbalumnos());
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

    /*Actualiza datos del tutor*/
    public void actualizaTutor(TbTutor tutor) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            String consulta = "UPDATE `tb_tutor` SET `nombre` = ?, `apellidopaterno` = ?, `apellidomaterno` = ?, `ocupacion` = ?, `parentesco` = ?,"
                    + " `calledomicilio` = ?, `numerodomicilio` = ?,\n"
                    + " `coloniadomicilio` = ?, `codigopostal` = ?, `telefonocasa` = ?, `celular` = ?, `correo` = ?, `nombre2` = ?, `apellidopaterno2` = ?, `apellidomaterno2` = ?, `ocupacion2` = ?, `parentesco2` = ?,"
                    + " `calledomicilio2` = ?, `numerodomicilio2` = ?,\n"
                    + " `coloniadomicilio2` = ?, `codigopostal2` = ?, `telefonocasa2` = ?, `celular2` = ?, `correo2` = ?, `nombre3` = ?, `apellidopaterno3` = ?, `apellidomaterno3` = ?, `ocupacion3` = ?, `parentesco3` = ?,"
                    + " `calledomicilio3` = ?, `numerodomicilio3` = ?,\n"
                    + " `coloniadomicilio3` = ?, `codigopostal3` = ?, `telefonocasa3` = ?, `celular3` = ?, `correo3` = ? "
                    + " WHERE (`idTb_Tutor` = ?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, tutor.getNombre());
            pst.setString(2, tutor.getApellidop());
            pst.setString(3, tutor.getApellidom());
            pst.setString(4, tutor.getOcupacion());
            pst.setString(5, tutor.getParentesco());
            pst.setString(6, tutor.getCalledom());
            pst.setInt(7, tutor.getNumerodom());
            pst.setString(8, tutor.getColoniadom());
            pst.setInt(9, tutor.getCodigopostal());
            pst.setString(10, tutor.getTelefonocasa());
            pst.setString(11, tutor.getCelular());
            pst.setString(12, tutor.getCorreo());
            pst.setString(13, tutor.getNombre2());
            pst.setString(14, tutor.getApellidop2());
            pst.setString(15, tutor.getApellidom2());
            pst.setString(16, tutor.getOcupacion2());
            pst.setString(17, tutor.getParentesco2());
            pst.setString(18, tutor.getCalledom2());
            pst.setInt(19, tutor.getNumerodom2());
            pst.setString(20, tutor.getColoniadom2());
            pst.setInt(21, tutor.getCodigopostal2());
            pst.setString(22, tutor.getTelefonocasa2());
            pst.setString(23, tutor.getCelular2());
            pst.setString(24, tutor.getCorreo2());
            pst.setString(25, tutor.getNombre3());
            pst.setString(26, tutor.getApellidop3());
            pst.setString(27, tutor.getApellidom3());
            pst.setString(28, tutor.getOcupacion3());
            pst.setString(29, tutor.getParentesco3());
            pst.setString(30, tutor.getCalledom3());
            pst.setInt(31, tutor.getNumerodom3());
            pst.setString(32, tutor.getColoniadom3());
            pst.setInt(33, tutor.getCodigopostal3());
            pst.setString(34, tutor.getTelefonocasa3());
            pst.setString(35, tutor.getCelular3());
            pst.setString(36, tutor.getCorreo3());
            pst.setInt(37, tutor.getIdtbtutor());
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

    /*Actualiza datos del personal en especifico*/
    public void actualizaPersonal(TbPersonal personal) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            String consulta = "UPDATE `tb_personal` SET `nombre` = ?, `apellidopaterno` = ?, `apellidomaterno` = ?, `fechanacimiento` = ?, `curp` = ?, `municipionacimiento` = ?,\n"
                    + " `estadonacimiento` = ?, `nacionalidad` = ?, `sexo` = ?, `calledomicilio` = ?, `numerodomicilio` = ?, `coloniadomicilio` = ?, `codigopostal` = ?, `telefonocasa` = ?, \n"
                    + " `celular` = ?, `correo` = ?, `nss` = ?, `rfc` = ?, `nivelmaxestudios` = ?, `licenciatura` = ?, `maestria` = ?, `doctorado` = ?, `r_puesto` = ? \n"
                    + " WHERE (`idTb_Personal` = ?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, personal.getNombre());
            pst.setString(2, personal.getApellidop());
            pst.setString(3, personal.getApellidom());
            pst.setString(4, personal.getFechanacimiento());
            pst.setString(5, personal.getCurp());
            pst.setString(6, personal.getMunicipionac());
            pst.setString(7, personal.getEstadonac());
            pst.setString(8, personal.getNacionalidad());
            pst.setBoolean(9, personal.getSexo());
            pst.setString(10, personal.getCalledom());
            pst.setInt(11, personal.getNumerodom());
            pst.setString(12, personal.getColoniadom());
            pst.setInt(13, personal.getCodigopostal());
            pst.setString(14, personal.getTelefonocasa());
            pst.setString(15, personal.getCelular());
            pst.setString(16, personal.getCorreo());
            pst.setString(17, personal.getNss());
            pst.setString(18, personal.getRfc());
            pst.setString(19, personal.getNivelestudios());
            pst.setString(20, personal.getLicenciatura());
            pst.setString(21, personal.getMaestria());
            pst.setString(22, personal.getDoctorado());
            pst.setInt(23, personal.getRpuesto());
            pst.setInt(24, personal.getIdtbpersonal());
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

    /*Eliminamos el registro del alumno de manera logica*/
    public void eliminaAlumno(int id) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "update `tb_alumnos` set `status` = 2 where `idTb_Alumnos` = ?";
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

    /*Eliminamos el registro del personal de manera logica*/
    public void eliminaPersonal(int id) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "update `tb_personal` set `status` = 2 where `idTb_Personal` = ?";
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

    /*Consulta para guardar personal*/
    public void guardaPersonal(TbPersonal personal, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into tb_personal (nombre,apellidopaterno,apellidomaterno,fechanacimiento,curp,municipionacimiento,estadonacimiento,nacionalidad,sexo,calledomicilio,"
                    + "numerodomicilio,coloniadomicilio,codigopostal,telefonocasa,celular,correo,nss,rfc,nivelmaxestudios,licenciatura,maestria,doctorado,r_puesto,status) values"
                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, personal.getNombre());
            pst.setString(2, personal.getApellidop());
            pst.setString(3, personal.getApellidom());
            pst.setString(4, personal.getFechanacimiento());
            pst.setString(5, personal.getCurp());
            pst.setString(6, personal.getMunicipionac());
            pst.setString(7, personal.getEstadonac());
            pst.setString(8, personal.getNacionalidad());
            pst.setBoolean(9, personal.getSexo());
            pst.setString(10, personal.getCalledom());
            pst.setInt(11, personal.getNumerodom());
            pst.setString(12, personal.getColoniadom());
            pst.setInt(13, personal.getCodigopostal());
            pst.setString(14, personal.getTelefonocasa());
            pst.setString(15, personal.getCelular());
            pst.setString(16, personal.getCorreo());
            pst.setString(17, personal.getNss());
            pst.setString(18, personal.getRfc());
            pst.setString(19, personal.getNivelestudios());
            pst.setString(20, personal.getLicenciatura());
            pst.setString(21, personal.getMaestria());
            pst.setString(22, personal.getDoctorado());
            pst.setInt(23, personal.getRpuesto());
            pst.setInt(24, 1);

            if (pst.executeUpdate() == 1) {
                con.commit();
                String sql2 = "update tb_personal set tb_personal.matricula = 10000 + tb_personal.idTb_Personal;";
                pst2 = con.prepareStatement(sql2);
                if (pst2.executeUpdate() > 0) {
                    con.commit();
                } else {
                    System.out.println("No se pudo guardar");
                }

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

    /*Consulta para obtener grados*/
    public List<CtGrado> getGrado(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtGrado> listgrado = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_grado where status =1 and tipoescuela = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                CtGrado grado = new CtGrado();
                grado.setIdtbgrado(rs.getInt("idCt_Grado"));
                grado.setNombre(rs.getString("nombre"));
                grado.setStatus(rs.getInt("status"));
                grado.setTipoescuela(rs.getInt("tipoescuela"));
                listgrado.add(grado);
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
        return listgrado;
    }

    /*Consulta para obtener grupos*/
    public List<CtGrupo> getGrupo(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtGrupo> listgrupo = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_grupo where status = 1 and tipoescuela = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                CtGrupo grupo = new CtGrupo();
                grupo.setIdtbgrupo(rs.getInt("idCt_Grupo"));
                grupo.setNombre(rs.getString("nombre"));
                grupo.setStatus(rs.getInt("status"));
                grupo.setTipoescuela(rs.getInt("tipoescuela"));
                listgrupo.add(grupo);
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
        return listgrupo;
    }

    /*Consulta para obtener area*/
    public List<CtAreaalumno> getArea(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtAreaalumno> listarea = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_areaalumno where status = 1 and tipoescuela = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                CtAreaalumno area = new CtAreaalumno();
                area.setIdtbarea(rs.getInt("idCt_AreaAlumno"));
                area.setNombre(rs.getString("nombre"));
                area.setStatus(rs.getInt("status"));
                area.setTipoescuela(rs.getInt("tipoescuela"));
                listarea.add(area);
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
        return listarea;

    }

    /*Consulta para obtener cpt*/
    public List<CtCptalumno> getCpt(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtCptalumno> listcpt = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_cptalumno where status = 1 and tipoescuela = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                CtCptalumno cpt = new CtCptalumno();
                cpt.setIdtbcpt(rs.getInt("idCt_CptAlumno"));
                cpt.setNombre(rs.getString("nombre"));
                cpt.setStatus(rs.getInt("status"));
                cpt.setTipoescuela(rs.getInt("tipoescuela"));
                listcpt.add(cpt);
            }

        } catch (SQLException e) {
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
        return listcpt;
    }

    /*Consulta pra obtener puesto*/
    public List<CtPuesto> getPuesto(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtPuesto> listpuesto = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_puesto where status = 1 and tipoescuela = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                CtPuesto puesto = new CtPuesto();
                puesto.setIdtbpuesto(rs.getInt("idCt_Puesto"));
                puesto.setNombre(rs.getString("nombre"));
                puesto.setStatus(rs.getInt("status"));
                puesto.setTipoescuela(rs.getInt("tipoescuela"));
                listpuesto.add(puesto);
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
        return listpuesto;
    }

    public List<GradoGrupo> getGradoGrupo(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<GradoGrupo> listgradogrupo = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "SELECT * FROM ct_grupo, ct_grado where ct_grado.status = 1 and ct_grado.tipoescuela = 1 and ct_grupo.status = 1 and ct_grupo.tipoescuela = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                GradoGrupo gradogrupo = new GradoGrupo();
                gradogrupo.setIdgrado(rs.getInt("ct_grado.idCt_Grado"));
                gradogrupo.setIdgrupo(rs.getInt("ct_grupo.idCt_Grupo"));
                gradogrupo.setGrado(rs.getString("ct_grado.nombre"));
                gradogrupo.setGrupo(rs.getString("ct_grupo.nombre"));
                gradogrupo.setStatus(rs.getInt("ct_grado.status"));
                listgradogrupo.add(gradogrupo);
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
        return listgradogrupo;
    }

    /*Consult para obtener tupo de calificacion*/
    public List<CtTipoCalificaicon> getTipoCali(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtTipoCalificaicon> listtipocali = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_tipocalificacion where status = 1 and tipoescuela = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                CtTipoCalificaicon tipocali = new CtTipoCalificaicon();
                tipocali.setIdtbtipocali(rs.getInt("idCt_TipoCalificacion"));
                tipocali.setNombre(rs.getString("nombre"));
                tipocali.setStatus(rs.getInt("status"));
                tipocali.setTipoescuela(rs.getInt("tipoescuela"));
                listtipocali.add(tipocali);
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
        return listtipocali;
    }

    /*Consulta pra guardr puesto*/
    public void guardaPuesto(CtPuesto puesto, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into ct_puesto (nombre,status,tipoescuela) values (?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, puesto.getNombre());
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

    public void actualizaPuesto(CtPuesto puesto) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "UPDATE `terranova`.`ct_puesto` SET `nombre` = ? WHERE (`idCt_Puesto` = ?);";
            pst = con.prepareStatement(consulta);
            pst.setString(1, puesto.getNombre());
            pst.setInt(2, puesto.getIdtbpuesto());

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

    /*Eliminamos registro de manera logica*/
    public void eliminaPuesto(int id) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "update `ct_puesto` set `status` = 2 where `idCt_Puesto` = ?";
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

    /*Consulta para guardar periodo*/
    public void guardaPeriodo(CtPeriodoEscolar periodo, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into ct_periodoescolar (nombre,fechainicio,fechafin,status,tipoescuela) values (?,?,?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, periodo.getNombre());
            pst.setString(2, periodo.getFechainicio());
            pst.setString(3, periodo.getFechafin());
            pst.setInt(4, 1);
            pst.setInt(5, tipoescuela);

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

    public void actualizaPeriodo(CtPeriodoEscolar periodo) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "UPDATE `terranova`.`ct_periodoescolar` SET `nombre` = ?, `fechainicio` = ?, `fechafin` = ? WHERE (`idCt_PeriodoEscolar` = ?);";
            pst = con.prepareStatement(consulta);
            pst.setString(1, periodo.getNombre());
            pst.setString(2, periodo.getFechainicio());
            pst.setString(3, periodo.getFechafin());
            pst.setInt(2, periodo.getIdtbperiodo());

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

    /*Eliminamos registro de manera logica*/
    public void eliminaPeriodo(int id) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "update `ct_periodoescolar` set `status` = 2 where `idCt_PeriodoEscolar` = ?";
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

    /*Consulta para guardar área*/
    public void guardaArea(CtAreaalumno area, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into ct_areaalumno (nombre,status,tipoescuela) values (?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, area.getNombre());
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

    public void actualizaArea(CtAreaalumno area) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "UPDATE `terranova`.`ct_areaalumno` SET `nombre` = ? WHERE (`idCt_AreaAlumno` = ?);";
            pst = con.prepareStatement(consulta);
            pst.setString(1, area.getNombre());
            pst.setInt(2, area.getIdtbarea());

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

    /*Eliminamos registro de manera logica*/
    public void eliminaArea(int id) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "update `ct_areaalumno` set `status` = 2 where `idCt_AreaAlumno` = ?";
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

    /*Consulta para guardar cpt*/
    public void guardaCpt(CtCptalumno cpt, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into ct_cptalumno (nombre,status,tipoescuela) values (?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, cpt.getNombre());
            pst.setInt(2, 1);
            pst.setInt(3, tipoescuela);

            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al guardar");
            }
        } catch (SQLException e) {
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

    public void actualizaCpt(CtCptalumno cpt) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "UPDATE `terranova`.`ct_cptalumno` SET `nombre` = ? WHERE (`idCt_CptAlumno` = ?);";
            pst = con.prepareStatement(consulta);
            pst.setString(1, cpt.getNombre());
            pst.setInt(2, cpt.getIdtbcpt());

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

    /*Eliminamos regisgtro de manera logica*/
    public void eliminaCpt(int id) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "update `ct_cptalumno` set `status` = 2 where `idCt_CptAlumno` = ?";
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

    /*Consulta que guarda grado*/
    public void guardaGrado(CtGrado grado, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into ct_grado (nombre,status,tipoescuela) values (?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, grado.getNombre());
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

    /*Consulta que guarda grupo*/
    public void guardaGrupo(CtGrupo grupo, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into ct_grupo (nombre,status,tipoescuela) values (?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, grupo.getNombre());
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

    /*Consulta que guarda tipo calificacion*/
    public void guardaTipoCali(CtTipoCalificaicon tipocali, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into ct_tipocalificacion (nombre,status,tipoescuela) values (?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, tipocali.getNombre());
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

    public void actualizaTipoCali(CtTipoCalificaicon tipocali) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "UPDATE `terranova`.`ct_tipocalificacion` SET `nombre` = ? WHERE (`idCt_TipoCalificacion` = ?);";
            pst = con.prepareStatement(consulta);
            pst.setString(1, tipocali.getNombre());
            pst.setInt(2, tipocali.getIdtbtipocali());

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

    /*Eliminamos registro de manera logica*/
    public void eliminaTipoCali(int id) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "update `ct_tipocalificacion` set `status` = 2 where `idCt_TipoCalificacion` = ?";
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

    /*Consulta pra traer las materias que aun no estan asociadas a grado o grupo*/
    public List<CtDatosMateria> getMateriasFaltantes(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtDatosMateria> listmateriafaltante = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select ct_datosmateria.nombrelargo, ct_datosmateria.nombrecorto, tb_materia.r_datosmateria, ct_datosmateria.idCt_DatosMateria from \n"
                    + "tb_materia right join ct_datosmateria on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria where tb_materia.r_datosmateria IS NULL\n"
                    + "and ct_datosmateria.tipoescuela = ? and ct_datosmateria.status = 1";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                CtDatosMateria materiafaltante = new CtDatosMateria();
                materiafaltante.setIdtbdatosmateria(rs.getInt("ct_datosmateria.idCt_DatosMateria"));
                materiafaltante.setNombrecorto(rs.getString("ct_datosmateria.nombrecorto"));
                materiafaltante.setNombrelargo(rs.getString("ct_datosmateria.nombrelargo"));
                listmateriafaltante.add(materiafaltante);
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
        return listmateriafaltante;
    }

    /*Comsulta para guardar nombre de una materia*/
    public void guardaNombreMateria(CtDatosMateria nombremateria, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into ct_datosmateria (nombrelargo,nombrecorto,status,tipoescuela) values (?,?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, nombremateria.getNombrelargo());
            pst.setString(2, nombremateria.getNombrecorto());
            pst.setInt(3, 1);
            pst.setInt(4, tipoescuela);

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

    /*Consulta que guarda materias asociandolas con grado, grupo, area o cpt*/
    public void guardaMateria(TbMateria materia, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into tb_materia (r_datosmateria,r_grado,r_grupo,r_area,r_cpt,status,tipoescuela) values (?,?,?,?,?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, materia.getRdatosmateria());
            if (materia.getRgrado() != 0) {
                pst.setInt(2, materia.getRgrado());
            } else {
                pst.setString(2, null);
            }
            if (materia.getRgrupo() != 0) {
                pst.setInt(3, materia.getRgrupo());
            } else {
                pst.setString(3, null);
            }
            if (materia.getRarea() != 0) {
                pst.setInt(4, materia.getRarea());
            } else {
                pst.setString(4, null);
            }
            if (materia.getRcpt() != 0) {
                pst.setInt(5, materia.getRcpt());
            } else {
                pst.setString(5, null);
            }
            pst.setInt(6, 1);
            pst.setInt(7, tipoescuela);

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

    /*Eliminamos registro de manera logica*/
    public void eliminaMateria(int id) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "update `tb_materia` set `status` = 2 where `idTb_Materia` = ?";
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

    /*Consulta para asignar un conjunto de materias a un profesor*/
    public void asignaMateria(int id, List<TbMateriaPersonal> materiapersonal, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            for (TbMateriaPersonal item : materiapersonal) {
                con.setAutoCommit(false);
                String consulta = "insert into tb_materiapersonal (r_materia,r_personal,r_periodo,status,tipoescuela) values (?,?,?,?,?)";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, item.getIdtbmateriapersonal());
                pst.setInt(2, id);
                pst.setInt(3, item.getR_periodo());
                pst.setInt(4, 1);
                pst.setInt(5, tipoescuela);

                if (pst.executeUpdate() == 1) {
                    con.commit();
                } else {
                    System.out.println("no se pudo guardar");
                }
                pst = null;
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

    /*Consulta para obtener las materias asociadas a un profesor*/
    public List<TbMateriaPersonal> getMateriasAPersonal(int tipoescuela, int idpersonal) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TbMateriaPersonal> personalmateria = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select tb_materiapersonal.idTb_MateriaPersonal,tb_materia.r_grado,tb_materia.r_grupo,tb_materia.r_area,tb_materia.r_cpt, ct_datosmateria.nombrecorto, tb_materiapersonal.r_periodo from tb_materiapersonal inner join tb_materia on tb_materiapersonal.r_materia = tb_materia.idTb_Materia\n"
                    + "inner join ct_datosmateria on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria \n"
                    + "where tb_materiapersonal.r_personal = ? and tb_materiapersonal.status = 1 and tb_materiapersonal.tipoescuela = ?;";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, idpersonal);
            pst.setInt(2, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                TbMateriaPersonal materia = new TbMateriaPersonal();
                materia.setIdtbmateriapersonal(rs.getInt("tb_materiapersonal.idTb_MateriaPersonal"));
                materia.setMateria(rs.getString("ct_datosmateria.nombrecorto"));
                materia.setR_periodo(rs.getInt("tb_materiapersonal.r_periodo"));
                materia.setR_grado(rs.getInt("tb_materia.r_grado"));
                materia.setR_grupo(rs.getInt("tb_materia.r_grupo"));
                materia.setR_area(rs.getInt("tb_materia.r_area"));
                materia.setR_cpt(rs.getInt("tb_materia.r_cpt"));
                personalmateria.add(materia);
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
        return personalmateria;
    }

    /*Consulta para asociar alumnos a materias de profesores*/
    public void asignaAlumnos(int tipoescuela, List<TbMateriaAlumno> listmateriaalum) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            for (TbMateriaAlumno item : listmateriaalum) {
                con.setAutoCommit(false);
                String consulta = "insert into tb_materiaalumno (r_materiapersonal,r_alumno,status,tipoescuela) values (?,?,?,?)";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, item.getR_materiapersonal());
                pst.setInt(2, item.getR_alumno());
                pst.setInt(3, 1);
                pst.setInt(4, tipoescuela);

                if (pst.executeUpdate() == 1) {
                    con.commit();
                } else {
                    System.out.println("no se pudo guardar");
                }
                pst = null;
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

    /*Consulta para obtener las materias de un alumno*/
    public List<TbMateriaAlumno> getMateriasAlum(int idalumno, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TbMateriaAlumno> listmateriasalum = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select tb_materiaalumno.idtb_materiaalumno, ct_datosmateria.nombrecorto, ct_datosmateria.nombrelargo from tb_materiaalumno inner join tb_materiapersonal on\n"
                    + "tb_materiaalumno.r_materiapersonal = tb_materiapersonal.idTb_MateriaPersonal inner join\n"
                    + "tb_materia on tb_materiapersonal.r_materia = tb_materia.idTb_Materia inner join\n"
                    + "ct_datosmateria on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria where\n"
                    + " tb_materiaalumno.r_alumno = ? and tb_materiaalumno.status = 1 and tb_materiaalumno.tipoescuela = ? and idtb_materiaalumno not in (\n"
                    + "	select r_materiaalumno from tb_horario inner join tb_materiaalumno on tb_horario.r_materiaalumno = tb_materiaalumno.idtb_materiaalumno\n"
                    + "	where tb_materiaalumno.r_alumno = ?\n"
                    + ");";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, idalumno);
            pst.setInt(2, tipoescuela);
            pst.setInt(3, idalumno);
            rs = pst.executeQuery();
            while (rs.next()) {
                TbMateriaAlumno materiaalum = new TbMateriaAlumno();
                materiaalum.setIdtbmateriaalumno(rs.getInt("tb_materiaalumno.idtb_materiaalumno"));
                materiaalum.setNombrecorto(rs.getString("ct_datosmateria.nombrecorto"));
                materiaalum.setNombrelargo(rs.getString("ct_datosmateria.nombrelargo"));
                listmateriasalum.add(materiaalum);
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
        return listmateriasalum;
    }

    public void asignaHorario(TbHorario horario, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into tb_horario (r_materiaalumno,r_periodo,hora,status,tipoescuela) values (?,?,?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, horario.getR_materiaalumno());
            pst.setInt(2, horario.getR_periodo());
            pst.setString(3, horario.getHora());
            pst.setInt(4, 1);
            pst.setInt(5, tipoescuela);

            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("no se pudo guardar");
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

    /*Consulta para guardar la asistencia tomada*/
    public void guardaAsistencia(List<TbAsistencia> listasistencia, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            for (TbAsistencia item : listasistencia) {
                con.setAutoCommit(false);
                String consulta = "insert into tb_asistencia (r_materiaalumno,r_dia,r_semanafiscal,r_periodo,asistencia,status,tipoescuela) values (?,?,?,?,?,?,?)";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, item.getR_materiaalumno());
                pst.setInt(2, item.getR_dia());
                pst.setInt(3, item.getR_semana());
                pst.setInt(4, item.getR_periodo());
                pst.setInt(5, item.getAsistencia());
                pst.setInt(6, 1);
                pst.setInt(7, tipoescuela);

                if (pst.executeUpdate() == 1) {
                    con.commit();
                } else {
                    System.out.println("no se pudo guardar");
                }
                pst = null;
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

    /*Consulta para guardar la informacion importada sobre el personal*/
    public void guardaImportaPersonal(List<TbPersonal> listpersonal) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        try {
            for (TbPersonal item : listpersonal) {
                con.setAutoCommit(false);
                String consulta = "insert into tb_personal (nombre,apellidopaterno,apellidomaterno,fechanacimiento,curp,municipionacimiento,estadonacimiento,nacionalidad,sexo,calledomicilio,"
                        + "numerodomicilio,coloniadomicilio,codigopostal,telefonocasa,celular,correo,nss,rfc,nivelmaxestudios,licenciatura,maestria,doctorado,r_puesto,status,tipoescuela) values"
                        + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                pst = con.prepareStatement(consulta);
                pst.setString(1, item.getNombre());
                pst.setString(2, item.getApellidop());
                pst.setString(3, item.getApellidom());
                pst.setString(4, item.getFechanacimiento());
                pst.setString(5, item.getCurp());
                pst.setString(6, item.getMunicipionac());
                pst.setString(7, item.getEstadonac());
                pst.setString(8, item.getNacionalidad());
                pst.setBoolean(9, item.getSexo());
                pst.setString(10, item.getCalledom());
                pst.setInt(11, item.getNumerodom());
                pst.setString(12, item.getColoniadom());
                pst.setInt(13, item.getCodigopostal());
                pst.setString(14, item.getTelefonocasa());
                pst.setString(15, item.getCelular());
                pst.setString(16, item.getCorreo());
                pst.setString(17, item.getNss());
                pst.setString(18, item.getRfc());
                pst.setString(19, item.getNivelestudios());
                pst.setString(20, item.getLicenciatura());
                pst.setString(21, item.getMaestria());
                pst.setString(22, item.getDoctorado());
                pst.setInt(23, item.getRpuesto());
                pst.setInt(24, 1);
                pst.setInt(25, item.getTipoescuela());

                if (pst.executeUpdate() == 1) {
                    con.commit();
                    String sql2 = "update tb_personal set tb_personal.matricula = 10000 + tb_personal.idTb_Personal;";
                    pst2 = con.prepareStatement(sql2);
                    if (pst2.executeUpdate() > 0) {
                        con.commit();
                    } else {
                        System.out.println("No se pudo guardar");
                    }

                } else {
                    System.out.println("Error al guardar");
                }
                pst = null;
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

    /*Consulta para guardar la informacion importada sobre el alumno*/
    public void guardaImportaAlumnos(List<TbAlumnos> listalumnos) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        int tutores = 1;
        int contador = 1;
        try {
            con.setAutoCommit(false);
            for (TbAlumnos item : listalumnos) {
                String consulta = "insert into tb_alumnos (matricula,nombre,apellidopaterno,apellidomaterno,fechanacimiento,curp,municipionacimiento,estadonacimiento,\n"
                        + "nacionalidad,sexo,calledomicilio,numerodomicilio,coloniadomicilio,codigopostal,telefonocasa,celularalumno,correoalumno,nivelcursa,\n"
                        + "r_grado,r_grupo,r_area,r_cpt,plantelprocedencia,nivelanterior,gradoanterior,turnoanterior,municipioanterior,r_tutor,status,tipoescuela) values (?,?,?,?,?,?,?,?"
                        + ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                pst = con.prepareStatement(consulta);
                pst.setString(1, item.getMatricula());
                pst.setString(2, item.getNombre());
                pst.setString(3, item.getApellidop());
                if (item.getApellidom() == "0") {
                    pst.setString(4, null);
                } else {
                    pst.setString(4, item.getApellidom());
                }
                pst.setString(5, item.getFechanacimiento());
                pst.setString(6, item.getCurp());
                pst.setString(7, item.getMunicipiona());
                pst.setString(8, item.getEstadona());
                pst.setString(9, item.getNacionalidad());
                pst.setBoolean(10, item.getSexo());
                pst.setString(11, item.getCalledom());
                pst.setInt(12, item.getNumerodom());
                pst.setString(13, item.getColoniadom());
                pst.setInt(14, item.getCodigopostal());
                pst.setString(15, item.getTelefonocasa());
                pst.setString(16, item.getCelular());
                pst.setString(17, item.getCorreo());
                pst.setString(18, item.getNivelcursa());
                pst.setInt(19, item.getRgrado());
                pst.setInt(20, item.getRgrupo());
                if (item.getRarea() != 0) {
                    pst.setInt(21, item.getRarea());
                } else {
                    pst.setString(21, null);
                }
                if (item.getRcpt() != 0) {
                    pst.setInt(22, item.getRcpt());
                } else {
                    pst.setString(22, null);
                }
                pst.setString(23, item.getPlantelproce());
                pst.setString(24, item.getNivelanterior());
                pst.setInt(25, item.getGradoanterior());
                pst.setInt(26, item.getTurnoanterior());
                pst.setString(27, item.getMunicipioante());
                pst.setInt(28, tutores);
                pst.setInt(29, 1);
                pst.setInt(30, item.getTipoescuela());

                if (pst.executeUpdate() == 1) {
                    con.commit();
                    String sql2 = "update tb_alumnos set tb_alumnos.matricula = 10000 + tb_alumnos.idTb_Alumnos;";
                    pst2 = con.prepareStatement(sql2);
                    if (pst2.executeUpdate() > 0) {
                        con.commit();
                    } else {
                        System.out.println("No se pudo guardar");
                    }
                } else {
                    System.out.println("Error al guardar");
                }
                pst = null;
                tutores++;
                contador++;
            }
            System.out.println(contador);
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

    /*Consulta para guardar la informacion importada sobre el tutor*/
    public void guardaImportaTutor(List<TbTutor> listtutor) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        try {
            for (TbTutor item : listtutor) {
                con.setAutoCommit(false);
                String consulta = "insert into tb_tutor (nombre,apellidopaterno,apellidomaterno,ocupacion,parentesco,calledomicilio,numerodomicilio,\n"
                        + "coloniadomicilio,codigopostal,telefonocasa,celular,correo,nombre2,apellidopaterno2,apellidomaterno2,ocupacion2,parentesco2,calledomicilio2,numerodomicilio2,\n"
                        + "coloniadomicilio2,codigopostal2,telefonocasa2,celular2,correo2, nombre3,apellidopaterno3,apellidomaterno3,ocupacion3,parentesco3,calledomicilio3,numerodomicilio3,\n"
                        + "coloniadomicilio3,codigopostal3,telefonocasa3,celular3,correo3, status,tipoescuela) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                pst = con.prepareStatement(consulta);
                pst.setString(1, item.getNombre());
                pst.setString(2, item.getApellidop());
                pst.setString(3, item.getApellidom());
                pst.setString(4, item.getOcupacion());
                pst.setString(5, item.getParentesco());
                pst.setString(6, item.getCalledom());
                pst.setInt(7, item.getNumerodom());
                pst.setString(8, item.getColoniadom());
                pst.setInt(9, item.getCodigopostal());
                pst.setString(10, item.getTelefonocasa());
                pst.setString(11, item.getCelular());
                pst.setString(12, item.getCorreo());
                pst.setString(13, item.getNombre2());
                pst.setString(14, item.getApellidop2());
                pst.setString(15, item.getApellidom2());
                pst.setString(16, item.getOcupacion2());
                pst.setString(17, item.getParentesco2());
                pst.setString(18, item.getCalledom2());
                pst.setInt(19, item.getNumerodom2());
                pst.setString(20, item.getColoniadom2());
                pst.setInt(21, item.getCodigopostal2());
                pst.setString(22, item.getTelefonocasa2());
                pst.setString(23, item.getCelular2());
                pst.setString(24, item.getCorreo2());
                pst.setString(25, item.getNombre3());
                pst.setString(26, item.getApellidop3());
                pst.setString(27, item.getApellidom3());
                pst.setString(28, item.getOcupacion3());
                pst.setString(29, item.getParentesco3());
                pst.setString(30, item.getCalledom3());
                pst.setInt(31, item.getNumerodom3());
                pst.setString(32, item.getColoniadom3());
                pst.setInt(33, item.getCodigopostal3());
                pst.setString(34, item.getTelefonocasa3());
                pst.setString(35, item.getCelular3());
                pst.setString(36, item.getCorreo3());
                pst.setInt(37, 1);
                pst.setInt(38, item.getTipoescuela());

                if (pst.executeUpdate() == 1) {
                    con.commit();
                    String sql2 = "update tb_tutor set tb_tutor.matricula = 10000 + tb_tutor.idTb_Tutor;";
                    pst2 = con.prepareStatement(sql2);
                    if (pst2.executeUpdate() > 0) {
                        con.commit();
                    } else {
                        System.out.println("No se pudo guardar");
                    }
                } else {
                    System.out.println("Error al guardar");
                }
                pst = null;
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

    /*Consulta para obtener el correo del personal, se usa pra enviar algunos mensajes de otras funciones*/
    public String correoUsuario(int id) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String correo = null;
        try {
            con.setAutoCommit(false);
            String consulta = "select tb_personal.correo from tb_personal where tb_personal.idTb_Personal = ?;";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                correo = (rs.getString("tb_personal.correo"));
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
        return correo;
    }

    /*COnsulta para obtener los dias*/
    public List<CtDia> getDias(int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtDia> listdia = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_dia where status = 1 and tipoescuela = ?;";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                CtDia dia = new CtDia();
                dia.setIdtbdia(rs.getInt("ct_dia.idCt_Dia"));
                dia.setNombre(rs.getString("ct_dia.nombre"));
                listdia.add(dia);
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
        return listdia;
    }

    /*Consulta para traer todas las faltas de los alumnos de cualquier materia, agrupandolos para no repetir registros, las condiciones son para
    determinar que ocnsulta usar dependiendo los parametros que llegan*/
    public List<TbAsistencia> getreporteGAsistencia(int idperiodo, int idsemana, int idgrado, int idgrupo, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TbAsistencia> listasistencia = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            if (idgrupo == 0) {
                String consulta = "select tb_asistencia.idtb_asistencia, count(tb_asistencia.asistencia) as faltas,group_concat(ct_dia.nombre) as dias,ct_datosmateria.nombrelargo,ct_datosmateria.idCt_DatosMateria,tb_alumnos.idTb_Alumnos,  tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno\n"
                        + "						from tb_asistencia inner join tb_materiaalumno\n"
                        + "						on tb_asistencia.r_materiaalumno = tb_materiaalumno.idtb_materiaalumno inner join ct_semanafiscal\n"
                        + "                        on tb_asistencia.r_semanafiscal = ct_semanafiscal.idCt_SemanaFiscal inner join ct_dia\n"
                        + "                        on tb_asistencia.r_dia = ct_dia.idCt_Dia inner join ct_periodoescolar\n"
                        + "                        on tb_asistencia.r_periodo = ct_periodoescolar.idCt_PeriodoEscolar inner join tb_alumnos\n"
                        + "                        on tb_materiaalumno.r_alumno = tb_alumnos.idTb_Alumnos inner join tb_materiapersonal\n"
                        + "                        on tb_materiaalumno.r_materiapersonal = tb_materiapersonal.idTb_MateriaPersonal inner join tb_personal\n"
                        + "                        on tb_materiapersonal.r_personal = tb_personal.idTb_Personal inner join tb_materia\n"
                        + "                        on tb_materiapersonal.r_materia = tb_materia.idTb_Materia inner join ct_datosmateria\n"
                        + "                        on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria\n"
                        + "                       where tb_asistencia.r_periodo = ? and  tb_materia.r_grado = ? and tb_asistencia.r_semanafiscal = ? and\n"
                        + "                       tb_asistencia.status = 1 and tb_asistencia.tipoescuela = ? and tb_asistencia.asistencia = 0\n"
                        + "					   group by tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno, ct_datosmateria.nombrelargo, tb_personal.Nombre, tb_personal.apellidopaterno\n"
                        + "                       order by tb_alumnos.apellidopaterno asc;";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, idperiodo);
                pst.setInt(2, idgrado);
                pst.setInt(3, idsemana);
                pst.setInt(4, tipoescuela);
                rs = pst.executeQuery();
                while (rs.next()) {
                    TbAsistencia asistencia = new TbAsistencia();
                    asistencia.setIdtbasistencia(rs.getInt("tb_asistencia.idtb_asistencia"));
                    asistencia.setNombrealum(rs.getString("tb_alumnos.nombre"));
                    asistencia.setApellidopa(rs.getString("tb_alumnos.apellidopaterno"));
                    asistencia.setApellidoma(rs.getString("tb_alumnos.apellidomaterno"));
                    asistencia.setNombrelargo(rs.getString("ct_datosmateria.nombrelargo"));
                    asistencia.setDia(rs.getString("dias"));
                    asistencia.setAsistencia(rs.getInt("faltas"));
                    asistencia.setR_materia(rs.getInt("ct_datosmateria.idCt_DatosMateria"));
                    asistencia.setR_alumno(rs.getInt("tb_alumnos.idTb_Alumnos"));
                    listasistencia.add(asistencia);
                }
            } else {
                String consulta = "select tb_asistencia.idtb_asistencia, count(tb_asistencia.asistencia) as faltas,group_concat(ct_dia.nombre) as dias,ct_datosmateria.nombrelargo,ct_datosmateria.idCt_DatosMateria,tb_alumnos.idTb_Alumnos,  tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno\n"
                        + "						from tb_asistencia inner join tb_materiaalumno\n"
                        + "						on tb_asistencia.r_materiaalumno = tb_materiaalumno.idtb_materiaalumno inner join ct_semanafiscal\n"
                        + "                        on tb_asistencia.r_semanafiscal = ct_semanafiscal.idCt_SemanaFiscal inner join ct_dia\n"
                        + "                        on tb_asistencia.r_dia = ct_dia.idCt_Dia inner join ct_periodoescolar\n"
                        + "                        on tb_asistencia.r_periodo = ct_periodoescolar.idCt_PeriodoEscolar inner join tb_alumnos\n"
                        + "                        on tb_materiaalumno.r_alumno = tb_alumnos.idTb_Alumnos inner join tb_materiapersonal\n"
                        + "                        on tb_materiaalumno.r_materiapersonal = tb_materiapersonal.idTb_MateriaPersonal inner join tb_personal\n"
                        + "                        on tb_materiapersonal.r_personal = tb_personal.idTb_Personal inner join tb_materia\n"
                        + "                        on tb_materiapersonal.r_materia = tb_materia.idTb_Materia inner join ct_datosmateria\n"
                        + "                        on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria\n"
                        + "                       where tb_asistencia.r_periodo = ? and  tb_materia.r_grado = ? and  tb_materia.r_grupo = ? and tb_asistencia.r_semanafiscal = ? and\n"
                        + "                       tb_asistencia.status = 1 and tb_asistencia.tipoescuela = ? and tb_asistencia.asistencia = 0\n"
                        + "					   group by tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno, ct_datosmateria.nombrelargo, tb_personal.Nombre, tb_personal.apellidopaterno\n"
                        + "                       order by tb_alumnos.apellidopaterno asc;";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, idperiodo);
                pst.setInt(2, idgrado);
                pst.setInt(3, idgrupo);
                pst.setInt(4, idsemana);
                pst.setInt(5, tipoescuela);
                rs = pst.executeQuery();
                while (rs.next()) {
                    TbAsistencia asistencia = new TbAsistencia();
                    asistencia.setIdtbasistencia(rs.getInt("tb_asistencia.idtb_asistencia"));
                    asistencia.setNombrealum(rs.getString("tb_alumnos.nombre"));
                    asistencia.setApellidopa(rs.getString("tb_alumnos.apellidopaterno"));
                    asistencia.setApellidoma(rs.getString("tb_alumnos.apellidomaterno"));
                    asistencia.setNombrelargo(rs.getString("ct_datosmateria.nombrelargo"));
                    asistencia.setDia(rs.getString("dias"));
                    asistencia.setAsistencia(rs.getInt("faltas"));
                    asistencia.setR_materia(rs.getInt("ct_datosmateria.idCt_DatosMateria"));
                    asistencia.setR_alumno(rs.getInt("tb_alumnos.idTb_Alumnos"));
                    listasistencia.add(asistencia);
                }
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

    /*Consulta que trae todas las meterias de acuerdo a los parametros dados, la condicion define que consulta usar de acuerdo a que parametro viene vacio*/
    public List<CtDatosMateria> getMateriasAsistencia(int idgrado, int idgrupo, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtDatosMateria> listmaterias = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            if (idgrupo == 0) {
                String consulta = "SELECT ct_datosmateria.idCt_DatosMateria, ct_datosmateria.nombrelargo FROM tb_materia inner join ct_datosmateria \n"
                        + "on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria\n"
                        + "where tb_materia.r_grado = ? and tb_materia.tipoescuela = ?;";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, idgrado);
                pst.setInt(2, tipoescuela);
                rs = pst.executeQuery();
                while (rs.next()) {
                    CtDatosMateria materia = new CtDatosMateria();
                    materia.setIdtbdatosmateria(rs.getInt("ct_datosmateria.idCt_DatosMateria"));
                    materia.setNombrelargo(rs.getString("ct_datosmateria.nombrelargo"));
                    listmaterias.add(materia);
                }
            } else {
                String consulta = "SELECT ct_datosmateria.idCt_DatosMateria, ct_datosmateria.nombrelargo FROM tb_materia inner join ct_datosmateria \n"
                        + "on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria\n"
                        + "where tb_materia.r_grado = ? and  tb_materia.r_grupo = ? and tb_materia.tipoescuela = ?;";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, idgrado);
                pst.setInt(2, idgrupo);
                pst.setInt(3, tipoescuela);
                rs = pst.executeQuery();
                while (rs.next()) {
                    CtDatosMateria materia = new CtDatosMateria();
                    materia.setIdtbdatosmateria(rs.getInt("ct_datosmateria.idCt_DatosMateria"));
                    materia.setNombrelargo(rs.getString("ct_datosmateria.nombrelargo"));
                    listmaterias.add(materia);
                }
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
        return listmaterias;
    }

    /*Consulta que trae los alumnos de acuerdo a los parametros, la condicion define que consulta de usrara con respecto a los parametros */
    public List<Alumno> getAlumnosAsistencia(int idgrado, int idgrupo, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Alumno> listalumno = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            if (idgrupo == 0) {
                String consulta = "SELECT tb_alumnos.idTb_Alumnos, tb_alumnos.nombre,tb_alumnos.apellidopaterno,tb_alumnos.apellidomaterno FROM tb_alumnos\n"
                        + "where tb_alumnos.r_grado = ? and tb_alumnos.tipoescuela = ?;";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, idgrado);
                pst.setInt(2, tipoescuela);
                rs = pst.executeQuery();
                while (rs.next()) {
                    Alumno alumno = new Alumno();
                    alumno.setId(rs.getInt("tb_alumnos.idTb_Alumnos"));
                    alumno.setNombre(rs.getString("tb_alumnos.nombre"));
                    alumno.setApellidop(rs.getString("tb_alumnos.apellidopaterno"));
                    alumno.setApellidom(rs.getString("tb_alumnos.apellidomaterno"));
                    listalumno.add(alumno);
                }
            } else {
                String consulta = "SELECT tb_alumnos.idTb_Alumnos, tb_alumnos.nombre,tb_alumnos.apellidopaterno,tb_alumnos.apellidomaterno FROM tb_alumnos\n"
                        + "where tb_alumnos.r_grado = ? and tb_alumnos.r_grupo = ? and tb_alumnos.tipoescuela = ?;";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, idgrado);
                pst.setInt(2, idgrupo);
                pst.setInt(3, tipoescuela);
                rs = pst.executeQuery();
                while (rs.next()) {
                    Alumno alumno = new Alumno();
                    alumno.setId(rs.getInt("tb_alumnos.idTb_Alumnos"));
                    alumno.setNombre(rs.getString("tb_alumnos.nombre"));
                    alumno.setApellidop(rs.getString("tb_alumnos.apellidopaterno"));
                    alumno.setApellidom(rs.getString("tb_alumnos.apellidomaterno"));
                    listalumno.add(alumno);
                }
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

    /*Consulta que trae una lista de manerias en donde el alumno ha faltado*/
    public List<CtDatosMateria> justificarFaltas(int idalumno, int idsemana, int idperiodo, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtDatosMateria> listmaterias = new ArrayList<>();
        Alumno alumno = new Alumno();
        try {
            con.setAutoCommit(false);
            String consulta = "SELECT tb_materiaalumno.idtb_materiaalumno, ct_datosmateria.nombrelargo, tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno FROM tb_asistencia inner join tb_materiaalumno \n"
                    + "					on tb_asistencia.r_materiaalumno = tb_materiaalumno.idtb_materiaalumno inner join tb_materiapersonal\n"
                    + "                    on tb_materiaalumno.r_materiapersonal = tb_materiapersonal.idTb_MateriaPersonal inner join tb_materia\n"
                    + "                    on tb_materiapersonal.r_materia = tb_materia.idTb_Materia inner join ct_datosmateria\n"
                    + "                    on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria inner join tb_alumnos\n"
                    + "                    on tb_materiaalumno.r_alumno = tb_alumnos.idTb_Alumnos\n"
                    + "                    where tb_materiaalumno.r_alumno = ? and tb_asistencia.status = 1 and tb_asistencia.tipoescuela = ? and tb_asistencia.asistencia = 0 and tb_asistencia.r_semanafiscal = ? and tb_asistencia.r_periodo = ?\n"
                    + "                    group by tb_materiaalumno.idtb_materiaalumno, ct_datosmateria.nombrelargo, tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno;";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, idalumno);
            pst.setInt(2, tipoescuela);
            pst.setInt(3, idsemana);
            pst.setInt(4, idperiodo);
            rs = pst.executeQuery();
            while (rs.next()) {
                CtDatosMateria materia = new CtDatosMateria();
                materia.setIdtbdatosmateria(rs.getInt("tb_materiaalumno.idtb_materiaalumno"));
                materia.setNombrelargo(rs.getString("ct_datosmateria.nombrelargo"));
                listmaterias.add(materia);
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
        return listmaterias;
    }

    /*Consulta que trae los dias en donde el alumno ha faltado*/
    public List<CtDia> getDiasFaltas(int idalumno, int idperiodo, int idsemana, int idmateria, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtDia> listdias = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select tb_asistencia.idtb_asistencia, ct_dia.nombre, ct_datosmateria.nombrelargo from tb_asistencia inner join tb_materiaalumno\n"
                    + "on tb_asistencia.r_materiaalumno = tb_materiaalumno.idtb_materiaalumno inner join tb_alumnos\n"
                    + "on tb_materiaalumno.r_alumno = tb_alumnos.idTb_Alumnos inner join ct_dia \n"
                    + "on tb_asistencia.r_dia = ct_dia.idCt_Dia inner join ct_semanafiscal\n"
                    + "on tb_asistencia.r_semanafiscal = ct_semanafiscal.idCt_SemanaFiscal inner join ct_periodoescolar\n"
                    + "on tb_asistencia.r_periodo = ct_periodoescolar.idCt_PeriodoEscolar inner join tb_materiapersonal\n"
                    + "on tb_materiaalumno.r_materiapersonal = tb_materiapersonal.idTb_MateriaPersonal inner join tb_materia\n"
                    + "on tb_materiapersonal.r_materia = tb_materia.idTb_Materia inner join ct_datosmateria\n"
                    + "on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria\n"
                    + "where tb_materiaalumno.r_alumno = ? and tb_asistencia.r_materiaalumno = ? and  tb_asistencia.r_semanafiscal = ? and tb_asistencia.r_periodo = ? \n"
                    + "and tb_asistencia.asistencia = 0 and tb_asistencia.status = 1 and tb_asistencia.tipoescuela = ?;";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, idalumno);
            pst.setInt(2, idmateria);
            pst.setInt(3, idsemana);
            pst.setInt(4, idperiodo);
            pst.setInt(5, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                CtDia dia = new CtDia();
                dia.setIdtbdia(rs.getInt("tb_asistencia.idtb_asistencia"));
                dia.setNombre(rs.getString("ct_dia.nombre"));
                listdias.add(dia);
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
        return listdias;
    }

    /*Consulta que actualiza el campo de asistencia para justificar*/
    public void updateJustificar(List<TbAsistencia> listasistencia) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            for (TbAsistencia item : listasistencia) {
                con.setAutoCommit(false);
                String consulta = "UPDATE `tb_asistencia` SET `asistencia` = '3' WHERE (`idtb_asistencia` = ?);";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, item.getIdtbasistencia());

                if (pst.executeUpdate() == 1) {
                    con.commit();
                } else {
                    System.out.println("no se pudo guardar");
                }
                pst = null;
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

    /*Consulta para traer alumno(s)*/
    public Alumno getAlumno(int idalumno, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Alumno alumno = new Alumno();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from tb_alumnos where idTb_Alumnos = ? and tipoescuela = ?;";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, idalumno);
            pst.setInt(2, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellidop(rs.getString("apellidopaterno"));
                alumno.setApellidom(rs.getString("apellidomaterno"));
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
        return alumno;
    }
    /*Consulta para actualizar registros en la tabla de asistencia*/
    public void actualizarAsistencia(List<TbAsistencia> listasistencia) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            for (TbAsistencia item : listasistencia) {
                con.setAutoCommit(false);
                String consulta = "UPDATE `tb_asistencia` SET `asistencia` = ? WHERE (`idtb_asistencia` = ?);";
                pst = con.prepareStatement(consulta);
                pst.setInt(1, item.getAsistencia());
                pst.setInt(2, item.getIdtbasistencia());

                if (pst.executeUpdate() == 1) {
                    con.commit();
                } else {
                    System.out.println("no se pudo guardar");
                }
                pst = null;
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
    /*Consulta para determinar si de acuerdo con los parametros recibidos se ha generado asistencia, si el count es mayor a 0 signofica que s eha generado asistencia*/
    public int verificarAsistencia(int idperiodo, int idsemana, int iddia, int idmateria, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        int bandera = 0;
        try {
            con.setAutoCommit(false);
            String consulta = "SELECT count(r_materiaalumno) as verifica from tb_asistencia inner join tb_materiaalumno\n"
                    + "on tb_asistencia.r_materiaalumno = tb_materiaalumno.idtb_materiaalumno  \n"
                    + "where tb_asistencia.r_periodo = ? and tb_asistencia.r_dia = ? and tb_asistencia.r_semanafiscal = ? and tb_materiaalumno.r_materiapersonal = ? and tb_asistencia.tipoescuela = ?;";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, idperiodo);
            pst.setInt(2, iddia);
            pst.setInt(3, idsemana);
            pst.setInt(4, idmateria);
            pst.setInt(5, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                bandera = rs.getInt("verifica");
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
        return bandera;
    }
    /*  Consulta para ttraer el nombre de la materia en el cual se esta actualizando*/
    public CtDatosMateria getMateriaAsistneciaA(int idmateria, int tipoescuela) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        CtDatosMateria materia = new CtDatosMateria();
        try {
            con.setAutoCommit(false);
            String consulta = "SELECT ct_datosmateria.nombrelargo FROM tb_materiapersonal inner join tb_materia\n"
                    + "on tb_materiapersonal.r_materia = tb_materia.idTb_Materia inner join ct_datosmateria\n"
                    + "on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria\n"
                    + "where tb_materiapersonal.idTb_MateriaPersonal = ? and tb_materiapersonal.status = 1 and tb_materiapersonal.tipoescuela = ?;";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, idmateria);
            pst.setInt(2, tipoescuela);
            rs = pst.executeQuery();
            while (rs.next()) {
                materia.setNombrelargo(rs.getString("ct_datosmateria.nombrelargo"));
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
        return materia;
    }

}
