/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficheros.Modelo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 *
 * @author jorge
 */
public class AccionesDirectorio {

    private Path directorio;

    public AccionesDirectorio() {

    }

    public void pasarDirectorio(Path directorio) {
        this.directorio = directorio;

    }

    public String listarContenido() {
        String devolver = "";
        try (Stream<Path> lista = Files.list(this.directorio)) {
            Iterator<Path> it = lista.iterator();
            while (it.hasNext()) {
                Path pa = it.next();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH-mm-ss");
                String fecha = formato.format(Files.getLastModifiedTime(pa).toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime());
                devolver += String.format("%s-4  -->  tamaño: %d-10 Bytes    Modificado a: %s-10 \n", pa.getFileName().toString(), Files.size(pa), fecha);
            }
        } catch (IOException ex) {
            devolver = "Hemos tenido un problema mostrando el contenido del directorio";
        }
        if (devolver.isEmpty()) {
            devolver = "Este directorio esta vacío";
        }
        return devolver;
    }

    public String listarContenido(String filt) {
        String devolver = "";

        return devolver;
    }

}
