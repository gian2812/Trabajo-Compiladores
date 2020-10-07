package AnalizadorLexico;

public class Token {

	public final static short PROC=257;
	public final static short NI=258;
	public final static short VAR=259;
	public final static short MENORIGUAL=260;
	public final static short CONST_INT=261;
	public final static short CONST_FLOAT=262;
	public final static short MAYORIGUAL=263;
	public final static short IGUALIGUAL=264;
	public final static short DISTINTO=265;
	public final static short CADENA=266;
	public final static short IF=267;
	public final static short ELSE=268;
	public final static short END_IF=269;
	public final static short OUT=270;
	public final static short THEN=271;
	public final static short FUNC=272;
	public final static short RETURN=273;
	public final static short INTEGER=274;
	public final static short FLOAT=275;
	public final static short WHILE=276;
	public final static short LOOP=277;
	public final static short ID=278;
	public final static short TRUE=279;
	public final static short FALSE=280;
	public final static short YYERRCODE=256;
	private String lexema;
	private int nro;
	
	public Token(String lexema) {
		this.lexema = lexema;
		this.nro = getNro();
	}
		
	public int getNro() {
		if (lexema.equals("+"))
			return 43;
		if (lexema.equals("Fin"))
			return 0;
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
			if (lexema.contains("f")||lexema.contains("."))
				return CONST_FLOAT;
			else
				return CONST_INT;
		if (lexema.charAt(0) == '.')
			return CONST_FLOAT;
		if (lexema.equals("{"))
			return 123;
		if (lexema.equals("Error"))
			return -1;
		if (lexema.equals("}"))
			return 125;
		if (lexema.equals("PROC"))
			return PROC;
		if (lexema.equals("NI"))
			return NI;
		if (lexema.equals("VAR"))
			return VAR;
		return ID;//SI NO ENTRO EN ALGUN OTRO IF LO UNICO QUE QUEDA ES QUE SEA IDENTIFICADOR
		
	}
	

	@Override
	public String toString() {
		return "Token [lexema= '" + lexema + "' , nro= " + nro + "]";
	}

	public String getLexema() {
		return this.lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	
}
