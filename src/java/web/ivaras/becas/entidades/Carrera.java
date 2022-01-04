/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.ivaras.becas.entidades;

/**
 *
 * @author cesar
 */
public class Carrera {
    private int id_carrera;
    private String descripcion;
    private int id_departamento;
    private String departamento;
    private int id_tipo_carrera;
    private String tipo_carrera;

    public Carrera() {
    }

    public Carrera(int id_carrera, String descripcion, int id_departamento, String departamento, int id_tipo_carrera, String tipo_carrera) {
        this.id_carrera = id_carrera;
        this.descripcion = descripcion;
        this.id_departamento = id_departamento;
        this.departamento = departamento;
        this.id_tipo_carrera = id_tipo_carrera;
        this.tipo_carrera = tipo_carrera;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getId_tipo_carrera() {
        return id_tipo_carrera;
    }

    public void setId_tipo_carrera(int id_tipo_carrera) {
        this.id_tipo_carrera = id_tipo_carrera;
    }

    public String getTipo_carrera() {
        return tipo_carrera;
    }

    public void setTipo_carrera(String tipo_carrera) {
        this.tipo_carrera = tipo_carrera;
    }
    
}
