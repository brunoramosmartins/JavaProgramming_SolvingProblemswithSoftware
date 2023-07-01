
/**
 * Escreva a descrição da classe BabyBirths aqui.
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
            System.out.println("Name " + rec.get(0) + 
                               " Gender " + rec.get(1) + 
                               " Num Born " + rec.get(2));
        }
    }
    

}
