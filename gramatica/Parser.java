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
package AnalizadorSintactico;
import java.lang.Math;
import java.io.*;
import java.util.Hashtable;
import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.Token;
//#line 24 "Parser.java"




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
public final static short PROC=257;
public final static short NI=258;
public final static short VAR=259;
public final static short MENORIGUAL=260;
public final static short CONST_INT=261;
public final static short CONST_FLOAT=262;
public final static short MAYORIGUAL=263;
public final static short IGUALIGUAL=264;
public final static short DISTINTO=265;
public final static short CADENA=266;
public final static short IF=267;
public final static short ELSE=268;
public final static short END_IF=269;
public final static short OUT=270;
public final static short THEN=271;
public final static short FUNC=272;
public final static short RETURN=273;
public final static short INTEGER=274;
public final static short FLOAT=275;
public final static short WHILE=276;
public final static short LOOP=277;
public final static short ID=278;
public final static short TRUE=279;
public final static short FALSE=280;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    2,    2,    3,    3,    3,    3,
    3,    4,    4,    4,    4,   12,   12,   12,   12,   12,
   12,   12,   14,   14,   14,   14,   13,   13,   13,   13,
   15,   15,   15,   15,   15,   15,   11,   11,   11,    5,
    5,    5,    6,    7,    7,    7,    8,    8,    8,    8,
    8,    8,    8,    8,    8,    8,    8,    9,    9,   18,
   18,   19,   20,   20,   17,   17,   21,   21,   21,   21,
   21,   21,   10,   10,   16,   16,   16,   16,   22,   22,
   22,   23,   23,   23,   23,   23,
};
final static short yylen[] = {                            2,
    1,    3,    2,    2,    1,    1,    1,    1,    1,    1,
    1,    2,    1,    1,    1,    9,    8,    6,    5,    8,
    8,    7,    3,    2,    2,    2,    1,    3,    5,    7,
    2,    3,    1,    1,    2,    2,    1,    3,    2,    3,
    2,    2,    4,    6,    5,    4,    8,    6,    7,    7,
    7,    7,    6,    5,    5,    5,    4,    4,    3,    2,
    3,    1,    3,    2,    3,    3,    1,    1,    1,    1,
    1,    1,    1,    1,    3,    3,    1,    3,    3,    3,
    1,    1,    1,    2,    1,    2,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,   73,   74,    0,    0,    0,    0,
    0,    0,    5,    6,    7,    8,    9,   10,   11,    0,
    0,   13,    4,    0,    0,   83,   85,   82,    0,    0,
    0,    0,    0,   81,    0,    0,    0,    0,    0,    0,
    0,    0,    3,   37,    0,   39,    0,    0,    0,   34,
    0,    0,    0,    0,    0,    0,   84,   86,   68,   69,
   67,   70,    0,   71,   72,    0,    0,    0,    0,    0,
   62,    0,    0,    0,    0,    0,    0,    0,   59,    0,
    0,    0,    2,   38,    0,    0,   35,    0,   31,    0,
    0,    0,   78,    0,    0,    0,    0,    0,    0,    0,
    0,   57,   60,   79,   80,   43,    0,    0,   46,   58,
    0,    0,    0,    0,    0,   32,    0,    0,    0,   54,
    0,   56,    0,   64,   61,    0,    0,   45,    0,   24,
    0,    0,   25,    0,    0,    0,    0,    0,    0,    0,
    0,   48,   63,   53,   44,   23,   22,    0,    0,    0,
    0,    0,   49,   51,   52,    0,   17,   21,    0,   20,
    0,   47,   16,   30,
};
final static short yydgoto[] = {                         10,
   11,   12,   13,   14,   15,   16,   17,   18,   19,   20,
   21,   22,   52,  114,   53,   31,   32,   72,   73,  100,
   68,   33,   34,
};
final static short yysindex[] = {                        66,
  -32,  -37,  -38,    9,    0,    0,  -36,    3,  -34,    0,
  119,   44,    0,    0,    0,    0,    0,    0,    0, -200,
  -19,    0,    0,   65, -146,    0,    0,    0,  -40, -185,
   61,   91,   -2,    0, -154,  -34, -157,  -26,  -34,  -34,
   -4,   74,    0,    0,  -19,    0, -143,   27, -167,    0,
 -140,   83,   96,   91,   55,  104,    0,    0,    0,    0,
    0,    0,  -34,    0,    0,    1,    1,  -34,    3,  120,
    0, -150,  103,    1,    1,  123,  -24,   91,    0,  -28,
   -4,   52,    0,    0,  -53,  126,    0, -110,    0,  -41,
 -146, -138,    0,  159,   -4,   -2,   -2,   -4,  111,  188,
   91,    0,    0,    0,    0,    0,   91, -106,    0,    0,
  -43,   66,  -88,   51,  -42,    0,   53,  138,   91,    0,
   91,    0, -125,    0,    0,  124,  -90,    0,   91,    0,
  -77,   78,    0,   66,   66,   63,   66, -146,  -82,  -81,
  176,    0,    0,    0,    0,    0,    0,   90,  105,   66,
  135,  145,    0,    0,    0,  -78,    0,    0,  147,    0,
 -146,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,  -21,    0,    0,
  193,    0,    0,    0,    0,    0,    0,    0,    0,  136,
  139,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -31,    0,    0,    0,    0,    0,  140,    0,
  142,    0,    0,    0,  143,    0,    0,    0,    0,    0,
   19,    0,  153,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  148,    0,    0,    0,    0,    0,    0,   41,    0,    0,
    0,    0,    0,    0,   18,   -9,   13,   30,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  150,    0,    0,    0,  163,    0,    0,
    0,    0,  151,    0,    0,    0,    0,    0,    0,    0,
  -22,    0,    0,    0,    0,  152,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  165,    0,    0,    0,  154,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  -51,   10,   56,    0,    0,    0,    0,    0,    0,   -1,
    6,    0,  -15,  -48,  -69,   26,   73,   71,  -13,    0,
    0,   81,   80,
};
final static int YYTABLESIZE=466;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         40,
   54,   29,   25,   36,   30,   40,   30,  113,   30,   77,
   30,   77,  110,   77,   79,   47,  108,  131,  113,  113,
   42,  118,   37,   51,   47,   45,   23,   77,   77,   77,
   77,   75,   86,   75,   41,   75,   26,   37,   66,   74,
   67,  117,   38,   80,   75,   30,   51,   88,   35,   75,
   75,   75,   75,   76,   55,   76,   99,   76,   66,   33,
  132,   55,   33,   39,   81,   82,  136,   85,  152,  112,
   65,   76,   76,   76,   76,   57,   58,   44,   66,   37,
  135,   36,  148,  149,   36,  151,  126,   71,   95,   51,
   65,   77,   93,   98,   66,   93,   67,   66,  159,   67,
   26,   56,   43,   66,   48,   67,    5,    6,   77,   71,
   87,   76,   49,   75,   64,   63,   65,  101,  102,   78,
   64,   63,   65,   90,   92,   71,    9,    5,    6,  119,
  120,   50,   83,   71,   84,   76,   51,   89,    9,   91,
   66,   42,  141,  142,   94,  164,   96,   97,  109,   71,
    9,    9,   65,  104,  105,   71,   71,   42,   42,   51,
   42,  103,   71,  106,  123,    9,  115,  116,   42,  124,
  129,  127,  133,  134,   71,  137,   71,  128,  144,    9,
    9,  138,  143,  146,   71,  150,  153,  154,  161,  139,
  162,  140,    1,   27,   15,    9,   71,   14,   41,  145,
   42,   12,  147,   28,  111,   29,   40,    9,   19,   55,
   18,  156,   50,   70,  157,  111,  111,  130,    0,    9,
   26,   27,   26,   27,   26,   27,   26,   27,   77,  158,
    0,   77,   77,   77,    0,   77,    9,   28,   77,   28,
   24,   28,    0,   28,   77,   77,   77,    0,    9,   46,
   75,   44,  107,   75,   75,   75,   37,   75,   46,  160,
   75,   26,   27,    0,    0,    0,   75,   75,   75,    0,
    0,  163,   76,    0,    0,   76,   76,   76,   28,   76,
    0,   70,   76,    0,   66,   49,    0,   66,   76,   76,
   76,    0,    0,   66,   66,   66,   65,    0,   70,   65,
    5,    6,    0,    0,   50,   65,   65,   65,    0,    0,
    0,    0,  125,    0,   59,    0,    0,   60,   61,   62,
   59,    1,    2,   60,   61,   62,    0,    0,    0,    0,
    0,    0,    3,    0,    2,    4,    0,    0,    0,    5,
    6,    7,    0,    8,    3,    0,    2,    4,    0,    0,
    0,    5,    6,    7,    0,    8,    3,    3,    0,    4,
    4,    2,    0,    5,    6,    7,    7,    8,   69,    0,
    0,    3,    0,    0,    4,    2,    0,    0,    5,    6,
    7,    0,    8,    0,    0,    3,    3,    0,    4,    4,
    0,    2,    5,    6,    7,    7,    8,   69,    0,    0,
    0,    3,    0,    2,    4,    0,    0,    0,    5,    6,
    7,    0,    8,    3,    0,    0,    4,    0,    0,    0,
    5,    6,    7,    0,    8,    3,  121,  122,    4,    0,
    0,    0,    0,    0,    7,    0,   69,    0,    0,    0,
    0,    0,    3,    0,  155,    4,    0,    0,    0,    0,
    0,    7,    0,   69,    3,    0,    0,    4,    0,    0,
    0,    0,    0,    7,    0,   69,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   41,   40,   40,   40,   45,   40,   45,   61,   45,   41,
   45,   43,   41,   45,   41,   44,   41,   61,   61,   61,
   11,   91,   44,   25,   44,   20,   59,   59,   60,   61,
   62,   41,   48,   43,    9,   45,   59,   59,   43,   42,
   45,   90,   40,   38,   47,   45,   48,   49,   40,   59,
   60,   61,   62,   41,   29,   43,   70,   45,   41,   41,
  112,   36,   44,   61,   39,   40,  115,   41,  138,  123,
   41,   59,   60,   61,   62,  261,  262,  278,   61,    7,
  123,   41,  134,  135,   44,  137,  100,   32,   63,   91,
   61,  123,   41,   68,   43,   41,   45,   43,  150,   45,
  123,   29,   59,   43,   40,   45,  274,  275,   36,   54,
  278,  266,  259,  123,   60,   61,   62,  268,  269,  277,
   60,   61,   62,   41,   54,   70,   61,  274,  275,  268,
  269,  278,   59,   78,  278,  123,  138,  278,   61,   44,
  123,  132,  268,  269,   41,  161,   66,   67,   78,   94,
   61,   61,  123,   74,   75,  100,  101,  148,  149,  161,
  151,   59,  107,   41,   94,   61,   41,  278,  159,   59,
  277,  101,  261,  123,  119,  123,  121,  107,  269,   61,
   61,   44,   59,  261,  129,  123,  269,  269,   44,  119,
  269,  121,    0,   41,   59,   61,  141,   59,   59,  129,
   59,   59,  125,   41,  258,   41,   59,   61,   59,   59,
   59,  141,   59,  123,  125,  258,  258,  261,   -1,   61,
  261,  262,  261,  262,  261,  262,  261,  262,  260,  125,
   -1,  263,  264,  265,   -1,  267,   61,  278,  270,  278,
  278,  278,   -1,  278,  276,  277,  278,   -1,   61,  278,
  260,  278,  277,  263,  264,  265,  278,  267,  278,  125,
  270,  261,  262,   -1,   -1,   -1,  276,  277,  278,   -1,
   -1,  125,  260,   -1,   -1,  263,  264,  265,  278,  267,
   -1,  123,  270,   -1,  267,  259,   -1,  270,  276,  277,
  278,   -1,   -1,  276,  277,  278,  267,   -1,  123,  270,
  274,  275,   -1,   -1,  278,  276,  277,  278,   -1,   -1,
   -1,   -1,  125,   -1,  260,   -1,   -1,  263,  264,  265,
  260,  256,  257,  263,  264,  265,   -1,   -1,   -1,   -1,
   -1,   -1,  267,   -1,  257,  270,   -1,   -1,   -1,  274,
  275,  276,   -1,  278,  267,   -1,  257,  270,   -1,   -1,
   -1,  274,  275,  276,   -1,  278,  267,  267,   -1,  270,
  270,  257,   -1,  274,  275,  276,  276,  278,  278,   -1,
   -1,  267,   -1,   -1,  270,  257,   -1,   -1,  274,  275,
  276,   -1,  278,   -1,   -1,  267,  267,   -1,  270,  270,
   -1,  257,  274,  275,  276,  276,  278,  278,   -1,   -1,
   -1,  267,   -1,  257,  270,   -1,   -1,   -1,  274,  275,
  276,   -1,  278,  267,   -1,   -1,  270,   -1,   -1,   -1,
  274,  275,  276,   -1,  278,  267,  268,  269,  270,   -1,
   -1,   -1,   -1,   -1,  276,   -1,  278,   -1,   -1,   -1,
   -1,   -1,  267,   -1,  269,  270,   -1,   -1,   -1,   -1,
   -1,  276,   -1,  278,  267,   -1,   -1,  270,   -1,   -1,
   -1,   -1,   -1,  276,   -1,  278,
};
}
final static short YYFINAL=10;
final static short YYMAXTOKEN=280;
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
null,null,null,null,null,null,null,"PROC","NI","VAR","MENORIGUAL","CONST_INT",
"CONST_FLOAT","MAYORIGUAL","IGUALIGUAL","DISTINTO","CADENA","IF","ELSE",
"END_IF","OUT","THEN","FUNC","RETURN","INTEGER","FLOAT","WHILE","LOOP","ID",
"TRUE","FALSE",
};
final static String yyrule[] = {
"$accept : programa",
"programa : body",
"body : body sentencia ';'",
"body : sentencia ';'",
"body : error ';'",
"sentencia : ejecutable",
"sentencia : declarativa",
"ejecutable : asignacion",
"ejecutable : salida",
"ejecutable : control",
"ejecutable : seleccion",
"ejecutable : invocacion",
"declarativa : tipo listavariables",
"declarativa : procedure",
"declarativa : listavariables",
"declarativa : tipo",
"procedure : PROC ID '(' dlistaparametros ')' ni '{' body '}'",
"procedure : PROC ID '(' ')' ni '{' body '}'",
"procedure : PROC ID '(' dlistaparametros ')' ni",
"procedure : PROC ID '(' ')' ni",
"procedure : PROC '(' dlistaparametros ')' ni '{' body '}'",
"procedure : PROC ID '(' dlistaparametros ')' '{' body '}'",
"procedure : PROC ID '(' ')' '{' body '}'",
"ni : NI '=' CONST_INT",
"ni : NI CONST_INT",
"ni : '=' CONST_INT",
"ni : NI '='",
"dlistaparametros : dparametro",
"dlistaparametros : dparametro ',' dparametro",
"dlistaparametros : dparametro ',' dparametro ',' dparametro",
"dlistaparametros : dparametro ',' dparametro ',' dparametro ',' dlistaparametros",
"dparametro : tipo ID",
"dparametro : VAR tipo ID",
"dparametro : tipo",
"dparametro : ID",
"dparametro : VAR ID",
"dparametro : VAR tipo",
"listavariables : ID",
"listavariables : listavariables ',' ID",
"listavariables : listavariables ID",
"asignacion : ID '=' expresion",
"asignacion : ID '='",
"asignacion : '=' expresion",
"salida : OUT '(' CADENA ')'",
"control : WHILE '(' condicion ')' LOOP bodycontrol",
"control : WHILE '(' condicion LOOP bodycontrol",
"control : WHILE condicion LOOP bodycontrol",
"seleccion : IF '(' condicion ')' bodycontrol ELSE bodycontrol END_IF",
"seleccion : IF '(' condicion ')' bodycontrol END_IF",
"seleccion : IF '(' ')' bodycontrol ELSE bodycontrol END_IF",
"seleccion : IF '(' condicion ')' bodycontrol ELSE bodycontrol",
"seleccion : IF '(' condicion ')' ELSE bodycontrol END_IF",
"seleccion : IF '(' condicion ')' bodycontrol ELSE END_IF",
"seleccion : IF condicion bodycontrol ELSE bodycontrol END_IF",
"seleccion : IF '(' ')' bodycontrol END_IF",
"seleccion : IF '(' condicion ')' bodycontrol",
"seleccion : IF '(' condicion ')' END_IF",
"seleccion : IF condicion bodycontrol END_IF",
"invocacion : ID '(' listavariables ')'",
"invocacion : ID '(' ')'",
"bodycontrol : sentenciacontrol ';'",
"bodycontrol : '{' listasentenciacontrol '}'",
"sentenciacontrol : ejecutable",
"listasentenciacontrol : listasentenciacontrol sentenciacontrol ';'",
"listasentenciacontrol : sentenciacontrol ';'",
"condicion : expresion comparador expresion",
"condicion : expresion '=' expresion",
"comparador : IGUALIGUAL",
"comparador : MENORIGUAL",
"comparador : MAYORIGUAL",
"comparador : DISTINTO",
"comparador : '<'",
"comparador : '>'",
"tipo : INTEGER",
"tipo : FLOAT",
"expresion : expresion '+' termino",
"expresion : expresion '-' termino",
"expresion : termino",
"expresion : '(' expresion ')'",
"termino : termino '*' factor",
"termino : termino '/' factor",
"termino : factor",
"factor : ID",
"factor : CONST_INT",
"factor : '-' CONST_INT",
"factor : CONST_FLOAT",
"factor : '-' CONST_FLOAT",
};

