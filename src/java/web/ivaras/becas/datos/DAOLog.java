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
import web.ivaras.becas.entidades.Log;
import web.ivaras.becas.util.iCRUD;

/**
 *
 * @author cesar
 */
public class DAOLog implements iCRUD<Log> {
    private final String sql_selectAll = "SELECT id_log, fecha_accion, tipo_usuario, nombre, email, accion, id_formulario, id_usuario FROM log";
    private final String sql_insert = "INSERT INTO log(fecha_accion,tipo_usuario,nombre,email,accion,id_formulario,id_usuario) VALUES (?,?,?,?,?,?,?)";
    /*
    private final String sql_delete = "DELETE FROM beneficio WHERE id_beneficio = ?";
    private final String sql_selectById = "SELECT * FROM beneficio WHERE id_beneficio = ?";
    private final String sql_update = "UPDATE beneficio SET nombre = ?,id_tipo_beneficio = ?,vigente = ? WHERE id_beneficio = ?";
    */
    private static Conexion objConn;
    private ResultSet rs;
    private PreparedStatement sql;

    public DAOLog() {
        objConn = Conexion.InstanciaConn();
    }
    
    
    @Override
    public ArrayList<Log> listarTodo() {
        try {
            ArrayList<Log> lista = new ArrayList<>();
            PreparedStatement sql = objConn.getConn().prepareStatement(sql_selectAll);
            rs = sql.executeQuery();
            while(rs.next())
            {
                lista.add(new Log(rs.getInt("id_log"),rs.getDate("fecha_accion"),rs.getString("tipo_usuario"),rs.getString("nombre"),rs.getString("email"),rs.getString("accion"),rs.getInt("id_formulario"),rs.getInt("id_usuario")));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAOBeneficio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int agregar(Log o) {
        try {
            java.sql.Date fecha_log = new java.sql.Date(o.getFecha_accion().getTime());
            sql = objConn.getConn().prepareStatement(sql_insert);
            sql.setDate(1, fecha_log);
            sql.setString(2,o.getTipo_usuario());
            sql.setString(3, o.getNombre());
            sql.setString(4, o.getEmail());
            sql.setString(5, o.getAccion());
            sql.setInt(6, o.getId_formulario());
            sql.setInt(7, o.getId_usuario());
            sql.execute();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getErrorCode();
        }
    }

    @Override
    public Log buscarPorId(int id) {
        return null;
    }

    @Override
    public int modificar(Log o) {
        return 0;
    }

    @Override
    public int eliminar(Log o) {
        return 0;
    }
    
}
