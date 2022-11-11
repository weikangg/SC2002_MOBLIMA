package entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumMap;

public class TicketPrice implements Serializable{
	/**
	 * Maps TicketType enum to a price
	 */
	private EnumMap<TicketType, Double> priceList = new EnumMap<>(TicketType.class);
	/**
	 * Maps MovieType enum to a price
	 */
	private EnumMap<MovieType, Double> mtList = new EnumMap<>(MovieType.class);
	/**
	 * Maps CinemaClass enum to a price
	 */
	private EnumMap<CinemaClass, Double> ccList = new EnumMap<>(CinemaClass.class);
	/**
	 * Maps SeatType enum to a price
	 */
	private EnumMap<SeatType, Double> stList = new EnumMap<>(SeatType.class);
	/**
	 * Stores price for each tickettype
	 */
	private ArrayList<Double> price;
	/**
	 * Stores price for each movietype
	 */
	private ArrayList<Double> mtPrice;
	/**
	 * Stores price for each cinemaclass
	 */
	private ArrayList<Double> ccPrice;
	/**
	 * Stores price for each seattype
	 */
	private ArrayList<Double> stPrice;
	


	/**
	 * constructer
	 * Reads from a csv file to get prices and maps it to each enum
	 */
	public TicketPrice()
	{
		String path = System.getProperty("user.dir") +"\\data\\prices\\ticketprice.csv";
		String path2 = System.getProperty("user.dir") +"\\data\\prices\\movietypeprice.csv";
		String path3 = System.getProperty("user.dir") +"\\data\\prices\\cinemaclassprice.csv";
		String path4 = System.getProperty("user.dir") +"\\data\\prices\\seattypeprice.csv";
		BufferedReader bufReader;
		BufferedReader bufReaderTwo;
		BufferedReader bufReaderThree;
		BufferedReader bufReaderFour;
        ArrayList<Double> price = new ArrayList<>();
        ArrayList<Double> mtPrice = new ArrayList<>();
		ArrayList<Double> ccPrice = new ArrayList<>();
		ArrayList<Double> stPrice = new ArrayList<>();
		try {
		    bufReader = new BufferedReader(new FileReader(path));

		    //listOfLines.add(0.2);
		    String line = bufReader.readLine();
		    while (line != null) 
		    {

			price.add(Double.valueOf(line));
					//System.out.println(line);
			line = bufReader.readLine();
		    }
		    bufReader.close();
		}
		catch (FileNotFoundException e){}
		catch (IOException e){}
		try {
		    bufReaderTwo = new BufferedReader(new FileReader(path2));

		    //listOfLines.add(0.2);
		    String line = bufReaderTwo.readLine();
		    while (line != null) 
		    {

			mtPrice.add(Double.valueOf(line));
					//System.out.println(line);
			line = bufReaderTwo.readLine();
		    }
		    bufReaderTwo.close();
		}
		catch (FileNotFoundException e){}
		catch (IOException e){}
		try {
		    bufReaderThree = new BufferedReader(new FileReader(path3));

		    //listOfLines.add(0.2);
		    String line = bufReaderThree.readLine();
		    while (line != null) 
		    {

			ccPrice.add(Double.valueOf(line));
			line = bufReaderThree.readLine();
		    }
		    bufReaderThree.close();
		}
		catch (FileNotFoundException e){}
		catch (IOException e){}
		try {
		    bufReaderFour = new BufferedReader(new FileReader(path4));
		    String line = bufReaderFour.readLine();
		    while (line != null) 
		    {

			stPrice.add(Double.valueOf(line));
			line = bufReaderFour.readLine();
		    }
		    bufReaderFour.close();
		}
		catch (FileNotFoundException e){}
		catch (IOException e){}
		int x = 0;
		for(TicketType type: TicketType.values())
		{
			priceList.put(type,price.get(x));
			x++;
		}
		x=0;
		for(MovieType mt: MovieType.values())
		{
			mtList.put(mt,mtPrice.get(x));
			x++;
		}
		x=0;
		for(CinemaClass cc: CinemaClass.values())
		{
			ccList.put(cc,ccPrice.get(x));
			x++;
		}
		x=0;
		for(SeatType st: SeatType.values())
		{
			stList.put(st,stPrice.get(x));
			x++;
		}
		this.price = price;
		this.mtPrice = mtPrice;
		this.ccPrice = ccPrice;
		this.stPrice = stPrice; 
	}


	

	/** 
	 * Gets the price of the specific tickettype
	 * @param index index of the specific ticket type price
	 * @return Double
	 */
	public Double getPrice(int index)
	{
		return this.price.get(index);
	}
	
	/** 
	 * Gets the price of the specific movietype
	 * @param index index of the specific movie type price
	 * @return Double
	 */
	public Double getMtPrice(int index)
	{
		return this.mtPrice.get(index);
	}
	
	/** 
	 * Gets the price of the specific cinemaclass
	 * @param index index of the specific cinema class price
	 * @return Double
	 */
	public Double getCCPrice(int index)
	{
		return this.ccPrice.get(index);
	}

