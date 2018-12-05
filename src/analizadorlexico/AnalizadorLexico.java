/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

/**
 *
 * @author jebu
 */
public class AnalizadorLexico {

LectorArchivo lector;
public static String codigo;
public MatrizDeEstados matrizDeEstados;
public static TablaDeSimbolos tablaDeSimbolos;
    
    
    public AnalizadorLexico(String codigo){
        this.codigo=codigo;
        this.tablaDeSimbolos = new TablaDeSimbolos();
        this.matrizDeEstados = new MatrizDeEstados();
    }
     
    public Token getNextToken(){
        
        Token token = this.matrizDeEstados.getToken(this.getCodigo());

        if (token != null){
            if (token.getClase().equals("id")  || token.getClase().equals("entero") || token.getClase().equals("string") || token.getClase().equals("doble")){
                if (!tablaDeSimbolos.isDeclarado(token.getLexema())){
                    tablaDeSimbolos.AgregarEntrada(token);
                    //System.out.println("\t\tAñade:"+token);
                }
            }
        }
        return token;
    }
    
    public static TablaDeSimbolos getTablaDeSimbolos(){
        return AnalizadorLexico.tablaDeSimbolos;
    }
    
    public void setLector(LectorArchivo lector) {
        this.lector = lector;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public void setMatrizDeEstados(MatrizDeEstados matrizDeEstados) {
        this.matrizDeEstados = matrizDeEstados;
    }

    public LectorArchivo getLector() {
        return lector;
    }

    public String getCodigo() {
        return codigo;
    }
    public MatrizDeEstados getMatrizDeEstados() {
        return matrizDeEstados;
    }
    
}