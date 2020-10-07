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
package AnalizadorSintatico;
import java.lang.Math;
import java.io.*;
import java.util.Hashtable;

import AnalizadorLexico.AnalizadorLexico;
//#line 22 "Parser.java"
import AnalizadorLexico.Token;




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
    0,    1,    1,    2,    2,    3,    3,    3,    3,    3,
    4,    4,   12,   12,   14,   13,   13,   15,   15,   11,
   11,    5,    6,    7,    8,    8,    9,    9,   18,   18,
   19,   20,   20,   17,   21,   21,   21,   21,   21,   21,
   10,   10,   16,   16,   16,   16,   22,   22,   22,   23,
   23,   23,   23,   23,
};
final static short yylen[] = {                            2,
    1,    3,    2,    1,    1,    1,    1,    1,    1,    1,
    2,    1,    9,    8,    3,    1,    3,    2,    3,    1,
    3,    3,    4,    6,    8,    6,    4,    3,    2,    3,
    1,    3,    2,    3,    1,    1,    1,    1,    1,    1,
    1,    1,    3,    3,    1,    3,    3,    3,    1,    1,
    1,    2,    1,    2,
};
final static short yydefred[] = {                         0,
    0,    0,    0,   41,   42,    0,    0,    0,    0,    0,
    4,    5,    6,    7,    8,    9,   10,    0,   12,    0,
    0,    0,    0,    0,    0,    0,    3,   20,    0,    0,
   51,   53,   50,    0,    0,    0,    0,    0,   49,    0,
    0,   28,    0,    0,    2,    0,    0,    0,    0,    0,
   16,    0,   52,   54,   36,   37,   35,   38,   39,   40,
    0,    0,    0,    0,    0,    0,   23,    0,   27,   21,
    0,    0,    0,   18,    0,    0,   46,    0,    0,    0,
    0,   31,    0,    0,   47,   48,    0,   19,    0,    0,
    0,   17,    0,    0,    0,   26,   29,   24,   15,    0,
    0,   33,   30,    0,    0,   14,    0,   32,   25,   13,
};
final static short yydgoto[] = {                          8,
    9,   10,   11,   12,   13,   14,   15,   16,   17,   18,
   29,   19,   50,   73,   51,   36,   37,   83,   84,   94,
   63,   38,   39,
};
final static short yysindex[] = {                      -219,
 -257,   -1,    9,    0,    0,   13,  -20,    0, -219,   22,
    0,    0,    0,    0,    0,    0,    0, -187,    0,   52,
  -40, -173,  -40,  -39,  -40,   36,    0,    0,   50,  -34,
    0,    0,    0,  -40, -184,  -10,   55,   23,    0,   56,
   57,    0,  -25,   37,    0, -179, -191, -158, -177,   35,
    0,   28,    0,    0,    0,    0,    0,    0,    0,    0,
  -44,  -44,  -40, -120,  -44,  -44,    0, -174,    0,    0,
 -172,   43,  -15,    0, -158, -213,    0,   23,   23,   37,
 -204,    0, -183,   46,    0,    0, -120,    0, -154, -219,
  -13,    0,   53,  -85, -120,    0,    0,    0,    0, -115,
 -219,    0,    0,   54, -155,    0,  -95,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,  111,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   58,    0,
    0,    0,    0,    0,    0,    0,    0,  -37,    0,    0,
    0,    0,    0,   59,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -28,  -17,   74,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  -26,    2,  -27,    0,    0,    0,    0,    0,    0,  -18,
   92,    0,    0,   44,   45,  -16,   97,  -51,  -67,    0,
    0,   26,   24,
};
final static int YYTABLESIZE=255;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         34,
   35,   42,   81,   45,   35,   45,   48,   45,   44,  106,
   26,   49,   43,   93,   43,   69,   43,   52,   46,   24,
   20,   45,   45,   44,   45,   44,  104,   44,   71,  110,
   43,   43,   61,   43,   62,   98,   82,    1,   21,  103,
   25,   44,   44,  105,   44,   47,   80,    2,   22,   59,
    3,   60,   23,   82,    4,    5,    6,   49,    7,   82,
    4,    5,    2,  100,   65,    3,   82,   82,   77,   66,
   61,    6,   62,    7,  107,   75,   53,   54,   76,   61,
   27,   62,    4,    5,   95,   96,   78,   79,   85,   86,
   28,   30,   40,   46,   45,   64,   67,   68,   70,   72,
   74,   26,   87,   89,   97,   88,   99,   90,   26,  101,
    1,  102,  108,  109,   34,   43,   11,   22,   91,   41,
   92,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    1,    0,    0,    0,    0,    2,    0,    0,    3,
    0,    2,    0,    0,    3,    6,    0,    7,    4,    5,
    6,    1,    7,    0,    0,    0,    0,    0,    0,    0,
    0,    2,    0,    0,    3,    0,    0,    0,    4,    5,
    6,    2,    7,    0,    3,    0,    0,    0,    0,    0,
    6,    0,    7,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   31,   32,    0,    0,
   31,   32,   45,    0,   47,   45,   45,   45,    0,    0,
    0,   43,    0,   33,   43,   43,   43,   33,   28,    4,
    5,    0,   44,    0,    0,   44,   44,   44,    0,   55,
    0,    0,   56,   57,   58,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   45,   41,  123,   41,   45,   43,   41,   45,   25,  125,
    9,   30,   41,   81,   43,   41,   45,   34,   44,   40,
  278,   59,   60,   41,   62,   43,   94,   45,   47,  125,
   59,   60,   43,   62,   45,   87,   64,  257,   40,  125,
   61,   59,   60,   95,   62,  259,   63,  267,   40,   60,
  270,   62,   40,   81,  274,  275,  276,   76,  278,   87,
  274,  275,  267,   90,   42,  270,   94,   95,   41,   47,
   43,  276,   45,  278,  101,   41,  261,  262,   44,   43,
   59,   45,  274,  275,  268,  269,   61,   62,   65,   66,
  278,   40,  266,   44,   59,   41,   41,   41,  278,  258,
  278,  100,  277,   61,   59,  278,  261,  123,  107,  123,
    0,   59,   59,  269,   41,   24,   59,   59,   75,   23,
   76,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,   -1,   -1,   -1,   -1,  267,   -1,   -1,  270,
   -1,  267,   -1,   -1,  270,  276,   -1,  278,  274,  275,
  276,  257,  278,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  267,   -1,   -1,  270,   -1,   -1,   -1,  274,  275,
  276,  267,  278,   -1,  270,   -1,   -1,   -1,   -1,   -1,
  276,   -1,  278,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  261,  262,   -1,   -1,
  261,  262,  260,   -1,  259,  263,  264,  265,   -1,   -1,
   -1,  260,   -1,  278,  263,  264,  265,  278,  278,  274,
  275,   -1,  260,   -1,   -1,  263,  264,  265,   -1,  260,
   -1,   -1,  263,  264,  265,
};
}
final static short YYFINAL=8;
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
"sentencia : ejecutable",
"sentencia : declarativa",
"ejecutable : asignacion",
"ejecutable : salida",
"ejecutable : control",
"ejecutable : seleccion",
"ejecutable : invocacion",
"declarativa : tipo listavariables",
"declarativa : procedure",
"procedure : PROC ID '(' dlistaparametros ')' ni '{' body '}'",
"procedure : PROC ID '(' ')' ni '{' body '}'",
"ni : NI '=' CONST_INT",
"dlistaparametros : dparametro",
"dlistaparametros : dlistaparametros ',' dparametro",
"dparametro : tipo ID",
"dparametro : VAR tipo ID",
"listavariables : ID",
"listavariables : listavariables ',' ID",
"asignacion : ID '=' expresion",
"salida : OUT '(' CADENA ')'",
"control : WHILE '(' condicion ')' LOOP bodycontrol",
"seleccion : IF '(' condicion ')' bodycontrol ELSE bodycontrol END_IF",
"seleccion : IF '(' condicion ')' bodycontrol END_IF",
"invocacion : ID '(' listavariables ')'",
"invocacion : ID '(' ')'",
"bodycontrol : sentenciacontrol ';'",
"bodycontrol : '{' listasentenciacontrol '}'",
"sentenciacontrol : ejecutable",
"listasentenciacontrol : listasentenciacontrol sentenciacontrol ';'",
"listasentenciacontrol : sentenciacontrol ';'",
"condicion : expresion comparador expresion",
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

