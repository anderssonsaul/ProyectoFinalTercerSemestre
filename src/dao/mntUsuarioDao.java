/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.ConexionMysql;
import modelo.MntUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.ConexionPoolMySQL;

/**
 *
 * @author ander
 */
public class mntUsuarioDao {

    private ConexionMysql conectar;

    public mntUsuarioDao() {
        this.conectar = new ConexionMysql();

    }

    public boolean guardar(MntUsuario usuario) {
        Connection connection = null;
        try {

            String SQL = "insert into usuario(ideUsuario,tipo,nomUsuario,contrasenia,Nombre,correo,telefono,direccion)"
                    + "values(?,?,?,?,?,?,?,?)";

            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            sentencia.setString(1, usuario.getIdeUsuario());
            sentencia.setString(2, usuario.getTipo());
            sentencia.setString(3, usuario.getNomUsuario());
            sentencia.setString(4, usuario.getContrasenia());
            sentencia.setString(5, usuario.getNombre());
            sentencia.setString(6, usuario.getCorreo());
            sentencia.setInt(7, usuario.getTelefono());
            sentencia.setString(8, usuario.getDireccion());

            sentencia.executeUpdate();
            sentencia.close();

            return true;
        } catch (Exception e) {
            System.err.println("Error al registrar al usuario");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return false;
        }
        /*finally {
            try {
                if (connection != null) {
                    ConexionPoolMySQL.getInstance().closeConnection(connection);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }*/
    }

    public List<MntUsuario> listaUsuario() {
        Connection connection = null;
        try {
            List<MntUsuario> listaUsuario = new ArrayList<>();
            String SQL = "Select ideUsuario,tipo,nomUsuario,contrasenia,Nombre,correo,telefono, direccion from usuario";
            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            ResultSet data = sentencia.executeQuery();
            while (data.next()) {
                MntUsuario usuario = new MntUsuario();
                usuario.setIdeUsuario(data.getString(1));
                usuario.setTipo(data.getString(2));
                usuario.setNomUsuario(data.getString(3));
                usuario.setContrasenia(data.getString(4));
                usuario.setNombre(data.getString(5));
                usuario.setCorreo(data.getString(6));
                usuario.setTelefono(data.getInt(7));
                usuario.setDireccion(data.getString(8));
                listaUsuario.add(usuario);
            }

            data.close();
            sentencia.close();
            return listaUsuario;
        } catch (Exception e) {
            System.err.println("Error obtener lista de usuario");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return null;
        }
    }

    public boolean editar(MntUsuario usuario) {
        Connection connection = null;
        try {
            String SQL = "update usuario set tipo=?, nomUsuario=?, contrasenia=?,"
                    + " Nombre=?, correo=?, telefono=?, direccion=?"
                    + " where ideUsuario=?";

            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setString(1, usuario.getTipo());
            sentencia.setString(2, usuario.getNomUsuario());
            sentencia.setString(3, usuario.getContrasenia());
            sentencia.setString(4, usuario.getNombre());
            sentencia.setString(5, usuario.getCorreo());
            sentencia.setInt(6, usuario.getTelefono());
            sentencia.setString(7, usuario.getDireccion());
            sentencia.setString(8, usuario.getIdeUsuario());

            sentencia.executeUpdate();
            sentencia.close();
            return true;
        } catch (Exception e) {
            System.err.println("Error obtener editar el usuario");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(String id) {
        Connection connection = null;
        try {
            String SQL = "delete from usuario where ideUsuario=?";
            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setString(1, id);
            sentencia.executeUpdate();
            sentencia.close();

            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar el usuario");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return false;
        }
    }
}
