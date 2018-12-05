/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

import analizadorlexico.MatrizDeEstados;
import analizadorlexico.PalabrasReservadas;
import analizadorlexico.objetos.Elemento;
import static analizadorlexico.MatrizDeEstados.buffer;
import static analizadorlexico.MatrizDeEstados.valores;
import static analizadorlexico.MatrizDeEstados.clasesToken;

/**
 *
 * @author jebu
 */
public class Token extends Elemento{
    
    private String clase; //identificador,constante,etc
    private String lexema; //Para el caso de los identificadores
    private String tipo;
    private int valor;
    private int linea ;
    private int verdadero;
    
    public Token(){ 
        this.inic();
        this.verdadero = -1;
    }
    
    public Token(int verdadero){ 
        this.inic();
        this.verdadero = verdadero;
    }
    
    public final void inic(){
        lexema="";
        buffer.forEach((c) -> {
            lexema+=c;
        });
        this.clase =clasesToken.getClaseToken(buffer);
        
        switch (this.clase) {
            case "Caracter simple":
                this.valor = (int)lexema.charAt(0);
                break;
            case "Palabra reservada":
                this.valor = PalabrasReservadas.getValor(lexema);
                break;
            case "Erroneo":
                this.valor=-1;
                break;
            default:
                this.valor = valores.getValor(clase);
                break;
        }
        
        switch (this.clase){
            case "entero":
                this.tipo = "integer";
                break;
            case "doble":
                this.tipo = "double";
                break;
            default:
                this.tipo = "Desconocido";
                break;
        }
        
        this.linea = MatrizDeEstados.numeroDeLinea;
    }
    
    public Token(String lexema,String tipo,int valor,int linea){
        this.linea=linea;
        this.lexema=lexema;
        this.clase=tipo;
        this.valor=valor;
        this.verdadero=-1;
        switch (this.clase){
            case "entero":
                this.tipo = "integer";
                break;
            case "doble":
                this.tipo = "double";
                break;
            default:
                this.tipo = "Desconocido";
                break;
        }
    }
    
    public int getVerdadero(){
        return verdadero;
    }  
    
    public int getLinea() {
        return linea;
    }

    public void setLinea(int Linea) {
        this.linea = Linea;
    }
    
    public void setClase(String clase) {
        this.clase = clase;
    }
     
    public void setIdentificador(int valor) {
        this.valor = valor;
    }
         
    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    @Override
    public String getClase(){
        return this.clase;
    }
    
    @Override
    public String getTipo() {
        return this.tipo;
    }
    
    @Override
    public String toString(){
        return this.lexema;
    }

    @Override
    public Elemento getInicio() {
        return this;
    }
    
    public float dobleToNumber(){
        if (this.clase.equals("doble")){
            String res=this.lexema,val="";
            int x=0;
            if (this.lexema.charAt(x)=='-') x++;
            int indexOfDot = this.lexema.indexOf(".");
            for (int i = x; i < indexOfDot; i++) {
                val = val + this.lexema.charAt(i);            
            }
            x=indexOfDot+1;
            while(x < this.lexema.length() && this.lexema.charAt(x)!='D'){
                val = val + this.lexema.charAt(x);
                x++;
            }

            if (this.lexema.contains("D")){
                int indexOfD = this.lexema.indexOf("D");

                if(this.lexema.charAt(indexOfD+1)=='-'){
                    int numb2 = Integer.parseInt(this.lexema.substring(indexOfD+2,this.lexema.length()));
                    res = "0.";                
                    for (int i = 0; i < numb2-1 ; i++) {
                        res = res +"0";                    
                    }
                    res = res + val;
                }
                else{
                    int numb = Integer.parseInt(this.lexema.substring(indexOfD+1,this.lexema.length()));
                    for (int i = 0; i < numb ; i++) {
                        res = res +"0";                    
                    }
                    res = val + res;
                }
            }
            if (this.lexema.charAt(0)=='-'){
                res = "-" + res;
            }        
            return Float.parseFloat(res);
        }
        return Float.parseFloat("0");
    }
    
    
}
