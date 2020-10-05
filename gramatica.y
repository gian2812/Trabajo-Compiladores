%{
package AnalizadorLexico;
import java.lang.Math;
import java.io.*;
import java.util.Hashtable;
%}

%token MENORIGUAL CONST_INT CONST_FLOAT SUCESION MAYORIGUAL IGUALIGUAL DISTINTO CADENA IF ELSE END_IF OUT THEN FUNC RETURN INTEGER FLOAT WHILE LOOP ID TRUE FALSE
%start programa

%%

programa 	: body
			;

procedure	: PROC ID '(' parametros ')' '{' body '}'
			| PROC ID '(' ')' '{' body '}'
			;

boolean 	: TRUE
			| FALSE
			;			

body		: '{' body '}'
			| ejecutable
			| body ejecutable
			| procedure
			| body procedure
			;

ejecutable	: asignacion
			| salida
			| control
			| seleccion
			| invocacion
			;			

control 	: WHILE '(' condicion ')' LOOP body ';'
			;

invocacion 	: ID '(' listaparametros ')' ';'
			| ID '(' ')' ';'
			;


salida		: OUT '(' sucesion ')' ';'
			;

seleccion 	: IF '(' condicion ')' body ELSE body END_IF ';'
			| IF '(' condicion ')' body END_IF ';'
			;


condicion 	:  expresion comparador expresion
			| '(' expresion comparador expresion ')'
			;

comparador	: IGUALGIAL
			| MENORIGUAL
			| MAYORIGUAL
			| '<'
			| '>'
			;

parametros 	: parametros ',' tipo ID 
			| tipo ID  
			;	

listaparametros	: parametros ',' ID
				| ID
				;	

tipo		: INTEGER
			| FLOAT
			;			
			
asignacion	: ID ASSIGN expresion ';'
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
 			| CTE_INT 
			| '-' CTE_INT {	if (!tSimbolos.containsKey("-"+$2.sval))
								tSimbolos.put("-"+$2.sval, 1);
							else 
								tSimbolos.replace("-"+$2.sval, (tSimbolos.get("-"+$2.sval)+1));
							tSimbolos.replace($2.sval, (tSimbolos.get($2.sval)-1));
							if (tSimbolos.get($2.sval) == 0)
									tSimbolos.remove($2.sval);}
			| CTE_FLOAT {}
			| '-' CTE_FLOAT {if (!tSimbolos.containsKey("-"+$2.sval))
								tSimbolos.put("-"+$2.sval, 1);
							else 
								tSimbolos.replace("-"+$2.sval, (tSimbolos.get("-"+$2.sval)+1));
							tSimbolos.replace($2.sval, (tSimbolos.get($2.sval)-1));
							if (tSimbolos.get($2.sval) == 0)
									tSimbolos.remove($2.sval);}
;

%%