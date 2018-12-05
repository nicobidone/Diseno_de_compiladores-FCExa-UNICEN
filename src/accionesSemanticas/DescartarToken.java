/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accionesSemanticas;

import accionesSemanticas.AccionSemantica;
import analizadorlexico.MatrizDeEstados;
import static analizadorlexico.MatrizDeEstados.buffer;
import static analizadorlexico.MatrizDeEstados.clasesToken;

/**
 *
 * @author jebu
 */
public class DescartarToken extends AccionSemantica {

    public DescartarToken() {
        super(1);
        
    }

    
    @Override
    public void EjecutarAccion() {
        String lexema="";
        for(Character c:buffer){
            if (c!='\n')
                lexema+=c;
                else
                    lexema+="";
        }
       System.out.println("\033[31mLINEA : "+ MatrizDeEstados.numeroDeLinea +" - ERROR: El token  "+clasesToken.getClaseToken(MatrizDeEstados.buffer) +" "+ lexema + " fue descartado");
       MatrizDeEstados.buffer.clear();
       MatrizDeEstados.estadoActual=MatrizDeEstados.estadoInicial;
    }
    
}
