//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "gramatica.y"
	package analizadorsintactico;
	import analizadorlexico.*;	
	import analizadorlexico.objetos.*;
	import analizadorlexico.objetos.ParserVal;
	import gestorArchivo.Texto;
	import java.util.ArrayList;
	import java.util.List;	
//#line 25 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short ID=257;
public final static short ASSIGN=258;
public final static short NO_IGUAL=259;
public final static short MAYOR_IGUAL=260;
public final static short MENOR_IGUAL=261;
public final static short STRING=262;
public final static short ENTERO=263;
public final static short DOBLE=264;
public final static short INTEGER=265;
public final static short DOUBLE=266;
public final static short FUN=267;
public final static short VOID=268;
public final static short IF=269;
public final static short END_IF=270;
public final static short ELSE=271;
public final static short LOOP=272;
public final static short UNTIL=273;
public final static short RETURN=274;
public final static short PRINT=275;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    1,    3,    3,    3,    3,    3,
    3,    3,    2,    2,    7,    7,    7,    7,    7,   11,
   11,   11,   11,    5,   10,   10,   10,   10,    8,    8,
    8,    8,    8,    8,    8,    4,    4,   14,   14,    6,
    6,   15,   18,   18,   18,   16,   16,   16,   16,   16,
   16,   16,   16,   16,   16,   16,   16,   20,   20,   20,
   21,   21,   19,   19,   22,   22,   12,   12,    9,    9,
    9,    9,    9,   13,   13,   13,   24,   24,   24,   24,
   24,   24,   23,   23,   23,   23,   23,   23,   25,   25,
   25,   26,   26,   26,   27,   27,   27,   27,   17,   17,
};
final static short yylen[] = {                            2,
    1,    1,    2,    1,    2,    4,    4,    4,    6,    2,
    2,    1,    1,    1,    1,    1,    1,    1,    2,    5,
    5,    4,    3,    3,    5,    5,    4,    3,    5,    7,
    7,    6,    5,    4,    3,    3,    1,    1,    2,    1,
    1,    3,    1,    3,    3,   12,   11,   11,   10,    9,
    8,    7,    6,    5,    4,    3,    3,    6,    4,    3,
    1,    1,    1,    2,    1,    1,    3,    3,    4,    4,
    4,    4,    3,    3,    3,    2,    1,    1,    1,    1,
    1,    1,    3,    3,    1,    3,    3,    2,    3,    3,
    1,    1,    1,    1,    1,    1,    2,    2,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,   99,  100,    0,    0,    0,    0,    0,    0,
    2,    4,   13,   14,   15,   16,   17,   18,    0,   40,
   41,    0,   10,   11,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   37,    0,
    0,    0,    3,    5,    0,   19,   43,    0,   73,    0,
    0,   95,   96,    0,   94,    0,    0,   91,   93,   68,
   67,   56,    0,   57,    0,   35,    0,    0,    0,    0,
   28,    0,    0,    0,   38,    0,    0,    0,   23,    0,
    0,    0,    0,   42,    0,   72,   97,   98,   88,   69,
    0,    0,    0,    0,   55,    0,   66,   65,    0,   63,
   24,    0,   82,   80,   81,   77,   78,   79,    0,   34,
    0,    0,    0,    0,    0,    0,   36,   39,   27,    0,
    0,   22,    0,    0,   71,   70,   45,   44,   86,    0,
   87,    0,   89,   90,   54,    0,    0,    0,    0,   64,
   75,    0,   33,   29,    0,    0,    0,    0,    0,    0,
   26,   25,   21,   20,   53,    0,    0,    0,    0,    0,
   32,    0,    0,    0,   60,    0,    0,   52,    0,    0,
    0,    0,    0,   31,   30,    0,   59,    0,   51,    0,
    0,    0,    0,   50,    0,    0,    0,   58,   49,    0,
    0,    0,   48,   47,    0,   46,
};
final static short yydgoto[] = {                          9,
   10,   39,   12,   40,   33,   13,   14,   15,   16,   17,
   18,   19,   67,   76,   20,   21,   22,   48,  171,  139,
  172,  100,   56,  109,   57,   58,   59,
};
final static short yysindex[] = {                        93,
   53,  -31,    0,    0, -102,  -30, -116,  -21,    0,   93,
    0,    0,    0,    0,    0,    0,    0,    0,   11,    0,
    0, -174,    0,    0,   51,  -45,  -28,   55,   28,   -6,
   60,   -8,  -83,   69,  -30, -116,  -20, -130,    0, -191,
   80, -160,    0,    0,  -95,    0,    0,   59,    0,   90,
   28,    0,    0, -100,    0,    8,  101,    0,    0,    0,
    0,    0,  108,    0,  116,    0,   74,   46,  113, -170,
    0,  -83, -189, -124,    0,   79,  121,  -16,    0,  125,
  -14,  127,  132,    0,  -89,    0,    0,    0,    0,    0,
  -33,  -19,   -8,   -8,    0,  136,    0,    0, -146,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   -4,    0,
  141,  151,  -69, -140,  -16,   -5,    0,    0,    0,  155,
  157,    0,  158,  161,    0,    0,    0,    0,    0,  101,
    0,  101,    0,    0,    0,  163,  -78,  -15, -187,    0,
    0,   27,    0,    0,  164, -196,  151,  -69,  157,  161,
    0,    0,    0,    0,    0,  166, -112,  169,  142,  174,
    0,  171,  172, -180,    0,  218,  165,    0,  219,   11,
  165,   -2,  165,    0,    0,  172,    0,  114,    0,  231,
   41,  168,  245,    0,  259,  -81,  267,    0,    0,  273,
  276,  196,    0,    0,  281,    0,
};
final static short yyrindex[] = {                         0,
    1,    0,    0,    0,    0,    0,    0,    0,    0,  327,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -39,    0,    0,    0,    0,    0,  -27,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   32,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   15,    0,    0,    0,    0,    0,    0,    0,    0,
   29,    0,    0,   43,    0,    0,    0,    0,    0,  -13,
    0,   18,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  287,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   57,    0,    0,    0,    0,    0,    0,    5,
    9,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   71,  320,   33,   10,    0,   -1,    0,    0,    0,
    0,  311,    0,    0,  254,    0,    0,    0,   -7,    0,
  160,  -57,   24,    0,   99,  100,    0,
};
final static int YYTABLESIZE=484;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         54,
   12,   92,   92,   92,   92,   92,   38,   92,   27,   32,
  167,   54,   61,   85,    8,   85,   85,   85,   42,   74,
   92,   92,   92,   32,  159,   54,  124,   83,    7,   83,
   83,   83,   85,   85,   85,  150,   54,   64,  181,   38,
   54,  140,    6,  191,   72,   61,   83,   83,   83,   62,
   91,   90,   92,   38,   46,   68,    9,   99,   84,  162,
   84,   84,   84,   97,   77,   70,   77,   27,   73,   91,
   11,   92,   76,  163,   88,  162,   88,   84,   84,   84,
   43,   78,   47,  115,  186,  111,  160,  121,   91,  176,
   92,   88,   88,   88,   49,   80,   23,   97,   62,  112,
  113,   81,   84,   66,  114,  107,  106,  108,   75,  136,
    2,   24,   71,  140,  101,  111,   65,   85,    3,    4,
  140,  137,   35,   79,  149,   36,    2,  138,   37,  147,
  148,   80,  142,   86,    3,    4,    5,  116,   35,   34,
    2,   36,   93,  166,   37,  146,  118,   94,    3,    4,
    5,   95,   35,   28,   29,   36,  110,   97,   37,  178,
   82,   29,   87,   88,  119,   97,  127,  128,  122,   97,
  125,   97,   69,    2,  190,  126,   97,  156,   29,  135,
  164,    3,    4,    5,  143,   35,  145,    2,   36,  130,
  132,   37,  133,  134,  144,    3,    4,    5,  151,   35,
  152,  153,   36,  117,  154,   37,  155,  161,  187,  165,
   50,   51,  168,  173,  174,  175,   92,   52,   53,   92,
   92,   92,  129,   51,   25,   31,   26,   60,   85,   52,
   53,   85,   85,   85,   41,   41,  131,   51,  183,  120,
  158,  123,   83,   52,   53,   83,   83,   83,   51,   63,
  123,  141,   51,  180,   52,   53,   12,   12,   52,   53,
   61,  177,  179,   89,   62,   12,   12,   12,   45,   12,
    8,    8,   12,   84,  184,   12,   84,   84,   84,    8,
    8,    8,   89,    8,    7,    7,    8,   88,  188,    8,
   88,   88,   88,    7,    7,    7,  185,    7,    6,    6,
    7,  102,  189,    7,  103,  104,  105,    6,    6,    6,
  192,    6,    9,    9,    6,   30,  193,    6,   98,  194,
  195,    9,    9,    9,  196,    9,    1,   74,    9,   44,
    0,    9,  182,    0,    0,    2,   55,    0,    0,    0,
    0,    0,   55,    3,    4,    5,    0,   35,    1,    2,
   36,    0,   98,   37,    0,   83,    0,    3,    4,    5,
    0,    6,    0,    0,    7,    0,    0,    8,    0,    0,
    2,   96,    2,    0,    0,    0,    0,    0,    3,    4,
    3,    4,   35,    0,   35,   36,    0,   36,   37,    0,
   37,    0,    0,    0,    0,    0,    0,  169,    2,    0,
    0,   55,   55,   55,   55,    0,    3,    4,    0,    0,
   35,    0,   98,   36,    0,    0,   37,    0,    0,   55,
   98,    2,    0,    0,   98,    0,   98,    0,    0,    3,
    4,   98,    0,   35,    0,    0,   36,    0,    0,   37,
    0,    0,    0,    0,    0,    0,    0,  157,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  170,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  170,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         45,
    0,   41,   42,   43,   44,   45,  123,   47,   40,   40,
  123,   45,   41,   41,    0,   43,   44,   45,   40,   40,
   60,   61,   62,   40,   40,   45,   41,   41,    0,   43,
   44,   45,   60,   61,   62,   41,   45,   44,   41,  123,
   45,   99,    0,  125,   35,   41,   60,   61,   62,   41,
   43,   44,   45,  123,   44,   32,    0,   65,   41,  256,
   43,   44,   45,   65,  256,   33,  256,   40,   36,   43,
    0,   45,   41,  270,   43,  256,   45,   60,   61,   62,
   10,  273,  257,  273,   44,  256,  274,   78,   43,  270,
   45,   60,   61,   62,   44,  256,   44,   99,   44,  270,
  271,  262,   44,   44,   72,   60,   61,   62,   38,  256,
  257,   59,   44,  171,   41,  256,  123,   59,  265,  266,
  178,  268,  269,   44,  115,  272,  257,  274,  275,  270,
  271,  256,  109,   44,  265,  266,  267,  262,  269,  256,
  257,  272,   42,  256,  275,  113,   76,   47,  265,  266,
  267,   44,  269,  256,  257,  272,   44,  159,  275,  167,
  256,  257,  263,  264,   44,  167,  256,  257,   44,  171,
   44,  173,  256,  257,  256,   44,  178,  256,  257,   44,
  148,  265,  266,  267,   44,  269,  256,  257,  272,   91,
   92,  275,   93,   94,   44,  265,  266,  267,   44,  269,
   44,   44,  272,  125,   44,  275,   44,   44,   41,   44,
  256,  257,   44,   40,   44,   44,  256,  263,  264,  259,
  260,  261,  256,  257,  256,  256,  258,  256,  256,  263,
  264,  259,  260,  261,  256,  256,  256,  257,  125,  256,
  256,  256,  256,  263,  264,  259,  260,  261,  257,  256,
  256,  256,  257,  256,  263,  264,  256,  257,  263,  264,
  256,   44,   44,  256,  256,  265,  266,  267,  258,  269,
  256,  257,  272,  256,   44,  275,  259,  260,  261,  265,
  266,  267,  256,  269,  256,  257,  272,  256,   44,  275,
  259,  260,  261,  265,  266,  267,  256,  269,  256,  257,
  272,  256,   44,  275,  259,  260,  261,  265,  266,  267,
   44,  269,  256,  257,  272,    5,   44,  275,   65,   44,
  125,  265,  266,  267,   44,  269,    0,   41,  272,   10,
   -1,  275,  173,   -1,   -1,  257,   26,   -1,   -1,   -1,
   -1,   -1,   32,  265,  266,  267,   -1,  269,  256,  257,
  272,   -1,   99,  275,   -1,   45,   -1,  265,  266,  267,
   -1,  269,   -1,   -1,  272,   -1,   -1,  275,   -1,   -1,
  257,  256,  257,   -1,   -1,   -1,   -1,   -1,  265,  266,
  265,  266,  269,   -1,  269,  272,   -1,  272,  275,   -1,
  275,   -1,   -1,   -1,   -1,   -1,   -1,  256,  257,   -1,
   -1,   91,   92,   93,   94,   -1,  265,  266,   -1,   -1,
  269,   -1,  159,  272,   -1,   -1,  275,   -1,   -1,  109,
  167,  257,   -1,   -1,  171,   -1,  173,   -1,   -1,  265,
  266,  178,   -1,  269,   -1,   -1,  272,   -1,   -1,  275,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  137,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  159,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  173,
};
}
final static short YYFINAL=9;
final static short YYMAXTOKEN=275;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,"';'",
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"ID","ASSIGN","NO_IGUAL","MAYOR_IGUAL",
"MENOR_IGUAL","STRING","ENTERO","DOBLE","INTEGER","DOUBLE","FUN","VOID","IF",
"END_IF","ELSE","LOOP","UNTIL","RETURN","PRINT",
};
final static String yyrule[] = {
"$accept : programa",
"programa : sentencia",
"sentencia : sentencia_simple",
"sentencia : sentencia sentencia_simple",
"sentencia : sentencia_error",
"sentencia : sentencia sentencia_error",
"sentencia_error : PRINT '(' STRING ')'",
"sentencia_error : LOOP bloque_sentencia UNTIL correcto_condicion",
"sentencia_error : IF correcto_condicion bloque_sentencia END_IF",
"sentencia_error : IF correcto_condicion bloque_sentencia ELSE bloque_sentencia END_IF",
"sentencia_error : error ','",
"sentencia_error : error ';'",
"sentencia_error : error",
"sentencia_simple : sentencia_declarativa",
"sentencia_simple : sentencia_ejecutable",
"sentencia_ejecutable : sentencia_seleccion",
"sentencia_ejecutable : sentencia_asignacion",
"sentencia_ejecutable : sentencia_control",
"sentencia_ejecutable : sentencia_salida",
"sentencia_ejecutable : id_fun ','",
"sentencia_salida : PRINT '(' STRING ')' ','",
"sentencia_salida : PRINT '(' STRING error ','",
"sentencia_salida : PRINT '(' error ','",
"sentencia_salida : PRINT error ','",
"correcto_condicion : '(' condicion ')'",
"sentencia_control : LOOP bloque_sentencia UNTIL correcto_condicion ','",
"sentencia_control : LOOP bloque_sentencia UNTIL error ','",
"sentencia_control : LOOP bloque_sentencia error ','",
"sentencia_control : LOOP error ','",
"sentencia_seleccion : IF correcto_condicion bloque_sentencia END_IF ','",
"sentencia_seleccion : IF correcto_condicion bloque_sentencia ELSE bloque_sentencia END_IF ','",
"sentencia_seleccion : IF correcto_condicion bloque_sentencia ELSE bloque_sentencia error ','",
"sentencia_seleccion : IF correcto_condicion bloque_sentencia ELSE error ','",
"sentencia_seleccion : IF correcto_condicion bloque_sentencia error ','",
"sentencia_seleccion : IF correcto_condicion error ','",
"sentencia_seleccion : IF error ','",
"bloque_sentencia : '{' bloque '}'",
"bloque_sentencia : sentencia_simple",
"bloque : sentencia_simple",
"bloque : bloque sentencia_simple",
"sentencia_declarativa : declaraciones",
"sentencia_declarativa : closure_funcion",
"declaraciones : tipo lista_variable ','",
"lista_variable : ID",
"lista_variable : lista_variable ';' ID",
"lista_variable : lista_variable ';' error",
"closure_funcion : FUN id_fun '{' closure_cuerpo closure_nulo RETURN '(' closure_retorno ')' ',' '}' ','",
"closure_funcion : FUN id_fun '{' closure_cuerpo RETURN '(' closure_retorno ')' ',' '}' ','",
"closure_funcion : FUN id_fun '{' closure_cuerpo RETURN '(' closure_retorno ')' ',' error ','",
"closure_funcion : FUN id_fun '{' closure_cuerpo RETURN '(' closure_retorno ')' error ','",
"closure_funcion : FUN id_fun '{' closure_cuerpo RETURN '(' closure_retorno error ','",
"closure_funcion : FUN id_fun '{' closure_cuerpo RETURN '(' error ','",
"closure_funcion : FUN id_fun '{' closure_cuerpo RETURN error ','",
"closure_funcion : FUN id_fun '{' closure_cuerpo error ','",
"closure_funcion : FUN id_fun '{' error ','",
"closure_funcion : FUN id_fun error ','",
"closure_funcion : FUN error ','",
"closure_funcion : FUN id_fun ','",
"closure_nulo : VOID id_fun '{' closure_cuerpo '}' ','",
"closure_nulo : VOID id_fun error ','",
"closure_nulo : VOID error ','",
"closure_retorno : id_fun",
"closure_retorno : closure_cuerpo",
"closure_cuerpo : sentencia_limitada",
"closure_cuerpo : closure_cuerpo sentencia_limitada",
"sentencia_limitada : declaraciones",
"sentencia_limitada : sentencia_ejecutable",
"id_fun : ID '(' ')'",
"id_fun : ID '(' error",
"sentencia_asignacion : ID ASSIGN expresion ','",
"sentencia_asignacion : id_fun ASSIGN id_fun ','",
"sentencia_asignacion : id_fun ASSIGN error ','",
"sentencia_asignacion : ID ASSIGN error ','",
"sentencia_asignacion : ID error ','",
"condicion : expresion comparador expresion",
"condicion : expresion comparador error",
"condicion : expresion error",
"comparador : '='",
"comparador : '<'",
"comparador : '>'",
"comparador : MAYOR_IGUAL",
"comparador : MENOR_IGUAL",
"comparador : NO_IGUAL",
"expresion : expresion '+' termino",
"expresion : expresion '-' termino",
"expresion : termino",
"expresion : expresion '+' error",
"expresion : expresion '-' error",
"expresion : expresion error",
"termino : termino '*' factor",
"termino : termino '/' factor",
"termino : factor",
"factor : ID",
"factor : cte",
"factor : id_fun",
"cte : ENTERO",
"cte : DOBLE",
"cte : '-' ENTERO",
"cte : '-' DOBLE",
"tipo : INTEGER",
"tipo : DOUBLE",
};

