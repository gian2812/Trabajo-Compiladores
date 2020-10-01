package AnalizadorLexico;

public class PalabrasReservadas {
	
	/* Tenemos que ponerles un ID que nos da el yacc*/
	private static final int IF = 0;
	private static final int THEN = 1;
	private static final int ELSE = 2;
	private static final int END_IF = 3;
	private static final int OUT = 4;
	private static final int FUNCT = 5;
	private static final int RETURN = 6;
	private static final int INTEGER = 7;
	private static final int FLOAT = 8;
	private static final int WHILE = 9;
	private static final int LOOP = 10;
	
	public static String getPalabraReservada(int id) {
		switch(id) {
		case 0:
				return "IF";
		case 1:
				return "THEN";
		case 2: 
				return "ELSE";
		case 3:
				return "END_IF";
		case 4:
				return "OUT";
		case 5:
				return "FUNCT";
		case 6:
				return "RETURN";
		case 7:
				return "INTEGER";
		case 8:
				return "FLOAT";
		case 9:
				return "WHILE";
		case 10:
				return "LOOP";
		default:
				return null;
		}
	}
}
