package PracFiveSource;
/**
 * Created by Shaaheen on 4/29/2015.
 * Interface for a chained entry into a hashtable
 */
public interface ChainedEntry extends Entry {
    ChainedEntry getNext();
}