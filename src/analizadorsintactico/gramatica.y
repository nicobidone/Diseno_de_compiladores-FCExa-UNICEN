%{
	package analizadorsintactico;
	import analizadorlexico.*;	
	import analizadorlexico.objetos.*;
	import analizadorlexico.objetos.ParserVal;
	import gestorArchivo.Texto;
	import java.util.ArrayList;
	import java.util.List;	
%}

%token 	ID ASSIGN NO_IGUAL MAYOR_IGUAL MENOR_IGUAL STRING ENTERO DOBLE 
		INTEGER DOUBLE FUN VOID IF END_IF  ELSE LOOP UNTIL RETURN PRINT

%start programa

%%

	programa 
		: sentencia
			{ 
				System.out.println("El programa funciona");
			}
			
	;
	
	sentencia
		: sentencia_simple
			{ 
				System.out.println("sentencia_simple");
			}
		| sentencia sentencia_simple 
			{ 
				System.out.println("sentencia sentencia_simple");
			}
		| sentencia_error
			{ 
				System.out.println("sentencia_error");
			}
		| sentencia sentencia_error
			{ 
				System.out.println("sentencia sentencia_error");
			}
	;
	
	sentencia_error
		: PRINT '(' STRING ')'													{ yyerror($4.begin_line, "Sentencia de salida PRINT"); } 
		| LOOP bloque_sentencia UNTIL correcto_condicion						{ yyerror(pos,"Sentencia de control LOOP-UNTIL"); }
		| IF correcto_condicion bloque_sentencia END_IF 						{ yyerror($4.begin_line,"Sentencia de seleccion IF-END_IF"); }
		| IF correcto_condicion bloque_sentencia ELSE bloque_sentencia END_IF 	{ yyerror($6.begin_line,"Sentencia de seleccion IF-END_IF"); }
		| error ','																{ yyerror($1.begin_line,"Gramática no reconocida"); }
		| error ';' 															{ yyerror($1.begin_line,"Gramática no reconocida"); }
		| error 																{ yyerror($1.begin_line,"Gramática no reconocida"); }
	;
	
	sentencia_simple
		: sentencia_declarativa
			{ 
				System.out.println("sentencia_declarativa");
			}
		| sentencia_ejecutable 
			{ 
				System.out.println("sentencia_ejecutable");
			}
	;
	
	sentencia_ejecutable
		: sentencia_seleccion 
			{ 
				System.out.println("sentencia_seleccion");
				this.s_ptr = i_ptr;
				
			}
		| sentencia_asignacion
			{ 
				System.out.println("sentencia_asignacion");
				this.s_ptr = this.a_ptr;
			}
		| sentencia_control
			{ 
				System.out.println("sentencia_control");
				this.s_ptr = this.c_ptr;
			}
		| sentencia_salida
			{ 
				System.out.println("sentencia_salida");
				this.s_ptr = this.p_ptr;
			}			
		| id_fun ','
			{ 
				System.out.println("ID ()");
				if (this.funs.isDeclarado((Token)this.f_ptr)){
					int size = this.lista.size()-1;
					Terceto element = this.lista.getElement(size),
							resul=null;
					while (size > 0 && element.getOperando1() != this.f_ptr){
						size--;
						if (element.getOperador().equals("R"))
							resul = ((Numero)element.getOperando1()).getInicio();
						element = this.lista.getElement(size);						
					}
					this.lista.agregar(new Terceto("C",this.f_ptr,new Numero(lista, resul)));
				}
				else
					this.errores.add("LINEA: "+this.lineNumber+" - ERROR - Variable no declarada como función");
				
			}
	;
	
	sentencia_salida
		: PRINT '(' STRING ')' ','
			{ 
				resultado.add("LINEA: "+ $1.begin_line +" Sentencia de salida PRINT");
				this.p_ptr = new Terceto("P",this.symbolTable.getToken($3.sval),null);
				this.lista.agregar((Terceto) this.p_ptr);
			}
		| PRINT '(' STRING error ','{ yyerror($4.begin_line,"Sentencia de salida PRINT"); }
		| PRINT '(' error ',' 		{ yyerror($3.begin_line,"Sentencia de salida PRINT"); }
		| PRINT error ','			{ yyerror($2.begin_line,"Sentencia de salida PRINT"); }
	;	
	
	correcto_condicion
		: '(' condicion ')' { pos = $3.begin_line; }
	;
	
	sentencia_control
		: LOOP bloque_sentencia UNTIL correcto_condicion ','
			{ 
				if (this.bfa_ptr == null)
					this.bfa_ptr = bf_ptr;
				resultado.add("LINEA: " + $1.begin_line +" Sentencia de control LOOP-UNTIL");
				this.iee_ptr = new Terceto("BI",
                                        new Numero(this.lista,(Terceto)this.bfa_ptr.getInicio()), null);
				this.ie_ptr = new Terceto("BF",
                                        new Numero(this.lista,(Terceto)this.c_ptr),
                                        new Numero(this.lista,(Terceto)this.iee_ptr,1));
										
				this.lista.agregar((Terceto) this.ie_ptr);
				this.lista.agregar((Terceto) this.iee_ptr);
				
				this.lista.agregar(new Terceto("L",
										new Numero(this.lista,(Terceto)this.bfa_ptr.getInicio()), null));
				this.lista.ordenarLOOP();
			}
		| LOOP bloque_sentencia UNTIL error ','	{ yyerror($4.begin_line,"Sentencia de control LOOP-UNTIL"); }
		| LOOP bloque_sentencia error ','		{ yyerror($3.begin_line,"Sentencia de control LOOP-UNTIL"); }
		| LOOP error ','						{ yyerror($2.begin_line,"Sentencia de control LOOP-UNTIL"); }
	;
	
	sentencia_seleccion
		: IF correcto_condicion bloque_sentencia END_IF ','
			{ 
				System.out.println("IF '(' condicion ')' bloque_sentencia END_IF ,");
				resultado.add("LINEA: "+$1.begin_line + " Sentencia de seleccion IF-END_IF");
				this.i_ptr = new Terceto("BF",
                                        new Numero(this.lista,(Terceto)this.c_ptr),
                                        new Numero(this.lista,(Terceto)this.bi_ptr,1));
				this.lista.agregar((Terceto) this.i_ptr);
				this.lista.ordenarIF();
			}
		| IF correcto_condicion bloque_sentencia ELSE bloque_sentencia END_IF ','
			{ 
				System.out.println("IF '(' condicion ')' bloque_sentencia else bloque_sentencia END_IF ,");
				resultado.add("LINEA: "+$1.begin_line + " Sentencia de seleccion IF-ELSE-END_IF");
				this.ie_ptr = new Terceto("BF",
                                        new Numero(this.lista,(Terceto)this.c_ptr),
                                        new Numero(this.lista,(Terceto)this.bf_ptr));
				this.lista.agregar((Terceto) this.ie_ptr);
				this.iee_ptr = new Terceto("BI",
                                        new Numero(this.lista,(Terceto)this.bi_ptr,1), null);
				this.lista.agregar((Terceto) this.iee_ptr);
				this.lista.ordenarIF();
			}
		| IF correcto_condicion bloque_sentencia ELSE bloque_sentencia error ','{ yyerror($6.begin_line,"Sentencia de seleccion IF-END_IF"); }
		| IF correcto_condicion bloque_sentencia ELSE error ',' 				{ yyerror($5.begin_line,"Sentencia de seleccion IF-END_IF"); }
		| IF correcto_condicion bloque_sentencia error ',' 						{ yyerror($4.begin_line,"Sentencia de seleccion IF-END_IF"); }
		| IF correcto_condicion error ',' 										{ yyerror($3.begin_line,"Sentencia de seleccion IF-END_IF"); }
		| IF error ',' 															{ yyerror($2.begin_line,"Sentencia de seleccion IF-END_IF"); }
		
	;
	
	bloque_sentencia
		: '{' bloque '}'
			{ 
				System.out.println("{ bloque_sentencia sentencia_simple }");
			}
		| sentencia_simple
			{ 
				System.out.println("sentencia_simple");
				this.bfa_ptr = this.bf_ptr;
				this.bi_ptr = this.s_ptr;
				this.bf_ptr = this.s_ptr;
			}
	;
	
	bloque
		: sentencia_simple
			{ 
				System.out.println("sentencia_simple");
				this.bfa_ptr = this.bf_ptr;
				this.bi_ptr = this.s_ptr;
				this.bf_ptr = this.s_ptr;
			}
		| bloque sentencia_simple
			{ 
				System.out.println("{ bloque_sentencia sentencia_simple }");
				this.bi_ptr = this.s_ptr;
			}
	;
	
	sentencia_declarativa
		: declaraciones
			{
				System.out.println("tipo lista_variable ,");
				resultado.add("LINEA: " + pos  + " Sentencia declarativa");
			}
		
		| closure_funcion
			{
				System.out.println("closure_funcion");
				resultado.add("LINEA: " + pos + " Sentencia declarativa FUN");				
			}
	;
	
	declaraciones
		: tipo lista_variable ','
			{				
				for (Token tk: this.declaraciones){
					tk.setTipo($1.sval);
					if (this.vars.isDeclarado(tk))
						this.errores.add("LINEA: "+this.lineNumber+" - ERROR - Variable redeclarada");
					else if (this.funs.isDeclarado(tk))
						this.errores.add("LINEA: "+this.lineNumber+" - ERROR - Variable declarada como funcion");
					else 
						this.vars.AgregarEntrada(tk);					
				}
				this.declaraciones.clear();
			}
	;
	
	lista_variable
		: ID 							
			{ 
				System.out.println("ID"); 
				pos=$1.begin_line;				
				this.f_ptr = this.symbolTable.getToken($1.sval);
				this.declaraciones.add((Token) this.f_ptr);
			}
		| lista_variable ';' ID 
			{ 
				System.out.println("lista_variable ; ID"); 
				pos=$3.begin_line;
				this.f_ptr = this.symbolTable.getToken($3.sval);				
				this.declaraciones.add((Token) this.f_ptr);
			}		
		| lista_variable ';' error 		{ yyerror($3.begin_line,"Lista de variables"); }
	;
	
	closure_funcion
		: FUN id_fun '{' closure_cuerpo closure_nulo RETURN '(' closure_retorno ')' ',' '}' ','
			{	
				System.out.println("FUN closure");
				Token tk = this.symbolTable.getToken($2.sval);
				tk.setTipo("fun");
				if (this.cr_ptr == this.f_ptr)
					if (this.ret_ptr == this.symbolTable.getToken($8.sval)) 
						System.out.println("->retorno void");
					else if(this.symbolTable.getToken($2.sval) == this.cr_ptr){
						this.bfa_ptr = this.bf_ptr;
						System.out.println("->Autoretorno");
					}
					else{
						this.errores.add("Linea: "+pos+" ERROR - identificador no permitido");
					}
				else{
					this.bi_ptr = this.s_ptr;
					this.bfa_ptr = this.bfa_ptr.getInicio();
				}
				pos = $1.begin_line;
				this.inicFUN($2.sval);
			}
		| FUN id_fun '{' closure_cuerpo RETURN '(' closure_retorno ')' ',' '}' ','
			{	
				System.out.println("FUN closure");
				Token tk = this.symbolTable.getToken($2.sval);
				tk.setTipo("fun");
				if (this.cr_ptr == this.f_ptr)	
					//if (this.symbolTable.getToken($2.sval) == this.symbolTable.getToken($8.sval)) 
					if(this.symbolTable.getToken($2.sval) == this.cr_ptr){
						this.bfa_ptr = this.bf_ptr;
						System.out.println("->Autoretorno");
					}
					else{
						this.errores.add("Linea: "+pos+" ERROR - identificador no permitido");
					}
				else{
					this.bi_ptr = this.s_ptr;
					this.bfa_ptr = this.bfa_ptr.getInicio();
				}
				
				pos = $1.begin_line;
				this.inicFUN($2.sval);
			}
		| FUN id_fun '{' closure_cuerpo RETURN '(' closure_retorno ')' ',' error ','{ yyerror($10.begin_line,"Closure FUN"); }
		| FUN id_fun '{' closure_cuerpo RETURN '(' closure_retorno ')' error ','	{ yyerror($9.begin_line,"Closure FUN"); }
		| FUN id_fun '{' closure_cuerpo RETURN '(' closure_retorno error ','		{ yyerror($8.begin_line,"Closure FUN"); }
		| FUN id_fun '{' closure_cuerpo RETURN '(' error ','						{ yyerror($7.begin_line,"Closure FUN"); }
		| FUN id_fun '{' closure_cuerpo RETURN error ','							{ yyerror($6.begin_line,"Closure FUN"); }
		| FUN id_fun '{' closure_cuerpo error ','									{ yyerror($5.begin_line,"Closure FUN"); }
		| FUN id_fun '{' error ','													{ yyerror($4.begin_line,"Closure FUN"); }
		| FUN id_fun error ','														{ yyerror($3.begin_line,"Closure FUN"); }
		| FUN error ','																{ yyerror($2.begin_line,"Closure FUN"); }		
		| FUN id_fun ','
			{
				Token tk = ((Token)this.f_ptr);
				tk.setTipo("fun");
				this.funs.AgregarEntrada(tk);
			}
	;
	
	closure_nulo
		: VOID id_fun '{' closure_cuerpo '}' ','	
			{	
				System.out.println("detecto un VOID closure");
				this.pos = $1.begin_line;
				this.voidd = true;
				resultado.add("LINEA: " + pos + " Sentencia declarativa VOID");
				// Agrego la funcion como terceto y realizo el acomodamiento
				Token tk = this.symbolTable.getToken($2.sval);
				this.ret_ptr = tk;
				tk.setClase("void");
				this.fun_ptr = new Terceto("V",tk, new Numero(this.lista, (Terceto) this.bfa_ptr.getInicio()));
				this.lista.agregar((Terceto) this.fun_ptr);
				this.lista.ordenarVOID(this.closures);
				//Chequeo los errores posibles
				if (this.funs.isDeclarado(tk))
					this.errores.add("LINEA: "+this.lineNumber+" - ERROR - Variable redeclarada");
				else if (this.vars.isDeclarado(tk))
					this.errores.add("LINEA: "+this.lineNumber+" - ERROR - Variable declarada como identificador");
				else
					this.funs.AgregarEntrada(tk);
				//Seteo el tipo de la funcion como el del retorno
				tk.setTipo("void"); 
			}
		| VOID id_fun error ','			{ yyerror($3.begin_line,"Closure VOID"); }
		| VOID error ','				{ yyerror($2.begin_line,"Closure VOID"); }
	;
	
	closure_retorno
		: id_fun 
			{ 
				this.cr_ptr = f_ptr;			
			}
		| closure_cuerpo 
			{
				this.cr_ptr = new Numero (this.lista,(Terceto)this.bf_ptr.getInicio());
				this.bf_ptr = this.a_ptr;
			}
	;
		
	closure_cuerpo
		: sentencia_limitada
			{	
				System.out.println("sentencia_limitada");
				this.bfa_ptr = this.s_ptr;
			}
		| closure_cuerpo sentencia_limitada
			{	
				System.out.println("cuerpo sentencia_limitada");
				this.bi_ptr = this.s_ptr;
			}		
	;
	
	sentencia_limitada
		: declaraciones
			{ 
				System.out.println("sentencia_declarativa");				
			}
		| sentencia_ejecutable 
			{ 
				System.out.println("sentencia_ejecutable");
			}
	;
	
	id_fun
		: ID '(' ')'
			{	
				System.out.println("id "+$1.sval+" ( )");
				this.fa_ptr = this.f_ptr;
				this.f_ptr = this.symbolTable.getToken($1.sval);
				pos = $1.begin_line;
			}
		| ID '(' error { yyerror($3.begin_line,"Indentificador FUN"); }
	;
	
	sentencia_asignacion 
		: ID ASSIGN expresion ',' 					
			{
				resultado.add("LINEA: " + $4.begin_line + " Asignacion ");
				this.a_ptr = new Terceto(":=",this.symbolTable.getToken($1.sval),this.getObject(this.e_ptr));
				compTipo(this.a_ptr,this.e_ptr,this.lineNumber);
				compDeclarado(this.a_ptr,this.e_ptr,this.lineNumber);

				this.lista.agregar((Terceto) this.a_ptr);
				
			}
		| id_fun ASSIGN id_fun ','		
			{
				resultado.add("LINEA: " + $4.begin_line + " Asignacion ");
				this.a_ptr = new Terceto(":=",this.getObject(this.fa_ptr),this.getObject(this.f_ptr));
				((Token)this.fa_ptr).setTipo(this.f_ptr.getTipo());
				this.lista.agregar((Terceto) this.a_ptr);
			}
		| id_fun ASSIGN error ','		{ yyerror($4.begin_line,"Asignacion"); }
		| ID ASSIGN error ',' 			{ yyerror($4.begin_line,"Asignacion"); }
		| ID error ',' 					{ yyerror($3.begin_line,"Asignacion"); }
	;
	
	condicion 
		: expresion comparador expresion
			{ 
				System.out.println("expresion = expresion");
				resultado.add("LINEA: " + this.lineNumber + " Comparacion ");
				compTipo(this.a_ptr,this.e_ptr,this.lineNumber);
				compDeclarado(this.a_ptr,this.e_ptr,this.lineNumber);

				this.c_ptr = new Terceto($2.sval,this.getObject(this.a_ptr),this.getObject(this.e_ptr));
				this.lista.agregar((Terceto)this.c_ptr);
			}
		| expresion comparador error 	{ yyerror($3.begin_line,"Comparacion"); }
		| expresion error 				{ yyerror($2.begin_line,"Comparacion"); }
	;
	
	comparador
		: '='
		| '<'
		| '>'
		| MAYOR_IGUAL
		| MENOR_IGUAL
		| NO_IGUAL
	;
	
	expresion
		: expresion '+' termino 
			{ 
				System.out.println("detecto una expresion '+' termino");
				compTipo(this.e_ptr,this.t_ptr,this.lineNumber);
				compDeclarado(this.e_ptr,this.t_ptr,this.lineNumber);
				
				this.e_ptr = new Terceto("+",this.getObject(this.e_ptr),this.getObject(this.t_ptr));
				this.lista.agregar((Terceto) this.e_ptr);
			}
		| expresion '-' termino 
			{ 
				System.out.println("detecto una expresion '-' termino");
				//compTipo(this.e_ptr,this.t_ptr,this.lineNumber);
				//compDeclarado(this.e_ptr,this.t_ptr,this.lineNumber);
				
				this.e_ptr = new Terceto("-",this.getObject(this.e_ptr),this.getObject(this.t_ptr));
				this.lista.agregar((Terceto) this.e_ptr);
			}
		| termino 				
			{ 
				System.out.println("detecto una termino");				
				this.a_ptr = this.e_ptr;	//REVISAR
				this.e_ptr = this.t_ptr;				
			}
		| expresion '+' error 	{ System.out.println("ERROR"); }
		| expresion '-' error 	{ System.out.println("ERROR"); }
		| expresion error 		{ System.out.println("ERROR"); }
	
	termino
		: termino '*' factor
			{ 	
				System.out.println("termino * factor");				
				compTipo(this.t_ptr,this.f_ptr,this.lineNumber);
				compDeclarado(this.t_ptr,this.f_ptr,this.lineNumber);
				
				this.t_ptr = new Terceto("*",this.getObject(this.t_ptr),this.getObject(this.f_ptr));
				this.lista.agregar((Terceto) this.t_ptr);				
			}
		| termino '/' factor
			{ 
				System.out.println("termino / factor");
				compTipo(this.t_ptr,this.f_ptr,this.lineNumber);
				compDeclarado(this.t_ptr,this.f_ptr,this.lineNumber);
				
				this.t_ptr = new Terceto("/",this.getObject(this.t_ptr),this.getObject(this.f_ptr));
				this.lista.agregar((Terceto) this.t_ptr);	
			}
		| factor
			{ 
				System.out.println("factor");
				this.t_ptr = this.f_ptr;
			}
	;
	
	factor 
		: ID 						
			{ 
				System.out.println("id "+$1.sval);
				this.f_ptr = this.symbolTable.getToken($1.sval);
			}
		
		| cte 						
			{ 
				System.out.println("cte "+$1.sval);
				this.f_ptr = this.symbolTable.getToken($1.sval);
			}
		| id_fun	
		
	;
	
	cte
		: ENTERO
			{
				System.out.println("ENTERO");
				Token token = this.symbolTable.getToken($1.sval);
				if (token.getLexema().equals("32768_i")){
					this.symbolTable.AgregarEntrada(new Token("32767_i",token.getClase(),token.getValor(),token.getLinea()));
					this.symbolTable.EliminarEntrada(token);
				}
			}
		| DOBLE
			{
				System.out.println("DOBLE");		
			}
		| '-' ENTERO
			{	
				System.out.println("-ENTERO");
				Token token = this.symbolTable.getToken($2.sval);
				if (token.getLexema().equals("32768_i")){
					this.symbolTable.AgregarEntrada(new Token("-32768_i",token.getClase(),token.getValor(),token.getLinea()));
					this.symbolTable.EliminarEntrada(token);
				}
				else{
					Token tk = new Token("-"+token.getLexema(),token.getClase(),token.getValor(),token.getLinea());
					this.symbolTable.AgregarEntrada(tk);
					this.f_ptr = tk;
				}
			}
		| '-' DOBLE
			{
				System.out.println("-DOBLE");
				Token token = this.symbolTable.getToken($2.sval);
				this.symbolTable.AgregarEntrada(new Token("-"+token.getLexema(),token.getClase(),token.getValor(),token.getLinea()));
			
			}
	;
	
	tipo
		: INTEGER
			{
				System.out.println("INTEGER");		
			}
		| DOUBLE
			{
				System.out.println("DOUBLE");
			}
	;
	

