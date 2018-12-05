/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accionesSemanticas;

import static analizadorlexico.AnalizadorLexico.*;
import static analizadorlexico.MatrizDeEstados.*;

/**
 *
 * @author jebu
 */
public class EspacioTabulacionYSaltoDeLinea extends AccionSemantica {
   
    public EspacioTabulacionYSaltoDeLinea() {
        super(1);
       
    }

    @Override
    public void EjecutarAccion() {
           //if( posicionEnCodigo != codigo.length() -1 ){
                //System.out.println("posicion en codigo -1 " + (int)codigo.charAt(posicionEnCodigo - 1));
                 
                        
               if((int)codigo.charAt(posicionEnCodigo - 1)== 10 ){// es un salto de linea
                   numeroDeLinea++;
                   buffer.remove(buffer.size()-1);
                   //System.out.println("Salto de linea, lo remueve y suma linea");
                    
               }
                    
                if ((int)codigo.charAt(posicionEnCodigo-1) == 32) { //es un espacio
                    buffer.remove(buffer.size()-1);
                   // System.out.println("Espacio, lo remueve");
                }
                if ((int)codigo.charAt(posicionEnCodigo-1)==9) {
                    buffer.remove(buffer.size()-1);
                    
                }
                
                
            
               // System.out.println((int)codigo.charAt(posicionEnCodigo -1)+ " " + (posicionEnCodigo-1));
          
          //}
           
           
              
       
    }
                      
             
    }
    /* while((((int)codigo.charAt(posicionEnCodigo) == 10)  || ((int)codigo.charAt(posicionEnCodigo)) == 32 ) && posicionEnCodigo != codigo.length() -1 ){
             System.out.println("Entro al while con " + (int)codigo.charAt(posicionEnCodigo));
             if ((int)codigo.charAt(posicionEnCodigo)== 10)
                 numeroDeLinea++;
             posicionEnCodigo++; 
            }*/

