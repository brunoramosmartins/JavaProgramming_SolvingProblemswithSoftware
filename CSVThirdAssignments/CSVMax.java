
/**
 * Find the highest (hottest) temperature in a file of CSV weather data.
 * 
 * @author Bruno Ramos Martins
 * @version 27/06/2023
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        // start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser) {
            largestSoFar = getLargestofTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }
    
    public void testHottestInDay() {
        FileResource fr = new FileResource("data\\2015\\weather-2015-01-02.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") + 
                            " at " + largest.get("TimeEST"));
    }
    
    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestofTwo(currentRow, largestSoFar);
        }
    // The largestSoFar is the answer
    return largestSoFar;
    }
    
    public void testHottestInManyDays() {
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                           " at " + largest.get("DateUTC"));
    }
    
    public CSVRecord getLargestofTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        
        // If largestSoFar is nothing
        if (largestSoFar == null) largestSoFar = currentRow;
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            // Check if currentRow's temperature > largestSoFar's temperature
            if (currentTemp > largestTemp) {
                // If so update largestSoFar to currentRow
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        // start with smallerSoFar as nothing
        CSVRecord smallerSoFar = null;
        for (CSVRecord currentRow : parser) {
            smallerSoFar = getSmallerofTwo(currentRow, smallerSoFar);
        }
        return smallerSoFar;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource("data\\2015\\weather-2015-01-02.csv");
        CSVRecord smaller = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + smaller.get("TemperatureF") + 
                            " at " + smaller.get("TimeEST"));
    }
    
    public CSVRecord getSmallerofTwo(CSVRecord currentRow, CSVRecord smallerSoFar) {
        
        // If smallerSoFar is nothing
        if (smallerSoFar == null) smallerSoFar = currentRow;
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallerTemp = Double.parseDouble(smallerSoFar.get("TemperatureF"));
            // Check if currentRow's temperature > largestSoFar's temperature
            if ((currentTemp > smallerTemp) && (currentTemp != -9999.0)) {
                // If so update smallerSoFar to currentRow
                smallerSoFar = currentRow;
            }
        }
        return smallerSoFar;
    }
    
    public String fileWithColdestTemperature() {
        
    }
    
    public void testFileWithColdestTemperature() {
        
    }
