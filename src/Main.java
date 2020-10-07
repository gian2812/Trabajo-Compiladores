import java.io.FileInputStream;
import AnalizadorLexico.Token;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/*-----Importaciones del paquete Analizador lexico-----*/
import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.PalabrasReservadas;
import AnalizadorLexico.TablaSimbolos;

/*-----Importaciones del paqute de Acciones semanticas----*/
import AccionesSemanticas.AccionSemantica;
import AccionesSemanticas.MatrizAcciones;

/*-----Importaciones del paqute de Analizador sintactico----*/
import AnalizadorSintatico.*;

public class Main {

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
		/*Parser p = new Parser("C:\\Users\\Gian\\Desktop\\Compiladores\\Pruebas SINT\\declaracion.txt");
		p.run();*/
		String path = "C:\\Users\\ale-5\\git\\Trabajo-Compiladores\\Pruebas LEX\\CadenaBienDefinida.txt";
		AnalizadorLexico al = new AnalizadorLexico(path);
		}
	}

