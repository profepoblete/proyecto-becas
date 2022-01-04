/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.ivaras.becas.datos;
import com.mysql.jdbc.*;
import java.sql.*;
import java.util.logging.*;
/**
 *
 * @author cesar
 */
public class Conexion {
    public static Conexion InstConn;
    private Connection conn;
    
    private Conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/financiamiento?zeroDateTimeBehavior=convertToNull","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public synchronized static Conexion InstanciaConn(){
        if(InstConn==null)
        {
            InstConn = new Conexion();
        }
        
        return InstConn;
    }
        
    public Connection getConn(){
        return conn;
    }
    
    public void Cerrar(){
        InstConn = null;
    }
 
}
