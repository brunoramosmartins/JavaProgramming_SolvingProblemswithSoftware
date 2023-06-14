
/**
 * Finding a Gene - Using the Simplified Algorithm.
 * 
 * @author Bruno Ramos Martins 
 * @version 
 */
public class Part1 {
    
    public String findSimpleGene(String dna) {
        
        // start codon is "ATG"
        // stop codon is "TAA"
        String result = "";
        int startIndex = dna.indexOf("ATG");
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        
        if (startIndex == -1 || stopIndex == -1 || (startIndex - stopIndex) % 3 != 0) {
            return "";
        }
        
        
        result = dna.substring(startIndex, stopIndex + 3);
        return result;
        
    }
    
    public void testSimpleGene() {
        
        String dna = "AATACGTAATAGGGT"; // DNA with no "ATG"
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);

        dna = "AATGCTAGGGTTATATGGT"; // DNA with no "TAA"
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);

        dna = "ATCCTATACTTCGGCTGCTCTTATATTGT"; // DNA with no "ATG" or "TAA"
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATCCTATGCTTCGGCTGCTCTAATATGGT";; // DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene)
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);

        dna = "ATGATTGATAA"; // DNA with ATG, TAA and the substring between them is not a multiple of 3
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATGTAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
    }

}
