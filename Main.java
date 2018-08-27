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
public class Main {
    public static void main(String[] args) {
        
        Estado automata = new Estado('0');
        Estado e1 = new Estado('1');
        Estado e2 = new Estado('2');        
        Estado ef = new Estado('f');
        automata.addEstadoSiguiente('_', e1);
        e1.addEstadoSiguiente('n', e2);
        e1.addEstadoSiguiente('l', e2);
        e2.addEstadoSiguiente('n', e2);
        e2.addEstadoSiguiente('l', e2);
        e2.addEstadoSiguiente('o', ef);
        
        String cadena = "_nlnllnnlnlox";
         
        Estado pun = automata;
        for (char car : cadena.toCharArray()){
            System.out.print(car+" ");
            pun = pun.getSiguiente(car);
        }
                
    }
}
