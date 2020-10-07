package AccionesSemanticas;

import AnalizadorLexico.AnalizadorLexico;

public class AS12 extends AccionSemantica{
	
	/* Accion semantica numero 12
	 * informa que se cierra el comentario 
	 * Se controla si cadenas y comentarios estan abiertos por si termina el programa sin cerrar alguno de los ya mencionados.
	 */
	
	public AS12() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void execute(AnalizadorLexico a_lexico,char c) {
		a_lexico.setComentario(false);
	}
	
	@Override
	public String toString() {
		return "AS12";
	}

}