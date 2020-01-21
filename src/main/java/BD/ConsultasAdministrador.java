/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelos.CtAreaalumno;
import Modelos.CtCptalumno;
import Modelos.CtGrado;
import Modelos.CtGrupo;
import Modelos.CtPuesto;
import Modelos.TbAlumnos;
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
            String consulta = "select tb_alumnos.idTb_Alumnos, tb_alumnos.nombre, tb_alumnos.apellidopaterno, tb_alumnos.apellidomaterno, tb_alumnos.matricula, ct_grado.nombre, ct_grupo.nombre, tb_tutor.nombre from\n"
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
            pst.setInt(21, alumno.getRarea());
            pst.setInt(22, alumno.getRcpt());
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

}
