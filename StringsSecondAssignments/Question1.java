
/**
 * Escreva a descrição da classe Question1 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Question1 {
    
    public void findAbc(String input) {
    int index = input.indexOf("abc");
    while (true) {
        if (index == -1) {
            break;
        }
        String found = input.substring(index+1, index+4);
        System.out.println(found);
        index = input.indexOf("abc", index+4);
    }
}
   public void test() {
       findAbc("abcd");
       findAbc("abcdabc");
}

}
