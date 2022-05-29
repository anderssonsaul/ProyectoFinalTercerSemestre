/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import modelo.MntUsuario;
import dao.mntUsuarioDao;
import java.util.List;
import java.util.Optional;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import dao.cambioVentana;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class VistaMntUsuarioController implements Initializable {

    private cambioVentana cambiarventana = new cambioVentana();
    @FXML
    private Button btnAtras;
    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private PasswordField txtConfirmaContrasenia;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtDireccion;

    @FXML
    private RadioButton rdbAdmin;

    @FXML
    private RadioButton rdbUsuarioSistema;

    @FXML
    private ToggleGroup grupo;

    @FXML
    private Button btnGuarda;

    @FXML
    private Button btnCancelar;

    @FXML
    private TableView<MntUsuario> tvUsuario;

    private MntUsuario usuario = new MntUsuario();
    private mntUsuarioDao mntusuario;
    private MntUsuario usuarioSelect;

    @FXML
    private ContextMenu cmOpciones;

    @FXML
    void eventKey(KeyEvent event) {

        //identifica si esta en el txt de usuario o en el de contrasenia
        Object evt = event.getSource();
        if (evt.equals(txtCodigo)) {
            //evita que se ingresen espacios en blanco
            if (event.getCharacter().equals(" ")) {
                JOptionPane.showMessageDialog(null, "No se permite ingresar espacios en blanco");
                event.consume();
            }
        } else if (evt.equals(txtUsuario)) {
            if (event.getCharacter().equals(" ")) {
                JOptionPane.showMessageDialog(null, "No se permite ingresar espacios en blanco");
                event.consume();
            }
        } else if (evt.equals(txtContrasenia)) {
            if (event.getCharacter().equals(" ")) {
                JOptionPane.showMessageDialog(null, "No se permite ingresar espacios en blanco");
                event.consume();
            }
        } else if (evt.equals(txtConfirmaContrasenia)) {
            if (event.getCharacter().equals(" ")) {
                JOptionPane.showMessageDialog(null, "No se permite ingresar espacios en blanco");
                event.consume();
            }
        } else if (evt.equals(txtCorreo)) {
            if (event.getCharacter().equals(" ")) {
                JOptionPane.showMessageDialog(null, "No se permite ingresar espacios en blanco");
                event.consume();
            }
        } else if (evt.equals(txtTel)) {
            // if (Integer.parseInt(event.getCharacter() )>=0) {
            if (!event.getCharacter().matches("([0-9]*)?")) {
                event.consume();
            }

            //  }
            if (event.getCharacter().equals(" ")) {
                JOptionPane.showMessageDialog(null, "No se permite ingresar espacios en blanco");
                event.consume();
            }

        }
    }

    @FXML
    void eventoClickCancelar(ActionEvent event) {
        usuarioSelect = null;
        limpiarPantalla();
        btnCancelar.setDisable(true);
    }

    @FXML
    void eventoAtras(ActionEvent event) {
        cambiarventana.cargar("/vista/MenuOpciones.fxml", event);
    }

    @FXML
    void eventoClickGuarda(ActionEvent event) {

        usuario = new MntUsuario();
        if (txtCodigo.getText().length() > 0) {
            usuario.setIdeUsuario(txtCodigo.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Debe de ingresar el codigo de su usuario");
            event.consume();
        }

        if (txtUsuario.getText().length() > 0) {
            usuario.setNomUsuario(txtUsuario.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Debe de ingresar un usuario");
            event.consume();
        }

        if (txtContrasenia.getText().length() > 0) {
            usuario.setContrasenia(txtContrasenia.getText());
            if (txtConfirmaContrasenia.getText().length() > 0) {
                if (txtContrasenia.getText().equals(txtConfirmaContrasenia.getText())) {
                    usuario.setContrasenia(txtContrasenia.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Las contrase;as ingresadas son distintas");
                    event.consume();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe de validar su contrasenia");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe de ingresar su contrasenia");
        }

        if (txtCorreo.getText().length() > 0) {
            usuario.setCorreo(txtCorreo.getText());
        }
        if (txtTel.getText().length() > 0 && txtTel.getText().length() == 8) {
            usuario.setTelefono(Integer.parseInt(txtTel.getText()));
        } else {
            JOptionPane.showMessageDialog(null, "El tel. debe de contener 8 numeros");
            usuario = new MntUsuario();
        }
        if (txtDireccion.getText() != null) {
            if (txtDireccion.getText().length() > 0) {
                usuario.setDireccion(txtDireccion.getText());
            }
        }
        if (txtNombre.getText().length() > 0) {
            usuario.setNombre(txtNombre.getText());
        }
        usuario.setTipo(grupo.getSelectedToggle().getUserData().toString());

        if (usuarioSelect == null) {
            boolean rsp = this.mntusuario.guardar(usuario);
            if (rsp) {
                JOptionPane.showMessageDialog(null, "Se guardo un usuario");
                limpiarPantalla();
                cargarUsuario();
            } else {
                JOptionPane.showMessageDialog(null, "No fue posible guardar al usuario");
            }
        } else {
            boolean rsp = this.mntusuario.editar(usuario);
            if (rsp) {
                JOptionPane.showMessageDialog(null, "Se edito un usuario");
                limpiarPantalla();
                usuarioSelect = null;
                cargarUsuario();
            } else {
                JOptionPane.showMessageDialog(null, "No fue posible guardar al usuario");
            }

        }
        btnCancelar.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.mntusuario = new mntUsuarioDao();

        rdbAdmin.setUserData("A");
        rdbUsuarioSistema.setUserData("U");

        /*   grupo.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (grupo.getSelectedToggle() != null) {
                    usuario.setTipo(grupo.getSelectedToggle().getUserData().toString());
                }
            }
        });*/
        cargarUsuario();

        CargaEditar();

    }

    private void limpiarPantalla() {
        txtCodigo.setText("");
        txtUsuario.setText("");
        txtContrasenia.setText("");
        txtConfirmaContrasenia.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
        txtTel.setText("");
        txtDireccion.setText("");
        rdbAdmin.setSelected(true);
        rdbUsuarioSistema.setSelected(false);

    }

    public void cargarUsuario() {
        tvUsuario.getItems().clear();
        tvUsuario.getColumns().clear();

        List<MntUsuario> usuario = this.mntusuario.listaUsuario();

        ObservableList<MntUsuario> data = FXCollections.observableArrayList(usuario);

        TableColumn ideUsuario = new TableColumn("Codigo");
        ideUsuario.setCellValueFactory(new PropertyValueFactory("ideUsuario"));

        TableColumn tipo = new TableColumn("Tipo Usuario");
        tipo.setCellValueFactory(new PropertyValueFactory("tipo"));

        TableColumn user = new TableColumn("Usuario");
        user.setCellValueFactory(new PropertyValueFactory("nomUsuario"));

        TableColumn contrasenia = new TableColumn("Contrasenia");
        contrasenia.setCellValueFactory(new PropertyValueFactory("contrasenia"));

        TableColumn nombre = new TableColumn("Nombre");
        nombre.setCellValueFactory(new PropertyValueFactory("nombre"));

        TableColumn correo = new TableColumn("Correo");
        correo.setCellValueFactory(new PropertyValueFactory("correo"));

        TableColumn tel = new TableColumn("Tel.");
        tel.setCellValueFactory(new PropertyValueFactory("telefono"));

        TableColumn direc = new TableColumn("Direccion");
        direc.setCellValueFactory(new PropertyValueFactory("direccion"));

        tvUsuario.setItems(data);
        tvUsuario.getColumns().addAll(ideUsuario, tipo, user, nombre, correo, tel, direc);
    }

    private void CargaEditar() {
        cmOpciones = new ContextMenu();
        MenuItem miEditar = new MenuItem("Editar");
        MenuItem miEliminar = new MenuItem("Eliminar");

        cmOpciones.getItems().addAll(miEditar, miEliminar);
        miEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tvUsuario.getSelectionModel().getSelectedIndex();
                usuarioSelect = tvUsuario.getItems().get(index);
                //   System.out.println(usuarioSelect);

                txtCodigo.setText(usuarioSelect.getIdeUsuario());
                txtUsuario.setText(usuarioSelect.getNomUsuario());
                txtContrasenia.setText(usuarioSelect.getContrasenia());
                txtConfirmaContrasenia.setText(usuarioSelect.getContrasenia());
                txtNombre.setText(usuarioSelect.getNombre());
                txtCorreo.setText(usuarioSelect.getCorreo());
                txtTel.setText(String.valueOf(usuarioSelect.getTelefono()));
                txtDireccion.setText(usuarioSelect.getDireccion());
                switch (usuarioSelect.getTipo()) {
                    case "A":
                        rdbAdmin.setSelected(true);
                        break;
                    case "U":
                        rdbUsuarioSistema.setSelected(true);
                        break;
                }
                btnCancelar.setDisable(false);
            }
        });

        miEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tvUsuario.getSelectionModel().getSelectedIndex();
                MntUsuario eliminarUsuario = tvUsuario.getItems().get(index);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmacion");
                alert.setHeaderText(null);
                alert.setContentText("Seguro de eliminar el usuario: " + eliminarUsuario.getNomUsuario() + " ?");
                alert.initStyle(StageStyle.UTILITY);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {

                    mntUsuarioDao eliminar = new mntUsuarioDao();
                    boolean rsp = eliminar.eliminar(eliminarUsuario.getIdeUsuario());
                    if (rsp) {
                        JOptionPane.showMessageDialog(null, "Se elimino un usuario");
                        limpiarPantalla();
                        usuarioSelect = null;
                        cargarUsuario();
                    } else {
                        JOptionPane.showMessageDialog(null, "No fue posible guardar al usuario");
                    }
                }
            }
        });
        tvUsuario.setContextMenu(cmOpciones);
    }

}
