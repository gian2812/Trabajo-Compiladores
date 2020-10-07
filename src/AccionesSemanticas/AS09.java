package AccionesSemanticas;

import AnalizadorLexico.AnalizadorLexico;

public class AS09 extends AccionSemantica{

	/* Accion semantica numero 9
	 * Agregar el caracter al string y meter en la tabla de simbolo si no existe. 
	 * Controla que se cierra la cadena 
	 */
	
	public AS09() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(AnalizadorLexico a_lexico,char c) {
		String lex = a_lexico.getLexema();
		lex += c;
		a_lexico.setLexema(lex);
		if (!a_lexico.isKey(lex))
			a_lexico.addSimbolo(lex, 3);//Cadena Multilinea
		a_lexico.setCadena(false);
		
	}
	
	@Override
	public String toString() {
		return "AS09";
	}

}
