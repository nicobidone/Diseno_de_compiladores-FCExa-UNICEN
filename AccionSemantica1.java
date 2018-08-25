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
public class AccionSemantica1 extends AccionSemantica {
    int pos = 0;
    
    public AccionSemantica1(String buffer, char caracter) {
        super(buffer, caracter);
    }
    
    public void aumentar(){
        pos++;
    }
    
    
}
