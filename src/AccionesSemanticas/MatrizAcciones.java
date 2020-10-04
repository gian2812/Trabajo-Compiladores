package AccionesSemanticas;

public class MatrizAcciones {

	private static final AS01 as01 = new AS01();
	private static final AS02 as02 = new AS02();
	private static final AS03 as03 = new AS03();
	private static final AS04 as04 = new AS04();
	private static final AS05 as05 = new AS05();
	private static final AS06 as06 = new AS06();
	private static final AS07 as07 = new AS07();
	private static final AS08 as08 = new AS08();
	private static final AS09 as09 = new AS09();
	private static final ASNula asNula = new ASNula();
	private AccionSemantica [][] matrizAcciones;
	
	
	public MatrizAcciones() {
		matrizAcciones= new AccionSemantica[][] {
			/*        letra   digito    f       /      *       +       -      = 	   <	   >	    {	    }	    (	    )	    ,	   ;	   "	   .	    %	    _   Blanco-tab	    i	   !	otro	   nl	     	   
			/* 0*/	{  as02,   as02, as02,   as02,   as02,  as02,   as02,  as02,    as02,    as02,   as02,   as02,   as02,   as02,   as02,   as02,  as02,   as02,  asNula,  asNula,     asNula,  as02,  as02,  asNula,   as06},
			/* 1*/	{  as03,   as03, as03,   as01,   as01,  as01,   as01,  as01,    as01,    as01,   as01,   as01,   as01,   as01,   as01,   as01,  as01,   as01,    as01,    as03,       as01,  as03,  as01,    as01,   as01},
			/* 2*/	{asNula,   as03,asNula,asNula, asNula,asNula, asNula,asNula,  asNula,  asNula, asNula, asNula, asNula, asNula, asNula, asNula,asNula,   as03,  asNula,  asNula,     asNula,asNula,asNula,  asNula, asNula},
			/* 3*/	{  as05,   as03, as03,   as05,   as05,  as05,   as05,  as05,    as05,    as05,   as05,   as05,   as05,   as05,   as05,   as05,  as05,   as05,    as05,    as05,       as05,  as05,  as05,    as05,   as05},
			/* 4*/	{asNula, asNula,asNula,asNula, asNula,  as03,   as03,asNula,  asNula,  asNula, asNula, asNula, asNula, asNula, asNula, asNula,asNula, asNula,  asNula,  asNula,     asNula,asNula,asNula,  asNula, asNula},
			/* 5*/	{asNula,   as03,asNula,asNula, asNula,asNula, asNula,asNula,  asNula,  asNula, asNula, asNula, asNula, asNula, asNula, asNula,asNula, asNula,  asNula,  asNula,     asNula,asNula,asNula,  asNula, asNula},
			/* 6*/	{  as07,   as07,  as07,  as07,   as07,  as07,   as07,  as07,    as07,    as07,   as07,   as07,   as07,   as07,   as07,   as07,  as07,   as07,    as07,  asNula,       as07,  as07,  as07,    as07,   as07},
			/* 7*/	{asNula, asNula,asNula,asNula, asNula,asNula, asNula,asNula,  asNula,  asNula, asNula, asNula, asNula, asNula, asNula, asNula,asNula, asNula,  asNula,  asNula,     asNula,asNula,asNula,  asNula,   as06},
			/* 8*/	{asNula, asNula,asNula,asNula, asNula,asNula, asNula,asNula,  asNula,  asNula, asNula, asNula, asNula, asNula, asNula, asNula,asNula, asNula,  asNula,  asNula,     asNula,asNula,asNula,  asNula,   as06},
			/* 9*/	{  as07,   as07,  as07,  as07,   as07,  as07,   as07,  as03,    as07,    as07,   as07,   as07,   as07,   as07,   as07,   as07,  as07,   as07,    as07,    as07,       as07,  as07,  as07,    as07,   as07},
			/*10*/  {  as07,   as07,  as07,  as07,   as07,  as07,   as07,  as03,    as07,    as07,   as07,   as07,   as07,   as07,   as07,   as07,  as07,   as07,    as07,    as07,       as07,  as07,  as07,    as07,   as07},
			/*11*/	{  as07,   as07,  as07,  as07,   as07,  as07,   as07,  as03,    as07,    as07,   as07,   as07,   as07,   as07,   as07,   as07,  as07,   as07,    as07,    as07,       as07,  as07,  as07,    as07,   as07},
			/*12*/	{  as03,   as03,  as03,  as03,   as03,  as03,   as03,  as03,    as03,    as03,   as03,   as03,   as03,   as03,   as03,   as03,  as09,   as03,    as03,    as03,       as03,  as03,  as03,    as03, asNula},
			/*13*/	{asNula, asNula,asNula,asNula, asNula,asNula, asNula,asNula,  asNula,  asNula, asNula, asNula, asNula, asNula, asNula, asNula,asNula, asNula,  asNula,  asNula,     asNula,  as04,asNula,  asNula, asNula},
			/*14*/	{asNula, asNula,asNula,asNula, asNula,asNula, asNula,  as03,  asNula,  asNula, asNula, asNula, asNula, asNula, asNula, asNula,asNula, asNula,  asNula,  asNula,     asNula,asNula,asNula,  asNula, asNula},
			/*15*/	{asNula,   as03,asNula,asNula, asNula,asNula, asNula,asNula,  asNula,  asNula, asNula, asNula, asNula, asNula, asNula, asNula,asNula, asNula,  asNula,  asNula,     asNula,asNula,asNula,  asNula, asNula},
			/*16*/	{  as05,   as03, as05,   as05,   as05,  as05,   as05,  as05,    as05,    as05,   as05,   as05,   as05,   as05,   as05,   as05,  as05,   as05,    as05,    as05,       as05,  as05,  as05,    as05,   as05},
			/*17*/	{  as03,   as03,  as03,  as03,   as03,  as03,   as03,  as03,    as03,    as03,   as03,   as03,   as03,   as03,   as03,   as03,  as09,   as03,    as03,    as03,       as03,  as03,  as03,    as03,   as08}
			};
	}
	
	/* Nos devuelve el siguiente estado */
	public AccionSemantica getSiguienteEstado( int i, int j) {
		return this.matrizAcciones[i][j]; 
	}
	
	/* Muestra la matriz de acciones semanticas */
	public void mostrarMatriz() {
		for(int i=0; i<this.matrizAcciones.length; i++) {
			System.out.print("|");
			for(int j=0; j<this.matrizAcciones[i].length;j++) {
				System.out.print(this.matrizAcciones[i][j].toString());
				if (j!=this.matrizAcciones[i].length-1) 
					System.out.print("\t");
			}
			System.out.println("|");
		}
	}
	
}
