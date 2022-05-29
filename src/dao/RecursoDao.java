/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.ConexionMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.ConexionPoolMySQL;
import modelo.Recurso;
import modelo.MntUsuario;
import modelo.Organizacion;

/**
 *
 * @author ander
 */
public class RecursoDao {

    private ConexionMysql conectar;

    public RecursoDao() {
        this.conectar = new ConexionMysql();
    }

//Se utilizara para hacer el insert de los datos
    public boolean guardar(Recurso recurso) {
        Connection connection = null;
        try {

            String SQL = "INSERT INTO recurso"
                    + "( codigoRecurso,"
                    + " nombre,"
                    + " aprobarCodigoUsuario,"
                    + " entregaRecurso,"
                    + " recibeRecurso,"
                    + " tiempoMaxUso,"
                    + " costoxHora,"
                    + " codigoOrganizacion, estado)"
                    + " VALUES (?, ?, ?, ?, ?, ?,?,?,'A')";

            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            sentencia.setString(1, recurso.getCodigoRecurso());
            sentencia.setString(2, recurso.getNombre());
            sentencia.setString(3, recurso.getAprobarCodigoUsuario());
            sentencia.setString(4, recurso.getEntregaRecurso());
            sentencia.setString(5, recurso.getRecibeRecurso());
            sentencia.setString(6, recurso.getTiempoMaxUso());
            sentencia.setString(7, recurso.getCostoxHora());
            sentencia.setString(8, recurso.getCodigoOrganizacion());

            sentencia.executeUpdate();
            sentencia.close();

            return true;
        } catch (Exception e) {
            System.err.println("Error al registrar un recurso");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return false;
        }
    }

//listara a todas las organizaciones
    public List<Recurso> listaRecurso() {
        Connection connection = null;
        try {
            List<Recurso> listaRecursos = new ArrayList<>();
            String SQL = "SELECT codigoRecurso,"
                    + "    nombre,"
                    + "    aprobarCodigoUsuario,"
                    + "    entregaRecurso,"
                    + "    recibeRecurso,"
                    + "    tiempoMaxUso,"
                    + "    costoxHora,"
                    + "    codigoOrganizacion, estado"
                    + " FROM recurso";
            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            ResultSet data = sentencia.executeQuery();
            while (data.next()) {
                Recurso rec = new Recurso();

                rec.setCodigoRecurso(data.getString(1));
                rec.setNombre(data.getString(2));
                rec.setAprobarCodigoUsuario(data.getString(3));
                rec.setEntregaRecurso(data.getString(4));
                rec.setRecibeRecurso(data.getString(5));
                rec.setTiempoMaxUso(data.getString(6));
                rec.setCostoxHora(data.getString(7));
                rec.setCodigoOrganizacion(data.getString(8));
                rec.setEstado(data.getString(9).charAt(0));
                listaRecursos.add(rec);
            }

            data.close();
            sentencia.close();
            return listaRecursos;
        } catch (Exception e) {
            System.err.println("Error obtener listado de Recursos");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return null;
        }
    }

//editara la organizacion
    public boolean editar(Recurso recurso) {
        Connection connection = null;
        try {
            String SQL = " UPDATE recurso"
                    + " SET"
                    + " nombre = ?,"
                    + " aprobarCodigoUsuario = ?,"
                    + " entregaRecurso = ?,"
                    + " recibeRecurso = ?,"
                    + " tiempoMaxUso = ?,"
                    + " costoxHora = ?,"
                    + " codigoOrganizacion = ?"
                    + " WHERE codigoRecurso = ?";

            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setString(1, recurso.getNombre());
            sentencia.setString(2, recurso.getAprobarCodigoUsuario());
            sentencia.setString(3, recurso.getEntregaRecurso());
            sentencia.setString(4, recurso.getRecibeRecurso());
            sentencia.setString(5, recurso.getTiempoMaxUso());
            sentencia.setString(6, recurso.getCostoxHora());
            sentencia.setString(7, recurso.getCodigoOrganizacion());
            sentencia.setString(8, recurso.getCodigoRecurso());

            sentencia.executeUpdate();
            sentencia.close();
            return true;
        } catch (Exception e) {
            System.err.println("Error al editar el curso");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(String id) {
        Connection connection = null;
        try {
            String SQL = "delete from recurso where codigoRecurso=?";
            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setString(1, id);
            sentencia.executeUpdate();
            sentencia.close();

            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar el recurso");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return false;
        }
    }

    public List<MntUsuario> listaUsuarioxOrganizacion(String CodigoOrganizacion) {
        Connection connection = null;
        try {
            List<MntUsuario> listaUsuario = new ArrayList<>();
            String SQL = " select u.ideUsuario, u.nomUsuario, u.Nombre from usuario as u"
                    + " inner join organizacion as o"
                    + " on u.ideUsuario=o.ideUsuario"
                    + " where o.estado='A'"
                    + " and o.codigoOrganizacion=?"
                    + " group by u.ideUsuario, u.nomUsuario, u.Nombre";

            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            sentencia.setString(1, CodigoOrganizacion);
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
            System.err.println("Error obtener lista de los usuarios pertenecientes a la empresa");
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
            System.err.println("Error obtener el usuario buscado");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return null;
        }
    }

    public List<Organizacion> lstOrganizacion() {
        Connection connection = null;
        try {
            List<Organizacion> lstOrg = new ArrayList<>();
            String SQL = " SELECT codigoOrganizacion,"
                    + "	nombre"
                    + " FROM organizacion"
                    + " where estado='A' ";

            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);

            ResultSet data = sentencia.executeQuery();
            while (data.next()) {
                Organizacion org = new Organizacion();

                org.setCodigoOrganizacion(data.getString(1));
                org.setNombre(data.getString(2));

                lstOrg.add(org);
            }

            data.close();
            sentencia.close();
            return lstOrg;
        } catch (Exception e) {
            System.err.println("Error obtener lista de los usuarios pertenecientes a la empresa");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return null;
        }
    }

    public Organizacion BuscarOrgxCodigo(String codigo) {
        Connection connection = null;
        try {
            //  List<MntUsuario> listaUsuario = new ArrayList<>();
            String SQL = " SELECT codigoOrganizacion,"
                    + "	nombre "
                    + " FROM organizacion"
                    + " where codigoOrganizacion= ? ";

            connection = this.conectar.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            sentencia.setString(1, codigo);
            ResultSet data = sentencia.executeQuery();
            Organizacion organizacion = null;
            while (data.next()) {

                organizacion = new Organizacion();
                organizacion.setCodigoOrganizacion(data.getString(1));
                organizacion.setNombre(data.getString(2));
            }

            data.close();
            sentencia.close();
            return organizacion;
        } catch (Exception e) {
            System.err.println("Error obtener la organizacion");
            System.err.println("Mensaje error:" + e.getMessage());
            System.err.println("Detalle error:");
            e.printStackTrace();
            return null;
        }
    }

}
