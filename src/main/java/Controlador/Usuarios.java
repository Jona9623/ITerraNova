/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import BD.ConsultasLogin;
import Modelos.Usuario;

/**
 *
 * @author Complx
 */
public class Usuarios {
    public Usuario iniciarSesion(Usuario user) throws Exception{
        ConsultasLogin consulta = new ConsultasLogin();
        return consulta.iniciarSesion(user);
    }

    public int registroUsuario(Usuario newuser) throws Exception {
        ConsultasLogin consulta = new ConsultasLogin();
        if (consulta.registroUsuario(newuser) != 0)
            return 1;
        else return 0;
    }
}
