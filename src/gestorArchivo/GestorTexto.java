
package gestorArchivo;

import java.io.*;

public class GestorTexto {

    public static BufferedReader abrirTexto(File archivo){
        BufferedReader bw = null;
        try {
            bw = new BufferedReader(new FileReader(archivo));
        } 
        catch (Exception e) { 
            System.out.println("Error E/S 5555");
        }
        return bw;
    } 
     
    public static String guardarTexto(String texto, File archivo){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo,false))){            
            bw.write(texto);
            return "Texto guardado";
        }
        catch(Exception e){
            return "Error E/S 222";
        }
    }
    
    
    // ABRE EL ARCHIVO DADO POR EL PATH Y ESCRIBE EL CONTENIDO. LUEGO LO CIERRA
    public static String guardarContenido(String contenido, String ruta) {
        try {
            FileOutputStream fos = new FileOutputStream(ruta);
            try (DataOutputStream dos = new DataOutputStream(fos)) {
                dos.writeChars(contenido);
            }
            return "Texto guardado";
        }
        catch(IOException e){
            return "Error E/S 222";
        }
    }
    
    public static String getContenido(String ruta) throws FileNotFoundException {
        String contenido = "";
        try {
            FileInputStream fis = new FileInputStream(ruta);
            try (DataInputStream dis = new DataInputStream(fis)) {
                while (dis.available() > 0)
                    contenido += dis.readChar();
            }
        }
        catch(IOException e){
            System.out.println("Error E/S 5555");
        }
        return contenido;
    }
    
}
