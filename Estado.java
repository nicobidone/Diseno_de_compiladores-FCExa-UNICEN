/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nicob
 */
public class Estado {
    
    private char idCaracter;
    private Map<Character,Estado> siguientesEstados = new HashMap<>();
    
    public Estado(char idCaracter){
        this.idCaracter = idCaracter;
    }
    
    public char getId(){
        return this.idCaracter;
    }
    
    public void addEstadoSiguiente(Character car, Estado siguiente){
        this.siguientesEstados.put(car, siguiente);
    }        
    
    private Boolean isSiguiente(char car){
        if (siguientesEstados.containsKey(car)){
            System.out.println("existe "+this.getId());
            return true;
        }
        System.out.println("no existe "+this.getId());
        return false;
    }
    
    public Estado getSiguiente(char car){
        if (this.isSiguiente(car)){
            return siguientesEstados.get(car);
        }
        return null;
    }   
    
}
