/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicob
 */
public class Estado {
    
    private char idCaracter;
    private List<Character> simboloAceptado = new ArrayList<>();
    private List<Estado> siguientesEstados = new ArrayList<>();
    
    public Estado(char idCaracter){
        this.idCaracter = idCaracter;
    }
    
    public void addEstadoSiguiente(Estado siguiente){
        this.siguientesEstados.add(siguiente);
    }
    
    public void addEstadosSiguientes(List<Estado> siguientes){
        for(Estado siguiente : siguientes){
            this.addEstadoSiguiente(siguiente);
        }        
    }
    
    public Boolean isSiguiente(char car){
        for (Character simbolo : simboloAceptado){
            if (car == simbolo)
                return true;
        }
        return false;
    }
    
    public Estado getSiguiente(char car){
        for (Estado estado : siguientesEstados){
            if (estado.isSiguiente(car))
                return estado;
        }
        return null;
    }   
    
}
