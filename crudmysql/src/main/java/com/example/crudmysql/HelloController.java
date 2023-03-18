package com.example.crudmysql;

import com.example.crudmysql.core.ControllerJugador;
import com.example.crudmysql.model.Jugador;
import com.example.crudmysql.model.Persona;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn<Jugador, Integer> colEdad;

    @FXML
    private TableColumn<Jugador, String> colEquipo;

    @FXML
    private TableColumn<Jugador, Integer> colId;

    @FXML
    private TableColumn<Jugador, String> colNombre;

    @FXML
    private TableColumn<Jugador, String> colPosicion;

    @FXML
    private TableView<Jugador> tblJugador;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtEquipo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPosicion;

    @FXML
    private TextField txtPrimerA;

    @FXML
    private TextField txtRfc;

    @FXML
    private TextField txtSegundoA;

    @FXML
    private TextField txtTelefono;

    ObservableList<Jugador> jugadorList;
    private int jugadorEnTabla;
    ControllerJugador cj;

    public void guardarJugador() throws Exception {
        Persona p = new Persona();
        Jugador j = new Jugador();

        p.setNombre(txtNombre.getText());
        p.setPrimerApellido(txtPrimerA.getText());
        p.setSegundoApellido(txtSegundoA.getText());
        p.setEdad(Integer.parseInt(txtEdad.getText()));
        p.setTelefono(txtTelefono.getText());
        p.setRfc(txtRfc.getText());

        j.setEquipo(txtEquipo.getText());
        j.setPosicion(txtPosicion.getText());
        j.setPersona(p);

        cj.guardarJugador(j);
        refrescarTabla();
    }

    public void modificarJugador() throws Exception {
        Persona p = new Persona();
        Jugador j = new Jugador();

        p.setNombre(txtNombre.getText());
        p.setPrimerApellido(txtPrimerA.getText());
        p.setSegundoApellido(txtSegundoA.getText());
        p.setEdad(Integer.parseInt(txtEdad.getText()));
        p.setTelefono(txtTelefono.getText());
        p.setRfc(txtRfc.getText());

        j.setIdJugador(jugadorList.get(jugadorEnTabla).getIdJugador());
        j.setEquipo(txtEquipo.getText());
        j.setPosicion(txtPosicion.getText());
        j.setPersona(p);

        cj.modificarJugador(j);
        refrescarTabla();
    }

    public void limpiarFormulario(){
        txtNombre.setText("");
        txtPrimerA.setText("");
        txtSegundoA.setText("");
        txtEdad.setText("");
        txtEquipo.setText("");
        txtPosicion.setText("");
        txtRfc.setText("");
        txtTelefono.setText("");
    }

    public void refrescarTabla() throws Exception {
        colNombre.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getPersona().getNombre()));
        colEdad.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getPersona().getEdad()));
        colEquipo.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getEquipo()));
        colId.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getIdJugador()));
        colPosicion.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getPosicion()));

        jugadorList = FXCollections.observableArrayList(cj.getAll());
        tblJugador.setItems(jugadorList);
        limpiarFormulario();
    }

    private final ListChangeListener<Jugador> selectorTabla = new ListChangeListener<Jugador>() {
        @Override
        public void onChanged(Change<? extends Jugador> c) {
            colocarJugadorSel();
        }
    };

    public Jugador getTablaJugador(){
        if(tblJugador != null){
            List<Jugador> tabla = tblJugador.getSelectionModel().getSelectedItems();
            if(tabla.size() == 1){
                final Jugador JugadorSel = tabla.get(0);
                return JugadorSel;
            }
        }
        return null;
    }
    
    public void eliminarJugador() throws Exception {
        cj.eliminarJugador(jugadorList.get(jugadorEnTabla).getIdJugador(),jugadorList.get(jugadorEnTabla).getPersona().getIdPersona());
        refrescarTabla();
    }

    public void colocarJugadorSel(){
        final Jugador jugador = getTablaJugador();
        jugadorEnTabla = jugadorList.indexOf(jugador);

        if(jugador != null){
            txtNombre.setText(jugador.getPersona().getNombre());
            txtPrimerA.setText(jugador.getPersona().getPrimerApellido());
            txtSegundoA.setText(jugador.getPersona().getSegundoApellido());
            txtEdad.setText(String.valueOf(jugador.getPersona().getEdad()));
            txtEquipo.setText(jugador.getEquipo());
            txtPosicion.setText(jugador.getPosicion());
            txtRfc.setText(jugador.getPersona().getRfc());
            txtTelefono.setText(jugador.getPersona().getTelefono());
        }
    }
    
    


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cj = new ControllerJugador();

        try {
            refrescarTabla();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        final ObservableList<Jugador> tablaJugadorSel = tblJugador.getSelectionModel().getSelectedItems();
        tablaJugadorSel.addListener(selectorTabla);

    }

}
