/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.objetos;

import analizadorlexico.PalabrasReservadas;
import java.util.ArrayList;
import java.util.List;
 
public class ClasesToken {
    
    public String getClaseToken(ArrayList<Character> buffer){                                                                                   
        String resultado = "Erroneo";
        List<Boolean> metodos = new ArrayList<>();
        metodos.add(this.EsComentario(buffer));
        metodos.add(this.EsCaracterSimple(buffer));
        metodos.add(this.EsString(buffer));
        metodos.add(this.esIdentificador(buffer));
        metodos.add(this.EsEntero(buffer));
        metodos.add(this.EsAsignacion(buffer));
        metodos.add(this.EsDistinto(buffer));
        metodos.add(this.EsMenorIgual(buffer));
        metodos.add(this.EsMayorIgual(buffer));
        metodos.add(this.EsPalabraReservada(buffer));
        metodos.add(this.EsDouble(buffer));
        int i = 0;
        while (i < metodos.size() && !metodos.get(i)) { i++; }
        switch(i){
            case 0: resultado = "Comentario"; 
            break;                
            case 1: resultado = "Caracter simple";
            break;
            case 2: resultado = "string"; 
            break;
            case 3: resultado = "id"; 
            break;
            case 4: resultado = "entero"; 
            break;
            case 5: resultado = "assign"; 
            break;
            case 6: resultado = "no_igual"; 
            break;
            case 7: resultado = "menor_igual"; 
            break;
            case 8: resultado = "mayor_igual"; 
            break;
            case 9: resultado = "Palabra reservada"; 
            break;
            case 10: resultado = "doble"; 
            break;
        }
        return resultado;    
    }
    
    
    public boolean esIdentificador(ArrayList<Character> buffer){
        
        if(buffer.get(0)=='_')
            return true;
        return false;
    }

    private boolean EsEntero(ArrayList<Character> buffer) {
         if ((buffer.size()-1 > 0) && (buffer.get(buffer.size()-1) == 'i') && (buffer.get(buffer.size()-2) == '_'))
                 return true;
         return false;
    }
    
    private boolean EsAsignacion(ArrayList<Character> buffer){
        if(buffer.size() == 2)
                if ((buffer.get(0))==':' && buffer.get(1)== '=')
                    return true;
        return false;
    }
    
    private boolean EsCaracterSimple(ArrayList<Character> buffer){
       if(buffer.size()==1){
        if(buffer.get(0)=='*'||buffer.get(0)=='/'||buffer.get(0)=='(' || buffer.get(0) == ')'||buffer.get(0)=='{'||buffer.get(0)=='}'||buffer.get(0)==';'||buffer.get(0)==',' || buffer.get(0)=='=' || buffer.get(0) == '+'|| buffer.get(0)=='-'|| buffer.get(0) == '<'|| buffer.get(0)=='>')
            return true;
        }
       return false;
        
    }
    
    private boolean EsDistinto(ArrayList<Character> buffer){
        if(buffer.size() == 2)
            if(buffer.get(0)=='!' && buffer.get(1)=='=')
                return true;
        return false;
    }
    
    private boolean EsMenorIgual(ArrayList<Character> buffer){
        if(buffer.size() == 2)  
            if(buffer.get(0)=='<' && buffer.get(1)=='=')
                return true;
        return false;
    }
    
     private boolean EsMayorIgual(ArrayList<Character> buffer){
         if(buffer.size() == 2) 
            if(buffer.get(0)=='>' && buffer.get(1)=='=')
             return true;
        return false;
    }
     
     private boolean EsString(ArrayList<Character> buffer){
         if (buffer.get(0)=='\''){
            return true;
         }
           return false;
     }
     
     private boolean EsPalabraReservada(ArrayList<Character> buffer){
        String lexema="";
        for(Character c:buffer){
            lexema+=c;
        }
        return PalabrasReservadas.esPalabraReservada(lexema);
     }

    private boolean EsComentario(ArrayList<Character> buffer) {
         if (buffer.get(0)=='#')
            return true;
         
         return false;
    }
    
    private Boolean EsDouble(ArrayList<Character> buffer){
        for ( Character c: buffer)
            if (c.equals('.'))
                return true;
        return false;
        
    }
}