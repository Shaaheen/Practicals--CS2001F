package PracFiveSource;
import java.util.List;
/**
 * ADT for Hashtable word entry.
 * 
 * Entry objects are intended for use in a hashtable implementation of the dictionary interface. 
 * 
 * An entry object aggregates a word and its definitions.
 * 
 * @author Stephan Jamieson
 * @version 23/4/2015
 */
public interface Entry {
    
    /**
     * Obtain the word defined in this entry.
     */
    String getWord();
    
    /**
     * Obtain the definitions for the word defined in this entry.
     */
    List<Definition> getDefinitions();
    
    /**
     * Add a definition consisting of the given word type and word description.
     */
    void addDefinition(WordType wordType, String description);
    /**
     * Add the given definition.
     */
    void addDefinition(Definition definition);
    
    /**
     * Determine whether this entry is for the given word.
     */
    public boolean isEntryFor(String word);
}
