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
import web.ivaras.becas.entidades.Porcentaje;
import web.ivaras.becas.util.iCRUD;

/**
 *
 * @author cesar
 */
public class DAOPorcentaje implements iCRUD<Porcentaje> {
    private final String sql_selectAll = "SELECT id_porcentaje, descripcion, vigente, porcentaje, id_beneficio FROM porcentaje";
    private final String sql_selectAllByTipo = "SELECT id_porcentaje, descripcion, vigente, porcentaje, id_beneficio FROM porcentaje where id_beneficio = ?";
    private final String sql_insert = "INSERT INTO porcentaje(descripcion,vigente,porcentaje,id_beneficio) VALUES (?,?,?,?)";
    private final String sql_delete = "DELETE FROM porcentaje WHERE id_porcentaje = ?";
    private final String sql_selectById = "SELECT id_porcentaje, descripcion, vigente, porcentaje, id_beneficio FROM porcentaje where id_porcentaje = ?";
    private final String sql_update = "UPDATE porcentaje SET descripcion = ? ,vigente = ?,porcentaje = ? ,id_beneficio = ? WHERE id_porcentaje = ?";
    private static Conexion objConn;
    private ResultSet rs;
    private PreparedStatement sql;

    public DAOPorcentaje() {
        objConn = Conexion.InstanciaConn();
    }
    
    @Override
    public ArrayList<Porcentaje> listarTodo() {
        try {
            ArrayList<Porcentaje> lista = new ArrayList<>();
            sql = objConn.getConn().prepareStatement(sql_selectAll);
            rs = sql.executeQuery();
            while(rs.next())
            {
                lista.add(new Porcentaje(rs.getInt("id_porcentaje"),rs.getString("descripcion"),(rs.getInt("vigente")==1),rs.getInt("porcentaje"),rs.getInt("id_beneficio")));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAOBeneficio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Porcentaje> listarPorTipo(int id_beneficio) {
        try {
            ArrayList<Porcentaje> lista = new ArrayList<>();
            sql = objConn.getConn().prepareStatement(sql_selectAllByTipo);
            sql.setInt(1, id_beneficio);
            rs = sql.executeQuery();
            while(rs.next())
            {
                lista.add(new Porcentaje(rs.getInt("id_porcentaje"),rs.getString("descripcion"),(rs.getInt("vigente")==1),rs.getInt("porcentaje"),rs.getInt("id_beneficio")));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAOBeneficio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int agregar(Porcentaje o) {
        try {
            sql = objConn.getConn().prepareStatement(sql_insert);
            sql.setString(1, o.getDescripcion());
            sql.setInt(2, (o.isVigente()?1:0));
            sql.setInt(3, o.getPorcentaje());
            sql.setInt(4, o.getId_beneficio());
            sql.execute();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getErrorCode();
        }
    }

    @Override
    public Porcentaje buscarPorId(int id) {
        try {
            
            Porcentaje o = null;
            PreparedStatement sql = objConn.getConn().prepareStatement(sql_selectById);
            sql.setInt(1, id);
            rs = sql.executeQuery();
            if(rs.next())
            {
                o = new Porcentaje(rs.getInt("id_porcentaje"),rs.getString("descripcion"),(rs.getInt("vigente")==1),rs.getInt("porcentaje"),rs.getInt("id_beneficio"));
            }
            return o;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int modificar(Porcentaje o) {
        try {
            sql = objConn.getConn().prepareStatement(sql_update);
            sql.setString(1, o.getDescripcion());
            sql.setInt(2, (o.isVigente()?1:0));
            sql.setInt(3, o.getPorcentaje());
            sql.setInt(4, o.getId_beneficio());
            sql.setInt(5, o.getId_porcentaje());
            sql.execute();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getErrorCode();
        }
    }

    @Override
    public int eliminar(Porcentaje o) {
        try {
            sql = objConn.getConn().prepareStatement(sql_delete);
            
            sql.setInt(1, o.getId_porcentaje());
            sql.execute();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getErrorCode();
        }
    }
    
}
