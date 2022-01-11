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
import web.ivaras.becas.entidades.Solicitud;
import web.ivaras.becas.util.iCRUD;

/**
 *
 * @author cesar
 */
public class DAOSolicitud implements iCRUD<Solicitud> {
    private final String sql_selectAll = "SELECT s.id_formulario, s.fecha_solicitud, s.anio_ingreso, s.semestre, s.anio_egreso, s.r2_hermano, s.r3_hermano, s.fecha_update, s.archivo, s.nombre_archivo, s.id_alumno,u.nombre_completo as alumno, s.id_estado, e.estado as estado, s.id_tipo_solicitud, ts.tipo_solicitud as tipo_solicitud, s.id_funcionario, u2.nombre_completo as funcionario, s.id_porcentaje, p.descripcion as porcentaje, s.id_beneficio, b.nombre as beneficio, s.monto_beneficio, s.id_carrera, c.descripcion as carrera, u.rut as rut_alumno FROM solicitud s left join usuario u ON u.id_usuario = s.id_alumno left join usuario u2 ON u2.id_usuario = s.id_funcionario left join tipo_solicitud ts ON ts.id_tipo_solicitud = s.id_tipo_solicitud left join estado e ON e.id_estado = s.id_estado left join beneficio b ON b.id_beneficio = s.id_beneficio left join porcentaje p on p.id_porcentaje = s.id_porcentaje left join carrera c on s.id_carrera = c.id_carrera";
    //private final String sql_selectAllByTipo = "SELECT id_porcentaje, descripcion, vigente, porcentaje, id_beneficio FROM porcentaje where id_beneficio = ?";
    private final String sql_insert = "INSERT INTO solicitud(fecha_solicitud,anio_ingreso,semestre,anio_egreso,r2_hermano,r3_hermano,fecha_update,archivo,nombre_archivo,id_alumno,id_estado,id_tipo_solicitud,id_funcionario,id_porcentaje,id_beneficio,monto_beneficio,id_carrera) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String sql_delete = "DELETE FROM solicitud WHERE id_formulario = ?";
    private final String sql_selectById = "SELECT s.id_formulario, s.fecha_solicitud, s.anio_ingreso, s.semestre, s.anio_egreso, s.r2_hermano, s.r3_hermano, s.fecha_update, s.archivo, s.nombre_archivo, s.id_alumno,u.nombre_completo as alumno, s.id_estado, e.estado as estado, s.id_tipo_solicitud, ts.tipo_solicitud as tipo_solicitud, s.id_funcionario, u2.nombre_completo as funcionario, s.id_porcentaje, p.descripcion as porcentaje, s.id_beneficio, b.nombre as beneficio, s.monto_beneficio, s.id_carrera, c.descripcion as carrera, u.rut as rut_alumno FROM solicitud s left join usuario u ON u.id_usuario = s.id_alumno left join usuario u2 ON u2.id_usuario = s.id_funcionario left join tipo_solicitud ts ON ts.id_tipo_solicitud = s.id_tipo_solicitud left join estado e ON e.id_estado = s.id_estado left join beneficio b ON b.id_beneficio = s.id_beneficio left join porcentaje p on p.id_porcentaje = s.id_porcentaje left join carrera c on s.id_carrera = c.id_carrera where s.id_formulario=?";
    private final String sql_update = "UPDATE solicitud SET fecha_solicitud = ?,anio_ingreso = ?,semestre = ?,anio_egreso = ?,r2_hermano = ?,r3_hermano = ?,fecha_update = ?,archivo = ?,nombre_archivo = ?,id_alumno = ?,id_estado = ?,id_tipo_solicitud = ?,id_funcionario = ?,id_porcentaje = ?,id_beneficio = ?,monto_beneficio = ?,id_carrera = ? WHERE id_formulario = ?";
    private static Conexion objConn;
    private ResultSet rs;
    private PreparedStatement sql;

    public DAOSolicitud() {
        objConn = Conexion.InstanciaConn();
    }
    
