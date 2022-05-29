/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Prestamo;
import dao.PrestamoDao;
import dao.cambioVentana;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class AprobarRecharaSolicitudController implements Initializable {

    private Prestamo prestamo = new Prestamo();
    private PrestamoDao prestamoDao;
    private Prestamo prestamoSelect;
    private cambioVentana cambiarventana = new cambioVentana();

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnAprobar;

    @FXML
    private Button btnRechazar;

    @FXML
    private TableView<Prestamo> tvSolicitud;

    @FXML
    void eventoClickAprobar(ActionEvent event) {

    }

    @FXML
    void eventoAtras(ActionEvent event) {
        cambiarventana.cargar("/vista/MenuOpciones.fxml", event);
    }

    @FXML
    void eventoClickRechazar(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prestamoDao = new PrestamoDao();
        cargarPrestamos();
    }

    public AprobarRecharaSolicitudController() {
    }

    public void cargarPrestamos() {
        tvSolicitud.getItems().clear();
        tvSolicitud.getColumns().clear();

        List<Prestamo> prest = this.prestamoDao.listaPrestamoPendiente();

        ObservableList<Prestamo> data = FXCollections.observableArrayList(prest);

        TableColumn idSolicitud = new TableColumn("idSolicitud");
        idSolicitud.setCellValueFactory(new PropertyValueFactory("idPrestamo"));

        TableColumn nombre = new TableColumn("r.nombre");
        nombre.setCellValueFactory(new PropertyValueFactory("nom"));

        tvSolicitud.setItems(data);
        tvSolicitud.getColumns().addAll(idSolicitud, nombre);
    }

}
