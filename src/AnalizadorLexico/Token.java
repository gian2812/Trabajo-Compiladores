package AnalizadorLexico;

public class Token {

	private static final int MENORIGUAL = 0;
	private static final int CONST_INT = 0;
	private static final int CONST_FLOAT = 0;
	private static final int MAYORIGUAL = 0;
	private static final int IGUALIGUAL = 0;
	private static final int DISTINTO = 0;
	private static final int CADENA = 0;
	private static final int IF = 0;
	private static final int ELSE = 0;
	private static final int END_IF = 0;
	private static final int OUT = 0;
	private static final int THEN = 0;
	private static final int FUNC = 0;
	private static final int RETURN = 0;
	private static final int INTEGER = 0;
	private static final int FLOAT = 0;
	private static final int WHILE = 0;
	private static final int LOOP = 0;
	private static final int ID = 0;
	private String lexema;
	private int nro;
	
	public Token(String lexema) {
		this.lexema = lexema;
		this.nro = getNro();
	}
		
	private int getNro() {
		if (lexema.equals("+"))
			return 43;
		if (lexema.equals("-"))
			return 45;
		if (lexema.equals(","))
			return 44;
		if (lexema.equals("/"))
			return 47;
		if (lexema.equals("*"))
			return 42;
		if (lexema.equals("("))
			return 40;
		if (lexema.equals(")"))
			return 41;
		if (lexema.equals("<"))
			return 60;
		if (lexema.equals(">"))
			return 62;
		if (lexema.equals("<="))
			return MENORIGUAL;
		if (lexema.equals(">="))
			return MAYORIGUAL;
		if (lexema.equals(";"))
			return 59;
		if (lexema.equals("="))
			return 61;
		if (lexema.equals("=="))
			return IGUALIGUAL;
		if (lexema.equals("!="))
			return DISTINTO;
		if (lexema.charAt(0) == '\"')
			return CADENA;
		if (lexema.equals("IF"))
			return IF;
		if (lexema.equals("ELSE"))
			return ELSE;
		if (lexema.equals("THEN"))
			return THEN;
		if (lexema.equals("END_IF"))
			return END_IF;
		if (lexema.equals("OUT"))
			return OUT;
		if (lexema.equals("FUNC"))
			return FUNC;
		if (lexema.equals("RETURN"))
			return RETURN;
		if (lexema.equals("INTEGER"))
			return INTEGER;
		if (lexema.equals("FLOAT"))
			return FLOAT;
		if (lexema.equals("WHILE"))
			return WHILE;
		if (lexema.equals("LOOP"))
			return LOOP;
		if ((lexema.charAt(0) == '0') || (lexema.charAt(0) == '1') || (lexema.charAt(0) == '2') || (lexema.charAt(0) == '3') || (lexema.charAt(0) == '4') || (lexema.charAt(0) == '5') ||
				(lexema.charAt(0) == '6') || (lexema.charAt(0) == '7') || (lexema.charAt(0) == '8') || (lexema.charAt(0) == '9'))
			if ( lexema.charAt(lexema.length()-1)== 'i')
				return CONST_INT;
			else
				return CONST_FLOAT;
		if (lexema.charAt(0) == '.')
			return CONST_FLOAT;
		if (lexema.equals("{"))
			return 123;
		if (lexema.equals("}"))
			return 125;
		return ID;//SI NO ENTRO EN ALGUN OTRO IF LO UNICO QUE QUEDA ES QUE SEA IDENTIFICADOR
		
	}
	

	@Override
	public String toString() {
		return "Token [lexema=" + lexema + ", nro=" + nro + "]";
	}

	public String getLexema() {
		return this.lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	
}
