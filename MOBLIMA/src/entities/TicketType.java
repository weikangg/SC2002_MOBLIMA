package entities;

public enum TicketType {
		MTW(0),
		THURS(1),
		FRIBS(2),
		FRIAS(3),
		WEEKEND(4),
		SENIORWD(5),
		STUDENTWD(6),
		HOLIDAY(7);
		
		private static final int size;
		private int type;
		
		static
		{
			size = values().length;
		}
		
		TicketType(int type)
		{
			this.type = type;
		}
		
		public static int getSize()
		{
			return size;
		}

		public int getTT()
		{
			return this.type;
		}
}
