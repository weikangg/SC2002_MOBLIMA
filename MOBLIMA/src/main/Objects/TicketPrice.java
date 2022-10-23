package main.Object;
import main.Object.Types.TicketType;

public class TicketPrice {
	private Double[] price;
	
	public TicketPrice(Double[] price)
	{
		this.price = new Double[]{10.00,14.50,5.0,7.0};
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
