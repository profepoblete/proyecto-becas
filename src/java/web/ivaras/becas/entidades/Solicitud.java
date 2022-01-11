/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.ivaras.becas.entidades;

import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author cesar
 */
public class Solicitud {
    private int id_formulario;
    private Date fecha_solicitud;
    private int anio_ingreso;
    private int semestre;
    private int anio_egreso;
    private String r2_hermano;
    private String r3_hermano;
    private Date fecha_update;
    private InputStream archivo; 
    private byte[] archivo_bytes;
    private String nombre_archivo;
    private int id_alumno;
    private String alumno;
    private int id_estado;
    private String estado;
    private int id_tipo_solicitud;
    private String tipo_solicitud;
    private int id_funcionario;
    private String funcionario;
    private int id_porcentaje;
    private String porcentahe;
    private int id_beneficio;
    private String beneficio;
    private int monto_beneficio;
    private int id_carrera;
    private String carrera;
    private String rut_alumno;
    


    public Solicitud() {
    }

    public Solicitud(int id_formulario, Date fecha_solicitud, int anio_ingreso, int semestre, int anio_egreso, String r2_hermano, String r3_hermano, Date fecha_update, InputStream archivo, String nombre_archivo, int id_alumno, String alumno, int id_estado, String estado, int id_tipo_solicitud, String tipo_solicitud, int id_funcionario, String funcionario, int id_porcentaje, String porcentahe, int id_beneficio, String beneficio, int monto_beneficio, int id_carrera, String carrera, String rut_alumno) {
        this.id_formulario = id_formulario;
        this.fecha_solicitud = fecha_solicitud;
        this.anio_ingreso = anio_ingreso;
        this.semestre = semestre;
        this.anio_egreso = anio_egreso;
        this.r2_hermano = r2_hermano;
        this.r3_hermano = r3_hermano;
        this.fecha_update = fecha_update;
        this.archivo = archivo;
        this.nombre_archivo = nombre_archivo;
        this.id_alumno = id_alumno;
        this.alumno = alumno;
        this.id_estado = id_estado;
        this.estado = estado;
        this.id_tipo_solicitud = id_tipo_solicitud;
        this.tipo_solicitud = tipo_solicitud;
        this.id_funcionario = id_funcionario;
        this.funcionario = funcionario;
        this.id_porcentaje = id_porcentaje;
        this.porcentahe = porcentahe;
        this.id_beneficio = id_beneficio;
        this.beneficio = beneficio;
        this.monto_beneficio = monto_beneficio;
        this.id_carrera = id_carrera;
        this.carrera = carrera;
        this.rut_alumno = rut_alumno;
    }

    public byte[] getArchivo_bytes() {
        return archivo_bytes;
    }

    public void setArchivo_bytes(byte[] archivo_bytes) {
        this.archivo_bytes = archivo_bytes;
    }

    
    public int getId_formulario() {
        return id_formulario;
    }

    public void setId_formulario(int id_formulario) {
        this.id_formulario = id_formulario;
    }

    public Date getFecha_solicitud() {
        return fecha_solicitud;
    }

    public void setFecha_solicitud(Date fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

    public int getAnio_ingreso() {
        return anio_ingreso;
    }

    public void setAnio_ingreso(int anio_ingreso) {
        this.anio_ingreso = anio_ingreso;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getAnio_egreso() {
        return anio_egreso;
    }

    public void setAnio_egreso(int anio_egreso) {
        this.anio_egreso = anio_egreso;
    }

    public String getR2_hermano() {
        return r2_hermano;
    }

    public void setR2_hermano(String r2_hermano) {
        this.r2_hermano = r2_hermano;
    }

    public String getR3_hermano() {
        return r3_hermano;
    }

    public void setR3_hermano(String r3_hermano) {
        this.r3_hermano = r3_hermano;
    }

    public Date getFecha_update() {
        return fecha_update;
    }

    public void setFecha_update(Date fecha_update) {
        this.fecha_update = fecha_update;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_tipo_solicitud() {
        return id_tipo_solicitud;
    }

    public void setId_tipo_solicitud(int id_tipo_solicitud) {
        this.id_tipo_solicitud = id_tipo_solicitud;
    }

    public String getTipo_solicitud() {
        return tipo_solicitud;
    }

    public void setTipo_solicitud(String tipo_solicitud) {
        this.tipo_solicitud = tipo_solicitud;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public int getId_porcentaje() {
        return id_porcentaje;
    }

    public void setId_porcentaje(int id_porcentaje) {
        this.id_porcentaje = id_porcentaje;
    }

    public String getPorcentahe() {
        return porcentahe;
    }

    public void setPorcentahe(String porcentahe) {
        this.porcentahe = porcentahe;
    }

    public int getId_beneficio() {
        return id_beneficio;
    }

    public void setId_beneficio(int id_beneficio) {
        this.id_beneficio = id_beneficio;
    }

    public String getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(String beneficio) {
        this.beneficio = beneficio;
    }

    public int getMonto_beneficio() {
        return monto_beneficio;
    }

    public void setMonto_beneficio(int monto_beneficio) {
        this.monto_beneficio = monto_beneficio;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getRut_alumno() {
        return rut_alumno;
    }

    public void setRut_alumno(String rut_alumno) {
        this.rut_alumno = rut_alumno;
    }
    
    
}
