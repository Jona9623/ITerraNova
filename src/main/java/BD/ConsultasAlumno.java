/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;
import java.sql.*;
/**
 *
 * @author complx
 */
public class ConsultasAlumno {
    public static void main (String [] args) throws  SQLException{
        Conexion con = new Conexion();
        if(con.conexion()!= null){
            System.out.println("Conexion exitosa");
        }
        else {
            System.out.println("Conexion fallida");
        }
    }
}
