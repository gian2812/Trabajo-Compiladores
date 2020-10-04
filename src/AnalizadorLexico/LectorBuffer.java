package AnalizadorLexico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;


/* Clase que nos permite leer el archivo de texto que tiene el codigo */
public class LectorBuffer extends BufferedReader {
	
	private int lineaActual;
	private int ultimoCaracterLeido;
	private boolean ultimoCaracterFueLeido;
	
	
	/* Definimos el constructor */
	public LectorBuffer(Reader entrada) {
		super(entrada);
		this.lineaActual = 1;
		this.ultimoCaracterFueLeido = false;
		this.ultimoCaracterLeido = 0;
	}
	
	/* Se obtiene la linea actual */
	public int getLineaActual() {
		return this.lineaActual;
	}
	
	/* Leo el proximo caracter */
	public int LeerProximoCaracter() throws IOException {
	
		if (this.ultimoCaracterFueLeido)
			this.ultimoCaracterFueLeido = false;
		else {
			if((char)this.ultimoCaracterLeido=='\n')
				this.lineaActual++;
			this.ultimoCaracterLeido = this.read();
		}
		return this.ultimoCaracterLeido;
	}
}
