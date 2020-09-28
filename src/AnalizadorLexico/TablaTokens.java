package AnalizadorLexico;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class TablaTokens {

	/* Tengo duda de si tenemos que crear una clase que represente un tipo, por que iria, _INTEGER,_FLOAT */
	private HashMap<String,Integer> simbolos; /* es la tabla de tokens */
	
	/* Constructor, se inicializa en vacio */
	public TablaTokens(){
		this.simbolos = new HashMap<>();
	}
	
	/* Para agregar un token */
	public void addTokens(String token,Integer id) {
		if (!this.simbolos.containsKey(token))
			this.simbolos.put(token, id);
	}
	
	/* Obtener un token */
	public Integer getTokens(Integer id) {
		return this.simbolos.get(id);
	}

	/* Elimina un token */
	public void removeToken(Integer id) {
		this.simbolos.remove(id);
	}
	
	/* Imprime por pantalla la tabla */
	public void imprimirTabla() {
		System.out.println("Tabla de Tokens");
		Iterator it = simbolos.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry e = (Entry) it.next();
		    System.out.println("Token: " + e.getKey() + " ID: " + e.getValue());
		}
	}
}
