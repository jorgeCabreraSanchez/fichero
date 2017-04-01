/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficheros.Modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public String listarContenido(String filt) {
        String devolver = "";
        boolean vacio = true;
        try (Stream<Path> lista = Files.list(this.directorio)) {
            Iterator<Path> it = lista.iterator();
            while (it.hasNext()) {
                vacio = false;
                Path pa = it.next();
                if (filt != "") {
                    if (pa.getFileName().toString().contains(filt)) {
                        devolver += contenidoDirectorio(pa) + "\n";
                    }
                } else {
                    devolver += contenidoDirectorio(pa) + "\n";
                }
            }
        } catch (IOException ex) {
            devolver = "Hemos tenido un problema listando el contenido del directorio";
        }
        if (devolver.isEmpty() && vacio == true) {
            devolver = "No se ha encontrado ningun directorio/fichero que contenga esa cadena";
        } else if (devolver.isEmpty()) {
            devolver = "Este directorio esta vacío";
        }
        return devolver;
    }

    private String contenidoDirectorio(Path pa) throws IOException {
        String devolver = "";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH-mm-ss");
        String fecha = formato.format(Files.getLastModifiedTime(pa).toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime());
        if (Files.isDirectory(pa)) {
            devolver = String.format("Directorio     %-27s-->  tamaño: %-9d Bytes    Modificado a: %-1s", pa.getFileName().toString(), Files.size(pa), fecha);
        } else {
            devolver = String.format("Archivo        %-27s-->  tamaño: %-9d Bytes    Modificado a: %-1s", pa.getFileName().toString(), Files.size(pa), fecha);
        }
        return devolver;
    }

    public String listarArchivosLectura() {
        String devolver = "";
        try (Stream<Path> lista = Files.list(this.directorio)) {
            Iterator<Path> it = lista.iterator();
            while (it.hasNext()) {
                Path pa = it.next();
                if (!Files.isDirectory(pa) && Files.isReadable(pa) && !Files.isExecutable(pa) && !Files.isWritable(pa)) {
                    devolver += contenidoDirectorio(pa) + "\n";
                }
            }
        } catch (IOException ex) {
            devolver = "Hemos tenido un problema listando los archivos";
        }
        if (devolver.isEmpty()) {
            devolver = "No se ha encontrado ningún archivo que sea solo lectura";
        }
        return devolver;
    }

    public String listarArchivosTamaño(int numero) {
        String devolver = "";
        try (Stream<Path> lista = Files.list(this.directorio)) {
            Iterator<Path> it = lista.iterator();
            while (it.hasNext()) {
                Path pa = it.next();
                if (Files.size(pa) >= numero && !Files.isDirectory(pa)) {
                    devolver += contenidoDirectorio(pa) + "\n";
                }
            }
        } catch (IOException ex) {
            devolver = "Hemos tenido un problema listando los archivos";
        }
        if (devolver.isEmpty()) {
            devolver = "No se ha encontrado ningún archivo que sea solo lectura";
        }
        return devolver;
    }

    public String crearArchivo(String nombre) {
        String devolver = "";
        if (nombre.isEmpty()) {
            devolver = "Tiene que ponerle un nombre al archivo";
        } else {           
                String ruta = this.directorio.toAbsolutePath().toString();
                Path fichero = Paths.get(ruta + "\\" + nombre + ".txt");
                try (BufferedWriter bw = Files.newBufferedWriter(fichero, StandardOpenOption.CREATE_NEW)){               
                devolver = "Archivo Creado";
            } catch (IOException ex) {
                devolver = "El archivo ya existe";
            }

        }
        return devolver;
    }

}