//#line 137 "gramatica.y"
private AnalizadorLexico al; 
public Parser(String path) {
	try {
		al = new AnalizadorLexico(path);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	al.imprimirTS();
}
private String valor;

private  int yylex() throws IOException{
	Token aux = al.yylex(valor);
	while (aux.getNro() == -1)
		aux = al.yylex(valor);
	yylval = new ParserVal(aux.getLexema());
	return aux.getNro();
}
public Object getLVal() {
	return valor;
}
private void yyerror(String s) {
	System.out.println("upss");
}
private void yyerror() {
	//do nothing;
}
//#line 333 "Parser.java"
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
        try {
			yychar = yylex();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //get next token
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
//#line 13 "gramatica.y"
{ System.out.println("se analizo el programa correctamente");}
break;
case 20:
//#line 49 "gramatica.y"
{System.out.println(val_peek(0));}
break;
case 51:
//#line 112 "gramatica.y"
{ /* if (Integer.parseInt($1.sval) == 32768){
 							System.out.println("Constante positiva fuera de rango");
							$1.sval = "32767";
							ts.addSimbolo("32767",0);
								}
						*/	}
break;
case 52:
//#line 118 "gramatica.y"
{/*	if (!tSimbolos.containsKey("-"+$2.sval))
								tSimbolos.put("-"+$2.sval, 1);
							else 
								tSimbolos.replace("-"+$2.sval, (tSimbolos.get("-"+$2.sval)+1));
							tSimbolos.replace($2.sval, (tSimbolos.get($2.sval)-1));
							if (tSimbolos.get($2.sval) == 0)
									tSimbolos.remove($2.sval);
								*/}
break;
case 53:
//#line 126 "gramatica.y"
{}
break;
case 54:
//#line 127 "gramatica.y"
{/*if (!tSimbolos.containsKey("-"+$2.sval))
								tSimbolos.put("-"+$2.sval, 1);
							else 
								tSimbolos.replace("-"+$2.sval, (tSimbolos.get("-"+$2.sval)+1));
							tSimbolos.replace($2.sval, (tSimbolos.get($2.sval)-1));
							if (tSimbolos.get($2.sval) == 0)
									tSimbolos.remove($2.sval);*/}
break;
//#line 524 "Parser.java"
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
        try {
			yychar = yylex();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        //get next character
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
