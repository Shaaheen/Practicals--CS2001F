package PracFiveSource.test;

import PracFiveSource.*;
import junit.framework.TestCase;

import java.util.ArrayList;

public class SCHashtableTest extends TestCase {
    Dictionary hashTable;

    public void setUp() throws Exception {
        super.setUp();
        hashTable = new SCHashtable(3739);
        FileUtil.load(hashTable,"lexicon.txt");
    }

    //Tests contains method by going through 50 random trials where a random lexicon word and a nonsense word are used
    public void testContainsWord() throws Exception {
        setUp();
        for (int i = 0; i < 50; i++){
            assertEquals(true,hashTable.containsWord(FileUtil.getRandomLexiconWord()));//checks if a random lexicon word is in the hashtable
            assertEquals(false,hashTable.containsWord(FileUtil.getNonsenseString())); //Might fail if by some miracle the nonsense word does exist in the lexicon dictionary
        }
    }

    //Tests if the get definition function returns the correct definition list
    public void testGetDefinitions() throws Exception {
        //Normal test case
        ArrayList<Definition> def = new ArrayList<Definition>();
        def.add(new Definition(WordType.toWordType("a"),"test"));
        def.add(new Definition(WordType.toWordType("n"),"multiple tests"));

        hashTable.insert("testWord",new Definition(WordType.toWordType("a"),"test"));
        hashTable.insert("testWord",new Definition(WordType.toWordType("n"),"multiple tests"));

        assertEquals(def.toString(), (hashTable.getDefinitions("testWord")).toString());

        //Boundary case with only one definition
        ArrayList<Definition> def2 = new ArrayList<Definition>();
        def2.add(new Definition(WordType.toWordType("a"),"test2"));

        hashTable.insert("testWord2",new Definition(WordType.toWordType("a"),"test2"));

        assertEquals(def2.toString(),(hashTable.getDefinitions("testWord2")).toString());

        //Boundary value with multiple definitions
        ArrayList<Definition> def3 = new ArrayList<Definition>();
        def3.add(new Definition(WordType.toWordType("a"),"test 1"));
        def3.add(new Definition(WordType.toWordType("a"),"test 2"));
        def3.add(new Definition(WordType.toWordType("a"),"test 3"));
        def3.add(new Definition(WordType.toWordType("a"),"test 4"));
        def3.add(new Definition(WordType.toWordType("a"),"test 5"));
        def3.add(new Definition(WordType.toWordType("a"),"test 6"));

        hashTable.insert("testWord3",new Definition(WordType.toWordType("a"),"test 1"));
        hashTable.insert("testWord3",new Definition(WordType.toWordType("a"),"test 2"));
        hashTable.insert("testWord3",new Definition(WordType.toWordType("a"),"test 3"));
        hashTable.insert("testWord3",new Definition(WordType.toWordType("a"),"test 4"));
        hashTable.insert("testWord3",new Definition(WordType.toWordType("a"),"test 5"));
        hashTable.insert("testWord3",new Definition(WordType.toWordType("a"),"test 6"));

        assertEquals(def3.toString(),(hashTable.getDefinitions("testWord3")).toString());
    }

    //tests if insert method actually inserts word
    public void testInsert() throws Exception {
        setUp();
        hashTable.insert("helloo",new Definition(WordType.toWordType("n"),"Testing word"));
        assertEquals(true, hashTable.containsWord("helloo"));
    }

    //Checks isEmpty() method is correct
    public void testIsEmpty() throws Exception {
        setUp();
        assertEquals(false,hashTable.isEmpty());
    }

    public void testEmpty() throws Exception {
        setUp();
        hashTable.empty();
        assertEquals(true, hashTable.isEmpty());
    }

    //Tests if table has the correct size
    public void testSize() throws Exception {
        setUp();
        assertEquals(3739,hashTable.size());
    }

    //Tests if load factor method works
    public void testLoadFactor() throws Exception {
        assertEquals(1.0,((SCHashtable) hashTable).loadFactor());
    }
}