//#line 596 "gramatica.y"

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
		this.errores.add("LINEA: "+pos+" - ERROR - Variable redeclarada");
	else if (this.vars.isDeclarado(tk))
		this.errores.add("LINEA: "+pos+" - ERROR - Variable declarada como identificador");
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
		errores.add("LINEA: "+lin+" - ERROR - Tipos incompatibles "+op1.getTipo()+" <-> "+op2.getTipo());
	}
}

public void compDeclarado (Elemento op1, Elemento op2, Integer lin){
	if (Token.class.getCanonicalName().equals(op1.getClass().getCanonicalName())
		&& op1.getClase().equals("id") && !this.vars.isDeclarado((Token)op1)){
			errores.add("LINEA: "+lin+" - ERROR - Variable no declarada "
								+op1.getTipo()+" "+op1.toString());
	}
	if (Token.class.getCanonicalName().equals(op2.getClass().getCanonicalName()) 
		&& op2.getClase().equals("id") && !this.vars.isDeclarado((Token)op2)){
			errores.add("LINEA: "+lin+" - ERROR - Variable no declarada "
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
//#line 558 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 20 "gramatica.y"
{ 
				System.out.println("El programa funciona");
			}
break;
case 2:
//#line 28 "gramatica.y"
{ 
				System.out.println("sentencia_simple");
			}
break;
case 3:
//#line 32 "gramatica.y"
{ 
				System.out.println("sentencia sentencia_simple");
			}
break;
case 4:
//#line 36 "gramatica.y"
{ 
				System.out.println("sentencia_error");
			}
break;
case 5:
//#line 40 "gramatica.y"
{ 
				System.out.println("sentencia sentencia_error");
			}
break;
case 6:
//#line 46 "gramatica.y"
{ yyerror(val_peek(0).begin_line, "Sentencia de salida PRINT"); }
break;
case 7:
//#line 47 "gramatica.y"
{ yyerror(pos,"Sentencia de control LOOP-UNTIL"); }
break;
case 8:
//#line 48 "gramatica.y"
{ yyerror(val_peek(0).begin_line,"Sentencia de seleccion IF-END_IF"); }
break;
case 9:
//#line 49 "gramatica.y"
{ yyerror(val_peek(0).begin_line,"Sentencia de seleccion IF-END_IF"); }
break;
case 10:
//#line 50 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Gramática no reconocida"); }
break;
case 11:
//#line 51 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Gramática no reconocida"); }
break;
case 12:
//#line 52 "gramatica.y"
{ yyerror(val_peek(0).begin_line,"Gramática no reconocida"); }
break;
case 13:
//#line 57 "gramatica.y"
{ 
				System.out.println("sentencia_declarativa");
			}
break;
case 14:
//#line 61 "gramatica.y"
{ 
				System.out.println("sentencia_ejecutable");
			}
break;
case 15:
//#line 68 "gramatica.y"
{ 
				System.out.println("sentencia_seleccion");
				this.s_ptr = i_ptr;
				
			}
break;
case 16:
//#line 74 "gramatica.y"
{ 
				System.out.println("sentencia_asignacion");
				this.s_ptr = this.a_ptr;
			}
break;
case 17:
//#line 79 "gramatica.y"
{ 
				System.out.println("sentencia_control");
				this.s_ptr = this.c_ptr;
			}
break;
case 18:
//#line 84 "gramatica.y"
{ 
				System.out.println("sentencia_salida");
				this.s_ptr = this.p_ptr;
			}
break;
case 19:
//#line 89 "gramatica.y"
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
					Token ter;
					if (element.getOperando2().getClass().getCanonicalName().equals(Token.class.getCanonicalName())){
						ter = (Token) element.getOperando2();
						while (size > 0 && element.getOperando1() != ter){
							size--;
							if (element.getOperador().equals("R"))
								resul = ((Numero)element.getOperando1()).getInicio();						
							element = this.lista.getElement(size);
						}
						if (element.getOperador().equals("C"))
							resul = ((Numero)element.getOperando2()).getInicio();
					}
					this.lista.agregar(new Terceto("C",this.f_ptr,new Numero(lista, resul)));
				}
				else
					this.errores.add("LINEA: "+this.lineNumber+" - ERROR - Variable no declarada como función");
				
			}
break;
case 20:
//#line 123 "gramatica.y"
{ 
				resultado.add("LINEA: "+ val_peek(4).begin_line +" Sentencia de salida PRINT");
				this.p_ptr = new Terceto("P",this.symbolTable.getToken(val_peek(2).sval),null);
				this.lista.agregar((Terceto) this.p_ptr);
			}
break;
case 21:
//#line 128 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Sentencia de salida PRINT"); }
break;
case 22:
//#line 129 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Sentencia de salida PRINT"); }
break;
case 23:
//#line 130 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Sentencia de salida PRINT"); }
break;
case 24:
//#line 134 "gramatica.y"
{ pos = val_peek(0).begin_line; }
break;
case 25:
//#line 139 "gramatica.y"
{ 
				if (this.bfa_ptr == null)
					this.bfa_ptr = bf_ptr;
				resultado.add("LINEA: " + val_peek(4).begin_line +" Sentencia de control LOOP-UNTIL");
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
break;
case 26:
//#line 156 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Sentencia de control LOOP-UNTIL"); }
break;
case 27:
//#line 157 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Sentencia de control LOOP-UNTIL"); }
break;
case 28:
//#line 158 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Sentencia de control LOOP-UNTIL"); }
break;
case 29:
//#line 163 "gramatica.y"
{ 
				System.out.println("IF '(' condicion ')' bloque_sentencia END_IF ,");
				resultado.add("LINEA: "+val_peek(4).begin_line + " Sentencia de seleccion IF-END_IF");
				this.i_ptr = new Terceto("BF",
                                        new Numero(this.lista,(Terceto)this.c_ptr),
                                        new Numero(this.lista,(Terceto)this.bi_ptr,1));
				this.lista.agregar((Terceto) this.i_ptr);
				this.lista.ordenarIF();
			}
break;
case 30:
//#line 173 "gramatica.y"
{ 
				System.out.println("IF '(' condicion ')' bloque_sentencia else bloque_sentencia END_IF ,");
				resultado.add("LINEA: "+val_peek(6).begin_line + " Sentencia de seleccion IF-ELSE-END_IF");
				this.ie_ptr = new Terceto("BF",
                                        new Numero(this.lista,(Terceto)this.c_ptr),
                                        new Numero(this.lista,(Terceto)this.bf_ptr));
				this.lista.agregar((Terceto) this.ie_ptr);
				this.iee_ptr = new Terceto("BI",
                                        new Numero(this.lista,(Terceto)this.bi_ptr,1), null);
				this.lista.agregar((Terceto) this.iee_ptr);
				this.lista.ordenarIF();
			}
break;
case 31:
//#line 185 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Sentencia de seleccion IF-END_IF"); }
break;
case 32:
//#line 186 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Sentencia de seleccion IF-END_IF"); }
break;
case 33:
//#line 187 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Sentencia de seleccion IF-END_IF"); }
break;
case 34:
//#line 188 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Sentencia de seleccion IF-END_IF"); }
break;
case 35:
//#line 189 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Sentencia de seleccion IF-END_IF"); }
break;
case 36:
//#line 195 "gramatica.y"
{ 
				System.out.println("{ bloque_sentencia sentencia_simple }");
			}
break;
case 37:
//#line 199 "gramatica.y"
{ 
				System.out.println("sentencia_simple");
				this.bfa_ptr = this.bf_ptr;
				this.bi_ptr = this.s_ptr;
				this.bf_ptr = this.s_ptr;
			}
break;
case 38:
//#line 209 "gramatica.y"
{ 
				System.out.println("sentencia_simple");
				this.bfa_ptr = this.bf_ptr;
				this.bi_ptr = this.s_ptr;
				this.bf_ptr = this.s_ptr;
			}
break;
case 39:
//#line 216 "gramatica.y"
{ 
				System.out.println("{ bloque_sentencia sentencia_simple }");
				this.bi_ptr = this.s_ptr;
			}
break;
case 40:
//#line 224 "gramatica.y"
{
				System.out.println("tipo lista_variable ,");
				resultado.add("LINEA: " + pos  + " Sentencia declarativa");
			}
break;
case 41:
//#line 230 "gramatica.y"
{
				System.out.println("closure_funcion");
				resultado.add("LINEA: " + pos + " Sentencia declarativa FUN");				
			}
break;
case 42:
//#line 238 "gramatica.y"
{				
				for (Token tk: this.declaraciones){
					tk.setTipo(val_peek(2).sval);
					if (this.vars.isDeclarado(tk))
						this.errores.add("LINEA: "+this.lineNumber+" - ERROR - Variable redeclarada");
					else if (this.funs.isDeclarado(tk))
						this.errores.add("LINEA: "+this.lineNumber+" - ERROR - Variable declarada como funcion");
					else 
						this.vars.AgregarEntrada(tk);					
				}
				this.declaraciones.clear();
			}
break;
case 43:
//#line 254 "gramatica.y"
{ 
				System.out.println("ID"); 
				pos=val_peek(0).begin_line;				
				this.f_ptr = this.symbolTable.getToken(val_peek(0).sval);
				this.declaraciones.add((Token) this.f_ptr);
			}
break;
case 44:
//#line 261 "gramatica.y"
{ 
				System.out.println("lista_variable ; ID"); 
				pos=val_peek(0).begin_line;
				this.f_ptr = this.symbolTable.getToken(val_peek(0).sval);				
				this.declaraciones.add((Token) this.f_ptr);
			}
break;
case 45:
//#line 267 "gramatica.y"
{ yyerror(val_peek(0).begin_line,"Lista de variables"); }
break;
case 46:
//#line 272 "gramatica.y"
{	
				System.out.println("FUN closure");
				Token tk = this.symbolTable.getToken(val_peek(10).sval);
				tk.setTipo("fun");
				if (this.cr_ptr == this.f_ptr)
					if (this.ret_ptr == this.symbolTable.getToken(val_peek(4).sval)) 
						System.out.println("->retorno void");
					else if(this.symbolTable.getToken(val_peek(10).sval) == this.cr_ptr){
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
				pos = val_peek(11).begin_line;
				this.inicFUN(val_peek(10).sval);
			}
break;
case 47:
//#line 294 "gramatica.y"
{	
				System.out.println("FUN closure");
				Token tk = this.symbolTable.getToken(val_peek(9).sval);
				tk.setTipo("fun");
				if (this.cr_ptr == this.f_ptr)	
					if(this.symbolTable.getToken(val_peek(9).sval) == this.cr_ptr){
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
				
				pos = val_peek(10).begin_line;
				this.inicFUN(val_peek(9).sval);
			}
break;
case 48:
//#line 314 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Closure FUN"); }
break;
case 49:
//#line 315 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Closure FUN"); }
break;
case 50:
//#line 316 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Closure FUN"); }
break;
case 51:
//#line 317 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Closure FUN"); }
break;
case 52:
//#line 318 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Closure FUN"); }
break;
case 53:
//#line 319 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Closure FUN"); }
break;
case 54:
//#line 320 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Closure FUN"); }
break;
case 55:
//#line 321 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Closure FUN"); }
break;
case 56:
//#line 322 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Closure FUN"); }
break;
case 57:
//#line 324 "gramatica.y"
{
				Token tk = ((Token)this.f_ptr);
				tk.setTipo("fun");
				this.funs.AgregarEntrada(tk);
			}
break;
case 58:
//#line 333 "gramatica.y"
{	
				System.out.println("detecto un VOID closure");
				this.pos = val_peek(5).begin_line;
				this.voidd = true;
				resultado.add("LINEA: " + pos + " Sentencia declarativa VOID");
				/* Agrego la funcion como terceto y realizo el acomodamiento*/
				Token tk = this.symbolTable.getToken(val_peek(4).sval);
				this.ret_ptr = tk;
				tk.setClase("void");
				this.fun_ptr = new Terceto("V",tk, new Numero(this.lista, (Terceto) this.bfa_ptr.getInicio()));
				this.lista.agregar((Terceto) this.fun_ptr);
				this.lista.ordenarVOID(this.closures);
				/*Chequeo los errores posibles*/
				if (this.funs.isDeclarado(tk))
					this.errores.add("LINEA: "+pos+" - ERROR - Variable redeclarada");
				else if (this.vars.isDeclarado(tk))
					this.errores.add("LINEA: "+pos+" - ERROR - Variable declarada como identificador");
				else
					this.funs.AgregarEntrada(tk);
				/*Seteo el tipo de la funcion como el del retorno*/
				tk.setTipo("void"); 
			}
break;
case 59:
//#line 355 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Closure VOID"); }
break;
case 60:
//#line 356 "gramatica.y"
{ yyerror(val_peek(1).begin_line,"Closure VOID"); }
break;
case 61:
//#line 361 "gramatica.y"
{ 
				this.cr_ptr = f_ptr;			
			}
break;
case 62:
//#line 365 "gramatica.y"
{
				this.cr_ptr = new Numero (this.lista,(Terceto)this.bf_ptr.getInicio());
				this.bf_ptr = this.a_ptr;
			}
break;
case 63:
//#line 373 "gramatica.y"
{	
				System.out.println("sentencia_limitada");
				this.bfa_ptr = this.s_ptr;				
				System.out.println("PASA POR ACA ----->"+this.s_ptr);
			}
break;
case 64:
//#line 379 "gramatica.y"
{	
				System.out.println("cuerpo sentencia_limitada");
				this.bi_ptr = this.s_ptr;
			}
break;
case 65:
//#line 387 "gramatica.y"
{ 
				System.out.println("sentencia_declarativa");				
			}
break;
case 66:
//#line 391 "gramatica.y"
{ 
				System.out.println("sentencia_ejecutable");
			}
break;
case 67:
//#line 398 "gramatica.y"
{	
				System.out.println("id "+val_peek(2).sval+" ( )");
				this.fa_ptr = this.f_ptr;
				this.f_ptr = this.symbolTable.getToken(val_peek(2).sval);
				pos = val_peek(2).begin_line;
			}
break;
case 68:
//#line 404 "gramatica.y"
{ yyerror(val_peek(0).begin_line,"Indentificador FUN"); }
break;
case 69:
//#line 409 "gramatica.y"
{
				resultado.add("LINEA: " + val_peek(0).begin_line + " Asignacion ");
				this.a_ptr = new Terceto(":=",this.symbolTable.getToken(val_peek(3).sval),this.getObject(this.e_ptr));
				compTipo(this.a_ptr,this.e_ptr,this.lineNumber);
				compDeclarado(this.a_ptr,this.e_ptr,this.lineNumber);

				this.lista.agregar((Terceto) this.a_ptr);
				
			}
break;
case 70:
//#line 419 "gramatica.y"
{
				Token tk = (Token) fa_ptr;
				if (this.funs.isDeclarado(tk))
					this.errores.add("LINEA: "+pos+" - ERROR - Variable redeclarada");
				else if (this.vars.isDeclarado(tk))
					this.errores.add("LINEA: "+pos+" - ERROR - Variable declarada como identificador");
				else{
					this.funs.AgregarEntrada(tk);				
					resultado.add("LINEA: " + val_peek(0).begin_line + " Asignacion ");				
					
					/*int size = this.lista.size()-1;*/
					/*Terceto element = this.lista.getElement(size),*/
					/*		resul=null;*/
					/*while (size > 0 && element.getOperando1() != this.f_ptr){*/
					/*	size--;*/
					/*	if (element.getOperador().equals("R"))*/
					/*		resul = ((Numero)element.getOperando1()).getInicio();*/
					/*	element = this.lista.getElement(size);						*/
					/*}                                        */
					/*this.a_ptr = new Terceto(":=",this.getObject(this.f_ptr),new Numero(lista, resul));*/
					
					this.a_ptr = new Terceto(":=",this.getObject(this.fa_ptr),this.getObject(this.f_ptr));
					this.lista.agregar((Terceto) this.a_ptr);
				}
			}
break;
case 71:
//#line 444 "gramatica.y"
{ yyerror(val_peek(0).begin_line,"Asignacion"); }
break;
case 72:
//#line 445 "gramatica.y"
{ yyerror(val_peek(0).begin_line,"Asignacion"); }
break;
case 73:
//#line 446 "gramatica.y"
{ yyerror(val_peek(0).begin_line,"Asignacion"); }
break;
case 74:
//#line 451 "gramatica.y"
{ 
				System.out.println("expresion = expresion");
				resultado.add("LINEA: " + this.lineNumber + " Comparacion ");
				compTipo(this.a_ptr,this.e_ptr,this.lineNumber);
				compDeclarado(this.a_ptr,this.e_ptr,this.lineNumber);

				this.c_ptr = new Terceto(val_peek(1).sval,this.getObject(this.a_ptr),this.getObject(this.e_ptr));
				this.lista.agregar((Terceto)this.c_ptr);
			}
break;
case 75:
//#line 460 "gramatica.y"
{ yyerror(val_peek(0).begin_line,"Comparacion"); }
break;
case 76:
//#line 461 "gramatica.y"
{ yyerror(val_peek(0).begin_line,"Comparacion"); }
break;
case 83:
//#line 475 "gramatica.y"
{ 
				System.out.println("detecto una expresion '+' termino");
				compTipo(this.e_ptr,this.t_ptr,this.lineNumber);
				compDeclarado(this.e_ptr,this.t_ptr,this.lineNumber);
				
				this.e_ptr = new Terceto("+",this.getObject(this.e_ptr),this.getObject(this.t_ptr));
				this.lista.agregar((Terceto) this.e_ptr);
			}
break;
case 84:
//#line 484 "gramatica.y"
{ 
				System.out.println("detecto una expresion '-' termino");
				/*compTipo(this.e_ptr,this.t_ptr,this.lineNumber);*/
				/*compDeclarado(this.e_ptr,this.t_ptr,this.lineNumber);*/
				
				this.e_ptr = new Terceto("-",this.getObject(this.e_ptr),this.getObject(this.t_ptr));
				this.lista.agregar((Terceto) this.e_ptr);
			}
break;
case 85:
//#line 493 "gramatica.y"
{ 
				System.out.println("detecto una termino");				
				this.a_ptr = this.e_ptr;	/*REVISAR*/
				this.e_ptr = this.t_ptr;				
			}
break;
case 86:
//#line 498 "gramatica.y"
{ System.out.println("ERROR"); }
break;
case 87:
//#line 499 "gramatica.y"
{ System.out.println("ERROR"); }
break;
case 88:
//#line 500 "gramatica.y"
{ System.out.println("ERROR"); }
break;
case 89:
//#line 505 "gramatica.y"
{ 	
				System.out.println("termino * factor");				
				compTipo(this.t_ptr,this.f_ptr,this.lineNumber);
				compDeclarado(this.t_ptr,this.f_ptr,this.lineNumber);
				
				this.t_ptr = new Terceto("*",this.getObject(this.t_ptr),this.getObject(this.f_ptr));
				this.lista.agregar((Terceto) this.t_ptr);				
			}
break;
case 90:
//#line 514 "gramatica.y"
{ 
				System.out.println("termino / factor");
				compTipo(this.t_ptr,this.f_ptr,this.lineNumber);
				compDeclarado(this.t_ptr,this.f_ptr,this.lineNumber);
				
				this.t_ptr = new Terceto("/",this.getObject(this.t_ptr),this.getObject(this.f_ptr));
				this.lista.agregar((Terceto) this.t_ptr);	
			}
break;
case 91:
//#line 523 "gramatica.y"
{ 
				System.out.println("factor");
				this.t_ptr = this.f_ptr;
			}
break;
case 92:
//#line 531 "gramatica.y"
{ 
				System.out.println("id "+val_peek(0).sval);
				this.f_ptr = this.symbolTable.getToken(val_peek(0).sval);
			}
break;
case 93:
//#line 537 "gramatica.y"
{ 
				System.out.println("cte "+val_peek(0).sval);
				Token tk = this.symbolTable.getToken(val_peek(0).sval);
				if (tk!=null)
					this.f_ptr = tk;
			}
break;
case 95:
//#line 549 "gramatica.y"
{
				System.out.println("ENTERO");
				Token token = this.symbolTable.getToken(val_peek(0).sval);
				if (token.getLexema().equals("32768_i")){
					this.symbolTable.AgregarEntrada(new Token("32767_i",token.getClase(),token.getValor(),token.getLinea()));
					/*this.symbolTable.EliminarEntrada(token);*/
				}
			}
break;
case 96:
//#line 558 "gramatica.y"
{
				System.out.println("DOBLE");		
			}
break;
case 97:
//#line 562 "gramatica.y"
{	
				System.out.println("-ENTERO");
				Token token = this.symbolTable.getToken(val_peek(0).sval);
				if (token.getLexema().equals("32768_i")){					
					this.symbolTable.AgregarEntrada(new Token("-32768_i",token.getClase(),token.getValor(),token.getLinea()));					
				}
				else{
					Token tk = new Token("-"+token.getLexema(),token.getClase(),token.getValor(),token.getLinea());
					this.symbolTable.AgregarEntrada(tk);
					this.f_ptr = tk;
				}
			}
break;
case 98:
//#line 575 "gramatica.y"
{
				System.out.println("-DOBLE");
				Token token = this.symbolTable.getToken(val_peek(0).sval);
				this.symbolTable.AgregarEntrada(new Token("-"+token.getLexema(),token.getClase(),token.getValor(),token.getLinea()));
				
			}
break;
case 99:
//#line 585 "gramatica.y"
{
				System.out.println("INTEGER");		
			}
break;
case 100:
//#line 589 "gramatica.y"
{
				System.out.println("DOUBLE");
			}
break;
//#line 1413 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
