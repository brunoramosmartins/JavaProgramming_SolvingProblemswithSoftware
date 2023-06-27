
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
    }
    // The largestSoFar is the answer
    return largestSoFar;
    }
    
    public void testHottestInManyDays() {
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                           " at " + largest.get("DateUTC"));
    }
}
