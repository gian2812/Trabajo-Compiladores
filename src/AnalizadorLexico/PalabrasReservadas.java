package AnalizadorLexico;
import java.util.ArrayList;

public class PalabrasReservadas {
	
	
	private ArrayList<String> listaPR = new ArrayList<String>();
	
	public PalabrasReservadas() {
		listaPR.add("IF");
		listaPR.add("THEN");
		listaPR.add("ELSE");
		listaPR.add("END_IF");
		listaPR.add("OUT");
		listaPR.add("FUNC");
		listaPR.add("RETURN");
		listaPR.add("INTEGER");
		listaPR.add("FLOAT");
		listaPR.add("WHILE");
		listaPR.add("LOOP");
	};
	
	public void addPR(String p) {
		listaPR.add(p);
	}
	
	public boolean isPalabraReservada(String l) {
		return listaPR.contains(l);
	}
}
