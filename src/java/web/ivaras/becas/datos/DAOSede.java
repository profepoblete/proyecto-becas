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

import web.ivaras.becas.entidades.Sede;
import web.ivaras.becas.util.iCodigos;

/**
 *
 * @author cesar
 */
public class DAOSede implements iCodigos<Sede> {
    private final String sql_selectAll = "SELECT id_sede, sede FROM sede";
    private static Conexion objConn;
    private ResultSet rs;
    private PreparedStatement sql;

    public DAOSede() {
        objConn = Conexion.InstanciaConn();
    }

    @Override
    public ArrayList<Sede> listaCodigos() {
        try {
            ArrayList<Sede> lista = new ArrayList<>();
            sql = objConn.getConn().prepareStatement(sql_selectAll);
            rs = sql.executeQuery();
            while(rs.next())
            {
                lista.add(new Sede(rs.getInt("id_sede"),rs.getString("sede")));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAOBeneficio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
