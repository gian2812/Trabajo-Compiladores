package AccionesSemanticas;

import AnalizadorLexico.AnalizadorLexico;

public class AS01 extends AccionSemantica {
	
	/* Accion semantica numero uno
	 * chequea la cantidad de caracteres que sea menor a 20, si es mayor tira un warning y trunquea el digito
	 * tambien chequea si es una de las palabras reservadas, si no es palabra reservada chequea que todo 
	 * sea en letras minusculas, si no, se fija en la tabla de simbolos por si es un identificador ya usado, 
	 * si existe se guarda puntero a la tabla de simbolo, si no existe se agrega y se guarda el puntero. 
	 * Y vuelve un digito para atras
	*/
	
	public AS01() {
		super();
	}
	//falta volver caracter a entrada
	public void execute(AnalizadorLexico a_lexico,char c) {
		//variable lexema
		int i=0;
		if (!a_lexico.isPalabraReservada(a_lexico.getLexema())) {
			if (a_lexico.getLexema().length() < 20) {
				//tirar warning
				a_lexico.setLexema(a_lexico.getLexema().substring(0,19));
				System.out.println("Warning longitud del lexema sobrepasa los limites en linea "+a_lexico.getNroLinea());
			}
			boolean hasUppercase = !a_lexico.getLexema().equals(a_lexico.getLexema().toLowerCase());
			if (hasUppercase) {
				System.out.println("Warning identificador contiene letra mayuscula");
				a_lexico.setLexema(a_lexico.getLexema().toLowerCase());
			}
			if (!a_lexico.isKey(a_lexico.getLexema()))
				a_lexico.addSimbolo(a_lexico.getLexema(), 0);
		}
		i = a_lexico.getIndice() -1;
		a_lexico.setIndice(i);
	}
	
	public String toString() {
		return "AS01";
	}
	
}
