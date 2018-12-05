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
public class AccionSemantica3 extends AccionSemantica {

    public AccionSemantica3() {
        super(1);
    }

    @Override
    public void EjecutarAccion() {
        MatrizDeEstados.token = new Token();
    }
    
}
