package AccionesSemanticas;

import AnalizadorLexico.AnalizadorLexico;

public class AS09 extends AccionSemantica{

	/* Accion semantica numero 9
	 * Meter en la tabla de simbolo si no existe y agregar el caracter al string
	 */
	
	public AS09() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(AnalizadorLexico a_lexico,char c) {
		String lexema = a_lexico.getLexema();
		lexema += c;
		a_lexico.setLexema(lexema);
		
	}
	
	@Override
	public String toString() {
		return "AS09";
	}

}
