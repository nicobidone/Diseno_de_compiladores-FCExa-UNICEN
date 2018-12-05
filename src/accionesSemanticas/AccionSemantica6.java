/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accionesSemanticas;

import analizadorlexico.MatrizDeEstados;
import static analizadorlexico.MatrizDeEstados.buffer;
import analizadorlexico.Token;
import java.math.BigDecimal;

/**
 *
 * @author jebu
 */
public class AccionSemantica6 extends AccionSemantica{

    public AccionSemantica6() {
        super(1);
    }

    @Override
    public void EjecutarAccion() {
     AccionSemantica ultC=new EliminarUltimoCaracterLeido();
     ultC.EjecutarAccion();     
     String st = "";
     for (Character c : buffer){
            st+=c;
     }
     double numero=0;
     //System.out.println("el String : " + st);
     numero = Double.parseDouble(st.replace("D","E"));
     //System.out.println("el numero : " + numero);
 
     if (((numero > 2.2250738585072014E-308) && (numero < 1.7976931348623157E308)) || (numero == 0.0))
        MatrizDeEstados.token = new Token();
        else{
            if (numero > 1.7976931348623157E308){
                String lexema="";
                for(Character c:buffer){
                lexema+=c;
                }
            
                System.out.println("LINEA :"+ MatrizDeEstados.numeroDeLinea  +" - WARNING: El double "+ lexema + " fuera de rango, se reemplaza por valor maximo permitido");
                buffer.clear();
                buffer.add('1');
                buffer.add('.');
                buffer.add('7');
                buffer.add('9');
                buffer.add('7');
                buffer.add('6');
                buffer.add('9');
                buffer.add('3');
                buffer.add('1');
                buffer.add('3');
                buffer.add('4');
                buffer.add('8');
                buffer.add('6');
                buffer.add('2');
                buffer.add('3');
                buffer.add('1');
                buffer.add('5');
                buffer.add('6');
                buffer.add('D');
                buffer.add('3');
                buffer.add('0');
                buffer.add('8');
                MatrizDeEstados.token = new Token();
        } else {
            String lexema="";
            for(Character c:buffer){
              lexema+=c;
            }
            System.out.println("LINEA :"+ MatrizDeEstados.numeroDeLinea  +" - WARNING: El double "+ lexema + " fuera de rango, se reemplaza por valor minimo permitido");
            buffer.clear();
            buffer.add('2');
            buffer.add('.');
            buffer.add('2');
            buffer.add('2');
            buffer.add('5');
            buffer.add('0');
            buffer.add('7');
            buffer.add('3');
            buffer.add('8');
            buffer.add('5');
            buffer.add('8');
            buffer.add('5');
            buffer.add('0');
            buffer.add('7');
            buffer.add('2');
            buffer.add('0');
            buffer.add('1');
            buffer.add('4');
            buffer.add('D');
            buffer.add('-');
            buffer.add('3');
            buffer.add('0');
            buffer.add('8');
            MatrizDeEstados.token = new Token();
          }
        }
   }
}


