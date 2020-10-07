import java.io.FileInputStream;
import AnalizadorLexico.Token;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/*-----Importaciones del paquete Analizador lexico-----*/
import AnalizadorLexico.AnalizadorLexico;

import AnalizadorLexico.PalabrasReservadas;
import AnalizadorLexico.TablaSimbolos;

/*-----Importaciones del paqute de Acciones semanticssas----*/
import AccionesSemanticas.AccionSemantica;
import AccionesSemanticas.MatrizAcciones;
import AnalizadorSintatico.*;
public class Main {

	// Funcion para inicializar la matriz de chars
	/*public static void inicMatrizChar(char mat[][]) {
		for (int f = 0; f < mat.length; f++)
			for (int c = 0; c < mat[f].length; c++) {
				mat[f][c] = 'F';
				System.out.println(mat[f][c]);
			}
	}*/

	public static void main(String[] args) throws IOException {
		
		/* String path = "C:\\Users\\Gian\\Desktop\\Compiladores\\Pruebas LEX\\pruebaPRlowercase.txt"; 
		 AnalizadorLexico al = new AnalizadorLexico(path);
		 Token tokenleido = new Token("hola");
		 String pv = null ;
		 while (tokenleido.getNro() != 0) {
			 tokenleido = al.yylex(pv);
			 System.out.println("token leido "+tokenleido);
		 }
		 al.imprimirTS();*/
		Parser p = new Parser("C:\\Users\\Gian\\Desktop\\Compiladores\\Pruebas SINT\\declaracion.txt");
		p.run();
			
		}
	}

