package AnalizadorLexico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class LectorBuffer extends BufferedReader {
	
	private int lineaActual;
	private int ultimoCaracterLeido;
	private boolean ultimoCaracterFueLeido;
	
	public LectorBuffer(Reader entrada, int tamaño) {
		super(entrada,tamaño);
		this.lineaActual = 1;
		this.ultimoCaracterFueLeido = false;
		this.ultimoCaracterLeido = 0;
		
	}
	
	public LectorBuffer(Reader entrada) {
		super(entrada);
		this.lineaActual = 1;
		this.ultimoCaracterFueLeido = false;
		this.ultimoCaracterLeido = 0;
	}
	
	public int getLineaActual() {
		return this.lineaActual;
	}
	
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
