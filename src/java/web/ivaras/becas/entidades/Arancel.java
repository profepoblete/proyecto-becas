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
public class Arancel {
    private int id_arancel;
    private int valor_matricula;
    private int valor_arancel;
    private int annio_ingreso;
    private int id_semestre;
    private int id_carrera;
    private String carrera;

    public Arancel() {
    }

    public Arancel(int id_arancel, int valor_matricula, int valor_arancel, int annio_ingreso, int id_semestre, int id_carrera, String carrera) {
        this.id_arancel = id_arancel;
        this.valor_matricula = valor_matricula;
        this.valor_arancel = valor_arancel;
        this.annio_ingreso = annio_ingreso;
        this.id_semestre = id_semestre;
        this.id_carrera = id_carrera;
        this.carrera = carrera;
    }

    public int getId_arancel() {
        return id_arancel;
    }

    public void setId_arancel(int id_arancel) {
        this.id_arancel = id_arancel;
    }

    public int getValor_matricula() {
        return valor_matricula;
    }

    public void setValor_matricula(int valor_matricula) {
        this.valor_matricula = valor_matricula;
    }

    public int getValor_arancel() {
        return valor_arancel;
    }

    public void setValor_arancel(int valor_arancel) {
        this.valor_arancel = valor_arancel;
    }

    public int getAnnio_ingreso() {
        return annio_ingreso;
    }

    public void setAnnio_ingreso(int annio_ingreso) {
        this.annio_ingreso = annio_ingreso;
    }

    public int getId_semestre() {
        return id_semestre;
    }

    public void setId_semestre(int id_semestre) {
        this.id_semestre = id_semestre;
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
    
}
