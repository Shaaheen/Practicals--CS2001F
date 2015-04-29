import java.util.List;
/**
 * Simple hash table implementation of Dictionary using linear probing.
 * 
 * @author Stephan Jamieson 
 * @version 24/4/2015
 */
public class LPHashtable implements Dictionary
{
    private final static int DEFAULT_SIZE = 50;
 
    private Entry[] table;
    private int entries;
 
    public LPHashtable() { this(DEFAULT_SIZE); }
    
    public LPHashtable(int size) { 
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
        if (getHashOfWord(word,hashFunction(word),0) != -1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public List<Definition> getDefinitions(String word) {
        // Implement this.
        boolean found = false;
        int key = getHashOfWord(word,hashFunction(word),0);
        if (key != -1){
            return table[key].getDefinitions();
        }
        else {
            return null;
        }
    }

    public int getHashOfWord(String word,int hashKey,int noOfProbes){
        if (hashKey > table.length){
            hashKey = hashKey - table.length;
        }
        if (noOfProbes > table.length) {
            //throw Exception;
        }
        if (table[hashKey] == null){
            return -1;
        }
        else if (word.equals(table[hashKey].getWord())){
            return hashKey;
        }
        else{
            noOfProbes++;
            return getHashOfWord(word,hashKey + 1,noOfProbes);
        }
    }

    //Inserts Word into the dictionary with its defintion
    public void insert(String word, Definition definition) {
        int hashKey = hashFunction(word); //Get hash code for word
        boolean inserted = false; //Used for while
        Word toInsert = new Word(word,definition); //The word object that will be inserted
        //Loops until word is inserted into hashtable or until the probing fails
        while (!inserted ){
            if (hashKey > table.length){ //if the hashkey is greater than the lenght of the table then loop around to beginning
                hashKey = hashKey - table.length;
            }
            if (toInsert.probe > table.length) { //if the hashtable has probed the enrty more than the size of table then probing failed
                //throw Exception;
            }
            if (table[hashKey] != null){ // if found empty position in table then put in word
                if (table[hashKey].getWord().equals(word)){
                    table[hashKey].addDefinition(definition); // If word already exist then add a definition to it
                    inserted = true;
                }
                else {
                    toInsert.addProbe(); //add probe value to be stored in word object
                    hashKey = hashKey + 1; // add Linear probe value to hashkey (probe squared)
                }
            }
            else {
                table[hashKey] = toInsert;
                inserted = true; //ends loop
            }

        }
        entries ++; //new entry
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
    public static void debug_print(LPHashtable hashtable) {
        Entry[] table = hashtable.table;
        for(int i=0; i<table.length; i++) {
            System.out.printf("\n%4d : %s", i, table[i]);
        }
    }
            
}
