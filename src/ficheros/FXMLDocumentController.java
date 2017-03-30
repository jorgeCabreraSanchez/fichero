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
    private RadioButton operacion2;
    @FXML
    private RadioButton operacion3;
    @FXML
    private TextField cadenaFiltrado;
    @FXML
    private RadioButton operacion4;
    @FXML
    private TextField cadenaBytes;
    @FXML
    private RadioButton operacion5;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
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
        try {
            this.resultadoFinal.setText(Acciones.listarContenido());
        } catch (IOException ex) {
            this.resultadoFinal.setText("Hemos tenido un problema mostrando el contenido del directorio");
        }
    }

    @FXML
    private void ruta(ActionEvent event) {
        Path directorio = Paths.get(this.cadenaRutaEntera.getText());
        if(Files.exists(directorio)){
            Acciones.pasarDirectorio(directorio);
        }
    }
    
    

}
