/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.objetos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicob
 */
public class ListaTercetos {
    private List<Terceto> pila;
    
    public ListaTercetos(){
        this.pila = new ArrayList<>();
    }
    
    public void agregar(Terceto ter){
        this.pila.add(ter);
    }
    
    @Override
    public String toString(){
        String res = "";
        for (Terceto terceto : pila) {
            res = res.concat(pila.indexOf(terceto)+". "+terceto.toString()+"\n");
        }
        return res;
    }
    
    public int indexOf(Terceto ter){
        return pila.indexOf(ter);
    }
    
    public Terceto getElement(int i){
            return this.pila.get(i);
    }
    
    public Terceto getTope(){
        return this.getElement(this.size()-1);
    }
    
    public int size(){
        return this.pila.size();
    }
    
    public void eliminar(Terceto t){
        this.pila.remove(t);
    }
    
   public void ordenarIF(){
        if (this.size() > 1){
            
            Terceto elem2 = this.getTope(),
                    elem1 = this.getTope();

            if (elem2.getOperador().equals("BI")){
                this.eliminar(elem2);
                elem1 = this.getTope();
                this.eliminar(elem1);
            }
            else
                this.eliminar(elem1);
            
            ListaTercetos aux = new ListaTercetos();
            Integer limit = ((Numero)elem1.getOperando1()).toInteger()+1;
            while (this.size() > limit ){
                
                aux.agregar(this.getTope());
                this.eliminar(aux.getTope());
            }
            this.agregar(elem1);     
            
            if (elem2.getOperador().equals("BI")){
                Integer fin = ((Numero)elem1.getOperando2()).toInteger(aux),
                        ini = ((Numero)elem2.getOperando1()).toInteger(aux); 
                    ((Numero)elem1.getOperando2()).cambiaLista(this);
                    ((Numero)elem2.getOperando1()).cambiaLista(this);
                                        
                    for (int i = ini ; i <= fin ; i++) {
                        this.agregar(aux.getTope());
                        
                        aux.eliminar(this.getTope());
                    }
                    ((Numero)elem1.getOperando2()).setElemento(aux.getTope());
                    this.agregar(elem2);
            }
            
            while (aux.size() > 0){
                this.agregar(aux.getTope());
                aux.eliminar(this.getTope());
            }
        }
    }
   
    public void ordenarFUN(ListaTercetos closures){
        if (this.size() > 1){
            Terceto elem2 = this.getTope();
            this.eliminar(elem2);
            Terceto elem1 = this.getTope();
            this.eliminar(elem1);
            
            ListaTercetos aux = new ListaTercetos();
            Integer limit = ((Numero)elem1.getOperando2()).toInteger(),limit2=-1;
            Terceto ter = null;
            if (limit-1 > -1)
                ter = this.getElement(limit-1);
            if (ter != null && ter.getOperando1().getClass().getCanonicalName().equals(Numero.class.getCanonicalName())){
                limit2=((Numero)ter.getOperando1()).toInteger();
            }
            if (limit2 > -1 && limit2 < limit && limit2 < limit-1)
                limit = limit2;
            else if (limit2 < limit-1 && limit - 2 > 0)
                limit=-2;            
            
            while (this.size() > 0 && this.size() > limit){
                
                aux.agregar(this.getTope());
                this.eliminar(aux.getTope());
            }
            this.agregar(elem1);
                        
            while (aux.size() > 0 && aux.getTope() != elem2.getOperando1().getInicio()){
                this.agregar(aux.getTope());
                aux.eliminar(this.getTope());
            }
            this.agregar(new Terceto("RI", elem2.getOperando1(), null));            
            
            while (aux.size() > 0){
                this.agregar(aux.getTope());
                aux.eliminar(this.getTope());
            }
            this.agregar(elem2);
            
            closures.agregar(elem1);
        }        
    }
    
    public void ordenarVOID(ListaTercetos closures){
        if (this.size() > 1){
            
            Terceto elem1 = this.getTope();
            this.eliminar(elem1);
            
            ListaTercetos aux = new ListaTercetos();
            Integer limit = ((Numero)elem1.getOperando2()).toInteger();
            while (this.size() > 0 && this.size() > limit){
                
                aux.agregar(this.getTope());
                this.eliminar(aux.getTope());
            }
            this.agregar(elem1);
            
            while (aux.size() > 0){
                this.agregar(aux.getTope());
                aux.eliminar(this.getTope());
            }
            
            closures.agregar(elem1);
        }        
    }
    
    public void ordenarLOOP(){
        if (this.size() > 1){
            
            Terceto elem = this.getTope();
            this.eliminar(elem);
            
            ListaTercetos aux = new ListaTercetos();
            Integer limit = ((Numero)elem.getOperando1()).toInteger();
            while (this.size() > limit ){
                
                aux.agregar(this.getTope());
                this.eliminar(aux.getTope());
            }
            this.agregar(elem);  
            
            while (aux.size() > 0){
                this.agregar(aux.getTope());
                aux.eliminar(this.getTope());
            }
        }
    }
    
}
