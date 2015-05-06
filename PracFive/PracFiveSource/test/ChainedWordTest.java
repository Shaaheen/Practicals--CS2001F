package PracFiveSource.test;

import PracFiveSource.ChainedWord;
import PracFiveSource.Definition;
import PracFiveSource.WordType;

public class ChainedWordTest extends WordTest {

    public void setUp() throws Exception {
        super.setUp();
        testWrd = new ChainedWord("testWord",new Definition(WordType.toWordType("n"),"test definition"));
    }

    public void testGetNext() throws Exception {
        assertEquals(null,((ChainedWord) testWrd).getNext());
    }
}