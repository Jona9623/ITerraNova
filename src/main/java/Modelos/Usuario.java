/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Complx
 */
public class Usuario {
    private int idtbusuario;
    private int r_tipo;
    private int r_personal;
    private String rtipo;
    private String usuario;
    private String contrasena;

    public int getR_personal() {
        return r_personal;
    }

    public void setR_personal(int r_personal) {
        this.r_personal = r_personal;
    }

    public int getIdtbusuario() {
        return idtbusuario;
    }

    public void setIdtbusuario(int idtbusuario) {
        this.idtbusuario = idtbusuario;
    }

    public int getR_tipo() {
        return r_tipo;
    }

    public void setR_tipo(int r_tipo) {
        this.r_tipo = r_tipo;
    }

    public String getRtipo() {
        return rtipo;
    }

    public void setRtipo(String rtipo) {
        this.rtipo = rtipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    
    
}
