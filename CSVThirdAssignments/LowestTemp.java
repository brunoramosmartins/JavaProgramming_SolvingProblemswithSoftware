
/**
 * Find the coldest hour in a file and in a year
 * 
 * @author Bruno Ramos Martins 
 * @version 30-06-2023
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class LowestTemp {
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestTemp = null;
        for(CSVRecord currRow : parser) {
            if (coldestTemp == null) coldestTemp = currRow;
            else {
                double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(coldestTemp.get("TemperatureF"));
                if ((currTemp < lowestTemp) && (currTemp != -9999.0)) coldestTemp = currRow;
            }
        }
        return coldestTemp;
    }
    
    public String fileWithColdestTemperature() {
        File fileName = null;
        CSVRecord coldestTemp = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currRow = coldestHourInFile(parser);
            
            if (fileName == null) {
                fileName = f;
                coldestTemp = currRow;
            }
            else {
                double lowestTemp = Double.parseDouble(coldestTemp.get("TemperatureF"));
                double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
                if (currTemp < lowestTemp) {
                    fileName = f;
                    coldestTemp = currRow;
                }
            }
            
        }
        return fileName.getAbsolutePath();
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumidity = null;
        int currHumd;
        int lowestHumd;
        for (CSVRecord currRow : parser) {
            if (lowestHumidity == null) {
                lowestHumidity = currRow;
            }
            else {
                if (!currRow.get("Humidity").equals("N/A") && !lowestHumidity.get("Humidity").equals("N/A")) {
                    currHumd = Integer.parseInt(currRow.get("Humidity"));
                    lowestHumd = Integer.parseInt(lowestHumidity.get("Humidity"));
                    if (currHumd < lowestHumd) {
                        lowestHumidity = currRow;
                    }
                }
            }
        }
        return lowestHumidity;
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestHumidity = null;
        int currHumd;
        int lowestHumd;
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currRow = lowestHumidityInFile(parser);
            
            if (lowestHumidity == null) lowestHumidity = currRow;
            else {
                int currTemp = Integer.parseInt(currRow.get("Humidity"));
                int lowestTemp = Integer.parseInt(lowestHumidity.get("Humidity"));
                if (currTemp < lowestTemp) lowestHumidity = currRow;
                else {
                    
                }
            }
        }
        return lowestHumidity;
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
		double num = 0.0;
		double sum = 0.0;

		for(CSVRecord record : parser) {
			double temp = Double.parseDouble(record.get("TemperatureF"));
			sum += temp;
			num++;
		}

		double average = sum / num;
		return average;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
		double num = 0.0;
		double sum = 0.0;

		for(CSVRecord record : parser) {
			double temp = Double.parseDouble(record.get("TemperatureF"));
			int humidity = Integer.parseInt(record.get("Humidity"));
			if(humidity >= value) {
				sum += temp;
				num++;
			}
		}

		double average = sum / num;
		return average;
    }
    
    public void testColdestHourInFile() {
		FileResource f = new FileResource();
		CSVParser parser = f.getCSVParser();

		CSVRecord lowestTemp = coldestHourInFile(parser);
		System.out.println(lowestTemp.get("TemperatureF") + ": " + lowestTemp.get("DateUTC"));
	}
	
    public void testFileWithColdestTemperature() {
		String fileWithColdestTemp = fileWithColdestTemperature();
		File f = new File(fileWithColdestTemp);
		String fileName = f.getName();

		System.out.println("Coldest day was in file " + fileName);

		
		FileResource fr = new FileResource(f);
		CSVParser parser = fr.getCSVParser();
		CSVRecord lowestTemp = coldestHourInFile(parser);

		System.out.println("Coldest Temperature is: " + lowestTemp.get("TemperatureF"));

		System.out.println("All the Temperatures on the coldest day were");
		CSVParser parser2 = fr.getCSVParser();
		for(CSVRecord record : parser2) {
			double temp = Double.parseDouble(record.get("TemperatureF"));
			System.out.println(temp);
		}
	}

    public void testLowestHumidityInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord lowestHumdity = lowestHumidityInFile(parser);

		System.out.println(lowestHumdity.get("Humidity") + " at " + lowestHumdity.get("DateUTC"));
	}
	
    public void testLowestHumidityInManyFiles() {
		CSVRecord record = lowestHumidityInManyFiles();
		System.out.println(record.get("Humidity") + " at " + record.get("DateUTC"));
	}
	
    public void testAverageTemperatureInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		double avg = averageTemperatureInFile(parser);

		System.out.println("average temperature is " + avg);
	}
	
    public void testAverageTemperatureWithHighHumidityInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		double avg = averageTemperatureWithHighHumidityInFile(parser, 80);

		if(!Double.isNaN(avg)) {
			System.out.println("average temperature is " + avg);
		} else {
			System.out.println("No Temperature was found");
		}
	}
}
