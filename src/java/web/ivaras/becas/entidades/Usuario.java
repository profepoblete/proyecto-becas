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
public class Usuario {
    private int id_usuario;
    private String rut;
    private String nombre_completo;
    private String telefono;
    private String email;
    private String pass;
    private int id_estado;
    private int id_carrera;
    private String carrera;
    private int id_tipo_usuario;
    private String tipo_usuario;
    private int id_sede;
    private String sede;

    public Usuario() {
    }

    public Usuario(int id_usuario, String rut, String nombre_completo, String telefono, String email, String pass, int id_estado, int id_carrera, String carrera, int id_tipo_usuario, String tipo_usuario, int id_sede, String sede) {
        this.id_usuario = id_usuario;
        this.rut = rut;
        this.nombre_completo = nombre_completo;
        this.telefono = telefono;
        this.email = email;
        this.pass = pass;
        this.id_estado = id_estado;
        
        this.id_carrera = id_carrera;
        this.carrera = carrera;
        this.id_tipo_usuario = id_tipo_usuario;
        this.tipo_usuario = tipo_usuario;
        this.id_sede = id_sede;
        this.sede = sede;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
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

    public int getId_tipo_usuario() {
        return id_tipo_usuario;
    }

    public void setId_tipo_usuario(int id_tipo_usuario) {
        this.id_tipo_usuario = id_tipo_usuario;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public int getId_sede() {
        return id_sede;
    }

    public void setId_sede(int id_sede) {
        this.id_sede = id_sede;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }
    
}
