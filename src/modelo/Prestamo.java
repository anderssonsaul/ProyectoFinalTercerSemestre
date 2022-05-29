/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ander
 */
public class Prestamo {

    private int idPrestamo;
    private String nombre;
    private String descripcion;
    private String fecha;
    private String horaInicio;
    private String HoraFin;
    private String usuarioReserva;
    private String autorizado;
    private String codigoRecurso;

    public String getCodigoRecurso() {
        return codigoRecurso;
    }

    public void setCodigoRecurso(String codigoRecurso) {
        this.codigoRecurso = codigoRecurso;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return HoraFin;
    }

    public String getUsuarioReserva() {
        return usuarioReserva;
    }

    public String getAutorizado() {
        return autorizado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(String HoraFin) {
        this.HoraFin = HoraFin;
    }

    public void setUsuarioReserva(String usuarioReserva) {
        this.usuarioReserva = usuarioReserva;
    }

    public void setAutorizado(String autorizado) {
        this.autorizado = autorizado;
    }

    public Prestamo() {
    }

}
