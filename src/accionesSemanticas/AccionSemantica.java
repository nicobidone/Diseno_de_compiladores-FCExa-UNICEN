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
public abstract class AccionSemantica {
    
    int identificador ;
    
    public AccionSemantica(int identificador) {
        this.identificador = identificador;
        
    }
    
    public abstract void EjecutarAccion();
    public  int getIdentificador(){
        return this.identificador;   
    }
        
    public boolean equals(AccionSemantica a){
        
        return this.getIdentificador() == a.getIdentificador();
    }
    
    public String toString(){
        return this.getClass().getSimpleName();
    }
}
