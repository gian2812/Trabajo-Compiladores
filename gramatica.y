%{
package AnalizadorLexico;
import java.lang.Math;
import java.io.*;
import java.util.Hashtable;
%}

%token PROC NI VAR MENORIGUAL CONST_INT CONST_FLOAT MAYORIGUAL IGUALIGUAL DISTINTO CADENA IF ELSE END_IF OUT THEN FUNC RETURN INTEGER FLOAT WHILE LOOP ID TRUE FALSE
%start programa

%%

programa 	: body
			;
			
body		:  body sentencia ';' 
			|  sentencia ';'
			
sentencia	| ejecutable
			| declarativa
			;
			
ejecutable	: asignacion
			| salida
			| control
			| seleccion
			| invocacion
			;		
			
declarativa : tipo listavariables  
			| procedure
			;	
			
procedure	: PROC ID '(' dlistaparametros ')' ni '{' body '}' 
			| PROC ID '(' ')' ni '{' body '}'
			;
			
ni          : NI '=' CONST_INT
			;			
			
dlistaparametros : dparametro			
				 | dlistaparametros ',' dparametro
				 ;

dparametro  : tipo ID
            | VAR tipo ID
			;
			
listavariables : ID
			   | listavariables ',' ID
			   ;
			   
asignacion	: ID ASSIGN expresion 
			;

salida		: OUT '(' CADENA ')' 
			;
			
control 	: WHILE '(' condicion ')' LOOP bodycontrol 
			;
			
seleccion 	: IF '(' condicion ')'  bodycontrol  ELSE  bodycontrol  END_IF 
			| IF '(' condicion ')'  bodycontrol  END_IF 
			;
			
invocacion 	: ID '(' listavariables ')' 
			| ID '(' ')' 
			;		

boolean 	: TRUE
			| FALSE
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
 			| CTE_INT { /* if (Integer.parseInt($1.sval) == 32768){
 							System.out.println("Constante positiva fuera de rango");
							$1.sval = "32767";
							ts.addSimbolo("32767",0);
								}
						*/	}		
			| '-' CTE_INT  {/*	if (!tSimbolos.containsKey("-"+$2.sval))
								tSimbolos.put("-"+$2.sval, 1);
							else 
								tSimbolos.replace("-"+$2.sval, (tSimbolos.get("-"+$2.sval)+1));
							tSimbolos.replace($2.sval, (tSimbolos.get($2.sval)-1));
							if (tSimbolos.get($2.sval) == 0)
									tSimbolos.remove($2.sval);
								*/}
			| CTE_FLOAT {}
			| '-' CTE_FLOAT {/*if (!tSimbolos.containsKey("-"+$2.sval))
								tSimbolos.put("-"+$2.sval, 1);
							else 
								tSimbolos.replace("-"+$2.sval, (tSimbolos.get("-"+$2.sval)+1));
							tSimbolos.replace($2.sval, (tSimbolos.get($2.sval)-1));
							if (tSimbolos.get($2.sval) == 0)
									tSimbolos.remove($2.sval);*/}
;

%%
private AnalizadorLexico al = new AnalizadorLexico("C:\\Users\\Gian\\Desktop\\Compiladores\\prueba2.txt");
private  int yylex(){
	al.getToken();
}
