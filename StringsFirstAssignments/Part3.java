
/**
 * Returns true if stringa appears at least twice in stringb, otherwise it returns false
 * 
 * @author Bruno Ramos Martins
 * @version (número de versão ou data)
 */
public class Part3 {
    
    public boolean twoOccurrences(String stringa, String stringb) {
        
        int firstTime = stringb.indexOf(stringa);
        int secondTime = stringb.indexOf(stringa, firstTime + stringa.length());
        
        if (firstTime == -1 || secondTime == -1) return false;
        else return true;
    }
    
    public String lastPart(String stringa, String stringb) {
        
        int firstTime = stringb.indexOf(stringa);
        
        if (firstTime == -1) return stringb;
        else                 return stringb.substring(firstTime + stringa.length(), stringb.length());
        
    }
    
    public void testtwoOccurrences() {
        
        String stringa = "ana";
        String stringb = "banana";
        System.out.println("Teste para saber se " + stringa + " aparece mais de uma vez na " + stringb + " : " + twoOccurrences(stringa, stringb));
        
        stringa = "le";
        stringb = "paralelepipedo";
        System.out.println("Teste para saber se " + stringa + " aparece mais de uma vez na " + stringb + " : " + twoOccurrences(stringa, stringb));
        
        stringa = "uno";
        stringb = "Bruno";
        System.out.println("Teste para saber se " + stringa + " aparece mais de uma vez na " + stringb + " : " + twoOccurrences(stringa, stringb));
        
        stringa = "an";
        stringb = "banana";
        System.out.println(lastPart(stringa, stringb));
        
        stringa = "zoo";
        stringb = "forest";
        System.out.println(lastPart(stringa, stringb));
        
    }

}
