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
import Modelos.TbAlumnos;
import Modelos.TbTutor;
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

    private Conexion con = new Conexion();
    private int x = 1;

    public void guardaTutor(TbTutor tutor) {
        PreparedStatement pst = null;
        try {
            con.conexion().setAutoCommit(false);
            String consulta = "insert into tb_tutor (nombre,apellidopaterno,apellidomaterno,ocupacion,parentesco,calledomicilio,numerodomicilio,\n"
                    + "coloniadomicilio,codigopostal,telefonocasa,celular,correo,status,tipoescuela) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = con.conexion().prepareStatement(consulta);
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
                con.conexion().commit();
            } else {
                System.out.println("Error al guardar");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (con.conexion() != null) {
                    con.conexion().close();
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
        PreparedStatement pst = null;
        int id = 0;
        try {
            con.conexion().setAutoCommit(false);
            String sql = "SELECT idTb_Tutor FROM tb_tutor ORDER BY idTb_Tutor DESC LIMIT 1";
            Statement st = con.conexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt(1);
            }
            String consulta = "insert into tb_alumnos (matricula,nombre,apellidopaterno,apellidomaterno,fechanacimiento,curp,municipionacimiento,estadonacimiento,\n"
                    + "nacionalidad,sexo,calledomicilio,numerodomicilio,coloniadomicilio,codigopostal,telefonocasa,celularalumno,correoalumno,nivelcursa,\n"
                    + "r_grado,r_grupo,r_area,r_cpt,plantelprocedencia,nivelanterior,gradoanterior,turnoanterior,municipioanterior,r_tutor,status,tipoescuela) values (?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = con.conexion().prepareStatement(consulta);
            pst.setString(1, alumno.getMatricula());
            pst.setString(1, alumno.getNombre());
            pst.setString(1, alumno.getApellidop());
            pst.setString(1, alumno.getApellidom());
            pst.setString(1, alumno.getFechanacimiento());
            pst.setString(1, alumno.getCurp());
            pst.setString(1, alumno.getMunicipiona());
            pst.setString(1, alumno.getEstadona());
            pst.setString(1, alumno.getNacionalidad());
            pst.setInt(1, alumno.getSexo());
            pst.setString(1, alumno.getCalledom());
            pst.setInt(1, alumno.getNumerodom());
            pst.setString(1, alumno.getColoniadom());
            pst.setInt(1, alumno.getCodigopostal());
            pst.setString(1, alumno.getTelefonocasa());
            pst.setString(1, alumno.getCelular());
            pst.setString(1, alumno.getCorreo());
            pst.setString(1, alumno.getNivelcursa());
            pst.setInt(1, alumno.getRgrado());
            pst.setInt(1, alumno.getRgrupo());
            pst.setInt(1, alumno.getRarea());
            pst.setInt(1, alumno.getRcpt());
            pst.setString(1, alumno.getPlantelproce());
            pst.setInt(1, alumno.getNivelanterior());
            pst.setInt(1, alumno.getGradoanterior());
            pst.setInt(1, alumno.getTurnoanterior());
            pst.setString(1, alumno.getMunicipioante());
            pst.setInt(1, id);
            pst.setInt(1, 1);
            pst.setInt(1,1);
            
            if (pst.executeUpdate() == 1) {
                con.conexion().commit();
            } else {
                System.out.println("Error al guardar");
            } 
            
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (con.conexion() != null) {
                    con.conexion().close();
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
        PreparedStatement pst = null;
        ResultSet rs = null;
        List <CtGrado> listgrado = new ArrayList<>();
        try {
            con.conexion().setAutoCommit(false);
            String consulta = "select * from ct_grado where status =1 and tipoescuela = 1";
            pst = con.conexion().prepareStatement(consulta);
            rs = pst.executeQuery();
            while(rs.next()){
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
                if (con.conexion()!= null) {
                    con.conexion().close();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<CtAreaalumno> getArea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<CtCptalumno> getCpt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
