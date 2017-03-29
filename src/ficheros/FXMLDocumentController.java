/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficheros;

import ficheros.Modelo.AccionesDirectorio;
import java.awt.Window;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

/**
 *
 * @author jorge
 */
public class FXMLDocumentController implements Initializable {

    AccionesDirectorio Acciones = new AccionesDirectorio();
    FileChooser fileChooser = new FileChooser();

    @FXML
    private TextField cadenaRutaEntera;
    @FXML
    private RadioButton operacion1;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }

    @FXML
    private void abrir(ActionEvent event) {
        Path archivo = Paths.get("prueba.txt");
//        fileChooser.showOpenDialog(ownerWindow);
        
        this.cadenaRutaEntera.setText(archivo.toAbsolutePath().toString());
        
        
    }

}
