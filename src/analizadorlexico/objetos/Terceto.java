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
public class Terceto extends Elemento{
    
    private Elemento operando1,operando2;
    private String operador;
    
    public Terceto(){
        this.operador = null;
        this.operando1 = null;
        this.operando2 = null;
    }
    
    public  Terceto(Terceto ter){
        this.operador = ter.getOperador();
        this.operando1 = (Terceto) ter.getOperando1();
        this.operando2 = (Terceto) ter.getOperando2();
    }
    
    public Terceto(String operador, Elemento operando1, Elemento operando2){
        this.operador = operador;
        this.operando1 = operando1;
        this.operando2 = operando2;
    }
    
    @Override
    public String toString(){
        if (this.operando2!=null)
            return "( "+this.operador+" , "+
                        this.operando1.toString()+" , "+
                        this.operando2.toString()+" )";
        else
            return "( "+this.operador+" , "+
                        this.operando1.toString()+" , "+
                        "-"+" )";
    }
    
    public String getOperador(){
        return this.operador;
    }
    
    public Elemento getOperando1(){
        return this.operando1;
    }
    public Elemento getOperando2(){
        return this.operando2;
    }
    
    @Override
    public String getClase() {        
        return (this.operando1).getClase();
    }
    
    @Override
    public Elemento getInicio() {
        if (operando2 != null && operando1.toString().charAt(0) == '[' && operando2.toString().charAt(0) == '['){
            if (((Numero)operando1).toInteger() < ((Numero)operando2).toInteger())
                return operando1.getInicio();
            else
                return operando2.getInicio();
        }
        else if (operando1.toString().charAt(0) == '[')
            return operando1.getInicio();
        else if (operando2 != null && operando2.toString().charAt(0) == '[')
            return operando2.getInicio();
        else
            return this;            
    }

    @Override
    public String getTipo() {
        return this.operando1.getTipo();
    }

}
