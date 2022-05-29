/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.ConexionMysql;
import modelo.Prestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.ConexionPoolMySQL;
import sun.dc.pr.PRError;
import modelo.MntUsuario;
import modelo.Recurso;

/**
 *
 * @author ander
 */
public class PrestamoDao {

    private ConexionMysql conectar;

    public PrestamoDao() {
        this.conectar = new ConexionMysql();
    }
    //Se utilizara para hacer el insert de los datos

    public boolean guardar(Prestamo prestamo) {
        Connection connection = null;
        try {
            /*
                estado del prestamos
                P= Pendiente
                A= Autorizado
                N= Negado
             */
            String SQL = " INSERT INTO prestamos"
                    + " ( "
                    + " nombre,"
                    + " descripcion,"
                    + " fecha,"
                    + " horaInicio,"
                    + " HoraFin,"
                    + " usuarioReserva,"
                    + " codigoRecurso, autorizado) "
                    + " VALUES (?,?,?,?,?,?,?,'P')";

            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            sentencia.setString(1, prestamo.getNombre());
            sentencia.setString(2, prestamo.getDescripcion());
            sentencia.setString(3, prestamo.getFecha());
            sentencia.setString(4, prestamo.getHoraInicio());
            sentencia.setString(5, prestamo.getHoraFin());
            sentencia.setString(6, prestamo.getUsuarioReserva());
            sentencia.setString(7, prestamo.getCodigoRecurso());

            sentencia.executeUpdate();
            sentencia.close();

            return true;
        } catch (Exception e) {
            System.err.println("Error al registrar a la Solicitud del Prestamo");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return false;
        }
    }

//listara a todas las organizaciones
    public List<Prestamo> listaPrestamo() {
        Connection connection = null;
        try {
            List<Prestamo> listaPrest = new ArrayList<>();
            String SQL = "Select idSolicitud,\n"
                    + "nombre,\n"
                    + "descripcion,\n"
                    + "fecha,\n"
                    + "horaInicio,\n"
                    + "HoraFin,\n"
                    + "usuarioReserva,\n"
                    + "autorizado,codigoRecurso from prestamos";
            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            ResultSet data = sentencia.executeQuery();
            while (data.next()) {
                Prestamo prestamo = new Prestamo();

                prestamo.setIdPrestamo(Integer.valueOf(data.getString(1)));
                prestamo.setNombre(data.getString(2));
                prestamo.setDescripcion(data.getString(3));
                prestamo.setFecha(data.getString(4));
                prestamo.setHoraInicio(data.getString(5));
                prestamo.setHoraFin(data.getString(6));
                prestamo.setUsuarioReserva(data.getString(7));
                prestamo.setAutorizado(data.getString(8));
                prestamo.setCodigoRecurso(data.getString(9));

                listaPrest.add(prestamo);
            }

            data.close();
            sentencia.close();
            return listaPrest;
        } catch (Exception e) {
            System.err.println("Error obtener lista de los prestamos");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return null;
        }
    }

//editara la organizacion
    public boolean editar(Prestamo prestamo) {
        Connection connection = null;
        try {
            String SQL = " UPDATE prestamos"
                    + " SET"
                    + " nombre = ?,"
                    + " descripcion = ?,"
                    + " fecha = ?,"
                    + " horaInicio = ?,"
                    + " HoraFin = ?,"
                    + " usuarioReserva = ?,"
                    + " codigoRecurso = ?"
                    + " WHERE idSolicitud = ?";

            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setString(1, prestamo.getNombre());
            sentencia.setString(2, prestamo.getDescripcion());
            sentencia.setString(3, prestamo.getFecha());
            sentencia.setString(4, prestamo.getHoraInicio());
            sentencia.setString(5, prestamo.getHoraFin());
            sentencia.setString(6, prestamo.getUsuarioReserva());
            sentencia.setString(7, prestamo.getCodigoRecurso());
            sentencia.setInt(8, prestamo.getIdPrestamo());

            sentencia.executeUpdate();
            sentencia.close();
            return true;
        } catch (Exception e) {
            System.err.println("Error al editar el prestamo");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return false;
        }
    }

//listara los recursos pendientes de aprobar
    public List<Prestamo> listaPrestamoPendiente() {
        Connection connection = null;
        try {
            List<Prestamo> listaPrest = new ArrayList<>();
            String SQL = "select p.idSolicitud, r.nombre as nom, r.entregaRecurso, r.recibeRecurso, r.costoxHora,\n"
                    + "p.descripcion, p.fecha, p.horaInicio, p.HoraFin, p.UsuarioReserva\n"
                    + " from prestamos as p\n"
                    + "inner join recurso as r\n"
                    + "on p.codigoRecurso = r.codigoRecurso\n"
                    + "where r.estado='A'\n"
                    + "and p.autorizado='P'\n"
                    + "order by p.fecha asc";
            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            ResultSet data = sentencia.executeQuery();
            while (data.next()) {
                Prestamo prestamo = new Prestamo();

                prestamo.setIdPrestamo(Integer.valueOf(data.getString(1)));
                prestamo.setNombre(data.getString(2));
                prestamo.setDescripcion(data.getString(3));
                prestamo.setFecha(data.getString(4));
                prestamo.setHoraInicio(data.getString(5));
                prestamo.setHoraFin(data.getString(6));
                prestamo.setUsuarioReserva(data.getString(7));
                prestamo.setAutorizado(data.getString(8));
                prestamo.setCodigoRecurso(data.getString(9));

                listaPrest.add(prestamo);
            }

            data.close();
            sentencia.close();
            return listaPrest;
        } catch (Exception e) {
            System.err.println("Error obtener lista de los prestamos");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return null;
        }
    }

