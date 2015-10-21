
	public enum OPERATION
	{
		ADD("ADD"),
		SUB("SUB"),
		MULT("MULT"),
		DIV("DIV");

		private final String OPname;

 		OPERATION(String name)
 		{
 			this.OPname = name;
 		}

 		public static boolean checkPriority(OPERATION op1, OPERATION op2)
 		{	
 			return !((op1 == ADD || op1 == SUB) && (op2 == MULT || op2 == DIV));
 		}

	}	

	