import java.util.Random;

/**
 * Created by Shaaheen on 5/1/2015.
 */
public class SearchPerformanceUtil {

    public static String getNonsenseString(){
        Random rnd = new Random();
        int length = rnd.nextInt(14) + 1; //Gets random number between 1 and 15
        String word = "";
        for (int i = 0; i < length; i++) {
            char newCharacter = (char) (rnd.nextInt(122 - 97 + 1) + 97); //Will get a random number between 97 and 122 (ASCII numbers for lowercase alphabet)
            word = word + newCharacter;
        }
        return word;
    }
}
