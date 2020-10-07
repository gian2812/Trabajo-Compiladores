package AnalizadorLexico;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import AccionesSemanticas.MatrizAcciones;
import AnalizadorSintactico.ParserVal;

public class AnalizadorLexico {

	private int nroLinea = 1; // se comienza a leer el archivo desde la primer linea
	private String lexema;
	private TablaSimbolos ts = new TablaSimbolos();
	private PalabrasReservadas pr = new PalabrasReservadas();
	private String programa;
	private boolean cadena; //variable que nos informa cuando se empezo una cadena
	private boolean comentario; //variable que nos informa cuando se empezo un comentario
	private int indice;
	private boolean termino;
	private int errores;

	private MatrizAcciones matrizAcciones;
	private static final int FINAL = -1;
	private static final int ERROR = -2;
	private static final int[][] matrizTransicionEstados = {

			/*        letra digito   f     /      *      +      -      =      <     >       {      }      (      )      ,      ;      "      .     %      _    Blanco-tab   i       !   otro    nl */
			/* 0 */  {    1,    2,    1,     6, FINAL, FINAL, FINAL,     9,    10,    11, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL,    12,    15, ERROR, ERROR,         0,    1,    14, ERROR,     0 },
			/* 1 */  {    1,    1,    1, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL,     1,     FINAL,    1, FINAL, FINAL, FINAL },
			/* 2 */  {ERROR,    2,ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,     3, ERROR,    13,     ERROR, ERROR,ERROR, ERROR, ERROR },
			/* 3 */  {FINAL,    3,    4, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL,     FINAL, FINAL,FINAL, FINAL, FINAL },
			/* 4 */  {ERROR,ERROR,ERROR, ERROR, ERROR,     5,     5, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,     ERROR, ERROR,ERROR, ERROR, ERROR },
			/* 5 */  {ERROR,   16,ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,     ERROR, ERROR,ERROR, ERROR, ERROR },
			/* 6 */  {FINAL,FINAL,FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL,     7, FINAL,     FINAL, FINAL,FINAL, FINAL, FINAL },
			/* 7 */  {    7,    7,    7,     7,     7,     7,     7,     7,     7,     7,     7,     7,     7,     7,     7,     7,     7,     7,     8,     7,         7,     7,    7,     7,     7 },
			/* 8 */  {    7,    7,    7,     0,     7,     7,     7,     7,     7,     7,     7,     7,     7,     7,     7,     7,     7,     7,     8,     7,         7,     7,    7,     7,     7 },
			/* 9 */  {FINAL,FINAL,FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL,     FINAL, FINAL,FINAL, FINAL, FINAL },
			/* 10 */ {FINAL,FINAL,FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL,     FINAL, FINAL,FINAL, FINAL, FINAL },
			/* 11 */ {FINAL,FINAL,FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL,     FINAL, FINAL,FINAL, FINAL, FINAL },
			/* 12 */ {   12,   12,   12,    12,    12,    12,    17,    12,    12,    12,    12,    12,    12,    12,    12,    12, FINAL,    12,    12,    12,        12,    12,   12,    12, ERROR },
			/* 13 */ {ERROR,ERROR,ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,     ERROR, FINAL,ERROR, ERROR, ERROR },
			/* 14 */ {ERROR,ERROR,ERROR, ERROR, ERROR, ERROR, ERROR, FINAL, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,     ERROR, ERROR,ERROR, ERROR, ERROR },
			/* 15 */ {ERROR,    3,ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR,     ERROR, FINAL,ERROR, ERROR, ERROR },
			/* 16 */ {FINAL,   16,FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL, FINAL,     FINAL, FINAL,FINAL, FINAL, FINAL },
			/* 17 */ {   12,   12,   12,    12,    12,    12,    17,    12,    12,    12,    12,    12,    12,    12,    12,    12, FINAL,    12,    12,    12,        12,    12,   12,    12,    12 }

	};

	public AnalizadorLexico(String path) throws IOException {
		matrizAcciones = new MatrizAcciones();
		this.programa = new String(Files.readAllBytes(Paths.get(path))) + "  ";// Para dectectar mas facil Errores
		this.indice = 0;
		this.lexema = "";
		this.cadena = false;
		this.comentario = false;
		this.termino = false;
	}

	/* Nos devuelve el siguiente estado */
	private int getSiguienteEstado(int i, int j) {
		return this.matrizTransicionEstados[i][j];
	}

