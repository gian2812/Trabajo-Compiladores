package AnalizadorLexico;

public class Token {

	private String tipo;
	private String lexema;
	private int linea;
	private int puntero;
	
	public Token(String lexema,String tipo ,int linea) {
		this.lexema = lexema;
		this.linea = linea;
		this.tipo = tipo;
		this.puntero = 1;
	}
		

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getLexema() {
		return this.lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public int getLinea() {
		return this.linea;
	}

	public String getTipo() {
		return this.tipo;
	}

	public int getPuntero() {
		return this.puntero;
	}
	
	public int addPuntero() {
		this.puntero ++;
		return this.puntero;
	}

	public int removePuntero() {
		this.puntero--;
		return this.puntero;
	}
	
}
