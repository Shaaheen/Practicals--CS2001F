import java.util.List;

/**
 * Created by Shaaheen on 4/29/2015.
 */
public class QPHashtable implements Dictionary {
    private static int DEFAULT_SIZE = 50;

    private Entry[] table;
    private int entries;

    public QPHashtable() { this(DEFAULT_SIZE); }

    public QPHashtable(int size) {
        size = primeSize(size); //Will make sure that size is a prime number
        this.table = new Entry[size];
        this.entries = 0;
    }

    //Returns a prime size number
    private int primeSize(int size){
       while (!checkPrime(size)){ //keep adding size by 1 until finds a prime size
           size++;
       }
        return size;
    }

    //checks if a number is a prime number
    private boolean checkPrime(int size){
        boolean prime = true;
        //if can divide by two then it is not prime
        if (size%2==0)  prime = false;
        //if not, then just check the odds as all even numbers are divisible by 2
        for(int i = 3; i*i <= size; i += 2) {
            if(size % i == 0)
                prime = false;
        }
        return prime;
    }

    //Will rehash table if load factor is more than 0.5 as if it is more than 0.5 a insert is not guaranteed
    private void validateHashTable(){
        if (loadFactor()>0.5){
            int size = (int) (table.length * 1.5);
            this.table = new Entry[size];
        }
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
        if (hashKey > table.length){ // if hashkey surpassed table size then deduct the table size amount from it to loop around
            hashKey = hashKey - table.length;
        }
        if (noOfProbes > table.length) { //Probing fails so word can't exist
            return -1;
        }
        if (table[hashKey] == null){ //if the position of the hash key is empty then the word must not exist
            return -1;
        }
        else if (word.equals(table[hashKey].getWord())){ //found a non-empty value, now will check if it is right word
            return hashKey;
        }
        else{               //if not correct word then follow along the appropriate probing strategy
            noOfProbes ++;
            return getHashOfWord(word,hashKey + (noOfProbes * noOfProbes) ,noOfProbes);
        }
    }

    //Inserts Word into the dictionary with its definition
    public void insert(String word, Definition definition) {
        validateHashTable(); //Makes sure the load factor is smaller than 0.5
        int hashKey = hashFunction(word); //Get hash code for word
        boolean inserted = false; //Used for while
        Word toInsert = new Word(word,definition); //The word object that will be inserted
        //Loops until word is inserted into hashtable or until the probing fails
        while (!inserted ){
            if (hashKey > table.length){ //if the hashkey is greater than the length of the table then loop around to beginning
                hashKey = hashKey - table.length;
            }
            if (toInsert.probe > table.length) { //if the hashtable has probed the entry more than the size of table then probing failed
                //throw Exception;
                System.out.println("EXCEPTION");
            }
            if (table[hashKey] != null){ // if found empty position in table then put in word
                if (table[hashKey].getWord().equals(word)){
                    table[hashKey].addDefinition(definition); // If word already exist then add a definition to it
                    entries --; // Reduce entries to cancel out when need to add to entries as a new entry is not being made
                    inserted = true;
                }
                else {
                    toInsert.addProbe(); //add probe value to be stored in word object
                    hashKey = hashKey + ((toInsert.probe)*(toInsert.probe)); // add Quadtratic probe value to hashkey (probe squared)
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
    public static void debug_print(QPHashtable hashtable) {
        Entry[] table = hashtable.table;
        for(int i=0; i<table.length; i++) {
            System.out.printf("\n%4d : %s", i, table[i]);
        }
    }
}
