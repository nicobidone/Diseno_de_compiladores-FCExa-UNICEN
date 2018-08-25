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
public abstract class AccionSemantica {
    String buffer;
    char caracter;
    
    public AccionSemantica(String buffer, char caracter){
        this.buffer = buffer;
        this.caracter = caracter;
    }
    
}