//#line 167 "gramatica.y"
private AnalizadorLexico al;
private int errores = 0; 
public Parser(String path) {
	try {
		al = new AnalizadorLexico(path);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
private String valor;

private  int yylex(){
	Token aux = al.yylex(valor);
	while (aux.getNro() == -1) {
		System.out.println("Error, se leyo un token erroneo en la linea "+al.getNroLinea());
		errores++;
		aux = al.yylex(valor);
	}
		
	yylval = new ParserVal(aux.getLexema());
	if(aux.getNro() != 0)
		System.out.println("Se leyo el token "+aux.toString());
	return aux.getNro();
}
private void yyerror(String s) {
	System.out.println("upss");
}
private void yyerror() {
	//do nothing;
}
//#line 460 "Parser.java"
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
//#line 15 "gramatica.y"
{System.out.println("Se analizo el programa correctamente"); 
					if (errores>0) 
						System.out.println("Cantidad de errores encontrados "+errores);
					al.imprimirTS();
					}
break;
case 4:
//#line 24 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: no se detecto una sentencia valida."); errores++;}
break;
case 7:
//#line 31 "gramatica.y"
{System.out.println("Se detecto una asignacion. Linea " + al.getNroLinea());}
break;
case 8:
//#line 32 "gramatica.y"
{System.out.println("Se detecto una salida. Linea " + al.getNroLinea());}
break;
case 9:
//#line 33 "gramatica.y"
{System.out.println("Se detecto una sentencia 'WHIlE'. Linea " + al.getNroLinea());}
break;
case 10:
//#line 34 "gramatica.y"
{System.out.println("Se detecto una sentencia 'IF'. Linea " + al.getNroLinea());}
break;
case 11:
//#line 35 "gramatica.y"
{System.out.println("Se detecto una invocacion. Linea " + al.getNroLinea());}
break;
case 12:
//#line 38 "gramatica.y"
{System.out.println("Se detecto declaracion de variables. Linea " + al.getNroLinea());}
break;
case 13:
//#line 39 "gramatica.y"
{System.out.println("Se detecto desclaracion de un procedure. Linea " + al.getNroLinea());}
break;
case 14:
//#line 40 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: no hay tipo para el identificador."); errores++;}
break;
case 15:
//#line 41 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: se esperaba un identificador y no se encontro."); errores++;}
break;
case 18:
//#line 46 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: no se definio el cuerpo del procedimiento."); errores++;}
break;
case 19:
//#line 47 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: no se definio el cuerpo del procedimiento."); errores++;}
break;
case 20:
//#line 48 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: ausencia del identificador del procedimiento."); errores++;}
break;
case 21:
//#line 49 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: ausencia de la palabra reservada 'NI'."); errores++;}
break;
case 22:
//#line 50 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: ausencia de la palabra reservada 'NI'."); errores++;}
break;
case 24:
//#line 54 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: ausencia de '=' despues del 'NI'."); errores++;}
break;
case 25:
//#line 55 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: ausencia de la palabtra 'NI'."); errores++;}
break;
case 26:
//#line 56 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: no se definio el valor para 'NI'."); errores++;}
break;
case 30:
//#line 62 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: exceso de parametros, maximo 3 parametros."); errores++;}
break;
case 33:
//#line 67 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: ausencia del identificador en la declaracion de parametros."); errores++;}
break;
case 34:
//#line 68 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: ausencia del tipo en la declaracion de parametros."); errores++;}
break;
case 35:
//#line 69 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: ausencia del tipo en la declaracion de parametros.."); errores++;}
break;
case 36:
//#line 70 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: ausencia del identificador en la declaracion de parametros."); errores++;}
break;
case 37:
//#line 73 "gramatica.y"
{System.out.println(val_peek(0).sval);}
break;
case 39:
//#line 75 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: se esperaba una ',' y se recibio 'ID'."); errores++;}
break;
case 41:
//#line 79 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: error en la asignacion, se espera una expresion."); errores++;}
break;
case 42:
//#line 80 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: error en la asignacion, se un identificador."); errores++;}
break;
case 45:
//#line 87 "gramatica.y"
{System.out.println("Warning Falta parentesis de cierre"); errores++;}
break;
case 46:
//#line 88 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: es necesario encerrar la condicion entre '('')'."); errores++;}
break;
case 49:
//#line 93 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: se espera una condicion adentro de los parentesis."); errores++;}
break;
case 50:
//#line 94 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: al final del 'IF' se espera un 'END_IF'"); errores++;}
break;
case 51:
//#line 95 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: no hay bloque de sentencias dentro del 'IF'"); errores++;}
break;
case 52:
//#line 96 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: no hay bloque de sentencias dentro del 'ELSE'"); errores++;}
break;
case 53:
//#line 97 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: es necesario encerrar la condicion entre '('')'."); errores++;}
break;
case 54:
//#line 98 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: se espera una condicion adentro de los parentesis."); errores++;}
break;
case 55:
//#line 99 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: al final del 'IF' se espera un 'END_IF'"); errores++;}
break;
case 56:
//#line 100 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: no hay bloque de sentencias dentro del 'IF'"); errores++;}
break;
case 57:
//#line 101 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: es necesario encerrar la condicion entre '('')'."); errores++;}
break;
case 66:
//#line 120 "gramatica.y"
{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: error en el comparador."); errores++;}
break;
case 83:
//#line 147 "gramatica.y"
{  if (Integer.parseInt(val_peek(0).sval) == 32768)
							{
								System.out.println("Warning: constante positiva fuera de rango");
								val_peek(0).sval = "32767";
								if (!al.isKey(val_peek(0).sval)) {
									al.addSimbolo(val_peek(0).sval, 1); /* 1 tipo Integer*/
								}
							}
						}
break;
case 84:
//#line 156 "gramatica.y"
{
								if (!al.isKey("-"+val_peek(0).sval))
									al.addSimbolo("-"+val_peek(0).sval,1); /* 1 tipo Integer*/
							}
break;
case 85:
//#line 160 "gramatica.y"
{}
break;
case 86:
//#line 161 "gramatica.y"
{ if (!al.isKey("-"+val_peek(0).sval))
									al.addSimbolo("-"+val_peek(0).sval,2); /* 2 tipo Float*/
								}
break;
//#line 802 "Parser.java"
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
