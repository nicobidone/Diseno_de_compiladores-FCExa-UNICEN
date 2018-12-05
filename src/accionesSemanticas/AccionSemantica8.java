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
 * @author jebu
 */
public class AccionSemantica8 extends AccionSemantica{

    public AccionSemantica8() {
        super(1);
    }

    @Override
    public void EjecutarAccion() {
        AccionSemantica ac3=new AccionSemantica3();
        AccionSemantica des=new DescartarToken();
        boolean valido=true;
        String lexema="";
        for(Character c:buffer){
            if (c == '\n'){
                    valido=false;
                    break;
            }
        }
        
        if (valido == true)
            ac3.EjecutarAccion();
        else{
                System.out.println("\033[31mLINEA : " + MatrizDeEstados.numeroDeLinea + " - ERROR: El String es multilinea");
                des.EjecutarAccion();
        }
            
    }
    
}
