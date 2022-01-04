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
public class TipoSolicitud {
    private int id_tipo_solicitud;
    private String tipo_solicitud;

    public TipoSolicitud() {
    }

    public TipoSolicitud(int id_tipo_solicitud, String tipo_solicitud) {
        this.id_tipo_solicitud = id_tipo_solicitud;
        this.tipo_solicitud = tipo_solicitud;
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
    
}
