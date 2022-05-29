/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import dao.OrganizacionDao;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
import modelo.MntUsuario;
import modelo.Organizacion;
import dao.cambioVentana;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class VistaMntOrganizacioController implements Initializable {

    private Organizacion organizacion = new Organizacion();
    private OrganizacionDao organizacionDao;
    private Organizacion organizacionSelect;
    private cambioVentana cambiarventana = new cambioVentana();

    @FXML
    private Button btnAtras;

    @FXML
    private TextField txtCodigoOrganizacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtCorreo;

    @FXML
    private ComboBox<MntUsuario> cbUsuario;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TableView<Organizacion> tvOrganizacion;

    @FXML
    private ContextMenu cmOpciones;

    @FXML
    void eventoAtras(ActionEvent event) {
        cambiarventana.cargar("/vista/MenuOpciones.fxml", event);
    }

    @FXML
    void eventKey(KeyEvent event) {
        //identifica si esta en el txt de usuario o en el de contrasenia
        Object evt = event.getSource();
        if (evt.equals(txtCodigoOrganizacion)) {
            //evita que se ingresen espacios en blanco
            if (event.getCharacter().equals(" ")) {
                JOptionPane.showMessageDialog(null, "No se permite ingresar espacios en blanco");
                event.consume();
            }
        } else if (evt.equals(txtTelefono)) {
            // if (Integer.parseInt(event.getCharacter() )>=0) {
            if (txtTelefono != null) {
                if (!event.getCharacter().matches("([0-9]*)?")) {
                    event.consume();
                }
            }
            if (event.getCharacter().equals(" ")) {
                JOptionPane.showMessageDialog(null, "No se permite ingresar espacios en blanco");
                event.consume();
            }
        }
    }

    @FXML
    void eventoClickCancelar(ActionEvent event) {
        organizacionSelect = null;
        limpiarPantalla();
        btnCancelar.setDisable(true);
        txtCodigoOrganizacion.editableProperty().setValue(true);
    }

    @FXML
    void eventoClickGuardar(ActionEvent event) {
        txtCodigoOrganizacion.editableProperty().setValue(true);
        organizacion = new Organizacion();
        if (txtCodigoOrganizacion.getText().length() > 0) {
            organizacion.setCodigoOrganizacion(txtCodigoOrganizacion.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Debe de ingresar el codigo de su organizacion");
            event.consume();
        }

        if (txtNombre.getText().length() > 0) {
            organizacion.setNombre(txtNombre.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Debe de ingresar un Nombre");
            event.consume();
        }

        if (txtCorreo.getText().length() > 0) {
            organizacion.setCorreo(txtCorreo.getText());
        }

        if (txtTelefono.getText() != null) {
            if (!txtTelefono.getText().trim().equals("")) {
                if (txtTelefono.getText().length() == 8) {
                    organizacion.setTelefono(txtTelefono.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "El tel. debe de contener 8 numeros");
                    organizacion = new Organizacion();
                }
            }
        }
        if (txtCorreo.getText() != null) {
            if (!txtCorreo.getText().trim().equals("")) {
                String regx = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                Pattern pattern = Pattern.compile(regx);
                Matcher matcher = pattern.matcher(txtCorreo.getText());
                if (!matcher.matches()) {
                    JOptionPane.showMessageDialog(null, "El correo electronico no es valido");
                    organizacion = new Organizacion();
                }
            }
        }
        if (txtDireccion.getText() != null) {
            if (txtDireccion.getText().length() > 0) {
                organizacion.setDireccion(txtDireccion.getText());
            }
        }
        if (txtNombre.getText().length() > 0) {
            organizacion.setNombre(txtNombre.getText());
        }
        if (cbUsuario.getSelectionModel().getSelectedItem() != null) {
            organizacion.setIdeUsuario(cbUsuario.getSelectionModel().getSelectedItem().getIdeUsuario());
        } else {
            JOptionPane.showMessageDialog(null, "Debe de seleccionar un usuario para asignarlo a la organizacion");
            organizacion = new Organizacion();
        }
        if (organizacion.getCodigoOrganizacion() != null) {

            if (organizacionSelect == null) {
                boolean rsp = this.organizacionDao.guardar(organizacion);
                if (rsp) {
                    JOptionPane.showMessageDialog(null, "Se creo una organizacion");
                    limpiarPantalla();
                    cargarUsuario();
                    btnCancelar.setDisable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No fue posible guardar la organizacion");
                    btnCancelar.setDisable(false);
                }
            } else {
                boolean rsp = this.organizacionDao.editar(organizacion);
                if (rsp) {
                    JOptionPane.showMessageDialog(null, "Se edito la organizacion");
                    limpiarPantalla();
                    organizacionSelect = null;
                    cargarUsuario();
                    btnCancelar.setDisable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No fue posible guardar la organizacion");
                    btnCancelar.setDisable(false);
                }

            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        organizacionDao = new OrganizacionDao();
        cargarUsuario();
        CargaEditar();
        llenarCbUsuario();
    }

    private void llenarCbUsuario() {
        ArrayList<MntUsuario> lstaUser = new ArrayList<>();
        // lstaUser=organizacionDao.listaUsuarioxIdeUserNombre();
        organizacionDao = new OrganizacionDao();
        cbUsuario.getItems().addAll(organizacionDao.listaUsuarioxIdeUserNombre());

        cbUsuario.setConverter(new StringConverter<MntUsuario>() {
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

    private void limpiarPantalla() {
        txtCodigoOrganizacion.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        //  cbUsuario.getSelectionModel().getSelectedItem().getIdeUsuario()==null;
        cbUsuario.setValue(organizacionDao.BuscarUsuarioxIdeUserNombre(null));
//cbUsuario.setValue(MntUsuario);
    }

    public void cargarUsuario() {
        tvOrganizacion.getItems().clear();
        tvOrganizacion.getColumns().clear();

        List<Organizacion> org = this.organizacionDao.listaOrganizacion();

        ObservableList<Organizacion> data = FXCollections.observableArrayList(org);

        TableColumn codigoOrganizacion = new TableColumn("Codigo");
        codigoOrganizacion.setCellValueFactory(new PropertyValueFactory("codigoOrganizacion"));

        TableColumn nombre = new TableColumn("Nombre");
        nombre.setCellValueFactory(new PropertyValueFactory("nombre"));

        TableColumn direccion = new TableColumn("Direccion");
        direccion.setCellValueFactory(new PropertyValueFactory("direccion"));

        TableColumn correo = new TableColumn("Correo");
        correo.setCellValueFactory(new PropertyValueFactory("correo"));

        TableColumn ideUsuario = new TableColumn("Codigo Usuario");
        ideUsuario.setCellValueFactory(new PropertyValueFactory("ideUsuario"));

        TableColumn telefono = new TableColumn("Telefono");
        telefono.setCellValueFactory(new PropertyValueFactory("telefono"));

        TableColumn estado = new TableColumn("Estado");
        estado.setCellValueFactory(new PropertyValueFactory("estado"));

        tvOrganizacion.setItems(data);
        tvOrganizacion.getColumns().addAll(codigoOrganizacion, nombre, direccion, correo, ideUsuario, telefono, estado);
    }

    private void CargaEditar() {
        cmOpciones = new ContextMenu();
        MenuItem miEditar = new MenuItem("Editar");
        MenuItem miEliminar = new MenuItem("Eliminar");

        cmOpciones.getItems().addAll(miEditar, miEliminar);
        miEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tvOrganizacion.getSelectionModel().getSelectedIndex();
                organizacionSelect = tvOrganizacion.getItems().get(index);
                //   System.out.println(usuarioSelect);

                txtCodigoOrganizacion.setText(organizacionSelect.getCodigoOrganizacion());
                txtCodigoOrganizacion.editableProperty().setValue(false);
                txtNombre.setText(organizacionSelect.getNombre());
                txtDireccion.setText(organizacionSelect.getDireccion());
                txtCorreo.setText(organizacionSelect.getCorreo());
                txtTelefono.setText(organizacionSelect.getTelefono());

                MntUsuario us = new MntUsuario();
                // organizacionDao.BuscarUsuarioxIdeUserNombre(organizacionSelect.getIdeUsuario());

                cbUsuario.setValue(organizacionDao.BuscarUsuarioxIdeUserNombre(organizacionSelect.getIdeUsuario()));
                //ANDY PENDIENTE USUARIO SELECCIONADO
                btnCancelar.setDisable(false);
            }
        });

        miEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tvOrganizacion.getSelectionModel().getSelectedIndex();
                Organizacion eliminarUsuario = tvOrganizacion.getItems().get(index);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmacion");
                alert.setHeaderText(null);
                alert.setContentText("Seguro de eliminar la organizacion: " + eliminarUsuario.getNombre() + " ?");
                alert.initStyle(StageStyle.UTILITY);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {

                    boolean rsp = organizacionDao.eliminar(eliminarUsuario.getCodigoOrganizacion());
                    if (rsp) {
                        JOptionPane.showMessageDialog(null, "Se elimino una organizacion");
                        limpiarPantalla();
                        organizacionSelect = null;
                        cargarUsuario();
                    } else {
                        JOptionPane.showMessageDialog(null, "No fue posible eliminar la organizacion");
                    }
                }
            }
        });
        tvOrganizacion.setContextMenu(cmOpciones);
    }

}