    @Override
    public ArrayList<Solicitud> listarTodo() {
        try {
            ArrayList<Solicitud> lista = new ArrayList<>();
            sql = objConn.getConn().prepareStatement(sql_selectAll);
            rs = sql.executeQuery();
            while(rs.next())
            {
                Solicitud sol = new Solicitud();
                sol.setId_formulario(rs.getInt("id_formulario"));
                sol.setFecha_solicitud(rs.getDate("fecha_solicitud"));
                sol.setAnio_ingreso(rs.getInt("anio_ingreso"));
                sol.setSemestre(rs.getInt("semestre"));
                sol.setAnio_egreso(rs.getInt("anio_egreso"));
                sol.setR2_hermano(rs.getString("r2_hermano"));
                sol.setR3_hermano(rs.getString("r3_hermano"));
                sol.setFecha_update(rs.getDate("fecha_update"));
                sol.setArchivo_bytes(rs.getBytes("archivo"));
                sol.setNombre_archivo(rs.getString("nombre_archivo"));
                sol.setId_alumno(rs.getInt("id_alumno"));
                sol.setAlumno(rs.getString("alumno"));
                sol.setId_estado(rs.getInt("id_estado"));
                sol.setEstado(rs.getString("estado"));
                sol.setId_tipo_solicitud(rs.getInt("id_tipo_solicitud"));
                sol.setTipo_solicitud(rs.getString("tipo_solicitud"));
                sol.setId_funcionario(rs.getInt("id_funcionario"));
                sol.setFuncionario(rs.getString("funcionario"));
                sol.setId_porcentaje(rs.getInt("id_porcentaje"));
                sol.setPorcentahe(rs.getString("porcentaje"));
                sol.setId_beneficio(rs.getInt("id_beneficio"));
                sol.setBeneficio(rs.getString("beneficio"));
                sol.setMonto_beneficio(rs.getInt("monto_beneficio"));
                sol.setId_carrera(rs.getInt("id_carrera"));
                sol.setCarrera(rs.getString("carrera"));
                sol.setRut_alumno(rs.getString("rut_alumno"));
                lista.add(sol);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAOBeneficio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int agregar(Solicitud o) {
        try {
            java.sql.Date fecha_solicitud = new java.sql.Date(o.getFecha_solicitud().getTime());
            java.sql.Date fecha_update = new java.sql.Date(o.getFecha_update().getTime());
            sql = objConn.getConn().prepareStatement(sql_insert);
            sql.setDate(1, fecha_solicitud);
            sql.setInt(2, o.getAnio_ingreso());
            sql.setInt(3, o.getSemestre());
            sql.setInt(4, o.getAnio_egreso());
            sql.setString(5, o.getR2_hermano());
            sql.setString(6, o.getR3_hermano());
            sql.setDate(7, fecha_update);
            sql.setBlob(8, o.getArchivo());
            sql.setString(9, o.getNombre_archivo());
            sql.setInt(10, o.getId_alumno());
            sql.setInt(11, o.getId_estado());
            sql.setInt(12, o.getId_tipo_solicitud());
            sql.setInt(13, o.getId_formulario());
            sql.setInt(14, o.getId_porcentaje());
            sql.setInt(15, o.getId_beneficio());
            sql.setInt(16, o.getMonto_beneficio());
            sql.setInt(17, o.getId_carrera());
            sql.execute();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getErrorCode();
        }
    }

    @Override
    public Solicitud buscarPorId(int id) {
        try {
            
            Solicitud o = null;
            PreparedStatement sql = objConn.getConn().prepareStatement(sql_selectById);
            sql.setInt(1, id);
            rs = sql.executeQuery();
            if(rs.next())
            {
                o = new Solicitud();
                o.setId_formulario(rs.getInt("id_formulario"));
                o.setFecha_solicitud(rs.getDate("fecha_solicitud"));
                o.setAnio_ingreso(rs.getInt("anio_ingreso"));
                o.setSemestre(rs.getInt("semestre"));
                o.setAnio_egreso(rs.getInt("anio_egreso"));
                o.setR2_hermano(rs.getString("r2_hermano"));
                o.setR3_hermano(rs.getString("r3_hermano"));
                o.setFecha_update(rs.getDate("fecha_update"));
                o.setArchivo_bytes(rs.getBytes("archivo"));
                o.setNombre_archivo(rs.getString("nombre_archivo"));
                o.setId_alumno(rs.getInt("id_alumno"));
                o.setAlumno(rs.getString("alumno"));
                o.setId_estado(rs.getInt("id_estado"));
                o.setEstado(rs.getString("estado"));
                o.setId_tipo_solicitud(rs.getInt("id_tipo_solicitud"));
                o.setTipo_solicitud(rs.getString("tipo_solicitud"));
                o.setId_funcionario(rs.getInt("id_funcionario"));
                o.setFuncionario(rs.getString("funcionario"));
                o.setId_porcentaje(rs.getInt("id_porcentaje"));
                o.setPorcentahe(rs.getString("porcentaje"));
                o.setId_beneficio(rs.getInt("id_beneficio"));
                o.setBeneficio(rs.getString("beneficio"));
                o.setMonto_beneficio(rs.getInt("monto_beneficio"));
                o.setId_carrera(rs.getInt("id_carrera"));
                o.setCarrera(rs.getString("carrera"));
                o.setRut_alumno(rs.getString("rut_alumno"));
            }
            return o;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int modificar(Solicitud o) {
        try {
            java.sql.Date fecha_solicitud = new java.sql.Date(o.getFecha_solicitud().getTime());
            java.sql.Date fecha_update = new java.sql.Date(o.getFecha_update().getTime());
            sql = objConn.getConn().prepareStatement(sql_update);
            sql.setDate(1, fecha_solicitud);
            sql.setInt(2, o.getAnio_ingreso());
            sql.setInt(3, o.getSemestre());
            sql.setInt(4, o.getAnio_egreso());
            sql.setString(5, o.getR2_hermano());
            sql.setString(6, o.getR3_hermano());
            sql.setDate(7, fecha_update);
            sql.setBlob(8, o.getArchivo());
            sql.setString(9, o.getNombre_archivo());
            sql.setInt(10, o.getId_alumno());
            sql.setInt(11, o.getId_estado());
            sql.setInt(12, o.getId_tipo_solicitud());
            sql.setInt(13, o.getId_formulario());
            sql.setInt(14, o.getId_porcentaje());
            sql.setInt(15, o.getId_beneficio());
            sql.setInt(16, o.getMonto_beneficio());
            sql.setInt(17, o.getId_carrera());
            sql.setInt(18, o.getId_formulario());
            sql.execute();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getErrorCode();
        }
    }

    @Override
    public int eliminar(Solicitud o) {
        try {
            sql = objConn.getConn().prepareStatement(sql_delete);
            
            sql.setInt(1, o.getId_formulario());
            sql.execute();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAOArancel.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getErrorCode();
        }
    }
    
}
