/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.objetos;

/**
 *
 * @author nicob
 */
public abstract class Elemento {
    
    @Override
    public abstract String toString();
    public abstract Elemento getInicio();
    public abstract String getClase();
    public abstract String getTipo();
}
