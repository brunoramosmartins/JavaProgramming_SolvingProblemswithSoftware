
/**
 * Escreva a descrição da classe WhichCountriesExport aqui.
 * 
 * @author Bruno Ramos Martins 
 * @version (número de versão ou data)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    
    public void listExporters(CSVParser parser, String exportOfInterest1, String exportOfInterest2) {

        // for each row in the CSV file
        for (CSVRecord record : parser) {
            // Look at the "Exports" column
            String export = record.get("Exports");
            // Check if it contains exportOfInterest
            if (export.contains(exportOfInterest1) && export.contains(exportOfInterest2)) {
                // If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public void whoExports() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "cotton", "flowers");
    }
    
    public void countryInfo(CSVParser parser, String country){
        
        // for each row in the CSV file
        boolean check = false;
        for (CSVRecord record : parser) {
            // Look at the "Country" column
            String infoCountry = record.get("Country");
            if (infoCountry.contains(country)) {
                String info = country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                System.out.println(info);
                check = true;
                break;
            }
        }
        if (!check) System.out.println("NOT FOUND!");        
    }
    
    public void whoInfo() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        countryInfo(parser, "Nauru");
    }
    
    public void numberOfExporters(CSVParser parser, String exportItem) {
        
        // for each row in the CSV file
        int count = 0;
        for (CSVRecord record : parser) {
            String exportItens = record.get("Exports");
            if (exportItens.contains(exportItem)) count++;
        }
        System.out.println("The number os countries that export " + exportItem + " is " + count);
    }
    
    public void testNumberOfExporters() {
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        numberOfExporters(parser, "cocoa");
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        
        // for each row in the CSV file
        for(CSVRecord record : parser) {
            if (amount.length() < record.get("Value (dollars)").length()) {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }
    
    public void testbigExporters() {
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        bigExporters(parser, "$999.999.999.999");
    }
}
