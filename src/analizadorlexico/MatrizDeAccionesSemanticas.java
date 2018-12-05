/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

import accionesSemanticas.AccionSemanticaNula;
import accionesSemanticas.AccionSemantica;
import accionesSemanticas.AccionSemantica1;
import accionesSemanticas.AccionSemantica2;
import accionesSemanticas.AccionSemantica3;
import accionesSemanticas.AccionSemantica4;
import accionesSemanticas.AccionSemantica5;
import accionesSemanticas.AccionSemantica6;
import accionesSemanticas.AccionSemantica7;
import accionesSemanticas.AccionSemantica8;
import accionesSemanticas.AumentarLinea;
import accionesSemanticas.CaracterErroneo;
import accionesSemanticas.DescartarToken;
import accionesSemanticas.EliminarUltimoCaracterLeido;
import accionesSemanticas.EspacioTabulacionYSaltoDeLinea;
import accionesSemanticas.LineaDeComentario;
import accionesSemanticas.ReconoceComentario;

 
public  class MatrizDeAccionesSemanticas  {
    
   static AccionSemantica[][] matrizDeAccionesSemanticas ;
  
    
    
    public MatrizDeAccionesSemanticas(){
     matrizDeAccionesSemanticas = new  AccionSemantica[14][19];
     for (int i = 0; i<14;i++)
            for (int j = 0 ; j< 19 ;j++)
                matrizDeAccionesSemanticas[i][j]=new AccionSemanticaNula();
     
     
     matrizDeAccionesSemanticas[0][3]=new EspacioTabulacionYSaltoDeLinea();
     matrizDeAccionesSemanticas[0][4]=new EspacioTabulacionYSaltoDeLinea();    
     matrizDeAccionesSemanticas[0][5]=new EspacioTabulacionYSaltoDeLinea();
     matrizDeAccionesSemanticas[0][7]=new AccionSemantica3();
     matrizDeAccionesSemanticas[0][9]=new AccionSemantica3();
     matrizDeAccionesSemanticas[0][10]=new AccionSemantica3();
     matrizDeAccionesSemanticas[0][14]=new LineaDeComentario();
     matrizDeAccionesSemanticas[0][18] = new CaracterErroneo();
     
     
     matrizDeAccionesSemanticas[1][2]=new AccionSemantica1();
     matrizDeAccionesSemanticas[1][3]=new AccionSemantica1();
     matrizDeAccionesSemanticas[1][4]=new AccionSemantica1();
     matrizDeAccionesSemanticas[1][5]=new AccionSemantica1();
     matrizDeAccionesSemanticas[1][6]=new AccionSemantica1();
     matrizDeAccionesSemanticas[1][7]=new AccionSemantica1();
     //matrizDeAccionesSemanticas[1][8]=new AccionSemantica1();
     matrizDeAccionesSemanticas[1][9]=new AccionSemantica1();
     matrizDeAccionesSemanticas[1][10]=new AccionSemantica1();
     matrizDeAccionesSemanticas[1][11]=new AccionSemantica1();
     matrizDeAccionesSemanticas[1][12]=new AccionSemantica1();
     matrizDeAccionesSemanticas[1][13]=new AccionSemantica1();
     matrizDeAccionesSemanticas[1][14]=new AccionSemantica1();
     matrizDeAccionesSemanticas[1][15]=new AccionSemantica1();
     matrizDeAccionesSemanticas[1][16]=new AccionSemantica1();
     //matrizDeAccionesSemanticas[1][17]=new AccionSemantica1();
     matrizDeAccionesSemanticas[1][18]=new AccionSemantica1();
     
     matrizDeAccionesSemanticas[2][0]=new DescartarToken();
     matrizDeAccionesSemanticas[2][1]=new DescartarToken();
     matrizDeAccionesSemanticas[2][2]=new DescartarToken();
     matrizDeAccionesSemanticas[2][3]=new AccionSemantica5();
     matrizDeAccionesSemanticas[2][4]=new AccionSemantica5();
     matrizDeAccionesSemanticas[2][5]=new AccionSemantica5();
     matrizDeAccionesSemanticas[2][6]=new DescartarToken();
     matrizDeAccionesSemanticas[2][7]= new AccionSemantica3();
     matrizDeAccionesSemanticas[2][8]=new DescartarToken();
     matrizDeAccionesSemanticas[2][9]=new DescartarToken();
     matrizDeAccionesSemanticas[2][10]=new DescartarToken();
     matrizDeAccionesSemanticas[2][11]=new DescartarToken();
     matrizDeAccionesSemanticas[2][12]=new DescartarToken();
     matrizDeAccionesSemanticas[2][13]=new DescartarToken();
     matrizDeAccionesSemanticas[2][14]=new DescartarToken();
     matrizDeAccionesSemanticas[2][15]=new DescartarToken();
     matrizDeAccionesSemanticas[2][16]=new DescartarToken();
     matrizDeAccionesSemanticas[2][17]=new DescartarToken();
     matrizDeAccionesSemanticas[2][18]=new DescartarToken();
           
     
     matrizDeAccionesSemanticas[3][0]=new DescartarToken();
    // matrizDeAccionesSemanticas[3][1]=new DescartarToken();
     //matrizDeAccionesSemanticas[3][2]=new DescartarToken();
     matrizDeAccionesSemanticas[3][3]=new AccionSemantica5();
     matrizDeAccionesSemanticas[3][4]=new AccionSemantica5();
     matrizDeAccionesSemanticas[3][5]=new AccionSemantica5();
     matrizDeAccionesSemanticas[3][6]=new DescartarToken();
     matrizDeAccionesSemanticas[3][7]=new DescartarToken();
     matrizDeAccionesSemanticas[3][8]=new DescartarToken();
     matrizDeAccionesSemanticas[3][9]=new DescartarToken();
     matrizDeAccionesSemanticas[3][10]=new DescartarToken();
     matrizDeAccionesSemanticas[3][11]=new DescartarToken();
     matrizDeAccionesSemanticas[3][12]=new DescartarToken();
     matrizDeAccionesSemanticas[3][13]=new DescartarToken();
     matrizDeAccionesSemanticas[3][14]=new DescartarToken();
     matrizDeAccionesSemanticas[3][15]=new DescartarToken();
     //matrizDeAccionesSemanticas[3][16]=new DescartarToken();
     matrizDeAccionesSemanticas[3][17]=new DescartarToken();
     matrizDeAccionesSemanticas[3][18]=new DescartarToken();
    
     
     
    
     matrizDeAccionesSemanticas[4][0]=new DescartarToken();
     matrizDeAccionesSemanticas[4][1]=new DescartarToken();
     matrizDeAccionesSemanticas[4][2]=new DescartarToken();
     matrizDeAccionesSemanticas[4][3]=new AccionSemantica5();
     matrizDeAccionesSemanticas[4][4]=new AccionSemantica5();
     matrizDeAccionesSemanticas[4][5]=new AccionSemantica5();
     matrizDeAccionesSemanticas[4][6]=new DescartarToken();
     matrizDeAccionesSemanticas[4][7]=new DescartarToken();
     matrizDeAccionesSemanticas[4][8]=new AccionSemantica2();
     matrizDeAccionesSemanticas[4][9]=new DescartarToken();
     matrizDeAccionesSemanticas[4][10]=new DescartarToken();
     matrizDeAccionesSemanticas[4][11]=new DescartarToken();
     matrizDeAccionesSemanticas[4][12]=new DescartarToken();
     matrizDeAccionesSemanticas[4][13]=new DescartarToken();
     matrizDeAccionesSemanticas[4][14]=new DescartarToken();
     matrizDeAccionesSemanticas[4][15]=new DescartarToken();
     matrizDeAccionesSemanticas[4][16]=new DescartarToken();
     matrizDeAccionesSemanticas[4][17]=new DescartarToken();
     matrizDeAccionesSemanticas[4][18]=new DescartarToken();
     
     matrizDeAccionesSemanticas[5][0]=new AccionSemantica7();
     matrizDeAccionesSemanticas[5][1]=new AccionSemantica7();
     matrizDeAccionesSemanticas[5][2]=new AccionSemantica7();
     matrizDeAccionesSemanticas[5][3]=new AccionSemantica7();
     matrizDeAccionesSemanticas[5][4]=new AccionSemantica7();
     matrizDeAccionesSemanticas[5][5]=new AumentarLinea();
     matrizDeAccionesSemanticas[5][6]=new AccionSemantica7();
     matrizDeAccionesSemanticas[5][7]=new AccionSemantica7();
     matrizDeAccionesSemanticas[5][8]=new AccionSemantica7();
     matrizDeAccionesSemanticas[5][9]=new AccionSemantica7();
     matrizDeAccionesSemanticas[5][10]=new AccionSemantica7();
     matrizDeAccionesSemanticas[5][11]=new AccionSemantica7();
     matrizDeAccionesSemanticas[5][12]=new AccionSemantica7();
     matrizDeAccionesSemanticas[5][13]=new AccionSemantica7();
     matrizDeAccionesSemanticas[5][14]=new ReconoceComentario();
     matrizDeAccionesSemanticas[5][15]=new AccionSemantica7();
     matrizDeAccionesSemanticas[5][16]=new AccionSemantica7();
     matrizDeAccionesSemanticas[5][17]=new AccionSemantica7();
     matrizDeAccionesSemanticas[5][18]=new AccionSemantica7();
     
     
     
     matrizDeAccionesSemanticas[6][0]=new AccionSemantica4();
     matrizDeAccionesSemanticas[6][1]=new AccionSemantica4();
     matrizDeAccionesSemanticas[6][2]=new AccionSemantica4();
     matrizDeAccionesSemanticas[6][3]=new AccionSemantica4();
     matrizDeAccionesSemanticas[6][4]=new AccionSemantica4();
     matrizDeAccionesSemanticas[6][5]=new AccionSemantica4();
     matrizDeAccionesSemanticas[6][6]=new AccionSemantica4();
     matrizDeAccionesSemanticas[6][7]=new AccionSemantica3();
     matrizDeAccionesSemanticas[6][8]=new AccionSemantica4();
     matrizDeAccionesSemanticas[6][9]=new AccionSemantica4();
     matrizDeAccionesSemanticas[6][10]=new AccionSemantica4();
     matrizDeAccionesSemanticas[6][11]=new AccionSemantica4();
     matrizDeAccionesSemanticas[6][12]=new AccionSemantica4();
     matrizDeAccionesSemanticas[6][13]=new AccionSemantica4();
     matrizDeAccionesSemanticas[6][14]=new AccionSemantica4();
     matrizDeAccionesSemanticas[6][15]=new AccionSemantica4();
     matrizDeAccionesSemanticas[6][16]=new AccionSemantica4();
     matrizDeAccionesSemanticas[6][17]=new AccionSemantica4();
     matrizDeAccionesSemanticas[6][18]=new AccionSemantica4();
     
     
     matrizDeAccionesSemanticas[7][0]=new AccionSemantica7();
     matrizDeAccionesSemanticas[7][1]=new AccionSemantica7();
     matrizDeAccionesSemanticas[7][2]=new AccionSemantica7();
     matrizDeAccionesSemanticas[7][3]=new AccionSemantica7();
     matrizDeAccionesSemanticas[7][4]=new AccionSemantica7();
     matrizDeAccionesSemanticas[7][5]=new AccionSemantica7();
     matrizDeAccionesSemanticas[7][6]=new AccionSemantica7();
     matrizDeAccionesSemanticas[7][7]=new AccionSemantica7();
     matrizDeAccionesSemanticas[7][8]=new AccionSemantica7();
     matrizDeAccionesSemanticas[7][9]=new AccionSemantica7();
     matrizDeAccionesSemanticas[7][10]=new AccionSemantica7(); 
     matrizDeAccionesSemanticas[7][11]=new AccionSemantica7();
     matrizDeAccionesSemanticas[7][12]=new AccionSemantica7();
     matrizDeAccionesSemanticas[7][13]=new AccionSemantica7();
     matrizDeAccionesSemanticas[7][14]=new AccionSemantica7();                  
     matrizDeAccionesSemanticas[7][15]=new AccionSemantica8();
     matrizDeAccionesSemanticas[7][16]=new AccionSemantica7();
     matrizDeAccionesSemanticas[7][17]=new AccionSemantica7();
     matrizDeAccionesSemanticas[7][18]=new AccionSemantica7();
     
     
     
     
     matrizDeAccionesSemanticas[8][1]=new AccionSemantica5();
     matrizDeAccionesSemanticas[8][3]=new AccionSemantica5();
     matrizDeAccionesSemanticas[8][4]=new AccionSemantica5();
     matrizDeAccionesSemanticas[8][5]=new AccionSemantica5();
     matrizDeAccionesSemanticas[8][6]=new AccionSemantica5();
     matrizDeAccionesSemanticas[8][7]=new AccionSemantica5();
     //matrizDeAccionesSemanticas[8][8]=new AccionSemantica5();
     matrizDeAccionesSemanticas[8][9]=new AccionSemantica5();
     matrizDeAccionesSemanticas[8][10]=new AccionSemantica5();
     matrizDeAccionesSemanticas[8][11]=new AccionSemantica5();
     matrizDeAccionesSemanticas[8][12]=new AccionSemantica5();
     matrizDeAccionesSemanticas[8][13]=new AccionSemantica5();
     matrizDeAccionesSemanticas[8][14]=new AccionSemantica5();
     matrizDeAccionesSemanticas[8][15]=new AccionSemantica5();
     matrizDeAccionesSemanticas[8][16]=new AccionSemantica5();
     //matrizDeAccionesSemanticas[8][17]=new AccionSemantica5();
     matrizDeAccionesSemanticas[8][18]=new AccionSemantica5();
 
 
     matrizDeAccionesSemanticas[9][0]=new DescartarToken(); 
     matrizDeAccionesSemanticas[9][2]=new DescartarToken();
     matrizDeAccionesSemanticas[9][3]=new DescartarToken();
     matrizDeAccionesSemanticas[9][4]=new DescartarToken();
     matrizDeAccionesSemanticas[9][5]=new DescartarToken();
     matrizDeAccionesSemanticas[9][6]=new DescartarToken();
     matrizDeAccionesSemanticas[9][7]=new DescartarToken();
     matrizDeAccionesSemanticas[9][8]=new DescartarToken();
     matrizDeAccionesSemanticas[9][9]=new DescartarToken();
     matrizDeAccionesSemanticas[9][10]=new DescartarToken();
     matrizDeAccionesSemanticas[9][11]=new DescartarToken();
     matrizDeAccionesSemanticas[9][12]=new DescartarToken();
     matrizDeAccionesSemanticas[9][13]=new DescartarToken();
     matrizDeAccionesSemanticas[9][14]=new DescartarToken();
     matrizDeAccionesSemanticas[9][15]=new DescartarToken();
     matrizDeAccionesSemanticas[9][16]=new DescartarToken();      
     matrizDeAccionesSemanticas[9][18]=new DescartarToken();
     
     
     
     matrizDeAccionesSemanticas[10][0]=new AccionSemantica6();
       //matrizDeAccionesSemanticas[10][1]=10;
     matrizDeAccionesSemanticas[10][2]=new AccionSemantica6();
     matrizDeAccionesSemanticas[10][3]=new AccionSemantica6();
     matrizDeAccionesSemanticas[10][4]=new AccionSemantica6();
     matrizDeAccionesSemanticas[10][5]=new AccionSemantica6();
     matrizDeAccionesSemanticas[10][6]=new AccionSemantica6();
     matrizDeAccionesSemanticas[10][7]=new AccionSemantica6();
     matrizDeAccionesSemanticas[10][8]=new AccionSemantica6();
     matrizDeAccionesSemanticas[10][9]=new AccionSemantica6();
     matrizDeAccionesSemanticas[10][10]=new AccionSemantica6();
     matrizDeAccionesSemanticas[10][11]=new AccionSemantica6();
     matrizDeAccionesSemanticas[10][12]=new AccionSemantica6();
     matrizDeAccionesSemanticas[10][13]=new AccionSemantica6();
     matrizDeAccionesSemanticas[10][14]=new AccionSemantica6();
     matrizDeAccionesSemanticas[10][15]=new AccionSemantica6();
     matrizDeAccionesSemanticas[10][16]=new AccionSemantica6();
        //matrizDeAccionesSemanticas[10][17]=11;
     matrizDeAccionesSemanticas[10][18]=new AccionSemantica6();
     
     matrizDeAccionesSemanticas[11][0]=new DescartarToken(); 
     matrizDeAccionesSemanticas[11][2]=new DescartarToken();
     matrizDeAccionesSemanticas[11][3]=new DescartarToken();
     matrizDeAccionesSemanticas[11][4]=new DescartarToken();
     matrizDeAccionesSemanticas[11][5]=new DescartarToken();
     matrizDeAccionesSemanticas[11][6]=new DescartarToken();
     matrizDeAccionesSemanticas[11][7]=new DescartarToken();
     matrizDeAccionesSemanticas[11][8]=new DescartarToken();
     matrizDeAccionesSemanticas[11][9]=new DescartarToken();
    // matrizDeAccionesSemanticas[11][10]=new DescartarToken();
     matrizDeAccionesSemanticas[11][11]=new DescartarToken();
     matrizDeAccionesSemanticas[11][12]=new DescartarToken();
     matrizDeAccionesSemanticas[11][13]=new DescartarToken();
     matrizDeAccionesSemanticas[11][14]=new DescartarToken();
     matrizDeAccionesSemanticas[11][15]=new DescartarToken();
     matrizDeAccionesSemanticas[11][16]=new DescartarToken();      
     matrizDeAccionesSemanticas[11][18]=new DescartarToken();
     


       matrizDeAccionesSemanticas[12][0]=new AccionSemantica6();
      // matrizDeAccionesSemanticas[12][1]=new AccionSemantica6();
       matrizDeAccionesSemanticas[12][2]=new AccionSemantica6();
       matrizDeAccionesSemanticas[12][3]=new AccionSemantica6();
       matrizDeAccionesSemanticas[12][4]=new AccionSemantica6();
       matrizDeAccionesSemanticas[12][5]=new AccionSemantica6();
       matrizDeAccionesSemanticas[12][6]=new AccionSemantica6();
       matrizDeAccionesSemanticas[12][7]=new AccionSemantica6();
       matrizDeAccionesSemanticas[12][8]=new AccionSemantica6();
       matrizDeAccionesSemanticas[12][9]=new AccionSemantica6();
       matrizDeAccionesSemanticas[12][10]=new AccionSemantica6();
       matrizDeAccionesSemanticas[12][11]=new AccionSemantica6();
       matrizDeAccionesSemanticas[12][12]=new AccionSemantica6();
       matrizDeAccionesSemanticas[12][13]=new AccionSemantica6();
       matrizDeAccionesSemanticas[12][14]=new AccionSemantica6();
       matrizDeAccionesSemanticas[12][15]=new AccionSemantica6();
       matrizDeAccionesSemanticas[12][16]=new AccionSemantica6();
       matrizDeAccionesSemanticas[12][17]=new AccionSemantica6();
       matrizDeAccionesSemanticas[12][18]=new AccionSemantica6();



















  
    
    }
    
     
    public AccionSemantica getAccionSemantica(int fila,int columna){
      return MatrizDeAccionesSemanticas.matrizDeAccionesSemanticas[fila][columna];
    }

  

     
}
