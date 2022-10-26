package entities;

public class Types {
	enum TicketType{
		NORMALWD("Normal Weekday"),
		NORMALWE("Normal Weekend"),
		SENIORWD("Senior Weekday (Age 55 and above)"),
		STUDENT("Student (Before 6pm)");
		
		private static final int size;
		private String type;
		
		static
		{
			size = values().length;
		}
		
		TicketType(String type)
		{
			this.type = type;
		}
		
		public String getDate()
		{
			return this.type;
		}
		
		public static int getSize()
		{
			return size;
		}
		
		public int getIndex(String tt)
		{
			return TicketType.valueOf(tt).ordinal();
		}
		
		
	}

	enum MovieType{
		IMAX,
		THREED,
		DOLBYATMOS;
	}
}
