import java.util.List;

/**
 * Created by Shaaheen on 4/29/2015.
 */
public class QPHashtable implements Dictionary {
    private final static int DEFAULT_SIZE = 50;

    private Entry[] table;
    private int entries;

    public QPHashtable() { this(DEFAULT_SIZE); }

    public QPHashtable(int size) {
        this.table = new Entry[size];
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
        if (getHashOfWord(word,hashFunction(word)) != -1){
            return true;
        }
        else{
            return false;
        }
    }
    public List<Definition> getDefinitions(String word) {
        // Implement this.
        boolean found = false;
        int key = getHashOfWord(word,hashFunction(word));
        if (key != -1){
            return table[key].getDefinitions();
        }
        else {
            return null;
        }
    }

    public int getHashOfWord(String word,int hashKey){
        if (table[hashKey] == null){
            return -1;
        }
        else if (word.equals(table[hashKey].getWord())){
            return hashKey;
        }
        else{
            return getHashOfWord(word,hashKey +1 );
        }
    }

    public void insert(String word, Definition definition) {
        // Implement this.
        int hashKey = hashFunction(word);
        boolean inserted = false;
        while (!inserted ){
            if (table[hashKey] == null){
                table[hashKey] = new Word(word,definition);
                inserted = true;
            }
            else {
                hashKey ++;
            }
        }
        entries ++;
    }

    public boolean isEmpty() { return entries == 0; }

    public void empty() { this.table = new Entry[this.table.length]; this.entries=0; }

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
    public static void debug_print(QPHashtable hashtable) {
        Entry[] table = hashtable.table;
        for(int i=0; i<table.length; i++) {
            System.out.printf("\n%4d : %s", i, table[i]);
        }
    }
}
