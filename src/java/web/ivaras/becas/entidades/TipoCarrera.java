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
public class TipoCarrera {
    private int id_tipo_carrera;
    private String tipo_carrera;

    public TipoCarrera() {
    }

    public TipoCarrera(int id_tipo_carrera, String tipo_carrera) {
        this.id_tipo_carrera = id_tipo_carrera;
        this.tipo_carrera = tipo_carrera;
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
