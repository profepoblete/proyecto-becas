/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.ivaras.becas.datos;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import web.ivaras.becas.entidades.Estado;
import web.ivaras.becas.util.iCodigos;

/**
 *
 * @author cesar
 */
public class DAOEstado implements iCodigos<Estado> {
    private final String sql_selectAll = "SELECT id_estado, estado FROM estado";
    private static Conexion objConn;
    private ResultSet rs;
    private PreparedStatement sql;

    public DAOEstado() {
        objConn = Conexion.InstanciaConn();
    }
    
    
    @Override
    public ArrayList<Estado> listaCodigos() {
        try {
            ArrayList<Estado> lista = new ArrayList<>();
            sql = objConn.getConn().prepareStatement(sql_selectAll);
            rs = sql.executeQuery();
            while(rs.next())
            {
                lista.add(new Estado(rs.getInt("id_estado"),rs.getString("estado")));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAOBeneficio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
