
/**
 * This program was developed to analyze the most popular names in a specific 
 * country throughout the years.
 * 
 * @author Bruno Ramos Martins 
 * @version 01-07-2023
 */

import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.*;
import java.util.*;

public class BabyBirths {
    
    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) + 
                                   " Gender " + rec.get(1) + 
                                   " Num Born " + rec.get(2));
            }
        }
    }
    
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public long getRank(int year, String name, String gender) {
        long rank = -1;
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        int count = 0;
        for (CSVRecord record : parser) {
            String currName = record.get(0);
            String currGender = record.get(1);
            
            if(currGender.equals(gender) && currName.equals(name)) {
                rank = record.getRecordNumber();
            }
        }
        return rank;
    }
    
    public String getName(int year, int rank, String gender) {
        String name = "";
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
                
        for(CSVRecord record : parser) {
            long currRank = record.getRecordNumber();
            String currGender = record.get(1);
            String currName = record.get(0);

            if(currRank == rank && currGender.equals(gender)) {
                name = currName;
            }
        }

        if(name != "") {
            return name;
        } 
        else {
            return "NO NAME";
        }
    }
        
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        FileResource fr = new FileResource();
        FileResource newFr = new FileResource();
        CSVParser parserOld = fr.getCSVParser(false);
        CSVParser parserNew = newFr.getCSVParser(false);
        String newName = "";
        long popularity = 0;
        
        for(CSVRecord record : parserOld) {
            String currName = record.get(0);
            String currGender = record.get(1);
        
            if(currName.equals(name) && currGender.equals(gender)) {
                popularity = record.getRecordNumber();
            }
        }

        for(CSVRecord record : parserNew) {
            String currGender = record.get(1);
            long currPopularity = record.getRecordNumber();
        
            if(currGender.equals(gender) && popularity == currPopularity) {
                newName = record.get(0);
            }
        }

        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }
        
    public int yearOfHighestRank(String name, String gender) {
        long highestRank = 0;
        int yearOfHighestRank = -1;
        String fileName = "";
        DirectoryResource dr = new DirectoryResource();
        
        // Iterate through all files
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            
            // Iterate through all records in file
            for(CSVRecord record : parser) {
                String currName = record.get(0);
                String currGender = record.get(1);

                if(currName.equals(name) && currGender.equals(gender)) {
                    long currRank = record.getRecordNumber();
                    
                    if(highestRank == 0) {
                        highestRank = currRank;
                        fileName = f.getName();
                    } 
                    else {
                        if(highestRank > currRank) {
                            highestRank = currRank;
                            fileName = f.getName();
                        }
                    }
                }
            }
        }

        // Remove all non-numeric characters from the filename
        fileName = fileName.replaceAll("[^\\d]", "");
        
        // Convert String fileName to Integer
        yearOfHighestRank = Integer.parseInt(fileName);

        return yearOfHighestRank;
    }
    
    public double getAverageRank(String name, String gender) {
        // Initialize a DirectoryResource
        DirectoryResource dr = new DirectoryResource();
        // Define rankTotal, howMany
        double rankTotal = 0.0;
        int howMany = 0;
        // For every file the directory add name rank to agvRank
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            for(CSVRecord record : parser) {
                String currName = record.get(0);
                String currGender = record.get(1);
                if(currName.equals(name) && currGender.equals(gender)){
                    long currRank = record.getRecordNumber();
                    rankTotal += (double)currRank;
                    howMany += 1;
                }
            }
        }
        // Define avgRank = rankTotal / howMany
        double avgRank = rankTotal / (double)howMany;
        return avgRank;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int numBorn = 0;
        long rank = getRank(year, name, gender);
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord record : parser) {
            int currBorn = Integer.parseInt(record.get(2));
            String currGender = record.get(1);
            long currRank = record.getRecordNumber();
            if(gender.equals(currGender) && rank > currRank) {
                numBorn += currBorn;
            }
        }
        return numBorn;
    }
    
    public void testTotlaBirth() {
        //FileResource fr = new FileResource();
        //totalBirths(fr);
        
        // Q3
        // long rank = getRank(1905, "Emily", "F");
        // System.out.println(rank);
        
        // Q4
        // long rank = getRank(1905, "Frank", "M");
        // System.out.println(rank);
        
        // Q5
        // String name = getName(1980, 350, "F");
        // System.out.println(name);
        
        // Q6
        // name = getName(1982, 450, "M");
        // System.out.println(name);
        
        // Q7
        // whatIsNameInYear("Susan", 1972, 2014, "F");
        
        // Q8
        // whatIsNameInYear("Owen", 1974, 2014, "M");
        
        // Q9
        // int year = yearOfHighestRank("Genevieve", "F");
        // System.out.println(year);
        
        // Q10
        // year = yearOfHighestRank("Mich", "M");
        // System.out.println(year);
        
        // Q11
        // double media = getAverageRank("Susan", "F");
        // System.out.println(media);
        
        // Q12
        // media = getAverageRank("Robert", "M");
        // System.out.println(media);
                
        // Q13
        // int t = getTotalBirthsRankedHigher(1990, "Emily", "F");
        // System.out.println(t);
        
        //Q14
        // t = getTotalBirthsRankedHigher(1990, "Drew", "M");
        // System.out.println(t);
    }

    public static void main() {
        BabyBirths names = new BabyBirths();
        names.testTotlaBirth();
    }
}
