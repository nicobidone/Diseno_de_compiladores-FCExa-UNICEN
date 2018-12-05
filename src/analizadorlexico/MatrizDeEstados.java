/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

 
import analizadorlexico.objetos.ClasesToken;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author jebu
 */
public class MatrizDeEstados   {
    
    int [][] Matriz;
    public static ArrayList <Character> buffer= new ArrayList<Character>();
    HashMap<Character,Integer> Columnas;
    public static int estadoInicial;
    public static int estadoFinal;
    public static int posicionEnCodigo;
    public static int estadoAnterior;
    public static int estadoActual;
    public static int numeroDeLinea;
    public static int lineaDeComentario;
    public static String codigoGuardado;
    public static Token token;
    public static PalabrasReservadas palabrasReservadas;
    public static ClasesToken clasesToken;
    public static Valores valores;
    
    
    
    
    public MatrizDeEstados() {
        this.estadoFinal = 14;
        this.estadoInicial = 0;
        this.Columnas = new HashMap<>();
        this.Matriz = new int[14][19];
        this.posicionEnCodigo=0;
        this.numeroDeLinea=1;
        this.SetColumnas();
        this.SetMatriz();
        clasesToken=new ClasesToken();
        valores = new Valores();
        palabrasReservadas= new PalabrasReservadas();
    }
    public void SetMatriz(){     
        for (int i = 0; i<14;i++)
            for (int j = 0 ; j< 19 ;j++)
                Matriz[i][j]=-1;
        
        Matriz[0][0]=8;
        Matriz[0][1]=3;
        Matriz[0][2]=1;
        Matriz[0][3]=0;
        Matriz[0][4]=0;
        Matriz[0][5]=0;
        Matriz[0][6]=2;
        Matriz[0][7]=this.estadoFinal;
        Matriz[0][8]=8;
        Matriz[0][9]=this.estadoFinal;
        Matriz[0][10]=this.estadoFinal;
        Matriz[0][11]=2;
        Matriz[0][12]=6;
        Matriz[0][13]=6;
        Matriz[0][14]=5;
        Matriz[0][15]=7;
        Matriz[0][16]=9;
        Matriz[0][17]=8;
        Matriz[0][18]=0;
         
          
        
        
     
        Matriz[1][0]=1;
        Matriz[1][1]=1;
        Matriz[1][2]=this.estadoFinal;
        Matriz[1][3]=this.estadoFinal;
        Matriz[1][4]=this.estadoFinal;
        Matriz[1][5]=this.estadoFinal;
        Matriz[1][6]=this.estadoFinal;
        Matriz[1][7]=this.estadoFinal;
        Matriz[1][8]=1;
        Matriz[1][9]=this.estadoFinal;
        Matriz[1][10]=this.estadoFinal;
        Matriz[1][11]=this.estadoFinal;
        Matriz[1][12]=this.estadoFinal;
        Matriz[1][13]=this.estadoFinal;
        Matriz[1][14]=this.estadoFinal;
        Matriz[1][15]=this.estadoFinal;
        Matriz[1][16]=this.estadoFinal;
        Matriz[1][17]=1;
        Matriz[1][18]=this.estadoFinal;
        
        Matriz[2][7]=this.estadoFinal;
        
        Matriz[3][1]=3;
        Matriz[3][2]=4;
        Matriz[3][16]=10;
        
        Matriz[4][8]=this.estadoFinal;
        
        Matriz[5][0]=5;
        Matriz[5][1]=5;
        Matriz[5][2]=5;
        Matriz[5][3]=5;
        Matriz[5][4]=5;
        Matriz[5][5]=5;
        Matriz[5][6]=5;
        Matriz[5][7]=5;
        Matriz[5][8]=5;
        Matriz[5][9]=5;
        Matriz[5][10]=5;
        Matriz[5][11]=5;
        Matriz[5][12]=5;
        Matriz[5][13]=5;
        Matriz[5][14]=this.estadoFinal;
        Matriz[5][15]=5;
        Matriz[5][16]=5;
        Matriz[5][17]=5;
        Matriz[5][18]=5;
        
        
        
        Matriz[6][0]=this.estadoFinal;
        Matriz[6][1]=this.estadoFinal;
        Matriz[6][2]=this.estadoFinal;
        Matriz[6][3]=this.estadoFinal;
        Matriz[6][4]=this.estadoFinal;
        Matriz[6][5]=this.estadoFinal;
        Matriz[6][6]=this.estadoFinal;
        Matriz[6][7]=this.estadoFinal;
        Matriz[6][8]=this.estadoFinal;
        Matriz[6][9]=this.estadoFinal;
        Matriz[6][10]=this.estadoFinal;
        Matriz[6][11]=this.estadoFinal;
        Matriz[6][12]=this.estadoFinal;
        Matriz[6][13]=this.estadoFinal;
        Matriz[6][14]=this.estadoFinal;
        Matriz[6][15]=this.estadoFinal;
        Matriz[6][16]=this.estadoFinal;
        Matriz[6][17]=this.estadoFinal;
        Matriz[6][18]=this.estadoFinal;
        
        
        Matriz[7][0]=7;
        Matriz[7][1]=7;
        Matriz[7][2]=7;
        Matriz[7][3]=7;
        Matriz[7][4]=7;
        Matriz[7][5]=7;
        Matriz[7][6]=7;
        Matriz[7][7]=7;
        Matriz[7][8]=7;
        Matriz[7][9]=7;
        Matriz[7][10]=7;
        Matriz[7][11]=7;
        Matriz[7][12]=7;
        Matriz[7][13]=7;
        Matriz[7][14]=7;
        Matriz[7][15]=this.estadoFinal;
        Matriz[7][16]=7;
        Matriz[7][17]=7;
        Matriz[7][18]=7;
        
        
        Matriz[8][0]=8;
        Matriz[8][1]=this.estadoFinal;
        Matriz[8][2]=8;
        Matriz[8][3]=this.estadoFinal;
        Matriz[8][4]=this.estadoFinal;
        Matriz[8][5]=this.estadoFinal;
        Matriz[8][6]=this.estadoFinal;
        Matriz[8][7]=this.estadoFinal;
        Matriz[8][8]=8;
        Matriz[8][9]=this.estadoFinal;
        Matriz[8][10]=this.estadoFinal;
        Matriz[8][11]=this.estadoFinal;
        Matriz[8][12]=this.estadoFinal;
        Matriz[8][13]=this.estadoFinal;
        Matriz[8][14]=this.estadoFinal;
        Matriz[8][15]=this.estadoFinal;
        Matriz[8][16]=this.estadoFinal;
        Matriz[8][17]=8;
        Matriz[8][18]=this.estadoFinal;
        
        
        Matriz[9][1]=10;
        Matriz[9][17]=11;
        
        Matriz[10][0]=this.estadoFinal;
        Matriz[10][1]=10;
        Matriz[10][2]=this.estadoFinal;
        Matriz[10][3]=this.estadoFinal;
        Matriz[10][4]=this.estadoFinal;
        Matriz[10][5]=this.estadoFinal;
        Matriz[10][6]=this.estadoFinal;
        Matriz[10][7]=this.estadoFinal;
        Matriz[10][8]=this.estadoFinal;
        Matriz[10][9]=this.estadoFinal;
        Matriz[10][10]=this.estadoFinal;
        Matriz[10][11]=this.estadoFinal;
        Matriz[10][12]=this.estadoFinal;
        Matriz[10][13]=this.estadoFinal;
        Matriz[10][14]=this.estadoFinal;
        Matriz[10][15]=this.estadoFinal;
        Matriz[10][16]=this.estadoFinal;
        Matriz[10][17]=11;
        Matriz[10][18]=this.estadoFinal;
        
        
        
        Matriz[11][1]=12;
        Matriz[11][10]=12;
        
        
        Matriz[12][0]=this.estadoFinal;
        Matriz[12][1]=12;
        Matriz[12][2]=this.estadoFinal;
        Matriz[12][3]=this.estadoFinal;
        Matriz[12][4]=this.estadoFinal;
        Matriz[12][5]=this.estadoFinal;
        Matriz[12][6]=this.estadoFinal;
        Matriz[12][7]=this.estadoFinal;
        Matriz[12][8]=this.estadoFinal;
        Matriz[12][9]=this.estadoFinal;
        Matriz[12][10]=this.estadoFinal;
        Matriz[12][11]=this.estadoFinal;
        Matriz[12][12]=this.estadoFinal;
        Matriz[12][13]=this.estadoFinal;
        Matriz[12][14]=this.estadoFinal;
        Matriz[12][15]=this.estadoFinal;
        Matriz[12][16]=this.estadoFinal;
        Matriz[12][17]=this.estadoFinal;
        Matriz[12][18]=this.estadoFinal;
        
        
        
        
    }  
     /**
      * Recorre el codigo,identificando un token,
      * la variable posicionEnCodigo, indica desde que parte del
      * codigo empieza a reconocer.
      */
    
