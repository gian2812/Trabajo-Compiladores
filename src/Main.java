import java.io.FileInputStream;
import java.io.InputStreamReader;

import AnalizadorLexico.LectorBuffer;

public class Main {

	public static void main(String[] args) {
		String cadena = "";
		LectorBuffer leeArchivo;
		String path = "c:\\Users\\ale_5\\git\\Trabajo-Compiladores\\prueba.txt"
		leeArchivo = new LectorBuffer(new InputStreamReader(new FileInputStream(path));
		cadena = leerArchivo.readLine();
		while(cadena != null) {
	          System.out.println(cadena);
	    }
	}

}
