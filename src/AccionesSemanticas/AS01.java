package AccionesSemanticas;

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
	
	public void execute() {
		//do something
	}
	
	public String toString() {
		return "AS01";
	}
	
}
