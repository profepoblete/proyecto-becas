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
import web.ivaras.becas.entidades.TipoSolicitud;
import web.ivaras.becas.util.iCodigos;

/**
 *
 * @author cesar
 */
public class DAOTipoSolicitud implements iCodigos<TipoSolicitud> {
    private final String sql_selectAll = "SELECT id_tipo_solicitud, tipo_solicitud FROM tipo_solicitud";
    private static Conexion objConn;
    private ResultSet rs;
    private PreparedStatement sql;

    public DAOTipoSolicitud() {
        objConn=Conexion.InstanciaConn();
    }
    
    
    @Override
    public ArrayList<TipoSolicitud> listaCodigos() {
        try {
            ArrayList<TipoSolicitud> lista = new ArrayList<>();
            sql = objConn.getConn().prepareStatement(sql_selectAll);
            rs = sql.executeQuery();
            while(rs.next())
            {
                lista.add(new TipoSolicitud(rs.getInt("id_tipo_solicitud"),rs.getString("tipo_solicitud")));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAOBeneficio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
