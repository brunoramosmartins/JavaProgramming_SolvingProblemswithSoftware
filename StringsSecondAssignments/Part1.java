
/**
 * Escreva a descrição da classe Part1 aqui.
 * 
 * @author Bruno Ramos Martins 
 * @version (número de versão ou data)
 */
public class Part1 {
    
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna) {
        
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) return "";
        
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        
        if (minIndex == dna.length()){
            return "";
        }
        
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public void testFindStopCodon() {
        
        String dna = "AATGATACGTAATAGGGT";
        System.out.println("DNA strand is " + dna);
        int gene = findStopCodon(dna, 1, "TTA");
        if (gene == dna.length()) {
            System.out.println("No valid genes. DNA chain length is " + gene);
        }
        else {
            System.out.println("The first stop Codon is " + gene + " index.");
        }
        
        dna = "AATGCTAGGGTTATATGGT";
        System.out.println("DNA strand is " + dna);
        gene = findStopCodon(dna, 1, "TTA");
        if (gene == dna.length()) {
            System.out.println("No valid genes. DNA chain length is " + gene);
        }
        else {
            System.out.println("The first stop Codon is " + gene + " index.");
        }

        dna = "ATGCTATACTTCGGCTGCTCTTATATTGT";
        System.out.println("DNA strand is " + dna);
        gene = findStopCodon(dna, 0, "TTA");
        if (gene == dna.length()) {
            System.out.println("No valid genes. DNA chain length is " + gene);
        }
        else {
            System.out.println("The first stop Codon is " + gene + " index.");
        }
        
        dna = "ATCCTATGCTTCGGCTGCTCTAATATGGT";
        System.out.println("DNA strand is " + dna);
        gene = findStopCodon(dna, 5, "TTA");
        if (gene == dna.length()) {
            System.out.println("No valid genes. DNA chain length is " + gene);
        }
        else {
            System.out.println("The first stop Codon is " + gene + " index.");
        }

        dna = "ATGATTGATAA";
        System.out.println("DNA strand is " + dna);
        gene = findStopCodon(dna, 0, "TTA");
        if (gene == dna.length()) {
            System.out.println("No valid genes. DNA chain length is " + gene);
        }
        else {
            System.out.println("The first stop Codon is " + gene + " index.");
        }
    }
    
    public void testFindGene() {
        
    }
    
    public void printAllGenes() {
        
    }
}
