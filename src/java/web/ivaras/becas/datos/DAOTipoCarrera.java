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
import web.ivaras.becas.entidades.TipoCarrera;
import web.ivaras.becas.util.iCodigos;

/**
 *
 * @author cesar
 */
public class DAOTipoCarrera implements iCodigos<TipoCarrera> {
    private final String sql_selectAll = "SELECT id_tipo_carrera, tipo_carrera FROM tipo_carrera";
    private static Conexion objConn;
    private ResultSet rs;
    private PreparedStatement sql;

    public DAOTipoCarrera() {
        objConn=Conexion.InstanciaConn();
    }
    
    @Override
    public ArrayList<TipoCarrera> listaCodigos() {
        try {
            ArrayList<TipoCarrera> lista = new ArrayList<>();
            sql = objConn.getConn().prepareStatement(sql_selectAll);
            rs = sql.executeQuery();
            while(rs.next())
            {
                lista.add(new TipoCarrera(rs.getInt("id_tipo_carrera"),rs.getString("tipo_carrera")));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAOBeneficio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
