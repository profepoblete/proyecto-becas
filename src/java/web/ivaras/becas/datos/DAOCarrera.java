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
import web.ivaras.becas.entidades.Carrera;
import web.ivaras.becas.util.iCRUD;

/**
 *
 * @author cesar
 */
public class DAOCarrera implements iCRUD<Carrera> {
    private final String sql_selectAll = "select c.id_carrera,c.descripcion,d.id_departamento,d.departamento,tc.id_tipo_carrera,tc.tipo_carrera from carrera c left join departamento d ON d.id_departamento = c.id_departamento left join tipo_carrera tc ON tc.id_tipo_carrera = c.id_tipo_carrera";
    /*
    private final String sql_insert = "INSERT INTO beneficio(nombre,id_tipo_beneficio,vigente) VALUES (?,?,?)";
    private final String sql_delete = "DELETE FROM beneficio WHERE id_beneficio = ?";
    private final String sql_selectById = "SELECT * FROM beneficio WHERE id_beneficio = ?";
    private final String sql_update = "UPDATE beneficio SET nombre = ?,id_tipo_beneficio = ?,vigente = ? WHERE id_beneficio = ?";
    */
    private static Conexion objConn;
    private ResultSet rs;
    private PreparedStatement sql;

    public DAOCarrera() {
        objConn = Conexion.InstanciaConn();
    }
    
    @Override
    public ArrayList<Carrera> listarTodo() {
        try {
            ArrayList<Carrera> lista = new ArrayList<>();
            sql = objConn.getConn().prepareStatement(sql_selectAll);
            rs = sql.executeQuery();
            while(rs.next())
            {
                lista.add(new Carrera(rs.getInt("id_carrera"),rs.getString("descripcion"),rs.getInt("id_departamento"),rs.getString("departamento"),rs.getInt("id_tipo_carrera"),rs.getString("tipo_carrera")));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAOBeneficio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int agregar(Carrera o) {
        return 0;
    }

    @Override
    public Carrera buscarPorId(int id) {
        return null;
    }

    @Override
    public int modificar(Carrera o) {
        return 0;
    }

    @Override
    public int eliminar(Carrera o) {
        return 0;
    }
    
}
