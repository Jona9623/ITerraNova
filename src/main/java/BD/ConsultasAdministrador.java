/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public List<TbAlumnos> getAlumnos() {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TbAlumnos> listalumnos = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select tb_alumnos.r_tutor, tb_alumnos.idTb_Alumnos, tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno, tb_alumnos.matricula, ct_grado.nombre, ct_grupo.nombre, tb_tutor.nombre from\n"
                    + "(((tb_alumnos inner join ct_grado on tb_alumnos.r_grado = ct_grado.idCt_Grado) inner join ct_grupo on tb_alumnos.r_grupo = ct_grupo.idCt_Grupo) inner join\n"
                    + "tb_tutor on tb_alumnos.r_tutor = tb_tutor.idTb_Tutor) where tb_alumnos.status = 1 and tb_alumnos.tipoescuela = 1";
            pst = con.prepareStatement(consulta);
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
                listalumnos.add(alumno);
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
        return listalumnos;
    }

    public List<TbPersonal> getPersonal() {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TbPersonal> listpersonal = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select tb_personal.idTb_Personal, tb_personal.nombre, tb_personal.apellidopaterno, tb_personal.apellidomaterno, tb_personal.correo, ct_puesto.nombre from (tb_personal inner join\n"
                    + "ct_puesto on tb_personal.r_puesto = ct_puesto.idCt_Puesto) where tb_personal.status = 1 and tb_personal.tipoescuela = 1";
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
        return listpersonal;
    }

    public void guardaTutor(TbTutor tutor) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into tb_tutor (nombre,apellidopaterno,apellidomaterno,ocupacion,parentesco,calledomicilio,numerodomicilio,\n"
                    + "coloniadomicilio,codigopostal,telefonocasa,celular,correo,status,tipoescuela) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            pst.setInt(13, 1);
            pst.setInt(14, 1);

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

    public void guardaAlumno(TbAlumnos alumno) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
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
                    + "r_grado,r_grupo,r_area,r_cpt,plantelprocedencia,nivelanterior,gradoanterior,turnoanterior,municipioanterior,r_tutor,status,tipoescuela) values (?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            pst.setInt(24, alumno.getNivelanterior());
            pst.setInt(25, alumno.getGradoanterior());
            pst.setInt(26, alumno.getTurnoanterior());
            pst.setString(27, alumno.getMunicipioante());
            pst.setInt(28, id);
            pst.setInt(29, 1);
            pst.setInt(30, 1);

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

    public TbAlumnos datosAlumno(int id) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        TbAlumnos alumno = new TbAlumnos();
        try {
            String consulta = "select * from tb_alumnos where idTb_Alumnos = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                alumno.setIdtbalumnos(rs.getInt("idTb_Alumnos"));
                alumno.setMatricula(rs.getString("matricula"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellidop(rs.getString("apellidopaterno"));
                alumno.setApellidom(rs.getString("apellidomaterno"));
                alumno.setFechanacimiento(rs.getString("fechanacimiento"));
                alumno.setCurp(rs.getString("curp"));
                alumno.setMunicipiona(rs.getString("municipionacimiento"));
                alumno.setEstadona(rs.getString("estadonacimiento"));
                alumno.setNacionalidad(rs.getString("nacionalidad"));
                alumno.setSexo(rs.getBoolean("sexo"));
                alumno.setCalledom(rs.getString("calledomicilio"));
                alumno.setNumerodom(rs.getInt("numerodomicilio"));
                alumno.setColoniadom(rs.getString("coloniadomicilio"));
                alumno.setCodigopostal(rs.getInt("codigopostal"));
                alumno.setTelefonocasa(rs.getString("telefonocasa"));
                alumno.setCelular(rs.getString("celularalumno"));
                alumno.setCorreo(rs.getString("correoalumno"));
                alumno.setNivelcursa(rs.getString("nivelcursa"));
                alumno.setRgrado(rs.getInt("r_grado"));
                alumno.setRgrupo(rs.getInt("r_grupo"));
                alumno.setRarea(rs.getInt("r_area"));
                alumno.setRcpt(rs.getInt("r_cpt"));
                alumno.setPlantelproce(rs.getString("plantelprocedencia"));
                alumno.setNivelanterior(rs.getInt("nivelanterior"));
                alumno.setGradoanterior(rs.getInt("gradoanterior"));
                alumno.setTurnoanterior(rs.getInt("turnoanterior"));
                alumno.setMunicipioante(rs.getString("municipioanterior"));
            }
        } catch (Exception e) {
            System.out.print("Error" + e);
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

    public TbTutor datosTutor(int id) {
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
            }
        } catch (Exception e) {
            System.out.print("Error" + e);
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

    public TbPersonal datosPersonal(int idpersonal) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        TbPersonal personal = new TbPersonal();
        try {
            String consulta = "select * from tb_personal where idTb_Personal = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, idpersonal);
            rs = pst.executeQuery();
            while (rs.next()) {
                personal.setIdtbpersonal(rs.getInt("idTb_Personal"));
                personal.setNombre(rs.getString("nombre"));
                personal.setApellidop(rs.getString("apellidopaterno"));
                personal.setApellidom(rs.getString("apellidomaterno"));
                personal.setFechanacimiento(rs.getString("fechanacimiento"));
                personal.setCurp(rs.getString("curp"));
                personal.setMunicipionac(rs.getString("municipionacimiento"));
                personal.setEstadonac(rs.getString("estadonacimiento"));
                personal.setNacionalidad(rs.getString("nacionalidad"));
                personal.setSexo(rs.getBoolean("sexo"));
                personal.setCalledom(rs.getString("calledomicilio"));
                personal.setNumerodom(rs.getInt("numerodomicilio"));
                personal.setColoniadom(rs.getString("coloniadomicilio"));
                personal.setCodigopostal(rs.getInt("codigopostal"));
                personal.setTelefonocasa(rs.getString("telefonocasa"));
                personal.setCelular(rs.getString("celular"));
                personal.setCorreo(rs.getString("correo"));
                personal.setNss(rs.getString("nss"));
                personal.setRfc(rs.getString("rfc"));
                personal.setNivelestudios(rs.getString("nivelmaxestudios"));
                personal.setLicenciatura(rs.getString("licenciatura"));
                personal.setMaestria(rs.getString("maestria"));
                personal.setDoctorado(rs.getString("doctorado"));
                personal.setRpuesto(rs.getInt("r_puesto"));
            }
        } catch (Exception e) {
            System.out.print("Error" + e);
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

    public void actualizaAlumno(TbAlumnos alumno) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            String consulta = "UPDATE `terranova`.`tb_alumnos` SET `matricula` = ?, `nombre` = ?, `apellidopaterno` = ?, `apellidomaterno` = ?, `fechanacimiento` = ?, `curp` = ?, \n"
                    + "`municipionacimiento` = ?, `estadonacimiento` = ?, `nacionalidad` = ?, `sexo` = ?, `calledomicilio` = ?, `numerodomicilio` = ?, `coloniadomicilio` = ?,\n"
                    + " `codigopostal` = ?, `telefonocasa` = ?, `celularalumno` = ?, `correoalumno` = ?, `nivelcursa` = ?, `r_grado` = ?, `r_grupo` = ?,\n"
                    + " `r_area` = ?, `r_cpt` = ?, `plantelprocedencia` = ?, `nivelanterior` = ?, `gradoanterior` = ?, `turnoanterior` = ?, `municipioanterior` = ? \n"
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
            pst.setInt(21, alumno.getRarea());
            pst.setInt(22, alumno.getRcpt());
            pst.setString(23, alumno.getPlantelproce());
            pst.setInt(24, alumno.getNivelanterior());
            pst.setInt(25, alumno.getGradoanterior());
            pst.setInt(26, alumno.getTurnoanterior());
            pst.setString(27, alumno.getMunicipioante());
            pst.setInt(28, alumno.getIdtbalumnos());
            if (pst.executeUpdate() == 1) {
                con.commit();
            }
        } catch (Exception e) {
            System.out.print("Error" + e);
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

    public void actualizaTutor(TbTutor tutor) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            String consulta = "UPDATE `terranova`.`tb_tutor` SET `nombre` = ?, `apellidopaterno` = ?, `apellidomaterno` = ?, `ocupacion` = ?, `parentesco` = ?,"
                    + " `calledomicilio` = ?, `numerodomicilio` = ?,\n"
                    + " `coloniadomicilio` = ?, `codigopostal` = ?, `telefonocasa` = ?, `celular` = ?, `correo` = ? WHERE (`idTb_Tutor` = ?)";
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
            pst.setInt(13, tutor.getIdtbtutor());
            if (pst.executeUpdate() == 1) {
                con.commit();
            }
        } catch (Exception e) {
            System.out.print("Error" + e);
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

    public void actualizaPersonal(TbPersonal personal) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            String consulta = "UPDATE `terranova`.`tb_personal` SET `nombre` = ?, `apellidopaterno` = ?, `apellidomaterno` = ?, `fechanacimiento` = ?, `curp` = ?, `municipionacimiento` = ?,\n"
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
            System.out.print("Error" + e);
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

    public void eliminaAlumno(int id) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "update `terranova`.`tb_alumnos` set `status` = 2 where `idTb_Alumnos` = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, id);
            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al eliminar");
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

    public void eliminaPersonal(int id) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "update `terranova`.`tb_personal` set `status` = 2 where `idTb_Personal` = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, id);
            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al eliminar");
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

    public void guardaPersonal(TbPersonal personal) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into tb_personal (nombre,apellidopaterno,apellidomaterno,fechanacimiento,curp,municipionacimiento,estadonacimiento,nacionalidad,sexo,calledomicilio,"
                    + "numerodomicilio,coloniadomicilio,codigopostal,telefonocasa,celular,correo,nss,rfc,nivelmaxestudios,licenciatura,maestria,doctorado,r_puesto,status,tipoescuela) values"
                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            pst.setInt(25, 1);

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

    public List<CtGrado> getGrado() {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtGrado> listgrado = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_grado where status =1 and tipoescuela = 1";
            pst = con.prepareStatement(consulta);
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
        return listgrado;
    }

    public List<CtGrupo> getGrupo() {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtGrupo> listgrupo = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_grupo where status = 1 and tipoescuela = 1";
            pst = con.prepareStatement(consulta);
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
        return listgrupo;
    }

    public List<CtAreaalumno> getArea() {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtAreaalumno> listarea = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_areaalumno where status = 1 and tipoescuela = 1";
            pst = con.prepareStatement(consulta);
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
        return listarea;

    }

    public List<CtCptalumno> getCpt() {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtCptalumno> listcpt = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_cptalumno where status = 1 and tipoescuela = 1";
            pst = con.prepareStatement(consulta);
            rs = pst.executeQuery();
            while (rs.next()) {
                CtCptalumno cpt = new CtCptalumno();
                cpt.setIdtbcpt(rs.getInt("idCt_CptAlumno"));
                cpt.setNombre(rs.getString("nombre"));
                cpt.setStatus(rs.getInt("status"));
                cpt.setTipoescuela(rs.getInt("tipoescuela"));
                listcpt.add(cpt);
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
        return listcpt;
    }

    public List<CtPuesto> getPuesto() {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtPuesto> listpuesto = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_puesto where status = 1 and tipoescuela = 1";
            pst = con.prepareStatement(consulta);
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
        return listpuesto;
    }
    
    public List<GradoGrupo> getGradoGrupo() {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<GradoGrupo> listgradogrupo = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "SELECT * FROM ct_grupo, ct_grado where ct_grado.status = 1 and ct_grado.tipoescuela = 1 and ct_grupo.status = 1 and ct_grupo.tipoescuela = 1";
            pst = con.prepareStatement(consulta);
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
        return listgradogrupo;
    }

    public List<CtTipoCalificaicon> getTipoCali() {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtTipoCalificaicon> listtipocali = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from ct_tipocalificacion where status = 1 and tipoescuela = 1";
            pst = con.prepareStatement(consulta);
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
        return listtipocali;
    }

    public void guardaPuesto(CtPuesto puesto) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into ct_puesto (nombre,status,tipoescuela) values (?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, puesto.getNombre());
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

    public void eliminaPuesto(int id) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "update `terranova`.`ct_puesto` set `status` = 2 where `idCt_Puesto` = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, id);
            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al eliminar");
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

    public void guardaPeriodo(CtPeriodoEscolar periodo) {
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
            pst.setInt(5, 1);

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

    public void eliminaPeriodo(int id) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "update `terranova`.`ct_periodoescolar` set `status` = 2 where `idCt_PeriodoEscolar` = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, id);
            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al eliminar");
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

    public void guardaArea(CtAreaalumno area) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into ct_areaalumno (nombre,status,tipoescuela) values (?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, area.getNombre());
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

    public void eliminaArea(int id) {
         con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "update `terranova`.`ct_areaalumno` set `status` = 2 where `idCt_AreaAlumno` = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, id);
            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al eliminar");
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

    public void guardaCpt(CtCptalumno cpt) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into ct_cptalumno (nombre,status,tipoescuela) values (?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, cpt.getNombre());
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

    public void eliminaCpt(int id) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "update `terranova`.`ct_cptalumno` set `status` = 2 where `idCt_CptAlumno` = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, id);
            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al eliminar");
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

    public void guardaGrado(CtGrado grado) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into ct_grado (nombre,status,tipoescuela) values (?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, grado.getNombre());
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

    public void guardaGrupo(CtGrupo grupo) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into ct_grupo (nombre,status,tipoescuela) values (?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, grupo.getNombre());
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

    public void guardaTipoCali(CtTipoCalificaicon tipocali) {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into ct_tipocalificacion (nombre,status,tipoescuela) values (?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setString(1, tipocali.getNombre());
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

    public void eliminaTipoCali(int id) {
         con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String consulta = "update `terranova`.`ct_tipocalificacion` set `status` = 2 where `idCt_TipoCalificacion` = ?";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, id);
            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al eliminar");
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

    public List<CtDatosMateria> getMateriasFaltantes() {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CtDatosMateria> listmateriafaltante = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            String consulta = "select ct_datosmateria.nombrelargo, ct_datosmateria.nombrecorto, tb_materia.r_datosmateria, ct_datosmateria.idCt_DatosMateria from "
                    + "tb_materia right join ct_datosmateria on tb_materia.r_datosmateria = ct_datosmateria.idCt_DatosMateria where tb_materia.r_datosmateria IS NULL; ";
            pst = con.prepareStatement(consulta);
            rs = pst.executeQuery();
            while (rs.next()) {
                CtDatosMateria materiafaltante = new CtDatosMateria();
                materiafaltante.setIdtbdatosmateria(rs.getInt("ct_datosmateria.idCt_DatosMateria"));
                materiafaltante.setNombrecorto(rs.getString("ct_datosmateria.nombrecorto"));
                materiafaltante.setNombrelargo(rs.getString("ct_datosmateria.nombrelargo"));
                listmateriafaltante.add(materiafaltante);
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
        return listmateriafaltante;
    }

}
