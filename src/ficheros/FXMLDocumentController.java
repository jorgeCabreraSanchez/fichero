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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
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
    @FXML
    private Button buttonSalir;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.radiusOperacion1.setUserData("listarContenido");
    }

    @FXML
    private void abrir(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage ventana = new Stage();
        ventana.initModality(Modality.WINDOW_MODAL);        
        Path archivo = directoryChooser.showDialog(ventana).toPath();
        Acciones.pasarDirectorio(archivo);
        this.cadenaRutaEntera.setText(archivo.toAbsolutePath().toString());
        if (!archivo.toString().isEmpty()) {
            this.panelGrid.setDisable(false);
        } else {
            this.panelGrid.setDisable(true);
        }
        if (this.opciones.getSelectedToggle() != null) {
            this.opciones.getSelectedToggle().setSelected(false);
        }
       
    }

    @FXML
    private void ruta(ActionEvent event) {
        Path directorio = Paths.get(this.cadenaRutaEntera.getText());
        if (this.cadenaRutaEntera.getText().isEmpty()) {
            String rutaActual = Paths.get(new File("").getAbsolutePath()).toString();
            this.cadenaRutaEntera.setText(rutaActual);
            Acciones.pasarDirectorio(Paths.get(rutaActual));
            this.panelGrid.setDisable(false);
        } else if (Files.exists(directorio)) {
            Acciones.pasarDirectorio(directorio);
            this.panelGrid.setDisable(false);
        } else {
            this.panelGrid.setDisable(true);
        }
        if (this.opciones.getSelectedToggle() != null) {
            this.opciones.getSelectedToggle().setSelected(false);
        }
    }

    @FXML
    private void operacion1(ActionEvent event) { //Operaciones 1 y 2
        this.resultadoFinal.setText(Acciones.listarContenido(""));

    }

    @FXML
    private void operacion2(ActionEvent event) {
        if (this.opciones.getSelectedToggle() == this.radiusOperacion2) {
            String texto = this.cadenaFiltrado.getText();
            this.resultadoFinal.setText(Acciones.listarContenido(texto));
        }
    }

    @FXML
    private void operacion3(ActionEvent event) {
        this.resultadoFinal.setText(Acciones.listarArchivosLectura());
    }

    @FXML
    private void operacion4(ActionEvent event) {
        if (this.opciones.getSelectedToggle() == this.radiusOperacion4) {
            if (this.cadenaBytes.getText().isEmpty()) {
                this.resultadoFinal.setText(Acciones.listarArchivosTamaño(0));
            } else {
                this.resultadoFinal.setText(Acciones.listarArchivosTamaño(Integer.parseInt(this.cadenaBytes.getText())));
            }
        }
    }

    @FXML
    private void operacion5(ActionEvent event) {
        if (this.opciones.getSelectedToggle() == this.radiusOperacion5) {
            try {
            this.resultadoFinal.setText(Acciones.crearArchivo(this.cadenaNuevoArchivo.getText()));
            } catch (Exception e){
                this.resultadoFinal.setText("No se ha podido crear el archivo");
            }
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }

}
