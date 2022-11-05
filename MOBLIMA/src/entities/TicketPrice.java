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
	private EnumMap<TicketType, Double> priceList = new EnumMap<>(TicketType.class);
	private EnumMap<MovieType, Double> mtList = new EnumMap<>(MovieType.class);
	private ArrayList<Double> price;
	private ArrayList<Double> mtPrice;
	


	/*
	 * constructer
	 */
	public TicketPrice()
	{
		String path = System.getProperty("user.dir") +"\\data\\ticketprice.csv";
		String path2 = System.getProperty("user.dir") +"\\data\\movietypeprice.csv";
		BufferedReader bufReader;
		BufferedReader bufReaderTwo;
        ArrayList<Double> price = new ArrayList<>();
        ArrayList<Double> mtPrice = new ArrayList<>();
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
		//System.out.println("Price 1:" + price.get(0));
		//System.out.println("MTPrice 1:" + mtPrice.get(0));
	}


	/*
	 * getters
	 */
	public Double getPrice(int index)
	{
		return this.price.get(index);
	}
	public Double getMtPrice(int index)
	{
		return this.mtPrice.get(index);
	}
	public ArrayList<Double> getPrices()
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
	public ArrayList<Double> getMtPriceList()
	{
		return this.mtPrice;
	}


	/*
	 * setters
	 */
	public void setPrice(int index, Double price)
	{
		this.price.set(index,price);
		updatePrice();
	}

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
	public void setMtPrice(int index, Double resoprice)
	{
		this.mtPrice.set(index,resoprice);
	}

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

	

}
