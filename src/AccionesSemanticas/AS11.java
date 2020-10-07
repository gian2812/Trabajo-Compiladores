package AccionesSemanticas;

import AnalizadorLexico.AnalizadorLexico;

public class AS11 extends AccionSemantica{
	
	/* Accion semantica numero 11
	 * informa que se abre comentario 
	 */
	public AS11() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void execute(AnalizadorLexico a_lexico,char c) {
		a_lexico.setComentario(true);
		a_lexico.setLexema("");
	}
	
	@Override
	public String toString() {
		return "AS11";
	}

}