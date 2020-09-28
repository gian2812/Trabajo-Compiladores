package AnalizadorLexico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;


/* Clase que nos permite leer el archivo de texto que tiene el codigo */
public class LectorBuffer extends BufferedReader {
	
	private int lineaActual;
	private String ultimoCaracterLeido;
	private boolean ultimoCaracterFueLeido;
	
	
	/* Definimos dos constructores, uno con tamaño y otro sin el tamaño */
	public LectorBuffer(Reader entrada, int tamaño) {
		super(entrada,tamaño);
		this.lineaActual = 1;
		this.ultimoCaracterFueLeido = false;
		this.ultimoCaracterLeido = "";
		
	}
	
	public LectorBuffer(Reader entrada) {
		super(entrada);
		this.lineaActual = 1;
		this.ultimoCaracterFueLeido = false;
		this.ultimoCaracterLeido = "";
	}
	
	/* Se obtiene la linea actual */
	public int getLineaActual() {
		return this.lineaActual;
	}
	
	/* Leo el proximo caracter */
	public String LeerProximoCaracter() throws IOException {
		if (this.ultimoCaracterFueLeido)
			this.ultimoCaracterFueLeido = false;
		else {
			if((String)this.ultimoCaracterLeido=="\n")
				this.lineaActual++;
			this.ultimoCaracterLeido = this.readLine();
		}
		return this.ultimoCaracterLeido;
	}
}
