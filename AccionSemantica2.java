/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author nicob
 */
public class AccionSemantica2 extends AccionSemantica{
    
    public AccionSemantica2(String buffer, char caracter) {
        super(buffer, caracter);
    }
    
    public Boolean chequear(String cadena){
        if (cadena.length() < 25)
            return true;
        return false;            
    }
    
}
