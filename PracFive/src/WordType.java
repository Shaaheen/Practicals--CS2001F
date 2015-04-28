
/**
 * Enumeration class WordType.
 * 
 * A word is a verb, noun, or adjective.
 * 
 * 
 * @author Stephan Jamieson
 * @version 23/4/2015
 */
public enum WordType
{
    NOUN, VERB, ADJECTIVE;
    
    /**
     * Obtain the WordType value for the type represented by the given string: "n" or "noun", 
     * "a" or "adjective", "v" or "verb".
     */
    public static WordType toWordType(String string) {
        string=string.toLowerCase().trim();
        if (string.equals("n")||string.equals("noun")) {
            return NOUN;
        }
        else if (string.equals("a")||string.equals("adjective")) {
            return ADJECTIVE;
        }
        else if (string.equals("v")||string.equals("verb")) {
            return VERB;
        }
        else {
            throw new IllegalArgumentException("WordType.toWordType("+string+"): argument not recognised.");
        }
    }
            
    /** 
     * Obtain a String representation of this WordType value.
     */     
    public String toString() { return super.toString().toLowerCase(); }
}
