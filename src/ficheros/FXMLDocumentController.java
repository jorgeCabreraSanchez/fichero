/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficheros;

import ficheros.Modelo.AccionesDirectorio;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author jorge
 */
public class FXMLDocumentController implements Initializable {

    AccionesDirectorio Acciones = new AccionesDirectorio();

    @FXML
    private TextField cadenaRutaEntera;
    @FXML
    private TextField cadenaFiltrado;
    @FXML
    private TextField cadenaBytes;
    @FXML
    private TextField cadenaNuevoArchivo;
    @FXML
    private Button buttonAbrir;
    @FXML
    private Button buttonEjecutarOpcion;
    @FXML
    private GridPane panelGrid;
    @FXML
    private RadioButton radiusOperacion1;
    @FXML
    private TextArea resultadoFinal;
    @FXML
    private ToggleGroup opciones;
    @FXML
    private RadioButton radiusOperacion2;
    @FXML
    private RadioButton radiusOperacion3;
    @FXML
    private RadioButton radiusOperacion4;
    @FXML
    private RadioButton radiusOperacion5;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.radiusOperacion1.setUserData("listarContenido");
    }

    @FXML
    private void abrir(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Path archivo = directoryChooser.showDialog(new Stage()).toPath();
        Acciones.pasarDirectorio(archivo);
        this.cadenaRutaEntera.setText(archivo.toAbsolutePath().toString());
    }

    @FXML
    private void operacion1(ActionEvent event) {
        if (this.opciones.getSelectedToggle().isSelected()) { 
                this.resultadoFinal.setText(Acciones.listarContenido());            
        }
    }

    @FXML
    private void ruta(ActionEvent event) {
        Path directorio = Paths.get(this.cadenaRutaEntera.getText());
        if (Files.exists(directorio)) {
            Acciones.pasarDirectorio(directorio);
        }
    }

}
