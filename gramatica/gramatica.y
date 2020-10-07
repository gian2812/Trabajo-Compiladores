%{
package AnalizadorSintactico;
import java.lang.Math;
import java.io.*;
import java.util.Hashtable;
import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.Token;
%}

%token PROC NI VAR MENORIGUAL CONST_INT CONST_FLOAT MAYORIGUAL IGUALIGUAL DISTINTO CADENA IF ELSE END_IF OUT THEN FUNC RETURN INTEGER FLOAT WHILE LOOP ID TRUE FALSE
%start programa

%%

programa 	: body {System.out.println("Se analizo el programa correctamente"); 
					if (errores>0) 
						System.out.println("Cantidad de errores encontrados "+errores);
					al.imprimirTS();
					}
			;
			
body		: body sentencia ';' 
			| sentencia ';'
			| error ';' {System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: no se detecto una sentencia valida."); errores++;}
			;
			
sentencia	: ejecutable
			| declarativa
			;
			
ejecutable	: asignacion	{System.out.println("Se detecto una asignacion. Linea " + al.getNroLinea());}
			| salida	{System.out.println("Se detecto una salida. Linea " + al.getNroLinea());}
			| control	{System.out.println("Se detecto una sentencia 'WHIlE'. Linea " + al.getNroLinea());}
			| seleccion	{System.out.println("Se detecto una sentencia 'IF'. Linea " + al.getNroLinea());}
			| invocacion	{System.out.println("Se detecto una invocacion. Linea " + al.getNroLinea());}
			;		
			
declarativa : tipo listavariables  {System.out.println("Se detecto declaracion de variables. Linea " + al.getNroLinea());}
			| procedure	{System.out.println("Se detecto desclaracion de un procedure. Linea " + al.getNroLinea());}
			| listavariables	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: no hay tipo para el identificador."); errores++;}
			| tipo	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: se esperaba un identificador y no se encontro."); errores++;}
			;	
			
procedure	: PROC ID '(' dlistaparametros ')' ni '{' body '}' 
			| PROC ID '(' ')' ni '{' body '}'
			| PROC ID '(' dlistaparametros ')' ni {System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: no se definio el cuerpo del procedimiento."); errores++;}
			| PROC ID '(' ')' ni {System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: no se definio el cuerpo del procedimiento."); errores++;}
			| PROC '(' dlistaparametros ')' ni '{' body '}'	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: ausencia del identificador del procedimiento."); errores++;}
			| PROC ID '(' dlistaparametros ')' '{' body '}' {System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: ausencia de la palabra reservada 'NI'."); errores++;}
			| PROC ID '(' ')' '{' body '}' {System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: ausencia de la palabra reservada 'NI'."); errores++;}
			;
			
ni          : NI '=' CONST_INT 
			| NI CONST_INT	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: ausencia de '=' despues del 'NI'."); errores++;}
			| '=' CONST_INT	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: ausencia de la palabtra 'NI'."); errores++;}
			| NI '='	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: no se definio el valor para 'NI'."); errores++;}
			;		
			
dlistaparametros : dparametro
				 | dparametro ',' dparametro
				 | dparametro ',' dparametro ',' dparametro
				 | dparametro ',' dparametro ',' dparametro ',' dlistaparametros	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: exceso de parametros, maximo 3 parametros."); errores++;}
				 ;

dparametro  : tipo ID
            | VAR tipo ID
			| tipo	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: ausencia del identificador en la declaracion de parametros."); errores++;}
			| ID	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: ausencia del tipo en la declaracion de parametros."); errores++;}
			| VAR ID	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: ausencia del tipo en la declaracion de parametros.."); errores++;}
			| VAR tipo	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: ausencia del identificador en la declaracion de parametros."); errores++;}
			;
			
listavariables : ID {System.out.println($1.sval);}
			   | listavariables ',' ID
			   | listavariables ID	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: se esperaba una ',' y se recibio 'ID'."); errores++;}
			   ;
			   
asignacion	: ID '=' expresion 
			| ID '='	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: error en la asignacion, se espera una expresion."); errores++;}
			| '=' expresion	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: error en la asignacion, se un identificador."); errores++;}
			;

salida		: OUT '(' CADENA ')' 
			;
			
control 	: WHILE '(' condicion ')' LOOP bodycontrol 
			| WHILE '(' condicion LOOP bodycontrol {System.out.println("Warning Falta parentesis de cierre"); errores++;}
			| WHILE condicion LOOP bodycontrol	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: es necesario encerrar la condicion entre '('')'."); errores++;}
			;
			
seleccion 	: IF '(' condicion ')'  bodycontrol  ELSE  bodycontrol  END_IF 
			| IF '(' condicion ')'  bodycontrol  END_IF 
			| IF '(' ')'  bodycontrol  ELSE  bodycontrol  END_IF	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: se espera una condicion adentro de los parentesis."); errores++;}
			| IF '(' condicion ')'  bodycontrol  ELSE  bodycontrol	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: al final del 'IF' se espera un 'END_IF'"); errores++;}
			| IF '(' condicion ')' ELSE  bodycontrol  END_IF	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: no hay bloque de sentencias dentro del 'IF'"); errores++;}
			| IF '(' condicion ')'  bodycontrol  ELSE  END_IF	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: no hay bloque de sentencias dentro del 'ELSE'"); errores++;}
			| IF condicion bodycontrol  ELSE  bodycontrol  END_IF	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: es necesario encerrar la condicion entre '('')'."); errores++;}
			| IF '(' ')'  bodycontrol  END_IF	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: se espera una condicion adentro de los parentesis."); errores++;}
			| IF '(' condicion ')'  bodycontrol	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: al final del 'IF' se espera un 'END_IF'"); errores++;}
			| IF '(' condicion ')'  END_IF	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: no hay bloque de sentencias dentro del 'IF'"); errores++;}
			| IF condicion bodycontrol  END_IF	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: es necesario encerrar la condicion entre '('')'."); errores++;}
			;
			
invocacion 	: ID '(' listavariables ')' 
			| ID '(' ')'
			;				

bodycontrol : sentenciacontrol ';'
			| '{' listasentenciacontrol '}' 
			;

sentenciacontrol : ejecutable
				 ;

listasentenciacontrol : listasentenciacontrol sentenciacontrol ';'
					  | sentenciacontrol ';'
					  ;

condicion 	:  expresion comparador expresion
			| expresion '=' expresion	{System.out.println("Linea " + al.getNroLinea() + ", ERROR sintactico: error en el comparador."); errores++;}
			;

comparador	: IGUALIGUAL
			| MENORIGUAL
			| MAYORIGUAL
			| DISTINTO
			| '<'
			| '>'
			;

tipo		: INTEGER
			| FLOAT
			;			
			
expresion	: expresion '+' termino 
 			| expresion '-' termino 
 			| termino
 			| '(' expresion ')' 
			;
 
termino 	: termino '*' factor
 			| termino '/' factor 
 			| factor
			;		
			
factor		: ID
 			| CONST_INT {  if (Integer.parseInt($1.sval) == 32768)
							{
								System.out.println("Warning: constante positiva fuera de rango");
								$1.sval = "32767";
								if (!al.isKey($1.sval)) {
									al.addSimbolo($1.sval, 1); // 1 tipo Integer
								}
							}
						}						
			| '-' CONST_INT {
								if (!al.isKey("-"+$2.sval))
									al.addSimbolo("-"+$2.sval,1); // 1 tipo Integer
							}
			| CONST_FLOAT {}
			| '-' CONST_FLOAT { if (!al.isKey("-"+$2.sval))
									al.addSimbolo("-"+$2.sval,2); // 2 tipo Float
								}
;

%%
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
