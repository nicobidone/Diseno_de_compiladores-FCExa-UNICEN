/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accionesSemanticas;
 
import analizadorlexico.MatrizDeEstados;
import static analizadorlexico.MatrizDeEstados.buffer;
 
/**
 *
 * @author nicob
 */
public class EliminarUltimoCaracterLeido extends AccionSemantica {
    int pos = 0;
    
    
    
    public EliminarUltimoCaracterLeido(){
        super(1);
        
    }
    
    /*
     retrocede una posicion en el codigo, y remueve el caracter
    */
    @Override
    public void EjecutarAccion(){
       MatrizDeEstados.posicionEnCodigo= MatrizDeEstados.posicionEnCodigo -1;
       MatrizDeEstados.buffer.remove(buffer.size()-1);
       //System.out.println("Elimino ultimo caracter leido");
          
    }

    
 
    
}
