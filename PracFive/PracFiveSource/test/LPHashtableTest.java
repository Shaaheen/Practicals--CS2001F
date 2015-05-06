package PracFiveSource.test;

import PracFiveSource.FileUtil;
import PracFiveSource.LPHashtable;

public class LPHashtableTest extends SCHashtableTest {

    public void setUp() throws Exception {
        super.setUp();
        hashTable = new LPHashtable(7481);
        FileUtil.load(hashTable, "lexicon.txt");
    }

    //Tests if load factor is lower than 0.75 as the rehashing operations will be called if any higher than this
    public void testLoadFactor() throws Exception {
        assertTrue(((LPHashtable) hashTable).loadFactor() < 0.75);
    }

}