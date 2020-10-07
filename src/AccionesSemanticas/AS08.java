package AccionesSemanticas;

import AnalizadorLexico.AnalizadorLexico;

public class AS08 extends AccionSemantica{
	
	/* Accion Semantica numero 8
	 *Cuenta el salto de linea y elimina el "-" de la cadena
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
		String lexema = a_lexico.getLexema().substring(0, a_lexico.getLexema().length()-1); 
		a_lexico.setLexema(lexema);
	}
	
	@Override
	public String toString() {
		return "AS08";
	}

}
