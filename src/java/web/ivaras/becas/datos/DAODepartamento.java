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
import web.ivaras.becas.entidades.Departamento;
import web.ivaras.becas.util.iCRUD;

/**
 *
 * @author cesar
 */
public class DAODepartamento implements iCRUD<Departamento> {
    private final String sql_selectAll = "select id_departamento, departamento from departamento";
    /*
    private final String sql_insert = "INSERT INTO beneficio(nombre,id_tipo_beneficio,vigente) VALUES (?,?,?)";
    private final String sql_delete = "DELETE FROM beneficio WHERE id_beneficio = ?";
    private final String sql_selectById = "SELECT * FROM beneficio WHERE id_beneficio = ?";
    private final String sql_update = "UPDATE beneficio SET nombre = ?,id_tipo_beneficio = ?,vigente = ? WHERE id_beneficio = ?";
    */
    private static Conexion objConn;
    private ResultSet rs;
    private PreparedStatement sql;

    public DAODepartamento() {
        objConn = Conexion.InstanciaConn();
    }
    
    
    @Override
    public ArrayList<Departamento> listarTodo() {
        try {
            ArrayList<Departamento> lista = new ArrayList<>();
            sql = objConn.getConn().prepareStatement(sql_selectAll);
            rs = sql.executeQuery();
            while(rs.next())
            {
                lista.add(new Departamento(rs.getInt("id_departamento"),rs.getString("departamento")));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAOBeneficio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int agregar(Departamento o) {
        return 0;
    }

    @Override
    public Departamento buscarPorId(int id) {
        return null;
    }

    @Override
    public int modificar(Departamento o) {
        return 0;
    }

    @Override
    public int eliminar(Departamento o) {
        return 0;
    }
    
}
