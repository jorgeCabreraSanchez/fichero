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
import java.util.Iterator;
import java.util.stream.Stream;

/**
 *
 * @author jorge
 */
public class AccionesDirectorio {
    private Path directorio;
    
    
    
    public AccionesDirectorio(){
        
    }
    
    public void pasarDirectorio(Path directorio){
        this.directorio =  directorio;
        
    }
    public String listarContenido() throws IOException{
        String devolver = "";
        Stream<Path> lista = Files.list(this.directorio);
        Iterator it = lista.iterator();
        while(it.hasNext()){
            Path pa = (Path) it.next();
            devolver += String.format("%s-4  -->  tamaño: %l-10 Bytes    Modificado a: %T-10 \n", pa.getFileName().toString(),Files.size(pa),Files.getLastModifiedTime(pa));
        }
        if (devolver.isEmpty()){
            devolver = "Este directorio esta vacío";
        }
        return devolver;
    }
    
    
}
