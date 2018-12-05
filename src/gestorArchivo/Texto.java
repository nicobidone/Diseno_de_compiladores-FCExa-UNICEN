/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorArchivo;

/**
 *
 * @author nicob
 */
public class Texto {
    private String texto;
    
    public Texto(){
        this.texto = "";
    }
    
    public void add(String texto){
        this.texto = this.texto.concat(texto+"\n");
    }
    
    public String get(){
        return this.texto;
    }
    
}
