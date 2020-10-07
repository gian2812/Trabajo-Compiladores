package AccionesSemanticas;

import AnalizadorLexico.AnalizadorLexico;
import java.lang.Math;

public class AS05 extends AccionSemantica {

	private static final float LIMINF = 1.17549435f * (float) Math.pow(10, -38);
	private static final float LIMSUP = 3.40282347f * (float) Math.pow(10, 38);
	/*
	 * Accion Semantica numero 5 se chequea los limites del exp y valor, se devuelve
	 * el ultimo caracter a la entrada se agrega a la tabla de simbolos si no existe
	 * el cast a string del valor de la constante
	 */

	public AS05() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(AnalizadorLexico a_lexico, char c) {
		String lex = a_lexico.getLexema();
		float value;
		int index = lex.indexOf('f');
		if (index != -1) {
			value = Float.parseFloat(lex.substring(0, index));
			int exp = Integer.parseInt(lex.substring(index + 1, lex.length()));
			value = value * (float) Math.pow(10, exp);
			if ((value < LIMINF)) {
				System.out.println("Warning Fuera de Limite Inferior Constante flotante ");
				value = LIMINF;
			}
			if ((value > LIMSUP)) {
				System.out.println("Warning Fuera de Limite Superior Constante flotante ");
				value = LIMSUP;
			}
		} else {
			value = Float.parseFloat(lex);
		}
		String key = String.valueOf(value);
		if (!a_lexico.isKey(key)) {
			a_lexico.addSimbolo(key, 2);// 2 tipo Float
		}
		int entrada = a_lexico.getIndice();
		entrada = entrada - 1;
		a_lexico.setIndice(entrada);
	}

	@Override
	public String toString() {
		return "AS05";
	}

}
