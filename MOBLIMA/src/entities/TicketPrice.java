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
		String path = System.getProperty("user.dir") +"\\data\\ticketprice.csv";
		String path2 = System.getProperty("user.dir") +"\\data\\movietypeprice.csv";
		String path3 = System.getProperty("user.dir") +"\\data\\cinemaclassprice.csv";
		String path4 = System.getProperty("user.dir") +"\\data\\seattypeprice.csv";
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
		catch (FileNotFoundException e){System.out.println("TicketPrice error");}
		catch (IOException e){System.out.println("TicketPrice error");}
		try {
		    bufReaderThree = new BufferedReader(new FileReader(path3));

		    //listOfLines.add(0.2);
		    String line = bufReaderThree.readLine();
		    while (line != null) 
		    {

			ccPrice.add(Double.valueOf(line));
					//System.out.println(line);
			line = bufReaderThree.readLine();
		    }
		    bufReaderThree.close();
		}
		catch (FileNotFoundException e){}
		catch (IOException e){}
		try {
		    bufReaderFour = new BufferedReader(new FileReader(path4));

		    //listOfLines.add(0.2);
		    String line = bufReaderFour.readLine();
		    while (line != null) 
		    {

			stPrice.add(Double.valueOf(line));
					//System.out.println(line);
			line = bufReaderFour.readLine();
		    }
		    bufReaderFour.close();
		}
		catch (FileNotFoundException e){}
		catch (IOException e){}
		//this.price = new Double[]{8.50,9.50,9.50,11.0,11.0,4.0,7.0,12.0};
		//this.mtPrice = new Double[]{1.00,1.29,1.45,1.50};
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
		//System.out.println("Price 1:" + price.get(0));
		//System.out.println("MTPrice 1:" + mtPrice.get(0));
	}


	

	/**
	 * getters
	 */
	/** 
	 * Gets the price of the specific tickettype
	 * @param index
	 * @return Double
	 */
	public Double getPrice(int index)
	{
		return this.price.get(index);
	}
	
	/** 
	 * Gets the price of the specific movietype
	 * @param index
	 * @return Double
	 */
	public Double getMtPrice(int index)
	{
		return this.mtPrice.get(index);
	}
	
	/** 
	 * Gets the price of the specific cinemaclass
	 * @param index
	 * @return Double
	 */
	public Double getCCPrice(int index)
	{
		return this.ccPrice.get(index);
	}

	/** 
	 * Gets the price of the specific seattypes
	 * @param index
	 * @return Double
	 */
	public Double getSTPrice(int index)
	{
		return this.stPrice.get(index);
	}
	
	/** 
	 * Gets the prices of the tickettypes
	 * @return ArrayList<Double>
	 */
	public ArrayList<Double> getPrices()
	{
		return this.price;
	}
	
	/** 
	 * Gets the mapped values of tickettype and its prices
	 * @return EnumMap<TicketType, Double>
	 */
	public EnumMap<TicketType, Double> getMappedPrice()
	{
		return this.priceList;
	}
	
	/** 
	 * Gets the mapped values of movietype and its prices
	 * @return EnumMap<MovieType, Double>
	 */
	public EnumMap<MovieType, Double> getMappedMovieTypePrice()
	{
		return this.mtList;
	}
	
	/** 
	 * Gets the prices of the movietypes
	 * @return ArrayList<Double>
	 */
	public ArrayList<Double> getMtPriceList()
	{
		return this.mtPrice;
	}
	
	/** 
	 * Gets the mapped values of cinemaclass and its prices
	 * @return EnumMap<CinemaClass, Double>
	 */
	public EnumMap<CinemaClass, Double> getMappedCinemaClassPrice()
	{
		return this.ccList;
	}
	
	/** 
	 * Gets the prices of the cinemaclasses
	 * @return ArrayList<Double>
	 */
	public ArrayList<Double> getCCPriceList()
	{
		return this.ccPrice;
	}

	/** 
	 * Gets the mapped values of seattype and its prices
	 * @return EnumMap<SeatType, Double>
	 */
	public EnumMap<SeatType, Double> getMappedSeatTypePrice()
	{
		return this.stList;
	}

	/** 
	 * Gets the prices of the seattypes
	 * @return ArrayList<Double>
	 */
	public ArrayList<Double> getSTPriceList()
	{
		return this.stPrice;
	}


	/**
	 * setters
	 */
	/** 
	 * Sets the price of a tickettype
	 * @param index
	 * @param price
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
		String path = System.getProperty("user.dir") +"\\data\\ticketprice.csv";
		//String path2 = System.getProperty("user.dir") +"\\data\\movietypeprice.csv";
		ArrayList<String> arrList = new ArrayList<String>();
		for(int i = 0; i<getPrices().size();i++)
		{
			arrList.add(getPrice(i).toString());
		}
    
        Path output = Paths.get(path);
        try {
            Files.write(output, arrList);
            System.out.println(output.toFile().getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/** 
	 * Sets the price of a movietype
	 * @param index
	 * @param resoprice
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
		//String path = System.getProperty("user.dir") +"\\data\\ticketprice.csv";
		String path2 = System.getProperty("user.dir") +"\\data\\movietypeprice.csv";
		ArrayList<String> arrList = new ArrayList<String>();
		for(int i = 0; i<getMtPriceList().size();i++)
		{
			arrList.add(getMtPrice(i).toString());
		}
    
        Path output = Paths.get(path2);
        try {
            Files.write(output, arrList);
            System.out.println(output.toFile().getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

	
	/** 
	 * Sets the price of a cinema class
	 * @param index
	 * @param ccprice
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
		String path3 = System.getProperty("user.dir") +"\\data\\cinemaclassprice.csv";
		ArrayList<String> arrList = new ArrayList<String>();
		for(int i = 0; i<getCCPriceList().size();i++)
		{
			arrList.add(getCCPrice(i).toString());
		}
    
        Path output = Paths.get(path3);
        try {
            Files.write(output, arrList);
            System.out.println(output.toFile().getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

	}


		/** 
	 * Sets the price of a cinema class
	 * @param index
	 * @param ccprice
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
		String path4 = System.getProperty("user.dir") +"\\data\\seattypeprice.csv";
		ArrayList<String> arrList = new ArrayList<String>();
		for(int i = 0; i<getSTPriceList().size();i++)
		{
			arrList.add(getSTPrice(i).toString());
		}
    
        Path output = Paths.get(path4);
        try {
            Files.write(output, arrList);
            System.out.println(output.toFile().getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

	

}
