package PracTwoSource.test;

import PracTwoSource.AVLTree;
import PracTwoSource.AVLTreeNode;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class AVLTreeTest extends TestCase {
    AVLTree testTree = new AVLTree();
    String path = new File(".").getAbsolutePath();


    @Test
    public void testCase1() throws Exception{
        //Set up
        testTree.insert(20);
        testTree.insert(30);
        testTree.insert(10);
        testTree.insert(12);
        testTree.insert(14);

        //Check if contains method works
        boolean containsAll = false;
        if (testTree.contains(14) && testTree.contains(30) && testTree.contains(10)){
            containsAll = true;
        }
        boolean expected = true;
        assertEquals(expected,containsAll);


        //Test to see if tree is structured correctly and that insert & delete works correctly

          //Create file from methods to test against expected
        System.out.println(new File(".").getAbsolutePath());
        File testFile = new File( path + "//PracTwoSource//test//TestTxtFiles//testFile1.txt");
        PrintStream streamtoFile = new PrintStream(testFile);
        testTree.print(streamtoFile);

          //Get expected Test File
        File expectedTestFile = new File(path + "//PracTwoSource//test//TestTxtFiles//expectedTestFile1.txt");

          //Turn File into a string so we can compare the content of both files
        String actualFileString = turnToString(testFile);
        String expectedFileString = turnToString(expectedTestFile);

          //Check if tree structures are the same
        assertEquals(expectedFileString,actualFileString);

        tearDown();
    }

    //This Case will test the evidence case given for Task 4 as evidence that it works
    @Test
    public void testCase2() throws Exception{
        //Set up - From Task 4 evidence
        testTree.insert(100); testTree.insert(150); testTree.insert(175); testTree.insert(125);
        testTree.insert(90); testTree.insert(99); testTree.insert(95);

        //Check if contains method works
        boolean containsAll = false;
        if (testTree.contains(95) && testTree.contains(150) && testTree.contains(90)){
            containsAll = true;
        }
        boolean expected = true;
        assertEquals(expected,containsAll);

        //Test to see if tree is structured correctly and that insert & delete works correctly

        //Create file from methods to test against expected
        File testFile = new File(path + "//PracTwoSource//test//TestTxtFiles//testFileAVL0.txt");
        PrintStream streamtoFile = new PrintStream(testFile);
        testTree.print(streamtoFile);

        //Get expected Test File
        File expectedTestFile = new File(path + "//PracTwoSource//test//TestTxtFiles//expectedAVL0.txt");

        //Turn File into a string so we can compare the content of both files
        String actualFileString = turnToString(testFile);
        String expectedFileString = turnToString(expectedTestFile);

        //Check if tree structures are the same
        assertEquals(expectedFileString,actualFileString);

        //Now for AVL2 evidence, have to insert more nodes
        testTree.insert(87); testTree.insert(200); testTree.insert(110); testTree.insert(130);
        testTree.insert(135);

        //Test to see if tree is structured correctly and that insert & delete works correctly

        //Create file from methods to test against expected
        File testFileNew = new File(path + "//PracTwoSource//test//TestTxtFiles//testFileAVL1.txt");
        PrintStream streamtoFileNew = new PrintStream(testFileNew);
        testTree.print(streamtoFileNew);

        //Get expected Test File
        File expectedTestFileNew = new File(path + "//PracTwoSource//test//TestTxtFiles//expectedAVL1.txt");

        //Turn File into a string so we can compare the content of both files
        String actualFileStringNew = turnToString(testFileNew);
        String expectedFileStringNew = turnToString(expectedTestFileNew);

        //Check if tree structures are the same
        assertEquals(expectedFileStringNew,actualFileStringNew);

    }

    public void tearDown() throws Exception {
        testTree = new AVLTree();
    }

    public static String turnToString(File fileToString) throws IOException {
        FileInputStream fin =  new FileInputStream(fileToString);
        BufferedReader myInput = new BufferedReader(new InputStreamReader(fin));
        String returnString = "";
        String thisLine;
        while ((thisLine = myInput.readLine()) != null) {
            returnString = returnString + thisLine + "\r\n";
        }

        return returnString;

    }



}