%%

private AnalizadorLexico lexer;
private TablaDeSimbolos symbolTable, vars, funs;
private Texto resultado;
private Texto errores;
private int errorCounter = 0;
private int lineNumber = 0;
private int currentRuleError = 0;
private int pos = 0;
private ListaTercetos lista, closures;
private List<Token> declaraciones;
private Elemento f_ptr,fa_ptr,t_ptr,e_ptr,a_ptr, c_ptr, iee_ptr, ie_ptr, i_ptr, bf_ptr, bfa_ptr, bi_ptr, s_ptr, p_ptr, fun_ptr, ret_ptr, cr_ptr;
private Boolean voidd;

public void inicFUN(String val){	
	// Agrego la funcion como terceto y realizo el acomodamiento
	Token tk = this.symbolTable.getToken(val);
	if (this.bf_ptr==null) this.bf_ptr = this.bfa_ptr;
	this.fun_ptr = new Terceto("F",tk, new Numero(this.lista, (Terceto) this.bf_ptr.getInicio()));
	this.ret_ptr = new Terceto("R",new Numero(lista, (Terceto) this.bfa_ptr.getInicio()),new Numero(lista,(Terceto)this.bi_ptr));
	this.lista.agregar((Terceto) this.fun_ptr);
	this.lista.agregar((Terceto) this.ret_ptr);
	this.lista.ordenarFUN(this.closures);
	//Chequeo los errores posibles
	if (this.funs.isDeclarado(tk))
		this.errores.add("LINEA: "+this.lineNumber+" - ERROR - Variable redeclarada");
	else if (this.vars.isDeclarado(tk))
		this.errores.add("LINEA: "+this.lineNumber+" - ERROR - Variable declarada como identificador");
	else
		this.funs.AgregarEntrada(tk);
	//Seteo el tipo de la funcion como el del retorno
	tk.setTipo(this.bf_ptr.getTipo());
}

