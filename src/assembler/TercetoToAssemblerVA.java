/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assembler;

import analizadorlexico.objetos.ListaTercetos;
import analizadorlexico.objetos.Numero;
import analizadorlexico.objetos.Terceto;
import analizadorlexico.Token;
import analizadorlexico.objetos.Elemento;

/**
 *
 * @author nicob
 */
public class TercetoToAssemblerVA {
    
    private ListaTercetos lista;
    private int jlabel;
    private int jlabel_sig;
    private String regLibre;
    private String auxs;
    
    public TercetoToAssemblerVA(ListaTercetos lista){
        this.lista = lista;
        this.jlabel = 0;
        this.jlabel_sig = -1;
        this.regLibre = "AX";
        this.auxs = "";
    }
    
    public String tercetoToAssembler(){
        String res ="";
        Terceto element;
        for (int i = 0; i < this.lista.size(); i++) {
            element = this.lista.getElement(i);
            switch (element.getOperador()){
                case "/":
                    if (!element.getTipo().equals("double"))
                        res = res + this.get(element,i,"DIV");
                    else
                        res = res + this.getDoble(element, i, "FDIV");                    
                break;
                case "*":
                    if (!element.getTipo().equals("double"))
                        res = res + this.get(element,i,"MUL");
                    else
                        res = res + this.getDoble(element, i, "FMUL");
                    
                    res = res + "JO @Label_ovfmul\n";
                break;
                case "+":                    
                    if (!element.getTipo().equals("double"))
                        res = res + this.get(element,i,"ADD");
                    else
                        res = res + this.getDoble(element, i, "FADD");
                    
                    res = res + "JO @Label_ovfadd\n";
                break;
                case "-":                    
                    if (!element.getTipo().equals("double"))
                        res = res + this.get(element,i,"SUB");
                    else
                        res = res + this.getDoble(element, i, "FSUD");
                break;
                case ":=":
                    if (!element.getTipo().equals("double"))
                         res = res + this.get(element,i,element.getOperador());
                    else
                        res = res + this.getDoble(element, i, element.getOperador());
                break;
                case ">": case "<": case "=": case "!=": case "<=": case ">=":
                    if (!element.getTipo().equals("double"))
                        res = res + this.get(element, i, element.getOperador());
                    else
                        res = res + this.getDoble(element, i, element.getOperador());
                    
                break;
                case "BF":
                        this.jlabel_sig = ((Numero)element.getOperando2()).toModInteger(lista);
                break;
                case "BI":
                    this.jlabel_sig = ((Numero)element.getOperando1()).toModInteger(lista);
                    res = res + "JMP @Label"+(this.jlabel_sig)+"\n"+
                            "@Label"+jlabel+":\n";
                break;
                case "P":
                    res = res + "invoke MessageBox, NULL, addr "+
                            tokenToId((Token)element.getOperando1())+", addr "+
                            tokenToId((Token)element.getOperando1())+", MB_OK\n";
                break;
                case "L":
                    if(!this.lista.getElement(i+1).getOperador().equals("RI"))
                        res = res + "@Label"+ ((Numero)element.getOperando1()).toInteger(lista)+":\n";            
                break;
                case "RI":
                    res = res + "@Label"+ ((Numero)element.getOperando1()).toInteger(lista)+":\n";
                break;
                case "R":
                    res = res + "MOV @aux"+((Numero)element.getOperando1()).toInteger(lista)+", "+regLibre+" \n"
                              + "RET\n";
                break;
                case "C":
                    res = res + "CALL @Label"+((Numero)element.getOperando2()).toInteger(lista)+"\n";
                break;
            }
            if (this.jlabel_sig == i+1){
                this.addAux(jlabel_sig);
                res = res +"@Label"+(jlabel_sig)+":\n"; 
            }           
        }        
        return  auxs+".code\nstart:\n"+res;
    }
    
