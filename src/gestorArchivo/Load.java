/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorArchivo;

import gestorArchivo.GestorTexto;
import java.io.BufferedReader;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicob
 */
public class Load {
    File archivo;
    JFileChooser seleccionado = new JFileChooser();
    
    
    public BufferedReader Text(JFrame jF_TheFrame){                             
        BufferedReader archivoTexto = null;
        
        if (seleccionado.showDialog(jF_TheFrame,"Abrir") == JFileChooser.APPROVE_OPTION){    
            archivo = seleccionado.getSelectedFile();
            if (archivo.canRead()){
                if (archivo.getName().endsWith("txt")){
                    archivoTexto = GestorTexto.abrirTexto(archivo);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Archivo invalido");
                }
            } 
        }
        
        return archivoTexto;
    }
    
    public String TextCont(JFrame jF_TheFrame) throws FileNotFoundException{
        String linea = "";
        if (seleccionado.showDialog(jF_TheFrame,"Abrir archivo con la codificación") == JFileChooser.APPROVE_OPTION){    
            archivo = seleccionado.getSelectedFile();
            if (archivo.canRead()){
                if (archivo.getName().endsWith("txt")){
                    
                    linea = GestorTexto.getContenido(archivo.getAbsolutePath());
                }
                else{
                    JOptionPane.showMessageDialog(null, "Archivo invalido");
                }
            } 
        }
        return linea;
    }
}
