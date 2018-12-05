/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accionesSemanticas;

import analizadorlexico.MatrizDeEstados;
import static analizadorlexico.MatrizDeEstados.buffer;
import analizadorlexico.Token;

/**
 *
 * @author jebu
 */
public class AccionSemantica2 extends AccionSemantica {

    public AccionSemantica2() {
       super(1);
    }

    @Override
    public void EjecutarAccion() {
         Integer numero=0;
         String st = "";
         for (Character c : buffer){
             if(c != '_')
                 st+=c;
             else
                 break;
         }
         String lexema="";
               for(Character c:buffer){
                  lexema+=c;
             }
        
         try {
            numero = Integer.parseInt(st.trim());
         }
         catch (RuntimeException e){
             numero= 32769;
         }
         if( numero <= 32768)
             MatrizDeEstados.token = new Token();
         else{
           
                 System.out.println( "LINEA :" + MatrizDeEstados.numeroDeLinea + " - WARNING: El entero  " + lexema + " se reemplazara por el maximo/minimo valor permitido");
                 buffer.clear();
                 buffer.add('3');
                 buffer.add('2');
                 buffer.add('7');
                 buffer.add('6');
                 buffer.add('8');
                 buffer.add('_');
                 buffer.add('i');
                 MatrizDeEstados.token = new Token();
             
         }
              
        }
    
}
         
        
 

     
    

