package AccionesSemanticas;

import AnalizadorLexico.AnalizadorLexico;

public class AS07 extends AccionSemantica {
	
	/* Accion semantica numero 7
	 * vuelve al caracter anterior
	 * ejemplo: precio = precio + 1
	 */

	public AS07() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(AnalizadorLexico a_lexico,char c) {
		int pos = a_lexico.getIndice();
		pos--;
		a_lexico.setIndice(pos); //Vuelve a la entrada el ultimo caracter 
	}

	@Override
	public String toString() {
		return "AS07";
	}
}
