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
import web.ivaras.becas.entidades.Beneficio;
import web.ivaras.becas.util.iCRUD;

/**
 *
 * @author cesar
 */
public class DAOBeneficio implements iCRUD<Beneficio> {
    private final String sql_selectAll = "SELECT id_beneficio, beneficio.nombre, id_tipo_beneficio, tb.nombre as tipo_beneficio, vigente FROM beneficio left join tipo_beneficio tb on (beneficio.id_tipo_beneficio = tb.id_tipo) where beneficio.vigente = 1 order by beneficio.id_beneficio";
    private final String sql_selectAllByTipo = "SELECT id_beneficio, beneficio.nombre, id_tipo_beneficio, tb.nombre as tipo_beneficio, vigente FROM beneficio left join tipo_beneficio tb on (beneficio.id_tipo_beneficio = tb.id_tipo) where beneficio.id_tipo_beneficio=? and beneficio.vigente = 1 order by beneficio.id_beneficio";
    private final String sql_insert = "INSERT INTO beneficio(nombre,id_tipo_beneficio,vigente) VALUES (?,?,?)";
    private final String sql_delete = "DELETE FROM beneficio WHERE id_beneficio = ?";
    private final String sql_selectById = "SELECT * FROM beneficio WHERE id_beneficio = ?";
    private final String sql_update = "UPDATE beneficio SET nombre = ?,id_tipo_beneficio = ?,vigente = ? WHERE id_beneficio = ?";
    private static Conexion objConn;
    private ResultSet rs;

    public DAOBeneficio() {
        objConn = Conexion.InstanciaConn();
    }
    
    
    @Override
    public ArrayList<Beneficio> listarTodo() {
        try {
            ArrayList<Beneficio> lista = new ArrayList<>();
            PreparedStatement sql = objConn.getConn().prepareStatement(sql_selectAll);
            rs = sql.executeQuery();
            while(rs.next())
            {
                lista.add(new Beneficio(rs.getInt("id_beneficio"),rs.getString("nombre"),rs.getInt("id_tipo_beneficio"),rs.getString("tipo_beneficio"),(rs.getInt("vigente")==1)));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAOBeneficio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Beneficio> listarTodoPorTipo(int tipoBeneficio) {
        try {
            ArrayList<Beneficio> lista = new ArrayList<>();
            PreparedStatement sql = objConn.getConn().prepareStatement(sql_selectAllByTipo);
            sql.setInt(1, tipoBeneficio);
            rs = sql.executeQuery();
            while(rs.next())
            {
                lista.add(new Beneficio(rs.getInt("id_beneficio"),rs.getString("nombre"),rs.getInt("id_tipo_beneficio"),rs.getString("tipo_beneficio"),(rs.getInt("vigente")==1)));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAOBeneficio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int agregar(Beneficio o) {
        try {
            PreparedStatement sql = objConn.getConn().prepareStatement(sql_insert);
            sql.setString(1, o.getNombre());
            sql.setInt(2, o.getId_tipo_beneficio());
            sql.setInt(3, (o.isVigente()?1:0));
            sql.execute();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getErrorCode();
        }
    }

    @Override
    public Beneficio buscarPorId(int id) {
        try {
            
            Beneficio be = null;
            PreparedStatement sql = objConn.getConn().prepareStatement(sql_selectById);
            sql.setInt(1, id);
            rs = sql.executeQuery();
            if(rs.next())
            {
                be = new Beneficio(rs.getInt("id_beneficio"),rs.getString("nombre"),rs.getInt("id_tipo_beneficio"),rs.getString("tipo_beneficio"),(rs.getInt("vigente")==1));
            }
            return be;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int modificar(Beneficio o) {
        try {
            PreparedStatement sql = objConn.getConn().prepareStatement(sql_update);
            sql.setString(1, o.getNombre());
            sql.setInt(2, o.getId_tipo_beneficio());
            sql.setInt(3, (o.isVigente()?1:0));
            sql.setInt(4, o.getId_beneficio());
            sql.execute();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getErrorCode();
        }
        
    }

    @Override
    public int eliminar(Beneficio o) {
        try {
            PreparedStatement sql = objConn.getConn().prepareStatement(sql_delete);
            sql.setInt(1, o.getId_beneficio());
            sql.execute();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getErrorCode();
        }
        
    }
    
}
