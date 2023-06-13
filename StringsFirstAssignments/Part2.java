
/**
 * Escreva a descrição da classe Part2 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Part2 {
    
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        
        // start codon is "ATG"
        // stop codon is "TAA"
        String result = "";
        int startIndex = dna.toUpperCase().indexOf(startCodon.toUpperCase());
        int stopIndex = dna.toUpperCase().indexOf(stopCodon.toUpperCase(), startIndex);
        
        if (startIndex == -1 || stopIndex == -1 || (startIndex - stopIndex) % 3 != 0) {
            return "";
        }
        
        
        result = dna.substring(startIndex, stopIndex + 3);
        return result;
        
    }
    
    public void testSimpleGene() {
        
        String dna = "AATACGTAATAGGGT"; // DNA with no "ATG"
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna, "ATG", "TTA");
        System.out.println("Gene is " + gene);

        dna = "AATGCTAGGGTTATATGGT"; // DNA with no "TAA"
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TTA");
        System.out.println("Gene is " + gene);

        dna = "ATCCTATACTTCGGCTGCTCTTATATTGT"; // DNA with no "ATG" or "TAA"
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TTA");
        System.out.println("Gene is " + gene);
        
        dna = "ATCCTATGCTTCGGCTGCTCTAATATGGT";; // DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene)
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TTA");
        System.out.println("Gene is " + gene);

        dna = "ATGATTGATAA"; // DNA with ATG, TAA and the substring between them is not a multiple of 3
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TTA");
        System.out.println("Gene is " + gene);
        
        dna = "ATGTAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TTA");
        System.out.println("Gene is " + gene);
    }

}
