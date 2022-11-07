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
		private int type;

		TicketType(int type)
		{
			this.type = type;
		}
}
