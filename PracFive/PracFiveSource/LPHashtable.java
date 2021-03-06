package PracFiveSource;
import java.util.List;
/**
 * Simple hash table implementation of Dictionary using linear probing.
 * 
 * @author Stephan Jamieson 
 * @version 24/4/2015
 *
 * Modified by Shaaheen Sacoor on 4/29/2015.
 * Linear Probing Hashtable implementation
 */
public class LPHashtable implements Dictionary
{
    private final static int DEFAULT_SIZE = 50;
 
    private Entry[] table;
    private int entries; //keeps track of number of entries in table
    public int totalProbes; //To keep track of total probes that hashTable has done
    boolean performanceTest = false; //Variable to make sure rehashing doesn't occur while doing performance tests.
    public int searchProbes; //keeps track of amount of probes taken when searching
 
    public LPHashtable() { this(DEFAULT_SIZE); }

    //Constructor method
    public LPHashtable(int size) {
        this.table = new Entry[size];
        this.entries = 0;
        this.totalProbes = 0;
        this.searchProbes = 0;
    }


    //Will rehash table if load factor is more than 0.75 as if it is more than 0.75 a insert is not guaranteed
    private void validateHashTable(){
        //This if statement is never met while running the performance tests as this method
        //would keep expanding the table when need be, which will make it unfair to compare to other hashtable
        if (loadFactor()>0.75 && !performanceTest){
            int size = (int) (table.length * 1.5); //increase the tables size by 50%

            //This statement rehashes the table - Expands the tables size of the hashtable whilst
            //still keeping all the entries in the same positions
            this.table = new Entry[size];
        }
    }

    //Method to get a key value when given a string word - Taken from textbook
    private int hashFunction(String key) {
        int hashVal = 0;
        for( int i = 0; i < key.length( ); i++ )
            hashVal = 37 * hashVal + key.charAt( i );
        hashVal %= table.length;
        if( hashVal < 0 )
            hashVal += table.length;

        return hashVal;
    }

    //Method to check if a word exist in the table
    public boolean containsWord(String word) {
        //Gets the proper hash of word, probing through table if need be and checking if exists
        //If value is -1 then word was not found
        if (getHashOfWord(word,hashFunction(word),0) != -1)
            return true;
        else{
            return false;
        }
    }

    //Gets definitions for a specified word
    public List<Definition> getDefinitions(String word) {
        //gets the proper hashkey of a word, probing if need be and checks if it exist then returning the list of definitions
        int key = getHashOfWord(word,hashFunction(word),0);
        if (key != -1){
            return table[key].getDefinitions();
        }
        else {
            return null;
        }
    }

    //Recursively find the hash key of a word, probing through the table if need be and returning -1 if not found
    public int getHashOfWord(String word,int hashKey,int noOfProbes){
        while (hashKey >= table.length){ // if hashkey surpassed table size then deduct the table size amount from it to loop around
            hashKey = hashKey - table.length;
        }
        if (noOfProbes > table.length) { //Probing fails so word can't exist
            searchProbes = searchProbes + noOfProbes; //add to search probes as it took this many probes to find out word doesn't exist
            return -1;
        }
        if (table[hashKey] == null){ //if the position of the hash key is empty then the word must not exist
            searchProbes = searchProbes + noOfProbes; //add to search probes as it took this many to probes to find word doesn't exist
            return -1;
        }
        else if (word.equals(table[hashKey].getWord())){ //found a non-empty value, now will check if it is right word
            searchProbes = searchProbes + noOfProbes;//add to search probes as it took this many to probes to find word exists
            return hashKey;
        }
        else{               //if not correct word then follow along the appropriate probing strategy
            noOfProbes ++;
            return getHashOfWord(word,hashKey + 1 ,noOfProbes);
        }
    }

    //Inserts Word into the dictionary with its defintion
    public void insert(String word, Definition definition) {
        validateHashTable();
        int hashKey = hashFunction(word); //Get hash code for word
        boolean inserted = false; //Used for while
        Word toInsert = new Word(word,definition); //The word object that will be inserted
        //Loops until word is inserted into hashtable or until the probing fails
        while (!inserted ){
            while (hashKey >= table.length){ //if the hashkey is greater than the lenght of the table then loop around to beginning
                hashKey = hashKey - table.length;
            }
            if (toInsert.probe > table.length) { //if the hashtable has probed the enrty more than the size of table then probing failed
                throw new IndexOutOfBoundsException();
            }
            if (table[hashKey] != null){ // if found empty position in table then put in word
                if (table[hashKey].getWord().equals(word)){
                    table[hashKey].addDefinition(definition); // If word already exist then add a definition to it
                    entries--;//New entry not made so cancel out later addition
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
        totalProbes = totalProbes + ((Word) table[hashKey]).probe;
        entries ++; //new entry
    }

    //checks no entries are in table
    public boolean isEmpty() { return entries == 0; }

    //Empties out the table
    public void empty() { this.table = new ChainedEntry[this.table.length]; this.entries=0; }

    //returns the number of entries in table
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
