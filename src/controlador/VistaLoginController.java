/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import modelo.UserDAO;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class VistaLoginController implements Initializable {

    private UserDAO model = new UserDAO();

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private Button btnLogin;

    @FXML
    private void eventKey(KeyEvent event) {

        //identifica si esta en el txt de usuario o en el de contrasenia
        Object evt = event.getSource();
        if (evt.equals(txtUsuario)) {
            //evita que se ingresen espacios en blanco
            if (event.getCharacter().equals(" ")) {
                event.consume();
            }
        } else if (evt.equals(txtContrasenia)) {
            if (event.getCharacter().equals(" ")) {
                event.consume();
            }
        }
    }

    @FXML
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();

        if (evt.equals(btnLogin)) {
            if (!txtUsuario.getText().isEmpty() && !txtContrasenia.getText().isEmpty()) {
                String usuario, contrasenia;
                usuario = txtUsuario.getText();
                contrasenia = txtContrasenia.getText();

                int state = model.login(usuario, contrasenia);
                //JOptionPane.showMessageDialog(null, "Dato: "+String.valueOf(state));
                if (state != -1) {
                    if (state == 1) {
                        //JOptionPane.showMessageDialog(null, "Datos Correctos");
                        loadStage("/vista/MenuOpciones.fxml", event);
                    } else {
                        JOptionPane.showMessageDialog(null, "No es posible iniciar sesión, datos incorrectos", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No es posible iniciar sesión, datos incorrectos", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void loadStage(String url, Event event) {

        try {

            //((Node)(event.getSource())).getScene().getWindow().hide();    
            Object eventSource = event.getSource();
            Node sourceAsNode = (Node) eventSource;
            Scene oldScene = sourceAsNode.getScene();
            Window window = oldScene.getWindow();
            Stage stage = (Stage) window;
            stage.hide();

            Parent root = FXMLLoader.load(getClass().getResource(url));
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();

            newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Platform.exit();
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(VistaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