    private void addAux(int linea){
        if (regLibre.equals("EAX"))
            auxs = auxs + "@aux"+linea+" DD ?\n";
        else
            auxs = auxs + "@aux"+linea+" DW ?\n";
    }
    
    private String getDoble(Terceto text, int linea, String op){
        this.regLibre="EAX";
        String  
        toString1 = getString(text.getOperando1()),
        toString2 = getString(text.getOperando2());
        
        switch (op){
            
            case ">": case "<": case "=": case "!=": case "<=": case ">=":
                
                this.jlabel = ((Numero)this.lista.getElement(linea+1).getOperando2()).toModInteger(lista);
                return  "FLD "+toString1+"\n"+
                        "FLD "+toString2+"\n"+
                        "FCOM\n"+
                        "FSTSW AX\n" +
                        "SAHF\n"+
                        toCMP(op)+" @Label"+jlabel+"\n";
            case ":=":
                return  "FLD "+toString2+"\n"+
                        "FSTP "+toString1+"\n";
            default:
                this.addAux(linea);
                return  "FLD "+toString1+"\n"+
                        "FLD "+toString2+"\n"+
                        op+"\n"+
                        "FSTP @aux"+linea+"\n";
        }
    }
        
    private String get(Terceto text, int linea, String op){
        this.regLibre="AX";
        String 
        toString1 = getString(text.getOperando1()),
        toString2 = getString(text.getOperando2());
        
        switch (op){
            case ">": case "<": case "=": case "!=": case "<=": case ">=":
                this.jlabel = ((Numero)this.lista.getElement(linea+1).getOperando2()).toModInteger();
                this.addAux(linea);
                return  "MOV "+regLibre+", "+toString2+"\n"+   
                        "CMP "+toString1+", "+regLibre+"\n"+
                        toCMP(op)+" @Label"+jlabel+"\n";
            case ":=":
                    return  "MOV "+regLibre+", "+toString2+"\n"+ 
                            "MOV "+toString1+", "+regLibre+"\n";
            case "DIV":
                this.addAux(linea);
                return  "MOV "+regLibre+", "+toString2+"\n"+
                        "ADD "+regLibre+", _zero\n"+
                        "JZ @Label_divzero\n"+
                        "MOV "+regLibre+", "+toString1+"\n"+                         
                        op+" "+toString2+", "+regLibre+"\n"+
                        "MOV @aux"+linea+", "+regLibre+"\n";
            default:
                this.addAux(linea);                
                return  "MOV "+regLibre+", "+toString1+"\n"+ 
                        op+" "+regLibre+", "+toString2+"\n"+
                        "MOV @aux"+linea+", "+regLibre+"\n";
        }        
    }
    
    private String getString(Elemento text){
        if (text.toString().charAt(0)=='[')
            return "@aux"+((Numero)text).toInteger(lista);
        else{
            return tokenToId((Token)text);
        }
    }
    
    private String toCMP(String op){
        switch(op){
            case ">": 
                return "JL";
            case "<":
                return "JG";
            case "=":
                return "JNE";
            case "!=": 
                return "JE";
            case "<=":
                return "JGE";
            case ">=":
                return "JLE";
        };
        return null;
    }
    
    public static String tokenToId(Token token){
        switch(token.getClase()){
                case "doble": 
                    return "_"+token.getLexema()
                            .replace(".", "p")
                                .replace("+", "ma")
                                    .replace("-", "me");
                case "entero": 
                    return "_"+token.getLexema().substring(0, token.getLexema().length()-2);
                case "id":
                    return "_"+token.getLexema();
                case "string" : 
                    String s = token.getLexema()
                            .substring(1, token.getLexema().length()-1)
                                    .replace(" ","_").replace("ó", "o").replace("á", "a").replace("í", "i").replace("ú", "u").replace("é", "e").replace("ñ", "n")
                                        .toLowerCase(); 
                                         
                    int x = 20;
                    if (s.length()<20) x = s.length();
                    return s.substring(0, x);
            }
        return null;
    }

}
