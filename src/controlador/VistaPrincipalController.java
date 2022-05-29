/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import dao.cambioVentana;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class VistaPrincipalController implements Initializable {

    private cambioVentana cambiarventana = new cambioVentana();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private Button btnAtras;

    @FXML
    void eventoAtras(ActionEvent event) {
        cambiarventana.cargar("/vista/MenuOpciones.fxml", event);
    }

}
