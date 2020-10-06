package AccionesSemanticas;

import AnalizadorLexico.AnalizadorLexico;

public class AS08 extends AccionSemantica{
	
	/* Accion Semantica numero 8
	 * Aumenta +1 al contador de linea y agrega el caracter al string
	 */

	public AS08() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(AnalizadorLexico a_lexico,char c) {
		int linea = a_lexico.getNroLinea();
		linea++;
		a_lexico.setNroLinea(linea);
		String lexema = a_lexico.getLexema() + c ;
		a_lexico.setLexema(lexema);
	}
	
	@Override
	public String toString() {
		return "AS08";
	}

}
