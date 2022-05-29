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
public class MenuOpcionesController implements Initializable {

    private cambioVentana cambiarVentana = new cambioVentana();

    @FXML
    private Button btnGestionarOrganizacion;

    @FXML
    private Button btnGestionarUsuario;

    @FXML
    private Button btnGestionarRecurso;

    @FXML
    private Button btnSolicitarPrestamo;

    @FXML
    private Button btnApruebaPrestamo;

    @FXML
    private Button btnReservarRecurso;

    @FXML
    private Button btnAprobarReserva;

    @FXML
    private Button btnEntregaRecibeRecurso;

    @FXML
    private Button btnCargarArchivos;

    @FXML
    private Button btnReporte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void eventoClickAprobarPrestamo(ActionEvent event) {
        cambiarVentana.cargar("/vista/AprobarRecharaSolicitud.fxml", event);
    }

    @FXML
    void eventoClickAprobarReserva(ActionEvent event) {
        cambiarVentana.cargar("/vista/VistaPrincipal.fxml", event);
    }

    @FXML
    void eventoClickCargaArchivos(ActionEvent event) {
        cambiarVentana.cargar("/vista/VistaPrincipal.fxml", event);
    }

    @FXML
    void eventoClickEntregaRecibeRecurso(ActionEvent event) {
        cambiarVentana.cargar("/vista/VistaPrincipal.fxml", event);
    }

    @FXML
    void eventoClickGestionaOrg(ActionEvent event) {
        cambiarVentana.cargar("/vista/VistaMntOrganizacio.fxml", event);
    }

    @FXML
    void eventoClickGestionaRecurso(ActionEvent event) {
        cambiarVentana.cargar("/vista/VistaRecurso.fxml", event);
    }

    @FXML
    void eventoClickGestionaUsuario(ActionEvent event) {
        cambiarVentana.cargar("/vista/VistaMntUsuario.fxml", event);
    }

    @FXML
    void eventoClickReservaRecurso(ActionEvent event) {
        cambiarVentana.cargar("/vista/VistaPrincipal.fxml", event);
    }

    @FXML
    void eventoClickSolicitaPrestamo(ActionEvent event) {
        cambiarVentana.cargar("/vista/SolicitarPrestamo.fxml", event);
    }

    @FXML
    void eventoclickReporte(ActionEvent event) {
        cambiarVentana.cargar("/vista/VistaPrincipal.fxml", event);
    }
}