    public boolean eliminar(int id) {
        Connection connection = null;
        try {
            String SQL = "delete from prestamos where idSolicitud=?";
            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setInt(1, id);
            sentencia.executeUpdate();
            sentencia.close();

            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar el prestamo");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return false;
        }
    }

    public List<MntUsuario> listaUsuarioxIdeUserNombre() {
        Connection connection = null;
        try {
            List<MntUsuario> listaUsuario = new ArrayList<>();
            String SQL = "SELECT ideUsuario,"
                    + "    nomUsuario,"
                    + "    Nombre"
                    + " FROM usuario";

            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);

            ResultSet data = sentencia.executeQuery();
            while (data.next()) {
                MntUsuario usuario = new MntUsuario();

                usuario.setIdeUsuario(data.getString(1));
                usuario.setNomUsuario(data.getString(2));
                usuario.setNombre(data.getString(3));

                listaUsuario.add(usuario);
            }

            data.close();
            sentencia.close();
            return listaUsuario;
        } catch (Exception e) {
            System.err.println("Error obtener lista de los usuarios para el llenado de comboBox");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return null;
        }
    }

    public List<Recurso> listaRecurso() {
        Connection connection = null;
        try {
            List<Recurso> listaRecurso = new ArrayList<>();
            String SQL = "SELECT codigoRecurso,"
                    + "    nombre"
                    + " FROM recurso where estado='A'";

            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);

            ResultSet data = sentencia.executeQuery();
            while (data.next()) {
                Recurso rec = new Recurso();

                rec.setCodigoRecurso(data.getString(1));
                rec.setNombre(data.getString(2));

                listaRecurso.add(rec);
            }

            data.close();
            sentencia.close();
            return listaRecurso;
        } catch (Exception e) {
            System.err.println("Error obtener lista de los Recursos Activos");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return null;
        }
    }

    public MntUsuario BuscarUsuarioxIdeUserNombre(String id) {
        Connection connection = null;
        try {
            //  List<MntUsuario> listaUsuario = new ArrayList<>();
            String SQL = "SELECT ideUsuario,"
                    + "    nomUsuario,"
                    + "    Nombre"
                    + " FROM usuario"
                    + " Where ideUsuario = ? ";

            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            sentencia.setString(1, id);
            ResultSet data = sentencia.executeQuery();
            MntUsuario usuario = null;
            while (data.next()) {

                usuario = new MntUsuario();
                usuario.setIdeUsuario(data.getString(1));
                usuario.setNomUsuario(data.getString(2));
                usuario.setNombre(data.getString(3));
            }

            data.close();
            sentencia.close();
            return usuario;
        } catch (Exception e) {
            System.err.println("Error obtener el cliente buscado");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return null;
        }
    }

    public Recurso BuscarRecursoxCodigo(String id) {
        Connection connection = null;
        try {
            //  List<MntUsuario> listaUsuario = new ArrayList<>();
            String SQL = "SELECT codigoRecurso,"
                    + "    nombre"
                    + " FROM recurso"
                    + " Where codigoRecurso = ? ";

            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            sentencia.setString(1, id);
            ResultSet data = sentencia.executeQuery();
            Recurso recurso = null;
            while (data.next()) {

                recurso = new Recurso();
                recurso.setCodigoRecurso(data.getString(1));
                recurso.setNombre(data.getString(2));
            }

            data.close();
            sentencia.close();
            return recurso;
        } catch (Exception e) {
            System.err.println("Error obtener el recurso por codigo");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return null;
        }
    }
}
