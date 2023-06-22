
/**
 * Escreva a descrição da classe Part1 aqui.
 * 
 * @author Bruno Ramos Martins 
 * @version (número de versão ou data)
 */
public class Part1 {
    
    public Integer findStopCodon(String dna, int startIndex, String stopCodon) {
        
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if (stopIndex != -1 && (startIndex - stopIndex) % 3 == 0) {
            return stopIndex;
        }
        else {
            return dna.length();
        }
    }
    
    public String findGene(String dna) {
        
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) return "";
        
        int stopIndex = 0;
        int firstTAA = findStopCodon(dna, startIndex, "TAA");
        int firstTAG = findStopCodon(dna, startIndex, "TAG");
        int firstTGA = findStopCodon(dna, startIndex, "TGA");
        if ((firstTAA != -1) && (firstTAA < firstTAG)) {
            stopIndex = firstTAA;
        }
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

}
