package AccionesSemanticas;

import AnalizadorLexico.AnalizadorLexico;

public class AS01 extends AccionSemantica {
	
	/* Accion semantica numero uno.
	 * Chequea si es una palabra reservada si no,
	 * chequea que la cantidad de caracteres sea menor a 20, si es mayor tira un warning y trunca el ID.
	 * chequea que sea en letras minusculas, si no, tiramos warning y se tranforma en minuscula luego
	 * se fija en la tabla de simbolos por si es un identificador ya usado, 
	 * si no existe se agrega   
	 * Y vuelve un digito para atras
	*/
	
	public AS01() {
		super();
	}
	public void execute(AnalizadorLexico a_lexico,char c) {
		String lex = a_lexico.getLexema();
		if (!a_lexico.isPalabraReservada(lex)) {
			if (lex.length() > 20) {
				lex = lex.substring(0,19);
				System.out.println("Warning longitud del lexema sobrepasa los limites en linea "+a_lexico.getNroLinea());
			}
			boolean hasUppercase = !lex.equals(lex.toLowerCase());
			if (hasUppercase) {
				System.out.println("Warning identificador contiene letra mayuscula");
				lex = (lex.toLowerCase());
				
			}
			a_lexico.setLexema(lex);
			if (!a_lexico.isKey(lex))
				a_lexico.addSimbolo(lex, 0);// 0 desconocido 
		}
		int i = a_lexico.getIndice() -1;
		a_lexico.setIndice(i);
	}
	
	public String toString() {
		return "AS01";
	}
	
}
