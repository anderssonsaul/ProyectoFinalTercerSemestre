/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ander
 */
public class Recurso {

    private String codigoRecurso;
    private String nombre;
    private String aprobarCodigoUsuario;
    private String entregaRecurso;
    private String recibeRecurso;
    private String tiempoMaxUso;
    private String costoxHora;
    private String codigoOrganizacion;
    private char estado;

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public String getCodigoRecurso() {
        return codigoRecurso;
    }

    public void setCodigoRecurso(String codigoRecurso) {
        this.codigoRecurso = codigoRecurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAprobarCodigoUsuario() {
        return aprobarCodigoUsuario;
    }

    public void setAprobarCodigoUsuario(String aprobarCodigoUsuario) {
        this.aprobarCodigoUsuario = aprobarCodigoUsuario;
    }

    public String getEntregaRecurso() {
        return entregaRecurso;
    }

    public void setEntregaRecurso(String entregaRecurso) {
        this.entregaRecurso = entregaRecurso;
    }

    public String getRecibeRecurso() {
        return recibeRecurso;
    }

    public void setRecibeRecurso(String recibeRecurso) {
        this.recibeRecurso = recibeRecurso;
    }

    public String getTiempoMaxUso() {
        return tiempoMaxUso;
    }

    public void setTiempoMaxUso(String tiempoMaxUso) {
        this.tiempoMaxUso = tiempoMaxUso;
    }

    public String getCostoxHora() {
        return costoxHora;
    }

    public void setCostoxHora(String costoxHora) {
        this.costoxHora = costoxHora;
    }

    public String getCodigoOrganizacion() {
        return codigoOrganizacion;
    }

    public void setCodigoOrganizacion(String codigoOrganizacion) {
        this.codigoOrganizacion = codigoOrganizacion;
    }

    public Recurso() {
    }

    @Override
    public String toString() {
        return "codigoRecurso" + getCodigoRecurso() + "/n"
                + "nombre" + getNombre() + "/n"
                + "aprobarCodigoUsuario" + getAprobarCodigoUsuario() + "/n"
                + "entregaRecurso" + getEntregaRecurso() + "/n"
                + "recibeRecurso" + getRecibeRecurso() + "/n"
                + "tiempoMaxUso" + getTiempoMaxUso() + "/n"
                + "costoxHora" + getCostoxHora() + "/n"
                + "codigoOrganizacion" + getCodigoOrganizacion() + "/n"
                + "estado" + getEstado() + "/n";

    }
}
