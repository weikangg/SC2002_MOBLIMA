package entities;
/**
 * An enum class defining Ticket Type
 * @author Jovan Sie
 * @version 1.0
 * @since 06-11-2022
 */
public enum TicketType {
		/**
		 * Ticket Type for Monday-Tuesday-Wednesday
		 */
		MTW(0),
		/**
		 * Ticket Type for Thursday
		 */
		THURS(1),
		/**
		 * Ticket Type for Friday Before 6pm
		 */
		FRIBS(2),
		/**
		 * Ticket Type for Friday After 6pm
		 */
		FRIAS(3),
		/**
		 * Ticket Type for Weekend
		 */
		WEEKEND(4),
		/**
		 * Ticket Type for Senior (Weekdays only)
		 */
		SENIORWD(5),
		/**
		 * Ticket Type for Student (Weekdays only)
		 */
		STUDENTWD(6),
		/**
		 * Ticket Type for Holidays
		 */
		HOLIDAY(7);
		/**
		 * index number
		 */
		private int type;
		TicketType(int type)
		{
			this.type = type;
		}
}
