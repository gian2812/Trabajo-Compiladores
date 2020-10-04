package AccionesSemanticas;

import AnalizadorLexico.AnalizadorLexico;

public class ASNula extends AccionSemantica{

	/* Accion semantica nula 
	 * La hice ya que hay lugar en la matriz que no asignamos ningun accion semantica
	*/
	
	public ASNula() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(AnalizadorLexico a_lexico,char c) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "ASNula";
	}

}
