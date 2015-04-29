/**
 * Created by Shaaheen on 4/29/2015.
 */
public class ChainedWord extends Word implements ChainedEntry{
    ChainedWord next;
    public ChainedWord(String word, Definition def) {
        super(word, def);
        next = null;
    }
    public ChainedWord(ChainedWord current,ChainedWord nextLink){
        super();
        this.word = current.word;
        this.definitions = current.definitions;
        this.probe = current.probe;
        this.next = nextLink;
    }

    public ChainedWord getNext(){
        return next;
    }

}
