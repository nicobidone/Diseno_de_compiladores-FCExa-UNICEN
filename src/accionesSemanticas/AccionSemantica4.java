/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accionesSemanticas;

import analizadorlexico.MatrizDeEstados;
import analizadorlexico.Token;

/**
 *
 * @author jebu
 */
public class AccionSemantica4 extends AccionSemantica {

    
    
    public AccionSemantica4(){
        super(1);
    }
    @Override
    public void EjecutarAccion() {
         EliminarUltimoCaracterLeido ult=new EliminarUltimoCaracterLeido();
         ult.EjecutarAccion();
         MatrizDeEstados.token = new Token();
    }
    
}
