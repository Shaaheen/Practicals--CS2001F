package PracFiveSource;
import java.util.List;

/**
 * Created by Shaaheen on 4/29/2015.
 * Seperate Chaining Hashtable implementation
 */
public class SCHashtable implements Dictionary {
    private static int DEFAULT_SIZE = 50;

    private ChainedEntry[] table;
    private int entries; //keeps track of entries in table
    public int totalProbes; //To keep track of total probes that hashTable has done
    public int searchProbes; //keeps number of probes taken to search for a word

    public SCHashtable() { this(DEFAULT_SIZE); }

    //Constructor method that sets instance variables
    public SCHashtable(int size) {
        this.table = new ChainedEntry[size];
        this.entries = 0;
        this.totalProbes = 0;
        this.searchProbes = 0;
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
        ChainedEntry listEntry = table[hashFunction(word)]; //Gets the Chained entry object as if word exist then it will exist in the hash functions key
        //Loop that goes through the linked list found at the index in the table
        while (listEntry != null){
            if (listEntry.getWord().equals(word)){ //If a word in the linked list is the same as the word searching for then end everything and return true
                return true;
            }
            listEntry = listEntry.getNext(); //go onto next entry in linked list if word not found
        }
        return false; //will return false if the entry is never found
    }

    //Gets the definitions of a given word
    public List<Definition> getDefinitions(String word) {
        // Implement this.
        ChainedEntry listEntry = table[hashFunction(word)]; //Gets linked list stored at a words hashed index
        while (listEntry != null){ //Traverses through linked list
            searchProbes = searchProbes + 1; //since had to go through an entry in linked list add to the search probes
            if (listEntry.getWord().equals(word)){ //if found word then return the list of defintions associated with the word
                return listEntry.getDefinitions();
            }
            listEntry = listEntry.getNext();
        }
        return null;
    }


    //Inserts Word into the dictionary with its definition
    public void insert(String word, Definition definition) {
        int probes = 0;
        int hashKey = hashFunction(word); //Get hash code for word
        ChainedWord toInsert = new ChainedWord(word,definition); //The word object that will be inserted
        if (hashKey >= table.length){ //if the hashkey is greater than the length of the table then loop around to beginning
            hashKey = hashKey - table.length;
        }
        if (table[hashKey] == null){ //If the space is empty then add the chained word
            table[hashKey] = toInsert;
        }
        else{
             //Finds the last ChainedWord object in the Linked List.
            ChainedEntry current = table[hashKey];
            probes++;
            while (current.getNext() != null) {
                if (current.getWord().equals(word)){ //If find an already existing word in list then add to its definitions
                    current.addDefinition(definition);
                    return;
                }
                probes++;
                current = current.getNext(); // Move to next Node
            }
            if (current.getWord().equals(word)){ //If last node is the word to be added then just add definisiont
                current.addDefinition(definition);
                return;
            }
            ((ChainedWord) current).next = toInsert; //Adds a new Chained Word to the end of the Linked List
        }
        totalProbes = totalProbes + probes;
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
    public static void debug_print(SCHashtable hashtable) {
        Entry[] table = hashtable.table;
        for(int i=0; i<table.length; i++) {
            System.out.printf("\n%4d : %s", i, table[i]);
        }
    }
}
