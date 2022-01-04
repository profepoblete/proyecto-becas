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
public class Beneficio {
    private int id_beneficio;
    private String nombre;
    private int id_tipo_beneficio;
    private String tipo_beneficio;
    private boolean vigente;

    public Beneficio() {
    }

    public Beneficio(int id_beneficio, String nombre, int id_tipo_beneficio, String tipo_beneficio, boolean vigente) {
        this.id_beneficio = id_beneficio;
        this.nombre = nombre;
        this.id_tipo_beneficio = id_tipo_beneficio;
        this.tipo_beneficio = tipo_beneficio;
        this.vigente = vigente;
    }

    public int getId_beneficio() {
        return id_beneficio;
    }

    public void setId_beneficio(int id_beneficio) {
        this.id_beneficio = id_beneficio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_tipo_beneficio() {
        return id_tipo_beneficio;
    }

    public void setId_tipo_beneficio(int id_tipo_beneficio) {
        this.id_tipo_beneficio = id_tipo_beneficio;
    }

    public String getTipo_beneficio() {
        return tipo_beneficio;
    }

    public void setTipo_beneficio(String tipo_beneficio) {
        this.tipo_beneficio = tipo_beneficio;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
    
}
