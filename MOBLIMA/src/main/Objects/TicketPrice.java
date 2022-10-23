package main.Object;
import main.Object.Types.TicketType;

public class TicketPrice {
	private Double[] price = new Double[TicketType.getSize()];
	
	public TicketPrice(Double[] price)
	{
		this.price = price;
	}
	
	public Double getPrice(int price)
	{
		return this.price[price];
	}
	
	/*public void hasPromotion()
	{
		this.price
	}*/
	
	
	public Double[] getPriceList()
	{
		return this.price;
	}
	
	public void setPrice(Double price, int ticket)
	{
		this.price[ticket] = price;
	}
}
