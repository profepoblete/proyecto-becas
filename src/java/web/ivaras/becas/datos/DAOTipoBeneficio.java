/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.ivaras.becas.datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import web.ivaras.becas.entidades.TipoBeneficio;
import web.ivaras.becas.util.iCodigos;

/**
 *
 * @author cesar
 */
public class DAOTipoBeneficio implements iCodigos<TipoBeneficio> {
    private final String sql_selectAll = "SELECT id_tipo, nombre FROM tipo_beneficio";
    private static Conexion objConn;
    private ResultSet rs;
    private PreparedStatement sql;

    public DAOTipoBeneficio() {
        objConn=Conexion.InstanciaConn();
    }
    
    @Override
    public ArrayList<TipoBeneficio> listaCodigos() {
        try {
            ArrayList<TipoBeneficio> lista = new ArrayList<>();
            sql = objConn.getConn().prepareStatement(sql_selectAll);
            rs = sql.executeQuery();
            while(rs.next())
            {
                lista.add(new TipoBeneficio(rs.getInt("id_tipo"),rs.getString("nombre")));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAOBeneficio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    } 
}
