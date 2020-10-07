package AccionesSemanticas;

import AnalizadorLexico.AnalizadorLexico;

public class AS04 extends AccionSemantica {

	/* Accion semantica numero 4
	* Chequea limite superior de integer
	* no sabemos si es positivo o negativo
	* se chequea en la tabla de simbolo,
	* si no existe se agrega
	* la constante no contiene _i ya que no se agrego en las acciones semanticas previas.
	* en la tabla de simbolos guardamos el valor sin el _i
	*/
	private static final int LIM = 32768;
	public AS04() {
		super();
		// TODO Auto-generated constructor stub
	}
	//Guardamos el lexema en la TS sin el _i
	public void execute(AnalizadorLexico a_lexico,char c) {
		String lex = a_lexico.getLexema();
		int aux = Integer.parseInt(lex);
		if(aux > LIM){
			System.out.println("Warning: Constante Entera Fuera De Rango en Linea "+a_lexico.getNroLinea());
			a_lexico.setLexema("32768");
			lex = "32768";
		}
		if (!(a_lexico.isKey(lex))) {
			a_lexico.addSimbolo(lex, 1);
			}
		}


	@Override
	public String toString() {
		return "AS04";
	}
	
}
