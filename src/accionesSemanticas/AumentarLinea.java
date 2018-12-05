/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accionesSemanticas;

import analizadorlexico.MatrizDeEstados;

/**
 *
 * @author jebu
 */
public class AumentarLinea extends AccionSemantica {

    public AumentarLinea() {
        super(1);
    }

    @Override
    public void EjecutarAccion() {
        AccionSemantica ac7 =new AccionSemantica7();
        MatrizDeEstados.numeroDeLinea++;
        ac7.EjecutarAccion();
    }
    
}
