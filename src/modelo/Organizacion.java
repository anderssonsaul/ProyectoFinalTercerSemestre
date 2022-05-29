/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ander
 */
public class Organizacion {

    private String codigoOrganizacion;
    private String nombre;
    private String direccion;
    private String correo;
    private String ideUsuario;
    private String telefono;
private char estado;

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public String getCodigoOrganizacion() {
        return codigoOrganizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getIdeUsuario() {
        return ideUsuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setCodigoOrganizacion(String codigoOrganizacion) {
        this.codigoOrganizacion = codigoOrganizacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setIdeUsuario(String ideUsuario) {
        this.ideUsuario = ideUsuario;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Organizacion() {
    }
    @Override
    public String toString() {
        return "codigoOrganizacion" + getCodigoOrganizacion()+ "/n"
                + "nombre" + getNombre()+ "/n"
                + "direccion" + getDireccion()+ "/n"
                + "correo" + getCorreo()+ "/n"
                + "ideUsuario" + getIdeUsuario() + "/n"
                + "telefono" + getTelefono() + "/n";

    }

}
