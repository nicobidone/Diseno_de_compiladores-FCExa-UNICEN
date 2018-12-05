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
public class Numero extends Elemento{
    
    private Terceto elemento;
    private ListaTercetos lista;
    private Integer mod;
    
    
    public Numero(ListaTercetos lista, Terceto posicion){
        this.elemento = posicion;
        this.lista = lista;
        this.mod = 0;
    }
    
    public Numero(ListaTercetos lista, Terceto posicion, Integer mod){
        this.elemento = posicion;
        this.lista = lista;
        this.mod = mod;
    }

    @Override
    public String getClase() {
        return this.elemento.getClase();
    }
    
    public void setElemento(Terceto elemento){
        this.elemento = elemento;
    }
    
    @Override
    public String toString() {
        int res = this.toInteger()+mod;
        return "["+res+"]";
    }
    
    public Integer toInteger(){
        return this.toInteger(lista);
    }
    
    public void cambiaLista(ListaTercetos lista){
        this.lista = lista;
    }
    
    public String toString(ListaTercetos lista) {
        return "["+this.toInteger(lista).toString()+"]";
    }
    
    public Integer toInteger(ListaTercetos lista){
        cambiaLista(lista);
        return this.lista.indexOf(elemento);
    }
    
    @Override
    public Terceto getInicio(){
        return (Terceto) this.elemento.getInicio();
    }

    @Override
    public String getTipo() {
        return this.elemento.getTipo();
    }

    public int toModInteger(ListaTercetos lista) {
        return this.toInteger(lista)+mod;
    }
    
    public int toModInteger(){
        return this.toInteger()+mod;
    }
    
}
