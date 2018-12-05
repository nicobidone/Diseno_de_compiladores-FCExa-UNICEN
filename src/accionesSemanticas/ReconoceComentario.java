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
public class ReconoceComentario extends AccionSemantica{

    public ReconoceComentario( ) {
        super(1);
    }

    @Override
    public void EjecutarAccion() {
        String lexema="";
        for(Character c:buffer){
            lexema+=c;
        }
        System.out.println("Comentario reconocido: Linea - "+MatrizDeEstados.lineaDeComentario+ " " + lexema);
        MatrizDeEstados.buffer.clear();
        MatrizDeEstados.estadoActual=MatrizDeEstados.estadoInicial;
        
    }
    
    
}
