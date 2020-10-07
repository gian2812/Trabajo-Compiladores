import java.io.FileInputStream;
import AnalizadorLexico.Token;
import AnalizadorSintactico.*;

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

public class Main {

	public static void main(String[] args) throws IOException {
		
		Parser p = new Parser("C:\\Users\\Gian\\Desktop\\Compiladores\\Pruebas SINT\\Declaracion_Float.txt");
		p.run();
		//p.imprimirTS();//Definimos esta funcion en el parser para poder analizar los contenidos de la tabla de simbolos.
		}
	}

