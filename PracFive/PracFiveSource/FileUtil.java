package PracFiveSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Module containing utility methods.
 * 
 * @author Shaaheen Sacoor
 * @version 29/4/2015
 */
public class FileUtil {

    private FileUtil() {}

    //Loads in the words and definitions from the lexicon dictionary and inserts them into a dictionary
    public static void load(Dictionary dictionary, String filename) throws IOException {
        BufferedReader bufReader = new BufferedReader( new FileReader(filename)); //Accesses file
        String line = null;
        while( ( line = bufReader.readLine() ) != null ) { //Traverses through file
            int indxWord = line.indexOf(":", 4); //Finds the second colon in line
            String word = line.substring(5, indxWord - 1); //Indexes word out of line
            String description = ""; //default description is empty as there are words without defitions
            if (indxWord +1 != line.length()){
                description = line.substring(indxWord+2, line.length()); //index and add definition
            }
            //Creates Definition object based on word type and defintion from line
            dictionary.insert(word, new Definition(WordType.toWordType(line.substring(0, 1)), description)); //insert into dictionary
        }
    }

    //Gets a random word from the lexicon dictionary
    public static String getRandomLexiconWord() throws IOException {
        Random rnd = new Random();
        int i = 0;
        int randomLine = rnd.nextInt(4996) + 1; //Gets random number between 1 and 4997
        BufferedReader bufReader = new BufferedReader( new FileReader("lexicon.txt")); //accesses file
        String line = null;
        while ( i < randomLine){ //traverses through list until reaches the random line number
            line = bufReader.readLine(); //gets line string
            i++;
        }
        int indxWord = line.indexOf(":", 4);
        String word = line.substring(5, indxWord - 1); //gets word string out of line
        return word;
    }

    //returns a random string of random characters
    public static String getNonsenseString(){
        Random rnd = new Random();
        int length = rnd.nextInt(14) + 1; //Gets random number between 1 and 15 - Max word size is 15 here
        String word = "";
        for (int i = 0; i < length; i++) { //Loops through to add a string for whole word length
            //assigns char variable to and ASCII number - gets appropriate character
            char newCharacter = (char) (rnd.nextInt(122 - 97 + 1) + 97); //Will get a random number between 97 and 122 (ASCII numbers for lowercase alphabet)
            word = word + newCharacter; //adds character to word
        }
        return word;
    }

}
