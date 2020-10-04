package AnalizadorLexico;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


import AccionesSemanticas.MatrizAcciones;

public class AnalizadorLexico {
	
	public int pos = 0;
	public int nroLinea = 1; //se comienza a leer el archivo desde la primer linea
	public String lexema;
	
	public PalabrasReservadas PR= new PalabrasReservadas();
	private LectorBuffer buffer;
	private MatrizAcciones matrizAcciones;
	private static final int FINAL = -1 ;
	private static final int ERROR = -2 ;
	private static final int[][] matrizTransicionEstados = {
			
			/*        letra   digito    f       /      *       +       -      = 	   <	   >	    {	    }	    (	    )	    ,	   ;	   "	   .	    %	    _    Blanco-tab	 i	     !	   otro	    nl	     	   
			/* 0*/	{    1,      2,      1,      6,  FINAL,  FINAL,  FINAL,    9  ,    10 ,    11 ,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,   12  ,     15,  ERROR,  ERROR,      0,      1,     14,  FINAL,   FINAL},
			/* 1*/	{    1,      1,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,    1  ,  FINAL,  FINAL,  FINAL,  FINAL,   FINAL},
			/* 2*/	{FINAL,      2,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,      3,  FINAL,     13,  FINAL,  FINAL,  FINAL,  FINAL,   FINAL},
			/* 3*/	{FINAL,      3,      4,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,	  FINAL},
			/* 4*/	{ERROR,  ERROR,  ERROR,  ERROR,  ERROR,      5,     5 ,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,   ERROR},
			/* 5*/	{ERROR,     16,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,   ERROR},
			/* 6*/	{FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,      7,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,   FINAL},
			/* 7*/	{    7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      8,      7,      7,      7,      7,      7,    7   },
			/* 8*/	{    7,      7,      7,      0,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      8,      7,      7,      7,      7,      7,    7   },
			/* 9*/	{FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,	  FINAL},
			/*10*/	{FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,	  FINAL},
			/*11*/	{FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,	  FINAL},
			/*12*/	{   12,     12,     12,     12,     12,     12,     17,     12,     12,     12,     12,     12,     12,     12,     12,     12,  FINAL,     12,     12,     12,     12,     12,     12,     12,     12 },
			/*13*/	{ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  FINAL,  ERROR,  ERROR,   ERROR},
			/*14*/	{ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  FINAL,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,   ERROR},
			/*15*/	{ERROR,      3,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  FINAL,  ERROR,  ERROR,   ERROR},
			/*16*/	{FINAL,     16,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,	  FINAL},
			/*17*/	{ 12,     12,     12,     12,     12,     12,     17,     12,     12,     12,     12,     12,     12,     12,     12,     12,  FINAL,     12,     12,     12,     12,     12,     12,     12,     12   }
			
			
			}; 
	
	public AnalizadorLexico(String path) throws IOException {

		buffer = new LectorBuffer(new InputStreamReader(new FileInputStream(path)));
		matrizAcciones = new MatrizAcciones();
		
		
	}
	
	
	/*------Funciones de la matriz de transicion de estados------*/
	
	/* Nos devuelve el siguiente estado */
	public int getSiguienteEstado( int i, int j) {
		return this.matrizTransicionEstados[i][j]; 
	}
	
	/* Muestra la matriz de transicion de estados */
	public void mostrarMatriz() {
		for(int i=0; i<this.matrizTransicionEstados.length; i++) {
			System.out.print("|");
			for(int j=0; j<this.matrizTransicionEstados[i].length;j++) {
				System.out.print(this.matrizTransicionEstados[i][j]);
				if (j!=this.matrizTransicionEstados[i].length-1) 
					System.out.print("\t");
			}
			System.out.println("|");
		}
	}
	
	/*------Fin de las funciones de la matriz de transicion de estados------*/
	private int paridad(char c) {
		if (c == 'f')
			return 2;
		if (c == '/')
			return 3;
		if (c == 'i')
			return 21;
		if (((int)c > 64 && (int)c < 91) || ( (int)c > 96 && (int)c < 123 )) //comparo por numero ASCII
			return 0;
		if ((int)c > 47 && (int)c <58 )
			return 1;
		if (c == '*')
			return 4;
		if (c == '+')
			return 5;
		if (c == '-')
			return 6;
		if (c == '=')
			return 7;
		if (c == '<')
			return 8;
		if (c == '>')
			return 9;
		if (c == '{')
			return 10;
		if (c == '}')
			return 11;
		if (c == '(')
			return 12;
		if (c == ')')
			return 13;
		if (c == ',')
			return 14;
		if (c == ';')
			return 15;
		if (c == '"')
			return 16;
		if (c == '.')
			return 17;
		if (c == '%')
			return 18;
		if (c == '_')
			return 19;
		if ((int)c == 32 || (int)c == 9)
			return 20;
		if (c == '!')
			return 22;
		if ((int)c == 10)
			return 24;
		return 23;
			
		
	}
	
	public int getNroLinea() {
		return nroLinea;
	}


	public void setNroLinea(int nroLinea) {
		this.nroLinea = nroLinea;
	}


	public String getLexema() {
		return lexema;
	}


	public void setLexema(String lexema) {
		this.lexema = lexema;
	}


	public PalabrasReservadas getPR() {
		return PR;
	}


	public void setPR(PalabrasReservadas pR) {
		PR = pR;
	}



}
