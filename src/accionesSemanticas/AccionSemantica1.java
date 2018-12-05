/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accionesSemanticas ;
 
 
import analizadorlexico.MatrizDeEstados;
import static analizadorlexico.MatrizDeEstados.buffer;
import static analizadorlexico.MatrizDeEstados.token;
import analizadorlexico.Token;


public class AccionSemantica1 extends AccionSemantica {

    public AccionSemantica1() {
        super(1);
    }

     

    @Override
    public void EjecutarAccion() {
       
         
        AccionSemantica ultC=new EliminarUltimoCaracterLeido();
        ultC.EjecutarAccion();
         String lexema="";
        for(Character c:buffer){
            lexema+=c;
        }
        if(buffer.size()>25){
            System.out.println("LINEA : "+ MatrizDeEstados.numeroDeLinea  + " - ERROR: El identificador "+ lexema + " con tamaño " + buffer.size() + " supera el tamaño maximo");
            AccionSemantica desT=new DescartarToken();
            desT.EjecutarAccion();
        }else
            token = new Token();
             
        
        
       
      
    }
    
    
}
