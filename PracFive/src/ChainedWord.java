/**
 * Created by Shaaheen on 4/29/2015.
 * A chained word entry that is for a Seperate Chaining Hashtable which contains a link to the next chained word
 */
public class ChainedWord extends Word implements ChainedEntry{
    ChainedWord next; //Each chained word has a link to the next object

    //Constructor - inherits from Word object
    public ChainedWord(String word, Definition def) {
        super(word, def);
        next = null;
    }

    //Gets the object that is linked to this ChainedWord
    public ChainedWord getNext(){
        return next;
    }

}
