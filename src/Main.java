import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import AnalizadorLexico.LectorBuffer;
import AccionesSemanticas.AccionSemantica;
//import AnalizadorLexico.PalabrasReservadas;
//import AnalizadorLexico.TablaTokens;

public class Main {

	//Funcion para inicializar la matriz de chars
		public static void inicMatrizChar(char mat[][]) {
			for(int f=0;f<mat.length;f++)
	            for(int c=0;c<mat[f].length;c++) {
					mat[f][c]='F';
					System.out.println(mat[f][c]);
				}
		}

	public static void main(String[] args) throws IOException {
		// -----Para leer linea por linea de un archivo
		 String cadena = "";
		LectorBuffer leerArchivo;
		//String pathAle = "c:\\Users\\ale_5\\git\\Trabajo-Compiladores\\prueba.txt";
		String pathGian = "C:\\Users\\Gian\\Desktop\\Compiladores\\prueba.txt";
		leerArchivo = new LectorBuffer(new InputStreamReader(new FileInputStream(pathGian)));
		while((cadena=leerArchivo.readLine()) != null) {
	          System.out.println(cadena);

	    }
		//Definicion de matriz de transicion de estados y matriz de Acciones Semanticas
		char matrizTrancision[][] = new char[12][23];
		//AccionSemantica[][] matrizAsemantica = new AccionSemantica[12][23];
		inicMatrizChar(matrizTrancision);

   }
}