	/** 
	 * Gets the price of the specific seattypes
	 * @param index index of the specific seat type price
	 * @return Double
	 */
	public Double getSTPrice(int index)
	{
		return this.stPrice.get(index);
	}
	
	/** 
	 * Gets the prices of the ticket types
	 * @return the current ticket type prices
	 */
	public ArrayList<Double> getPrices()
	{
		return this.price;
	}
	
	/** 
	 * Gets the mapped values of ticket type and its prices
	 * @return current enum map of ticket type and its prices
	 */
	public EnumMap<TicketType, Double> getMappedPrice()
	{
		return this.priceList;
	}
	
	/** 
	 * Gets the mapped values of movie type and its prices
	 * @return current enum map of movie type and its prices
	 */
	public EnumMap<MovieType, Double> getMappedMovieTypePrice()
	{
		return this.mtList;
	}
	
	/** 
	 * Gets the prices of the movie types
	 * @return current prices of movie types
	 */
	public ArrayList<Double> getMtPriceList()
	{
		return this.mtPrice;
	}
	
	/** 
	 * Gets the mapped values of cinema class and its prices
	 * @return current enum map of cinema class and its prices
	 */
	public EnumMap<CinemaClass, Double> getMappedCinemaClassPrice()
	{
		return this.ccList;
	}
	
	/** 
	 * Gets the prices of the cinema classes
	 * @return current cinema class prices
	 */
	public ArrayList<Double> getCCPriceList()
	{
		return this.ccPrice;
	}

	/** 
	 * Gets the mapped values of seat type and its prices
	 * @return current enum map of seat type and its prices
	 */
	public EnumMap<SeatType, Double> getMappedSeatTypePrice()
	{
		return this.stList;
	}

	/** 
	 * Gets the prices of the seat types
	 * @return the current prices of seat types
	 */
	public ArrayList<Double> getSTPriceList()
	{
		return this.stPrice;
	}


	/** 
	 * Sets the price of a tickettype
	 * @param index index of the specific ticket type
	 * @param price new price of the specific ticket type
	 */
	public void setPrice(int index, Double price)
	{
		this.price.set(index,price);
		updatePrice();
	}
	/**
	 * updates the ticket type price csv file
	 */
	public void updatePrice()
	{
		String path = System.getProperty("user.dir") +"\\data\\prices\\ticketprice.csv";
		ArrayList<String> arrList = new ArrayList<String>();
		for(int i = 0; i<getPrices().size();i++)
		{
			arrList.add(getPrice(i).toString());
		}
    
        Path output = Paths.get(path);
        try {
            Files.write(output, arrList);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/** 
	 * Sets the price of a movietype
	 * @param index index of the specific movie type
	 * @param resoprice new price of the specific movie tyoe
	 */
	public void setMtPrice(int index, Double resoprice)
	{
		this.mtPrice.set(index,resoprice);
		updateMTPrice();
	}

	/**
	 * updates the movie type price csv file
	 */
	public void updateMTPrice()
	{
		String path2 = System.getProperty("user.dir") +"\\data\\prices\\movietypeprice.csv";
		ArrayList<String> arrList = new ArrayList<String>();
		for(int i = 0; i<getMtPriceList().size();i++)
		{
			arrList.add(getMtPrice(i).toString());
		}
    
        Path output = Paths.get(path2);
        try {
            Files.write(output, arrList);
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

	
	/** 
	 * Sets the price of a cinema class
	 * @param index index of the specific cinema class
	 * @param ccprice new price of the specific cinema class
	 */
	public void setCCPrice(int index, Double ccprice)
	{
		this.ccPrice.set(index,ccprice);
		updateCCPrice();
	}

	/**
	 * updates the cinema class price csv file
	 */
	public void updateCCPrice()
	{
		//String path = System.getProperty("user.dir") +"\\data\\ticketprice.csv";
		String path3 = System.getProperty("user.dir") +"\\data\\prices\\cinemaclassprice.csv";
		ArrayList<String> arrList = new ArrayList<String>();
		for(int i = 0; i<getCCPriceList().size();i++)
		{
			arrList.add(getCCPrice(i).toString());
		}
    
        Path output = Paths.get(path3);
        try {
            Files.write(output, arrList);
        } catch (Exception e) {
            e.printStackTrace();
        }

	}


	/** 
	 * Sets the price of a seat type
	 * @param index index of the specific seat type
	 * @param stprice price of the new seat type
	 */
	public void setSTPrice(int index, Double stprice)
	{
		this.stPrice.set(index,stprice);
		updateSTPrice();
	}

	/**
	 * updates the cinema class price csv file
	 */
	public void updateSTPrice()
	{
		//String path = System.getProperty("user.dir") +"\\data\\ticketprice.csv";
		String path4 = System.getProperty("user.dir") +"\\data\\prices\\seattypeprice.csv";
		ArrayList<String> arrList = new ArrayList<String>();
		for(int i = 0; i<getSTPriceList().size();i++)
		{
			arrList.add(getSTPrice(i).toString());
		}
    
        Path output = Paths.get(path4);
        try {
            Files.write(output, arrList);
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

	

}
