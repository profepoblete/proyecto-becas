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
import web.ivaras.becas.entidades.TipoUsuario;
import web.ivaras.becas.util.iCodigos;
/**
 *
 * @author cesar
 */
public class DAOTipoUsuario implements iCodigos<TipoUsuario> {
    private final String sql_selectAll = "SELECT id_tipo_usuario, descripcion FROM tipo_usuario";
    private static Conexion objConn;
    private ResultSet rs;
    private PreparedStatement sql;

    public DAOTipoUsuario() {
        objConn=Conexion.InstanciaConn();
    }
    
    
    @Override
    public ArrayList<TipoUsuario> listaCodigos() {
        try {
            ArrayList<TipoUsuario> lista = new ArrayList<>();
            sql = objConn.getConn().prepareStatement(sql_selectAll);
            rs = sql.executeQuery();
            while(rs.next())
            {
                lista.add(new TipoUsuario(rs.getInt("id_tipo_usuario"),rs.getString("descripcion")));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAOBeneficio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
