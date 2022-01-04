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
public class Porcentaje {
    private int id_porcentaje;
    private String descripcion;
    private boolean vigente;
    private int porcentaje;
    private int id_beneficio;

    public Porcentaje() {
    }

    public Porcentaje(int id_porcentaje, String descripcion, boolean vigente, int porcentaje, int id_beneficio) {
        this.id_porcentaje = id_porcentaje;
        this.descripcion = descripcion;
        this.vigente = vigente;
        this.porcentaje = porcentaje;
        this.id_beneficio = id_beneficio;
    }

    public int getId_porcentaje() {
        return id_porcentaje;
    }

    public void setId_porcentaje(int id_porcentaje) {
        this.id_porcentaje = id_porcentaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getId_beneficio() {
        return id_beneficio;
    }

    public void setId_beneficio(int id_beneficio) {
        this.id_beneficio = id_beneficio;
    }
    
}
