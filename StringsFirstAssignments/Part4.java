
import edu.duke.*;
// import java.io.File;

/**
 * Escreva a descrição da classe Part4 aqui.
 * 
 * @author Bruno Ramos Martins 
 * @version (número de versão ou data)
 */
public class Part4 {
    
    public void findYouTubeLink()  {
    
    URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    String youtube = "youtube.com";
    String StartStop = "\"";
    String link = "";
    for (String word : ur.words()) {
        if (word.toLowerCase().indexOf(youtube) != -1) {
            
            System.out.println(word);
            // Encontramos um link
            int indexStart = word.indexOf(StartStop); // Inicio do link
            int indexStop = word.indexOf(StartStop, indexStart + 1); // Final do link
            link = word.substring(indexStart, indexStop); // Recorte do link
            System.out.println(link);
        }
    }
}   
}
