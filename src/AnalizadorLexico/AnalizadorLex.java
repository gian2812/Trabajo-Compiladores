package AnalizadorLexico;

public class AnalizadorLex {
	static short ERROR = -1 ;
	static short FINAL = 5 ;
	static final short[][] matrizTransicionEstados = {
			
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


}
