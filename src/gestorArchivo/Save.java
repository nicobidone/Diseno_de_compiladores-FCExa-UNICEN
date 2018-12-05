/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorArchivo;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author nicob
 */
public class Save {
    File archivo;
    JFileChooser seleccionado = new JFileChooser();
        
    public void Text(JFrame jF_TheFrame, String texto, String dialogo){
        if (seleccionado.showDialog(jF_TheFrame,dialogo) == JFileChooser.APPROVE_OPTION){
            archivo = seleccionado.getSelectedFile();
            //if (archivo.getName().endsWith("txt") ){                                      
                String rta = GestorTexto.guardarTexto(texto, archivo);
                if (rta != null)
                    JOptionPane.showMessageDialog(null, rta);
                else
                    JOptionPane.showMessageDialog(null, "Error");
                }
//            else{                
//                JOptionPane.showMessageDialog(null, "Formato incompatible");
//                }
//            }
    } 
    
    public void TextCont(JFrame jF_TheFrame, String texto, String dialogo){
        if (seleccionado.showDialog(jF_TheFrame,dialogo) == JFileChooser.APPROVE_OPTION){
            archivo = seleccionado.getSelectedFile();
            if (archivo.getName().endsWith("txt")){ 
                String msg = GestorTexto.guardarContenido(texto, archivo.getAbsolutePath());                    
                if (msg != null)
                    JOptionPane.showMessageDialog(null, msg);
                else
                    JOptionPane.showMessageDialog(null, "Error");
                }
            else{                
                JOptionPane.showMessageDialog(null, "Formato incompatible");
            }
        }
    }
}
