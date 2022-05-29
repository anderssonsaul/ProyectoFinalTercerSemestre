/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import dao.OrganizacionDao;
import dao.RecursoDao;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import modelo.Recurso;
import modelo.Organizacion;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Toggle;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import modelo.MntUsuario;
import dao.cambioVentana;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class VistaRecursoController implements Initializable {

    private Recurso recurso = new Recurso();
    private RecursoDao recursoDao = new RecursoDao();
    private Recurso recursoSelect;
    private OrganizacionDao organizacionDao = new OrganizacionDao();
private cambioVentana cambiarventana = new cambioVentana();

    @FXML
    private ComboBox<Organizacion> cbCodigoOrganizacion;

    @FXML
    private TextField txtCodigoRecurso;

    @FXML
    private TextField txtNombre;

    @FXML
    private RadioButton rbAprobacionSi;

    @FXML
    private ToggleGroup grpAprobar;

    @FXML
    private RadioButton rbAprobacionNo;

    @FXML
    private ComboBox<MntUsuario> cbUsuarioAprueba;

    @FXML
    private RadioButton rdConfirmEntregRecSi;

    @FXML
    private ToggleGroup grpConfirmEntreRecep;

    @FXML
    private RadioButton rdConfirmEntregRecNo;

    @FXML
    private ComboBox<MntUsuario> cbUsuarioEntrega;

    @FXML
    private ComboBox<MntUsuario> cbUsuarioRecibe;

    @FXML
    private TextField txtTiempoMaxUso;

    @FXML
    private TextField txtCostoHora;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnAtras;

    @FXML
    private TableView<Recurso> tvRecurso;

    @FXML
    private ContextMenu cmOpciones;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recursoDao = new RecursoDao();
        cargarRecurso();
        CargaEditar();
        llenarcbCodigoOrganizacion();
        llenarCbUsuario();
        rbAprobacionSi.setUserData("S");
        rbAprobacionNo.setUserData("N");
//btnCancelar.setDisable(true);
        rdConfirmEntregRecSi.setUserData("S");
        rdConfirmEntregRecNo.setUserData("N");
        grpAprobar.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (grpAprobar.getSelectedToggle() != null) {
                    if (grpAprobar.getSelectedToggle().getUserData().toString() == "S") {
                        cbUsuarioAprueba.setDisable(false);
                    } else {
                        cbUsuarioAprueba.setDisable(true);
                    }
                }
            }
        });
        grpConfirmEntreRecep.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (grpConfirmEntreRecep.getSelectedToggle() != null) {
                    if (grpConfirmEntreRecep.getSelectedToggle().getUserData().toString() == "S") {
                        cbUsuarioEntrega.setDisable(false);
                        cbUsuarioRecibe.setDisable(false);
                    } else {
                        cbUsuarioEntrega.setDisable(true);
                        cbUsuarioRecibe.setDisable(true);
                    }
                }
            }
        });
    }

    private void llenarcbCodigoOrganizacion() {
        //ArrayList<MntUsuario> lstaUser = new ArrayList<>();
        // lstaUser=organizacionDao.listaUsuarioxIdeUserNombre();
        recursoDao = new RecursoDao();
        cbCodigoOrganizacion.getItems().addAll(recursoDao.lstOrganizacion());

        cbCodigoOrganizacion.setConverter(new StringConverter<Organizacion>() {
            @Override
            public String toString(Organizacion object) {
                return object.getCodigoOrganizacion() + " | " + object.getNombre();
            }

            @Override
            public Organizacion fromString(String string) {
                return null;
            }
        });

    }

    private void llenarCbUsuario() {
        ArrayList<MntUsuario> lstaUser = new ArrayList<>();
        // lstaUser=organizacionDao.listaUsuarioxIdeUserNombre();
        organizacionDao = new OrganizacionDao();
        cbUsuarioAprueba.getItems().addAll(organizacionDao.listaUsuarioxIdeUserNombre());
        cbUsuarioEntrega.getItems().addAll(organizacionDao.listaUsuarioxIdeUserNombre());
        cbUsuarioRecibe.getItems().addAll(organizacionDao.listaUsuarioxIdeUserNombre());

        cbUsuarioAprueba.setConverter(new StringConverter<MntUsuario>() {
            @Override
            public String toString(MntUsuario object) {
                return object.getNomUsuario() + " | " + object.getNombre();
            }

            @Override
            public MntUsuario fromString(String string) {
                return null;
            }
        });

        cbUsuarioEntrega.setConverter(new StringConverter<MntUsuario>() {
            @Override
            public String toString(MntUsuario object) {
                return object.getNomUsuario() + " | " + object.getNombre();
            }

            @Override
            public MntUsuario fromString(String string) {
                return null;
            }
        });

        cbUsuarioRecibe.setConverter(new StringConverter<MntUsuario>() {
            @Override
            public String toString(MntUsuario object) {
                return object.getNomUsuario() + " | " + object.getNombre();
            }

            @Override
            public MntUsuario fromString(String string) {
                return null;
            }
        });

    }

    @FXML
    void eventKey(KeyEvent event) {
        //identifica si esta en el txt de usuario o en el de contrasenia
        Object evt = event.getSource();
        if (evt.equals(txtCodigoRecurso)) {
            //evita que se ingresen espacios en blanco
            if (event.getCharacter().equals(" ")) {
                JOptionPane.showMessageDialog(null, "No se permite ingresar espacios en blanco");
                event.consume();
            }
        }
    }

    @FXML
    void eventoClickCancelar(ActionEvent event) {
        recursoSelect = null;
        limpiarPantalla();
        // btnCancelar.setDisable(true);
        txtCodigoRecurso.editableProperty().setValue(true);
    }

    @FXML
    void eventoAtras(ActionEvent event) {
        cambiarventana.cargar("/vista/MenuOpciones.fxml", event);
    }

    @FXML
    void eventoClickGuardar(ActionEvent event) {
        txtCodigoRecurso.editableProperty().setValue(true);
        recurso = new Recurso();
        if (txtCodigoRecurso.getText().length() > 0) {
            recurso.setCodigoRecurso(txtCodigoRecurso.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Debe de ingresar el codigo de su recurso");
            event.consume();
        }

        if (txtNombre.getText().length() > 0) {
            recurso.setNombre(txtNombre.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Debe de ingresar un Nombre");
            event.consume();
        }

        if (cbCodigoOrganizacion.getSelectionModel().getSelectedItem() != null) {
            recurso.setCodigoOrganizacion(cbCodigoOrganizacion.getSelectionModel().getSelectedItem().getCodigoOrganizacion());
        } else {
            JOptionPane.showMessageDialog(null, "Debe de seleccionar una organizacion");
            recurso = new Recurso();
        }
        if (grpAprobar.getSelectedToggle().getUserData().toString() == "S") {

            if (cbUsuarioAprueba.getSelectionModel().getSelectedItem() != null) {
                recurso.setAprobarCodigoUsuario(cbUsuarioAprueba.getSelectionModel().getSelectedItem().getIdeUsuario());
            } else {
                JOptionPane.showMessageDialog(null, "Debe de seleccionar un usuario para Aprobar el recurso");
                recurso = new Recurso();
            }
        } else {
            recurso.setAprobarCodigoUsuario(null);
        }

        if (grpConfirmEntreRecep.getSelectedToggle().getUserData().toString() == "S") {
            if (cbUsuarioEntrega.getSelectionModel().getSelectedItem() != null) {
                recurso.setEntregaRecurso(cbUsuarioEntrega.getSelectionModel().getSelectedItem().getIdeUsuario());
                recurso.setRecibeRecurso(cbUsuarioRecibe.getSelectionModel().getSelectedItem().getIdeUsuario());
            } else {
                JOptionPane.showMessageDialog(null, "Debe de seleccionar un usuario para Entrega/Recepcion el recurso");
                recurso = new Recurso();
            }

        } else {
            recurso.setEntregaRecurso(null);
            recurso.setRecibeRecurso(null);
        }

        if (txtTiempoMaxUso.getText().length() > 0) {
            recurso.setTiempoMaxUso(txtTiempoMaxUso.getText());
        }
        if (txtTiempoMaxUso.getText().length() > 0) {
            recurso.setTiempoMaxUso(txtTiempoMaxUso.getText());
        }
        if (txtCostoHora.getText().length() > 0) {
            recurso.setCostoxHora(txtCostoHora.getText());
        }

        if (recurso.getCodigoRecurso() != null) {

            if (recursoSelect == null) {
                boolean rsp = this.recursoDao.guardar(recurso);
                if (rsp) {
                    JOptionPane.showMessageDialog(null, "Se creo una organizacion");
                    limpiarPantalla();
                    cargarRecurso();
                    //   btnCancelar.setDisable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No fue posible guardar la organizacion");
                    btnCancelar.setDisable(false);
                }
            } else {
                boolean rsp = this.recursoDao.editar(recurso);
                if (rsp) {
                    JOptionPane.showMessageDialog(null, "Se edito el recurso");
                    limpiarPantalla();
                    recursoSelect = null;
                    cargarRecurso();
                    //  btnCancelar.setDisable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No fue posible editar el recurso");
                    btnCancelar.setDisable(false);
                }

            }
        }
    }

    private void limpiarPantalla() {
        txtCodigoRecurso.setText("");
        txtNombre.setText("");
        txtTiempoMaxUso.setText("");
        txtCostoHora.setText("");

        cbCodigoOrganizacion.setValue(null);
        cbUsuarioAprueba.setValue(null);
        cbUsuarioEntrega.setValue(null);
        cbUsuarioRecibe.setValue(null);
    }

    public void cargarRecurso() {
        tvRecurso.getItems().clear();
        tvRecurso.getColumns().clear();
        btnCancelar.setDisable(false);
        List<Recurso> org = this.recursoDao.listaRecurso();

        ObservableList<Recurso> data = FXCollections.observableArrayList(org);

        TableColumn codigoRecurso = new TableColumn("Codigo Recurso");
        codigoRecurso.setCellValueFactory(new PropertyValueFactory("codigoRecurso"));

        TableColumn nombre = new TableColumn("Nombre");
        nombre.setCellValueFactory(new PropertyValueFactory("nombre"));

        TableColumn aprobarCodigoUsuario = new TableColumn("Codigo Usuario Aprobador");
        aprobarCodigoUsuario.setCellValueFactory(new PropertyValueFactory("aprobarCodigoUsuario"));

        TableColumn entregaRecurso = new TableColumn("Cod. Usuario Entrega Recurso");
        entregaRecurso.setCellValueFactory(new PropertyValueFactory("entregaRecurso"));

        TableColumn recibeRecurso = new TableColumn("Cod. Usuario Recibe Recurso");
        recibeRecurso.setCellValueFactory(new PropertyValueFactory("recibeRecurso"));

        TableColumn tiempoMaxUso = new TableColumn("Tiempo maximo de uso");
        tiempoMaxUso.setCellValueFactory(new PropertyValueFactory("tiempoMaxUso"));

        TableColumn costoxHora = new TableColumn("costoxHora");
        costoxHora.setCellValueFactory(new PropertyValueFactory("costoxHora"));

        TableColumn codigoOrganizacion = new TableColumn("Codigo Organizacion");
        codigoOrganizacion.setCellValueFactory(new PropertyValueFactory("codigoOrganizacion"));

        TableColumn estado = new TableColumn("Estado");
        estado.setCellValueFactory(new PropertyValueFactory("estado"));

        tvRecurso.setItems(data);
        tvRecurso.getColumns().addAll(codigoRecurso, nombre, aprobarCodigoUsuario, entregaRecurso, recibeRecurso, tiempoMaxUso, costoxHora, codigoOrganizacion, estado);
    }

    private void CargaEditar() {
        cmOpciones = new ContextMenu();
        MenuItem miEditar = new MenuItem("Editar");
        MenuItem miEliminar = new MenuItem("Eliminar");
        btnCancelar.setDisable(false);
        cmOpciones.getItems().addAll(miEditar, miEliminar);
        miEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tvRecurso.getSelectionModel().getSelectedIndex();
                recursoSelect = tvRecurso.getItems().get(index);
                //   System.out.println(usuarioSelect);

                txtCodigoRecurso.setText(recursoSelect.getCodigoRecurso());
                txtCodigoRecurso.editableProperty().setValue(false);
                txtNombre.setText(recursoSelect.getNombre());
                txtTiempoMaxUso.setText(recursoSelect.getTiempoMaxUso());
                txtCostoHora.setText(recursoSelect.getCostoxHora());
                cbCodigoOrganizacion.setValue(recursoDao.BuscarOrgxCodigo(recursoSelect.getCodigoOrganizacion()));
                if (recursoSelect.getAprobarCodigoUsuario() != null) {
                    rbAprobacionSi.setSelected(true);
                    rbAprobacionNo.setSelected(false);
                    cbUsuarioAprueba.setDisable(false);
                    cbUsuarioAprueba.setValue(recursoDao.BuscarUsuarioxIdeUserNombre(recursoSelect.getAprobarCodigoUsuario()));
                } else {
                    rbAprobacionSi.setSelected(false);
                    rbAprobacionNo.setSelected(true);
                    cbUsuarioAprueba.setDisable(true);
                    cbUsuarioAprueba.setValue(recursoDao.BuscarUsuarioxIdeUserNombre(recursoSelect.getAprobarCodigoUsuario()));
                }
                if (recursoSelect.getEntregaRecurso() != null) {

                    rdConfirmEntregRecSi.setSelected(true);
                    rdConfirmEntregRecNo.setSelected(false);
                    cbUsuarioEntrega.setValue(recursoDao.BuscarUsuarioxIdeUserNombre(recursoSelect.getEntregaRecurso()));
                    cbUsuarioRecibe.setValue(recursoDao.BuscarUsuarioxIdeUserNombre(recursoSelect.getRecibeRecurso()));
                    cbUsuarioEntrega.setDisable(false);
                    cbUsuarioRecibe.setDisable(false);
                } else {
                    rdConfirmEntregRecSi.setSelected(false);
                    rdConfirmEntregRecNo.setSelected(true);
                    cbUsuarioEntrega.setValue(recursoDao.BuscarUsuarioxIdeUserNombre(recursoSelect.getEntregaRecurso()));
                    cbUsuarioRecibe.setValue(recursoDao.BuscarUsuarioxIdeUserNombre(recursoSelect.getRecibeRecurso()));
                    cbUsuarioEntrega.setDisable(true);
                    cbUsuarioRecibe.setDisable(true);
                }
//cbUsuario.setValue(organizacionDao.BuscarUsuarioxIdeUserNombre(organizacionSelect.getIdeUsuario()));
                //ANDY PENDIENTE USUARIO SELECCIONADO
                btnCancelar.setDisable(false);
            }
        });

        miEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tvRecurso.getSelectionModel().getSelectedIndex();
                Recurso eliminarUsuario = tvRecurso.getItems().get(index);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmacion");
                alert.setHeaderText(null);
                alert.setContentText("Seguro de eliminar la organizacion: " + eliminarUsuario.getNombre() + " ?");
                alert.initStyle(StageStyle.UTILITY);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {

                    boolean rsp = recursoDao.eliminar(eliminarUsuario.getCodigoRecurso());
                    if (rsp) {
                        JOptionPane.showMessageDialog(null, "Se elimino una organizacion");
                        limpiarPantalla();
                        recursoSelect = null;
                        cargarRecurso();
                    } else {
                        JOptionPane.showMessageDialog(null, "No fue posible eliminar la organizacion");
                    }
                }
            }
        });
        tvRecurso.setContextMenu(cmOpciones);
    }

}
