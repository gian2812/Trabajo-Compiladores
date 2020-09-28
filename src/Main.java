import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import AnalizadorLexico.LectorBuffer;
import AnalizadorLexico.PalabrasReservadas;
import AnalizadorLexico.TablaTokens;

public class Main {

	public static void main(String[] args) throws IOException {
		/* -----Para leer linea por linea de un archivo-------------
		 String cadena = "";
		
		LectorBuffer leerArchivo;
		String path = "c:\\Users\\ale_5\\git\\Trabajo-Compiladores\\prueba.txt";
		leerArchivo = new LectorBuffer(new InputStreamReader(new FileInputStream(path)));
		while((cadena=leerArchivo.LeerProximoCaracter()) != null) {
	          System.out.println(cadena);
	    }
	    */ 
		
		
		/*------Prueba de si devuelve una palabra reservada--------------
		System.out.println(PalabrasReservadas.getPalabraReservada(3));
		*/
		
		
		/*-----Prueba de tabla de tokens-----
		TablaTokens table = new TablaTokens();
		table.addTokens(";(", 3);
		table.addTokens(")", 4);
		table.imprimirTabla();
		*/
	    }
}
