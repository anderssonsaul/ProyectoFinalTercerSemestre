/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ander
 */
public class MntUsuario {

    private String ideUsuario;
    private String tipo;
    private String nomUsuario;
    private String contrasenia;
    private String nombre;
    private String correo;
    private String direccion;
    private int telefono;

    public String getIdeUsuario() {
        return ideUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setIdeUsuario(String ideUsuario) {
        this.ideUsuario = ideUsuario;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public MntUsuario() {
    }

    @Override
    public String toString() {
        return "ideUsuario" + getIdeUsuario() + "/n"
                + "tipo" + getTipo() + "/n"
                + "nomUsuario" + getNomUsuario() + "/n"
                + "contrasenia" + getContrasenia() + "/n"
                + "nombre" + getNombre() + "/n"
                + "correo" + getCorreo() + "/n"
                + "direccion" + getDireccion() + "/n"
                + "telefono" + getDireccion() + "/n";
    }

}
