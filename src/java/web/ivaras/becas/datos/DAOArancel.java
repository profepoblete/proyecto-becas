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
import web.ivaras.becas.entidades.Arancel;
import web.ivaras.becas.util.iCRUD;

/**
 *
 * @author cesar
 */
public class DAOArancel implements iCRUD<Arancel> {
    private final String sql_selectAll = "SELECT id_arancel, valor_matricula, valor_arancel, annio_ingreso, id_semestre, arancel.id_carrera id_carrera, descripcion as carrera FROM arancel left join carrera on (arancel.id_carrera = carrera.id_carrera) order by arancel.id_arancel";
    private final String sql_insert = "INSERT INTO arancel (ivalor_matricula, valor_arancel, annio_ingreso, id_semestre, id_carrera) VALUES ( ?, ?, ?, ?, ?)";
    private final String sql_delete = "DELETE FROM arancel WHERE id_arancel = ?";
    private final String sql_selectById = "SELECT id_arancel, valor_matricula, valor_arancel, annio_ingreso, id_semestre, arancel.id_carrera id_carrera, descripcion as carrera FROM arancel left join carrera on (arancel.id_carrera = carrera.id_carrera) WHERE arancel.id_arancel = ?";
    private final String sql_selectByAnioSemestre = "SELECT id_arancel, valor_matricula, valor_arancel, annio_ingreso, id_semestre, arancel.id_carrera id_carrera, descripcion as carrera FROM arancel left join carrera on (arancel.id_carrera = carrera.id_carrera) WHERE id_semestre = ? and annio_ingreso =? and arancel.id_carrera=?";
    private final String sql_update = "UPDATE arancel SET valor_matricula = ?,valor_arancel = ?,annio_ingreso = ?,id_semestre = ?,id_carrera = ? WHERE id_arancel = ?";
    private static Conexion objConn;
    private ResultSet rs;

    public DAOArancel() {
        objConn = Conexion.InstanciaConn();
    }
    
    @Override
    public ArrayList<Arancel> listarTodo() {
        try {
            ArrayList<Arancel> list = new ArrayList<>();
            PreparedStatement sql = objConn.getConn().prepareStatement(sql_selectAll);
            rs = sql.executeQuery();
            while(rs.next())
            {
                list.add(new Arancel(rs.getInt("id_arancel"),rs.getInt("valor_matricula"),rs.getInt("valor_arancel"),rs.getInt("annio_ingreso"),rs.getInt("id_semestre"),rs.getInt("id_carrera"),rs.getString("carrera")));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int agregar(Arancel o) {
        try {
            PreparedStatement sql = objConn.getConn().prepareStatement(sql_insert);
            sql.setInt(1, o.getValor_matricula());
            sql.setInt(2, o.getValor_arancel());
            sql.setInt(3, o.getAnnio_ingreso());
            sql.setInt(4, o.getId_semestre());
            sql.setInt(5, o.getId_carrera());
            sql.execute();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getErrorCode();
        }
    }

    @Override
    public Arancel buscarPorId(int id) {
        try {
            
            Arancel ar = null;
            PreparedStatement sql = objConn.getConn().prepareStatement(sql_selectById);
            sql.setInt(1, id);
            rs = sql.executeQuery();
            if(rs.next())
            {
                ar = new Arancel(rs.getInt("id_arancel"),rs.getInt("valor_matricula"),rs.getInt("valor_arancel"),rs.getInt("annio_ingreso"),rs.getInt("id_semestre"),rs.getInt("id_carrera"),rs.getString("carrera"));
            }
            return ar;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Arancel buscarPorSemestreAnio(int semestre, int anio, int idcarrera) {
        try {
            
            Arancel ar = null;
            PreparedStatement sql = objConn.getConn().prepareStatement(sql_selectByAnioSemestre);
            sql.setInt(1, semestre);
            sql.setInt(2, anio);
            sql.setInt(3, idcarrera);
            rs = sql.executeQuery();
            if(rs.next())
            {
                ar = new Arancel(rs.getInt("id_arancel"),rs.getInt("valor_matricula"),rs.getInt("valor_arancel"),rs.getInt("annio_ingreso"),rs.getInt("id_semestre"),rs.getInt("id_carrera"),rs.getString("carrera"));
            }
            return ar;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int modificar(Arancel o) {
        try {
            PreparedStatement sql = objConn.getConn().prepareStatement(sql_update);
            sql.setInt(1, o.getValor_matricula());
            sql.setInt(2, o.getValor_arancel());
            sql.setInt(3, o.getAnnio_ingreso());
            sql.setInt(4, o.getId_semestre());
            sql.setInt(5, o.getId_carrera());
            sql.setInt(6, o.getId_arancel());
            sql.execute();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getErrorCode();
        }
    }

    @Override
    public int eliminar(Arancel o) {
        try {
            PreparedStatement sql = objConn.getConn().prepareStatement(sql_delete);
            
            sql.setInt(1, o.getId_arancel());
            sql.execute();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getErrorCode();
        }
    }
    
}
