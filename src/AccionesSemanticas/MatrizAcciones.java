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
	private AccionSemantica [][] matrizTransicion;
	
	
	public MatrizAcciones() {
		matrizTransicion = new AccionSemantica[][] {
			/*        letra   digito    f       /      *       +       -      = 	   <	   >	    {	    }	    (	    )	    ,	   ;	   "	   .	    %	    _   Blanco-tab	    i	   !	otro	   nl	     	   
			/* 0*/	{  as02,   as02, as02,   as02,   as02,  as02,   as02,  as02,    as02,    as02,   as02,   as02,   as02,   as02,   as02,   as02,  as02,   as02,  asNula,  asNula,     asNula,  as02,  as02,  asNula,   as06},
			/* 1*/	{  as03,   as03, as03,   as01,   as01,  as01,   as01,  as01,    as01,    as01,   as01,   as01,   as01,   as01,   as01,   as01,  as01,   as01,    as01,    as03,       as01,  as03,  as01,    as01,   as01},
			/* 2*/	{asNula,   as03,asNula,asNula, asNula,asNula, asNula,asNula,  asNula,  asNula, asNula, asNula, asNula, asNula, asNula, asNula,asNula,   as03,  asNula,  asNula,     asNula,asNula,asNula,  asNula, asNula},
			/* 3*/	{  as05,   as03, as03,   as05,   as05,  as05,   as05,  as05,    as05,    as05,   as05,   as05,   as05,   as05,   as05,   as05,  as05,   as05,    as05,    as05,       as05,  as05,  as05,    as05,   as05},
			/* 4*/	{asNula, asNula,asNula,asNula, asNula,  as03,   as03,asNula,  asNula,  asNula, asNula, asNula, asNula, asNula, asNula, asNula,asNula, asNula,  asNula,  asNula,     asNula,asNula,asNula,  asNula, asNula},
			/* 5*/	{asNula,   as03,asNula,asNula, asNula,asNula, asNula,asNula,  asNula,  asNula, asNula, asNula, asNula, asNula, asNula, asNula,asNula, asNula,  asNula,  asNula,     asNula,asNula,asNula,  asNula, asNula},
			/* 6*/	{  as07,   as07,  as07,  as07,   as07,  as07,   as07,  as07,    as07,    as07,   as07,   as07,   as07,   as07,   as07,   as07,  as07,   as07,    as07,  asNula,       as07,  as07,  as07,    as07,   as07},
			/* 7*/	{asNula, asNula,asNula,asNula, asNula,asNula, asNula,asNula,  asNula,  asNula, asNula, asNula, asNula, asNula, asNula, asNula,asNula, asNula,  asNula,  asNula,       7,      7,      7,      7,    7   },
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
	}
	
}
