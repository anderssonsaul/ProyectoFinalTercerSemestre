/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author ander
 */
public class ConexionMysql {

    private Connection connection;
    private String usuario = "root";
    private String contrasenia = "1234";
    private String servidor = "localhost";
    private String puerto = "3306";
    private String nombreBD = "recursos";

    private String url = "jdbc:mysql://" + servidor + ":" + puerto + "/" + nombreBD + "?serverTimezone=UTC";
    private String driver = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection() {
        return connection;
    }

    public ConexionMysql() {

        try {
            Class.forName(driver);

            connection = DriverManager.getConnection(url, usuario, contrasenia);
            if (connection != null) {
                System.out.println("conexion realizada exitosamente");

            }
        } catch (Exception e) {
            System.err.println("Error al conectar: " + e.getMessage());
            System.err.println("error " + e.toString());
            e.printStackTrace();
        }
    }

}