	public Token yylex(String yylval) {
		if (termino)
			return new Token("Fin");
		char c;
		Token t = null;
		int estado = 0;
		int estadosig = 0;
		while ((estado != ERROR) && (estado != FINAL)) {
			c = this.programa.charAt(indice);
			//System.out.println("c "+c);
			//System.out.println(estado);
			if ((int) c == 13) { // Condicion Para ignorar los /r antes de /n
				this.indice++;
				c = this.programa.charAt(indice);
			}
			int col = paridad(c);
			estadosig = getSiguienteEstado(estado, col);
			matrizAcciones.getSiguienteEstado(estado, col).execute(this, c);
			estado = estadosig;
			indice++;
			if (indice > this.programa.length() - 1) {
				this.termino = true;
				if (cadena) {
					System.out.println("Error Lexico no se cerro una cadena");
					this.errores++;
					return new Token("Error");
				}
				if (comentario) {
					System.out.println("Error Lexico no se cerro un comentario");
					this.errores++;
				}
				return new Token("Fin");
			}

		}
		if (estado == ERROR) {
			this.errores++;
			System.out.println("Error Lexico se leyo un token no reconocible en la linea: " + this.nroLinea);
			return new Token("Error");
		}

		t = new Token(this.lexema);
		return t;
	}

	/*public Token getToken() throws IOException {
		char c;
		Token t = null;
		int estado = 0;
		int estadosig = 0;
		this.lexema = "";
		if (this.programa.length() - 1 < indice) {
			System.out.println("Fin De Archivo");
			return new Token("Fin");
		}
		while ((estado != ERROR) && (estado != FINAL) && (indice <= this.programa.length() - 1)) {
			c = this.programa.charAt(indice);
			if ((int) c == 13) {
				this.indice++;
				c = this.programa.charAt(indice);
			}
			int col = paridad(c);
			estadosig = getSiguienteEstado(estado, col);
			matrizAcciones.getSiguienteEstado(estado, col).execute(this, c);
			estado = estadosig;
			indice++;
		}
		if (this.programa.length() - 1 < indice) {
			if ((cadena) || (comentario)) {
				System.out.println("Error Lexico no se cierra algo ");
				return new Token("Error");
			}
		}
		if (estado == ERROR) {
			System.out.println("Error Lexico En Linea " + this.nroLinea);
			return new Token("Error");
		}
		if ((this.programa.length() - 1 < indice) && (lexema == "")) {
			System.out.println("Fin De Archivo");
			return new Token("Fin");
		}
		t = new Token(lexema);
		return t;
	}*/

	private int paridad(char c) {
		if (c == 'f')
			return 2;
		if (c == '/')
			return 3;
		if (c == 'i')
			return 21;
		if (((int) c > 64 && (int) c < 91) || ((int) c > 96 && (int) c < 123)) // comparo por numero ASCII
			return 0;
		if ((int) c > 47 && (int) c < 58)
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
		if ((int) c == 32 || (int) c == 9)
			return 20;
		if (c == '!')
			return 22;
		if ((int) c == 10)
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
		return pr;
	}

	public void setPR(PalabrasReservadas pR) {
		pr = pR;
	}

	public boolean isKey(String key) {
		return this.ts.isKey(key);
	}

	public void addSimbolo(String lexema, Integer id) {
		ts.addSimbolo(lexema, id);
	}

	public boolean isPalabraReservada(String l) {
		return pr.isPalabraReservada(l);
	}

	public void imprimirTS() {
		this.ts.imprimirTabla();
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public boolean getCadena() {
		return cadena;
	}

	public void setCadena(boolean cadena) {
		this.cadena = cadena;
	}

	public boolean getComentario() {
		return comentario;
	}

	public void setComentario(boolean comentario) {
		this.comentario = comentario;
	}

	/* Muestra la matriz de transicion de estados */
	public void mostrarMatriz() {
		for (int i = 0; i < this.matrizTransicionEstados.length; i++) {
			System.out.print("|");
			for (int j = 0; j < this.matrizTransicionEstados[i].length; j++) {
				System.out.print(this.matrizTransicionEstados[i][j]);
				if (j != this.matrizTransicionEstados[i].length - 1)
					System.out.print("\t");
			}
			System.out.println("|");
		}
	}

}