public Elemento getObject(Elemento obj){
	if (Terceto.class.getCanonicalName().equals(obj.getClass().getCanonicalName()))
		return new Numero(this.lista,(Terceto)obj);
	else
		return obj;	
}
	
public void compTipo(Elemento op1, Elemento op2, Integer lin){
	if (!op1.getTipo().equals(op2.getTipo())){
		errores.add("Linea: "+lin+" - ERROR: Tipos incompatibles "+op1.getTipo()+" <-> "+op2.getTipo());
	}
}

public void compDeclarado (Elemento op1, Elemento op2, Integer lin){
	if (Token.class.getCanonicalName().equals(op1.getClass().getCanonicalName())
		&& op1.getClase().equals("id") && !this.vars.isDeclarado((Token)op1)){
			errores.add("Linea: "+lin+" - ERROR: Variable no declarada "
								+op1.getTipo()+" "+op1.toString());
	}
	if (Token.class.getCanonicalName().equals(op2.getClass().getCanonicalName()) 
		&& op2.getClase().equals("id") && !this.vars.isDeclarado((Token)op2)){
			errores.add("Linea: "+lin+" - ERROR: Variable no declarada "
								+op2.getTipo()+" "+op2.toString());
	}        
}

public void setArtifacts(AnalizadorLexico lexer, TablaDeSimbolos symbolTable, Texto resultado, Texto errores, ListaTercetos tercetos) {
  	this.lexer = lexer;
  	this.symbolTable = symbolTable;
	this.resultado = resultado;
	this.errores = errores;
	this.lista = tercetos;
	this.closures = new ListaTercetos();
	this.declaraciones = new ArrayList<>();
	this.vars = new TablaDeSimbolos();
	this.funs = new TablaDeSimbolos();
	this.voidd = false;
}

void yyerror(String s) {
	if (!s.equals("syntax error")) { //Ignore yacc default error.
		this.errorCounter++;
	  	System.out.println("Er	ror de sintaxis cerca de la línea " + this.lineNumber + ": " + s);
		this.currentRuleError++;
	}
}

void yyerror(int linea, String s) {
	System.out.println("LINEA: "+ linea +" - ERROR");
	errores.add("LINEA: "+ linea +" - ERROR : "+ s);
}

private int yylex() {
  	Token token = this.lexer.getNextToken();
    if (token != null) {
        this.yylval = new ParserVal(token.getLexema());
        this.yylval.begin_line = token.getLinea();
        this.yylval.end_line = token.getLinea();
        this.lineNumber = token.getLinea();
        return token.getValor();
    }
    return -1;
}

public int getErrorCount() {
	return this.errorCounter;
}

public void parse() {
	yyparse();
}