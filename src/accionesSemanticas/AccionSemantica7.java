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
public class AccionSemantica7 extends AccionSemantica {

    public AccionSemantica7() {
        super(1);
    }

    

   

    @Override
    public void EjecutarAccion() {
          
         if (MatrizDeEstados.posicionEnCodigo == MatrizDeEstados.codigoGuardado.length())
           if(MatrizDeEstados.buffer.get(0)=='#') 
             System.out.println("\033[31mLINEA : " + MatrizDeEstados.lineaDeComentario + " ERROR: Comentario erroneo, falta cerrar con #");
             else
               System.out.println("\033[31mLINEA : " + MatrizDeEstados.lineaDeComentario + " ERROR: String erroneo, falta cerrar con ' ");
    
    
    } 
   
                
    
}
