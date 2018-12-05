/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accionesSemanticas;


import analizadorlexico.MatrizDeEstados;
import analizadorlexico.Token;

 
public class AccionSemantica5 extends AccionSemantica {

    public AccionSemantica5() {
        super(1);
       
    }

    @Override
    public void EjecutarAccion() {
        AccionSemantica des = new DescartarToken();
        AccionSemantica ul = new EliminarUltimoCaracterLeido();
        ul.EjecutarAccion();
        MatrizDeEstados.token = new Token();
        if (MatrizDeEstados.token.getClase().equals("Erroneo"))
          des.EjecutarAccion();
    }
    
}
