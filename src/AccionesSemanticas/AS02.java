package AccionesSemanticas;

import AnalizadorLexico.AnalizadorLexico;

public class AS02 extends AccionSemantica{
	
	/* Accion semantica numero 2
	* Inicializar String (se reserva la maxima longitud permitida para identificadores)
	* Agrega letra a string
	*/
	
	public AS02(){
		super();
	}
	
	public void execute(AnalizadorLexico a_lexico,char c) {
			String lexema = "";
			lexema += c;
			a_lexico.setLexema(lexema);
		}
	
	public String toString() {
		return "AS02";
	}

}
