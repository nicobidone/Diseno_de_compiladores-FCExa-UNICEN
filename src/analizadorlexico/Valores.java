/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author jebu
 */
public class Valores {
    
    private  HashMap<String,Integer> tablaDeValores;
    
    public Valores(){
        tablaDeValores = new HashMap();
        Parser.class.getDeclaredFields();
        Field[] fields = Parser.class.getDeclaredFields();
        
        List<Field> fieldList = Arrays.asList(fields).
                                    stream().filter(field -> Modifier.
                                        isPublic(field.getModifiers())).
                                            collect(Collectors.toList());
        Boolean b = true;                       

        for (Field field : fieldList) {
            
            if (b)
                    try {
                        tablaDeValores.put(field.getName().toLowerCase(),field.getInt(field));
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(Valores.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (field.getName().equals("DOBLE")) b=false;
        }

    }
    
    public HashMap<String,Integer > getTablaDeValores() {
        return tablaDeValores;
    }

    public void setTablaDeValores(HashMap<String,Integer> tablaDeValores) {
        this.tablaDeValores = tablaDeValores;
    }
    public int getValor(String tipo) {
        
        return tablaDeValores.get(tipo);
    }
    
}