     public Token getToken(String codigo){  
         token = null;
         codigoGuardado=codigo;
         buffer.clear();
         estadoActual=estadoInicial;
         estadoAnterior=-1;
         MatrizDeAccionesSemanticas AccionesSemanticas = new MatrizDeAccionesSemanticas();
         while(estadoActual != estadoFinal && estadoActual != -1  && posicionEnCodigo != codigo.length()){    
           
             //System.out.println("Con el estado " +estadoActual+" y el simbolo  "+""+" "+codigo.charAt(posicionEnCodigo)+ " Voy al estado "+this.getEstadoSiguiente(estadoActual,codigo.charAt(posicionEnCodigo)) );
             estadoAnterior =estadoActual;
             estadoActual = this.getEstadoSiguiente(estadoActual,codigo.charAt(posicionEnCodigo));
            
              /*
               agrega el caracter al buffer, cuando llega a un estado final
               estara guardado el token correspondiente.
             */         
             buffer.add(codigo.charAt(posicionEnCodigo));
             posicionEnCodigo++;   
             /*
             Ejecuta la accion semantica 
             */       
             if ( AccionesSemanticas.getAccionSemantica(estadoAnterior, this.DevolverColumna(codigo.charAt(posicionEnCodigo-1))).getIdentificador() != 0){
                //System.out.println("Hizo accion semantica "+ AccionesSemanticas.getAccionSemantica(estadoAnterior, this.DevolverColumna(codigo.charAt(posicionEnCodigo-1))));
                AccionesSemanticas.getAccionSemantica(estadoAnterior, this.DevolverColumna(codigo.charAt(posicionEnCodigo-1))).EjecutarAccion();              
             }
        // System.out.println("Posicion en codigo "+ posicionEnCodigo + "Tamaño codigo" + codigo.length());
         if(posicionEnCodigo == codigo.length())
             return null;
         }
           return token;
     }
    
     
      /**
      * Dado un estado y un caracter, devuelve el estado 
      * al que pasa.
      */       
   public int getEstadoSiguiente(int estado,char caracter){
         int c = this.DevolverColumna(caracter);
         return this.Matriz[estado][c];
   }
   
