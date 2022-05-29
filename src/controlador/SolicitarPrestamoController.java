/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import modelo.Prestamo;
import dao.PrestamoDao;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
import modelo.MntUsuario;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;
import javafx.util.converter.LocalDateStringConverter;
import modelo.Recurso;
import dao.cambioVentana;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class SolicitarPrestamoController implements Initializable {

    private Prestamo prestamo = new Prestamo();
    private PrestamoDao prestamoDao;
    private Prestamo prestamoSelect;
private cambioVentana cambiarventana = new cambioVentana();

    @FXML
    private Button btnAtras;
    @FXML
    private TextField txtNombre;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtIdSolicitud;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TextField txtHoraInicio;

    @FXML
    private TextField txtHoraFin;

    @FXML
    private ComboBox<MntUsuario> cbUsuarioReserva;
    @FXML
    private ComboBox<Recurso> cbCodigoRecurso;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;
    @FXML
    private TableView<Prestamo> tvPrestamo;

    @FXML
    private ContextMenu cmOpciones;

    @FXML
    void eventKey(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dpFecha.setEditable(false);
        dpFecha.setValue(LocalDate.now());
        prestamoDao = new PrestamoDao();
        cargarUsuario();
        CargaEditar();
        llenarCbUsuario();
    }

    @FXML
    void eventoAtras(ActionEvent event) {
        cambiarventana.cargar("/vista/MenuOpciones.fxml", event);
    }

    @FXML
    void eventoClickCancelar(ActionEvent event) {
        prestamoSelect = null;
        limpiarPantalla();
        btnCancelar.setDisable(true);
    }

    @FXML
    void eventoClickGuardar(ActionEvent event) {

        prestamo = new Prestamo();
        if (txtNombre.getText().length() > 0) {
            prestamo.setNombre(txtNombre.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Debe de ingresar el nombre del prestamo");
            event.consume();
        }

        if (txtDescripcion.getText().length() > 0) {
            prestamo.setDescripcion(txtDescripcion.getText());
        }
        if (txtIdSolicitud.getText().length() > 0) {
            prestamo.setIdPrestamo(Integer.valueOf(txtIdSolicitud.getText()));
        }

        if (dpFecha.getValue().toString().length() > 0) {
            prestamo.setFecha(dpFecha.getValue().toString());
        }

        if (txtHoraInicio.getText() != null) {
            prestamo.setHoraInicio(txtHoraInicio.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Debe de ingresar una hora de inicio");
            prestamo = new Prestamo();
        }
        if (txtHoraFin.getText() != null) {
            prestamo.setHoraFin(txtHoraFin.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Debe de ingresar una hora final");
            prestamo = new Prestamo();
        }
        if (cbCodigoRecurso.getSelectionModel().getSelectedItem() != null) {
            prestamo.setCodigoRecurso(cbCodigoRecurso.getSelectionModel().getSelectedItem().getCodigoRecurso());
        } else {
            JOptionPane.showMessageDialog(null, "Debe de seleccionar el recurso que se solicitara");
            prestamo = new Prestamo();
        }
        if (cbUsuarioReserva.getSelectionModel().getSelectedItem() != null) {
            prestamo.setUsuarioReserva(cbUsuarioReserva.getSelectionModel().getSelectedItem().getIdeUsuario());
        } else {
            JOptionPane.showMessageDialog(null, "Debe de seleccionar un usuario para asignarlo a la solicitud");
            prestamo = new Prestamo();
        }
        if (prestamo.getNombre() != null) {

            if (prestamoSelect == null) {
                boolean rsp = this.prestamoDao.guardar(prestamo);
                if (rsp) {
                    JOptionPane.showMessageDialog(null, "Se creo solicitud de prestamo");
                    limpiarPantalla();
                    cargarUsuario();
                    btnCancelar.setDisable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No fue posible guardar la solicitud de prestamo");
                    btnCancelar.setDisable(false);
                }
            } else {
                boolean rsp = this.prestamoDao.editar(prestamo);
                if (rsp) {
                    JOptionPane.showMessageDialog(null, "Se edito el prestamo");
                    limpiarPantalla();
                    prestamoSelect = null;
                    cargarUsuario();
                    btnCancelar.setDisable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No fue posible editar el prestamo");
                    btnCancelar.setDisable(false);
                }

            }
        }

    }

    private void llenarCbUsuario() {
        ArrayList<MntUsuario> lstaUser = new ArrayList<>();
        // lstaUser=organizacionDao.listaUsuarioxIdeUserNombre();
        prestamoDao = new PrestamoDao();
        cbUsuarioReserva.getItems().addAll(prestamoDao.listaUsuarioxIdeUserNombre());
        cbCodigoRecurso.getItems().addAll(prestamoDao.listaRecurso());

        cbUsuarioReserva.setConverter(new StringConverter<MntUsuario>() {
            @Override
            public String toString(MntUsuario object) {
                return object.getNomUsuario() + " | " + object.getNombre();
            }

            @Override
            public MntUsuario fromString(String string) {
                return null;
            }
        });

        cbCodigoRecurso.setConverter(new StringConverter<Recurso>() {
            @Override
            public String toString(Recurso object) {
                return object.getCodigoRecurso() + " | " + object.getNombre();
            }

            @Override
            public Recurso fromString(String string) {
                return null;
            }
        });

    }

    private void limpiarPantalla() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtIdSolicitud.setText("");
        txtHoraInicio.setText("");
        txtHoraFin.setText("");

        //  cbUsuario.getSelectionModel().getSelectedItem().getIdeUsuario()==null;
        cbUsuarioReserva.setValue(prestamoDao.BuscarUsuarioxIdeUserNombre(null));
        cbCodigoRecurso.setValue(null);
//cbUsuario.setValue(MntUsuario);
    }

    public void cargarUsuario() {
        tvPrestamo.getItems().clear();
        tvPrestamo.getColumns().clear();

        List<Prestamo> prest = this.prestamoDao.listaPrestamo();

        ObservableList<Prestamo> data = FXCollections.observableArrayList(prest);

        TableColumn idSolicitud = new TableColumn("idSolicitud");
        idSolicitud.setCellValueFactory(new PropertyValueFactory("idSolicitud"));

        TableColumn nombre = new TableColumn("Nombre");
        nombre.setCellValueFactory(new PropertyValueFactory("nombre"));

        TableColumn descripcion = new TableColumn("Descripcion");
        descripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));

        TableColumn fecha = new TableColumn("Fecha");
        fecha.setCellValueFactory(new PropertyValueFactory("fecha"));

        TableColumn horaInicio = new TableColumn("Hora Inicio");
        horaInicio.setCellValueFactory(new PropertyValueFactory("horaInicio"));

        TableColumn HoraFin = new TableColumn("Hora Fin");
        HoraFin.setCellValueFactory(new PropertyValueFactory("HoraFin"));

        TableColumn usuarioReserva = new TableColumn("Codigo Usuario que reserva");
        usuarioReserva.setCellValueFactory(new PropertyValueFactory("usuarioReserva"));

        TableColumn autorizado = new TableColumn("Estado de Autorizacion");
        autorizado.setCellValueFactory(new PropertyValueFactory("autorizado"));

        TableColumn codigoRecurso = new TableColumn("Codigo del Recurso");
        codigoRecurso.setCellValueFactory(new PropertyValueFactory("codigoRecurso"));

        tvPrestamo.setItems(data);
        tvPrestamo.getColumns().addAll(codigoRecurso, nombre, descripcion, fecha, horaInicio, HoraFin, usuarioReserva, autorizado, idSolicitud);
    }

    private void CargaEditar() {
        cmOpciones = new ContextMenu();
        MenuItem miEditar = new MenuItem("Editar");
        MenuItem miEliminar = new MenuItem("Eliminar");

        cmOpciones.getItems().addAll(miEditar, miEliminar);
        miEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tvPrestamo.getSelectionModel().getSelectedIndex();
                prestamoSelect = tvPrestamo.getItems().get(index);
                //   System.out.println(usuarioSelect);

                txtNombre.setText(prestamoSelect.getNombre());
                txtDescripcion.setText(prestamoSelect.getNombre());
                txtIdSolicitud.setText(String.valueOf(prestamoSelect.getIdPrestamo()));
//                dpFecha.setText(prestamoSelect.getFecha());
                LocalDate d = LocalDate.parse(prestamoSelect.getFecha());
                dpFecha.setValue(d);
                txtHoraInicio.setText(prestamoSelect.getHoraInicio());
                txtHoraFin.setText(prestamoSelect.getHoraFin());

                // organizacionDao.BuscarUsuarioxIdeUserNombre(organizacionSelect.getIdeUsuario());
                cbUsuarioReserva.setValue(prestamoDao.BuscarUsuarioxIdeUserNombre(prestamoSelect.getUsuarioReserva()));
                cbCodigoRecurso.setValue(prestamoDao.BuscarRecursoxCodigo(prestamoSelect.getCodigoRecurso()));
                //ANDY PENDIENTE USUARIO SELECCIONADO
                btnCancelar.setDisable(false);
            }
        });

        miEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tvPrestamo.getSelectionModel().getSelectedIndex();
                Prestamo eliminarPrestamo = tvPrestamo.getItems().get(index);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmacion");
                alert.setHeaderText(null);
                alert.setContentText("Seguro de eliminar la organizacion: " + eliminarPrestamo.getNombre() + " ?");
                alert.initStyle(StageStyle.UTILITY);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {

                    boolean rsp = prestamoDao.eliminar(eliminarPrestamo.getIdPrestamo());
                    if (rsp) {
                        JOptionPane.showMessageDialog(null, "Se elimino el prestamo");
                        limpiarPantalla();
                        prestamoSelect = null;
                        cargarUsuario();
                    } else {
                        JOptionPane.showMessageDialog(null, "No fue posible eliminar el prestamo");
                    }
                }
            }
        });
        tvPrestamo.setContextMenu(cmOpciones);
    }
}
