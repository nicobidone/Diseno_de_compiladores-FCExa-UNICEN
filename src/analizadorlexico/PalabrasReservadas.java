package analizadorlexico;
 

import analizadorsintactico.Parser;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PalabrasReservadas {
    public static HashMap<String,Integer> palabrasReservadas;
    
    public PalabrasReservadas(){
        palabrasReservadas = new HashMap<>();
        
        Field[] fields = Parser.class.getDeclaredFields();
        
        List<Field> fieldList = Arrays.asList(fields).
                                    stream().filter(field -> Modifier.
                                        isPublic(field.getModifiers())).
                                            collect(Collectors.toList());
        Boolean b = false;
        for (Field field : fieldList) {
            
            if (b)
                try {
                    palabrasReservadas.put(field.getName().toLowerCase(),field.getInt(field));
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(PalabrasReservadas.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (field.getName().equals("DOBLE")) b=true;                
        }
        
    }
    public static boolean esPalabraReservada(String palabra){
        if ( palabrasReservadas.containsKey(palabra))
                return true;
        return false;
    }
    
    public static Integer getValor(String palabra){
        
        return palabrasReservadas.get(palabra);
    }
    
     public String getString(){
        String salida="";
        for ( String s:this.palabrasReservadas.keySet()){
            salida=salida.concat("lexema->" + s + " idPalabraReservada->" + palabrasReservadas.get(s) + "\n");
        }
        return salida;
    }
}