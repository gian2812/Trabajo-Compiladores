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
		if (!a_lexico.PR.isPalabraReservada(a_lexico.lexema)){
			if (a_lexico.lexema.length() < 20) {
				//tirar warning
				a_lexico.lexema = a_lexico.lexema.substring(0,19);
				System.out.println("Warning longitud del lexema sobrepasa los limites en linea "+a_lexico.nroLinea);
				
			}
			boolean hasUppercase = !a_lexico.lexema.equals(a_lexico.lexema.toLowerCase());
			if (hasUppercase) {
				System.out.println("Warning identificador contiene letra mayuscula");
				a_lexico.lexema = a_lexico.lexema.toLowerCase();
			}
			if (!a_lexico.TS.isKey(a_lexico.lexema))
				a_lexico.TS.addSimbolo(a_lexico.lexema, 0);
		}
	}
	
	public String toString() {
		return "AS01";
	}
	
}
