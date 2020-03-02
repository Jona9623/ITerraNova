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
public class Conexion {
    private static Connection con = null;
    public static Connection conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Terranova"+"?serverTimezone=UTC","root","qwerty");
            //con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/escape9_TerraNova","escape9_admin","Terranova");
            con.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return con;
    }
}
