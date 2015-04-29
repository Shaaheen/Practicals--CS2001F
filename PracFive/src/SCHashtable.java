import java.util.List;

/**
 * Created by Shaaheen on 4/29/2015.
 */
public class SCHashtable implements Dictionary {
    private static int DEFAULT_SIZE = 50;

    private ChainedEntry[] table;
    private int entries;

    public SCHashtable() { this(DEFAULT_SIZE); }

    public SCHashtable(int size) {
        this.table = new ChainedEntry[size];
        this.entries = 0;
    }

    private int hashFunction(String key) {
        // Your hash function here.
        int hashVal = 0;
        for( int i = 0; i < key.length( ); i++ )
            hashVal = 37 * hashVal + key.charAt( i );
        hashVal %= table.length;
        if( hashVal < 0 )
            hashVal += table.length;

        return hashVal;
    }


    public boolean containsWord(String word) {
        // Implement this.
        ChainedEntry listEntry = table[hashFunction(word)];
        while (listEntry != null){
            if (listEntry.getWord().equals(word)){
                return true;
            }
            listEntry = listEntry.getNext();
        }
        return false;
    }

    public List<Definition> getDefinitions(String word) {
        // Implement this.
        ChainedEntry listEntry = table[hashFunction(word)];
        while (listEntry != null){
            if (listEntry.getWord().equals(word)){
                return listEntry.getDefinitions();
            }
            listEntry = listEntry.getNext();
        }
        return null;
    }


    //Inserts Word into the dictionary with its definition
    public void insert(String word, Definition definition) {
        int hashKey = hashFunction(word); //Get hash code for word
        ChainedWord toInsert = new ChainedWord(word,definition); //The word object that will be inserted
        if (hashKey > table.length){ //if the hashkey is greater than the length of the table then loop around to beginning
            hashKey = hashKey - table.length;
        }
        if (table[hashKey] == null){
            table[hashKey] = toInsert;
        }
        else{
            while (table[hashKey].getNext() != null) {
                table[hashKey] = table[hashKey].getNext();
            }
            table[hashKey] = new ChainedWord((ChainedWord) table[hashKey],toInsert);
        }

        entries ++; //new entry
    }

    public boolean isEmpty() { return entries == 0; }

    public void empty() { this.table = new ChainedEntry[this.table.length]; this.entries=0; }

    public int size() { return this.entries; }

    /* Hash Table Functions */

    /**
     * Obtain the current load factor (entries / table size).
     */
    public double loadFactor() {
        return entries/(double)table.length;
    }


    /* DEBUGGING CODE */
    /**
     * Print the contents of the given hashtable.
     */
    public static void debug_print(SCHashtable hashtable) {
        Entry[] table = hashtable.table;
        for(int i=0; i<table.length; i++) {
            System.out.printf("\n%4d : %s", i, table[i]);
        }
    }
}
