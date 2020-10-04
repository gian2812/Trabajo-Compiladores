package AnalizadorLexico;

public class AnalizadorLexico {
	public int pos = 0;
	public int nroLinea = 1; //se comienza a leer el archivo desde la linea 1 
	public String cadena;
	public PalabrasReservadas PR= new PalabrasReservadas();
	private static final int FINAL = -1 ;
	private static final int ERROR = -2 ;
	private static final int[][] matrizTransicionEstados = {
			
			/*        letra   digito    f       /      *       +       -      = 	   <	   >	    {	    }	    (	    )	    ,	   ;	   "	   .	    %	    _    Blanco-tab	 i	     !	   otro	    nl	     	   
			/* 0*/	{    1,      2,      1,      6,  FINAL,  FINAL,  FINAL,    9  ,    10 ,    11 ,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,   12  ,     15,  ERROR,  ERROR,      0,      1,     14,  FINAL,   FINAL},
			/* 1*/	{    1,      1,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,    1  ,  FINAL,  FINAL,  FINAL,  FINAL,   FINAL},
			/* 2*/	{FINAL,      2,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,      3,  FINAL,     13,  FINAL,  FINAL,  FINAL,  FINAL,   FINAL},
			/* 3*/	{FINAL,      3,      4,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,	  FINAL},
			/* 4*/	{ERROR,  ERROR,  ERROR,  ERROR,  ERROR,      5,     5 ,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,   ERROR},
			/* 5*/	{ERROR,     16,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,   ERROR},
			/* 6*/	{FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,      7,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,   FINAL},
			/* 7*/	{    7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      8,      7,      7,      7,      7,      7,    7   },
			/* 8*/	{    7,      7,      7,      0,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      7,      8,      7,      7,      7,      7,      7,    7   },
			/* 9*/	{FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,	  FINAL},
			/*10*/	{FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,	  FINAL},
			/*11*/	{FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,	  FINAL},
			/*12*/	{   12,     12,     12,     12,     12,     12,     17,     12,     12,     12,     12,     12,     12,     12,     12,     12,  FINAL,     12,     12,     12,     12,     12,     12,     12,     12 },
			/*13*/	{ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  FINAL,  ERROR,  ERROR,   ERROR},
			/*14*/	{ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  FINAL,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,   ERROR},
			/*15*/	{ERROR,      3,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  ERROR,  FINAL,  ERROR,  ERROR,   ERROR},
			/*16*/	{FINAL,     16,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,  FINAL,	  FINAL},
			/*17*/	{ 12,     12,     12,     12,     12,     12,     17,     12,     12,     12,     12,     12,     12,     12,     12,     12,  FINAL,     12,     12,     12,     12,     12,     12,     12,     12   }
			
			
			}; 
	
	public AnalizadorLexico() {
		
	}
	
	
	/*------Funciones de la matriz de transicion de estados------*/
	
	/* Nos devuelve el siguiente estado */
	public int getSiguienteEstado( int i, int j) {
		return this.matrizTransicionEstados[i][j]; 
	}
	
	/* Muestra la matriz de transicion de estados */
	public void mostrarMatriz() {
		for(int i=0; i<this.matrizTransicionEstados.length; i++) {
			System.out.print("|");
			for(int j=0; j<this.matrizTransicionEstados[i].length;j++) {
				System.out.print(this.matrizTransicionEstados[i][j]);
				if (j!=this.matrizTransicionEstados[i].length-1) 
					System.out.print("\t");
			}
			System.out.println("|");
		}
	}
	
	/*------Fin de las funciones de la matriz de transicion de estados------*/


}
