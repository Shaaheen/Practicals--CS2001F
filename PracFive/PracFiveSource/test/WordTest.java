package PracFiveSource.test;

import PracFiveSource.Definition;
import PracFiveSource.Word;
import PracFiveSource.WordType;
import junit.framework.TestCase;

import java.util.ArrayList;

public class WordTest extends TestCase {

    Word testWrd;
    public void setUp() throws Exception {
        super.setUp();
        testWrd = new Word("testWord",new Definition(WordType.toWordType("n"),"test definition"));
    }

    public void testAddProbe() throws Exception {
        setUp();
        testWrd.addProbe();
        assertEquals(1, testWrd.probe);
    }

    public void testGetWord() throws Exception {
        assertEquals("testWord",testWrd.getWord());
    }

    public void testGetDefinitions() throws Exception {
        ArrayList<Definition> def = new ArrayList<Definition>();
        def.add(new Definition(WordType.toWordType("n"),"test definition"));

        assertEquals(def.toString(),(testWrd.getDefinitions()).toString());
    }

    public void testAddDefinition() throws Exception {
        testWrd.addDefinition(new Definition(WordType.toWordType("a"),"2 test definition"));
        ArrayList<Definition> def = new ArrayList<Definition>();
        def.add(new Definition(WordType.toWordType("n"),"test definition"));
        def.add(new Definition(WordType.toWordType("a"),"2 test definition"));

        assertEquals(def.toString(),(testWrd.getDefinitions()).toString());

        testWrd.addDefinition(WordType.toWordType("a"), "3 test definition");
        def.add(new Definition(WordType.toWordType("a"),"3 test definition"));

        assertEquals(def.toString(),(testWrd.getDefinitions()).toString());
    }

    public void testIsEntryFor() throws Exception {
        assertEquals(true,testWrd.isEntryFor("testWord"));
    }
}