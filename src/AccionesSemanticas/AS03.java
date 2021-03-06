package AccionesSemanticas;

import AnalizadorLexico.AnalizadorLexico;

public class AS03 extends AccionSemantica {

	/* Accion semantica numero 3
	 * Agrega al string el caracter, letra, digito o guion bajo
	 * Agregar letra o d�gito al string 
	*/
	
	public AS03() {
		super();
	}
	
	public void execute(AnalizadorLexico a_lexico,char c)  {
			String lexema = a_lexico.getLexema();
			lexema += c;
			a_lexico.setLexema(lexema);
		}
	
	@Override
	public String toString() {
		return "AS03";
	}

	
}
