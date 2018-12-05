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
public class LineaDeComentario extends AccionSemantica {

    public LineaDeComentario( ) {
        super(1);
    }

    @Override
    public void EjecutarAccion() {
         MatrizDeEstados.lineaDeComentario=MatrizDeEstados.numeroDeLinea;
    }
    
}