    /*
    Devuelve la columna que le corresponde al caracter
    */
    public int DevolverColumna(Character caracter){
       int valorAscii = (int)caracter;
       if (valorAscii == 105 )
           caracter ='i';
       else if (valorAscii == 68)
           caracter = 'D';
           else if (valorAscii>=65 && valorAscii <= 90 || valorAscii>= 97 && valorAscii<=122) //es una letra
                caracter='l';
                    else 
                        if(valorAscii>=48 && valorAscii <= 57) //es un digito
                            caracter='d';
                        else if (Columnas.containsKey(caracter) == false)
                            caracter = '$';

        return Columnas.get(caracter);
    }
    
     public void SetColumnas(){ 
        Columnas.put('l', 0);
        Columnas.put('d', 1);
        Columnas.put('_', 2);
        Columnas.put(' ', 3);
        Columnas.put('\t',4);//espacio
        Columnas.put('\n',5); //salto de linea
        Columnas.put(':', 6);
        Columnas.put('=', 7);
        Columnas.put('i',8);
        Columnas.put('*',9);
        Columnas.put('/',9);
        Columnas.put('(',9);
        Columnas.put(')',9);
        Columnas.put('{',9);
        Columnas.put('}',9);
        Columnas.put(';',9);
        Columnas.put(',',9);
        Columnas.put('+', 10);
        Columnas.put('-', 10);
        Columnas.put('!', 11);
        Columnas.put('<',12);
        Columnas.put('>',13);
        Columnas.put('#',14 );
        Columnas.put('\'',15);
        Columnas.put('.',16);
        Columnas.put('D',17);
        Columnas.put('$', 18);
        
        
    }
   
   
    
    

    public static int getNumeroDeLinea() {
        return numeroDeLinea;
    }

    public static void setNumeroDeLinea(int numeroDeLinea) {
        MatrizDeEstados.numeroDeLinea = numeroDeLinea;
    }
    
    
    
    
    
}
