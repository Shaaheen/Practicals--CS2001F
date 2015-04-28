
/**
 * A word definition has a word type and word description.
 * 
 * @author Stephan Jamieson
 * @version 23/4/2015
 */
public class Definition {
    private WordType wordType;
    private String description;
    
    /**
     * Create a definition with the given word type and description.
     */
    public Definition(WordType wordType, String description) {
        this.wordType = wordType;
        this.description = description;
    }
    
    /**
     * Obtain the word type.
     */
    public WordType getType() { return wordType; }
    
    /**
     * Obtain the description.
     */
    public String getDescription() { return description; }
    
    /**
     * Return a string representation of this definition.
     */
    public String toString() { return "("+wordType+") "+description; }
}
