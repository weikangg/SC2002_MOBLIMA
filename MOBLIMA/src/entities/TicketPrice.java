package entities;

import java.io.Serializable;
import java.util.EnumMap;

public class TicketPrice implements Serializable{
	private EnumMap<TicketType, Double> priceList = new EnumMap<>(TicketType.class);
	private EnumMap<MovieType, Double> mtList = new EnumMap<>(MovieType.class);
	private Double[] price;
	private Double[] mtPrice;
	


	/*
	 * constructer
	 */
	public TicketPrice()
	{
		this.price = new Double[]{8.50,9.50,9.50,11.0,11.0,4.0,7.0};
		this.mtPrice = new Double[]{1.00,1.29,1.45,1.50};
		int x = 0;
		for(TicketType type: TicketType.values())
		{
			priceList.put(type,price[x]);
			x++;
		}
		x=0;
		for(MovieType mt: MovieType.values())
		{
			mtList.put(mt,mtPrice[x]);
			x++;
		}
	}


	/*
	 * getters
	 */
	public Double getPrice(int index)
	{
		return this.price[index];
	}
	public Double getMtPrice(int index)
	{
		return this.mtPrice[index];
	}
	public Double[] getPrices()
	{
		return this.price;
	}
	public EnumMap<TicketType, Double> getMappedPrice()
	{
		return this.priceList;
	}
	public EnumMap<MovieType, Double> getMappedMovieTypePrice()
	{
		return this.mtList;
	}
	public Double[] getMtPriceList()
	{
		return this.mtPrice;
	}


	/*
	 * setters
	 */
	public void setPrice(Double price, int index)
	{
		this.price[index] = price;
	}
	public void setMtPricePrice(Double resoprice, int index)
	{
		this.mtPrice[index] = resoprice;
	}

}
