/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

import java.util.HashMap;
import java.util.Set;


public class TablaDeSimbolos { 
    
    HashMap<String,Token> tablaDeSimbolos;
    
    public TablaDeSimbolos(){
        tablaDeSimbolos = new HashMap<>();
        
    }

    public HashMap<String, Token> getTablaDeSimbolos() {
        return tablaDeSimbolos;
    }
    
    public void AgregarEntrada(Token token){
        tablaDeSimbolos.put(token.getLexema(), token);
    }
    public void EliminarEntrada(Token token){
        tablaDeSimbolos.remove(token.getLexema());
        System.out.println("Eliminado"+token.getLexema());
    }
    
    public boolean isDeclarado(Token token){
        if (tablaDeSimbolos.get(token.getLexema()) == null)
            return false;
        return true;
    }
    
    public boolean isDeclarado(String token){
        if (tablaDeSimbolos.get(token) == null)
            return false;
        return true;
    }
    
    public Token getToken(String clave){
        return tablaDeSimbolos.get(clave);
    }
    
    public Set<String> getKeys(){
        return this.tablaDeSimbolos.keySet();
    }
        
    public void imprimir(){
       for(String s:this.tablaDeSimbolos.keySet()){
           System.out.println("lexema-> " + s + "    IdToken -> " + tablaDeSimbolos.get(s).getValor() +"   Tipo de token -> "+ tablaDeSimbolos.get(s).getClase());
       }
    }
    
    @Override
    public String toString(){
        String salida="";
        for ( String s:this.tablaDeSimbolos.keySet()){
            salida=salida.concat("lexema->" + s + " idToken->" + tablaDeSimbolos.get(s).getClase() + "\n");
        }
        return salida;
    }

    
}
