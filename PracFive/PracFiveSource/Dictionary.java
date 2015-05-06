package PracFiveSource;
import java.util.List;
/**
 * ADT for a dictionary type of object.
 * 
 * A dictionary contains words and their definitions. A word may have more than one definition.
 * A definition consists of the word type (noun, adjective, verb) and a description.
 * 
 * @author Stephan Jamieson 
 * @version 23/4/2015
 */
public interface Dictionary {
    /**
     * Returns true if this dictionary contains a definition for the specified word.
     */
    boolean containsWord(String word);
    
    /**
     * Return the entry for the specified word, or null if this 
     * dictionary contains no entry for the word.
     */
    List<Definition> getDefinitions(String word);
    
    /**
     * Returns true if this dictionary contains no words.
     */
    boolean isEmpty();
    
    /**
     * Removes all words from the dictionary.
     */
    void empty();
    
    /**
     * Inserts the given word and definition.
     * 
     */
    void insert(String word, Definition definition);
    
    /**
     * Returns the number of words in this dictionary.
     */
    int size();
}
    