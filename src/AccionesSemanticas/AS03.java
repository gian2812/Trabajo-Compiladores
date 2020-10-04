package AccionesSemanticas;

import AnalizadorLexico.AnalizadorLexico;

public class AS03 extends AccionSemantica {

	/* Accion semantica numero 3
	 * Agrega al string el caracter, letra, digito o guion bajo
	 * Agregar letra o dígito al string 
	*/
	
	public AS03() {
		super();
	}
	
	public void execute(AnalizadorLexico a_lexico,char c) {
		//do something
		a_lexico.cadena+=c;
	}

	@Override
	public String toString() {
		return "AS03";
	}

	
}
