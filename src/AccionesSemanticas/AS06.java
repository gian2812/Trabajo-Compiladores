package AccionesSemanticas;

import AnalizadorLexico.AnalizadorLexico;

public class AS06 extends AccionSemantica{

	/* Accion semantica numero 6
	 * Agrega +1 al contador de linea 
	 */
	
	public AS06() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(AnalizadorLexico a_lexico,char c) {
		int linea = a_lexico.getNroLinea();
		linea++;
		a_lexico.setNroLinea(linea);
	}

	@Override
	public String toString() {
		return "AS06";
	}
	
	
}
