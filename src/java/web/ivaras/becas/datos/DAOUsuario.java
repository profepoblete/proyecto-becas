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
import web.ivaras.becas.entidades.Usuario;
import web.ivaras.becas.util.iCRUD;

/**
 *
 * @author cesar
 */
public class DAOUsuario implements iCRUD<Usuario> {
    private final String sql_selectAll = "select u.id_usuario,u.rut,u.nombre_completo,u.telefono,u.email,u.pass,u.estado,u.id_carrera,c.descripcion carrera,u.id_tipo_usuario,tu.descripcion tipo_usuario,u.id_sede ,s.sede sede from usuario u left join carrera c ON c.id_carrera = u.id_carrera left join tipo_usuario tu ON tu.id_tipo_usuario = u.id_tipo_usuario left join sede s ON s.id_sede = u.id_sede";
    private final String sql_selectByLogin ="select u.id_usuario,u.rut,u.nombre_completo,u.telefono,u.email,u.pass,u.estado,u.id_carrera,c.descripcion carrera,u.id_tipo_usuario,tu.descripcion tipo_usuario,u.id_sede ,s.sede sede from usuario u left join carrera c ON c.id_carrera = u.id_carrera left join tipo_usuario tu ON tu.id_tipo_usuario = u.id_tipo_usuario left join sede s ON s.id_sede = u.id_sede where u.email=?";
    private final String sql_selectByRut ="select u.id_usuario,u.rut,u.nombre_completo,u.telefono,u.email,u.pass,u.estado,u.id_carrera,c.descripcion carrera,u.id_tipo_usuario,tu.descripcion tipo_usuario,u.id_sede ,s.sede sede from usuario u left join carrera c ON c.id_carrera = u.id_carrera left join tipo_usuario tu ON tu.id_tipo_usuario = u.id_tipo_usuario left join sede s ON s.id_sede = u.id_sede where u.rut=?";
    private final String sql_selectById ="select u.id_usuario,u.rut,u.nombre_completo,u.telefono,u.email,u.pass,u.estado,u.id_carrera,c.descripcion carrera,u.id_tipo_usuario,tu.descripcion tipo_usuario,u.id_sede ,s.sede sede from usuario u left join carrera c ON c.id_carrera = u.id_carrera left join tipo_usuario tu ON tu.id_tipo_usuario = u.id_tipo_usuario left join sede s ON s.id_sede = u.id_sede where u.id_usuario=?";
    /*
    private final String sql_selectAllByTipo = "SELECT id_porcentaje, descripcion, vigente, porcentaje, id_beneficio FROM porcentaje where id_beneficio = ?";
    private final String sql_insert = "INSERT INTO porcentaje(descripcion,vigente,porcentaje,id_beneficio) VALUES (?,?,?,?)";
    private final String sql_delete = "DELETE FROM porcentaje WHERE id_porcentaje = ?";
    private final String sql_selectById = "SELECT id_porcentaje, descripcion, vigente, porcentaje, id_beneficio FROM porcentaje where id_porcentaje = ?";
    private final String sql_update = "UPDATE porcentaje SET descripcion = ? ,vigente = ?,porcentaje = ? ,id_beneficio = ? WHERE id_porcentaje = ?";
    */ 
    private static Conexion objConn;
    private ResultSet rs;
    private PreparedStatement sql;

    public DAOUsuario() {
        objConn = Conexion.InstanciaConn();
    }
    
    
    @Override
    public ArrayList<Usuario> listarTodo() {
        try {
            ArrayList<Usuario> lista = new ArrayList<>();
            sql = objConn.getConn().prepareStatement(sql_selectAll);
            rs = sql.executeQuery();
            while(rs.next())
            {
                lista.add(new Usuario(rs.getInt("id_usuario"),rs.getString("rut"),rs.getString("nombre_completo"),rs.getString("telefono"),rs.getString("email"),rs.getString("pass"),rs.getInt("estado"),rs.getInt("id_carrera"),rs.getString("carrera"),rs.getInt("id_tipo_usuario"),rs.getString("tipo_usuario"),rs.getInt("id_sede"),rs.getString("sede")));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAOBeneficio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int agregar(Usuario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario buscarPorId(int id) {
        try {
            
            Usuario o = null;
            sql = objConn.getConn().prepareStatement(sql_selectById);
            sql.setInt(1, id);
            rs = sql.executeQuery();
            if(rs.next())
            {
                o = new Usuario(rs.getInt("id_usuario"),rs.getString("rut"),rs.getString("nombre_completo"),rs.getString("telefono"),rs.getString("email"),rs.getString("pass"),rs.getInt("estado"),rs.getInt("id_carrera"),rs.getString("carrera"),rs.getInt("id_tipo_usuario"),rs.getString("tipo_usuario"),rs.getInt("id_sede"),rs.getString("sede"));
            }
            return o;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Usuario buscarPorLogin(String email) {
        try {
            
            Usuario o = null;
            sql = objConn.getConn().prepareStatement(sql_selectByLogin);
            sql.setString(1, email);
            rs = sql.executeQuery();
            if(rs.next())
            {
                o = new Usuario(rs.getInt("id_usuario"),rs.getString("rut"),rs.getString("nombre_completo"),rs.getString("telefono"),rs.getString("email"),rs.getString("pass"),rs.getInt("estado"),rs.getInt("id_carrera"),rs.getString("carrera"),rs.getInt("id_tipo_usuario"),rs.getString("tipo_usuario"),rs.getInt("id_sede"),rs.getString("sede"));
            }
            return o;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Usuario buscarPorRut(String rut) {
        try {
            
            Usuario o = null;
            sql = objConn.getConn().prepareStatement(sql_selectByRut);
            sql.setString(1, rut);
            rs = sql.executeQuery();
            if(rs.next())
            {
                o = new Usuario(rs.getInt("id_usuario"),rs.getString("rut"),rs.getString("nombre_completo"),rs.getString("telefono"),rs.getString("email"),rs.getString("pass"),rs.getInt("estado"),rs.getInt("id_carrera"),rs.getString("carrera"),rs.getInt("id_tipo_usuario"),rs.getString("tipo_usuario"),rs.getInt("id_sede"),rs.getString("sede"));
            }
            return o;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int modificar(Usuario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(Usuario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
