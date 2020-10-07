package AccionesSemanticas;

import AnalizadorLexico.AnalizadorLexico;

public class AS10 extends AccionSemantica{
	
	/* Accion semantica numero 10
	 * Agregar el caracter al string y informa se abrio una cadena
	 */
	public AS10() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void execute(AnalizadorLexico a_lexico,char c) {
		String lexema = "";
		lexema += c;
		a_lexico.setLexema(lexema);
		a_lexico.setCadena(true);
	}
	
	@Override
	public String toString() {
		return "AS10";
	}

}
