
/**
 * This program was developed to analyze the most popular names in a specific 
 * country throughout the years.
 * 
 * @author Bruno Ramos Martins 
 * @version 01-07-2023
 */

import edu.duke.*;
import org.apache.commons.csv.*;

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
    
    public int getRank(int year, String name, String gender) {
        int rank = -1;
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        int count = 0;
        for (CSVRecord record : parser) {
            String currName = record.get(0);
            String currGender = record.get(1);
            count++;
            if (currGender.equals(gender) && currName.equals(name)){
                rank = count;
                break;
            }
        }
        return rank;
    }
    
    public String getName(int year, int rank, String gender) {
        String name = "";
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        int currRank = 0;
        
        for (CSVRecord record : parser) {
            if (record.get(1).equals(gender)) currRank++;
            String currGender = record.get(1);
            String currName= record.get(0);
            
            if (currRank == rank && currGender.equals(gender)) {
                name = currName;
            }
        }
        
        if (name != "") return name;
        else            return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        FileResource fr = new FileResource();
        FileResource newFr = new FileResource();
        CSVParser parserOld = fr.getCSVParser(false);
        CSVParser parserNew = newFr.getCSVParser(false);
        String newName = "";
        int popularity = 0;
        
        int totalBoysOld = 0;
        int totalGirlsOld = 0;
        int totalBoysNew = 0;
        int totalGirlsNew = 0;
        
        for (CSVRecord record : parserOld) {
            String currName = record.get(0);
            String currGender = record.get(1);
            if (currGender.equals("M")) totalBoysOld++;
            else                        totalGirlsOld++;
            
            if (currName.equals(name) && currGender.equals(gender)) {
                if (gender.equals("M")) popularity = totalBoysOld;
                else                    popularity = totalGirlsOld;
                break;
            }
        }
        
        for (CSVRecord record : parserNew) {
            String currGender = record.get(1);
            if (currGender.equals("M")) totalBoysNew++;
            else                        totalGirlsNew++;
            
            if (currGender.equals(gender) && currGender.equals(gender)) {
                if (gender.equals("M")) int currPopularity = totalBoysNew;
                else                    int currPopularity = totalGirlsNew;
                break;
            }
        }
        
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }
    
    public int yearOfHighestRank(String name, String gender) {
        int highestRank = 0;
        int yearOfHighestRank = -1;
        String fileName = "";
        DirectoryResource dr = new DirectoryResource();
    }
}
