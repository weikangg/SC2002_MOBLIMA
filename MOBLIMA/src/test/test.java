package test;

import java.io.FileReader;

import com.opencsv.*;



public class test {
    public static void main(String[] args) {
        try {
            FileReader filereader = new FileReader(System.getProperty("user.dir") +"\\rsc\\login.csv"); 
            System.out.println("a");
            CSVReader csvReader = new CSVReader(filereader); 
            System.out.println("b");
            String[] nextRecord; 
            nextRecord = csvReader.readNext();
            System.out.println(nextRecord[0]);
            csvReader.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
