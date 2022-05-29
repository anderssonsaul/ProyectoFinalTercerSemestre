/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.ConexionMysql;
import modelo.Organizacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.ConexionPoolMySQL;
import modelo.MntUsuario;

/**
 *
 * @author ander
 */
public class OrganizacionDao {

    private ConexionMysql conectar;

    public OrganizacionDao() {
        this.conectar = new ConexionMysql();
    }

    //Se utilizara para hacer el insert de los datos
    public boolean guardar(Organizacion organizacion) {
        Connection connection = null;
        try {

            String SQL = "INSERT INTO organizacion (codigoOrganizacion,nombre,direccion,telefono,correo,ideUsuario,estado)"
                    + "VALUES (?, ?, ?, ?, ?, ?,'A')";

            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            sentencia.setString(1, organizacion.getCodigoOrganizacion());
            sentencia.setString(2, organizacion.getNombre());
            sentencia.setString(3, organizacion.getDireccion());
            sentencia.setString(4, organizacion.getTelefono());
            sentencia.setString(5, organizacion.getCorreo());
            sentencia.setString(6, organizacion.getIdeUsuario());

            sentencia.executeUpdate();
            sentencia.close();

            return true;
        } catch (Exception e) {
            System.err.println("Error al registrar a la Organizacion");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return false;
        }
    }

//listara a todas las organizaciones
    public List<Organizacion> listaOrganizacion() {
        Connection connection = null;
        try {
            List<Organizacion> listaOrganizacion = new ArrayList<>();
            String SQL = "Select codigoOrganizacion,nombre,direccion,telefono,correo,ideUsuario,estado from organizacion";
            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            ResultSet data = sentencia.executeQuery();
            while (data.next()) {
                Organizacion organizacion = new Organizacion();

                organizacion.setCodigoOrganizacion(data.getString(1));
                organizacion.setNombre(data.getString(2));
                organizacion.setDireccion(data.getString(3));
                organizacion.setTelefono(data.getString(4));
                organizacion.setCorreo(data.getString(5));
                organizacion.setIdeUsuario(data.getString(6));
                organizacion.setEstado(data.getString(7).charAt(0));
                listaOrganizacion.add(organizacion);
            }

            data.close();
            sentencia.close();
            return listaOrganizacion;
        } catch (Exception e) {
            System.err.println("Error obtener lista de las organizaciones");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return null;
        }
    }

//editara la organizacion
    public boolean editar(Organizacion organizacion) {
        Connection connection = null;
        try {
            String SQL = "UPDATE organizacion"
                    + " SET "
                    + " direccion = ?,"
                    + " telefono = ?,"
                    + " correo = ?,"
                    + " ideUsuario = ?"
                    + " WHERE codigoOrganizacion = ?";

            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setString(1, organizacion.getDireccion());
            sentencia.setString(2, organizacion.getTelefono());
            sentencia.setString(3, organizacion.getCorreo());
            sentencia.setString(4, organizacion.getIdeUsuario());
            sentencia.setString(5, organizacion.getCodigoOrganizacion());

            sentencia.executeUpdate();
            sentencia.close();
            return true;
        } catch (Exception e) {
            System.err.println("Error al editar la organizacion");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(String id) {
        Connection connection = null;
        try {
            String SQL = "delete from organizacion where codigoOrganizacion=?";
            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setString(1, id);
            sentencia.executeUpdate();
            sentencia.close();

            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar la organizacion");
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

}
