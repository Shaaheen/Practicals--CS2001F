package PracFiveSource;
import java.util.List;
import java.util.Scanner;
/**
 * Simple electronic dictionary program.
 * 
 * @author Stephan Jamieson
 * @version 23/4/2015
 */
public class UserInterface {
    
    private UserInterface() {}
    
    public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Choose a hash table implementation:");
        System.out.println("1. Linear Probing.");
        System.out.println("2. Quadratic Probing.");
        System.out.println("3. Separate Chaining.");        
        int selection = input.nextInt();

        Dictionary dictionary = null;
  
        switch (selection) {
            case 1:
                dictionary = new LPHashtable(7481);
                break;
             case 2:
                 dictionary = new QPHashtable(7481);
                 break;
            case 3:
                 dictionary = new SCHashtable(3739);
                 break;
          default: 
                System.out.println("Selection not recognised.");
                System.exit(-1);
        }
        
        FileUtil.load(dictionary, "lexicon.txt");
        
        System.out.println("Enter a word (or '#quit'):");
        String word = input.next().toLowerCase();
        while (!word.equals("#quit")) {
            List<Definition> definitions = dictionary.getDefinitions(word);
            if (definitions==null) {
                System.out.println("No entry for this word.");
            }
            else {
                System.out.println(definitions);
            }           
            System.out.println("Enter a word (or '#quit'):");
            word = input.next().toLowerCase();
        }        
        
        
        
        
        
     }
}
