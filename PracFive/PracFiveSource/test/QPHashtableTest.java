package PracFiveSource.test;

import PracFiveSource.FileUtil;
import PracFiveSource.QPHashtable;

public class QPHashtableTest extends SCHashtableTest {

    public void setUp() throws Exception {
        super.setUp();
        hashTable = new QPHashtable(7481);
        FileUtil.load(hashTable, "lexicon.txt");
    }

    //Tests if load factor is lower than 0.5 as the rehashing operations will be called if any higher than this
    public void testLoadFactor() throws Exception {
        assertTrue(((QPHashtable) hashTable).loadFactor() < 0.5);
    }

}