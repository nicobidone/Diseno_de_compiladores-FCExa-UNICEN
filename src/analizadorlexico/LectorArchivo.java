/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

import java.io.*;
/**
 *
 * @author jebu
 */
public class LectorArchivo {
  
    public LectorArchivo( ){
        
    }
    
    public String LeerArchivo (String direccion){
        
        String listaDeCaracteres="";
        FileReader fr = null;
        try {
            fr = new FileReader(direccion);
            int caract = fr.read(); //lee el primer caracter
            //Se recorre el fichero hasta encontrar el carácter -1
            //que marca el final del fichero
            while(caract != -1) {
               listaDeCaracteres+=(char)caract;            
               caract = fr.read();               
            }
        }
        catch (FileNotFoundException e) {
            //Operaciones en caso de no encontrar el fichero
            System.out.println("Error: Fichero no encontrado");
            //Mostrar el error producido por la excepción
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            //Operaciones en caso de error general
            System.out.println("Error de lectura del fichero");
            System.out.println(e.getMessage());
        }
        finally {
            //Operaciones que se harán en cualquier caso. Si hay error o no.
            try {
                //Cerrar el fichero si se ha abierto
                if(fr != null)
                    fr.close();
            }
            catch (IOException e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }
        return listaDeCaracteres;
    }
    
    
}
