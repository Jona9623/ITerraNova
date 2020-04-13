/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Complx
 */
public class ConsultasLogin {

    private Connection con;

    public Usuario iniciarSesion(Usuario user) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Usuario usuario = new Usuario();
        try {
            con.setAutoCommit(false);
            String consulta = "select * from usuarios where usuario = ? and contrasena = ? and status = 1";
            pst = con.prepareStatement(consulta);
            pst.setString(1, user.getUsuario());
            pst.setString(2, user.getContrasena());
            rs = pst.executeQuery();
            while (rs.next()) {
                usuario.setIdtbusuario(rs.getInt("idUsuarios"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setR_tipo(rs.getInt("r_tipo"));
                usuario.setR_personal(rs.getInt("r_personal"));
            }
        } catch (Exception e) {
            throw e;
        }finally{
            try {
                if(con != null){
                    con.close();
                }
                if (pst != null){
                    pst.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return usuario;
    }

    public int registroUsuario(Usuario newuser) throws Exception {
        con = new Conexion().conexion();
        PreparedStatement pst = null;
        try {
            con.setAutoCommit(false);
            String consulta = "insert into usuarios (r_tipo,r_personal,usuario,contrasena,status,tipoescuela) values (?,?,?,?,?,?)";
            pst = con.prepareStatement(consulta);
            pst.setInt(1, newuser.getR_tipo());
            pst.setInt(2, newuser.getR_personal());
            pst.setString(3, newuser.getUsuario());
            pst.setString(4, newuser.getContrasena());
            pst.setInt(5, 1);
            pst.setInt(6, 1);
            
            if (pst.executeUpdate() == 1) {
                con.commit();
            } else {
                System.out.println("Error al guardar");
            }
        } catch (Exception e) {
           throw e;
        }finally {
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
        return 1;
    }

}
