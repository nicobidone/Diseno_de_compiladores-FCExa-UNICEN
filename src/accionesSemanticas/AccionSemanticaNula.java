/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accionesSemanticas;

/**
 *
 * @author nicob
 */
public class AccionSemanticaNula extends AccionSemantica {

    public AccionSemanticaNula(int identificador) {
        super(identificador);
    }

    public AccionSemanticaNula() {
        super(0);
    }

    @Override
    public void EjecutarAccion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
