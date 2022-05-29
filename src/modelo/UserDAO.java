/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ander
 */
public class UserDAO {

    public int login(String user, String password) {

        Connection connection = null;
        PreparedStatement pst;
        ResultSet rs;
        int state = -1;

        try {

            connection = ConexionPoolMySQL.getInstance().getConnection();

            if (connection != null) {

                //  String sql = "SELECT * FROM usuario WHERE BINARY user=? AND pass=AES_ENCRYPT(?, 'key')";
                String sql = "SELECT ideUsuario,tipo,nomUsuario,contrasenia,Nombre,correo,telefono FROM usuario WHERE BINARY nomUsuario=? AND contrasenia=?";

                pst = connection.prepareStatement(sql);
                pst.setString(1, user);
                pst.setString(2, password);

                rs = pst.executeQuery();

                if (rs.next()) {
                    state = 1;
                } else {
                    state = 0;
                }

            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al conectarse con la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error de ejecución, posibles errores:\n"
                    + ex.getMessage());
        } finally {

            try {
                if (connection != null) {
                    ConexionPoolMySQL.getInstance().closeConnection(connection);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

        }

        return state;

    }
}
