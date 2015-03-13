package test;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import PracticalOne.*;


public class MainClassTest extends TestCase {
    SimpleBST testTree1;
    SimpleBST testTree2;


    @Before
    public void setUp(){
        testTree1 = new SimpleBST();
        testTree2 = new SimpleBST();
    }
    
    
    @Test
    public void testSimilar1() throws Exception {
        setUp();
        testTree1.insert(25); testTree1.insert(4);testTree1.insert(30);
        testTree1.insert(12); testTree1.insert(6);testTree1.insert(20);
        testTree1.insert(18);

        testTree2.insert(16); testTree2.insert(3);testTree2.insert(50);
        testTree2.insert(13); testTree2.insert(15);testTree2.insert(10);
        testTree2.insert(14); 
        
        boolean expected = true;
        boolean actual = testTree1.similar(testTree2);
        assertEquals(expected,actual);

    }
    //30,6,35,32,45,31,34,40,100,4,14,1,5,10,28
    @Test
    public void testSimilar2() throws Exception {
        setUp();
        testTree1.insert(27); testTree1.insert(5);testTree1.insert(32);testTree1.insert(12);
        testTree1.insert(2); testTree1.insert(29); testTree1.insert(50); testTree1.insert(1);
        testTree1.insert(3); testTree1.insert(9); testTree1.insert(18);testTree1.insert(28);
        testTree1.insert(30); testTree1.insert(40);testTree1.insert(90);

        testTree2.insert(30); testTree2.insert(6);testTree2.insert(35);testTree2.insert(32);
        testTree2.insert(45); testTree2.insert(31); testTree2.insert(34); testTree2.insert(100);
        testTree2.insert(4); testTree2.insert(14); testTree2.insert(1);testTree2.insert(5);
        testTree2.insert(10); testTree2.insert(28);testTree2.insert(40);

        boolean expected = true;
        boolean actual = testTree1.similar(testTree2);
        assertEquals(expected,actual);

    }
    @Test
    public void testSimilar3() throws Exception {
        setUp();
        testTree1.insert(50); testTree1.insert(25);testTree1.insert(90);

        testTree2.insert(5); testTree2.insert(2);testTree2.insert(7);
        boolean expected = true;
        boolean actual = testTree1.similar(testTree2);
        assertEquals(expected,actual);

    }
    //Exact same shape until last branch
    @Test
    public void testDifferent1() throws Exception {
        setUp();
        testTree1.insert(25); testTree1.insert(4);testTree1.insert(26);
        testTree1.insert(12); testTree1.insert(40);testTree1.insert(18);

        testTree2.insert(17); testTree2.insert(11);testTree2.insert(94);
        testTree2.insert(14); testTree2.insert(12);testTree2.insert(100);

        boolean expected = false;
        boolean actual = testTree1.similar(testTree2);
        assertEquals(expected,actual);

    }
    //different level trees
    @Test
    public void testDifferent2() throws Exception {
        setUp();
        testTree1.insert(25); testTree1.insert(4);testTree1.insert(26);
        testTree1.insert(12); testTree1.insert(40);testTree1.insert(18);

        testTree2.insert(17); testTree2.insert(11);testTree2.insert(94);
        testTree2.insert(14); testTree2.insert(12);
        boolean expected = false;
        boolean actual = testTree1.similar(testTree2);
        assertEquals(expected,actual);

    }
    //simple difference in trees, tree structure different
    @Test
    public void testDifferent3() throws Exception {
        setUp();
        testTree1.insert(28); testTree1.insert(30);testTree1.insert(29);

        testTree2.insert(40); testTree2.insert(30);testTree2.insert(50);
        boolean expected = false;
        boolean actual = testTree1.similar(testTree2);
        assertEquals(expected,actual);

    }
}